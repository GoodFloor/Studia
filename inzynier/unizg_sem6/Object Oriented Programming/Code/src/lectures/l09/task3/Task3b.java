package lectures.l09.task3;

public class Task3b {
    public static void main(String[] args) {
        Map3b grades = new Map3b();
        grades.addGrade("Bob", 3);
        grades.addGrade("Ana", 5);
        grades.addGrade("Jack", 2);
        grades.addGrade("Bob", 4);
        grades.addGrade("Ana", 5);
        grades.addGrade("Jack", 3);
        grades.addGrade("Bob", 5);
        grades.addGrade("Ana", 4);
        grades.addGrade("Jack", 2);
        grades.printAverageGrades();
        System.out.printf("Avg = %.2f%n", grades.getAverage());
        
    }
}
