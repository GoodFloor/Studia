package lectures.l09.task3;

import java.util.Map;
import java.util.TreeMap;

public class Map3a {
    private Map<String, Integer> gradeMap = new TreeMap<>();

    public void assignGrade(String student, int grade) {
        gradeMap.put(student, grade);
    }
    
    public void increaseGrades() {
        for (var entry : gradeMap.entrySet()) {
            if (entry.getValue() < 5) {
                entry.setValue(entry.getValue() + 1);
            }
        }
        // for (String key : gradeMap.keySet()) {
        //     if (gradeMap.get(key) < 5) {
        //         gradeMap.put(key, gradeMap.get(key) + 1)
        //     }
        // }
    }

    public void printGrades() {
        for (Integer grade : gradeMap.values()) {
            System.out.println(grade);
        }
    }
    
    public double averageGrade() {
        if (gradeMap.isEmpty()) {
            return 0.;
        }
        int sum = 0;
        for (var entry : gradeMap.entrySet()) {
            sum += entry.getValue();
        }
        return (double)sum / gradeMap.size();
    }
}
