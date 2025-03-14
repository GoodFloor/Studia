public class GraphNode {
    private GraphNode[] connectedNodes;
    private int nodeDegree;
    public GraphNode() {
        connectedNodes = new GraphNode[0];
        nodeDegree = 0;
    }
    public void addNode(GraphNode node) {
        GraphNode[] tempArr = new GraphNode[nodeDegree + 1];
        for (int i = 0; i < nodeDegree; i++) {
            if (connectedNodes[i] == node) {
                return;
            }
            tempArr[i] = connectedNodes[i];
        }
        tempArr[nodeDegree] = node;
        connectedNodes = tempArr;
        nodeDegree++;
        node.addNode(this);
    }
    public GraphNode getNode(int pathNumber) {
        if (pathNumber >= nodeDegree) {
            return null;
        }
        return connectedNodes[pathNumber];
    }
    public int getDegree() {
        return nodeDegree;
    }
}