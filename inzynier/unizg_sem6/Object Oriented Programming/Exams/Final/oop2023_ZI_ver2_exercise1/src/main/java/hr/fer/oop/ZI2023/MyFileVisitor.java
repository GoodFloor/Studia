package hr.fer.oop.ZI2023;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
    private final Path searchedFile;
    private String firstLine;
    private String folderName;

    public MyFileVisitor(String path) {
        this.searchedFile = Path.of(path);
    }
    public String getFirstLine() throws IOException {
        if (firstLine != null) {
            return firstLine;
        }
        throw new IOException("File " + searchedFile.toString() + " does not exist!");
    }
    public String getFolderName() throws IOException {
        if (folderName != null) {
            return folderName;
        }
        throw new IOException("File " + searchedFile.toString() + " does not exist!");
    }
    private void readFirstLine(Path path) {
        try (InputStream input = Files.newInputStream(path)) { 
            StringBuilder result = new StringBuilder();
            while (true) {
                char b = (char)input.read();
                if (b != 10 && b != -1) {
                    result.append(b);
                } else {
                    break;
                }
            }
            firstLine = result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FileVisitResult visitFile(Path arg0, BasicFileAttributes arg1) throws IOException {
        if (arg0.getFileName().equals(searchedFile.getFileName())) {
            folderName = arg0.getParent().toAbsolutePath().toString();
            readFirstLine(arg0);
            return FileVisitResult.TERMINATE;
        }
        return super.visitFile(arg0, arg1);
    }
    
}
