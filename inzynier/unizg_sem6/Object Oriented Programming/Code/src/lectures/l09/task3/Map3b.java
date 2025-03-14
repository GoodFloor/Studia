package lectures.l09.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Map3b {
    private Map<String, List<Integer>> map = new HashMap<>();

    public void printAverageGrades() {
        for (var entry : map.entrySet()) {
            System.out.printf("%s => %.2f%n", entry.getKey(), calculateAverage(entry.getValue()));
        }
    }

    public double getAverage() {
        double totalSum = 0;
        // int numberOfGrades = 0;
        // for (var entry : map.entrySet()) {
        //     numberOfGrades += entry.getValue().size();
        //     for (Integer grade : entry.getValue()) {
        //         totalSum += grade;
        //     }
        // }
        // return (double)totalSum / numberOfGrades;
        for (var entry : map.entrySet()) {
            totalSum += calculateAverage(entry.getValue());
        }
        return totalSum / map.entrySet().size();
    }

    private double calculateAverage(List<Integer> list) {
        int sum = 0;
        for (int n : list) {
            sum += n;
        }
        return (double)sum / list.size();
    }

    public void addGrade(String name, int grade) {
        List<Integer> grades = map.get(name);
        if (grades == null) {
            grades = new ArrayList<>();
            map.put(name, grades);
        } 
        grades.add(grade);
    }
}
