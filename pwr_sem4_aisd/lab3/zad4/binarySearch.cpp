#include <iostream>
#include <string.h>
#include <chrono>
#include "arrays.hpp"
#include "performanceTest.hpp"
#include "rng.hpp"

using namespace std;

bool areResultsPrinted;
performanceTest* perf;

bool binarySearch(int* array, int startId, int endId, int searchedElement)
{
    if (areResultsPrinted)
    {
        cout << "binarySearch(";
        printArray(array, startId, endId);
        cout << ")" << endl;
    }
    if (endId <= startId)
    {
        return false;
    }
    int middle = (startId + endId) / 2;
    if (areResultsPrinted)
    {
        cout << "\tmiddle: A[" << middle << "] = " << array[middle] << endl;
    }
    if (perf->equals(array[middle], searchedElement))
    {
        return true;
    }
    else if (perf->lessThan(array[middle], searchedElement))
    {
        return binarySearch(array, middle + 1, endId, searchedElement);
    }
    else
    {
        return binarySearch(array, startId, middle, searchedElement);
    }
}

int main(int argc, char const *argv[])
{
    int n, v;
    cin >> n;
    cin >> v;
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
    bool isSerial = false;
    bool randomV = false;
    for (int i = 1; i < argc; i++)
    {
        if (strcmp(argv[i], "r") == 0)
        {
            randomV = true;
        }
        else if (strcmp(argv[i], "s") == 0)
        {
            isSerial = true;
            areResultsPrinted = false;
        }
    }

    if (areResultsPrinted)
    {
        cout << "Tablica na wejściu:\n\t";
        printArray(inputArray, n);
        cout << endl;
    }
    if (randomV)
    {
        Rng* rng = new Rng(0, n - 1);
        v = inputArray[rng->nextInt()];
        if (areResultsPrinted)
        {
            cout << "Szukany element: " << v << endl;
        }
    }
    perf = new performanceTest();
    auto start = chrono::high_resolution_clock::now();
    bool result = binarySearch(inputArray, 0, n, v);
    auto finish = chrono::high_resolution_clock::now();
    if (isSerial)
    {
        cout << n << "\t" << v << "\t" << perf->getNumberOfComparisons() << "\t" << chrono::duration_cast<chrono::nanoseconds>(finish - start).count() << endl;
    }
    else
    {
        if (result)
        {
            cout << "Szukany element istnieje w tablicy!" << endl;
        }
        else
        {
            cout << "Szukany element nie istnieje w tablicy!" << endl;
        }
        cout << "Liczba porównań: " << perf->getNumberOfComparisons() << endl << "Czas wykonywania: " << chrono::duration_cast<chrono::nanoseconds>(finish - start).count() << endl;
    }
    return 0;
}
