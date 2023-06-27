class queueElement
{
private:
    unsigned char c;
    unsigned char h;
    std::string path;
    long long hash;
public:
    queueElement* next;
    queueElement(long long hash, unsigned char cost, std::string path);
    // ~queueElement();
    unsigned char getCost();
    unsigned char getWeight();
    std::string getPath();
    long long getHash();
};
class priorityQueue
{
private:
    queueElement* top;
public:
    priorityQueue();
    ~priorityQueue();
    void add(long long hash, unsigned char cost, std::string path);
    queueElement* popTop();
};
