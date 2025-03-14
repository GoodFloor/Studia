package lectures.l09.summary;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentGradesComplicated {
    public static void main(String[] args) {
        // Student, different courses, 1 or more grades
        // K = Student, V = Map(K = Subject, V = List of grades)
        Map<String, Map<String, List<Integer>>> students = new HashMap<>();

        try (Scanner sc = new Scanner(System.in)) {
            boolean readNext = true;
            while (readNext) {
                System.out.print("Student: ");
                String student = sc.nextLine();
                if (student.equals("quit")) {
                    readNext = false;
                    break;
                }
                Map<String, List<Integer>> subjects = students.get(student);
                if (subjects == null) {
                    subjects = new HashMap<>();
                    students.put(student, subjects);
                }
                while (readNext) {
                    System.out.print("Course: ");
                    String course = sc.nextLine();
                    if (course.equals("quit")) {
                        readNext = false;
                        break;
                    } else if (course.equals("back")) {
                        break;
                    }
                    while (readNext) {
                        System.out.print("Grade for " + course + ": ");
                        String gradeS = sc.nextLine();
                        if (gradeS.equals("quit")) {
                            readNext = false;
                            break;
                        } else if (gradeS.equals("back")) {
                            break;
                        }
                        Integer grade = Integer.parseInt(gradeS);
                        List<Integer> grades = subjects.get(course);
                        if (grades == null) {
                            grades = new LinkedList<>();
                            subjects.put(course, grades);
                        }
                        grades.add(grade);
                        System.out.println(students);
                    }
                }
            }
            for (String stu : students.keySet()) {
                System.out.println(stu + ":");
                for (String sub : students.get(stu).keySet()) {
                    System.out.println("\t" + sub + ":");
                    int sum = 0;
                    for (Integer gra : students.get(stu).get(sub)) {
                        System.out.println("\t\t" + gra);
                        sum += gra;
                    }
                    double avg = sum / (double)students.get(stu).get(sub).size();
                    System.out.printf("\tAVG = %.2f%n", avg);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
