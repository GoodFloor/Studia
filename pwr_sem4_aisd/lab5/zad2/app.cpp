#include <iostream>
#include <string.h>
#include "binomialHeap.hpp"
#include "fibonacciHeap.hpp"

using namespace std;

int main(int argc, char const *argv[])
{
    if (argc != 2)
    {
        cout << "Poprawna składnia:\n\t./app <rodzaj kopca>\nDostępne rodzaje kopców:\n\tB - kopiec dwumianowy\n\tF - kopiec Fibonacciego" << endl;
        return 44;
    }
    // 1. Make-Heap()
    Heap* h1;
    Heap* h2;
    if (strcmp(argv[1], "B") == 0)
    {
        h1 = new BinomialHeap();
        h2 = new BinomialHeap();
    }
    else if (strcmp(argv[1], "F") == 0)
    {
        h1 = new FibonacciHeap();
        h2 = new FibonacciHeap();
    }
    else
    {
        cout << "Poprawna składnia:\n\t./app <rodzaj kopca>\nDostępne rodzaje kopców:\n\tB - kopiec dwumianowy\n\tF - kopiec Fibonacciego" << endl;
        return 44;
    }

    // 2. Heap-Insert()
    int n;
    cin >> n;
    unsigned int t = 0;
    for (int i = 0; i < n; i++)
    {
        int x;
        cin >> x;
        // h1->numberOfComparisons = 0;
        h1->heapInsert(x);
        // cout << n << "\t" << t << "\t" << h1->numberOfComparisons << endl;
        t++;
        // cout << "h1: " << endl;
        // h1->print();
    }
    int m;
    cin >> m;
    for (int i = 0; i < m; i++)
    {
        int x;
        cin >> x;
        // h1->numberOfComparisons = 0;
        h2->heapInsert(x);
        // cout << n << "\t" << t << "\t" << h1->numberOfComparisons << endl;
        t++;
        // cout << "h2: " << endl;
        // h2->print();
    }

    // 3. Heap-Union()
    // h1->numberOfComparisons = 0;
    h1->heapUnion(h2);
    // cout << n << "\t" << t << "\t" << h1->numberOfComparisons << endl;
    t++;
    cout << "Union: " << endl;
    h1->print();

    // 4. Extract-Min()
    int prev = INT32_MIN;
    for (int i = 0; i < n + m; i++)
    {
        // h1->numberOfComparisons = 0;
        int curr = h1->extractMin();
        // cout << n << "\t" << t << "\t" << h1->numberOfComparisons << endl;
        t++;
        cout << "Usunięto " << curr << endl;
        h1->print();
        if (curr < prev)
        {
            cout << "Ciąg usuwanych elementów nie jest posortowany! (" << prev << " > " << curr << ")" << endl;
        }
        prev = curr;
    }
    if (!h1->isEmpty())
    {
        cout << "Kopiec nie jest pusty!" << endl;
    }
    cout << n << "\t" << h1->numberOfComparisons << endl;
    return 0;
}
