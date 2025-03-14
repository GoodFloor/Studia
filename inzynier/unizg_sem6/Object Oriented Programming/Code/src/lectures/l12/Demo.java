package lectures.l12;

import java.util.*;

public class Demo {
    public static void main(String[] args) {
        // // 1. List needs equals() method in Student class
        // List<Student> students = new ArrayList<>();

        // // 2. HashSet needs hashCode() method in Student class
        // Set<Student> students = new HashSet<>();

        // // 3. TreeSet needs compare() method, we can say Student implements Comparable, we can also specify different Comparator for TreeSet
        // Set<Student> students = new TreeSet<>(new StudentComparator());
        // // If we want to change the order we can just edit StudentComparator class

        // // 4. TreeSet with anonymous class
        // Set<Student> students = new TreeSet<>(new Comparator<Student>() {
        //     @Override
        //     public int compare(Student o1, Student o2) {
        //         return o1.studentId.compareTo(o2.studentId);
        //     }
        // });

        // // 5. TreeSet with lambda expression
        // Set<Student> students = new TreeSet<>(
        //     (s1, s2) -> s1.studentId.compareTo(s2.studentId)
        // );

        // // 6. Using decorators on Comparator
        // StudentComparator sc = new StudentComparator();
        // ReverseComparator rc = new ReverseComparator(sc);
        // Set<Student> students = new TreeSet<>(rc);

        // // 7. Using built-in method 
        // StudentComparator sc = new StudentComparator();
        // Set<Student> students = new TreeSet<>(sc.reversed());

        // // 8. For many criteria it can be easier to use CompositeComparator along with Comparators defined inside Student class
        // // CompositeComparator<Student> cc = new CompositeComparator<>(
        // //     Student.BY_FN.reversed(), Student.BY_LN, Student.BY_ID
        // // );
        // ArrayList<Comparator<Student>> al = new ArrayList<>();
        // Collections.addAll(al, Student.BY_FN.reversed(), Student.BY_LN, Student.BY_ID);
        // CompositeComparator<Student> cc = new CompositeComparator<>(al);
        // Set<Student> students = new TreeSet<>(cc);

        // 9. Built-in composite comparator
        Set<Student> students = new TreeSet<>(
            Student.BY_FN.reversed()
            .thenComparing(Student.BY_LN)
            .thenComparing(Student.BY_ID)
        );

        Common.fillStudentsCollection(students, Student::new);
        Common.printCollection(students);
        Student s = new Student("Poe", "Edgar Allan", "2345678901");
        System.out.println("Poe in collection? - " + students.contains(s));

        // Thanks to using StudentFactory we can create a list of different possible types
        System.out.println("Erasmus students:");
        List<ErasmusStudent> erasmusStudents = new ArrayList<>();
        Common.fillStudentsCollection(erasmusStudents, ErasmusStudent::new);
        Common.printCollection(erasmusStudents);

        System.out.println("PhD students:");
        List<PhdStudent> phdStudents = new ArrayList<>();
        Common.fillStudentsCollection(phdStudents, PhdStudent::new);
        Common.printCollection(phdStudents);
    }
}
