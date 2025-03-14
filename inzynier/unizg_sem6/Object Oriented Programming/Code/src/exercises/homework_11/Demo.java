package exercises.homework_11;

import java.util.Iterator;

public class Demo {
    public static void main(String[] args) {
        Integer[] array = {0, -53, 4, 25, 8, -50};
        ImmutableContainer ic = new ImmutableContainer(array);

        for (Integer integer : ic) {
            System.out.format("%d", integer);
        }

        System.out.println();
        Iterator<Integer> iterator = ic.iterator();
        while (iterator.hasNext()) {
            System.out.format("%d", iterator.next());
        }

        System.out.println();
        for (Integer integer : ic) {
            for (Integer integer2 : ic) {
                System.out.format("(%3d, %3d)\n", integer, integer2);
            }
        }
    }
}
