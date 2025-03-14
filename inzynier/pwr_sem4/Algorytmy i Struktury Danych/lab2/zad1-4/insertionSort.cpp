#include <iostream>
#include "arrays.hpp"

using namespace std;

int numberOfSwaps;
int numberOfComparisons;

// Funkcja zwraca true jeśli a > b, false w p. p.
bool greaterThan(int a, int b) 
{
    numberOfComparisons++;
    return a > b;
}
// Funkcja ustawia wartość elementu tablicy o id1 na wartość elementu o id2
void set(int* array, int id1, int id2)
{
    numberOfSwaps++;
    array[id1] = array[id2];
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
    for (int j = 1; j < n; j++)
    {
        int key = inputArray[j];
        int i = j - 1;
        while (i >= 0 && greaterThan(inputArray[i], key))
        {
            set(inputArray, i + 1, i);
            i--;
        }
        numberOfSwaps ++;
        inputArray[i + 1] = key;
    }
    for (int i = 1; i < n; i++)
    {
        if (inputArray[i - 1] > inputArray[i])
        {
            cout << "Nie udało się posortować tablicy!" << endl;
            return 49;
        }
    }
    cout << "Pomyślnie posortowano tablicę";
    if (n < 40)
    {
        cout << ": ";
        printArray(inputArray, n);
    }
    cout << endl << "Wykonano " << numberOfComparisons << " porównań oraz " << numberOfSwaps << " razy ustawiono ponownie wartość poszczególnego elementu tablicy." << endl;
    return 0;
}
