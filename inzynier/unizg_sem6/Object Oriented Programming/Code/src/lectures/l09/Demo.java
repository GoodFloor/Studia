package lectures.l09;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Demo {

    public static void main(String[] args) {

        // Arrays are great but what if we want to dynamically change its size? We can use ArrayList then
        ArrayList<Integer> aL = new ArrayList<>(10);    // initial capacity
        System.out.println("AL size: " + aL.size());                    // size != capacity
        for (int i = 0; i < 1000; i++) {
            aL.add(i * 2);
        }
        System.out.println("AL new size: " + aL.size());
        System.out.println("AL[750] = " + aL.get(750));
        // ArrayList stores references to variables

        // If we want to remove or insert elements in the middle of the list it'tS not good for performance - in that case it'tS better to use LinkedList
        List<Integer> lL = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            lL.add(2 * i);
        }
        lL.add(5, 5);
        System.out.println("LL: " + lL);
        int sum = 0;
        for (Integer integer : lL) {
            sum += integer;
        }
        System.out.println("LL sum of elements: " + sum);
        
        // Collection that doesn't contain duplicates is a Set
        // Integer[] intArr = {10, 7, 12, 5, 8, 7, 15, 11, 9, 10};
        Integer[] intArr = {23, 76, 55, 23, 12, 99, 76, 11, 10};
        Set<Integer> tS = new TreeSet<>();                               // TreeSet stores elements in red-black tree
        Loader.addToSet(tS, intArr);
        System.out.println("TreeSet: \t" + tS);

        Set<Integer> hS = new HashSet<>();                              // HashSet stores elements in buckets
        Loader.addToSet(hS, intArr);
        System.out.println("HashSet: \t" + hS);

        Set<Integer> lHS = new LinkedHashSet<>();                       // LinkedHashSet is a HashSet that also remembers order of inputed elements
        Loader.addToSet(lHS, intArr);
        System.out.println("LinkedHashSet: \t" + lHS);
        

    }
}