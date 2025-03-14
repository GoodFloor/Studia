package exercises.homework_04;
public class Teacher extends Person {
    private String email;
    private String subject;
    private double salary;
    
    public Teacher(String name, String surname, int age, String email, String subject, double salary) {
        super(name, surname, age);
        this.email = email;
        this.subject = subject;
        this.salary = salary;
    }
    public static void increaseSalary(int percentage, Teacher... teachers) {
        for (Teacher teacher : teachers) {
            teacher.increaseSalary(percentage);
        }
    }
    public void increaseSalary(int percentage) {
        salary = salary * percentage / 100;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "Teacher " + name + " " + surname + ", email: " + email + ", subject:" + subject + ", age="
                + age + ", salary=" + salary;
    }
    public boolean equals(Teacher other) {
        return this.email == other.email;
    }
    
}
