#include <iostream>
#include "rng.hpp"

using namespace std;

int main(int argc, char const *argv[])
{
    if (argc != 2)
    {
        cout << "Poprawna składnia:\n\t./test1 <n>" << endl;
        return 44;
    }
    int n = stoi(argv[1]);
    int* outputArray = (int*)malloc(sizeof(int) * n);
    cout << n << endl;
    for (int i = 0; i < n; i++)
    {
        outputArray[i] = i;
        cout << i << endl;
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
    for (int i = 0; i < n; i++)
    {
        cout << outputArray[i] << endl;
    }
    return 0;
}
