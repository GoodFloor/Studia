package lectures.l10;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.Set;

public class MyAdvancedDSFilter implements DirectoryStream.Filter<Path> {

    private Set<String> extensions;

    public MyAdvancedDSFilter(String... acceptedExtensions) {
        this.extensions = Set.of(acceptedExtensions);
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        for (String extension : extensions) {
            if (entry.toString().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
    
}
