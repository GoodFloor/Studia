#include <stdio.h>
#include "splayTree.hpp"

unsigned long long numberOfConnectionsAccessed;
unsigned long long numberOfComparisons;

bool isAddressEqual(treeElement* x, treeElement* y)
{
    return x == y;
}

treeElement *treeElement::getLeft()
{
    numberOfConnectionsAccessed++;
    return this->left;
}

treeElement *treeElement::getRight()
{
    numberOfConnectionsAccessed++;
    return this->right;
}

treeElement *treeElement::getParent()
{
    numberOfConnectionsAccessed++;
    return this->parent;
}

void treeElement::setLeft(treeElement *newLeft)
{
    numberOfConnectionsAccessed++;
    this->left = newLeft;
}

void treeElement::setRight(treeElement *newRight)
{
    numberOfConnectionsAccessed++;
    this->right = newRight;
}

void treeElement::setParent(treeElement *newParent)
{
    numberOfConnectionsAccessed++;
    this->parent = newParent;
}

treeElement::treeElement(int key, treeElement *parent)
{
    this->key = key;
    this->parent = parent;
    this->left = nullptr;
    this->right = nullptr;
}

treeElement::~treeElement()
{
}

int treeElement::getKey()
{
    return key;
}

int treeElement::getHeight()
{
    int heightLeft = 0;
    int heightRight = 0;
    if (this->left != nullptr)
    {
        heightLeft = this->left->getHeight();
    }
    if (this->right != nullptr)
    {
        heightRight = this->right->getHeight();
    }
    if (heightLeft > heightRight)
    {
        return heightLeft + 1;
    }
    return heightRight + 1;
}

void treeElement::changeKey(int newKey)
{
    this->key = newKey;
}

void splayTree::deleteTree(treeElement *x)
{
    if (x != nullptr)
    {
        deleteTree(x->getLeft());
        deleteTree(x->getRight());
        delete(x);
    }
}

void splayTree::printSubtree(treeElement *x, int depth)
{
    if (x != nullptr)
    {
        printSubtree(x->getRight(), depth + 1);
        for (int i = 0; i < depth; i++)
        {
            printf("-");
        }
        printf("%d\n", x->getKey());
        printSubtree(x->getLeft(), depth + 1);
    }
}

treeElement *splayTree::getSuccessor(treeElement *x)
{
    if (x->getRight() != nullptr)
    {   
        treeElement* curr = x->getRight();
        while (curr->getLeft() != nullptr)
        {
            curr = curr->getLeft();
        }
        return curr;
    }
    treeElement* prev = x;
    treeElement* curr = x->getParent();
    while (curr != nullptr && prev == curr->getRight())
    {   
        prev = curr;
        curr = curr->getParent();
    }
    return curr;
}

treeElement *splayTree::searchKey(int key)
{
    treeElement* curr = root;
    while (curr != nullptr && !this->isKeyEqual(curr->getKey(), key))
    {
        if (this->isKeySmaller(key, curr->getKey()))
        {
            curr = curr->getLeft();
        }
        else
        {
            curr = curr->getRight();
        }
    }
    return curr;
}

void splayTree::deleteElement(treeElement *x)
{
    if (x == nullptr)
    {
        return;
    }
    if (x->getLeft() == nullptr || x->getRight() == nullptr)
    {
        treeElement* onlyChild = x->getLeft() == nullptr ? x->getRight() : x->getLeft();
        treeElement* parent = x->getParent();
        if (x != root)
        {
            if (parent->getLeft() == x)
            {
                parent->setLeft(onlyChild);
            }
            else
            {
                parent->setRight(onlyChild);
            }
        }
        else
        {
            root = onlyChild;
        }
        if (onlyChild != nullptr)
        {
            onlyChild->setParent(parent);
            delete(x);
        }
        splay(parent);
    }
    else
    {
        treeElement* successor = getSuccessor(x);
        if (successor == nullptr)
        {
            return;
        }
        x->changeKey(successor->getKey());
        deleteElement(successor);        
    }
}

bool splayTree::isKeyEqual(int a, int b)
{
    numberOfComparisons++;
    return a == b;
}

bool splayTree::isKeySmaller(int a, int b)
{
    numberOfComparisons++;
    return a < b;
}

void splayTree::splay(treeElement *x)
{
    if (isAddressEqual(x, nullptr) || isAddressEqual(x, this->root))
    {
        return;
    }
    treeElement* parent = x->getParent();
    // Case 1
    if (isAddressEqual(parent, root))
    {
        if (x->isLeftChild())
        {
            this->rotateRight(parent);
        }
        else
        {
            this->rotateLeft(parent);
        }
        return;       
    }
    // Case 2, 3
    if (x->isLeftChild())
    {
        this->rotateRight(parent);
    }
    else
    {
        this->rotateLeft(parent);
    }
    parent = x->getParent();
    if (x->isLeftChild())
    {
        this->rotateRight(parent);
    }
    else
    {
        this->rotateLeft(parent);
    }
    splay(x);
}

treeElement* splayTree::rotateLeft(treeElement *x)
{
    // printf("rotateLeft(%d)", x->getKey());
    treeElement* y = x->getRight();
    if (y == nullptr)
    {
        return nullptr;
    }
    treeElement* b = y->getLeft();
    if (x == this->root)
    {
        this->root = y;
    }
    else if (x->getParent()->getLeft() == x)
    {
        x->getParent()->setLeft(y);
    }
    else
    {
        x->getParent()->setRight(y);
    }
    
    y->setParent(x->getParent());
    y->setLeft(x);
    x->setParent(y);
    x->setRight(b);
    if (b != nullptr)
    {
        b->setParent(x);
    }
    
    
    return y;
}

treeElement* splayTree::rotateRight(treeElement *x)
{
    treeElement* y = x->getLeft();
    if (isAddressEqual(y, nullptr))
    {
        return nullptr;
    }
    treeElement* b = y->getRight();
    if (isAddressEqual(x, this->root))
    {
        this->root = y;
    }
    else if (isAddressEqual(x->getParent()->getLeft(), x))
    {
        x->getParent()->setLeft(y);
    }
    else
    {
        x->getParent()->setRight(y);
    }
    y->setParent(x->getParent());
    y->setRight(x);
    x->setParent(y);
    x->setLeft(b);
    if (b != nullptr)
    {
        b->setParent(x);
    }
    return y;
}

splayTree::splayTree()
{
    numberOfComparisons = 0;
    numberOfConnectionsAccessed = 0;
    this->root = nullptr;
}

splayTree::~splayTree()
{
    deleteTree(root);
}

void splayTree::insertKey(int key)
{
    // printf("insert(%d)", key);
    if (root == nullptr)
    {
        root = new treeElement(key, nullptr);
        return;
    }
    treeElement* prev = nullptr;
    treeElement* curr = root;
    while (curr != nullptr)
    {
        prev = curr;
        if (this->isKeySmaller(key, curr->getKey()))
        {
            curr = curr->getLeft();
        }
        else
        {
            curr = curr->getRight();
        }
    }
    curr = new treeElement(key, prev);
    if (this->isKeySmaller(key, prev->getKey()))
    {
        prev->setLeft(curr);
    }
    else
    {
        prev->setRight(curr);
    }
    this->splay(curr);
}

void splayTree::deleteKey(int key)
{
    return this->deleteElement(searchKey(key));
}

void splayTree::printTree()
{
    if (root == nullptr)
    {
        printf("Drzewo jest puste");
    }
    else
    {
        printSubtree(root, 0);
    }
}

int splayTree::getHeight()
{
    if (root == nullptr)
    {
        return 0;
    }
    return root->getHeight();
}

unsigned long long splayTree::getNumberOfAccesses()
{
    return numberOfConnectionsAccessed;
}

unsigned long long splayTree::getNumberOfComparisons()
{
    return numberOfComparisons;
}

void splayTree::resetStatistics()
{
    numberOfComparisons = 0;
    numberOfConnectionsAccessed = 0;
}

treeElement *treeElement::getUncle()
{
    if (this->getParent() == nullptr)
    {
        return nullptr;
    }
    return this->getParent()->getSibling();
}

treeElement *treeElement::getSibling()
{
    if (this->getParent() == nullptr)
    {
        return nullptr;
    }
    if (this->isLeftChild())
    {
        return this->getParent()->getRight();
    }    
    return this->getParent()->getLeft();
}

treeElement *treeElement::getGrandparent()
{
    if (this->getParent() == nullptr)
    {
        return nullptr;
    }    
    return this->getParent()->getParent();
}

bool treeElement::isLeftChild()
{    
    return this->getParent() == nullptr ? false : this->getParent()->getLeft() == this;
}
