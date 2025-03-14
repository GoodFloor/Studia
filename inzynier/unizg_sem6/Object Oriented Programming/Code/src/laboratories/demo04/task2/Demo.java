package laboratories.demo04.task2;

import java.io.IOException;
import java.nio.file.Path;

public class Demo {
    public static void main(String[] args) throws IOException {
        Path inputFile = Path.of("src\\laboratories\\demo4\\task2\\example.txt");
        Path outputFile = Path.of("src\\laboratories\\demo4\\task2\\processed.txt");
        FileEditor.processFile(inputFile, outputFile);
    }
}
