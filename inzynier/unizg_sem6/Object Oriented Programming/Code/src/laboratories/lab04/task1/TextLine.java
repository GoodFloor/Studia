package laboratories.lab04.task1;

public class TextLine {

    private int lineNumber;
    private String text;
  
    public TextLine(int lineNumber, String text) {
      this.lineNumber = lineNumber;
      this.text = text;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "[" + lineNumber + "] " + text;
    }
}
