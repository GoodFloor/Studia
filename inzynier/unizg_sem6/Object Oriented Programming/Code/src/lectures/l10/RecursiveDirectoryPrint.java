package lectures.l10;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class RecursiveDirectoryPrint {

    private static void printRec(Path p, String prefix) throws IOException {
        DirectoryStream<Path> ds = Files.newDirectoryStream(p);
        for (Path entry : ds) {

            if (Files.isDirectory(entry)) {
                System.out.println(prefix.replace(' ', '-') + "D " + entry.getFileName());
                printRec(entry, prefix + "  ");
            } else {
                System.out.println(prefix + "  " + entry.getFileName());
            }
        }
        ds.close();
    }
    public static void main(String[] args) {
        Path p = Path.of("C:\\Users\\lukas\\OneDrive\\Dokumenty\\Studia\\unizg_sem6_oop\\");
        try {
            printRec(p, "|");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
