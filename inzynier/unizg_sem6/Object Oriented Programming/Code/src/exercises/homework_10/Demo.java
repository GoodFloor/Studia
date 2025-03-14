package exercises.homework_10;

import java.io.IOException;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        System.out.println("Podaj rok: ");
        try (Scanner sc = new Scanner(System.in)) {
            int year = sc.nextInt();
            System.out.println(StandingsUtil.orderByPoints(PointsUtil.getForYear(year)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
