package exercises.homework_10;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PointSaver {
    private static final int[] pointValues = {12, 10, 8, 7, 6, 5, 4, 3, 2, 1};
    public static void savePoints(String year, String country, String type, String[] points) {
        File outputFile = new File("src\\exercises\\homework_10\\data\\" + year + "\\voting\\" + country + "-" + type + ".txt");
        if (!outputFile.exists()) {
            outputFile.getParentFile().mkdirs();
        }
        try (OutputStream output = new FileOutputStream(outputFile)) {
            for (int i = 0; i < points.length; i++) {
                output.write(String.format("%2d %s%n", pointValues[i], points[i]).getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
