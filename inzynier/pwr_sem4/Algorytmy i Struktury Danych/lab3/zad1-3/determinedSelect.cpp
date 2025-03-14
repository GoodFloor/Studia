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
    if (areResultsPrinted)
    {
        cout << "determinedSelect (A = ";
        printArray(array, startId, endId);
        cout << "; k = " << k << ")" << endl;
    }
    
    if (endId - startId <= subarraySize)
    {
        insertionSort(array, startId, endId);
        return array[startId + k];
    }
    int numberOfSubarrays = ceil((endId - startId) * 1.0 / subarraySize);
    int* meanArray = newArray(numberOfSubarrays);
    int p = startId, q = startId + subarraySize;
    if (areResultsPrinted)
    {
        cout << subarraySize << "-elementowe, posortowane podtablice:" << endl;
    }
    for (int i = 0; i < numberOfSubarrays - 1; i++)
    {
        insertionSort(array, p, q);
        meanArray[i] = array[(q + p) / 2];
        if (areResultsPrinted)
        {
             cout << "\t";
            printArray(array, p, q);
            cout << endl;
        }
        p = q;
        q += subarraySize;
    }
    insertionSort(array, p, endId);
    meanArray[numberOfSubarrays - 1] = array[(endId + p) / 2];
    if (areResultsPrinted)
    {
        cout << "\t";
        printArray(array, p, endId);
        cout << endl;
    }
    int meanOfMeans = determinedSelect(meanArray, 0, numberOfSubarrays, numberOfSubarrays / 2);
    if (areResultsPrinted)
    {
        cout << "Mediana median (piwot do partycjonowania): " << meanOfMeans << endl << "Tablica po partycjonowaniu: \n\t";
    }
    free(meanArray);
    int r = determinedPartition(array, startId, endId, meanOfMeans);
    if (areResultsPrinted)
    {
        printArray(array, startId, endId);
        cout << endl;
    }
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
    if (argc > 1)
    {
        subarraySize = stoi(argv[1]);
    }

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
    if (argc > 2)
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
    int x = determinedSelect(inputArray, 0, n, k - 1);
    long int duration = perf->stopTimer();
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
        cout << n << "\t" << k << "\t" << perf->getNumberOfComparisons() << "\t" << perf->getNumberOfSwaps() << "\t" << duration << endl;
    }
    else
    {
        cout << "Liczba porównań: " << perf->getNumberOfComparisons() << endl << "Liczba swapów: " << perf->getNumberOfSwaps() << endl << "Czas trwania: " << duration << endl;
    }
    return 0;
}
