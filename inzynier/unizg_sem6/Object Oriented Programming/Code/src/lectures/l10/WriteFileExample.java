package lectures.l10;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

public class WriteFileExample {
    public static void main(String[] args) {
        Path p1 = Path.of("C:\\Users\\lukas\\OneDrive\\Dokumenty\\Studia\\unizg_sem6_oop\\src\\lectures\\l10\\file.txt");
        Path p2 = Path.of("C:\\Users\\lukas\\OneDrive\\Dokumenty\\Studia\\unizg_sem6_oop\\src\\lectures\\l10\\file2.txt");

        // PrintWriter - old class using Files
        try (PrintWriter pw = new PrintWriter(p1.toFile())) {
            pw.println("weeee");
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        // If we want to append to already existing file we can use FileWriter(Path, true)
        try (FileWriter fw = new FileWriter(p2.toFile(), true); PrintWriter pw = new PrintWriter(fw)) {
            pw.println("Weeee");
        } catch (IOException e) {
            System.out.println(e);
        }    
        
        // Files.writeString(p, "Weeee");

    }
}
