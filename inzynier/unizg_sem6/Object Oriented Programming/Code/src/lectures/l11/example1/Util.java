package lectures.l11.example1;

import java.util.function.Predicate;

public class Util {

    public static boolean isCarCheap(Car car) {
        return car.getPrice() < 100000;
    }

    public static Predicate<Car> getMeAPredicate() {
        return Util::isCarCheap;
    }
}
