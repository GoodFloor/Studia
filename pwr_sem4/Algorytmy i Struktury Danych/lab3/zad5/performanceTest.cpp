#include <time.h>
#include <sys/time.h>
#include "performanceTest.hpp"

performanceTest::performanceTest()
{
    numberOfComparisons = 0;
    numberOfSwaps = 0;
}

performanceTest::~performanceTest(){}

bool performanceTest::lessThan(int a, int b)
{
    numberOfComparisons++;
    return a < b;
}

bool performanceTest::greaterThan(int a, int b)
{
    numberOfComparisons++;
    return a > b;
}

bool performanceTest::equals(int a, int b)
{
    numberOfComparisons++;
    return a == b;
}

void performanceTest::swap(int *a, int *b)
{
    if (a == b)
    {
        return;
    }
    numberOfSwaps++;
    int temp = *a;
    *a = *b;
    *b = temp;
}

int performanceTest::getNumberOfComparisons()
{
    return numberOfComparisons;
}

int performanceTest::getNumberOfSwaps()
{
    return numberOfSwaps;
}

void performanceTest::startTimer()
{
    gettimeofday(&begin, NULL);
}

long int performanceTest::stopTimer()
{
    timeval end;
    gettimeofday(&end, NULL);
    timeval difference;
    timersub(&end, &begin, &difference);
    return (long int)difference.tv_sec * 1000000 + (long int)difference.tv_usec;
}
