package lectures.l12;

@FunctionalInterface
public interface StudentFactory <S extends Student> {
    public S create(String firstName, String lastName, String studentId);
}
