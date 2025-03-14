import java.util.Scanner;

public class Main {
    private static void ex1() {
        for (int i = 10; i <= 1000000; i *= 10) {
            System.out.println(calculatePi1(i));            
        }
    }
    private static double calculatePi1(int n) {
        double sum = 0.0;
        double sign = 1.0;
        for (int i = 0; i < n; i++) {
            sum += sign / (2.0 * i + 1.0);
            sign *= -1.0;
        }
        return sum * 4.0;
    }

    private static void ex2() {
        System.out.println(calculatePi2());
    }
    private static int calculatePi2() {
        double sum = 0.0;
        int li = 0;
        for (int i = 1; Math.abs(Math.sqrt(sum * 6.0) - Math.PI) >= 0.00001; i++) {
            sum += 1.0 / (i * i);
            li = i;
        }
        return li;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean stillGoing = true;
        while (stillGoing) {
            System.out.print("Pick exercise number(1 - 8) or type '0' to exit: ");
            switch (sc.nextInt()) {
                case 0:
                    stillGoing = false;
                    break;
                case 1:
                    ex1();
                    break;
                case 2:
                    ex2();
                    break;
                default:
                    System.out.println("You picked the wrong number!");
                    break;
            }
        }
        System.out.println("Dovidzenja!");
        sc.close();
    }
}
