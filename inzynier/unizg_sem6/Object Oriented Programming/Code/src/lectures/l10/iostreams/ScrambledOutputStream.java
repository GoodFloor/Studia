package lectures.l10.iostreams;

import java.io.IOException;
import java.io.OutputStream;

public class ScrambledOutputStream extends OutputStream {

    OutputStream stream;

    public ScrambledOutputStream(OutputStream stream) {
        this.stream = stream;
    }

    @Override
    public void write(int b) throws IOException {
        int data = b ^ 3;
        stream.write(data);
    }
    
}
