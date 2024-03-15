#include <iostream>
#include "splayTree.hpp"

using namespace std;

double addToAVG(double avg, int newAddition, int avgWeight)
{
    return avgWeight / (avgWeight + 1.0) * avg + 1.0 / (avgWeight + 1.0) * newAddition;
}

int main(int argc, char const *argv[])
{
    bool isDisplayed = argc == 2 ? false : true;
    splayTree* tree = new splayTree();
    int n;
    cin >> n;
    isDisplayed = n > 50 ? false : isDisplayed;
    double avgTreeHeight = 0;
    double avgNumberOfComparisons = 0;
    double avgNumberOfAccesses = 0;
    int maxHeight = 0;
    int maxNumberOfComparisons = 0;
    int maxNumberOfAccesses = 0;
    for (int i = 0; i < n; i++)
    {
        int x;
        cin >> x;
        tree->resetStatistics();
        tree->insertKey(x);
        int tempNOC = tree->getNumberOfComparisons();
        int tempNOA = tree->getNumberOfAccesses();
        int currHeight = tree->getHeight();
        if (tempNOC > maxNumberOfComparisons)
        {
            maxNumberOfComparisons = tempNOC;
        }
        if (tempNOA > maxNumberOfAccesses)
        {
            maxNumberOfAccesses = tempNOA;
        }
        if (currHeight > maxHeight)
        {
            maxHeight = currHeight;
        }
        avgTreeHeight = addToAVG(avgTreeHeight, currHeight, i);
        avgNumberOfComparisons = addToAVG(avgNumberOfComparisons, tempNOC, i);
        avgNumberOfAccesses = addToAVG(avgNumberOfAccesses, tempNOA, i);
        if (isDisplayed)
        {
            cout << "Insert(" << x << ")" << endl;
            tree->printTree();
            cout << "Wysokość drzewa: " << tree->getHeight() << endl;
        }
    }
    for (int i = 0; i < n; i++)
    {
        int x;
        cin >> x;
        tree->resetStatistics();
        tree->deleteKey(x);
        int tempNOC = tree->getNumberOfComparisons();
        int tempNOA = tree->getNumberOfAccesses();
        int currHeight = tree->getHeight();
        if (tempNOC > maxNumberOfComparisons)
        {
            maxNumberOfComparisons = tempNOC;
        }
        if (tempNOA > maxNumberOfAccesses)
        {
            maxNumberOfAccesses = tempNOA;
        }
        avgTreeHeight = addToAVG(avgTreeHeight, currHeight, i + n);
        avgNumberOfComparisons = addToAVG(avgNumberOfComparisons, tempNOC, i + n);
        avgNumberOfAccesses = addToAVG(avgNumberOfAccesses, tempNOA, i + n);
        if (isDisplayed)
        {
            cout << "Delete(" << x << ")" << endl;
            tree->printTree();
            cout << endl;
        }
    }
    if (isDisplayed)
    {
        cout << "Średnia liczba porównań między kluczami dla jednej operacji: " << avgNumberOfComparisons << endl;
        cout << "Maksymalna liczba porównań między kluczami dla jednej operacji: " << maxNumberOfComparisons << endl;
        cout << "Średnia liczba odczytów i podstawień wskaźników łączących elementy struktury drzewa dla jednej operacji: " << avgNumberOfAccesses << endl;
        cout << "Maksymalna liczba odczytów i podstawień wskaźników łączących elementy struktury drzewa dla jednej operacji: " << maxNumberOfAccesses << endl;
        cout << "Średnia wysokość drzewa po każdej operacji: " << avgTreeHeight << endl;
        cout << "Maksymalna wysokość drzewa w trakcie całego testu: " << maxHeight << endl;
    }
    else
    {
        // n    średniaLiczbaPorównań  maksLiczbaPorównańDlaJednejOperacji średniaLiczbaOdczytówIPodstawieńWskaźników maksLiczbaOdczytówIPodstawień   średniaWysokośćDrzewaPoKażdejOperacji   maksWysokośćDrzewa
        cout << n << "\t" << avgNumberOfComparisons << "\t" << maxNumberOfComparisons << "\t" << avgNumberOfAccesses << "\t" << maxNumberOfAccesses << "\t" << avgTreeHeight << "\t" << maxHeight << endl;
    }
    delete(tree);
    return 0;
}
