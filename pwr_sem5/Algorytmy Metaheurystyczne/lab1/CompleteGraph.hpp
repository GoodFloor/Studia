#include "Node.hpp"

class Graph
{
private:
    void nodeDFS(int currentNode, bool* wasVisited, Node* result, int* resultID);
protected:
    int numberOfNodes;
    int** adjacencyMatrix;
    Node* nodeArray;
public:
    Graph();
    Graph(int numberOfNodes, Node* nodeArray, int** adjacencyMatrix);
    ~Graph();
    int getGraphWeight();
    Node* getDFS();
    Node* getDFS(int startingNode);
};

class CompleteGraph : public Graph
{
private:
    int currentNumberOfNodes;
public:
    CompleteGraph(int numberOfVertices);
    void addVertex(int posX, int posY);
    void addAllEdges();
    int getEdgeWeight(int a, int b);
    Graph getMST();
};
