package laboratories.demo04.task2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileEditor {
    /**
     * Reads input file encoded in UTF-8, process content and writes it to output file.
     * @param inputFile input file
     * @param outputFile output file
     * @throws IOException
     */
    public static void processFile(Path inputFile, Path outputFile) throws IOException {
        InputStream input = Files.newInputStream(inputFile);
        OutputStream output = Files.newOutputStream(outputFile);
        boolean lineBegin = true;
        while (true) {
            int b = input.read();
            if (b == -1) {
                break;
            } else if (b == '\t' && lineBegin) {
                output.write(' ');
                output.write(' ');
            } else {
                output.write(b);
                if (b == '\n') {
                    lineBegin = true;
                } else {
                    lineBegin = false;
                }
            }
        }
        input.close();
        output.close();
    }
}
