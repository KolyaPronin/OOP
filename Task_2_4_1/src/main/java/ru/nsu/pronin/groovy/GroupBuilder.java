package ru.nsu.pronin.groovy;

import ru.nsu.pronin.config.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupBuilder {
    private List<Student> students = new ArrayList<>();

    public void student(Map<String, Object> params) {
        String username = (String) params.get("username");
        String fullName = (String) params.get("fullName");
        String repo = (String) params.get("repo");
        students.add(new Student(username, fullName, "", repo));
    }

    public List<Student> getStudents() {
        return students;
    }
} 