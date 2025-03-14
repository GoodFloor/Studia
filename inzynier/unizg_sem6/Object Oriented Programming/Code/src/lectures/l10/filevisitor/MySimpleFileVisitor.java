package lectures.l10.filevisitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MySimpleFileVisitor extends SimpleFileVisitor<Path> {

    long minSize;

    public MySimpleFileVisitor(long minSize) {
        this.minSize = minSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toString().endsWith(".txt") || attrs.size() > minSize) {
            System.out.println(file + "\tsize: " + attrs.size());
        }
        return FileVisitResult.CONTINUE;
    }
}
