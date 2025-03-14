package lectures.l12;

public class ErasmusStudent extends Student {

    public ErasmusStudent(String firstName, String lastName, String studentId) {
        super(firstName, lastName, studentId);
    }

    @Override
    public String toString() {
        return "E " + super.toString();
    }
    
}
