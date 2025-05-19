package ru.nsu.pronin.javaClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.awt.Desktop;
import ru.nsu.pronin.config.Task;
import ru.nsu.pronin.git.GitHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class LabTasksRunner {
    private final GitHandler gitHandler;

    public LabTasksRunner() {
        this.gitHandler = new GitHandler(new File("."));
    }

    public LabResult runAllTasks(File taskDir, Task task) {
        try {
            System.out.println("Starting build in directory: " + taskDir.getAbsolutePath());
            
            // Получаем дату последнего коммита
            LocalDate lastCommitDate = getLastCommitDate(taskDir);
            
            // Проверяем наличие gradlew.bat
            File gradlew = new File(taskDir, "gradlew.bat");
            if (!gradlew.exists()) {
                System.out.println("No gradlew.bat found in " + taskDir.getAbsolutePath());
                return new LabResult(task.getId(), 0, 0, 0, 0,
                    task.getSoftDeadline(), task.getHardDeadline(), lastCommitDate);
            }

            // Проверяем наличие build.gradle
            File buildGradle = new File(taskDir, "build.gradle");
            if (!buildGradle.exists()) {
                System.out.println("No build.gradle found in " + taskDir.getAbsolutePath());
                return new LabResult(task.getId(), 0, 0, 0, 0,
                    task.getSoftDeadline(), task.getHardDeadline(), lastCommitDate);
            }

            // Запускаем тесты
            ProcessBuilder pb = new ProcessBuilder("gradlew.bat", "clean", "test");
            pb.directory(taskDir);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Читаем вывод процесса
            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[" + task.getId() + "] " + line);
                    output.append(line).append("\n");
                }
            }

            int exitCode = process.waitFor();
            System.out.println("Build completed with code: " + exitCode);

            // --- Новый парсинг XML-отчётов ---
            int totalTests = 0;
            int passedTests = 0;
            int failedTests = 0;
            int skippedTests = 0;
            File testResultsDir = new File(taskDir, "build/test-results/test");
            if (testResultsDir.exists() && testResultsDir.isDirectory()) {
                File[] files = testResultsDir.listFiles((dir, name) -> name.startsWith("TEST-") && name.endsWith(".xml"));
                if (files != null) {
                    for (File xmlFile : files) {
                        try {
                            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                            Document doc = dBuilder.parse(xmlFile);
                            doc.getDocumentElement().normalize();
                            Element testSuite = doc.getDocumentElement();
                            int tests = Integer.parseInt(testSuite.getAttribute("tests"));
                            int failures = Integer.parseInt(testSuite.getAttribute("failures"));
                            int errors = testSuite.hasAttribute("errors") ? Integer.parseInt(testSuite.getAttribute("errors")) : 0;
                            int skipped = testSuite.hasAttribute("skipped") ? Integer.parseInt(testSuite.getAttribute("skipped")) : 0;
                            totalTests += tests;
                            failedTests += failures + errors;
                            skippedTests += skipped;
                        } catch (Exception ex) {
                            System.err.println("Error parsing XML report: " + xmlFile.getAbsolutePath());
                        }
                    }
                    passedTests = totalTests - failedTests - skippedTests;
                }
            }
            // --- Конец парсинга XML ---

            System.out.println("Test results for " + task.getId() + ":");
            System.out.println("Total: " + totalTests);
            System.out.println("Passed: " + passedTests);
            System.out.println("Failed: " + failedTests);
            System.out.println("Skipped: " + skippedTests);

            // Создаем результат с учетом тестов
            return new LabResult(task.getId(), totalTests, passedTests, failedTests, skippedTests,
                task.getSoftDeadline(), task.getHardDeadline(), lastCommitDate);
        } catch (Exception e) {
            System.err.println("Error running tasks: " + e.getMessage());
            e.printStackTrace();
            return new LabResult(task.getId(), 0, 0, 0, 0,
                task.getSoftDeadline(), task.getHardDeadline(), null);
        }
    }

    public String generateReport(List<LabResult> results) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n");
        html.append("<html>\n");
        html.append("<head>\n");
        html.append("    <title>Lab Tasks Report</title>\n");
        html.append("    <style>\n");
        html.append("        body { font-family: Arial, sans-serif; margin: 20px; }\n");
        html.append("        table { border-collapse: collapse; width: 100%; }\n");
        html.append("        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }\n");
        html.append("        th { background-color:rgb(255, 255, 255); }\n");
        html.append("        tr:nth-child(even) { background-color:rgb(255, 255, 255); }\n");
        html.append("        .passed { color: green; }\n");
        html.append("        .failed { color: red; }\n");
        html.append("        .skipped { color: orange; }\n");
        html.append("    </style>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("    <h1>Lab Tasks Report</h1>\n");
        html.append("    <table>\n");
        html.append("        <tr>\n");
        html.append("            <th>Task ID</th>\n");
        html.append("            <th>Total Tests</th>\n");
        html.append("            <th>Passed</th>\n");
        html.append("            <th>Failed</th>\n");
        html.append("            <th>Skipped</th>\n");
        html.append("            <th>Score</th>\n");
        html.append("            <th>Status</th>\n");
        html.append("            <th>Soft Deadline</th>\n");
        html.append("            <th>Hard Deadline</th>\n");
        html.append("            <th>Submission Date</th>\n");
        html.append("        </tr>\n");

        for (LabResult result : results) {
            html.append("        <tr>\n");
            html.append("            <td>").append(result.getTaskId()).append("</td>\n");
            html.append("            <td>").append(result.getTotalTests()).append("</td>\n");
            html.append("            <td class=\"passed\">").append(result.getPassedTests()).append("</td>\n");
            html.append("            <td class=\"failed\">").append(result.getFailedTests()).append("</td>\n");
            html.append("            <td class=\"skipped\">").append(result.getSkippedTests()).append("</td>\n");
            html.append("            <td>").append(result.getPoints()).append("</td>\n");
            html.append("            <td>").append(result.getDeadlineStatus()).append("</td>\n");
            html.append("            <td>").append(result.getSoftDeadline()).append("</td>\n");
            html.append("            <td>").append(result.getHardDeadline()).append("</td>\n");
            html.append("            <td>").append(result.getSubmissionDate()).append("</td>\n");
            html.append("        </tr>\n");
        }

        html.append("    </table>\n");
        html.append("    <h2>Итоговая сумма баллов: ").append(sumScores(results)).append("</h2>\n");
        html.append("    <h2>Итоговая оценка: ").append(calculateGrade(sumScores(results))).append("</h2>\n");
        html.append("</body>\n");
        html.append("</html>");

        return html.toString();
    }

    public double sumScores(List<LabResult> results) {
        return results.stream().mapToDouble(LabResult::getPoints).sum();
    }

    public int analyzeActivity(File repoDir) throws IOException, InterruptedException {
        // Проверяем активность в конкретной директории
        ProcessBuilder pb = new ProcessBuilder("git", "log", "--since=1 week ago", "--format=%cd", "--date=short", "--", repoDir.getPath());
        pb.directory(repoDir.getParentFile()); // Переходим в родительскую директорию репозитория
        pb.redirectErrorStream(true);

        Process process = pb.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            int commitCount = 0;
            while (reader.readLine() != null) {
                commitCount++;
            }
            process.waitFor();
            return commitCount;
        }
    }

    public LocalDate getLastCommitDate(File repoDir) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("git", "log", "-1", "--format=%cd", "--date=short", "--", repoDir.getPath());
        pb.directory(repoDir.getParentFile());
        pb.redirectErrorStream(true);

        Process process = pb.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String dateStr = reader.readLine();
            process.waitFor();
            if (dateStr != null && !dateStr.isEmpty()) {
                return LocalDate.parse(dateStr);
            }
        }
        return null;
    }

    public String calculateGrade(double totalScore) {
        if (totalScore >= 13) {
            return "отлично";
        } else if (totalScore >= 10) {
            return "хорошо";
        } else if (totalScore >= 7) {
            return "удовлетворительно";
        } else {
            return "неудовлетворительно";
        }
    }

    public void openReportInBrowser(String report) throws IOException {
        File htmlFile = new File(System.getProperty("user.dir"), "report.html");
        System.out.println("Saving report to file: " + htmlFile.getAbsolutePath());
        try (FileWriter writer = new FileWriter(htmlFile)) {
            writer.write(report);
        }
        System.out.println("Report saved to file: " + htmlFile.getAbsolutePath());
        Desktop.getDesktop().browse(htmlFile.toURI());
    }
}