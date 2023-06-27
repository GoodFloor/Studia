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
        return 44;
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
            return 49;
        }
    }
    cout << n << ";" << numberOfComparisons << ";" << numberOfSwaps << endl;
    return 0;
}
