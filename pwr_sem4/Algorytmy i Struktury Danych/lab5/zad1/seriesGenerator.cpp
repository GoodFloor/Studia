#include <iostream>
#include <random>

using namespace std;

int main(int argc, char const *argv[])
{
    if (argc != 3)
    {
        cout << "Poprawna składnia:\n\t./seriesGenerator <długość pierwszego ciągu> <długość drugiego ciągu>" << endl;
        return 44;
    }
    int n = atoi(argv[1]);
    int m = atoi(argv[2]);
    if (n < 1 || m < 1)
    {
        cout << "Długość ciągu musi być liczbą dodatnią!" << endl;
        return 45;
    }
    mt19937 mt(time(nullptr));
    cout << n << endl;
    for (int i = 0; i < n; i++)
    {
        cout << (char)('A' + (mt() % 26)) << endl;
    }
    cout << m << endl;
    for (int i = 0; i < m; i++)
    {
        cout << (char)('A' + (mt() % 26)) << endl;
    }
    return 0;
}
