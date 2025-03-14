package lectures.l10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class ReadFileExample {
    public static void main(String[] args) {
        Path p = Path.of("C:\\Users\\lukas\\OneDrive\\Dokumenty\\Studia\\unizg_sem6_oop\\src\\lectures\\l10\\lyrics.txt");
        
        // To read all lines we can use scanner
        try (Scanner sc = new Scanner(p)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println("\"" + line + "\"");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("\n----------------------------------------------------------------\n");

        // We can also use function Files.readAllLines(Path)
        try {
            List<String> lines = Files.readAllLines(p);
            for (String line : lines) {
                System.out.println("\"" + line + "\"");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("\n----------------------------------------------------------------\n");

        // We can also use function Files.readString(Path)
        try {
            String everything = Files.readString(p);
            System.out.println("\"" + everything + "\"");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("\n----------------------------------------------------------------\n");

        // But all of these methods don't work very well with big files
    }
}
