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
int partition(int* array, int startId, int endId)
{
    // Hoare partition
    int pivot = array[(startId + endId) / 2];
    int i = startId - 1;
    int j = endId;
    while (true)
    {
        do
        {
            i++;
        } while (lessThan(array[i], pivot));
        do
        {
            j--;
        } while (lessThan(pivot, array[j]));
        if (i >= j)
        {
            return i;
        }
        swap(array, i, j);        
    }
    
    // Lomuto partition
    // int pivot = array[startId];
    // int i = startId;
    // for (int j = startId + 1; j < endId; j++)
    // {
    //     if (lessThan(array[j], pivot))
    //     {
    //         i++;
    //         swap(array, i, j);
    //     }
    // }
    // swap(array, i, startId);
    // return i;
}
// Rekurencyjna funkcja sortująca tablicę od startId (włącznie) do endId (bez endId)
void quickSort(int* array, int startId, int endId)
{
    if (endId - startId <= 1)
    {
        return;
    }
    if (endId - startId < 40)
    {
        cout << "Tablica przed partycjonowaniem: {" << array[startId];
        for (int i = startId + 1; i < endId; i++)
        {
            cout << ", " << array[i];
        }
        cout << "}" << endl;
    }
    int pivot = partition(array, startId, endId);
    if (endId - startId < 40)
    {
        cout << "Tablica po partycjonowaniu: {" << array[startId];
        for (int i = startId + 1; i < endId; i++)
        {
            cout << ", " << array[i];
        }
        cout << "}" << endl ;
        cout << "Piwot: A[" << pivot - startId << "] = " << array[pivot] << endl << endl;
    }
    
    quickSort(array, startId, pivot);
    quickSort(array, pivot, endId);
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
    quickSort(inputArray, 0, n);
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
