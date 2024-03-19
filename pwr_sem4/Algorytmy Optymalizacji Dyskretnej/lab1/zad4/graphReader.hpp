class arc
{
private:
    int tail;
    int head;
public:
    arc();
    arc(int from, int to);
    ~arc();
    void printArc();
    int getTail();
    int getHead();
};

void addArc(int tail, int head, arc* arcsTab, int currentNumberOfArcs, int* firstArcIdTab, int graphSize);