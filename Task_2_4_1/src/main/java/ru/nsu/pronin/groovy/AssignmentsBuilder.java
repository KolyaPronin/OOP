package ru.nsu.pronin.groovy;

import java.util.*;

public class AssignmentsBuilder {
    private Map<String, List<String>> assignments = new HashMap<>();

    public void assign(String username, List<String> taskIds) {
        assignments.put(username, taskIds);
    }

    public Map<String, List<String>> getAssignments() {
        return assignments;
    }
} 