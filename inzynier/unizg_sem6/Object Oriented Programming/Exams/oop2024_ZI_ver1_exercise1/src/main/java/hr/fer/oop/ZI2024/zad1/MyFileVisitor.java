package hr.fer.oop.ZI2024.zad1;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

public class MyFileVisitor implements FileVisitor<Path> {
    private List<Item> items;

    public MyFileVisitor() {
        this.items = new LinkedList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double totalSum = 0.;
        for (Item item : items) {
            totalSum += item.getTotalPrice();
        }
        return totalSum;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!file.toString().endsWith(".txt")) {
            return FileVisitResult.CONTINUE;
        }
        List<String> content = Files.readAllLines(file);
        for (String item : content) {
            String[] itemComponents = item.split(";");
            if (itemComponents.length != 3) {
                return FileVisitResult.CONTINUE;
            }
            try {
                items.add(new Item(itemComponents[0], Integer.parseInt(itemComponents[1]), Double.parseDouble(itemComponents[2])));
            } catch (NumberFormatException e) {
                continue;
            };
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

}
