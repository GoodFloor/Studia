package lectures.l12;

import java.util.Collection;

public class Common {

    public static <S extends Student> void fillStudentsCollection(Collection<S> students, StudentFactory<S> factory) {
        students.add(factory.create("Joe", "Black", "1234567890"));
        students.add(factory.create("Edgar Allan", "Poe", "2345678901"));
        students.add(factory.create("Immanuel", "Kant", "3456789012"));
        students.add(factory.create("Joe", "Rock", "0123456789"));
        students.add(factory.create("Joe", "Black", "5678901234"));
        students.add(factory.create("Francis", "Drake", "4567890123"));
    }

    public static <T> void printCollection(Iterable<T> collection) {
        for (T t : collection) {
            System.out.println(t);
        }
        System.out.println();
    }

}
