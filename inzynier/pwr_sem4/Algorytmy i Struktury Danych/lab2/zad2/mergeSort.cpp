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
void set(int* array, int id1, int value)
{
    numberOfSwaps++;
    array[id1] = value;
}
// Rekurencyjna funkcja sortująca tablicę od startId (włącznie) do endId (bez endId)
void mergeSort(int* array, int startId, int endId)
{
    int length = endId - startId;
    if (length <= 1)
    {
        return;
    }
    int halfId = length / 2 + startId;
    mergeSort(array, startId, halfId);
    mergeSort(array, halfId, endId);
    int* mergeReult = newArray(length);
    int a = startId;
    int b = halfId;
    for (int i = 0; i < length; i++)
    {
        if (a >= halfId)
        {
            set(mergeReult, i, array[b]);
            b++;
        }
        else if (b >= endId)
        {
            set(mergeReult, i, array[a]);
            a++;
        }        
        else if (lessThan(array[a], array[b]))
        {
            set(mergeReult, i, array[a]);
            a++;
        }
        else
        {
            set(mergeReult, i, array[b]);
            b++;
        }
    }
    for (int i = startId; i < endId; i++)
    {
        set(array, i, mergeReult[i - startId]);
    }
    free(mergeReult);
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
    mergeSort(inputArray, 0, n);
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
