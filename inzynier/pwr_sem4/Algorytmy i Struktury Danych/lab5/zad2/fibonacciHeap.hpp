#include "heap.hpp"

class FibonacciNode
{
public:
    FibonacciNode* parent;
    FibonacciNode* child;
    FibonacciNode* left;
    FibonacciNode* right;
    int key;
    int degree;
    bool mark;
    FibonacciNode(int key);
    ~FibonacciNode();
};

class FibonacciHeap : public Heap
{
private:
    FibonacciNode* min;
    int size;
    int getMin();
    void heapUnion(FibonacciHeap* h);
    void consolidate();
    void heapLink(FibonacciNode* y, FibonacciNode* x);
    void printNode(FibonacciNode* x, int depth, FibonacciNode* start);
public:
    FibonacciHeap();
    ~FibonacciHeap();
    void heapInsert(int key);
    void heapUnion(Heap* h);
    int extractMin();
    void print();
    bool isEmpty();
};
