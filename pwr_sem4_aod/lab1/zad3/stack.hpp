struct stackElement
{
    int node;
    int predecessor;
    stackElement* next;
};

class stack
{
private:
    stackElement* head;
public:
    stack();
    ~stack();
    void push(stackElement* newElement);
    void pushId(int nodeNumber, int predecessorNumber);
    void pushId(int nodeNumber);
    stackElement* pop();
    int popId();
    int getTopPredecessor();
};
