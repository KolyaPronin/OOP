package ru.nsu.pronin.config;

public class Student {
    private String username;
    private String name;
    private String group;
    private String gitHubRepo;

    public Student(String username, String name, String group, String gitHubRepo) {
        this.username = username;
        this.name = name;
        this.group = group;
        this.gitHubRepo = gitHubRepo;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public String getGitHubRepo() {
        return gitHubRepo;
    }

    @Override
    public String toString() {
        return String.format("Student{username='%s', name='%s', group='%s', gitHubRepo='%s'}", 
            username, name, group, gitHubRepo);
    }
} 