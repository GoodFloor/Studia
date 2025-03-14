package lectures.l09.task3;

import java.util.HashMap;
import java.util.Map;

public class Map3c {
    private Map<String, Map<String, Integer>> map = new HashMap<>();

    public double averageCourseGrade(String courseName) {
        int sum = 0;
        int count = 0;
        for (var grades : map.values()) {
            Integer grade = grades.get(courseName);
            if (grade != null) {
                sum += grade;
                count++;
            }
        }
        return count > 0 ? (double)sum / count : 0.;
    }

    public void printStudentAverages() {
        for (var entry : map.entrySet()) {
            System.out.printf("%s => %.2f%n", entry.getKey(), calculateAverage(entry.getValue()));
        }
    }

    private double calculateAverage(Map<String, Integer> grades) {
        if (grades == null) {
            return 0.;
        }
        int sum = 0;
        for (Integer grade : grades.values()) {
            sum += grade;
        }
        return (double)sum / grades.values().size();
    }

    public void assignGrade(String student, String subject, int grade) {
        Map<String, Integer> subjectsMap = map.get(student);
        if (subjectsMap == null) {
            subjectsMap = new HashMap<>();
            map.put(student, subjectsMap);
        }
        subjectsMap.put(subject, grade);
    }

    public void printData() {
        System.out.println(map);
    }
}
