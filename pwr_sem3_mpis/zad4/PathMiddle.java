public class PathMiddle implements GraphFactory {

    @Override
    public GraphNode generateGraph(int numberOfNodes) {
        GraphNode prevNode = new GraphNode();
        GraphNode middleNode = null;
        for (int i = 1; i < numberOfNodes; i++) {
            GraphNode nextNode = new GraphNode();
            if (i + 1 == numberOfNodes / 2) {
                middleNode = nextNode;
            }
            nextNode.addNode(prevNode);
            prevNode = nextNode;
        }
        return middleNode;
    }
    
}
