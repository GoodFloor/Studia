package laboratories.lab04.task1;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Demo {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("src\\laboratories\\lab4\\task1\\example.txt");

        Grep grep = new Grep(file);

        System.out.println("Lines:");
        List<TextLine> lines = grep.findLinesWithWord("Java");
        for (TextLine l : lines)
        System.out.println(l);

        System.out.println("\nLine numbers:");
        List<Integer> lineNumbers = grep.findLineNumbersWithWord("Java");
        for (Integer i : lineNumbers)
        System.out.println(i);
    }
}
