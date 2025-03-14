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
    numberOfSwaps+= 2;
    int temp = array[id1];
    array[id1] = array[id2];
    array[id2] = temp;
}
void set(int* array, int id, int value)
{
    numberOfSwaps++;
    array[id] = value;
}
// Dzieli fragment tablicy na dwie części: mniejszą oraz większą lub równą od piwota oraz zwraca id piwota 
int partition(int* array, int startId, int endId)
{
    // Hoare partition
    int pivot = array[endId - 1];
    int i = startId - 1;
    for (int j = startId; j < endId - 1; j++)
    {
        if (lessThan(array[j], pivot))
        {
            i++;
            swap(array, i, j);
        }
    }
    swap(array, i + 1, endId - 1);
    return i + 1;
}
void insertionSort(int* array, int startId, int endId)
{
    for (int j = startId + 1; j < endId; j++)
    {
        int key = array[j];
        int i = j - 1;
        while (i >= startId && lessThan(key, array[i]))
        {
            set(array, i + 1, array[i]);
            i--;
        }
        set(array, i + 1, key);
    }
}
void hybridQuickSort(int* array, int startId, int endId)
{
    while (endId - startId > 1)
    {
        if (endId - startId < 10)
        {
            insertionSort(array, startId, endId);
            break;
        }
        else
        {
            int pivot = partition(array, startId, endId);
            if (pivot - startId < endId - pivot - 1)
            {
                hybridQuickSort(array, startId, pivot);
                startId = pivot + 1;
            }
            else
            {
                hybridQuickSort(array, pivot + 1, endId);
                endId = pivot;
            }
        }
    }
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
        cout << "Tablica otrzymana na wejściu jest rozmiaru 0!" << endl;
        return 44;
    }
    if (n < 40)
    { 
        cout << "Tablica otrzymana na wejściu: ";
        printArray(inputArray, n);
        cout << endl;
    }
    else
    {
        cout << "Tablica otrzymana na wejściu ma " << n << " elementów." << endl;
    }

    // Sortowanie właściwe
    cout << "Wywołano procedurę quickSort" << endl << endl;
    hybridQuickSort(inputArray, 0, n);
    for (int i = 1; i < n; i++)
    {
        if (inputArray[i - 1] > inputArray[i])
        {
            cout << "Nie udało się posortować tablicy!";
            if (n < 40)
            {
                cout << " Tablica wyjściowa:\n\t";
                printArray(inputArray, n);
            }
            cout << endl;
            return 49;
        }
    }
    cout << "Pomyślnie posortowano tablicę";
    if (n < 40)
    {
        cout << ": ";
        printArray(inputArray, n);
    }
    cout << endl << "Wykonano " << numberOfComparisons << " porównań oraz " << numberOfSwaps << " razy zamieniono dwa klucze miejscami." << endl;
    return 0;
}
