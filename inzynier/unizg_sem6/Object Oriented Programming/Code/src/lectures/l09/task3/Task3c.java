package lectures.l09.task3;

public class Task3c {
    public static void main(String[] args) {
        Map3c grades = new Map3c();
        grades.assignGrade("Bob", "OOP", 5);
        grades.assignGrade("Bob", "Math", 4);
        grades.assignGrade("Ana", "Math", 5);
        grades.assignGrade("Ana", "OOP", 5);
        grades.assignGrade("Jack", "OOP", 3);
        grades.assignGrade("Bob", "Phy", 2);
        
        grades.printData();
        System.out.printf("Avg(OOP) = %.2f%n", grades.averageCourseGrade("OOP"));
        System.out.printf("Avg(Math) = %.2f%n", grades.averageCourseGrade("Math"));
        System.out.printf("Avg(Phy) = %.2f%n", grades.averageCourseGrade("Phy"));
        System.out.printf("Avg(---) = %.2f%n", grades.averageCourseGrade("---"));
        grades.printStudentAverages();
        // grades.printGrades();
        // System.out.printf("Avg = %.2f%n", grades.averageGrade());
        // grades.increaseGrades();
        // grades.printGrades();
        // System.out.printf("Avg = %.2f%n", grades.averageGrade());
    }
}
