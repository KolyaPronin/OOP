package ru.nsu.pronin.config;

import java.time.LocalDate;

public class Task {
    private String id;
    private String title;
    private double points;
    private LocalDate softDeadline;
    private LocalDate hardDeadline;

    public Task(String id, String title, double points, LocalDate softDeadline, LocalDate hardDeadline) {
        this.id = id;
        this.title = title;
        this.points = points;
        this.softDeadline = softDeadline;
        this.hardDeadline = hardDeadline;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPoints() {
        return points;
    }

    public LocalDate getSoftDeadline() {
        return softDeadline;
    }

    public LocalDate getHardDeadline() {
        return hardDeadline;
    }

    @Override
    public String toString() {
        return String.format("Task{id='%s', title='%s', points=%.1f, softDeadline=%s, hardDeadline=%s}",
            id, title, points, softDeadline, hardDeadline);
    }
} 