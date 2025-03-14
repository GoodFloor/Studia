package exercises.homework_10;

import java.util.Arrays;

public class JuryPoints {
    public static void main(String[] args) {
        if (args.length != 12) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        String year = args[0];
        String country = args[1];
        String[] points = Arrays.copyOfRange(args, 2, 12);
        PointSaver.savePoints(year, country, "jury", points);
    }
}
