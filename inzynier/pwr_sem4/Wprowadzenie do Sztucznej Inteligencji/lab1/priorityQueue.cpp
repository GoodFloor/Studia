#include <stdlib.h>
#include <iostream>
#include "hashHandler.hpp"
#include "priorityQueue.hpp"

priorityQueue::priorityQueue()
{
    top = nullptr;
}

priorityQueue::~priorityQueue()
{
    while (top != nullptr)
    {   
        queueElement* next = top->next;
        delete(top);
        top = next;
    }
}

void priorityQueue::add(long long hash, unsigned char cost, std::string path)
{
    queueElement* newElement = new queueElement(hash, cost, path);
    if (top == nullptr)
    {
        top = newElement;
        return;
    }
    queueElement* prevEl = nullptr;
    queueElement* nextEl = top;
    while (nextEl != nullptr)
    {
        if (nextEl->getHash() == hash)
        {
            // std::cout << "Element już w kolejce, ";
            if (newElement->getWeight() < nextEl->getWeight())
            {
                // std::cout << "zamieniam." << std::endl;
                queueElement* toBeDeleted = nextEl;
                nextEl = nextEl->next;
                prevEl->next = nextEl;
                delete(toBeDeleted);
            }
            else 
            {
                // std::cout << "nie dodaję." << std::endl;
                return;
            }
        }
        if (prevEl == nullptr)
        {
            if (newElement->getWeight() < nextEl->getWeight())
            {
                newElement->next = nextEl;
                top = newElement;
                // std::cout << "Dodano na początku." << std::endl;
            }
        }
        else if (prevEl->getWeight() <= newElement->getWeight() && newElement->getWeight() < nextEl->getWeight())
        {
            prevEl->next = newElement;
            newElement->next = nextEl;
            // std::cout << "Dodano w środku." << std::endl;
        }
        prevEl = nextEl;
        nextEl = nextEl->next;
    }
    if (prevEl->getWeight() <= newElement->getWeight())
    {
        prevEl->next = newElement;
    }
    
}

queueElement* priorityQueue::popTop()
{
    if (top == nullptr)
    {
        return nullptr;
    }
    queueElement* topElement = top;
    top = top->next;
    topElement->next = nullptr;
    return topElement;
}

queueElement::queueElement(long long hash, unsigned char cost, std::string path)
{
    this->hash = hash;
    this->c = cost;
    this->h = heuristic(hash);
    this->path = path;
    this->next = nullptr;
}

unsigned char queueElement::getCost()
{
    return c;
}

unsigned char queueElement::getWeight()
{
    return c + h;
}

std::string queueElement::getPath()
{
    return path;
}

long long queueElement::getHash()
{
    return hash;
}
