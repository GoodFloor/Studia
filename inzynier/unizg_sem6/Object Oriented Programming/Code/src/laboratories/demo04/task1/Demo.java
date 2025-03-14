package laboratories.demo04.task1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Demo {
    public static void main(String[] args) throws IOException {
        JavaProjectFileVisitor visitor = new JavaProjectFileVisitor();
        File f = new File("src\\laboratories\\demo4\\task1\\folder1");
        Files.walkFileTree(f.toPath(), visitor);
        System.out.println(visitor.getMinFilePath()); // folder1/a.txt
        System.out.println(visitor.getMinFileSize()); // 10
        System.out.println(visitor.getFewestFilesDirPath()); // folder1
    }
}
