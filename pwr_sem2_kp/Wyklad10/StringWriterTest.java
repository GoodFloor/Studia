import java.io.StringWriter;

// Wykorzystanie strumienia StreamWriter
public class StringWriterTest {
    public static void main(String[] args) {
	String str1 = "Hello World!";
	String str2 ="\nThis is StringWriter Program.";
    StringWriter outputWriter = new StringWriter();
	outputWriter.write(str1,0,5);
	System.out.println(outputWriter.toString());

	StringBuffer sbuf = outputWriter.getBuffer();
	sbuf.append(str2);
	System.out.println(outputWriter.toString());
    }
}