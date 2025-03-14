struct stackElement
{
    int node;
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
    void pushId(int nodeNumber);
    stackElement* pop();
    int popId();
};
