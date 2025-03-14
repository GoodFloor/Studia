package lectures.l13;

import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipFile;

public class ZipStreamDemo {
    public static void main(String[] args) throws IOException {
        ZipFile zip = new ZipFile("C:\\Users\\lukas\\OneDrive\\Dokumenty\\Studia\\unizg_sem6_oop\\src\\lectures\\l13\\lyrics.zip");
        zip.stream()
            .filter(ze -> ze.getName().endsWith(".txt"))
            // .forEach(ze -> System.out.println(ze.getName()));
            .forEach(e1 -> {
                try (Scanner sc = new Scanner(zip.getInputStream(e1))) {
                    System.out.println("--------------------------------");
                    System.out.println(sc.nextLine());
                    System.out.println(sc.nextLine());
                    System.out.println(sc.nextLine());
                    System.out.println("--------------------------------");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        zip.close();
    }
}
