#include <string>
#include <iostream>
#include <typeinfo>

using namespace std;

template<typename Type>
class TreeElement {
public:
    Type value;
    TreeElement<Type>* leftLeaf;
    TreeElement<Type>* rightLeaf;
    TreeElement(Type element);
};

template<typename Type>
class Tree {
private:
    TreeElement<Type>* root;
public:
    Tree();
    bool isEmpty();
    bool insertElement(Type element);
    void deleteElement(Type element);
    string printNode(TreeElement<Type>* node, string prefix);
    void draw();
    bool search(Type searched);
};
