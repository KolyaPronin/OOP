package ru.nsu.pronin.config;

import java.time.LocalDate;

public class Checkpoint {
    private LocalDate date;

    public Checkpoint(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("Checkpoint{date=%s}", date);
    }
} 