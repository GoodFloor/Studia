#include <iostream>
#include <fstream>
#include "automate.hpp"

using namespace std;

int leadingOnes(unsigned char n)
{
    int t = 128;
    int leadingOnes = 0;
    while (n >= t && t > 0)
    {
        leadingOnes++;
        n -= t;
        t /= 2;
    }
    return leadingOnes;
}

int main(int argc, char const *argv[])
{
    if (argc != 3)
    {
        cout << "Poprawna składnia: ./test <wzorzec> <przeszukiwany plik>" << endl;
        return 44;
    }
    fstream inputFile;
    inputFile.open(argv[2], ios::in);
    if (!inputFile.good())
    {
        cout << "Requested file couldn't be opened." << endl;
        return 45;
    }
    string pattern = argv[1];
    int currentLetter = 0;
    int patternLength = 0;
    for (int i = 0; i < pattern.length(); i++)
    {
        patternLength++;
        if (leadingOnes(pattern[i]) > 1)
        {
            i += leadingOnes(pattern[i]) - 1;
        }
    }
    

    Automate automate = Automate(pattern);
    bool isOutputEmpty = true;
    cout << "Wzorzec znaleziony na pozycjach:\n\t[";
    while (!inputFile.eof())
    {
        unsigned char x;
        inputFile >> x;
        if (inputFile.eof())
        {
            break;
        }
        if (leadingOnes(x) != 1)
        {
            currentLetter++;
        }

        if (automate.nextLetter(x))
        {
            if (isOutputEmpty)
            {
                isOutputEmpty = false;
            }
            else
            {
                cout << ", ";
            }
            
            
            cout << currentLetter - patternLength;
        }
        

        
    }
    inputFile.close();
    cout << "]" << endl;
    cout << "Liczba znaków w pliku: " << currentLetter << endl;
    return 0;
}
