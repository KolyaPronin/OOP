package ru.nsu.pronin.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<Task> loadTasks(String filename) throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String id = parts[0].trim();
                    String title = parts[1].trim();
                    double points = Double.parseDouble(parts[2].trim());
                    LocalDate softDeadline = LocalDate.parse(parts[3].trim(), DATE_FORMATTER);
                    LocalDate hardDeadline = LocalDate.parse(parts[4].trim(), DATE_FORMATTER);
                    tasks.add(new Task(id, title, points, softDeadline, hardDeadline));
                }
            }
        }
        return tasks;
    }

    public static List<Student> loadStudents(String filename) throws IOException {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String username = parts[0].trim();
                    String name = parts[1].trim();
                    String group = parts[2].trim();
                    String gitHubRepo = parts[3].trim();
                    students.add(new Student(username, name, group, gitHubRepo));
                }
            }
        }
        return students;
    }

    public static List<Checkpoint> loadCheckpoints(String filename) throws IOException {
        List<Checkpoint> checkpoints = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LocalDate date = LocalDate.parse(line.trim(), DATE_FORMATTER);
                checkpoints.add(new Checkpoint(date));
            }
        }
        return checkpoints;
    }
} 