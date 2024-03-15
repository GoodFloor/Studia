#include <stdio.h>
#include "binarySearchTree.hpp"

long long numberOfConnectionsAccessed;

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

void binarySearchTree::deleteTree(treeElement *x)
{
    if (x != nullptr)
    {
        deleteTree(x->getLeft());
        deleteTree(x->getRight());
        delete(x);
    }
}

void binarySearchTree::printSubtree(treeElement *x, int depth)
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

treeElement *binarySearchTree::getSuccessor(treeElement *x)
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

treeElement *binarySearchTree::searchKey(int key)
{
    treeElement* curr = root;
    while (curr != nullptr && !this->isEqual(curr->getKey(), key))
    {
        if (this->isSmaller(key, curr->getKey()))
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

void binarySearchTree::deleteElement(treeElement *x)
{
    if (x == nullptr)
    {
        return;
    }
    if (x->getLeft() == nullptr || x->getRight() == nullptr)
    {
        treeElement* onlyChild = x->getLeft() == nullptr ? x->getRight() : x->getLeft();
        if (x != root)
        {
            if (x->getParent()->getLeft() == x)
            {
                x->getParent()->setLeft(onlyChild);
            }
            else
            {
                x->getParent()->setRight(onlyChild);
            }
        }
        else
        {
            root = onlyChild;
        }
        if (onlyChild != nullptr)
        {
            onlyChild->setParent(x->getParent());
            delete(x);
        }
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

bool binarySearchTree::isEqual(int a, int b)
{
    numberOfComparisons++;
    return a == b;
}

bool binarySearchTree::isSmaller(int a, int b)
{
    numberOfComparisons++;
    return a < b;
}

binarySearchTree::binarySearchTree()
{
    this->numberOfComparisons = 0;
    numberOfConnectionsAccessed = 0;
    this->root = nullptr;
}

binarySearchTree::~binarySearchTree()
{
    deleteTree(root);
}

void binarySearchTree::insertKey(int key)
{
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
        if (this->isSmaller(key, curr->getKey()))
        {
            curr = curr->getLeft();
        }
        else
        {
            curr = curr->getRight();
        }
    }
    if (this->isSmaller(key, prev->getKey()))
    {
        prev->setLeft(new treeElement(key, prev));
    }
    else
    {
        prev->setRight(new treeElement(key, prev));
    }
}

void binarySearchTree::deleteKey(int key)
{
    return this->deleteElement(searchKey(key));
}

void binarySearchTree::printTree()
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

int binarySearchTree::getHeight()
{
    if (root == nullptr)
    {
        return 0;
    }
    return root->getHeight();
}

long long binarySearchTree::getNumberOfAccesses()
{
    return numberOfConnectionsAccessed;
}
