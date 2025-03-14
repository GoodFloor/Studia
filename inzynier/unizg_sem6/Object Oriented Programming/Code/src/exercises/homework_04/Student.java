package exercises.homework_04;
public class Student extends Person {
    private String studentId;
    private short academicYear;
    public Student(String name, String surname, int age, String studentId, short academicYear) {
        super(name, surname, age);
        this.studentId = studentId;
        this.academicYear = academicYear;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public short getAcademicYear() {
        return academicYear;
    }
    public void setAcademicYear(short academicYear) {
        this.academicYear = academicYear;
    }
    @Override
    public String toString() {
        return "Student " + name + " " + surname + ", studentId: " + studentId + ", academicYear="
                + academicYear + ", age=" + age;
    }
    public boolean equals(Student other) {
        return this.studentId == other.studentId;
    }
    
}
