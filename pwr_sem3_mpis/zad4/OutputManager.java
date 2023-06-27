import java.io.BufferedWriter;
import java.io.FileWriter;

public class OutputManager {
    private BufferedWriter output;
    private int threadsCount;
    private int finishedThreads;
    public OutputManager(String fileName) throws Exception{
        try {
            output = new BufferedWriter(new FileWriter(fileName));
            threadsCount = 0;
            finishedThreads = 0;
        } catch (Exception e) {
            throw e;
        }
    }
    public synchronized void println(String message) throws Exception{
        try {
            output.append(message);  
        } catch (Exception e) {
            throw e;
        }
    }
    public void close() throws Exception{
        try {
            output.close();
        } catch (Exception e) {
            throw e;
        } 
    }
    public synchronized void newThread() {
        threadsCount++;
    }
    public synchronized void endThread() {
        finishedThreads++;
        System.out.println((finishedThreads / 39) + " %");
    }
    public int activeThreads() {
        return threadsCount - finishedThreads;
    }
    public boolean allThreadsDown() {
        if (threadsCount == finishedThreads) {
            return true;
        } else {
            return false;
        }
    }
}
