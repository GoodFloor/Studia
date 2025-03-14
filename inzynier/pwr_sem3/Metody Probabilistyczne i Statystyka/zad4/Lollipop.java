public class Lollipop implements GraphFactory {

    @Override
    public GraphNode generateGraph(int numberOfNodes) {
        Clique clique = new Clique();
        PathEnd pathEnd = new PathEnd();
        GraphNode cliqueNode = clique.generateGraph(2 * numberOfNodes / 3);
        GraphNode pathNode = pathEnd.generateGraph(numberOfNodes - (2 * numberOfNodes / 3));
        GraphNode anotherCliqueNode = cliqueNode.getNode(0);
        cliqueNode.addNode(pathNode);
        return anotherCliqueNode;
    }
}
