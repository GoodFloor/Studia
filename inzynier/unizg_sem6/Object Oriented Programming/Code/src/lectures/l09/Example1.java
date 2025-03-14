package lectures.l09;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Example1 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = Loader.loadNonNegativeNumbers(sc);

        if (!numbers.isEmpty()) {
            double avg = calcAverage(numbers);
            Predicate<Number> belowAverage = new BelowThreshold(avg);
            numbers.removeIf(belowAverage);

            Collections.sort(numbers);

            System.out.format("Numbers >= %.2f %n", avg);
            for (Integer n : numbers) {
                System.out.println(n);
            }
        }
    }

    private static double calcAverage(List<Integer> numbers) {
        int sum = 0;
        for (Integer d : numbers) {
            sum += d;
        }
        return (double)sum / numbers.size();
    }
}
