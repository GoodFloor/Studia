#include "automate.hpp"

bool Automate::isSuffix(std::string a, std::string b)
{
    int lenA = a.length();
    int lenB = b.length();
    if (lenA > lenB)
    {
        return false;
    }
    return lenA > lenB ? false : b.substr(lenB - lenA) == a;
}

bool Automate::nextLetter(unsigned char c)
{
    currentState = delta[currentState][alphabet.find(c)] > 0 ? delta[currentState][alphabet.find(c)] : 0;
    return currentState == pattern.length();
}

Automate::Automate(std::string pattern)
{
    this->currentState = 0;
    this->pattern = pattern;
    this->alphabet = "αβγδ";
    // for (int i = 0; i < pattern.length(); i++)
    // {
    //     bool isInAlphabet = false;
    //     for (int j = 0; j < alphabet.length(); j++)
    //     {
    //         if (pattern[i] == pattern[j])
    //         {
    //             isInAlphabet = true;
    //         }
    //     }
    //     if (!isInAlphabet)
    //     {
    //         alphabet += pattern[i];
    //     }        
    // }

    this->delta = (int**)malloc(sizeof(int*) * pattern.length() + 1);
    for (int i = 0; i <= pattern.length(); i++)
    {
        this->delta[i] = (int*)malloc(sizeof(int) * alphabet.length());
    }

    int m = pattern.length();
    for (int q = 0; q <= m; q++)
    {
        for (int j = 0; j < alphabet.length(); j++)
        {
            char a = alphabet[j];
            int k = m < q + 1 ? m : q + 1;
            while (!isSuffix(pattern.substr(0, k), pattern.substr(0, q) + a))
            {
                k--;
            }
            delta[q][j] = k;
        }
    }
}

Automate::~Automate()
{
    for (int i = 0; i <= pattern.length(); i++)
    {
        free(delta[i]);
    }
    free(delta);
}
