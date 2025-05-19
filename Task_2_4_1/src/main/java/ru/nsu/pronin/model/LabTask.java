package ru.nsu.pronin.model;

import java.time.LocalDate;

public class LabTask {
    public String id;
    public String title;
    public int maxPoints;
    public LocalDate softDeadline;
    public LocalDate hardDeadline;

    public LabTask(String id, String title, int maxPoints, LocalDate softDeadline, LocalDate hardDeadline) {
        this.id = id;
        this.title = title;
        this.maxPoints = maxPoints;
        this.softDeadline = softDeadline;
        this.hardDeadline = hardDeadline;
    }
}
