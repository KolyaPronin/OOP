package ru.nsu.pronin.javaClasses;

import java.time.LocalDate;

public class LabResult {
    private String taskId;
    private int totalTests;
    private int passedTests;
    private int failedTests;
    private int skippedTests;
    private double points;
    private DeadlineStatus deadlineStatus;
    private LocalDate softDeadline;
    private LocalDate hardDeadline;
    private LocalDate submissionDate;

    public enum DeadlineStatus {
        ON_TIME,
        AFTER_SOFT,
        AFTER_HARD,
        UNKNOWN
    }

    public LabResult(String taskId, int totalTests, int passedTests, int failedTests, int skippedTests,
                    double points, DeadlineStatus deadlineStatus) {
        this.taskId = taskId;
        this.totalTests = totalTests;
        this.passedTests = passedTests;
        this.failedTests = failedTests;
        this.skippedTests = skippedTests;
        this.points = points;
        this.deadlineStatus = deadlineStatus;
    }

    public LabResult(String taskId, int totalTests, int passedTests, int failedTests, int skippedTests,
                    LocalDate softDeadline, LocalDate hardDeadline, LocalDate submissionDate) {
        this.taskId = taskId;
        this.totalTests = totalTests;
        this.passedTests = passedTests;
        this.failedTests = failedTests;
        this.skippedTests = skippedTests;
        this.softDeadline = softDeadline;
        this.hardDeadline = hardDeadline;
        this.submissionDate = submissionDate;
        
        // Если хотя бы один тест прошел - максимальный балл
        if (passedTests > 0) {
            this.deadlineStatus = DeadlineStatus.ON_TIME;
            this.points = 2.0;
        } else {
            // Если билд упал или тесты не собираются
            this.deadlineStatus = DeadlineStatus.AFTER_HARD;
            this.points = 0.0;
        }
    }

    public String getTaskId() {
        return taskId;
    }

    public int getTotalTests() {
        return totalTests;
    }

    public int getPassedTests() {
        return passedTests;
    }

    public int getFailedTests() {
        return failedTests;
    }

    public int getSkippedTests() {
        return skippedTests;
    }

    public double getPoints() {
        return points;
    }

    public DeadlineStatus getDeadlineStatus() {
        return deadlineStatus;
    }

    public LocalDate getSoftDeadline() {
        return softDeadline;
    }

    public LocalDate getHardDeadline() {
        return hardDeadline;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    @Override
    public String toString() {
        return String.format("LabResult{taskId='%s', totalTests=%d, passedTests=%d, failedTests=%d, " +
                "skippedTests=%d, points=%.1f, deadlineStatus=%s, softDeadline=%s, hardDeadline=%s, submissionDate=%s}",
                taskId, totalTests, passedTests, failedTests, skippedTests, points, deadlineStatus,
                softDeadline, hardDeadline, submissionDate);
    }
}
