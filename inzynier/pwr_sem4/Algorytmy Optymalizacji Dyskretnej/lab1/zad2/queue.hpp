struct queueElement
{
    int node;
    queueElement* next;
};

class queue
{
private:
    queueElement* head;
    queueElement* rear;
public:
    queue();
    ~queue();
    void push(queueElement* newElement);
    void pushId(int nodeNumber);
    queueElement* pop();
    int popId();
};
