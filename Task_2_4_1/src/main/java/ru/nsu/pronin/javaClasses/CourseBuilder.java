package ru.nsu.pronin.javaClasses;

import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import ru.nsu.pronin.model.*;
import ru.nsu.pronin.config.Task;
import ru.nsu.pronin.config.Student;
import ru.nsu.pronin.config.Checkpoint;

import java.time.LocalDate;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class CourseBuilder {
    private List<Task> tasks = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Checkpoint> checkpoints = new ArrayList<>();
    private final Map<String, List<String>> studentAssignments = new HashMap<>();

    public void course(@DelegatesTo(CourseBuilder.class) Closure<?> closure) {
        closure.setDelegate(this);
        closure.setResolveStrategy(Closure.DELEGATE_FIRST);
        closure.call();
    }

    public void task(Map<String, Object> params) {
        String id = (String) params.get("id");
        String name = (String) params.get("name");
        double maxScore = ((Number) params.get("maxScore")).doubleValue();
        LocalDate softDeadline = LocalDate.parse((String) params.get("softDeadline"));
        LocalDate hardDeadline = LocalDate.parse((String) params.get("hardDeadline"));
        tasks.add(new Task(id, name, maxScore, softDeadline, hardDeadline));
    }

    public void group(Map<String, Object> params, @DelegatesTo(GroupBuilder.class) Closure<?> closure) {
        GroupBuilder builder = new GroupBuilder(this, (String) params.get("name"));
        closure.setDelegate(builder);
        closure.setResolveStrategy(Closure.DELEGATE_FIRST);
        closure.call();
    }

    public void assignments(@DelegatesTo(AssignmentsBuilder.class) Closure<?> closure) {
        AssignmentsBuilder builder = new AssignmentsBuilder(this);
        closure.setDelegate(builder);
        closure.setResolveStrategy(Closure.DELEGATE_FIRST);
        closure.call();
    }

    public void checkpoint(Map<String, Object> params) {
        String name = (String) params.get("name");
        LocalDate date = LocalDate.parse((String) params.get("date"));
        checkpoints.add(new Checkpoint(date));
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void assignTaskToStudent(String studentUsername, List<String> taskIds) {
        studentAssignments.put(studentUsername, taskIds);
    }

    public void printSummary() {
        System.out.println("Tasks:");
        for (Task task : tasks) {
            System.out.printf("ID: %s, Title: %s, Points: %.1f, Soft Deadline: %s, Hard Deadline: %s%n",
                task.getId(), task.getTitle(), task.getPoints(), task.getSoftDeadline(), task.getHardDeadline());
        }

        System.out.println("\nStudents:");
        for (Student student : students) {
            System.out.printf("Name: %s, Group: %s, GitHub: %s%n",
                student.getName(), student.getGroup(), student.getGitHubRepo());
        }

        System.out.println("\nCheckpoints:");
        for (Checkpoint checkpoint : checkpoints) {
            System.out.printf("Date: %s%n", checkpoint.getDate());
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

    public List<Group> getGroups() {
        return new ArrayList<>();
    }

    public LabTask getTaskById(String id) {
        return null;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    // + геттеры, если нужно в будущем
}
