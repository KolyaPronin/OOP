package ru.nsu.pronin.javaClasses;

import ru.nsu.pronin.config.Student;

import java.util.Map;

public class GroupBuilder {
    private final CourseBuilder course;
    private final String groupName;

    public GroupBuilder(CourseBuilder course, String groupName) {
        this.course = course;
        this.groupName = groupName;
    }

    public void student(Map<String, Object> params) {
        String username = (String) params.get("username");
        String fullName = (String) params.get("fullName");
        String repo = (String) params.get("repo");
        course.addStudent(new Student(username, fullName, "", repo));
    }
}
