package ru.nsu.pronin.model;

import java.time.LocalDate;

public class Checkpoint {
    public String title;
    public LocalDate date;

    public Checkpoint(String title, LocalDate date) {
        this.title = title;
        this.date = date;
    }
}
