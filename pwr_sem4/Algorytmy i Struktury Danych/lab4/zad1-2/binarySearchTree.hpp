class treeElement
{
private:
    int key;
    treeElement* left;
    treeElement* right;
    treeElement* parent;
public:
    treeElement* getLeft();
    treeElement* getRight();
    treeElement* getParent();
    void setLeft(treeElement* newLeft);
    void setRight(treeElement* newRight);
    void setParent(treeElement* newParent);
    treeElement(int key, treeElement* parent);
    ~treeElement();
    int getKey();
    int getHeight();
    void changeKey(int newKey);
};

class binarySearchTree
{
private:
    treeElement* root;
    void deleteTree(treeElement* x);
    void printSubtree(treeElement* x, int depth);
    treeElement* getSuccessor(treeElement* x);
    treeElement* searchKey(int key);
    void deleteElement(treeElement* x);
    bool isEqual(int a, int b);
    bool isGreater(int a, int b);
    bool isSmaller(int a, int b);
public:
    long long numberOfComparisons;
    binarySearchTree();
    ~binarySearchTree();
    void insertKey(int key);
    void deleteKey(int key);
    void printTree();
    int getHeight();
    long long getNumberOfAccesses();
};
