#include <iostream>
#include "arrays.hpp"

using namespace std;

int main(int argc, char const *argv[])
{
    if (argc != 3)
    {
        cout << "Poprawna składnia:\n\t./ascendingArray <n><v>" << endl;
        return 44;
    }
    int n = stoi(argv[1]);
    cout << n << endl;
    cout << argv[2] << endl;
    for (int i = 0; i < n; i++)
    {
        cout << i << endl;
    }
    return 0;
}
