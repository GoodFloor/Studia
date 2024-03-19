struct binaryTreeElement
{
    long long hash;
    binaryTreeElement* left;
    binaryTreeElement* right;
};

class binaryTree
{
private:
    binaryTreeElement* root;
    void removeSubtree(binaryTreeElement* element);
public:
    binaryTree();
    ~binaryTree();
    void add(long long hash);
    bool isInTree(long long hash);
};
