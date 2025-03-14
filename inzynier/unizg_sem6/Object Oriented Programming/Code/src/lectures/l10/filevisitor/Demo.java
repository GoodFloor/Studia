package lectures.l10.filevisitor;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;

public class Demo {
    public static void main(String[] args) {
        Path startingDirectory = Path.of("C:\\Users\\lukas\\OneDrive\\Dokumenty\\Studia\\unizg_sem6_oop\\");

        FileVisitor<Path> fv = new MyFileVisitor(4 * 1024);
        try {
            Files.walkFileTree(startingDirectory, fv);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n----------------------------------------------------------------\n");

        // We don't want to implement all the methods so instead of implementing FileVisitor we can extend SimpleFileVisitro
        FileVisitor<Path> sfv = new MySimpleFileVisitor(4 * 1024);
        try {
            Files.walkFileTree(startingDirectory, sfv);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
