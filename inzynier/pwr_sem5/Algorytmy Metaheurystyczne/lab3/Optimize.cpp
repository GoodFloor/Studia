#include <cstdlib>
#include <random>
#include <iostream>
#include <vector>
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
        if (tabA[i].id != tabB[i].id)
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

void TabuSearch(Node *p, int n)
{
    // Parametr początkowy
    int tabuLength = 20;

    // Algorytm
    std::vector<Node*> tabuList;

    int* cost = (int*)malloc(sizeof(int) * n);
    int totalCost = 0;
    Node* bestPermutation = nodeArray(n);
    for (int i = 0; i < n; i++)
    {
        bestPermutation[i] = p[i];
        cost[i] = nodeDistance(p[i], p[(i + 1) % n]);
        totalCost += cost[i];
    }
    int bestCost = totalCost;
    std::mt19937 mt(time(nullptr));
    while (tabuList.size() < tabuLength)
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
                for (int a = 0; a < tabuList.size() && !isInTabu; a++)
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
        Node* t = nodeArray(n);
        copyArray(p, t, n);
        tabuList.push_back(t);

        free(tempBestPermutation);
    }
    copyArray(bestPermutation, p, n);
    p[0].costToReach = 0;
    for (int i = 1; i < n; i++)
    {
        p[i].costToReach = p[i - 1].costToReach + nodeDistance(p[i - 1], p[i]);
    }
    p[0].costToReach = p[n - 1].costToReach + nodeDistance(p[n - 1], p[0]);
    
    // free(bestPermutation);
    // for (int i = 0; i < nextTabuId; i++)
    // {
    //     free(tabuList[i]);
    // }
    // free(tabuList);
}
