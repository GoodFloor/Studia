#include <iostream>
#include "arrays.hpp"

using namespace std;

int numberOfSwaps;
int numberOfComparisons;

// Funkcja zwraca true jeśli a < b, false w p. p.
bool lessThan(int a, int b) 
{
    numberOfComparisons++;
    return a < b;
}
// Funkcja ustawia wartość elementu tablicy o id1 na wartość elementu o id2
void swap(int* array, int id1, int id2)
{
    if (id1 == id2)
    {
        return;
    }
    numberOfSwaps++;
    int temp = array[id1];
    array[id1] = array[id2];
    array[id2] = temp;
}
// Dzieli fragment tablicy na dwie części: mniejszą oraz większą lub równą od piwota oraz zwraca id piwota 
void partition(int* array, int startId, int endId, int* p, int* q)
{
    // Strategia Count
    int leftTailCount = 0;
    int rightTailCount = 0;
    if (lessThan(array[endId - 1], array[startId]))
    {
        swap(array, startId, endId - 1);
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
            if (lessThan(array[i], leftPivot))
            {
                swap(array, i, a);                
                a++;
                leftTailCount++;
            }
            else if (lessThan(rightPivot, array[i]))
            {
                while (lessThan(rightPivot, array[b]) && i < b)
                {
                    b--;
                }
                swap(array, i, b);
                b--;
                rightTailCount++;
                if (lessThan(array[i], leftPivot))
                {
                    swap(array, i, a);
                    a++;
                    leftTailCount++;
                }
            }
            i++;
        }
        else
        {  
            if (lessThan(rightPivot, array[i]))
            {
                while (lessThan(rightPivot, array[b]) && i < b)
                {
                    b--;
                }
                swap(array, i, b);
                b--;
                rightTailCount++;
                if (lessThan(array[i], leftPivot))
                {
                    swap(array, i, a);
                    a++;
                    leftTailCount++;
                }
            }
            else if (lessThan(array[i], leftPivot))
            {
                swap(array, i, a);                
                a++;
                leftTailCount++;
            }
            i++;
        }    
    }
    a--;
    b++;
    swap(array, a, startId);
    swap(array, b, endId - 1);
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
    numberOfComparisons = 0;
    numberOfSwaps = 0;
    int n;
    cin >> n;
    int* inputArray = newArray(n); 
    for (int i = 0; i < n; i++)
    {
        cin >> inputArray[i];
    }
    if (n == 0)
    {
        return 44;
    }

    // Sortowanie właściwe
    quickSort(inputArray, 0, n);
    for (int i = 1; i < n; i++)
    {
        if (inputArray[i - 1] > inputArray[i])
        {
            return 49;
        }
    }
    cout << n << ";" << numberOfComparisons << ";" << numberOfSwaps << endl;
    return 0;
}
