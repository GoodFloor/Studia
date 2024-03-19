#ifndef GeneticAlgorithm
#define GeneticAlgorithm

#include <vector>
#include "Node.hpp"

class Population
{
private:
    int populationSize;
    int subjectSize;
    std::vector<Node*> subjects;
    Node* halfbreed(Node* father, Node* mother);
    void reducePopulation();
    void crossbreedPopulation();
    void mutateRandom(int howMany);
    void adultSubject(Node* p);
public:
    Population(int populationSize, int subjectSize);
    ~Population();
    void addSubject(Node* permutation);
    Node* populationEvolution(int generations);
    Node* getBest();
    Node* popBest();
    void printSubject(int id);
    void printPopulationFenotype();
};

class Archipelago
{
private:
    int size;
    int currIslandId;
    Population** islands;
public:
    Archipelago(int size);
    ~Archipelago();
    void addIsland(Population* p);
    Node* populationEvolution(int generations);
};

#endif
