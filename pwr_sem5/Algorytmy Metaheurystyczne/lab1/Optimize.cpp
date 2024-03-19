#include <cstdlib>
#include <random>
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
