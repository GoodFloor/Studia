#ifndef ABSTRACT_HEAP
#define ABSTRACT_HEAP
class Heap
{
public:
    unsigned int numberOfComparisons;
    virtual void heapInsert(int key) = 0;
    virtual void heapUnion(Heap* h) = 0;
    virtual int extractMin() = 0;
    virtual void print() = 0;
    virtual bool isEmpty() = 0;
};
#endif
