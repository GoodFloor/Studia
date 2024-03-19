struct queueElement
{
    int node;
    int predecessor;
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
    void pushId(int nodeNumber, int predecessorNumber);
    queueElement* pop();
    int popId();
    int getTopPredecessor();
};
