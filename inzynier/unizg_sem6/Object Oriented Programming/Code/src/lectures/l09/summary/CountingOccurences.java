package lectures.l09.summary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CountingOccurences {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            if (line.equals("quit")) {
                break;
            }
            list.add(line);

            Integer count = map.get(line);
            System.out.println("Current value for " + line + ": " + count);
            if (count == null) {
                map.put(line, 1);
            } else {
                map.put(line, count + 1);
            }
        }
        
        System.out.println("LIST: " + list);
        System.out.println("MAP: " + map);
        sc.close();
    }
}
