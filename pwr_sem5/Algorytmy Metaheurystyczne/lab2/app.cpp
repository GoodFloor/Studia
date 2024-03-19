#include <cmath>
#include <iostream>
#include <fstream>
#include <random>
#include "Optimize.hpp"

using namespace std;

void printCycle(Node* permutation, int n)
{
    cout << n << endl;
    for (int i = 0; i < n; i++)
    {
        cout << permutation[i].x << " " << permutation[i].y << " " << permutation[(i + 1) % n].x << " " << permutation[(i + 1) % n].y << endl;
    }
}

int main(int argc, char const *argv[])
{
    if (argc < 3 || argc > 4)
    {
        cout << "Poprawna składnia:\n\t./app [nazwa pliku z grafem][zad1/zad2]" << endl;
        return 44;
    }
    fstream inputFile;
    inputFile.open(argv[1], ios::in);
    if (!inputFile.good())
    {
        cout << "Podany plik nie mógł zostać otwarty." << endl;
        return 45;
    }
    bool zad1 = false;
    bool zad2 = false;
    if (((string)argv[2]).compare("zad1") == 0)
    {
        zad1 = true;
    }
    else if (((string)argv[2]).compare("zad2") == 0)
    {
        zad2 = true;
    }
    else
    {
        cout << "Podaj poprawny numer zadania! (zad1/zad2)" << endl;
        return 44;
    }

    bool normalOutput = true;
    bool drawingOutput = false;
    bool shortOutput = false;
    if (argc == 4)
    {
        if (((string)argv[3]).compare("draw") == 0)
        {
            normalOutput = false;
            drawingOutput = true;
        }
        else if (((string)argv[3]).compare("short") == 0)
        {
            normalOutput = false;
            shortOutput = true;
        }
    }

    // Wczytywanie rozmiaru grafu i przejście do sekcji ze współrzędnymi wierzchołków
    string inputWord;
    int graphSize = 0;
    inputFile >> inputWord;
    while (inputWord.compare("NODE_COORD_SECTION") != 0)
    {
        if (inputWord.compare("DIMENSION") == 0)
        {
            inputFile >> inputWord;
            inputFile >> graphSize;
        }
        inputFile >> inputWord;
    }


    /// Zadania
    Node* permutation = (Node*)malloc(sizeof(Node) * graphSize);
    for (int i = 0; i < graphSize; i++)
    {
        int id;
        int xPos;
        int yPos;
        inputFile >> id;
        inputFile >> xPos;
        inputFile >> yPos;
        permutation[i] = Node();
        permutation[i].x = xPos;
        permutation[i].y = yPos;
    }
    mt19937 mt(time(nullptr));
    int bestWeight = INT32_MAX;
    double avgWeight = 0.0;
    Node* bestPermutation = (Node*)malloc(sizeof(Node) * graphSize);
    int repeats = 100;
    for (int k = 0; k < repeats; k++)
    {
        // Wylosowanie nowej permutacji
        for (int i = graphSize - 1; i > 0; i--)
        {
            int j = mt() % (i + 1);
            if (j != i)
            {
                Node t = permutation[i];
                permutation[i] = permutation[j];
                permutation[j] = t;
            }
        }
        int optimizedWeight = 0;
        if (zad1)
        {
            optimizedWeight = SimulatedAnnealing(permutation, graphSize);
        }
        else if (zad2)
        {
            optimizedWeight = TabuSearch(permutation, graphSize);
        }
        else
        {
            return 44;
        }
        avgWeight = avgWeight * (k / (k + 1.0)) + optimizedWeight / (k + 1.0);
        if (optimizedWeight < bestWeight)
        {
            bestWeight = optimizedWeight;
            for (int i = 0; i < graphSize; i++)
            {
                bestPermutation[i] = permutation[i];
            }
        }
    }
    if (normalOutput)
    {
        cout << "Średnia waga otrzymanych rozwiązań: " << avgWeight << "\nWaga najlepszego otrzymanego rozwiązania: " << bestWeight << endl;
    }
    else
    {
        cout << "|V|\tśrednia waga\tminimalna waga" << endl;
        cout << graphSize << "\t" << avgWeight << "\t" << bestWeight << endl;
    }
    return 0;
}
