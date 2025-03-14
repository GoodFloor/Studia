struct Node
{
    int x;
    int y;
};

class MinimumSpanningTree
{
private:
    int numberOfNodes;
    void nodeDFS(int node, int parent, int* resultArray, int* resultID);
public:
    int** adjacencyMatrix;
    MinimumSpanningTree(int numberOfVertices, int** adjacencyMatrix);
    ~MinimumSpanningTree();
    int getTreeWeight();
    int* getDFS();
};

class CompleteGraph
{
private:
    int numberOfNodes;
    int currentNumberOfNodes;
    Node** nodeArray;
    int** adjacencyMatrix;
public:
    CompleteGraph(int numberOfVertices);
    ~CompleteGraph();
    void addVertex(int posX, int posY);
    void addAllEdges();
    int getEdgeWeight(int a, int b);
    int getNodeX(int n);
    int getNodeY(int n);
    MinimumSpanningTree getMST();
};
