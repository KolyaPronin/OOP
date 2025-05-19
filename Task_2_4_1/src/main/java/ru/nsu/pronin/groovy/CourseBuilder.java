package ru.nsu.pronin.groovy;

import ru.nsu.pronin.config.Task;
import ru.nsu.pronin.config.Student;
import ru.nsu.pronin.config.Checkpoint;
import groovy.lang.Closure;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CourseBuilder {
    private List<Task> tasks = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Checkpoint> checkpoints = new ArrayList<>();
    private Map<String, List<String>> studentTasks = new HashMap<>();

    public void task(Map<String, Object> params) {
        String id = (String) params.get("id");
        String name = (String) params.get("name");
        double maxScore = ((Number) params.get("maxScore")).doubleValue();
        LocalDate softDeadline = LocalDate.parse((String) params.get("softDeadline"));
        LocalDate hardDeadline = LocalDate.parse((String) params.get("hardDeadline"));
        
        tasks.add(new Task(id, name, maxScore, softDeadline, hardDeadline));
    }

    public void group(Map<String, Object> params, Closure closure) {
        String groupName = (String) params.get("name");
        GroupBuilder groupBuilder = new GroupBuilder();
        closure.setDelegate(groupBuilder);
        closure.call();
        
        for (Student student : groupBuilder.getStudents()) {
            students.add(new Student(student.getUsername(), student.getName(), groupName, student.getGitHubRepo()));
        }
    }

    public void assignments(Closure closure) {
        AssignmentsBuilder assignmentsBuilder = new AssignmentsBuilder();
        closure.setDelegate(assignmentsBuilder);
        closure.call();
        this.studentTasks = assignmentsBuilder.getAssignments();
    }

    public void checkpoint(Map<String, Object> params) {
        String name = (String) params.get("name");
        LocalDate date = LocalDate.parse((String) params.get("date"));
        checkpoints.add(new Checkpoint(date));
    }

    public void printSummary() {
        System.out.println("\nTasks:");
        for (Task task : tasks) {
            System.out.printf("ID: %s, Title: %s, Points: %.1f, Soft Deadline: %s, Hard Deadline: %s%n",
                task.getId(), task.getTitle(), task.getPoints(),
                task.getSoftDeadline(), task.getHardDeadline());
        }

        System.out.println("\nStudents:");
        for (Student student : students) {
            System.out.printf("Name: %s, Group: %s, GitHub: %s%n",
                student.getName(), student.getGroup(), student.getGitHubRepo());
        }

        System.out.println("\nCheckpoints:");
        for (Checkpoint checkpoint : checkpoints) {
            System.out.println("Date: " + checkpoint.getDate());
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public List<Task> getStudentTasks(String username) {
        List<String> taskIds = studentTasks.get(username);
        if (taskIds == null) {
            return new ArrayList<>();
        }
        return tasks.stream()
            .filter(task -> taskIds.contains(task.getId()))
            .collect(Collectors.toList());
    }

    public void course(groovy.lang.Closure<?> closure) {
        closure.setDelegate(this);
        closure.call();
    }
} 