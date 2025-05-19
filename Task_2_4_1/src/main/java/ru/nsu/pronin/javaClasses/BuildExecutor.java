package ru.nsu.pronin.javaClasses;

import java.io.File;
import java.io.IOException;

public class BuildExecutor {
    /**
     * Запускает указанную gradle-задачу в директории проекта
     * @param projectDir директория gradle проекта
     * @param task имя задачи, например "build", "javadoc", "checkstyleMain"
     * @return true, если задача выполнена успешно
     */
    public boolean runGradleTask(File projectDir, String task) throws IOException, InterruptedException {
        File wrapper = new File(projectDir, "gradlew");
        ProcessBuilder pb;
        if (wrapper.exists() && wrapper.canExecute()) {
            pb = new ProcessBuilder("./gradlew", task);
        } else {
            pb = new ProcessBuilder("gradle", task);
        }
        pb.directory(projectDir);
        pb.inheritIO();

        Process process = pb.start();
        int exitCode = process.waitFor();
        return exitCode == 0;
    }
}
