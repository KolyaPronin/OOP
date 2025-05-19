package ru.nsu.pronin.javaClasses;

import java.util.List;

public class AssignmentsBuilder {
    private final CourseBuilder course;

    public AssignmentsBuilder(CourseBuilder course) {
        this.course = course;
    }

    public void assign(String studentUsername, List<String> taskIds) {
        course.assignTaskToStudent(studentUsername, taskIds);
    }
} 