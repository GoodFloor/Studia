package lectures.l10;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadingDirectory {
    public static void main(String[] args) {
        Path p = Path.of("C:\\Users\\lukas\\OneDrive\\Dokumenty\\Studia\\unizg_sem6_oop\\");
        
        // We can list contents of a directory
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(p);) {
            for (Path element : ds) {
                System.out.println(element);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("\n----------------------------------------------------------------\n");

        // We can filter the output with DirectoryStream.Filter
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(p, new MyDSFilter());) {
            for (Path element : ds) {
                System.out.println(element);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("\n----------------------------------------------------------------\n");

        // We can make more complex filters
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(p, new MyAdvancedDSFilter(".txt", ".java"))) {
            for (Path element : ds) {
                System.out.println(element);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
