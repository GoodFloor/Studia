#include <iostream>
#include <fstream>
#include <time.h>
#include <sys/time.h>
#include "arcs.hpp"
#include "graphReader.hpp"
#include "queue.hpp"

using namespace std;

int main(int argc, char const *argv[])
{
    if (argc != 2)
    {
        cout << "Poprawna składnia: ./zad2 <nazwa pliku do analizy>" << endl;
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
    int firstArcId[numberOfNodes];
    int indegree[numberOfNodes];
    //int order[numberOfNodes];
    for (int i = 0; i < numberOfNodes; i++)
    {
        firstArcId[i] = 0;
        indegree[i] = 0;
    }
    arc* arcs = (arc*)malloc(sizeof(arc) * numberOfArcs);
    //arc arcs[numberOfArcs];
    for (int i = 0; i < numberOfArcs; i++)
    {
        int tail, head;
        input >> tail;
        input >> head;
        addArc(tail, head, arcs, i, firstArcId, numberOfNodes);
        indegree[head - 1]++;
    }
    queue list;
    for (int i = 0; i < numberOfNodes; i++)
    {
        if (indegree[i] == 0)
        {
            list.pushId(i + 1);
        }        
    }
    queue orderedNodes;
    int currentOrder = 0;
    bool isListEmpty = false;
    while (!isListEmpty)
    {
        try
        {
            int currentNode = list.popId();
            currentOrder++;
            //order[currentNode - 1] = currentOrder;
            orderedNodes.pushId(currentNode);
            int firstArc = firstArcId[currentNode - 1];
            int lastArc;
            if (currentNode == numberOfNodes)
            {
                lastArc = numberOfArcs - 1;
            }
            else
            {
                lastArc = firstArcId[currentNode] - 1;
            }
            for (int i = firstArc; i <= lastArc; i++)
            {
                int destination = arcs[i].getHead();
                indegree[destination - 1]--;
                if (indegree[destination - 1] == 0)
                {
                    list.pushId(destination);
                }
            }
        }
        catch(int err)
        {
            isListEmpty = true;
        }
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
    

    if (currentOrder == numberOfNodes)
    {
        cout << "Graf nie zawiera skierowanego cyklu." << endl;
        if (numberOfNodes <= 200)
        {
            cout << "Wierzchołki w porządku topologicznym: " << endl;
            for (int i = 0; i < numberOfNodes; i++)
            {
                cout << orderedNodes.popId() << endl;
            }
        }
    }
    else
    {
        cout << "Graf zawiera skierowany cykl." << endl;
    }
    cout << "Czas wykonywania: " << minutes << " minut " << seconds << ".";
    for (int i = 0; i < leadingZeros; i++)
    {
        cout << "0";
    }
    cout << useconds << " sekund" << endl;
    return 0;
}
