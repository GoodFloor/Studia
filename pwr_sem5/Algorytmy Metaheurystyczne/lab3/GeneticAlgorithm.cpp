#include <cstdlib>
#include <random>
#include "GeneticAlgorithm.hpp"
#include "Optimize.hpp"

#include <iostream>

Node *Population::halfbreed(Node *father, Node *mother)
{
    int permutationSize = subjectSize;
    bool alreadyIncluded[permutationSize];
    for (int i = 0; i < permutationSize; i++)
    {
        alreadyIncluded[i] = false;
    }
    
    Node* son = nodeArray(permutationSize);
    for (int i = 0; i < permutationSize / 2; i++)
    {
        son[i] = father[i];
        alreadyIncluded[son[i].id] = true;
    }

    int currI = permutationSize / 2;
    for (int i = 0; i < permutationSize && currI < permutationSize; i++)
    {
        if (!alreadyIncluded[mother[i].id])
        {
            son[currI] = mother[i];
            son[currI].costToReach = son[currI - 1].costToReach + nodeDistance(son[currI - 1], son[currI]);
            currI++;
        }
    }
    son[0].costToReach = son[permutationSize - 1].costToReach + nodeDistance(son[permutationSize - 1], son[0]);
    return son;
}

Population::Population(int populationSize, int subjectSize)
{
    this->populationSize = populationSize;
    this->subjectSize = subjectSize;
    if (populationSize % 2 == 1)
        this->populationSize--;
}

Population::~Population()
{
    for (int i = 0; i < subjects.size(); i++)
    {
        free(subjects[i]);
    }
}

void Population::addSubject(Node *permutation)
{
    int c = permutation[0].costToReach;
    std::vector<Node*>::iterator it = subjects.begin();
    for (it; it < subjects.end(); it++)
        if ((*it)[0].costToReach >= c)
            break;
    subjects.insert(it, permutation);
}

void Population::reducePopulation()
{
    while (subjects.size() > populationSize)
    {
        free(subjects.back());
        subjects.pop_back();
    }
}

void Population::crossbreedPopulation()
{
    bool inCouple[populationSize];
    for (int i = 0; i < populationSize; i++)
    {
        inCouple[i] = false;
    }

    std::mt19937 mt(time(nullptr));
    std::vector<Node*> childrenTempVector;

    // Dobieramy wszystkie osobniki w pary i generujemy dzieci
    for (int i = 0; i < populationSize / 2; i++)
    {
        int paren1 = mt() % populationSize;
        while (inCouple[paren1])
        {
            paren1 = mt() % populationSize;
        }
        inCouple[paren1] = true;

        int paren2 = mt() % populationSize;
        while (inCouple[paren2])
        {
            paren2 = mt() % populationSize;
        }
        inCouple[paren2] = true;

        childrenTempVector.push_back(halfbreed(subjects[paren1], subjects[paren2]));
        childrenTempVector.push_back(halfbreed(subjects[paren2], subjects[paren1]));
    }
    
    // Dodajemy dzieci do populacji
    for (int i = 0; i < childrenTempVector.size(); i++)
    {
        adultSubject(childrenTempVector[i]);
        addSubject(childrenTempVector[i]);
    }
}

void Population::mutateRandom(int howMany)
{
    std::mt19937 mt(time(nullptr));
    bool alreadyMutated[populationSize];
    for (int i = 0; i < populationSize; i++)
    {
        alreadyMutated[i] = false;
    }

    for (int k = 0; k < howMany; k++)
    {
        int id = mt() % populationSize;
        while (alreadyMutated[id])
        {
            id = mt() % populationSize;
        }
        alreadyMutated[id] = true;

        int a = mt() % (subjectSize - 1) + 1;
        int b = mt() % (subjectSize - 1) + 1;

        if (a != b)
        {
            // Zamiana
            Node* s = subjects[id];
            subjects.erase(subjects.begin() + id);
            Node t = s[a];
            s[a] = s[b];
            s[b] = t;

            // Przeliczenie kosztów
            int first = a <= b ? a : b;
            int second = a > b ? a : b;
            if (first == 1)
            {
                s[1].costToReach = nodeDistance(s[0], s[1]);
                first++;
            }
            for (int i = first; i < subjectSize; i++)
                s[i].costToReach = s[i - 1].costToReach + nodeDistance(s[i - 1], s[i]);
            s[0].costToReach = s[subjectSize - 1].costToReach + nodeDistance(s[subjectSize - 1], s[0]);
            addSubject(s);
        }
    }
}

void Population::adultSubject(Node *permutation)
{
    TabuSearch(permutation, subjectSize);
}

Node *Population::populationEvolution(int generations)
{
    Node* bestPermutation = nodeArray(subjectSize);
    for (int i = 0; i < subjectSize; i++)
    {
        bestPermutation[i] = subjects[0][i];
    }

    for (int k = 0; k < generations; k++)
    {
        reducePopulation();
        crossbreedPopulation();
        if (subjects[0][0].costToReach < bestPermutation[0].costToReach)
        {
            for (int i = 0; i < subjectSize; i++)
            {
                bestPermutation[i] = subjects[0][i];
            }
        }
        mutateRandom(populationSize * 0.05);
    }
    return bestPermutation;
}

Node *Population::getBest()
{
    return subjects[0];
}

Node *Population::popBest()
{
    Node* best = subjects[0];
    subjects.erase(subjects.begin());
    return best;
}

void Population::printSubject(int id)
{
    Node* p = subjects[id];
    std::cout << "[(" << p[0].id << ", " << p[0].costToReach << ")";
    for (int i = 1; i < subjectSize; i++)
    {
        std::cout << ", (" << p[i].id << ", " << p[i].costToReach << ")";
    }
    std::cout << "]" << std::endl;
}

void Population::printPopulationFenotype()
{
    std::cout << "[";
    if (subjects.size() > 0)
    {
        std::cout << subjects[0][0].costToReach;
    }
    for (int i = 1; i < subjects.size(); i++)
    {
        std::cout << ", " << subjects[i][0].costToReach;
    }
    std::cout << "]" << std::endl;
}



Archipelago::Archipelago(int size)
{
    this->size = size;
    currIslandId = 0;
    islands = (Population**)malloc(sizeof(Population*) * size);
}

Archipelago::~Archipelago()
{
    for (int i = 0; i < size; i++)
    {
        delete(islands[i]);
    }
}

void Archipelago::addIsland(Population *p)
{
    if (currIslandId >= size)
        throw "Brak wolnych wysp";    
    islands[currIslandId] = p;
    currIslandId++;
}

Node *Archipelago::populationEvolution(int generations)
{
    int transferGenerations = generations / 3;
    for (int k = 0; k < 3; k++)
    {
        // std::cerr << k << " iteracja" << std::endl;
        std::vector<Node*> best;
        for (int i = 0; i < size; i++)
            islands[i]->populationEvolution(transferGenerations);
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (i != j)
                    best.push_back(islands[j]->popBest());
        for (int i = 0; i < size; i++)
        {
            for (int j = 1; j < size; j++)
            {    
                islands[i]->addSubject(best[0]);
                best.erase(best.begin());
            }
        }    
    }
    Node* best = islands[0]->getBest();
    for (int i = 1; i < size; i++)
    {
        if (islands[i]->getBest()[0].costToReach < best[0].costToReach)
        {
            best = islands[i]->getBest();
        }
    }
    return best;
}
