public class BinaryTree implements GraphFactory {

    @Override
    public GraphNode generateGraph(int numberOfNodes) {
        GraphNode root = new GraphNode();
        int currRowSize = 2;
        GraphNode[] prevRow = new GraphNode[currRowSize];
        prevRow[0] = new GraphNode();
        prevRow[1] = new GraphNode();
        prevRow[0].addNode(root);
        prevRow[1].addNode(root);
        numberOfNodes-= 3;
        while (numberOfNodes > 0) {
            currRowSize*= 2;
            GraphNode[] currRow = new GraphNode[currRowSize];
            int nodesInCurrRow = 0;
            for (int i = 0; i < prevRow.length && numberOfNodes > 0; i++) {
                while (numberOfNodes > 0 && prevRow[i].getDegree() < 3) {
                    GraphNode newNode = new GraphNode();
                    prevRow[i].addNode(newNode);
                    currRow[nodesInCurrRow] = newNode;
                    nodesInCurrRow++;
                    numberOfNodes--;
                }
            }
            prevRow = currRow;
        }
        return root;
    }
}
