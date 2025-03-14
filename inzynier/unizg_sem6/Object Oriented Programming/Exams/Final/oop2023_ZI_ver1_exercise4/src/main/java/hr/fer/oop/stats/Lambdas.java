package hr.fer.oop.stats;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lambdas {

    public static BiConsumer<Stream<String>, 
            Map<String, Collection<Entry<Integer, Long>>>> loadFromStream = (lines, map) -> {
                // map = lines.collect(Collectors.toMap(
                //         line -> line.split("<")[0], 
                //         line -> {
                //                 int year = Integer.parseInt(line.split(",")[1]);
                //                 long value = Long.parseLong(line.split(",")[2]);
                //                 Collection<Entry<Integer, Long>> list = new LinkedList<>();
                //                 list.add(new SimpleEntry<Integer, Long>(year, value));
                //                 return list;
                //         },
                //         (oldL, newL) -> {
                //                 oldL.addAll(newL);
                //                 return oldL;
                //         }));
                // };
                lines.forEach(line -> {
                        String[] data = line.split(",");
                        String country = data[0];
                        int year = Integer.parseInt(data[1]);
                        long value = Long.parseLong(data[2]);
                        if (!map.containsKey(country)) {
                                map.put(country, new LinkedList<>());    
                        }
                        map.get(country).add(new SimpleEntry<>(year, value));
                });
        };


        //         // lines.collect(Collectors.toMap(
        //         //         line -> line.split(",")[0], 
        //         //         line -> {
        //         //                 int year = Integer.parseInt(line.split(","));
        //         //                 long value = Long.parseLong(line.split(","));
        //         //         }, 
        //         //         (arg0, arg1) -> {

        //         //         }
        //         // ));
        //     };
    
    public static BiFunction<Map<String, Collection<Entry<Integer, Long>>>, 
            Integer, Collection<Entry<String, Long>>> getValuesForYear = (map, year) -> {
                Collection<Entry<String, Long>> result = new HashSet<>();
                map.forEach((country, entrySet) -> {
                        entrySet.forEach(entry -> {
                                if (year.equals(entry.getKey())) {
                                        result.add(new SimpleEntry<>(country, entry.getValue()));
                                }
                        });
                });
                return result;
            };

    public static BiFunction<Map<String, Collection<Entry<Integer, Long>>>, 
            String, Entry<Integer, Long>> getMinForCountry = (map, country) -> {
                Optional<Entry<Integer, Long>> result = map.get(country)
                .stream()
                .sorted((arg0, arg1) -> (int)(arg0.getValue() - arg1.getValue()))
                .findFirst();
                return result.get();
        };

}
