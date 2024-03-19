#include "automate.hpp"

bool Automate::nextLetter(char t)
{
    while (currentState > 0 && pattern[currentState] != t)
    {
        currentState = pi[currentState - 1];
    }
    if (pattern[currentState] == t)
    {
        currentState++;
    }
    if (currentState == pattern.length())
    {
        currentState = pi[currentState - 1];
        return true;
    }
    return false;
}

Automate::Automate(std::string pattern)
{
    this->pattern = pattern;
    this->pi = (int*)malloc(sizeof(int) * pattern.length());
    this->currentState = 0;

    int m = pattern.length();
    pi[0] = 0;
    int k = 0;
    for (int q = 2; q <= m; q++)
    {
        while (k > 0 && pattern[k] != pattern[q - 1])
        {
            k = pi[k - 1];
        }
        if (pattern[k] == pattern[q - 1])
        {
            k++;
        }
        pi[q - 1] = k;
    }
}

Automate::~Automate()
{
    free(pi);
}
