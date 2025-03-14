package lectures.l11.example1;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Demo {
    public static void main(String[] args) {
        List<Car> cars = CarCatalog.loadCars();

        // Let's print all diesel cars
        System.out.println("Diesel cars: ");
        printDieselCars(cars);

        // What if we want to print all petrol cars? We need to rewrite this method changing one line. We can also add an argument of wanted car type
        // What if we also want to filter out cars that are to expensive? We can add another argument but for more car parameters it might get inconvenient
        // Better idea is to use a Predicate
        System.out.println("\nSpecialized predicate");
        Predicate<Car> cheapCar = new CheapCarPredicate();
        printCars(cars, cheapCar);

        // But we don't want to create new Predicate class for every property we might want
        System.out.println("\nAnonymous predicate: ");
        Predicate<Car> cheaperCar = new Predicate<Car>() {
            @Override
            public boolean test(Car t) {
                return t.getPrice() < 50000;
            }
        };
        printCars(cars, cheaperCar);

        // We can make predicate that takes an argument
        System.out.println("\nPredicate with argument");
        cheapCar = new PricePredicate(100000);
        printCars(cars, cheapCar);

        // We can extend existing Predicate
        System.out.println("\nExtended predicate");
        Predicate<Car> extendedPredicate = new PricePredicate(100000) {
            // Example of static block in anonymous class
            int randomPower;
            {
                randomPower = 70 + (int) (Math.random() * 50);
                System.out.println("Random power: " + randomPower);
            }

            @Override
            public boolean test(Car t) {
                return super.test(t) && t.getPower() > randomPower;
            }
        };
        printCars(cars, extendedPredicate);

        // Instead of anonymous classes we can use lambda expression since Predicate has only one method
        // We only specify this one method like this: (arguments) -> {method body}
        System.out.println("\nLambda expression");
        Predicate<Car> lambdaPredicate = (Car car) -> {
            return car.getType() == CarType.DIESEL;
        };
        printCars(cars, lambdaPredicate);

        // We can skip defining argument type in lambda expression since there is only one possibility of what method do we mean
        System.out.println("\nLambda without specifying argument type");
        lambdaPredicate = (car) -> {
            return car.getType() == CarType.DIESEL;
        };
        printCars(cars, lambdaPredicate);        

        // If lambda expression has only one line, we can ommit curly braces and "return" keyword, also brackets over argument are optional here but sometimes is needed 
        System.out.println("\nCompact lambda expression");
        Predicate<Car> compactLambda = (car) -> car.getType() == CarType.DIESEL;
        printCars(cars, compactLambda);

        // We can delegate the job of doing something with accepted cars to a different function
        System.out.println("\nUsing consumer");
        Consumer<Car> action = car -> System.out.println("Accepted car: " + car);
        procerssCars(cars, (car) -> car.getType() == CarType.DIESEL, action);

        // We can for example invoke a method in lambda expression
        System.out.println("\nLambda expression with invoking method");
        printCars(cars, (car) -> Util.isCarCheap(car));

        // But since called method and method in lambda expressino have same arguments and return type we can write it like this
        // :: returns reference to a method (this way it only works for static methods but we can also invoke it like 'instance::methodName' for non-static methods)
        System.out.println("\nUsing method reference");
        printCars(cars, Util::isCarCheap);
        System.out.println("\nCalling method");
        printCars(cars, Util.getMeAPredicate());


        // Let's find two most similiar cars with another FunctionalInterfaces (with only one method)
        BiFunction<Car, Car, Integer> similiarity = (a, b) -> (int) Math.abs(a.getPrice() - b.getPrice());
        BiConsumer<Car, Car> biAction = (a, b) -> System.out.format("The most similiar are: %n\t%s%n\t%s%n", a, b);
        theMostSimiliarCar(cars, similiarity, biAction);


    }

    private static void printDieselCars(Iterable<Car> cars) {
        for (Car car : cars) {
            if (car.getType() == CarType.DIESEL) {
                System.out.println(car);
            }
        }
    }

    private static void printCars(Iterable<Car> cars, Predicate<Car> predicate) {
        for (Car car : cars) {
            if (predicate.test(car)) {
                System.out.println(car);
            }
        }
    }

    private static void procerssCars(Iterable<Car> cars, Predicate<Car> predicate, Consumer<Car> action) {
        for (Car car : cars) {
            if (predicate.test(car)) {
                action.accept(car);
            }
        }
    }

    private static void theMostSimiliarCar(Iterable<Car> cars, BiFunction<Car, Car, Integer> distanceFunction, BiConsumer<Car, Car> action) {

        class CarPair {
            public Car first, second;
            public CarPair(Car first, Car second) {
                this.first = first;
                this.second = second;
            }
        }

        CarPair pair = null;
        int min = Integer.MAX_VALUE;

        for (Car first : cars) {
            for (Car second : cars) {
                if (first == second) {
                    continue;
                }
                int distance = distanceFunction.apply(first, second);
                if (pair == null || distance < min) {
                    if (pair == null) {
                        pair = new CarPair(first, second);
                    } else {
                        pair.first = first;
                        pair.second = second;
                    }
                    min = distance;
                }
            }
        }

        if (pair != null) {
            action.accept(pair.first, pair.second);
        }
    }
}
