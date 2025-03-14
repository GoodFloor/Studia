package lectures.l11.example1;

import java.util.function.Predicate;

public class CheapCarPredicate implements Predicate<Car> {

    @Override
    public boolean test(Car t) {
        return t.getPrice() < 100000;
    }
    
}
