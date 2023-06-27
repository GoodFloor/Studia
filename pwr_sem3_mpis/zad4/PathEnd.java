public class PathEnd implements GraphFactory {

    @Override
    public GraphNode generateGraph(int numberOfNodes) {
        GraphNode prevNode = new GraphNode();
        for (int i = 1; i < numberOfNodes; i++) {
            GraphNode nextNode = new GraphNode();
            nextNode.addNode(prevNode);
            prevNode = nextNode;
        }
        return prevNode;
    }
    
}
