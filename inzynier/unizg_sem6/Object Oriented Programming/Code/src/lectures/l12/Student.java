package lectures.l12;

import java.util.Comparator;

public class Student {
    protected final String firstName;
    protected final String lastName;
    protected final String studentId;
    
    public Student(String firstName, String lastName, String studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName.substring(0, 2);
    }

    public String getLastName() {
        return lastName;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Student))
            return false;
        Student otherStudent = (Student)obj;
        return this.studentId.equals(otherStudent.studentId);
    }

    @Override
    public String toString() {
        return String.format("(%s) %s %s", studentId, firstName, lastName);
    }

    @Override
    public int hashCode() {
        return this.studentId.hashCode();
    }

    // Variable for Comparator
    public static final Comparator<Student> BY_ID = (s1, s2) -> s1.studentId.compareTo(s2.studentId);
    public static final Comparator<Student> BY_LN = (s1, s2) -> s1.lastName.compareTo(s2.lastName);
    public static final Comparator<Student> BY_FN = (s1, s2) -> s1.firstName.compareTo(s2.firstName);
}
