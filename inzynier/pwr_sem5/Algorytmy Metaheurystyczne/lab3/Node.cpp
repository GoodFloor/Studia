#include <math.h>
#include <iostream>
#include "Node.hpp"

int nodeDistance(Node n1, Node n2)
{
    return round(sqrt(pow(n1.x - n2.x, 2) + pow(n1.y - n2.y, 2)));
}

Node *nodeArray(int size)
{
    return (Node*)malloc(sizeof(Node) * size);
}
