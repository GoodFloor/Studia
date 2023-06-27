#include <iostream>
#include <stdlib.h>
#include "arrays.hpp"
#include "performanceTest.hpp"

using namespace std;

bool areResultsPrinted;
performanceTest* perf;


int randPartition(int* array, int startId, int endId)
{
    // Hoare partition
    int pivotId = rand() % (endId - startId) + startId;
    perf->swap(&array[pivotId], &array[(startId + endId) / 2]);
    int pivot = array[(startId + endId) / 2];
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
            while (array[i] < pivot)
            {
                i++;
            }
            while (array[i] > pivot)
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

int randomizedSelect(int* array, int startId, int endId, int k)
{
    if (areResultsPrinted)
    {
        cout << "randomizedSelect (A = ";
        printArray(array, startId, endId);
        cout << "; k = " << k << ")" << endl;
    }
    if (endId - startId == 1)
    {
        return array[startId];
    }
    int r = randPartition(array, startId, endId);
    int i = r - startId;
    if (areResultsPrinted)
    {
        cout << "Piwot: A[" << i << "] = " << array[r] << endl << "Tablica po partycjonowaniu: \n\t";
        printArray(array, startId, endId);
        cout << endl;
    }
    if (i == k)
    {
        return array[r];
    }
    else if (i < k)
    {
        return randomizedSelect(array, r + 1, endId, k - i - 1);
    }
    else
    {
        return randomizedSelect(array, startId, r, k);
    }
}

// QuickSort do sprawdzenia poprawności wyniku
int partition(int* array, int startId, int endId)
{
    int pivot = array[(startId + endId) / 2];
    int i = startId - 1;
    int j = endId;
    while (true)
    {
        do
        {
            i++;
        } while (array[i] < pivot);
        do
        {
            j--;
        } while (pivot < array[j]);
        if (i >= j)
        {
            return i;
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;      
    }
}
void quickSort(int* array, int startId, int endId)
{
    if (endId - startId <= 1)
    {
        return;
    }
    int pivot = partition(array, startId, endId);
    quickSort(array, startId, pivot);
    quickSort(array, pivot, endId);
}

int main(int argc, char const *argv[])
{
    srand(time(NULL));
    int n, k;
    cin >> n;
    cin >> k;
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
    int x = randomizedSelect(inputArray, 0, n, k - 1); 
    if (areResultsPrinted)
    {
        cout << "Tablica na wyjściu:\n\t";
        printArray(inputArray, n);
        cout << endl << "Element o " << k << ". statystyce pozycyjnej: " << x << endl ;
        quickSort(inputArray, 0, n);
        cout << "Posortowana tablica wejściowa:\n\t";
        printArray(inputArray, n);
        cout << endl << k << ". element posortowanej tablicy wejściowej: " << inputArray[k - 1] << endl;
    }
    if (isSerial)
    {
        cout << n << "\t" << k << "\t" << perf->getNumberOfComparisons() << "\t" << perf->getNumberOfSwaps() << endl;
    }
    else
    {
        cout << "Liczba porównań: " << perf->getNumberOfComparisons() << endl << "Liczba swapów: " << perf->getNumberOfSwaps() << endl;
    }
    return 0;
}
