#include <cstdlib>
#include <queue>
#include "CompleteGraph.hpp"
#include "Node.hpp"

CompleteGraph::CompleteGraph(int numberOfVertices)
{
    this->numberOfNodes = numberOfVertices;
    this->currentNumberOfNodes = 0;
    this->nodeArray = (Node*)malloc(sizeof(Node) * numberOfVertices);
    this->adjacencyMatrix = (int**)malloc(sizeof(int*) * numberOfVertices);
    for (int i = 0; i < numberOfVertices; i++)
    {
        adjacencyMatrix[i] = (int*)malloc(sizeof(int) * numberOfVertices);
        for (int j = 0; j < numberOfVertices; j++)
        {
            adjacencyMatrix[i][j] = -1;
        }
    }
}

void CompleteGraph::addVertex(int posX, int posY)
{
    if (currentNumberOfNodes >= numberOfNodes)
    {
        throw "Too many vertices";
    }
    nodeArray[currentNumberOfNodes] = Node();
    nodeArray[currentNumberOfNodes].x = posX;
    nodeArray[currentNumberOfNodes].y = posY;
    currentNumberOfNodes++;
}

void CompleteGraph::addAllEdges()
{
    if (currentNumberOfNodes < numberOfNodes)
    {
        throw "Too few vertices";
    }
    for (int i = 0; i < numberOfNodes; i++)
    {
        Node n1 = nodeArray[i];
        for (int j = i + 1; j < numberOfNodes; j++)
        {
            Node n2 = nodeArray[j];
            int distance = nodeDistance(n1, n2);
            adjacencyMatrix[i][j] = distance;
            adjacencyMatrix[j][i] = distance;
        }
    }
}

int CompleteGraph::getEdgeWeight(int a, int b)
{
    if (a >= numberOfNodes || b >= numberOfNodes)
    {
        throw "Index out of bounds";
    }
    return adjacencyMatrix[a][b];
}

struct QueueElement
{
    int start;
    int end;
    int weight;
};

bool operator<(const QueueElement& lhs, const QueueElement& rhs)
{
    return lhs.weight > rhs.weight;
}


Graph CompleteGraph::getMST()
{
    std::priority_queue<QueueElement> q;
    bool isNodeInMST[numberOfNodes];
    bool isEdgeInMST[numberOfNodes][numberOfNodes];
    for (int i = 0; i < numberOfNodes; i++)
    {
        isNodeInMST[i] = 0;
        for (int j = 0; j < numberOfNodes; j++)
        {
            isEdgeInMST[i][j] = 0;
        }
    }
    int currentNode = 0;
    isNodeInMST[currentNode] = 1;
    int nodesInMST = 1;
    for (int i = 0; i < numberOfNodes; i++)
    {
        if (i != currentNode && !isNodeInMST[i])
        {
            QueueElement edge = QueueElement();
            edge.start = currentNode;
            edge.end = i;
            edge.weight = adjacencyMatrix[currentNode][i];
            q.push(edge);
        }
    }
    while (nodesInMST < numberOfNodes)
    {
        currentNode = q.top().end;
        if (isNodeInMST[currentNode])
        {
            q.pop();
            continue;
        }
        isNodeInMST[currentNode] = 1;
        nodesInMST++;
        int predecessor = q.top().start;
        q.pop();
        isEdgeInMST[currentNode][predecessor] = 1;
        isEdgeInMST[predecessor][currentNode] = 1;
        for (int i = 0; i < numberOfNodes; i++)
        {
            if (i != currentNode && !isNodeInMST[i])
            {
                QueueElement edge = QueueElement();
                edge.start = currentNode;
                edge.end = i;
                edge.weight = adjacencyMatrix[currentNode][i];
                q.push(edge);
            }
        }
    }

    int** newAdjacencyMatrix = (int**)malloc(sizeof(int*) * numberOfNodes);
    for (int i = 0; i < numberOfNodes; i++)
    {
        newAdjacencyMatrix[i] = (int*)malloc(sizeof(int) * numberOfNodes);
        for (int j = 0; j < numberOfNodes; j++)
        {
            if (isEdgeInMST[i][j])
            {
                newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
            }
            else
            {
                newAdjacencyMatrix[i][j] = -1;
            }
        }
    }
    return Graph(numberOfNodes, nodeArray, newAdjacencyMatrix);
}

Graph::Graph()
{
    this->numberOfNodes = 0;
    this->nodeArray = nullptr;
    this->adjacencyMatrix = nullptr;
}

Graph::Graph(int numberOfNodes, Node *nodeArray, int **adjacencyMatrix)
{
    this->numberOfNodes = numberOfNodes;
    this->nodeArray = (Node*)malloc(sizeof(Node) * numberOfNodes);
    this->adjacencyMatrix = (int**)malloc(sizeof(int*) * numberOfNodes);
    for (int i = 0; i < numberOfNodes; i++)
    {
        this->nodeArray[i] = nodeArray[i];
        this->adjacencyMatrix[i] = (int*)malloc(sizeof(int) * numberOfNodes);
        for (int j = 0; j < numberOfNodes; j++)
        {
            this->adjacencyMatrix[i][j] = adjacencyMatrix[i][j];
        }
    }
}

Graph::~Graph()
{
    if (nodeArray != nullptr)
        free(nodeArray);
    if (adjacencyMatrix != nullptr)
    {
        for (int i = 0; i < numberOfNodes; i++)
        {
            free(adjacencyMatrix[i]);
        }
        free(adjacencyMatrix); 
    }
}

int Graph::getGraphWeight()
{
    int sum = 0;
    for (int i = 0; i < numberOfNodes; i++)
    {
        for (int j = i + 1; j < numberOfNodes; j++)
        {
            if (adjacencyMatrix[i][j] > -1)
            {
                sum += adjacencyMatrix[i][j];
            }
        }
    }
    return sum;
}

Node *Graph::getDFS()
{
    return getDFS(0);
}

Node *Graph::getDFS(int startingNode)
{
    bool* wasVisited = (bool*)malloc(sizeof(bool) * numberOfNodes);
    for (int i = 0; i < numberOfNodes; i++)
    {
        wasVisited[i] = false;
    }
    Node* result = (Node*)malloc(sizeof(Node) * numberOfNodes);
    int nodeID = 0;
    nodeDFS(startingNode, wasVisited, result, &nodeID);
    return result;
}

void Graph::nodeDFS(int currentNode, bool *wasVisited, Node *result, int *resultID)
{
    wasVisited[currentNode] = true;
    result[*resultID] = nodeArray[currentNode];
    (*resultID)++;
    for (int i = 0; i < numberOfNodes; i++)
    {
        if (i != currentNode && !wasVisited[i] && adjacencyMatrix[currentNode][i] > -1)
        {
            nodeDFS(i, wasVisited, result, resultID);
        } 
    }
}

