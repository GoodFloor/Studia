package lectures.l11;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Iterating {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        System.out.println("Using foreach loop");
        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("\nUsing class Iterator");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String s = it.next();
            System.out.println(s);
        }

        System.out.println("\nTwo Iterators are not connected in any way");
        Iterator<String> it1 = list.iterator();
        Iterator<String> it2 = list.iterator();
        System.out.println("1: " + it1.next());
        System.out.println("1: " + it1.next());
        System.out.println("2: " + it2.next());
        System.out.println("1: " + it1.next());
        System.out.println("2: " + it2.next());
        System.out.println("2: " + it2.next());

        System.out.println("\nWe can create custom classes implementing Iterable");
        MyNumber myNum = new MyNumber(12345);
        for (Integer integer : myNum) {
            System.out.println(integer);
        }
        // If we make the nested class public we can use it like this
        // DigitIterator di = new MyNumber.DigitIterator(myNum); // For static nested class
        // DigitIterator di = myNum.new DigitIterator(); // For non-static nested class
    }
}
