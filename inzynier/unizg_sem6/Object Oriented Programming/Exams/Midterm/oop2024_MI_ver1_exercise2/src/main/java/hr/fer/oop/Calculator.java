package hr.fer.oop;

public class Calculator {
    public static double score(Animal animal) {
        if (animal instanceof Dog) {
            return (animal.getAge() + ((Dog)animal).getBarkSound().length()) / 2.;
        } else if (animal instanceof Cat) {
            return (animal.getId().length() + ((Cat)animal).getPurrSound().length()) / 2.;

        } else {
            return 0.;
        }
    }
}
