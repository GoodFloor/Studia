#include <iostream>
#include "arrays.hpp"
#include "performanceTest.hpp"

using namespace std;

bool areResultsPrinted;
performanceTest* perf;

// Dzieli fragment tablicy na dwie części: mniejszą oraz większą lub równą od piwota oraz zwraca id piwota 
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
    if (endId - startId <= 1)
    {
        return;
    }    
    int pivotLeft = 0;
    int pivotRight = 0;
    partition(array, startId, endId, &pivotLeft, &pivotRight);    
    quickSort(array, startId, pivotLeft);
    quickSort(array, pivotLeft + 1, pivotRight);
    quickSort(array, pivotRight + 1, endId);
}


int main(int argc, char const *argv[])
{
    perf = new performanceTest();
    int n;
    cin >> n;
    int* inputArray = newArray(n); 
    for (int i = 0; i < n; i++)
    {
        cin >> inputArray[i];
    }
    if (n == 0)
    {
        cout << "Tablica otrzymana na wejściu jest rozmiaru 0!" << endl;
        return 44;
    }

    // Sortowanie właściwe
    perf->startTimer();
    quickSort(inputArray, 0, n);
    long int duration = perf->stopTimer();
    cout << n << "\t" << perf->getNumberOfComparisons() << "\t" << duration << endl;
    return 0;
}
