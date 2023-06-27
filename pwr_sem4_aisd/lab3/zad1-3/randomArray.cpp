#include <iostream>
#include "arrays.hpp"
#include "rng.hpp"

using namespace std;

int main(int argc, char const *argv[])
{
    if (argc != 3)
    {
        cout << "Poprawna składnia:\n\t./randomArray <n><k>" << endl;
        return 44;
    }
    int n = stoi(argv[1]);
    int* outputArray = newArray(n);
    for (int i = 0; i < n; i++)
    {
        outputArray[i] = i;
    }
    Rng* rand = new Rng(0, n - 1);
    for (int i = 0; i < n; i++)
    {
        int x = rand->nextInt();
        if (x != i)
        {
            int t = outputArray[i];
            outputArray[i] = outputArray[x];
            outputArray[x] = t;
        }
        
    }
    cout << n << endl << argv[2] << endl;
    for (int i = 0; i < n; i++)
    {
        cout << outputArray[i] << endl;
    }
    return 0;
}
