package lectures.l11.example1;

import java.util.function.Predicate;

public class PricePredicate implements Predicate<Car> {
    double maxPrice;

    public PricePredicate(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public boolean test(Car t) {
        return t.getPrice() < maxPrice;
    }
}
