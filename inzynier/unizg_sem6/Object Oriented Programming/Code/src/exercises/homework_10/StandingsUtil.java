package exercises.homework_10;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class StandingsUtil {
    public static List<Pair<String, Integer>> orderByPoints(Map<String, Integer> map) {
        List<Pair<String, Integer>> result = new LinkedList<>();
        Map<Integer, Set<String>> tree = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer points = entry.getValue();
            String country = entry.getKey();
            Set<String> equalCountires = tree.get(points);
            if (equalCountires == null) {
                equalCountires = new HashSet<>();
                tree.put(points, equalCountires);
            }
            equalCountires.add(country);
        }

        for (var entry : tree.entrySet()) {
            for (String country : entry.getValue()) {
                result.addFirst(new Pair<String,Integer>(country, entry.getKey()));
            }
        }
        return result;
    }
}
