package lectures.l09;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MapExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> namesH = new HashMap<>();
        Map<String, Integer> namesT = new TreeMap<>();
        Map<String, Integer> namesLH = new LinkedHashMap<>();

        String name;
        while (!(name = sc.nextLine()).equals("quit")) {
            Integer val = namesH.get(name);
            namesH.put(name, val == null ? 1 : val + 1);
            namesT.put(name, val == null ? 1 : val + 1);
            namesLH.put(name, val == null ? 1 : val + 1);
        }
        for (Map.Entry<String, Integer> entry : namesH.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
        System.out.println();
        for (Map.Entry<String, Integer> entry : namesT.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
        System.out.println();
        for (Map.Entry<String, Integer> entry : namesLH.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
        sc.close();
    }
}
