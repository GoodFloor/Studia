public class Clique implements GraphFactory{

    @Override
    public GraphNode generateGraph(int numberOfNodes) {
        GraphNode[] graph = new GraphNode[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            graph[i] = new GraphNode();
        }
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = i + 1; j < numberOfNodes; j++) {
                graph[i].addNode(graph[j]);
            }
        }
        return graph[0];
    }
    
}
