#include <cmath>
#include <iostream>
#include <fstream>
#include <random>
#include "CompleteGraph.hpp"

using namespace std;

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
        cout << "Podany plik nie mógł zostać otwarty." << endl;
        return 45;
    }
    bool normalOutput = true;
    bool mstOutput = false;
    bool dfsOutput = false;
    bool shortOutput = false;
    if (argc == 3)
    {
        if (((string)argv[2]).compare("mst") == 0)
        {
            normalOutput = false;
            mstOutput = true;
        }
        else if (((string)argv[2]).compare("dfs") == 0)
        {
            normalOutput = false;
            dfsOutput = true;
        }
        else if (((string)argv[2]).compare("short") == 0)
        {
            normalOutput = false;
            shortOutput = true;
        }
    }
    
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
    MinimumSpanningTree mst = graph.getMST();
    if (mstOutput)
    {
        cout << graphSize << endl;
        for (int i = 0; i < graphSize; i++)
        {
            for (int j = i + 1; j < graphSize; j++)
            {
                if (mst.adjacencyMatrix[i][j] > -1)
                {
                    cout << graph.getNodeX(i) << " " << graph.getNodeY(i) << " " << graph.getNodeX(j) << " " << graph.getNodeY(j) << endl;
                }
            }
        }
        return 0;
    }
    int* dfs = mst.getDFS();
    if (dfsOutput)
    {
        cout << graphSize << endl;
        int n1 = dfs[0];
        for (int i = 1; i < graphSize; i++)
        {
            int n2 = dfs[i];
            cout << graph.getNodeX(n1) << " " << graph.getNodeY(n1) << " " << graph.getNodeX(n2) << " " << graph.getNodeY(n2) << endl;
            n1 = n2;
        }
        cout << graph.getNodeX(n1) << " " << graph.getNodeY(n1) << " " << graph.getNodeX(dfs[0]) << " " << graph.getNodeY(dfs[0]) << endl;
        return 0;
    }
    int n1 = dfs[0];
    int totalPathCost = 0;
    // cout << n1;
    for (int i = 1; i < graphSize; i++)
    {
        int n2 = dfs[i];
        // cout << " " << n2;
        totalPathCost += graph.getEdgeWeight(n1, n2);
        n1 = n2;
    }
    // cout << endl;
    totalPathCost += graph.getEdgeWeight(n1, dfs[0]);
    if (normalOutput)    
        cout << "Waga drzewa: " << mst.getTreeWeight() << endl << "Waga znalezionego cyklu: " << totalPathCost << endl;
    mt19937 mt(time(nullptr));
    int min10 = INT32_MAX;
    int min50 = INT32_MAX;
    int minAll = INT32_MAX;
    int sumMin10 = 0;
    int sumMin50 = 0;
    for (int i = 1; i <= 1000; i++)
    {
        int permutation[graphSize];
        for (int j = 0; j < graphSize; j++)
        {
            permutation[j] = j;
        }
        for (int j = 0; j < graphSize - 1; j++)
        {
            int k = mt() % (graphSize - j - 1) + j;
            if (k != j)
            {
                int temp = permutation[k];
                permutation[k] = permutation[j];
                permutation[j] = temp;
            }
        }
        
        int pathCost = 0;
        int n1 = permutation[0];
        for (int i = 1; i < graphSize; i++)
        {
            int n2 = permutation[i];
            pathCost += graph.getEdgeWeight(n1, n2);
            n1 = n2;
        }
        pathCost += graph.getEdgeWeight(n1, permutation[0]);

        if (pathCost < min10)
        {
            min10 = pathCost;
        }
        if (pathCost < min50)
        {
            min50 = pathCost;
        }
        if (pathCost < minAll)
        {
            minAll = pathCost;
        }       

        if (i % 10 == 0)
        {
            sumMin10 += min10;            
            if (i % 50 == 0)
            {
                sumMin50 += min50;
                int min50 = INT32_MAX;
            }
            int min10 = INT32_MAX;
        }
    }
    if (normalOutput)
        cout << "Dla 1000 losowych permutacji:\n\t-średnia z minimum dla każdych 10 kolejnych losowań (100 grup po 10 losowań) = " << sumMin10 / 100.0 << "\n\t-średnia z minimum dla każdych 50 kolejnych losowań (20 grup po 50 losowań) = " << sumMin50 / 20.0 << "\n\t-minimalna wartość z 1000 losowań = " << minAll << endl;
    if (shortOutput)
    {
        cout << "|V|\twaga MST\twaga DFS\tavg(min10)\tavg(min50)\tavg(min1000)" << endl;
        cout << graphSize << "\t" << mst.getTreeWeight() << "\t" << totalPathCost << "\t" << sumMin10 / 100.0 << "\t" << sumMin50 / 20.0 << "\t" << minAll << endl;
    }
    return 0;
}
