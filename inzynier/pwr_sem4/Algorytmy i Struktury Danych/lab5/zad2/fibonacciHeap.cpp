#include <cmath>
#include <iostream>
#include <vector>
#include "fibonacciHeap.hpp"

FibonacciNode::FibonacciNode(int key)
{
    this->degree = 0;
    this->parent = nullptr;
    this->child = nullptr;
    this->left = this;
    this->right = this;
    this->mark = false;
    this->key = key;
}

FibonacciNode::~FibonacciNode()
{
}

int FibonacciHeap::getMin()
{
    if (this->min == nullptr)
    {
        throw 0;
    }
    return this->min->key;
}

void FibonacciHeap::heapUnion(FibonacciHeap *h)
{
    if (h->isEmpty())
    {
        return;
    }
    if (this->min == nullptr)
    {
        this->min = h->min;
        h->min = nullptr;
        return;
    }
    FibonacciNode* lastH = h->min->left;
    h->min->left->right = this->min;
    h->min->left = this->min->left;
    this->min->left->right = h->min;
    this->min->left = lastH;
    if (this->min->key > h->min->key)
    {
        this->min = h->min;
    }
    this->size += h->size;
    h->min = nullptr;
}

void FibonacciHeap::consolidate()
{
    // int maxDegree = static_cast<int>(log2(this->size)) + 1;
    // std::vector<FibonacciNode*> degreeTable(maxDegree, nullptr);
    // FibonacciNode* currentNode = this->min;
    // std::vector<FibonacciNode*> nodesToVisit;
    // do 
    // {
    //     nodesToVisit.push_back(currentNode);
    //     currentNode = currentNode->right;
    // } while (currentNode != this->min);
    // for (FibonacciNode* node : nodesToVisit)
    // {
    //     int degree = node->degree;
    //     while (degreeTable[degree] != nullptr)
    //     {
    //         FibonacciNode* sameDegreeNode = degreeTable[degree];
    //         if (node->key > sameDegreeNode->key) 
    //         {
    //             FibonacciNode* temp = node;
    //             node = sameDegreeNode;
    //             sameDegreeNode = temp;
    //         }
    //         heapLink(sameDegreeNode, node);
    //         degreeTable[degree] = nullptr;
    //         degree++;
    //     }
    //     degreeTable[degree] = node;
    // }
    // this->min = nullptr;
    // for (FibonacciNode* node : degreeTable) 
    // {
    //     if (node != nullptr) 
    //     {
    //         if (this->min == nullptr) 
    //         {
    //             this->min = node;
    //         } 
    //         else 
    //         {
    //             if (node->key < this->min->key) 
    //             {
    //                 this->min = node;
    //             }
    //         }
    //     }
    // }
    // return;
    
    int temp1;  //d
    float tempLog = (log(this->size)) / (log(2));
    int temp3 = tempLog; //maxD
    temp3++;
    FibonacciNode* array[temp3 + 1];
    for (int i = 0; i <= temp3; i++)
    {
        array[i] = nullptr;
    }
    FibonacciNode* ptr1 = this->min; //curr
    FibonacciNode* ptr4 = ptr1; //next
    do
    {
        ptr4 = ptr4->right;
        temp1 = ptr1->degree;
        while (array[temp1] != nullptr)
        {
            FibonacciNode* ptr2 = array[temp1]; //currD
            if (ptr1->key > ptr2->key)
            {
                FibonacciNode* temp = ptr1;
                ptr1 = ptr2;
                ptr2 = temp;
            }
            if (ptr2 == this->min)
            {
                this->min = ptr1;
            }
            this->heapLink(ptr2, ptr1);
            if (ptr1->right == ptr1)
            {
                this->min = ptr1;
            }
            array[temp1] = nullptr;
            temp1++;
        }
        array[temp1] = ptr1;
        ptr1 = ptr1->right;
    } while (ptr1 != this->min);
    this->min = nullptr;
    for (int i = 0; i <= temp3; i++)
    {
        if (array[i] != nullptr)
        {
            array[i]->left = array[i];
            array[i]->right = array[i];
            if (this->min != nullptr)
            {
                this->min->left->right = array[i];
                array[i]->right = this->min;
                array[i]->left = this->min->left;
                this->min->left = array[i];
                if (array[i]->key < this->min->key)
                {
                    this->min = array[i];
                }
            }
            else
            {
                this->min = array[i];
            }
            if (this->min == nullptr)
            {
                this->min = array[i];
            }
            else if (array[i]->key < this->min->key)
            {
                this->min = array[i];
            }
        }
    }
    return;
    // FibonacciNode* ptr1 = this->min;
    // int maxDegree = ptr1->degree;
    // int numberOfRoots = 0;
    // do
    // {
    //     if (ptr1->degree > maxDegree)
    //     {
    //         maxDegree = ptr1->degree;
    //     }
    //     numberOfRoots++;
    //     ptr1 = ptr1->right;
    // } while (ptr1 != this->min);
    // maxDegree++;
    // FibonacciNode** array = (FibonacciNode**)malloc(sizeof(FibonacciNode*) * maxDegree);
    // for (int i = 0; i < maxDegree; i++)
    // {
    //     array[i] = nullptr;
    // }
    // maxDegree--;
    // FibonacciNode** roots = (FibonacciNode**)malloc(sizeof(FibonacciNode*) * numberOfRoots);
    // for (int i = 0; i < numberOfRoots; i++)
    // {
    //     roots[i] = ptr1;
    //     ptr1 = ptr1->right;
    // }
    // for (int i = 0; i < numberOfRoots; i++)
    // {
    //     FibonacciNode* x = roots[i];
    //     int temp1 = x->degree;
    //     // Risky
    //     while (temp1 <= maxDegree && array[temp1] != nullptr)
    //     {
    //         FibonacciNode* y = array[temp1];
    //         if (x->key > y->key)
    //         {
    //             int tempKey = x->key;
    //             x->key = y->key;
    //             y->key = tempKey;
    //         }
    //         this->heapLink(y, x);
    //         array[temp1] = nullptr;
    //         temp1 = temp1 + 1;            
    //     }
    //     // Risky 2
    //     array[temp1] = x;
    // }
    // this->min = nullptr;
    // for (int i = 0; i <= maxDegree; i++)
    // {
    //     if (array[i] != nullptr)
    //     {
    //         array[i]->parent = nullptr;
    //         if (this->min == nullptr)
    //         {
    //             array[i]->left = array[i];
    //             array[i]->right = array[i];
    //         }
    //         else
    //         { 
    //             array[i]->left = this->min->left;
    //             this->min->left->right = array[i];
    //             array[i]->right = this->min;
    //             this->min->left = array[i];
    //         }
    //         if (array[i]->key < this->min->key)
    //         {
    //             this->min = array[i];
    //         }            
    //     }
    // }
}

void FibonacciHeap::heapLink(FibonacciNode *y, FibonacciNode *x)
{
    // // Usuń node2 z kopca
    // node2->left->right = node2->right;
    // node2->right->left = node2->left;

    // // Stań node2 na prawo od node1
    // node2->left = node1;
    // node2->right = node1->right;
    // node1->right = node2;
    // node2->right->left = node2;

    // // Uaktualnij wskaźnik na rodzica
    // node2->parent = node1;

    // // Zwiększ stopień node1
    // node1->degree++;

    // // Oznacz node2 jako nieostrzeżony
    // node2->mark = false;
    y->left->right = y->right;
    y->right->left = y->left;
    if (x->right == x)
    {
        this->min = x;
    }
    y->left = y;
    y->right = y;
    y->parent = x;
    if (x->child == nullptr)
    {
        x->child = y;
    }
    y->right = x->child;
    // std::cout << "Before" << std::endl;
    // printf("x:(%d)", x->key);
    y->left = x->child->left;
    // std::cout << "After" << std::endl;
    x->child->left->right = y;
    x->child->left = y;
    if (y->key < x->child->key)
    {
        x->child = y;
    }
    x->degree++;
}

void FibonacciHeap::printNode(FibonacciNode *x, int depth, FibonacciNode *start)
{
    if (x == nullptr || x == start)
    {
        return;
    }
    if (start == x->parent)
    {
        start = x;
    }
    std::cout << "\t";
    for (int i = 0; i < depth; i++)
    {
        std::cout << "-";
    }
    std::cout << x->key << std::endl;
    printNode(x->child, depth + 1, x);
    printNode(x->right, depth, start);
}

FibonacciHeap::FibonacciHeap()
{
    this->numberOfComparisons = 0;
    this->min = nullptr;
    this->size = 0;
}

FibonacciHeap::~FibonacciHeap()
{
}

void FibonacciHeap::heapInsert(int key)
{
    FibonacciNode* x = new FibonacciNode(key);
    if (this->min != nullptr)
    {
        this->min->left->right = x;
        x->right = this->min;
        x->left = this->min->left;
        this->min->left = x;
    }
    if (this->min == nullptr || key < this->min->key)
    {
        this->min = x;
    }
    this->size++;
}

void FibonacciHeap::heapUnion(Heap *h)
{
    heapUnion(dynamic_cast<FibonacciHeap*>(h));
}

int FibonacciHeap::extractMin()
{
    if (this->min == nullptr)
    {
        throw std::runtime_error("Heap is empty");
    }
    // int minValue = this->min->key;
    // FibonacciNode* z = this->min;
    // FibonacciNode* child = z->child;
    // while (child != nullptr)
    // {
    //     FibonacciNode* nextChild = child->right;
    //     child->left->right = child->right;
    //     child->right->left = child->left;
    //     child->left = this->min;
    //     child->right = this->min->right;
    //     this->min->right = child;
    //     child->right->left = child;
    //     child->parent = nullptr;
    //     child = nextChild;
    // }
    // z->left->right = z->right;
    // z->right->left = z->left;
    // if (z == z->right)
    // {
    //     this->min = nullptr;
    // }
    // else
    // {
    //     this->min = z->right;
    //     this->consolidate();
    // }
    // this->size--;
    // return minValue;
    
    int minValue = this->min->key;
    FibonacciNode* temp = this->min;
    FibonacciNode* pntr = temp;
    FibonacciNode* x = nullptr;
    if (temp->child != nullptr)
    {
        x = temp->child;
        do
        {
            pntr = x->right;
            this->min->left->right = x;
            x->right = this->min;
            x->left = this->min->left;
            this->min->left = x;
            if (x->key < this->min->key)
            {
                this->min = x;
            }
            x->parent = nullptr;
            x = pntr;            
        } while (pntr != temp->child);
    }
    temp->left->right = temp->right;
    temp->right->left = temp->left;
    this->min = temp->right;
    if (temp == temp->right && temp->child == nullptr)
    {
        this->min = nullptr;
    }
    else
    {
        this->min = temp->right;
        this->consolidate();
    }
    this->size--;
    return minValue;
    // FibonacciNode* z = this->min;
    // int minValue = z->key;
    // FibonacciNode* child = z->child;
    // if (child != nullptr)
    // {
    //     while (child->parent != nullptr)
    //     {
    //         child->parent = nullptr;
    //         child = child->right;
    //     }
    //     FibonacciNode* leftSibling = child->left;
    //     leftSibling->right = z;
    //     child->left = z->left;
    //     z->left->right = child;
    //     z->left = leftSibling;
    //     z->child = nullptr;
    // }
    // if (z == z->right)
    // {
    //     this->min = nullptr;
    // }
    // else
    // {
    //     this->min = z->right;
    //     this->min->left = z->left;
    //     z->left->right = this->min;
    //     this->consolidate();
    // }
    // this->size--;
    // delete(z);
    // return minValue;
}

void FibonacciHeap::print()
{
    printNode(this->min, 0, nullptr);
}

bool FibonacciHeap::isEmpty()
{
    return this->min == nullptr;
}
