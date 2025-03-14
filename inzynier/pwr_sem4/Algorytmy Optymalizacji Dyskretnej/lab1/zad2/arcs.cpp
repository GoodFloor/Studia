#include "arcs.hpp"
#include <iostream>

arc::arc()
{
    arc(-1, -1);
}

arc::arc(int from, int to)
{
    tail = from;
    head = to;
}

arc::~arc()
{
}

void arc::printArc()
{
    std::cout << "(" << tail << ";" << head << ")" << std::endl;
}

int arc::getTail()
{
    return tail;
}

int arc::getHead()
{
    return head;
}
