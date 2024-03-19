#ifndef CustomNode
#define CustomNode

struct Node
{
    int id;
    int x;
    int y;
    int costToReach;
    friend bool operator == (Node const& l, Node const& r) noexcept
    {
        return l.id == r.id;
    }
    friend bool operator != (Node const& l, Node const& r) noexcept
    {
        return !(l == r);
    }
};

int nodeDistance(Node n1, Node n2);
Node* nodeArray(int size);

#endif
