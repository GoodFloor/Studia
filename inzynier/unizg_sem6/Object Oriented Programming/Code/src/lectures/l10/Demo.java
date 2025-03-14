package lectures.l10;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class Demo {
    public static void main(String[] args) {
        // Old package: java.io - interface File
        // New package: java.nio - interface Path
        Path directory = Path.of("C:\\Users\\lukas\\OneDrive\\Dokumenty\\Studia\\unizg_sem6_oop\\src\\lectures\\l10");
        Path textFile = Path.of("C:\\Users\\lukas\\OneDrive\\Dokumenty\\Studia\\unizg_sem6_oop\\src\\lectures\\l10\\file.txt");
        File f = textFile.toFile();
        if (Files.exists(textFile)) {
            System.out.println(textFile + " exists");
        } else {
            System.out.println(textFile + " doesn't exist!");
        }
        if (Files.isDirectory(directory)) {
            System.out.println(directory + " is a directory");
        }
        if (Files.isRegularFile(textFile)) {
            System.out.println(textFile + " is a regular file");
        }
        for (Path path : textFile) {
            System.out.printf(path.toString() + " ");
        }
        System.out.println();
    }
}
