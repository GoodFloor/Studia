#include <iostream>
#include <fstream>
#include <time.h>
#include <sys/time.h>
#include "graphReader.hpp"
#include "stack.hpp"

using namespace std;

void dfs(bool* alreadyVisitedTab, int investigatedNode, int* arcIdTab, int graphSize, arc* arcsTab, int numberOfArcs, stack* toBeInvestigated, stack* processingOrder)
{
    if (alreadyVisitedTab[investigatedNode - 1])
    {
        return;
    }
    alreadyVisitedTab[investigatedNode - 1] = true;
    int firstArcId, lastArcId;
    firstArcId = arcIdTab[investigatedNode - 1];
    lastArcId = numberOfArcs - 1;
    if (investigatedNode < graphSize)
    {
        lastArcId = arcIdTab[investigatedNode] - 1;
    }
    for (int i = firstArcId; i <= lastArcId; i++)
    {
        toBeInvestigated->pushId(arcsTab[i].getHead(), investigatedNode);        
    }
    bool stackNotEmpty = true;
    while (stackNotEmpty)
    {
        try
        {
            if (toBeInvestigated->getTopPredecessor() == investigatedNode)
            {
               dfs(alreadyVisitedTab, toBeInvestigated->popId(), arcIdTab, graphSize, arcsTab, numberOfArcs, toBeInvestigated, processingOrder); 
            }
            else
            {
                processingOrder->pushId(investigatedNode);
                return;
            }
        }
        catch(int err)
        {
            stackNotEmpty = false;
        }
    }
}
int printDfs(bool* alreadyVisitedTab, int investigatedNode, int* arcIdTab, int graphSize, arc* arcsTab, int numberOfArcs, stack* toBeInvestigated)
{
    if (alreadyVisitedTab[investigatedNode - 1])
    {
        return 0;
    }
    alreadyVisitedTab[investigatedNode - 1] = true;
    if (graphSize <= 200)
    {        
        cout << "\t" << investigatedNode << endl;
    }
    int firstArcId, lastArcId;
    firstArcId = arcIdTab[investigatedNode - 1];
    lastArcId = numberOfArcs - 1;
    if (investigatedNode < graphSize)
    {
        lastArcId = arcIdTab[investigatedNode] - 1;
    }
    for (int i = firstArcId; i <= lastArcId; i++)
    {
        toBeInvestigated->pushId(arcsTab[i].getHead(), investigatedNode);        
    }
    bool stackNotEmpty = true;
    int suma = 0;
    while (stackNotEmpty)
    {
        try
        {
            if (toBeInvestigated->getTopPredecessor() == investigatedNode)
            {
               suma += printDfs(alreadyVisitedTab, toBeInvestigated->popId(), arcIdTab, graphSize, arcsTab, numberOfArcs, toBeInvestigated); 
            }
            else
            {
                return suma + 1;
            }
        }
        catch(int err)
        {
            stackNotEmpty = false;
        }
    }
    return suma + 1;
}

int main(int argc, char const *argv[])
{
    if (argc != 2)
    {
        cout << "Poprawna składnia: ./zad3 <nazwa pliku do analizy>" << endl;
        return 44;
    }
    fstream input;
    input.open(argv[1]);
    if (!input.good())
    {
        cout << "Błąd otwarcia pliku!" << endl;
        return 44;
    }
    timeval begin;
    timeval end;
    timeval difference;
    gettimeofday(&begin, NULL);
    bool isDirected;
    {
        char t;
        input >> t;
        if (t == 'D')
        {
            isDirected = true;
        }
        else
        {
            isDirected = false;
        }
    }
    int numberOfNodes;
    int numberOfArcs;
    input >> numberOfNodes;
    input >> numberOfArcs;
    int* firstArcId = (int*)malloc(sizeof(int) * numberOfNodes);
    int* transposedFirstArcId = (int*)malloc(sizeof(int) * numberOfNodes);
    bool* isNodeVisited = (bool*)malloc(sizeof(bool) * numberOfNodes);
    for (int i = 0; i < numberOfNodes; i++)
    {
        firstArcId[i] = 0;
        transposedFirstArcId[i] = 0;
        isNodeVisited[i] = false;
    }
    arc* arcs = (arc*)malloc(sizeof(arc) * numberOfArcs);
    arc* transposedArcs = (arc*)malloc(sizeof(arc) * numberOfArcs);
    //cout << "Jeszcze dobry1" << endl;
    for (int i = 0; i < numberOfArcs; i++)
    {
        int tail, head;
        input >> tail;
        input >> head;
        addArc(tail, head, arcs, i, firstArcId, numberOfNodes);
        addArc(head, tail, transposedArcs, i, transposedFirstArcId, numberOfNodes);
    }
    //cout << "Jeszcze dobry2" << endl;
    stack* processOrder = (stack*)malloc(sizeof(stack));
    stack* toBeProcessed = (stack*)malloc(sizeof(stack));
    //cout << "Jeszcze dobry3" << endl;
    dfs(isNodeVisited, 1, firstArcId, numberOfNodes, arcs, numberOfArcs, toBeProcessed, processOrder);
    //cout << "Jeszcze dobry4" << endl;
    for (int i = 0; i < numberOfNodes; i++)
    {
        isNodeVisited[i] = false;
    }
    bool isStackEmpty = false;
    int numberOfComponents = 0;
    while (!isStackEmpty)
    {
        try
        {
            int topNode = processOrder->popId();
            if (!isNodeVisited[topNode - 1])
            {
                numberOfComponents++;
                cout << "Silnie spójna składowa " << numberOfComponents << ":" << endl;
                int nodes = printDfs(isNodeVisited, topNode, transposedFirstArcId, numberOfNodes, transposedArcs, numberOfArcs, toBeProcessed);
                cout << "\tLiczba wierzchołków: " << nodes << endl;
            }
        }
        catch(int err)
        {
            isStackEmpty = true;
        }
    }
    cout << "Liczba silnie spójnych składowych: " << numberOfComponents << endl;
    gettimeofday(&end, NULL);
    timersub(&end, &begin, &difference);
    int seconds = difference.tv_sec;
    int useconds = difference.tv_usec;
    int leadingZeros = 0;
    int usecondsTemp = useconds;
    while (usecondsTemp < 100000)
    {
        leadingZeros++;
        usecondsTemp *= 10;
    }
    int minutes = seconds / 60;
    seconds = seconds % 60;
    cout << "Czas wykonywania: " << minutes << " minut " << seconds << ".";
    for (int i = 0; i < leadingZeros; i++)
    {
        cout << "0";
    }
    cout << useconds << " sekund" << endl;
    return 0;
}
