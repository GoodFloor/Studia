package laboratories.demo04.task1;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class JavaProjectFileVisitor extends SimpleFileVisitor<Path> {

    private long minFileSize;
    private Path minFilePath;
    private Path currentDir;
    private Map<Path, Integer> filesCount;

    public JavaProjectFileVisitor() {
        minFileSize = -1;
        filesCount = new HashMap<>();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        currentDir = dir;
        filesCount.put(dir, 0);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isRegularFile()) {
            filesCount.put(currentDir, filesCount.get(currentDir) + 1);
            if (attrs.size() < minFileSize || minFileSize == -1) {
                minFileSize = attrs.size();
                minFilePath = file;
            }
        }
        return FileVisitResult.CONTINUE;
    }

    /**
     * @return Path from the smallest file
     */
    public Path getMinFilePath() {
        return minFilePath;
    }

    /**
     * @return The size of the smallest file
     */
    public long getMinFileSize() {
        return minFileSize;
    }

    /**
     * @return Path of the directory that has the least amount of files, but containing at least one file. Also, it counts files only in this level and doesn't count dirs
     */
    public Path getFewestFilesDirPath() {
        int smallest = -1;
        Path smallestPath = null;
        for (Path path : filesCount.keySet()) {
            if (filesCount.get(path) > 0 && (filesCount.get(path) < smallest || smallest == -1)) {
                smallest = filesCount.get(path);
                smallestPath = path;
            }
        }
        return smallestPath;
    }
}
