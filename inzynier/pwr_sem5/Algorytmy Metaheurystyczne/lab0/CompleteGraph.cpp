#include <cmath>
#include <cstdlib>
#include <queue>
#include "CompleteGraph.hpp"

void MinimumSpanningTree::nodeDFS(int node, int parent, int *resultArray, int *resultID)
{
    resultArray[*resultID] = node;
    (*resultID)++;
    for (int i = 0; i < numberOfNodes; i++)
    {
        if (i != node && i != parent && adjacencyMatrix[node][i] > -1)
        {
            nodeDFS(i, node, resultArray, resultID);
        } 
    }
}

MinimumSpanningTree::MinimumSpanningTree(int numberOfVertices, int **adjacencyMatrix)
{
    this->numberOfNodes = numberOfVertices;
    this->adjacencyMatrix = adjacencyMatrix;
}

MinimumSpanningTree::~MinimumSpanningTree()
{
    for (int i = 0; i < numberOfNodes; i++)
    {
        free(adjacencyMatrix[i]);
    }
    free(adjacencyMatrix);
}

int MinimumSpanningTree::getTreeWeight()
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

int *MinimumSpanningTree::getDFS()
{
    int* dfsResult = (int*)malloc(sizeof(int) * numberOfNodes);
    int currentNode = 0;
    nodeDFS(0, -1, dfsResult, &currentNode);
    return dfsResult;
}

CompleteGraph::CompleteGraph(int numberOfVertices)
{
    this->numberOfNodes = numberOfVertices;
    this->currentNumberOfNodes = 0;
    this->nodeArray = (Node**)malloc(sizeof(Node*) * numberOfVertices);
    this->adjacencyMatrix = (int**)malloc(sizeof(int*) * numberOfVertices);
    for (int i = 0; i < numberOfVertices; i++)
    {
        nodeArray[i] = nullptr;
        adjacencyMatrix[i] = (int*)malloc(sizeof(int) * numberOfVertices);
        for (int j = 0; j < numberOfVertices; j++)
        {
            adjacencyMatrix[i][j] = -1;
        }
    }
}

CompleteGraph::~CompleteGraph()
{
    for (int i = 0; i < numberOfNodes; i++)
    {
        if (nodeArray[i] != nullptr)
        {
            delete(nodeArray[i]);
        }
        free(adjacencyMatrix[i]);
    }
    free(nodeArray);
    free(adjacencyMatrix);
}

void CompleteGraph::addVertex(int posX, int posY)
{
    if (currentNumberOfNodes >= numberOfNodes)
    {
        throw "Too many vertices";
    }
    nodeArray[currentNumberOfNodes] = new Node();
    nodeArray[currentNumberOfNodes]->x = posX;
    nodeArray[currentNumberOfNodes]->y = posY;
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
        Node* n1 = nodeArray[i];
        for (int j = i + 1; j < numberOfNodes; j++)
        {
            Node* n2 = nodeArray[j];
            int distance = round(sqrt(pow(n1->x - n2->x, 2) + pow(n1->y - n2->y, 2)));
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

int CompleteGraph::getNodeX(int n)
{
    return nodeArray[n]->x;
}

int CompleteGraph::getNodeY(int n)
{
    return nodeArray[n]->y;
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


MinimumSpanningTree CompleteGraph::getMST()
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
    return MinimumSpanningTree(numberOfNodes, newAdjacencyMatrix);
}
