#include "heap.hpp"

class BinomialNode
{
public:
    BinomialNode* parent;
    BinomialNode* child;
    BinomialNode* sibling;
    int key;
    int degree;
    BinomialNode(int key);
    ~BinomialNode();
};




class BinomialHeap : public Heap
{
private:
    BinomialNode* head;
    BinomialNode* getMin();
    void binomialLink(BinomialNode* y, BinomialNode* z);
    void binomialMerge(BinomialHeap* h);
    void heapUnion(BinomialHeap* h);
    void printNode(BinomialNode* node, int depth);
    bool lessThan(int a, int b);
    bool isEqual(int a, int b);
public:
    BinomialHeap();
    ~BinomialHeap();
    void heapInsert(int key);
    void heapUnion(Heap* h);
    int extractMin();
    void print();
    bool isEmpty();
};
