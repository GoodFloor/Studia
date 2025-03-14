package exercises.homework_10;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public class VotingFilter implements DirectoryStream.Filter<Path> {

    @Override
    public boolean accept(Path entry) throws IOException {
        String fileName = entry.getFileName().toString();
        return fileName.endsWith("-jury.txt") || fileName.endsWith("televoting.txt");
    }
    
}
