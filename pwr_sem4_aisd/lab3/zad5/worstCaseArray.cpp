#include <iostream>
#include "arrays.hpp"

using namespace std;

int main(int argc, char const *argv[])
{
    if (argc != 2)
    {
        cout << "Poprawna składnia:\n\t./randomArray <n>" << endl;
        return 44;
    }
    int n = stoi(argv[1]);
    cout << n << endl;
    for (int i = 0; i < n; i++)
    {
        cout << i << endl;
    }
    return 0;
}
