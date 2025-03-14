package lectures.l10;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public class MyDSFilter implements DirectoryStream.Filter<Path> {

    @Override
    public boolean accept(Path entry) throws IOException {
        if (entry.toString().endsWith(".txt")) {
            return true;
        } else {
            return false;
        }
    }
    
}
