#include <iostream>
#include <fstream>
#include <time.h>
#include <sys/time.h>
#include "arcs.hpp"
#include "queue.hpp"
#include "stack.hpp"

using namespace std;

void addArc(int tail, int head, arc* tab, int currTabSize, int* firstArcIdTab, int graphSize)
{
    //cout << "Dodawanie krawędzi (" << tail << ", " << head << ") do tablicy o " << currTabSize << " elementach" << endl; 
    if (tail == graphSize)
    {
        tab[currTabSize] = arc(tail, head);
        return;
    }    
    for (int i = currTabSize - 1; i >= firstArcIdTab[tail]; i--)
    {
        tab[i + 1] = tab[i];
    }
    tab[firstArcIdTab[tail]] = arc(tail, head);
    for (int i = tail; i < graphSize; i++)
    {
        firstArcIdTab[i]++;
    }
}
void dfs(bool* alreadyVisitedTab, int investigatedNode, int* arcIdTab, int graphSize, arc* arcsTab, int numberOfArcs, stack* stack)
{
    if (alreadyVisitedTab[investigatedNode - 1])
    {
        return;
    }
    alreadyVisitedTab[investigatedNode - 1] = true;
    int firstArcId, lastArcId;
    firstArcId = arcIdTab[investigatedNode - 1];
    if (investigatedNode == graphSize)
    {
        lastArcId = numberOfArcs - 1;
    }
    else
    {
        lastArcId = arcIdTab[investigatedNode] - 1;
    }
    cout << "Visiting node " << investigatedNode << endl;
    for (int i = firstArcId; i <= lastArcId; i++)
    {
        stack->pushId(arcsTab[i].getHead(), investigatedNode);        
    }
    bool stackNotEmpty = true;
    while (stackNotEmpty)
    {
        try
        {
            dfs(alreadyVisitedTab, stack->popId(), arcIdTab, graphSize, arcsTab, numberOfArcs, stack); 
        }
        catch(int err)
        {
            stackNotEmpty = false;
        }
    }
}
void printDfs(bool* alreadyVisitedTab, int investigatedNode, int* arcIdTab, int graphSize, arc* arcsTab, int numberOfArcs, stack* stack, int depth)
{
    if (alreadyVisitedTab[investigatedNode - 1])
    {
        return;
    }
    alreadyVisitedTab[investigatedNode - 1] = true;
    int firstArcId, lastArcId;
    firstArcId = arcIdTab[investigatedNode - 1];
    if (investigatedNode == graphSize)
    {
        lastArcId = numberOfArcs - 1;
    }
    else
    {
        lastArcId = arcIdTab[investigatedNode] - 1;
    }
    for (int i = 0; i < depth; i++)
    {
        cout << "-";
    }
    cout << investigatedNode << endl;
    for (int i = firstArcId; i <= lastArcId; i++)
    {
        stack->pushId(arcsTab[i].getHead(), investigatedNode);        
    }
    bool stackNotEmpty = true;
    while (stackNotEmpty)
    {
        try
        {
            if (investigatedNode == stack->getTopPredecessor())
            {
                //cout << "Idę z " << investigatedNode << " o głębokości " << depth << endl;
                printDfs(alreadyVisitedTab, stack->popId(), arcIdTab, graphSize, arcsTab, numberOfArcs, stack, depth + 1);
            }
            else 
            {
                return;
            }
        }
        catch(int err)
        {
            stackNotEmpty = false;
        }
    }
}
void bfs(bool* alreadyVisitedTab, int investigatedNode, int* arcIdTab, int graphSize, arc* arcsTab, int numberOfArcs, queue* queue)
{
    if (alreadyVisitedTab[investigatedNode - 1])
    {
        return;
    }
    alreadyVisitedTab[investigatedNode - 1] = true;
    int firstArcId, lastArcId;
    firstArcId = arcIdTab[investigatedNode - 1];
    if (investigatedNode == graphSize)
    {
        lastArcId = numberOfArcs - 1;
    }
    else
    {
        lastArcId = arcIdTab[investigatedNode] - 1;
    }
    cout << "Visiting node " << investigatedNode << endl;
    for (int i = firstArcId; i <= lastArcId; i++)
    {
        queue->pushId(arcsTab[i].getHead(), investigatedNode);        
    }
    bool queueNotEmpty = true;
    while (queueNotEmpty)
    {
        try
        {
            bfs(alreadyVisitedTab, queue->popId(), arcIdTab, graphSize, arcsTab, numberOfArcs, queue); 
        }
        catch(int err)
        {
            queueNotEmpty = false;
        }
    }
}
void printBfs(bool* alreadyVisitedTab, int investigatedNode, int* arcIdTab, int graphSize, arc* arcsTab, int numberOfArcs, queue* queue, int* depthTab)
{
    if (alreadyVisitedTab[investigatedNode - 1])
    {
        return;
    }
    alreadyVisitedTab[investigatedNode - 1] = true;
    int firstArcId, lastArcId;
    firstArcId = arcIdTab[investigatedNode - 1];
    if (investigatedNode == graphSize)
    {
        lastArcId = numberOfArcs - 1;
    }
    else
    {
        lastArcId = arcIdTab[investigatedNode] - 1;
    }
    for (int i = 0; i < depthTab[investigatedNode - 1]; i++)
    {
        cout << "-";
    }
    cout << investigatedNode << endl;
    for (int i = firstArcId; i <= lastArcId; i++)
    {
        int possibleDestination = arcsTab[i].getHead();
        queue->pushId(possibleDestination, investigatedNode); 
        if (depthTab[possibleDestination - 1] == -1)
        {   
            depthTab[possibleDestination - 1] = depthTab[investigatedNode - 1] + 1;
        }
               
    }
    bool queueNotEmpty = true;
    while (queueNotEmpty)
    {
        try
        {
            printBfs(alreadyVisitedTab, queue->popId(), arcIdTab, graphSize, arcsTab, numberOfArcs, queue, depthTab); 
        }
        catch(int err)
        {
            queueNotEmpty = false;
        }
    }
}


int main(int argc, char const *argv[])
{
    if (argc < 3 || argc > 4)
    {
        cout << "Prawidłowa składnia: ./zad1 <nazwa pliku do zbadania> d|b (wybór algorytmu przeszukiwania) [t (czy drukować drzewo)] << endl";
    }
    fstream input;
    input.open(argv[1]);
    if (!input.good())
    {
        cout << "Błąd otwarcia pliku" << endl;
        return 44;
    }
    timeval begin;
    timeval end;
    timeval difference;
    gettimeofday(&begin, NULL);
    char t;
    input >> t;
    bool isDirected;
    if (t == 'D')
    {
        isDirected = true;
    }
    else
    {
        isDirected = false;
    }
    int numberOfNodes;
    int numberOfArcs;
    input >> numberOfNodes;
    input >> numberOfArcs;
    int firstArcId[numberOfNodes];
    for (int i = 0; i < numberOfNodes; i++)
    {
        firstArcId[i] = 0;
    }
    int numberOfDirectedArcs;
    if (isDirected)
    {
        numberOfDirectedArcs = numberOfArcs;
    }
    else
    {
        numberOfDirectedArcs = 2 * numberOfArcs;
    }

    arc arcs[numberOfDirectedArcs];
    for (int i = 0; i < numberOfDirectedArcs; i++)
    {
        int tail, head;
        input >> tail;
        input >> head;
        addArc(tail, head, arcs, i, firstArcId, numberOfNodes);
        if (!isDirected)
        {
            i++;
            addArc(head, tail, arcs, i, firstArcId, numberOfNodes);
        }
    }

    bool isNodeVisited[numberOfNodes];
    for (int i = 0; i < numberOfNodes; i++)
    {
        isNodeVisited[i] = false;
    }
    if (argv[2][0] == 'd')
    {
        stack* myStack = (stack*)malloc(sizeof(stack));
        if (argc == 4 && argv[3][0] == 't')
        {
            printDfs(isNodeVisited, 1, firstArcId, numberOfNodes, arcs, numberOfDirectedArcs, myStack, 0);
        }
        else if (argc == 3)
        {
            dfs(isNodeVisited, 1, firstArcId, numberOfNodes, arcs, numberOfDirectedArcs, myStack);
        }
        else
        {
            cout << "Nieodpowiedni argument, wpisz \'t\' aby wypisać drzewo lub nic aby wypisać tylko kolejność odwiedzania wierzchołków" << endl;
        }
        
    }
    else if (argv[2][0] == 'b')
    {
        queue* myQueue = (queue*)malloc(sizeof(queue));
        int nodesDepth[numberOfNodes];
        nodesDepth[0] = 0;
        for (int i = 1; i < numberOfNodes; i++)
        {
            nodesDepth[i] = -1;
        }
        
        if (argc == 4 && argv[3][0] == 't')
        {
            printBfs(isNodeVisited, 1, firstArcId, numberOfNodes, arcs, numberOfDirectedArcs, myQueue, nodesDepth);
        }
        else if (argc == 3)
        {
            bfs(isNodeVisited, 1, firstArcId, numberOfNodes, arcs, numberOfDirectedArcs, myQueue);
        }
        else
        {
            cout << "Nieodpowiedni argument, wpisz \'t\' aby wypisać drzewo lub nic aby wypisać tylko kolejność odwiedzania wierzchołków" << endl;
        }
    }
    else
    {
        cout << "Wybrano nieodpowiedni algorytm. Możliwe opcje to: d (dfs) lub b (bfs)." << endl;
    }

    gettimeofday(&end, NULL);
    timersub(&end, &begin, &difference);
    long int seconds = difference.tv_sec;
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
    
    
    

            //
            //
    // for (int i = 0; i < numberOfDirectedArcs; i++)
    // {
    //     arcs[i].printArc();
    // }
    // for (int i = 0; i < numberOfNodes; i++)
    // {
    //     cout << i << " " << firstArcId[i] << endl;
    // }
    
    

    //arcs[5].printArc();
    // queue queue;
    // queue.pushId(5);
    // queue.pushId(10);
    // try
    // {
    //     cout << queue.popId() << endl << queue.popId() << endl;
    // }
    // catch(int nr)
    // {
    //     cout << "Error number " << nr << endl;
    // }
    // stack stack;
    // stack.pushId(5);
    // stack.pushId(10);
    // try
    // {
    //     cout << stack.popId() << endl << stack.popId() << endl << stack.popId() << endl;
    // }
    // catch(int nr)
    // {
    //     cout << "Error number " << nr << endl;
    // }
    
    

    
    



    // for (int i = 1; i <= 25; i++)
    // {
    //     if (i % 5 != 0)
    //     {
    //         cout << i << " " << i + 1 << endl;
    //     }
    //     if (i < 21)
    //     {
    //         cout << i << " " << i + 5 << endl;
    //     }
        
    // }
    


    input.close();
    return 0;
}
