package lectures.l09;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Loader {

    public static List<Integer> loadNonNegativeNumbers(Scanner sc) {
        List<Integer> numbers = new LinkedList<>();
        System.out.println("Enter integer numbers (quit or negative number to end):");
        while (true) {
            if (sc.hasNextInt()) {
                int x = sc.nextInt();
                if (x >= 0) {
                    numbers.add(x);
                } else {
                    break;
                }
            } else {
                String value = sc.next();
                if ("quit".equals(value)) {
                    break;
                }
                System.out.println("Unexpected input: " + value);
            }
        }
        return numbers;
    }

    public static <T> void addToSet(Set<T> s, T[] arr) {
        for (T n : arr) {
            s.add(n);
        }
    }

}
