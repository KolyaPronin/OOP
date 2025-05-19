package ru.nsu.pronin.git;

import ru.nsu.pronin.model.Student;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class GitHandler {
    private final File workingDir;

    public GitHandler(File workingDir) {
        this.workingDir = workingDir;
        if (!workingDir.exists()) {
            workingDir.mkdirs();
        }
    }

    /**
     * Клонирует или обновляет репозиторий студента
     * @param student - студент с данными репозитория
     * @return директория с локальной копией репозитория
     */
    public File cloneOrUpdateRepo(Student student) throws IOException, InterruptedException {
        File studentDir = new File(workingDir, student.githubUsername);

        if (studentDir.exists()) {
            // Pull latest changes
            System.out.println("Updating repo for " + student.githubUsername);
            updateRepository();
        } else {
            // Clone repo
            System.out.println("Cloning repo for " + student.githubUsername);
            cloneRepository(student.repoUrl);
        }

        // Попытка переключиться на ветку main или master
        try {
            runCommand(new String[]{"git", "checkout", "main"}, studentDir);
        } catch (RuntimeException e) {
            try {
                runCommand(new String[]{"git", "checkout", "master"}, studentDir);
            } catch (RuntimeException ignored) {
                System.err.println("Neither 'main' nor 'master' branch found for " + student.githubUsername);
            }
        }

        return studentDir;
    }

    /**
     * Получает дату последнего коммита в репозитории
     * @param taskDir директория репозитория
     * @return дата последнего коммита (LocalDate) или null при ошибке
     */
    public LocalDate getLastCommitDate(File taskDir) {
        try {
            System.out.println("Checking last commit date for: " + taskDir.getAbsolutePath());
            ProcessBuilder pb = new ProcessBuilder("git", "log", "--format=%at", "--", taskDir.getName());
            pb.directory(workingDir);
            Process process = pb.start();
            
            List<String> output = new ArrayList<>();
            try (var reader = process.inputReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.add(line);
                }
            }
            
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Error getting commit history. Exit code: " + exitCode);
                return null;
            }
            
            System.out.println("All commits for " + taskDir.getName() + ":");
            for (String commit : output) {
                System.out.println(commit);
            }
            
            if (output.isEmpty()) {
                System.out.println("No commits found for " + taskDir.getName());
                return null;
            }
            
            // Берем первый коммит (самый последний)
            long timestamp = Long.parseLong(output.get(0));
            return LocalDateTime.ofInstant(
                java.time.Instant.ofEpochSecond(timestamp),
                ZoneId.systemDefault()
            ).toLocalDate();
            
        } catch (IOException | InterruptedException e) {
            System.err.println("Error getting last commit date: " + e.getMessage());
            return null;
        }
    }

    /**
     * Запускает команду в указанной директории
     */
    private void runCommand(String[] command, File dir) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.directory(dir);
        pb.inheritIO();
        Process p = pb.start();
        int code = p.waitFor();
        if (code != 0) {
            throw new RuntimeException("Command failed: " + String.join(" ", command));
        }
    }

    /**
     * Ищет все подпапки с gradle проектами (с build.gradle или build.gradle.kts)
     * @param repoDir корневая директория репозитория
     * @return список директорий с gradle-проектами
     */
    public List<File> findGradleProjects(File repoDir) {
        List<File> gradleProjects = new ArrayList<>();
        findGradleRecursively(repoDir, gradleProjects);
        return gradleProjects;
    }

    private void findGradleRecursively(File dir, List<File> result) {
        File[] files = dir.listFiles();
        if (files == null) return;

        boolean hasGradle = false;
        for (File f : files) {
            if (f.getName().equals("build.gradle") || f.getName().equals("build.gradle.kts")) {
                hasGradle = true;
                break;
            }
        }

        if (hasGradle) {
            result.add(dir);
        } else {
            for (File f : files) {
                if (f.isDirectory()) {
                    findGradleRecursively(f, result);
                }
            }
        }
    }

    /**
     * Компилирует gradle проект в указанной директории
     * @param projectDir директория с gradle-проектом
     * @return true, если сборка прошла успешно
     */
    public boolean compileProject(File projectDir) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("gradlew.bat", "build");
        pb.directory(projectDir);
        pb.redirectErrorStream(true);
        Process process = pb.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            return exitCode == 0;
        }
    }

    public void cloneRepository(String repoUrl) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("git", "clone", repoUrl, ".");
        pb.directory(workingDir);
        Process process = pb.start();
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException("Failed to clone repository. Exit code: " + exitCode);
        }
    }

    public void updateRepository() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("git", "pull");
        pb.directory(workingDir);
        Process process = pb.start();
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException("Failed to update repository. Exit code: " + exitCode);
        }
    }
}
