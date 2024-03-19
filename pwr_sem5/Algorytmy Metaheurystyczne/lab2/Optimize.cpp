#include <cstdlib>
#include <random>
#include <iostream>
#include "Node.hpp"
#include "Optimize.hpp"

template <typename T>
void invert(T* p, int a, int b)
{
    if (a == b)
    {
        return;
    }
    if (a > b)
    {
        int t = a;
        a = b;
        b = t;
    }
    T temp[b - a + 1];
    for (int i = 0; i < b - a + 1; i++)
    {
        temp[i] = p[b - i];
    }
    for (int i = 0; i < b - a + 1; i++)
    {
        p[a + i] = temp[i];
    }
}

bool compareArray(Node* tabA, Node* tabB, int size)
{
    for (int i = 0; i < size; i++)
    {
        if (tabA[i].x != tabB[i].x || tabA[i].y != tabB[i].y)
        {
            return false;
        }
    }
    return true;
}
void copyArray(Node* source, Node* destination, int size)
{
    for (int i = 0; i < size; i++)
    {
        destination[i] = source[i];
    }
}

int LocalSearch(Node *p, int n)
{
    int* cost = (int*)malloc(sizeof(int) * n);
    int totalCost = 0;
    for (int i = 0; i < n; i++)
    {
        cost[i] = nodeDistance(p[i], p[(i + 1) % n]);
        totalCost += cost[i];
    }
    int bestCost = totalCost;
    int it = 1;
    while (true)
    {
        int bestInversionI = -1;
        int bestInversionJ = -1;
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                if (i == 0 && j == n - 1)
                {
                    continue;
                }
                
                int prevI = i == 0 ? n - 1 : i - 1;
                int neighbourCost = totalCost - cost[prevI] - cost[j] + nodeDistance(p[prevI], p[j]) + nodeDistance(p[i], p[(j + 1) % n]);
                if (neighbourCost < bestCost)
                {
                    bestInversionI = i;
                    bestInversionJ = j;
                    bestCost = neighbourCost;
                }   
            }
        }
        if(bestInversionI == -1)
            return it;
        it++;
        invert(p, bestInversionI, bestInversionJ);
        invert(cost, bestInversionI, bestInversionJ - 1);
        int prevI = bestInversionI == 0 ? n - 1 : bestInversionI - 1;
        cost[prevI] = nodeDistance(p[prevI], p[bestInversionI]);
        cost[bestInversionJ] = nodeDistance(p[bestInversionJ], p[(bestInversionJ + 1) % n]);
        totalCost = bestCost;
    }
}

int QuickLocalSearch(Node *p, int n)
{
    std::mt19937 mt(time(nullptr));
    int* cost = (int*)malloc(sizeof(int) * n);
    int totalCost = 0;
    for (int i = 0; i < n; i++)
    {
        cost[i] = nodeDistance(p[i], p[(i + 1) % n]);
        totalCost += cost[i];
    }
    int bestCost = totalCost;
    int it = 1;
    bool permutationVisited[n][n];
    while (true)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                permutationVisited[i][j] = false;
            }
        }
        int bestInversionI = -1;
        int bestInversionJ = -1;
        for (int k = 0; k < n; k++)
        {
            int i = mt() % n;
            int j = mt() % n;
            while ((i == 0 && j == n - 1) || (i == j) || permutationVisited[i][j])
            {
                i = mt() % n;
                j = mt() % n;
            }
            permutationVisited[i][j] = true;
            permutationVisited[j][i] = true;
            
            int prevI = i == 0 ? n - 1 : i - 1;
            int neighbourCost = totalCost - cost[prevI] - cost[j] + nodeDistance(p[prevI], p[j]) + nodeDistance(p[i], p[(j + 1) % n]);
            if (neighbourCost < bestCost)
            {
                bestInversionI = i;
                bestInversionJ = j;
                bestCost = neighbourCost;
            }  
        }
        if(bestInversionI == -1)
            return it;
        it++;
        invert(p, bestInversionI, bestInversionJ);
        invert(cost, bestInversionI, bestInversionJ - 1);
        int prevI = bestInversionI == 0 ? n - 1 : bestInversionI - 1;
        cost[prevI] = nodeDistance(p[prevI], p[bestInversionI]);
        cost[bestInversionJ] = nodeDistance(p[bestInversionJ], p[(bestInversionJ + 1) % n]);
        totalCost = bestCost;
    }
}

int SimulatedAnnealing(Node *p, int n)
{
    // Parametry początkowe
    double temperature = 25.0;
    double coolingFactor = 0.9;
    int k = 100;                // Liczba prób w ramach jednej epoki

    // Algorytm
    std::mt19937 mt(time(nullptr));
    int* cost = (int*)malloc(sizeof(int) * n);
    int totalCost = 0;
    Node* bestPermutation = (Node*)malloc(sizeof(Node) * n);
    for (int i = 0; i < n; i++)
    {
        bestPermutation[i] = p[i];
        cost[i] = nodeDistance(p[i], p[(i + 1) % n]);
        totalCost += cost[i];
    }
    int bestCost = totalCost;

    while (temperature > 1.0)
    {
        for (int i = 0; i < k; i++)
        {
            int a = mt() % n;
            int b = mt() % n;
            while ((a == b) || (a == 0 && b == n - 1) || (a == n - 1 && b == 0))
            {
                a = mt() % n;
                b = mt() % n;
            }
            if (a > b)
            {
                int t = a;
                a = b;
                b = t;
            }
            int prevI = i == 0 ? n - 1 : i - 1;
            int neighbourCost = totalCost - cost[(a - 1 + n) % n] - cost[b] + nodeDistance(p[(a - 1 + n) % n], p[b]) + nodeDistance(p[a], p[(b + 1) % n]);
            if (neighbourCost < totalCost || (mt() % 1000000001 / 1000000000.0) <= exp((totalCost - neighbourCost) / temperature))
            {
                invert(p, a, b);
                invert(cost, a, b - 1);
                cost[(a - 1 + n) % n] = nodeDistance(p[(a - 1 + n) % n], p[a]);
                cost[b] = nodeDistance(p[b], p[(b + 1) % n]);
                totalCost = neighbourCost;
            }
            if (totalCost < bestCost)
            {
                for (int i = 0; i < n; i++)
                {
                    bestPermutation[i] = p[i];
                }
            }     
        }
        temperature *= coolingFactor;
    }

    for (int i = 0; i < n; i++)
    {
        p[i] = bestPermutation[i];
    }
    free(bestPermutation);
    return bestCost;    
}

int TabuSearch(Node *p, int n)
{
    // Parametr początkowy
    int tabuLength = n;

    // Algorytm
    Node** tabuList = (Node**)malloc(sizeof(Node*) * tabuLength);
    for (int i = 0; i < tabuLength; i++)
    {
        tabuList[i] = (Node*)malloc(sizeof(Node) * n);
    }
    int nextTabuId = 0;

    int* cost = (int*)malloc(sizeof(int) * n);
    int totalCost = 0;
    Node* bestPermutation = (Node*)malloc(sizeof(Node) * n);
    for (int i = 0; i < n; i++)
    {
        bestPermutation[i] = p[i];
        cost[i] = nodeDistance(p[i], p[(i + 1) % n]);
        totalCost += cost[i];
    }
    int bestCost = totalCost;
    std::mt19937 mt(time(nullptr));
    while (nextTabuId < tabuLength)
    {
        int bestInversionI = -1;
        int bestInversionJ = -1;
        int tempBestCost = INT32_MAX;
        Node* tempBestPermutation = (Node*)malloc(sizeof(Node) * n);

        for (int k = 0; k < 100; k++)
        {
            // Losuj i, j
            int i = mt() % n;
            int j = mt() % n;
            while ((i == 0 && j == n - 1) || (i == j) || (j == 0 && i == n - 1))
            {
                i = mt() % n;
                j = mt() % n;
            }
            if (i > j)
            {
                int t = i;
                i = j;
                j = t;
            }
            int prevI = i == 0 ? n - 1 : i - 1;

            // Sprawdź koszt permutacji
            int neighbourCost = totalCost - cost[prevI] - cost[j] + nodeDistance(p[prevI], p[j]) + nodeDistance(p[i], p[(j + 1) % n]);
            
            // Jeśli lepszy niż dotychczas znaleziony
            if (neighbourCost < tempBestCost)
            {
                // Sprawdź czy jest już na liście tabu
                bool isInTabu = false;
                copyArray(p, tempBestPermutation, n);
                invert(tempBestPermutation, i, j);
                for (int a = 0; a < nextTabuId && !isInTabu; a++)
                {
                    isInTabu = compareArray(tabuList[a], tempBestPermutation, n);
                }

                // Jeśli nie to zapamiętaj tą inwersję
                if (!isInTabu)
                {
                    bestInversionI = i;
                    bestInversionJ = j;
                    tempBestCost = neighbourCost;
                }   
            }  
        }

        // Jeśli nie znaleziono żadnej dobrej inwersji to przejdź do nastęnej iteracji
        if (bestInversionI == -1)
        {
            continue;
        }

        // W p.p. zapisujemy nową permutację i jej koszt
        invert(p, bestInversionI, bestInversionJ);
        invert(cost, bestInversionI, bestInversionJ - 1);
        int prevI = bestInversionI == 0 ? n - 1 : bestInversionI - 1;
        cost[prevI] = nodeDistance(p[prevI], p[bestInversionI]);
        cost[bestInversionJ] = nodeDistance(p[bestInversionJ], p[(bestInversionJ + 1) % n]);
        totalCost = tempBestCost;

        // Jeżeli nowa permutacja jest lepsza niż najlepsza dotychczas to zapamiętujemy ją
        if (totalCost < bestCost)
        {
            copyArray(p, bestPermutation, n);
            bestCost = totalCost;
        }

        // Zapisujemy nową permutację na liście tabu
        copyArray(p, tabuList[nextTabuId], n);
        nextTabuId++;

        free(tempBestPermutation);
    }
    copyArray(bestPermutation, p, n);
    // free(bestPermutation);
    // for (int i = 0; i < nextTabuId; i++)
    // {
    //     free(tabuList[i]);
    // }
    // free(tabuList);
    return bestCost;
}
