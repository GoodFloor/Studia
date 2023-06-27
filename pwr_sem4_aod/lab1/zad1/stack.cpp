#include <cstdlib>
#include "stack.hpp"

stack::stack()
{
    head = nullptr;
}

stack::~stack()
{
}

void stack::push(stackElement* newElement)
{
    newElement->next = head;
    head = newElement;
}

void stack::pushId(int nodeNumber, int predecessorNumber)
{
    stackElement* newElement = (stackElement*)malloc(sizeof(stackElement));
    newElement->node = nodeNumber;
    newElement->predecessor = predecessorNumber;
    push(newElement);
}

stackElement* stack::pop()
{
    if (head == nullptr)
    {
        throw 404;
    }
    stackElement* popped;
    popped = head;
    head = popped->next;
    return popped;
}

int stack::popId()
{
    try
    {
        stackElement* popped = pop();
        return popped->node;
    }
    catch(int err)
    {
        throw err;
    }
}

int stack::getTopPredecessor()
{
    if (head == nullptr)
    {
        throw 404;
    }
    return head->predecessor;
}
