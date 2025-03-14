#include <iostream>
#include "arrays.hpp"
#include "performanceTest.hpp"

using namespace std;

bool areResultsPrinted;
performanceTest* perf;

// Dzieli fragment tablicy na dwie części: mniejszą oraz większą lub równą od piwota oraz zwraca id piwota 
int partition(int* array, int startId, int endId)
{
    // // Hoare partition
    // int pivot = array[(startId + endId) / 2];
    // int i = startId - 1;
    // int j = endId;
    // while (true)
    // {
    //     do
    //     {
    //         i++;
    //     } while (lessThan(array[i], pivot));
    //     do
    //     {
    //         j--;
    //     } while (lessThan(pivot, array[j]));
    //     if (i >= j)
    //     {
    //         return i;
    //     }
    //     swap(array, i, j);        
    // }
    
    // Lomuto partition
    int pivot = array[startId];
    int i = startId;
    for (int j = startId + 1; j < endId; j++)
    {
        if (perf->lessThan(array[j], pivot))
        {
            i++;
            perf->swap(&array[i], &array[j]);
        }
    }
    perf->swap(&array[i], &array[startId]);
    return i;
}
// Rekurencyjna funkcja sortująca tablicę od startId (włącznie) do endId (bez endId)
void quickSort(int* array, int startId, int endId)
{
    if (endId - startId <= 1)
    {
        return;
    }
    int pivot = partition(array, startId, endId);    
    quickSort(array, startId, pivot);
    quickSort(array, pivot + 1, endId);
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
