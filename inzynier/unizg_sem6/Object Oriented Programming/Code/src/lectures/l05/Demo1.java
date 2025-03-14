package lectures.l05;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Demo1 {
    public static void scannerTest() throws IOException {
        Path p = Path.of("C:\\test\\file.txt");
        Scanner sc = new Scanner(p);
        sc.close();
    }

    public static void scannerResource() {
        Path p = Path.of("C:\\test\\file.txt");
        try (Scanner sc = new Scanner(p)) { // Resource needs to implement autoCloseable
            // Some code
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static double trianglePerimiter(int a, int b, int c) {
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("This is not a triangle");
        }
        double s = (a + b + c) / 2.;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    public static void main(String[] args) {
        try {
            System.out.println(trianglePerimiter(1, 1, 6));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        // System.out.println(trianglePerimiter(1, 1, 6));

        try {
            scannerTest();
        } catch (IOException e) {
            System.out.println("ERROR: No file named \"" + e.getMessage() + "\" found.");
        }

        System.out.println("----------------------------------------------------------------");
        String[] arr = {"123", "abc"};
        try {
            for (int i = 0; i < arr.length; i++) {
                System.out.println(Integer.parseInt(arr[i]));
            }
        } catch (ClassCastException e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            System.out.println("----------------------------------------------------------------");
        }
    }
}