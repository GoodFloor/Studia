#include <cmath>
#include <iostream>
#include <fstream>
#include <random>
#include "CompleteGraph.hpp"
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
        cout << "Poprawna składnia:\n\t./app [nazwa pliku z grafem][zad1/zad2/zad3]" << endl;
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
    bool zad3 = false;
    if (((string)argv[2]).compare("zad1") == 0)
    {
        zad1 = true;
    }
    else if (((string)argv[2]).compare("zad2") == 0)
    {
        zad2 = true;
    }
    else if (((string)argv[2]).compare("zad3") == 0)
    {
        zad3 = true;
    }
    else
    {
        cout << "Podaj poprawny numer zadania! (zad1/zad2/zad3)" << endl;
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
     
    /// Zadanie 1
    if (zad1)
    {
        CompleteGraph graph = CompleteGraph(graphSize);
        for (int i = 0; i < graphSize; i++)
        {
            int id;
            int xPos;
            int yPos;
            inputFile >> id;
            inputFile >> xPos;
            inputFile >> yPos;
            graph.addVertex(xPos, yPos);
        }
        graph.addAllEdges();
        if (normalOutput)
            cout << "Graf o " << graphSize << " wierzchołkach wczytany poprawnie" << endl;
        Graph mst = graph.getMST();
        mt19937 mt(time(nullptr));
        double avgOptimizationIterations = 0.0;
        double avgWeight = 0.0;
        int bestWeight = INT32_MAX;
        Node* bestCycle = nullptr;
        int numberOfIterations = ceil(sqrt(graphSize));
        for (int i = 0; i < numberOfIterations; i++)
        {
            Node* permutation = mst.getDFS(mt() % graphSize);
            avgOptimizationIterations += LocalSearch(permutation, graphSize);
            int weight = 0;
            for (int j = 0; j < graphSize; j++)
            {
                weight += nodeDistance(permutation[j], permutation[(j + 1) % graphSize]);
            }
            avgWeight += weight;
            if (weight < bestWeight)
            {
                bestWeight = weight;
                bestCycle = permutation;
            }
            else
            {
                free(permutation);
            }
            
        }
        avgWeight /= numberOfIterations;
        avgOptimizationIterations /= numberOfIterations;

        if (shortOutput)
        {
            cout << "|V|\twaga MST\tavg(|pi|)\tavg(iter)\tpi_min" << endl;
            cout << graphSize << "\t" << mst.getGraphWeight() << "\t" << avgWeight << "\t" << avgOptimizationIterations << "\t" << bestWeight << endl;
        }
        else if (drawingOutput)
        {
            printCycle(bestCycle, graphSize);
        }
        else if (normalOutput)
        {
            cout << "Waga drzewa: " << mst.getGraphWeight() << "\n\tŚrednia waga zoptymalizowanych rozwiązań: " << avgWeight << "\n\tŚrednia liczba kroków poprawy: " << avgOptimizationIterations << "\n\tNajlepsze znalezione rozwiązanie: " << bestWeight << endl;
        }
        return 0; 
    }


    /// Zadanie 2
    if (zad2)
    {
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
        double avgWeight = 0.0;
        double avgIterations = 0.0;
        unsigned int bestWeight = UINT32_MAX;
        Node* bestPermutation = (Node*)malloc(sizeof(Node) * graphSize);
        for (int k = 0; k < graphSize; k++)
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

            avgIterations += LocalSearch(permutation, graphSize);
            int weight = 0;
            for (int j = 0; j < graphSize; j++)
            {
                weight += nodeDistance(permutation[j], permutation[(j + 1) % graphSize]);
            }
            avgWeight += weight;
            if (weight < bestWeight)
            {
                bestWeight = weight;
                for (int i = 0; i < graphSize; i++)
                {
                    bestPermutation[i] = permutation[i];
                }
            }
        }
        avgWeight /= ceil(sqrt(graphSize));
        avgIterations /= ceil(sqrt(graphSize));
        if (shortOutput)
        {
            cout << "|V|\tavg(|pi|)\tavg(iter)\tpi_min" << endl;
            cout << graphSize << "\t" << avgWeight << "\t" << avgIterations << "\t" << bestWeight << endl;
        }
        else if (drawingOutput)
        {
            printCycle(bestPermutation, graphSize);
        }
        else if (normalOutput)
        {
            cout << "Średnia waga zoptymalizowanych rozwiązań: " << avgWeight << "\nŚrednia liczba kroków poprawy: " << avgIterations << "\nNajlepsze znalezione rozwiązanie: " << bestWeight << endl;
        }
        return 0;
    }
    

    /// Zadanie 3
    if (zad3)
    {
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
        double avgWeight = 0.0;
        double avgIterations = 0.0;
        unsigned int bestWeight = UINT32_MAX;
        Node* bestPermutation = (Node*)malloc(sizeof(Node) * graphSize);
        for (int k = 0; k < graphSize; k++)
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

            avgIterations += QuickLocalSearch(permutation, graphSize);
            int weight = 0;
            for (int j = 0; j < graphSize; j++)
            {
                weight += nodeDistance(permutation[j], permutation[(j + 1) % graphSize]);
            }
            avgWeight += weight;
            if (weight < bestWeight)
            {
                bestWeight = weight;
                for (int i = 0; i < graphSize; i++)
                {
                    bestPermutation[i] = permutation[i];
                }
            }
        }
        avgWeight /= ceil(sqrt(graphSize));
        avgIterations /= ceil(sqrt(graphSize));
        if (shortOutput)
        {
            cout << "|V|\tavg(|pi|)\tavg(iter)\tpi_min" << endl;
            cout << graphSize << "\t" << avgWeight << "\t" << avgIterations << "\t" << bestWeight << endl;
        }
        else if (drawingOutput)
        {
            printCycle(bestPermutation, graphSize);
        }
        else if (normalOutput)
        {
            cout << "Średnia waga zoptymalizowanych rozwiązań: " << avgWeight << "\nŚrednia liczba kroków poprawy: " << avgIterations << "\nNajlepsze znalezione rozwiązanie: " << bestWeight << endl;
        }
        return 0;
    }
            
            
  

        
    
    

    // if (dfsOutput)
    // {
    //     cout << graphSize << endl;
    //     Node n1 = dfs[0];
    //     for (int i = 1; i < graphSize; i++)
    //     {
    //         Node n2 = dfs[i];
    //         cout << n1.x << " " << n1.y << " " << n2.x << " " << n2.y << endl;
    //         n1 = n2;
    //     }
    //     cout << n1.x << " " << n1.y << " " << dfs[0].x << " " << dfs[0].y << endl;
    //     return 0;
    // }
    // Node n1 = dfs[0];
    // int totalPathCost = 0;
    // // cout << n1;
    // for (int i = 1; i < graphSize; i++)
    // {
    //     Node n2 = dfs[i];
    //     // cout << " " << n2;
    //     totalPathCost += nodeDistance(n1, n2);
    //     n1 = n2;
    // }
    // // cout << endl;
    // totalPathCost += nodeDistance(n1, dfs[0]);
    // if (normalOutput)    
    //     cout << "Waga drzewa: " << mst.getGraphWeight() << endl << "Waga znalezionego cyklu: " << totalPathCost << endl;
    // int min10 = INT32_MAX;
    // int min50 = INT32_MAX;
    // int minAll = INT32_MAX;
    // int sumMin10 = 0;
    // int sumMin50 = 0;
    // for (int i = 1; i <= 1000; i++)
    // {
    //     int permutation[graphSize];
    //     for (int j = 0; j < graphSize; j++)
    //     {
    //         permutation[j] = j;
    //     }
    //     for (int j = 0; j < graphSize - 1; j++)
    //     {
    //         int k = mt() % (graphSize - j - 1) + j;
    //         if (k != j)
    //         {
    //             int temp = permutation[k];
    //             permutation[k] = permutation[j];
    //             permutation[j] = temp;
    //         }
    //     }
        
    //     int pathCost = 0;
    //     int n1 = permutation[0];
    //     for (int i = 1; i < graphSize; i++)
    //     {
    //         int n2 = permutation[i];
    //         pathCost += graph.getEdgeWeight(n1, n2);
    //         n1 = n2;
    //     }
    //     pathCost += graph.getEdgeWeight(n1, permutation[0]);

    //     if (pathCost < min10)
    //     {
    //         min10 = pathCost;
    //     }
    //     if (pathCost < min50)
    //     {
    //         min50 = pathCost;
    //     }
    //     if (pathCost < minAll)
    //     {
    //         minAll = pathCost;
    //     }       

    //     if (i % 10 == 0)
    //     {
    //         sumMin10 += min10;            
    //         if (i % 50 == 0)
    //         {
    //             sumMin50 += min50;
    //             int min50 = INT32_MAX;
    //         }
    //         int min10 = INT32_MAX;
    //     }
    // }
    // if (normalOutput)
    //     cout << "Dla 1000 losowych permutacji:\n\t-średnia z minimum dla każdych 10 kolejnych losowań (100 grup po 10 losowań) = " << sumMin10 / 100.0 << "\n\t-średnia z minimum dla każdych 50 kolejnych losowań (20 grup po 50 losowań) = " << sumMin50 / 20.0 << "\n\t-minimalna wartość z 1000 losowań = " << minAll << endl;
    // if (shortOutput)
    // {
    //     cout << "|V|\twaga MST\twaga DFS\tavg(min10)\tavg(min50)\tavg(min1000)" << endl;
    //     cout << graphSize << "\t" << mst.getGraphWeight() << "\t" << totalPathCost << "\t" << sumMin10 / 100.0 << "\t" << sumMin50 / 20.0 << "\t" << minAll << endl;
    // }
    return 0;
}
