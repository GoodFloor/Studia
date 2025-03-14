package lectures.l09.summary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("John");
        list.add("Paul");
        list.add("George");
        System.out.println("LIST: " + list);
        list.add(1, "Ringo");
        System.out.println("LIST: " + list);

        Set<String> set = new HashSet<>();
        set.add("John");
        set.add("Paul");
        set.add("George");
        System.out.println("SET: " + set);
        set.add("Ringo");
        System.out.println("SET: " + set);

        Map<String, Integer> map = new HashMap<>();
        map.put("Marko", 3);
        map.put("John", 5);
        System.out.println("MAP: " + map);
        map.put("Marko", 99);
        System.out.println("MAP: " + map);
    }
}
