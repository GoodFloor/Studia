package laboratories.lab04.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class Grep {
    List<String> content;
    /**
     * Creates grep object
     * @param file text file in UTF-8 format that need to be loaded.
     * @throws IOException if file can not be read
     */
    public Grep(Path file) throws IOException {
        content = Files.readAllLines(file);
    }

    /**
     * Finds all lines that contain word
     * @param word text that need to be searched
     * @return list of lines
     */
    public List<TextLine> findLinesWithWord(String word) {
        List<TextLine> result = new LinkedList<>();
        int lineNo = 1;
        for (String line : content) {
            if (line.contains(word)) {
                result.add(new TextLine(lineNo, line));
            }
            lineNo++;
        }
        return result;
    }

    /**
     * Finds all lines that contain word
     * @param word text that need to be searched
     * @return list of line numbers
     */
    public List<Integer> findLineNumbersWithWord(String word) {
        List<Integer> result = new LinkedList<>();
        int lineNo = 1;
        for (String line : content) {
            if (line.contains(word)) {
                result.add(lineNo);
            }
            lineNo++;
        }
        return result;
    }
}
