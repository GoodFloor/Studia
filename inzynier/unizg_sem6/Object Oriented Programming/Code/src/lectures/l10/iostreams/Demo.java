package lectures.l10.iostreams;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Demo {
    public static void main(String[] args) {
        Path p = Path.of("C:\\Users\\lukas\\OneDrive\\Dokumenty\\Studia\\unizg_sem6_oop\\src\\lectures\\l10\\file2.txt");
        // InputStream is = new FileInputStream(p.toFile())
        try (InputStream is = Files.newInputStream(p)) {
            while (true) {
                int b = is.read();
                if (b == -1) {
                    break;
                }
                if (b == 10) {
                    System.out.println(b);
                } else {
                    System.out.print(b + " ");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // But system reads a chunk of data at once, not byte by byte so we can load a chunk of data to a buffer
        try (InputStream is = Files.newInputStream(p)) {
            byte[] buff = new byte[256];
            while (true) {
                int size = is.read(buff);
                if (size < 1) {
                    break;
                }
                for (int i = 0; i < size; i++) {
                    int b = buff[i];
                    if (b == 10) {
                        System.out.println(b);
                    } else {
                        System.out.print(b + " ");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Creating custom OutputStream that processes data befor saving them - example of Decorator 
        Path src = Path.of("C:\\Users\\lukas\\OneDrive\\Dokumenty\\Studia\\unizg_sem6_oop\\src\\lectures\\l10\\lyrics.txt");
        Path scrambled = Path.of("C:\\Users\\lukas\\OneDrive\\Dokumenty\\Studia\\unizg_sem6_oop\\src\\lectures\\l10\\iostreams\\lyricsScrambled.txt");
        Path unscrambled = Path.of("C:\\Users\\lukas\\OneDrive\\Dokumenty\\Studia\\unizg_sem6_oop\\src\\lectures\\l10\\iostreams\\lyricsUnscrambled.txt");
        try {
            InputStream fis = Files.newInputStream(src);
            OutputStream fos = Files.newOutputStream(scrambled);
            OutputStream sos = new ScrambledOutputStream(fos);
            while (true) {
                int b = fis.read();
                if (b == -1) {
                    break;
                }
                sos.write(b);
            }
            sos.close();
            fis.close();

            fis = Files.newInputStream(scrambled);
            fos = Files.newOutputStream(unscrambled);
            sos = new ScrambledOutputStream(fos);
            while (true) {
                int b = fis.read();
                if (b == -1) {
                    break;
                }
                sos.write(b);
            }
            sos.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
