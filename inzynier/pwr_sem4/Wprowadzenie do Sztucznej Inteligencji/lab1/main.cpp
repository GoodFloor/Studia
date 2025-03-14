#include <iostream>
#include <random>
#include "hashHandler.hpp"
#include "priorityQueue.hpp"
#include "binaryTree.hpp"

using namespace std;

int main(int argc, char const *argv[])
{
    binaryTree* alreadyVisited = new binaryTree();
    priorityQueue* toBeVisited = new priorityQueue();
    unsigned char cost = 0;
    long long startingHash = 0x123456789abcdef0;
    if (argc < 2)
    {
        uniform_int_distribution<> dist = uniform_int_distribution<>(0, 4);
        random_device rd;
        mt19937 gen = mt19937(rd());
        for (int i = 0; i < 50; i++)
        {
            long long prevHash = startingHash;
            long long nextHash = startingHash;
            while (nextHash == prevHash || nextHash == startingHash)
            {
                double rand = dist(gen);
                if (rand < 1)
                {
                    nextHash = moveNorth(startingHash);
                }
                else if (rand < 2)
                {
                    nextHash = moveEast(startingHash);
                }
                else if (rand < 3)
                {
                    nextHash = moveSouth(startingHash);
                }
                else
                {
                    nextHash = moveWest(startingHash);
                }
            }
            startingHash = nextHash;      
        }
        while ((startingHash & (long long)0xf) != 0)
        {

            double rand = dist(gen);
            if (rand < 2)
            {
                startingHash = moveEast(startingHash);
            }
            else
            {
                startingHash = moveSouth(startingHash);
            }
        }
    }
    else 
    {
        startingHash = 0;
        for (int i = 0; i < 16; i++)
        {
            startingHash = startingHash << 4;
            switch (argv[1][i])
            {
            case '0':
                startingHash += 0x0;
                break;
            case '1':
                startingHash += 0x1;
                break;
            case '2':
                startingHash += 0x2;
                break;
            case '3':
                startingHash += 0x3;
                break;
            case '4':
                startingHash += 0x4;
                break;
            case '5':
                startingHash += 0x5;
                break;
            case '6':
                startingHash += 0x6;
                break;
            case '7':
                startingHash += 0x7;
                break;
            case '8':
                startingHash += 0x8;
                break;
            case '9':
                startingHash += 0x9;
                break;
            case 'a':
                startingHash += 0xa;
                break;
            case 'b':
                startingHash += 0xb;
                break;
            case 'c':
                startingHash += 0xc;
                break;
            case 'd':
                startingHash += 0xd;
                break;
            case 'e':
                startingHash += 0xe;
                break;
            case 'f':
                startingHash += 0xf;
                break;
            default:
                return 42;
                break;
            }
        }
    }
    
    
    // startingHash = 0xfe8cab9d26513740;
    // startingHash = moveWest(moveNorth(moveEast(moveWest(moveSouth(moveNorth(moveWest(moveNorth(startingHash))))))));
    // startingHash = 0x9124a37865dbfec0;
    if ((startingHash & (long long)0xf) != 0)
    {
        cout << "Podaj planszę z pustym polem w prawym dolnym rogu!" << endl;
        return 43;
    }
    
    if (!isSolvable(startingHash))
    {
        cout << "Podana plansza jest nierozwiązywalna" << endl;
        return 44;
    }
    // cout << "Ułożenie początkowe: " << hex << startingHash << dec << endl;
    cout << hex << startingHash << dec << " \t";
    // cout << (int)manhattanHeuristic(startingHash) << "\t" << (int)inversionHeuristic(startingHash) << endl;
    int numberOfVisitedStates = 0;
    toBeVisited->add(startingHash, 0, "");

    while (true)
    {
        queueElement* currentHash = toBeVisited->popTop();
        if (currentHash == nullptr)
        {
            cout << "Koniec kolejki" << endl;
            break;
        }
        numberOfVisitedStates++;
        long long hash = currentHash->getHash();
        cost = currentHash->getCost();
        string path = currentHash->getPath();
        // cout << "Odwiedzam: " << endl;
        // printBoard(hash);
        if (hash == 0x123456789abcdef0)
        {
            // cout << "Znaleziono rozwiązanie w " << (int)cost << " ruchach odwiedzając " << numberOfVisitedStates << " stanów." << endl;
            cout << (int)cost << "\t" << numberOfVisitedStates << endl;
            long long pathHash = startingHash;
            // printBoard(pathHash);
            // for (int i = 0; i < cost; i++)
            // {
            //     system("sleep 0.2");
            //     cout << endl;
            //     if (path[i] == 'N')
            //     {
            //         pathHash = moveNorth(pathHash);
            //     }
            //     else if (path[i] == 'E')
            //     {
            //         pathHash = moveEast(pathHash);
            //     }
            //     else if (path[i] == 'S')
            //     {
            //         pathHash = moveSouth(pathHash);
            //     }
            //     else if (path[i] == 'W')
            //     {
            //         pathHash = moveWest(pathHash);
            //     }
            //     printBoard(pathHash);  
            // }
            break;
        }
        
        alreadyVisited->add(hash);
        
        long long nextHash = moveNorth(hash);
        if (nextHash != hash && !alreadyVisited->isInTree(nextHash))
        {
            toBeVisited->add(nextHash, cost + 1, (path + 'N'));
            // cout << "Dodaję: " << endl;
            // printBoard(nextHash);
        }
        nextHash = moveEast(hash);
        if (nextHash != hash && !alreadyVisited->isInTree(nextHash))
        {
            toBeVisited->add(nextHash, cost + 1, (path + 'E'));
            // cout << "Dodaję: " << endl;
            // printBoard(nextHash);
        }
        nextHash = moveSouth(hash);
        if (nextHash != hash && !alreadyVisited->isInTree(nextHash))
        {
            toBeVisited->add(nextHash, cost + 1, (path + 'S'));
            // cout << "Dodaję: " << endl;
            // printBoard(nextHash);
        }
        nextHash = moveWest(hash);
        if (nextHash != hash && !alreadyVisited->isInTree(nextHash))
        {
            toBeVisited->add(nextHash, cost + 1, (path + 'W'));
            // cout << "Dodaję: " << endl;
            // printBoard(nextHash);
        }
        delete(currentHash);
    }
    // printBoard(hash);
    
    // alreadyVisited->add(5);
    // alreadyVisited->add(1);
    // alreadyVisited->add(10);
    // alreadyVisited->add(7);
    // alreadyVisited->add(12);
    // cout << alreadyVisited->isInTree(12) << endl;

    // string text = "a\n";
    // text += 'b';
    // char* tekst = (char*)malloc(sizeof(char) * 5);
    // tekst[0] = 'a';
    // tekst[1] = 'b';
    // tekst[2] = 'c';
    // // text = tekst;
    // cout << text;
    // unsigned int h = 0xf0000000;
    // cout << hex << h << endl;
    // h = h >> 4;
    // cout << h << endl;
    // printBoard(hash);
    // // cout << findBlank(hash) << endl;
    // // printBoard(swapValues(hash, 0, 5));
    // // printBoard(moveEast(hash));
    // cout << (int)heuristic(hash) << endl;
    delete(alreadyVisited);
    delete(toBeVisited);
    return 0;
}
