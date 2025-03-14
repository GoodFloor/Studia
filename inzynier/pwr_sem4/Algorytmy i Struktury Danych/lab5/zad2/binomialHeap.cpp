#include <iostream>
#include <queue>
#include "binomialHeap.hpp"

// Węzeł

BinomialNode::BinomialNode(int key)
{
    this->parent = nullptr;
    this->child = nullptr;
    this->sibling = nullptr;
    this->degree = 0;
    this->key = key;
}

BinomialNode::~BinomialNode()
{
}

// Sterta

BinomialNode* BinomialHeap::getMin()
{
    BinomialNode* y = nullptr;
    BinomialNode* x = this->head;
    int min = __INT32_MAX__; 
    while (x != nullptr)
    {
        if (this->lessThan(x->key, min))
        {
            min = x->key;
            y = x;
        }
        x = x->sibling;
    }    
    return y;
}

void BinomialHeap::binomialLink(BinomialNode *y, BinomialNode *z)
{
    y->parent = z;
    y->sibling = z->child;
    z->child = y;
    z->degree = z->degree + 1;
}

void BinomialHeap::binomialMerge(BinomialHeap *h)
{
    BinomialNode* curr1 = this->head;
    BinomialNode* curr2 = h->head;
    if (curr2 == nullptr)
    {
        return;
    }
    if (curr1 == nullptr)
    {
        this->head = curr2;
        h->head = nullptr;
        return;
    }
    BinomialNode* prev = nullptr;
    if (curr1->degree > curr2->degree)
    {
        this->head = curr2;
        prev = curr2;
        curr2 = curr2->sibling;
    }
    else
    {
        this->head = curr1;
        prev = curr1;
        curr1 = curr1->sibling;
    }
    while (curr1 != nullptr || curr2 != nullptr)
    {
        if (curr1 == nullptr || (curr2 != nullptr && curr1->degree > curr2->degree))
        {
            prev->sibling = curr2;
            curr2 = curr2->sibling;
        }
        else
        {
            prev->sibling = curr1;
            curr1 = curr1->sibling;
        }
        prev = prev->sibling;
    }
    h->head = nullptr;
}

BinomialHeap::BinomialHeap() : Heap()
{
    this->head = nullptr;
    this->numberOfComparisons = 0;
}

BinomialHeap::~BinomialHeap()
{

}

void BinomialHeap::heapInsert(int key)
{
    // std::cout << "Insert(" << key << ")" << std::endl;
    BinomialNode* x = new BinomialNode(key);
    BinomialHeap* h = new BinomialHeap();
    h->head = x;
    this->heapUnion(h);
}

void BinomialHeap::heapUnion(Heap* h)
{
    heapUnion(dynamic_cast<BinomialHeap*>(h));
}

void BinomialHeap::heapUnion(BinomialHeap *h)
{
    // std::cout << "heapUnion" << std::endl << "h1:" << std::endl;
    // this->print();
    // std::cout << "h2:" << std::endl;
    // h->print();
    this->binomialMerge(h);
    // std::cout << "afterMerge:" << std::endl;
    // this->print();
    if (this->head == nullptr)
    {
        return;
    }
    BinomialNode* prev = nullptr;
    BinomialNode* x = this->head;
    BinomialNode* next = x->sibling;
    while (next != nullptr)
    {
        if (x->degree != next->degree || (next->sibling != nullptr && next->sibling->degree == x->degree))
        {
            // std::cout << "Case 1" << std::endl;
            prev = x;
            x = next;
        }
        else 
        {
            if (this->lessThan(x->key, next->key) || this->isEqual(x->key, next->key))
            {
                // std::cout << "Case 2" << std::endl;
                x->sibling = next->sibling;
                this->binomialLink(next, x);
            }
            else
            {
                // std::cout << "Case 3" << std::endl;
                if (prev == nullptr)
                {
                    this->head = next;
                }
                else
                {
                    prev->sibling = next;
                }
                this->binomialLink(x, next);
                x = next;
            }
        }
        next = x->sibling;
    }
}

void BinomialHeap::printNode(BinomialNode *node, int depth)
{
    if (node == nullptr)
    {
        return;
    }
    for (int i = 0; i < depth; i++)
    {
        std::cout << "-";
    }
    std::cout << node->key << "(" << node->degree << ")" << std::endl;
    this->printNode(node->child, depth + 1);
    this->printNode(node->sibling, depth);
}

bool BinomialHeap::lessThan(int a, int b)
{
    numberOfComparisons++;
    return a < b;
}

bool BinomialHeap::isEqual(int a, int b)
{
    numberOfComparisons++;
    return a == b;
}

int BinomialHeap::extractMin()
{
    if (this->head == nullptr)
    {
        throw std::runtime_error("Heap is empty");
    }
    BinomialNode* minPrev = nullptr;
    BinomialNode* min = this->head;
    BinomialNode* prev = this->head;
    BinomialNode* curr = this->head->sibling;
    while (curr != nullptr)
    {
        if (this->lessThan(curr->key, min->key))
        {
            minPrev = prev;
            min = curr;
        }
        prev = curr;
        curr = curr->sibling;
    }
    if (minPrev == nullptr)
    {
        this->head = min->sibling;
    }
    else
    {
        minPrev->sibling = min->sibling;
    }
    int minValue = min->key;
    prev = min->child;
    if (prev == nullptr)
    {
        return minValue;
    }
    curr = prev->sibling;
    BinomialHeap* h = new BinomialHeap();
    if (curr == nullptr)
    {
        h->head = prev;
        this->heapUnion(h);
        return minValue;
    }
    BinomialNode* next = curr->sibling;
    prev->sibling = nullptr;
    while (curr != nullptr)
    {
        curr->sibling = prev;
        prev = curr;
        curr = next;
        if (next != nullptr)
        {
            next = next->sibling;
        }
    }
    h->head = prev;
    this->heapUnion(h);
    return minValue;
}

void BinomialHeap::print()
{
    // std::queue<BinomialNode*> toBePrinted;
    // toBePrinted.push(this->head);
    // while (!toBePrinted.empty())
    // {
    //     BinomialNode* curr = toBePrinted.front();
    //     toBePrinted.pop();
    //     do
    //     {
    //         if (curr == nullptr)
    //         {
    //             std::cout << "\t";
    //         }
    //         else
    //         {
    //             std::cout << curr->key << "\t";
    //             for (int i = 0; i < curr->degree; i++)
    //             {
    //                 std::cout << "\t";
    //             }
    //         }
    //         curr = curr->sibling;
    //     } while (curr != nullptr);
    // }
    this->printNode(this->head, 0);
}

bool BinomialHeap::isEmpty()
{
    return this->head == nullptr;
}
