#include <cstdlib>
#include "queue.hpp"

queue::queue()
{
    head = nullptr;
    rear = nullptr;
}

queue::~queue()
{
}

void queue::push(queueElement* newElement)
{
    newElement->next = nullptr;

    if (head == nullptr)
    {
        head = newElement;
        rear = newElement;
        return;
    }
    rear->next = newElement;
    rear = newElement;
}

void queue::pushId(int nodeNumber, int predecessorNumber)
{
    queueElement* newElement = (queueElement*)malloc(sizeof(queueElement));
    newElement->node = nodeNumber;
    newElement->predecessor = predecessorNumber;
    push(newElement);
}

queueElement* queue::pop() 
{
    if (head == nullptr)
    {
        throw 404;
    }
    queueElement* popped;
    popped = head;
    head = popped->next;
    return popped;
}

int queue::popId()
{
    try
    {
        queueElement* popped = pop();
        return popped->node;
    }
    catch(int err)
    {
        throw err;
    }
}

int queue::getTopPredecessor()
{
    if (head == nullptr)
    {
        throw 404;
    }
    return head->predecessor;
}
