#include <iostream>
#include <math.h>
#include "arrays.hpp"
#include "performanceTest.hpp"

using namespace std;

bool areResultsPrinted;
performanceTest* perf;
int subarraySize = 5;


int determinedPartition(int* array, int startId, int endId, int pivot)
{
    int pivotId = startId;
    while (pivotId < endId - 1 && array[pivotId] != pivot)
    {
        pivotId++;
    }
    perf->swap(&array[pivotId], &array[(startId + endId) / 2]);
    int i = startId - 1;
    int j = endId;
    while (true)
    {
        do
        {
            i++;
        } while (perf->lessThan(array[i], pivot));
        do
        {
            j--;
        } while (perf->lessThan(pivot, array[j]));
        if (i >= j)
        {  
            while (perf->lessThan(array[i], pivot))
            {
                i++;
            }
            while (perf->greaterThan(array[i], pivot))
            {
                i--;
            }
            if (!perf->equals(array[i], pivot))
            {
                int x = startId;
                while (!perf->equals(array[x], pivot))
                {
                    x++;
                }
                if (x < i)
                {
                    perf->swap(&array[i], &array[x]);
                }
                else if (x > i)
                {
                    i++;
                    perf->swap(&array[i], &array[x]);
                } 
            }    
            return i;
        }
        perf->swap(&array[i], &array[j]);        
    }
}

void insertionSort(int* array, int startId, int endId)
{
    for (int j = startId + 1; j < endId; j++)
    {
        int key = array[j];
        int i = j - 1;
        while (i >= startId && perf->greaterThan(array[i], key))
        {
            perf->swap(&array[i + 1], &array[i]);
            i--;
        }
    }
}

int determinedSelect(int* array, int startId, int endId, int k)
{   
    if (endId - startId <= subarraySize)
    {
        insertionSort(array, startId, endId);
        return array[startId + k];
    }
    int numberOfSubarrays = ceil((endId - startId) * 1.0 / subarraySize);
    int* meanArray = newArray(numberOfSubarrays);
    int p = startId, q = startId + subarraySize;
    for (int i = 0; i < numberOfSubarrays - 1; i++)
    {
        insertionSort(array, p, q);
        meanArray[i] = array[(q + p) / 2];
        p = q;
        q += subarraySize;
    }
    insertionSort(array, p, endId);
    meanArray[numberOfSubarrays - 1] = array[(endId + p) / 2];
    int meanOfMeans = determinedSelect(meanArray, 0, numberOfSubarrays, numberOfSubarrays / 2);
    free(meanArray);
    int r = determinedPartition(array, startId, endId, meanOfMeans);
    int i = r - startId;
    if (i == k)
    {
        return array[r];
    }
    else if (i < k)
    {
        return determinedSelect(array, r + 1, endId, k - i - 1);
    }
    else
    {
        return determinedSelect(array, startId, r, k);
    }
}

void partition(int* array, int startId, int endId, int* p, int* q)
{
    // Strategia Count
    int leftTailCount = 0;
    int rightTailCount = 0;
    if (perf->lessThan(array[endId - 1], array[startId]))
    {
        perf->swap(&array[startId], &array[endId - 1]);
    }
    int leftPivot = array[startId];
    int rightPivot = array[endId - 1];
    int i = startId + 1;
    int a = startId + 1;
    int b = endId - 2;
    while (i <= b)
    {
        if (leftTailCount > rightTailCount)
        {
            if (perf->lessThan(array[i], leftPivot))
            {
                perf->swap(&array[i], &array[a]);                
                a++;
                leftTailCount++;
            }
            else if (perf->lessThan(rightPivot, array[i]))
            {
                while (perf->lessThan(rightPivot, array[b]) && i < b)
                {
                    b--;
                }
                perf->swap(&array[i], &array[b]);
                b--;
                rightTailCount++;
                if (perf->lessThan(array[i], leftPivot))
                {
                    perf->swap(&array[i], &array[a]); 
                    a++;
                    leftTailCount++;
                }
            }
            i++;
        }
        else
        {  
            if (perf->lessThan(rightPivot, array[i]))
            {
                while (perf->lessThan(rightPivot, array[b]) && i < b)
                {
                    b--;
                }
                perf->swap(&array[i], &array[b]);
                b--;
                rightTailCount++;
                if (perf->lessThan(array[i], leftPivot))
                {
                    perf->swap(&array[i], &array[a]);
                    a++;
                    leftTailCount++;
                }
            }
            else if (perf->lessThan(array[i], leftPivot))
            {
                perf->swap(&array[i], &array[a]);                
                a++;
                leftTailCount++;
            }
            i++;
        }    
    }
    a--;
    b++;
    perf->swap(&array[a], &array[startId]);
    perf->swap(&array[b], &array[endId - 1]);
    *p = a;
    *q = b;
    return; 
}
// Rekurencyjna funkcja sortująca tablicę od startId (włącznie) do endId (bez endId)
void quickSort(int* array, int startId, int endId)
{
    if (areResultsPrinted)
    {
        cout << "quickSort dla tablicy:\n\t";
        printArray(array, startId, endId);
        cout << endl;
    }
    if (endId - startId <= 1)
    {
        return;
    }
    int pivotLeftId = (endId - startId) / 3;
    int pivotRightId = 2 * (endId - startId) / 3;
    
    int pivotLeft = determinedSelect(array, startId, endId, pivotLeftId);
    int pivotRight = determinedSelect(array, startId, endId, pivotRightId);
    pivotLeftId += startId;
    pivotRightId += startId;
    if (!perf->equals(array[pivotLeftId], pivotLeft))
    {
        pivotLeftId = startId;
        while (!perf->equals(array[pivotLeftId], pivotLeft))
        {
            pivotLeftId++;
        }
    }
    if (!perf->equals(array[pivotRightId], pivotRight))
    {
        pivotRightId = startId;
        while (!perf->equals(array[pivotRightId], pivotRight))
        {
            pivotRightId++;
        }
    }
    perf->swap(&array[pivotLeftId], &array[startId]);
    perf->swap(&array[pivotRightId], &array[endId - 1]);
    partition(array, startId, endId, &pivotLeftId, &pivotRightId);
    if (areResultsPrinted)
    {
        cout << "Piwot lewy: " << array[pivotLeftId] << endl << "Piwot prawy: " << array[pivotRightId] << endl;
    }    
    quickSort(array, startId, pivotLeftId);
    quickSort(array, pivotLeftId + 1, pivotRightId);
    quickSort(array, pivotRightId + 1, endId);
}

int main(int argc, char const *argv[])
{   
    int n;
    cin >> n;
    int* inputArray = newArray(n);
    for (int i = 0; i < n; i++)
    {
        cin >> inputArray[i];
    }
    if (n <= 50)
    {
        areResultsPrinted = true;
    }
    else
    {
        areResultsPrinted = false;
    }
    perf = new performanceTest();
    bool isSerial = false;
    if (argc > 1)
    {
        isSerial = true;
        areResultsPrinted = false;
    }
    if (areResultsPrinted)
    {
        cout << "Tablica na wejściu:\n\t";
        printArray(inputArray, n);
        cout << endl;
    }
    perf->startTimer();
    quickSort(inputArray, 0, n);
    long int duration = perf->stopTimer();
    if (areResultsPrinted)
    {
        cout << "Tablica na wyjściu:\n\t";
        printArray(inputArray, n);
        cout << endl;
    }
    if (isSerial)
    {
        cout << n << "\t" << perf->getNumberOfComparisons() << "\t" << duration << endl;
    }
    else
    {
        cout << "Liczba porównań: " << perf->getNumberOfComparisons() << endl << "Czas trwania: " << duration << endl;
    }
    return 0;
}
