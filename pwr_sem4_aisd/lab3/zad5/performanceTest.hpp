#include <time.h>

class performanceTest
{
private:
    int numberOfComparisons;
    int numberOfSwaps;
    timeval begin;
public:
    performanceTest();
    ~performanceTest();
    bool lessThan(int a, int b);
    bool greaterThan(int a, int b);
    bool equals(int a, int b);
    void swap(int* a, int* b);
    int getNumberOfComparisons();
    int getNumberOfSwaps();
    void startTimer();
    long int stopTimer();
};
