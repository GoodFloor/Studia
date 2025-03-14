package lectures.l09.task3;

public class Task3a {
    public static void main(String[] args) {
        Map3a grades = new Map3a();
        grades.assignGrade("Bob", 3);
        grades.assignGrade("Ana", 5);
        grades.assignGrade("Jack", 2);
        grades.printGrades();
        System.out.printf("Avg = %.2f%n", grades.averageGrade());
        grades.increaseGrades();
        grades.printGrades();
        System.out.printf("Avg = %.2f%n", grades.averageGrade());
    }
}
