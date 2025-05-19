package ru.nsu.pronin.javaClasses;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import ru.nsu.pronin.config.Task;
import ru.nsu.pronin.config.Student;
import ru.nsu.pronin.config.Checkpoint;
import ru.nsu.pronin.git.GitHandler;
import ru.nsu.pronin.groovy.CourseBuilder;
import ru.nsu.pronin.groovy.GroupBuilder;
import ru.nsu.pronin.groovy.AssignmentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import groovy.lang.Script;

public class Main {
    public static void main(String[] args) {
        try {
            System.setProperty("file.encoding", "UTF-8");
            CourseBuilder course = new CourseBuilder();

            Binding binding = new Binding();
            binding.setVariable("course", course);
            binding.setVariable("group", new GroupBuilder());
            binding.setVariable("assignments", new AssignmentsBuilder());

            GroovyShell shell = new GroovyShell(binding);
            Script script = shell.parse(new File("src/main/java/ru/nsu/pronin/groovy/CourseConfig.groovy"));
            script.run();

            course.printSummary();

            // Создаём директорию для репозиториев
            Path reposDir = Paths.get("repos");
            if (!Files.exists(reposDir)) {
                Files.createDirectories(reposDir);
            }

            // Обрабатываем каждого студента
            for (Student student : course.getStudents()) {
                System.out.println("\nProcessing repository for " + student.getName());
                
                // Создаем директорию для студента
                Path studentPath = reposDir.resolve(student.getUsername());
                if (!Files.exists(studentPath)) {
                    Files.createDirectories(studentPath);
                }

                // Клонируем или обновляем репозиторий
                GitHandler gitHandler = new GitHandler(studentPath.toFile());
                if (!Files.exists(studentPath.resolve(".git"))) {
                    gitHandler.cloneRepository(student.getGitHubRepo());
                } else {
                    gitHandler.updateRepository();
                }

                // Запускаем тесты для каждой задачи
                LabTasksRunner runner = new LabTasksRunner();
                List<LabResult> results = new ArrayList<>();

                // Получаем список задач для студента
                List<Task> studentTasks = course.getStudentTasks(student.getUsername());
                if (studentTasks.isEmpty()) {
                    studentTasks = course.getTasks(); // Если нет специальных назначений, берем все задачи
                }

                for (Task task : studentTasks) {
                    Path taskDir = studentPath.resolve(task.getId());
                    if (Files.exists(taskDir)) {
                        LabResult result = runner.runAllTasks(taskDir.toFile(), task);
                        results.add(result);
                    } else {
                        System.out.println("Task directory not found: " + taskDir);
                        // Добавляем результат с нулевыми баллами для отсутствующей задачи
                        results.add(new LabResult(task.getId(), 0, 0, 0, 0, 
                            task.getSoftDeadline(), task.getHardDeadline(), null));
                    }
                }

                // Генерируем отчет
                String reportHtml = runner.generateReport(results);
                System.out.println("Report generated and opened in browser.");
                
                // Открываем отчет в браузере
                runner.openReportInBrowser(reportHtml);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void openReport(Path reportPath) {
        try {
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop.getDesktop().browse(reportPath.toUri());
            } else {
                System.out.println("Откройте отчёт вручную: " + reportPath.toAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error opening report: " + e.getMessage());
        }
    }
}
