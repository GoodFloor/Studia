package lectures.l09.summary;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentGrades {
    public static void main(String[] args) {
        // Student, different courses, 1 or more grades
        Map<String, List<Integer>> subjects = new HashMap<>();
        // List<Integer> OOPgrades = new LinkedList<>();
        // OOPgrades.add(5);
        // OOPgrades.add(3);
        // OOPgrades.add(5);
        // subjects.put("OOP", OOPgrades);
        // System.out.println(subjects);
        // List<Integer> ITPgrades = new LinkedList<>(List.of(2, 2, 5));
        // subjects.put("ITP", ITPgrades);
        // System.out.println(subjects);

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("Course: ");
                String course = sc.nextLine();
                if (course.equals("quit")) {
                    break;
                }
                System.out.print("Grade for " + course + ": ");
                Integer grade = Integer.parseInt(sc.nextLine());
                List<Integer> grades = subjects.get(course);
                if (grades == null) {
                    grades = new LinkedList<>();
                    subjects.put(course, grades);
                }
                grades.add(grade);
                System.out.println(subjects);
        }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
