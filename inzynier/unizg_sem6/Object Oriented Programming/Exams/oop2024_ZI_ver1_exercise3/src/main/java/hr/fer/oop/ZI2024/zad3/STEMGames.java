package hr.fer.oop.ZI2024.zad3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class STEMGames {
    private List<Student> students;
    private int highestScoreInEng;
    private int highestScoreInMath;
    private int highestScoreInTotal;
    private List<String> topStudentsInEng;
    private List<String> topStudentsInMath;
    private List<String> topStudentsInTotal;

    public STEMGames() {
        students = new LinkedList<>();
        topStudentsInEng = new LinkedList<>();
        topStudentsInMath = new LinkedList<>();
        topStudentsInTotal = new LinkedList<>();
    }

    public void addStudent(String name, int pointsEng, int pointsMath) {
        Student newStudent = new Student(name, pointsEng, pointsMath);
        students.add(newStudent);

        if (pointsEng > highestScoreInEng) {
            topStudentsInEng.clear();
            highestScoreInEng = pointsEng;
        }
        if (pointsEng == highestScoreInEng) {
            topStudentsInEng.add(newStudent.getName());
        }

        if (pointsMath > highestScoreInMath) {
            topStudentsInMath.clear();
            highestScoreInMath = pointsMath;
        }
        if (pointsMath == highestScoreInMath) {
            topStudentsInMath.add(newStudent.getName());
        }

        if (newStudent.getTotalPoints() > highestScoreInTotal) {
            topStudentsInTotal.clear();
            highestScoreInTotal = newStudent.getTotalPoints();
        }
        if (newStudent.getTotalPoints() == highestScoreInTotal) {
            topStudentsInTotal.add(newStudent.getName());
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getHighestScoreInEng() {
        return highestScoreInEng;
    }

    public int getHighestScoreInMath() {
        return highestScoreInMath;
    }

    public int getHighestScoreInTotal() {
        return highestScoreInTotal;
    }
    
    public Map<String, List<String>> getTopStudents() {
        Map<String, List<String>> result = new HashMap<>();
        result.put("eng", topStudentsInEng);
        result.put("math", topStudentsInMath);
        result.put("total", topStudentsInTotal);
        return result;
    }
}
