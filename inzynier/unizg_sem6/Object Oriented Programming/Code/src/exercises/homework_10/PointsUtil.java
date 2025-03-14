package exercises.homework_10;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointsUtil {
    public static void updatePoints(Map<String, Integer> map, Path file) {
        try {
            List<String> points = Files.readAllLines(file);
            for (String line : points) {
                String p = line.substring(0, 2);
                if (p.charAt(0) == ' ') {
                    p = p.substring(1);
                }
                String c = line.substring(3);
                int pI = Integer.parseInt(p);
                Integer currP = map.get(c);
                if (currP == null) {
                    map.put(c, pI);
                } else {
                    map.put(c, pI + currP);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Map<String, Integer> getForYear(int year) throws IOException {
        Map<String, Integer> points = new HashMap<>();
        Path path = Path.of("src\\exercises\\homework_10\\data\\" + year + "\\voting");
        if (Files.exists(path)) {
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, new VotingFilter())) {
                for (Path path2 : ds) {
                    updatePoints(points, path2);
                }
            }
        }
        return points;        
    }
}
