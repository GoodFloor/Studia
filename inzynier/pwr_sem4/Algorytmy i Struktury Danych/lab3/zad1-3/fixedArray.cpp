#include <iostream>
#include "arrays.hpp"

using namespace std;

int main(int argc, char const *argv[])
{
    if (argc != 2)
    {
        cout << "Poprawna składnia:\n\t./fixedArray <k>" << endl;
        return 44;
    }
    int n = 25;
    int outputArray[n] = {17, 6, 8, 10, 07, 15, 23, 3, 20, 22, 13, 1, 12, 19, 18, 5, 04, 24, 21, 11, 16, 2, 9, 14, 0};
    cout << n << endl << argv[1] << endl;
    for (int i = 0; i < n; i++)
    {
        cout << outputArray[i] << endl;
    }
    return 0;
}
