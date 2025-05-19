package ru.nsu.pronin.model;

public class Student {
    public String githubUsername;
    public String fullName;
    public String repoUrl;

    public Student(String githubUsername, String fullName, String repoUrl) {
        this.githubUsername = githubUsername;
        this.fullName = fullName;
        this.repoUrl = repoUrl;
    }
}
