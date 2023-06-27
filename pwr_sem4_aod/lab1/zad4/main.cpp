#include <iostream>
#include <fstream>
#include <time.h>
#include <sys/time.h>
#include "graphReader.hpp"
#include "stack.hpp"
#include "queue.hpp"

using namespace std;

int main(int argc, char const *argv[])
{
    if (argc != 2)
    {
        cout << "Poprawna składnia: ./zad4 <nazwa pliku do analizy>" << endl;
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
    isDirected = true;
    int numberOfNodes;
    int numberOfArcs;
    input >> numberOfNodes;
    input >> numberOfArcs;
    int* firstArcId = (int*)malloc(sizeof(int) * numberOfNodes);
    int* bipartialSide = (int*)malloc(sizeof(int) * numberOfNodes);
    bool* alreadyVisited = (bool*)malloc(sizeof(bool) * numberOfNodes);
    stack toBeInvestigated;
    for (int i = 0; i < numberOfNodes; i++)
    {
        firstArcId[i] = 0;
        bipartialSide[i] = -1;
        alreadyVisited[i] = false;
        toBeInvestigated.pushId(numberOfNodes - i);
    }
    if (!isDirected)
    {
        numberOfArcs = 2 * numberOfArcs;
    }
    arc* arcs = (arc*)malloc(sizeof(arc) * numberOfArcs);
    for (int i = 0; i < numberOfArcs; i++)
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
    
    bool isStackEmpty = false;
    bool isBipartial = true;
    int counter = 0;
    while (!isStackEmpty && isBipartial)
    {
        try
        {
            int currentNode = toBeInvestigated.popId();
            if (alreadyVisited[currentNode - 1])
            {
                continue;
            }
            alreadyVisited[currentNode - 1] = true;
            counter ++;
            int firstArc = firstArcId[currentNode - 1];
            int lastArc = numberOfNodes - 1;
            if (currentNode != numberOfNodes)
            {
                lastArc = firstArcId[currentNode] - 1;
            }
            int currentSide = 0;
            if (bipartialSide[currentNode - 1] == -1)
            {
                bipartialSide[currentNode - 1] = currentSide;
            }
            else
            {
                currentSide = bipartialSide[currentNode - 1];
            }
            
            for (int i = firstArc; i <= lastArc; i++)
            {
                int head = arcs[i].getHead();
                if (!alreadyVisited[head - 1])
                {
                    toBeInvestigated.pushId(head);
                }
                if (bipartialSide[head - 1] == currentSide)
                {
                    isBipartial = false;
                    break;
                }
                else if (bipartialSide[head - 1] == -1)
                {
                    bipartialSide[head - 1] = (currentSide + 1) % 2;
                }
            }
        }
        catch(int err)
        {
            isStackEmpty = true;
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

    if (counter == numberOfNodes)
    {
        cout << "Graf jest dwudzielny." << endl;
        if (numberOfNodes <= 200)
        {
            int v0 = 0;
            int v1 = 0;
            cout << "V0 \t| V1" << endl;
            while (v0 != numberOfNodes || v1 != numberOfNodes)
            {
                while (v0 < numberOfNodes && bipartialSide[v0] != 0)
                {
                    v0++;
                }
                if (v0 < numberOfNodes)
                {
                    cout << v0 + 1;
                    v0++;
                }
                cout << "\t| ";
                while (v1 < numberOfNodes && bipartialSide[v1] != 1)
                {
                    v1++;
                }
                if (v1 < numberOfNodes)
                {
                    cout << v1 + 1;
                    v1++;
                }
                cout << endl;
            }
        }
    }
    else
    {
        cout << "Graf nie jest dwudzielny." << endl;
    }

    cout << "Czas wykonywania: " << minutes << " minut " << seconds << ".";
    for (int i = 0; i < leadingZeros; i++)
    {
        cout << "0";
    }
    cout << useconds << " sekund" << endl;

    return 0;
}
