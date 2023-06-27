public class TestUnit implements Runnable{
    private int n;
    private OutputManager output;
    private GraphNode currentNode;
    private RNG random;
    public TestUnit(int n, GraphNode startingNode, OutputManager output) {
        this.n = n;
        this.currentNode = startingNode;
        this.output = output;
        this.random = new GoodRNG();
        output.newThread();
    }
    @Override
    public void run() {
        try {
            int visitedNodes = 1;
            GraphNode[] alreadyVisited = new GraphNode[n];
            alreadyVisited[0] = currentNode;
            int coverTime = 0;
            while (visitedNodes < n) {
                coverTime++;
                int path = random.getInt(1, currentNode.getDegree());
                GraphNode nextNode = currentNode.getNode(path - 1);
                if (nextNode == null) {
                    continue;
                }
                currentNode = nextNode;
                for (int i = 0; i < visitedNodes; i++) {
                    if (alreadyVisited[i] == currentNode) {
                        break;
                    }
                    else if (i == visitedNodes - 1) {
                        alreadyVisited[visitedNodes] = currentNode;
                        visitedNodes++;
                    }
                }  
            }
            output.println(n + ";" + coverTime + "\n"); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        output.endThread();
    }
}
