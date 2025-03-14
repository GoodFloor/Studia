package lectures.l13;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsDemo {
    public static void main(String[] args) {
        List<Student> list = StudentData.load();

        // Printing list with foreach loop
        System.out.println("for loop:");
        for (Student s : list) {
            System.out.println(s);
        }

        // Printing with forEach method
        System.out.println("\n.forEach():");
        list.forEach(s -> System.out.println(s));

        // Filtering and printing with streams
        System.out.println("\nStream:");
        list.stream()
            .filter(s -> s.getPoints() >= 50)
            .filter(s -> s.getLastName().endsWith("nić"))
            .forEach(s -> System.out.println(s));
        long count = list.stream()
            .filter(s -> s.getPoints() == 50)
            .count();
        System.out.println("Count: " + count);
        Stream<Student> ss = list.stream();
        ss = ss.filter(s -> s.getLastName().contains("R"));
        ss.forEach(System.out::println);
        
        // Can't do that because terminal method(forEach()) has been already invoked on this stream
        // ss.filter(s -> s.getPoints() > 40).forEach(s -> System.out.println(s));

        // Sorting
        System.out.println("\nSorting:");
        list.stream()
            .filter(s -> s.getPoints() >= 30 && s.getPoints() <= 40)
            // .sorted()
            .sorted(Student.BY_LAST_NAME.reversed().thenComparing(Student.BY_POINTS.reversed()))
            .forEach(s -> System.out.println(s));

        System.out.println("\nDefault sorting is by id (method Student::compareTo):");
        list.stream()
            .sorted()
            .forEach(s -> System.out.println(s));
        
        // We can also change the streamed object and remap it to different type
        System.out.println("\nRemapping to list of surnames:");
        list.stream()
            .map(s -> s.getLastName())  // We get a stream of Strings
            .distinct()                 // We filter out the duplicates
            // .sorted()                // It still works as regular Stream but default String comparing doesn't work with non-english characters
            .sorted(Collator.getInstance(Locale.forLanguageTag("hr")))  // We can again use the language-specific Collator
            .forEach(x -> System.out.println(x));

        System.out.println("\nMapping only points:");
        list.stream()
            .map(s -> s.getPoints())    // We get Stream<Integer>
            .forEach(s -> System.out.println(s));

        // Collect data from stream back to the list
        System.out.println("\nConverting stream, back to a list:");
        List<Student> list2 = list.stream()
                                  .filter(s -> s.getPoints() >= 45)
                                  .toList();
        list2.forEach(s -> System.out.println(s));
        
        // Collect to "something"
        System.out.println("\nConverting to list using Collectors:");
        List<Student> list3 = list.stream()
                                  .sorted(Student.BY_LAST_NAME)
                                  .collect(Collectors.toList());
        list3.forEach(s -> System.out.println(s));

        // Collect to Set
        System.out.println("\nCollect to Set:");
        Set<String> set1 = list.stream()
                               .map(s -> s.getLastName())
                               // .distinct() // not needed because a Set is distinct by default 
                               .collect(Collectors.toSet());
        set1.forEach(s -> System.err.println(s));

        // Collect to Map
        System.out.println("\nCollect to Map:");
        Map<String, Student> map1 = 
            list.stream()
                .collect(Collectors.toMap(
                    s -> s.getStudentID(), 
                    s -> s));
        map1.forEach((k, v) -> System.out.println(k + " -> " + v));

        // Map<id, points>
        System.out.println("\nMapping points:");
        Map<String, Integer> map2 = 
            list.stream()
                .filter(s -> s.getPoints() >= 40)
                .collect(Collectors.toMap(
                    s -> s.getStudentID(), 
                    s -> s.getPoints()));
        map2.forEach((k, v) -> System.out.println(k + " -> " + v));

        // Map<lastName, firstName>
        System.out.println("\nResolving conflict while mapping:");
        Map<String, String> map3 = 
            list.stream()
                .collect(Collectors.toMap(
                    s -> s.getLastName(), 
                    s -> s.getFirstName(),
                    (oldV, newV) -> oldV + " & " + newV));  // Function that resolves confilcts
        map3.forEach((k, v) -> System.out.println(k + " -> " + v));

        // Map<lastName, List<firstNames>>
        System.out.println("\nSame mapping but with list of first names:");
        Map<String, List<String>> map4 = 
            list.stream()
                .collect(Collectors.toMap(
                    s -> s.getLastName(), 
                    s -> { 
                        List<String> l = new ArrayList<>();
                        l.add(s.getFirstName());
                        return l;
                    },
                    (oldV, newV) -> {
                        oldV.addAll(newV);
                        return oldV;
                    }));
        map4.forEach((k, v) -> System.out.println(k + " -> " + v));

        // All the last names sorted into the String, delimited by ','
        System.out.println("\nAll the last names sorted into the String, delimited by ','");
        String allNames = list.stream()
                        .map(s -> s.getLastName())
                        .distinct()
                        .sorted(Collator.getInstance(Locale.forLanguageTag("hr")))
                        .collect(Collectors.joining(", "));
        System.out.println(allNames);

        // Mapping to primitive types (not usually possible with generics)
        System.out.println("\nWe can force mapping to a primitive types");
        list.stream()                           // Stream<Student>
            .mapToInt(s -> s.getPoints())       // returns IntStream
            .average()                          // returns OptionalDouble - a Double that has a special state in case the stream was empty
            .ifPresent(v -> System.out.println("AVG: " + v));

        // OptionalDouble type
        OptionalDouble od = list.stream().filter(s -> s.getFirstName() == "John").mapToInt(s -> s.getPoints()).average();
        if (od.isPresent()) {
            System.out.println("Everything is good. AVG = " + od.getAsDouble());
        } else {
            System.out.println("The stream was empty - couldn't calculate average");
        }

    }
}
