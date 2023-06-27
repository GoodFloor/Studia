#include <stdio.h>
#include "redBlackTree.hpp"

unsigned long long numberOfConnectionsAccessed;
unsigned long long numberOfComparisons;

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
    this->isRed = true;
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

void redBlackTree::deleteTree(treeElement *x)
{
    if (x != nullptr)
    {
        deleteTree(x->getLeft());
        deleteTree(x->getRight());
        delete(x);
    }
}

void redBlackTree::printSubtree(treeElement *x, int depth)
{
    if (x != nullptr)
    {
        printSubtree(x->getRight(), depth + 1);
        for (int i = 0; i < depth; i++)
        {
            printf("-");
        }
        if (x->isRed)
        {
            printf("\033[31m%d\033[37m\n", x->getKey());
        }
        else
        {
            printf("%d\n", x->getKey());
        }
        printSubtree(x->getLeft(), depth + 1);
    }
}

treeElement *redBlackTree::getSuccessor(treeElement *x)
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

treeElement *redBlackTree::searchKey(int key)
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

void redBlackTree::deleteElement(treeElement *x)
{
    if (x == nullptr)
    {
        return;
    }
    if (x->getLeft() == nullptr || x->getRight() == nullptr)
    {
        treeElement* onlyChild = x->getLeft() == nullptr ? x->getRight() : x->getLeft();
        if (onlyChild != nullptr && onlyChild->isRed != x->isRed)
        {
            onlyChild->isRed = false;
        }
        else if (!(onlyChild == nullptr && x->isRed))
        {
            deleteFixUp(x, onlyChild);
        }
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

bool redBlackTree::isKeyEqual(int a, int b)
{
    numberOfComparisons++;
    return a == b;
}

bool redBlackTree::isKeySmaller(int a, int b)
{
    numberOfComparisons++;
    return a < b;
}

treeElement* redBlackTree::rotateLeft(treeElement *x)
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

treeElement* redBlackTree::rotateRight(treeElement *x)
{
    treeElement* y = x->getLeft();
    if (y == nullptr)
    {
        return nullptr;
    }
    treeElement* b = y->getRight();
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
    y->setRight(x);
    x->setParent(y);
    x->setLeft(b);
    if (b != nullptr)
    {
        b->setParent(x);
    }
    return y;
}

void redBlackTree::fixUp(treeElement *x)
{
    // printf("fixup(%d)", x->getKey());
    treeElement* parent = x->getParent();
    if (x == this->root || parent == this->root)
    {
        this->root->isRed = false;
        return;
    }
    if (!parent->isRed)
    {
        this->root->isRed = false;
        return;
    }
    
    treeElement* grandparent = parent->getParent();
    treeElement* uncle = (parent == grandparent->getLeft()) ? grandparent->getRight() : grandparent->getLeft();
    
    if (uncle != nullptr && uncle->isRed)
    {
        grandparent->isRed = true;
        parent->isRed = false;
        uncle->isRed = false;
        fixUp(grandparent);
    }
    else if (parent->getRight() == x && grandparent->getLeft() == parent)
    {
        this->rotateLeft(parent);
        fixUp(parent);
    }
    else if (parent->getLeft() == x && grandparent->getRight() == parent)
    {
        this->rotateRight(parent);
        fixUp(parent);
    }
    else if (grandparent->getLeft() == parent)
    {
        rotateRight(grandparent);
        grandparent->isRed = true;
        parent->isRed = false;
    }
    else
    {
        rotateLeft(grandparent);
        grandparent->isRed = true;
        parent->isRed = false;
    }
}

void redBlackTree::deleteFixUp(treeElement *deleted, treeElement *child)
{
    if (deleted == this->root)
    {
        if (child != nullptr)
        {
            child->isRed = false;
        }
        return;
    }
    treeElement* sibling = deleted->getSibling();
    if (sibling != nullptr && !sibling->isRed)
    {
        if (sibling->isLeftChild() && sibling->getLeft() != nullptr && sibling->getLeft()->isRed)
        {   
            sibling->getLeft()->isRed = false;
            this->rotateRight(deleted->getParent());
            return;
        }
        else if (!sibling->isLeftChild() && sibling->getRight() != nullptr && sibling->getRight()->isRed)
        {
            sibling->getRight()->isRed = false;
            this->rotateLeft(deleted->getParent());
            return;
        }
        else if (sibling->isLeftChild() && sibling->getRight() != nullptr && sibling->getRight()->isRed)
        {
            this->rotateLeft(sibling);
            this->rotateRight(deleted->getParent());
            return;
        }
        else if (!sibling->isLeftChild() && sibling->getLeft() != nullptr && sibling->getLeft()->isRed)
        {
            this->rotateRight(sibling);
            this->rotateLeft(deleted->getParent());
            return;
        }
        else if ((sibling->getLeft() == nullptr || !sibling->getLeft()->isRed) && (sibling->getRight() == nullptr || !sibling->getRight()->isRed))
        {
            sibling->isRed = true;
            // TODO: Rekursja (b)
        }
    }
    else if (sibling != nullptr)
    {
        if (sibling->isLeftChild())
        {
            sibling->getParent()->isRed = true;
            sibling->isRed = false;
            this->rotateRight(sibling->getParent());
        }
        else
        {
            sibling->getParent()->isRed = true;
            sibling->isRed = false;
            this->rotateLeft(sibling->getParent());
        }
        deleteFixUp(deleted, child);
    }
    
    
    
}

redBlackTree::redBlackTree()
{
    numberOfComparisons = 0;
    numberOfConnectionsAccessed = 0;
    this->root = nullptr;
}

redBlackTree::~redBlackTree()
{
    deleteTree(root);
}

void redBlackTree::insertKey(int key)
{
    // printf("insert(%d)", key);
    if (root == nullptr)
    {
        root = new treeElement(key, nullptr);
        root->isRed = false;
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
    this->fixUp(curr);
}

void redBlackTree::deleteKey(int key)
{
    return this->deleteElement(searchKey(key));
}

void redBlackTree::printTree()
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

int redBlackTree::getHeight()
{
    if (root == nullptr)
    {
        return 0;
    }
    return root->getHeight();
}

unsigned long long redBlackTree::getNumberOfAccesses()
{
    return numberOfConnectionsAccessed;
}

unsigned long long redBlackTree::getNumberOfComparisons()
{
    return numberOfComparisons;
}

void redBlackTree::resetStatistics()
{
    numberOfComparisons = 0;
    numberOfConnectionsAccessed = 0;
}

treeElement *treeElement::getUncle()
{
    if (this->parent == nullptr)
    {
        return nullptr;
    }
    return this->parent->getSibling();
}

treeElement *treeElement::getSibling()
{
    if (this->parent == nullptr)
    {
        return nullptr;
    }
    if (this->isLeftChild())
    {
        return this->parent->getRight();
    }    
    return this->parent->getLeft();
}

treeElement *treeElement::getGrandparent()
{
    if (this->parent == nullptr)
    {
        return nullptr;
    }    
    return this->parent->parent;
}

bool treeElement::isLeftChild()
{    
    return this->parent == nullptr ? false : this->parent->getLeft() == this;
}
