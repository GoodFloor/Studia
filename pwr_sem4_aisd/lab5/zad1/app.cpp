#include <iostream>
#include <time.h>
#include <sys/time.h>

using namespace std;

struct node
{
    node* prev;
    int value;
    char seriesAddition;
};


int main(int argc, char const *argv[])
{
    // Wczytanie ciągow
    int sizeA;
    cin >> sizeA;
    char* arrayA = (char*)malloc(sizeof(char) * sizeA);
    for (int i = 0; i < sizeA; i++)
    {
        cin >> arrayA[i];
    }
    int sizeB;
    cin >> sizeB;
    char* arrayB = (char*)malloc(sizeof(char) * sizeB);
    for (int i = 0; i < sizeB; i++)
    {
        cin >> arrayB[i];
    }

    // Przygotowanie tablicy z wynikami
    node** resultsArray = (node**)malloc(sizeof(node*) * (sizeA + 1));
    for (int i = 0; i <= sizeA; i++)
    {
        resultsArray[i] = (node*)malloc(sizeof(node) * (sizeB + 1));
        resultsArray[i][0] = node();
        resultsArray[i][0].prev = nullptr;
        resultsArray[i][0].seriesAddition = 0;
        resultsArray[i][0].value = 0;
    }
    for (int i = 1; i <= sizeB; i++)
    {
        resultsArray[0][i] = node();
        resultsArray[0][i].prev = nullptr;
        resultsArray[0][i].seriesAddition = 0;
        resultsArray[0][i].value = 0;
    }

    // Szukanie najdłuższego wspólnego podciągu
    int numberOfComparisons = 0;
    timeval begin;
    gettimeofday(&begin, NULL);
    for (int i = 1; i <= sizeA; i++)
    {
        for (int j = 1; j <= sizeB; j++)
        {
            resultsArray[i][j] = node();
            numberOfComparisons++;
            if (arrayA[i - 1] == arrayB[j - 1])
            {
                resultsArray[i][j].prev = &resultsArray[i - 1][j - 1];
                resultsArray[i][j].seriesAddition = arrayA[i - 1];
                resultsArray[i][j].value = resultsArray[i - 1][j - 1].value + 1;
            }
            else if (resultsArray[i - 1][j].value > resultsArray[i][j - 1].value)
            {
                resultsArray[i][j].prev = &resultsArray[i - 1][j];
                resultsArray[i][j].seriesAddition = 0;
                resultsArray[i][j].value = resultsArray[i - 1][j].value;
            }
            else
            {
                resultsArray[i][j].prev = &resultsArray[i][j - 1];
                resultsArray[i][j].seriesAddition = 0;
                resultsArray[i][j].value = resultsArray[i][j - 1].value;
            }
        }
    }
    timeval end;
    gettimeofday(&end, NULL);
    timeval diff;
    timersub(&end, &begin, &diff);
    unsigned long long time = (unsigned long long)diff.tv_sec * 1000000 + (unsigned long long)diff.tv_usec;

    // Odczytanie wyników
    if (sizeA < 100)
    {
        cout << "Czas działania: " << time << " us" << endl;
        node curr = resultsArray[sizeA][sizeB];
        int length = curr.value;
        cout << "Długość najdłuższego wspólnego ciągu: " << length << endl;
        char* longestSeries = (char*)malloc(sizeof(char) * length);
        int l = 0;
        while (curr.prev != nullptr)
        {
            if (curr.seriesAddition != 0)
            {
                l++;
                longestSeries[length - l] = curr.seriesAddition;
            }
            curr = *curr.prev;
        }    
        cout << "Najdłuższy wspólny ciąg: {";
        for (int i = 0; i < length; i++)
        {
            if (i > 0)
            {
                cout << ", ";
            }
            
            cout << longestSeries[i];
        }
        cout << "}" << endl;
    }
    else
    {
        cout << sizeA << "\t" << time << "\t" << numberOfComparisons << endl;
    }
    
    
    return 0;
}
