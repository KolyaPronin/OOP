package ru.nsu.pronin.javaClasses;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JUnitReportParser {

    public static class TestStats {
        public int total = 0;
        public int failed = 0;
        public int skipped = 0;
        public int passed = 0;

        @Override
        public String toString() {
            return "Tests run: " + total + ", Passed: " + passed + ", Failed: " + failed + ", Skipped: " + skipped;
        }
    }

    public TestStats parseReportDir(File reportsDir) throws Exception {
        if (!reportsDir.exists() || !reportsDir.isDirectory()) {
            throw new IllegalArgumentException("Report directory does not exist: " + reportsDir);
        }

        File[] files = reportsDir.listFiles((dir, name) -> name.endsWith(".xml"));
        if (files == null || files.length == 0) {
            throw new IllegalStateException("No XML reports found in " + reportsDir);
        }

        TestStats stats = new TestStats();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        for (File file : files) {
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList testSuites = doc.getElementsByTagName("testsuite");
            for (int i = 0; i < testSuites.getLength(); i++) {
                Element suite = (Element) testSuites.item(i);

                int tests = Integer.parseInt(suite.getAttribute("tests"));
                int failures = Integer.parseInt(suite.getAttribute("failures"));
                int skipped = 0;
                String skippedAttr = suite.getAttribute("skipped");
                if (skippedAttr != null && !skippedAttr.isEmpty()) {
                    skipped = Integer.parseInt(skippedAttr);
                }
                int errors = 0;
                String errorsAttr = suite.getAttribute("errors");
                if (errorsAttr != null && !errorsAttr.isEmpty()) {
                    errors = Integer.parseInt(errorsAttr);
                }

                stats.total += tests;
                stats.failed += failures + errors; // считаем ошибки как неуспешные
                stats.skipped += skipped;
            }
        }

        stats.passed = stats.total - stats.failed - stats.skipped;

        return stats;
    }
}
