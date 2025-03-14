#include <stdlib.h>
#include <iostream>
#include "binaryTree.hpp"

binaryTree::binaryTree()
{
    root = nullptr;
}

binaryTree::~binaryTree()
{
    removeSubtree(root);
    root = nullptr;
}

void binaryTree::add(long long hash)
{
    binaryTreeElement* newElement = new binaryTreeElement;
    newElement->hash = hash;
    newElement->left = nullptr;
    newElement->right = nullptr;
    if (root == nullptr)
    {
        root = newElement;
        return;
    }
    binaryTreeElement* prev = nullptr;
    binaryTreeElement* curr = root;
    while (curr != nullptr)
    {
        prev = curr;
        if (curr->hash > hash)
        {
            curr = curr->left;
        }
        else
        {
            curr = curr->right;
        }
    }
    if (prev->hash > hash)
    {
        prev->left = newElement;
    }
    else
    {
        prev->right = newElement;
    }
}

bool binaryTree::isInTree(long long hash)
{
    if (root == nullptr)
    {
        return false;
    }
    binaryTreeElement* curr = root;
    while (curr != nullptr)
    {
        if (curr->hash == hash)
        {
            return true;
        }
        if (curr->hash > hash)
        {
            curr = curr->left;
        }
        else
        {
            curr = curr->right;
        }
    }
    return false;
}

void binaryTree::removeSubtree(binaryTreeElement *element)
{
    if (element == nullptr)
    {
        return;
    }
    removeSubtree(element->left);
    removeSubtree(element->right);
    delete(element);
}
