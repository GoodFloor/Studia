package lectures.l09;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Example3b {
    public static void main(String[] args) {
        String[] names = {"Boris", "Anna", "Boris", "Wang", "Anna", "Li", "Chen", "Li"};

        writeInReverse(names);
    }

    private static void writeInReverse(String[] names) {
        List<String> list = new ArrayList<>();      
        Set<String> set = new HashSet<>();      // Best for fast lookup
        for (int i = names.length - 1; i >= 0; i--) {
            if (set.add(names[i])) {
                list.add(names[i]);
            }
        }
        for (String string : list) {
            System.out.println(string);
        }
    }
}
