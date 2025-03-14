package lectures.l09;

import java.util.HashSet;
import java.util.Set;

public class Example3c {
    public static void main(String[] args) {
        String[] names = {"Boris", "Anna", "Boris", "Wang", "Anna", "Li", "Chen", "Li"};

        writeInReverse(names);
    }

    private static void writeInReverse(String[] names) {
        Set<String> set = new HashSet<>();      // Best for fast lookup
        for (int i = names.length - 1; i >= 0; i--) {
            if (set.add(names[i])) {
                System.out.println(names[i]);
            }
        }
    }
}
