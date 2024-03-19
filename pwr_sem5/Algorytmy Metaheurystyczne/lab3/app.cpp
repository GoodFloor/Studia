#include <cmath>
#include <iostream>
#include <fstream>
#include <random>
#include "GeneticAlgorithm.hpp"
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
    if (argc < 2 || argc > 3)
    {
        cout << "Poprawna składnia:\n\t./app [nazwa pliku z grafem]" << endl;
        return 44;
    }
    fstream inputFile;
    inputFile.open(argv[1], ios::in);
    if (!inputFile.good())
    {
        cout << "Plik " << argv[1] << " nie mógł zostać otwarty." << endl;
        return 45;
    }

    bool normalOutput = true;
    bool drawingOutput = false;
    bool shortOutput = false;
    if (argc == 3)
    {
        if (((string)argv[2]).compare("draw") == 0)
        {
            normalOutput = false;
            drawingOutput = true;
        }
        else if (((string)argv[2]).compare("short") == 0)
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


    // Wczytanie wierzchołków grafu
    Node* permutation = nodeArray(graphSize);
    for (int i = 0; i < graphSize; i++)
    {
        int id;
        int xPos;
        int yPos;
        inputFile >> id;
        inputFile >> xPos;
        inputFile >> yPos;
        permutation[i] = Node();
        permutation[i].id = id;
        permutation[i].x = xPos;
        permutation[i].y = yPos;
        permutation[i].costToReach = 0;
    }

    // Inicjalizacja zmiennych pomocniczych
    mt19937 mt(time(nullptr));
    int bestWeight = INT32_MAX;
    double avgWeight = 0.0;
    int repeats = 10;
    int populationSize = 20;
    int numberOfGenerations = 30;
    int archipelagoSize = 4;

    // Wykonywanie testów
    for (int k = 0; k < repeats; k++)
    {
        Archipelago archipelago = Archipelago(archipelagoSize);
        for (int a = 0; a < archipelagoSize; a++)
        {
            // Wylosowanie nowej populacji
            Population* population = new Population(populationSize, graphSize);
            for (int p = 0; p < populationSize; p++)
            {
                Node* t = nodeArray(graphSize);
                t[0] = permutation[0];

                bool alreadyIncluded[graphSize];
                alreadyIncluded[0] = true;
                for (int i = 1; i < graphSize; i++)
                {
                    alreadyIncluded[i] = false;
                }

                // Losowanie permutacji
                for (int i = 1; i < graphSize; i++)
                {
                    int rnd = (mt() % (graphSize - 1)) + 1;
                    while (alreadyIncluded[rnd])
                    {
                        rnd = (mt() % (graphSize - 1)) + 1;
                    }
                    alreadyIncluded[rnd] = true;
                    t[i] = permutation[rnd];
                    t[i].costToReach = t[i - 1].costToReach + nodeDistance(t[i], t[i - 1]);            
                }
                t[0].costToReach = t[graphSize - 1].costToReach + nodeDistance(t[graphSize - 1], t[0]);
                population->addSubject(t);
            }
            archipelago.addIsland(population);
        }
        Node* best = archipelago.populationEvolution(numberOfGenerations);
        if (best[0].costToReach < bestWeight)
        {
            bestWeight = best[0].costToReach;
        }

        avgWeight = avgWeight * (k / (k + 1.0)) + best[0].costToReach / (k + 1.0);
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
