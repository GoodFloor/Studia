package lectures.l08;
public class Demo2 {
    public static void intGreaterThanX(Integer[] arr, Integer x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > x) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
    public static <T extends Number & Comparable<T>> void numGreaterThanX(T[] arr, T x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(x) > 0) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
    public static <T extends Comparable<T>> void greaterThanX(T[] arr, T x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(x) > 0) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6};
        intGreaterThanX(arr, 3);
        numGreaterThanX(arr, 3);
        greaterThanX(arr, 3);

    }
}
