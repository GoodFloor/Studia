public class ThreadsController {
    private OutputManager outputManager;
    public ThreadsController(String graphType) throws Exception {
        try {
            outputManager = new OutputManager(graphType + ".txt");
            outputManager.println("n;cover time\n");
            System.out.println("0 %");
        } catch (Exception e) {
            throw e;
        }
    }
    public void newThread(int n, GraphNode startingNode) throws Exception {
        while (outputManager.activeThreads() > 64) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                throw e;
            }
        }
        TestUnit unit = new TestUnit(n, startingNode, outputManager);
        Thread thread = new Thread(unit);
        thread.start();
    }
    public void waitForAll() throws Exception {
        while (!outputManager.allThreadsDown()) {
            try { 
                Thread.sleep(10000);
            } catch (Exception e) {
                throw e;
            }
        }
    }
    public void close() throws Exception {
        outputManager.close();
    }
}
