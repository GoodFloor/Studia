#include "arcs.hpp"
#include "graphReader.hpp"

void addArc(int tail, int head, arc* arcsTab, int currentNumberOfArcs, int* firstArcIdTab, int graphSize)
{
    if (tail == graphSize)
    {
        arcsTab[currentNumberOfArcs] = arc(tail, head);
        return;
    }    
    for (int i = currentNumberOfArcs - 1; i >= firstArcIdTab[tail]; i--)
    {
        arcsTab[i + 1] = arcsTab[i];
    }
    arcsTab[firstArcIdTab[tail]] = arc(tail, head);
    for (int i = tail; i < graphSize; i++)
    {
        firstArcIdTab[i]++;
    }
}