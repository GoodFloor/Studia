class treeElement
{
private:
    int key;
    treeElement* left;
    treeElement* right;
    treeElement* parent;
public:
    treeElement(int key, treeElement* parent);
    ~treeElement();
    void setLeft(treeElement* newLeft);
    void setRight(treeElement* newRight);
    void setParent(treeElement* newParent);
    treeElement* getLeft();
    treeElement* getRight();
    treeElement* getParent();
    treeElement* getUncle();
    treeElement* getSibling();
    treeElement* getGrandparent();
    bool isLeftChild();
    bool isRightChild();
    int getKey();
    int getHeight();
    void changeKey(int newKey);
};

class splayTree
{
private:
    treeElement* root;
    void deleteTree(treeElement* x);
    void printSubtree(treeElement* x, int depth);
    treeElement* getSuccessor(treeElement* x);
    treeElement* searchKey(int key);
    treeElement* rotateLeft(treeElement* x);
    treeElement* rotateRight(treeElement* x);
    void deleteElement(treeElement* x);
    bool isKeyEqual(int a, int b);
    bool isKeyGreater(int a, int b);
    bool isKeySmaller(int a, int b);
    void splay(treeElement* x);
public:
    splayTree();
    ~splayTree();
    void insertKey(int key);
    void deleteKey(int key);
    void printTree();
    int getHeight();
    unsigned long long getNumberOfAccesses();
    unsigned long long getNumberOfComparisons();
    void resetStatistics();
};
