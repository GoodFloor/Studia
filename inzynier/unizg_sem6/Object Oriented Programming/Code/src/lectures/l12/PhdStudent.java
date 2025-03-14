package lectures.l12;

public class PhdStudent extends Student {

    public PhdStudent(String firstName, String lastName, String studentId) {
        super(firstName, lastName, studentId);
    }

    @Override
    public String toString() {
        return "P " + super.toString();
    }
    
}
