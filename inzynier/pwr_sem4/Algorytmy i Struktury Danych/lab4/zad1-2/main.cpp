#include <iostream>
#include "binarySearchTree.hpp"

using namespace std;

int main(int argc, char const *argv[])
{
    bool isDisplayed = argc == 2 ? false : true;
    binarySearchTree* tree = new binarySearchTree();
    int n;
    cin >> n;
    double avgTreeHeight = 0;
    int maxNumberOfComparisons = 0;
    int maxNumberOfAccesses = 0;
    int maxHeight = 0;
    for (int i = 0; i < n; i++)
    {
        int tempNOC = tree->numberOfComparisons;
        int tempNOA = tree->getNumberOfAccesses();
        int x;
        cin >> x;
        tree->insertKey(x);
        tempNOC = tree->numberOfComparisons - tempNOC;
        if (tempNOC > maxNumberOfComparisons)
        {
            maxNumberOfComparisons = tempNOC;
        }
        tempNOA = tree->getNumberOfAccesses() - tempNOA;
        if (tempNOA > maxNumberOfAccesses)
        {
            maxNumberOfAccesses = tempNOA;
        }
        int currHeight = tree->getHeight();
        avgTreeHeight = i / (i + 1.0) * avgTreeHeight + 1.0 / (i + 1.0) * currHeight;
        if (currHeight > maxHeight)
        {
            maxHeight = currHeight;
        }
        if (isDisplayed)
        {
            cout << "Insert(" << x << ")" << endl;
            tree->printTree();
            cout << "Wysokość drzewa: " << tree->getHeight() << endl;
        }
    }
    for (int i = 0; i < n; i++)
    {
        int tempNOC = tree->numberOfComparisons;
        int tempNOA = tree->getNumberOfAccesses();
        int x;
        cin >> x;
        tree->deleteKey(x);
        tempNOC = tree->numberOfComparisons - tempNOC;
        if (tempNOC > maxNumberOfComparisons)
        {
            maxNumberOfComparisons = tempNOC;
        }
        tempNOA = tree->getNumberOfAccesses() - tempNOA;
        if (tempNOA > maxNumberOfAccesses)
        {
            maxNumberOfAccesses = tempNOA;
        }
        avgTreeHeight = (n + i) / (n + i + 1.0) * avgTreeHeight + 1.0 / (n + i + 1.0) * tree->getHeight();
        if (isDisplayed)
        {
            cout << "Delete(" << x << ")" << endl;
            tree->printTree();
            cout << endl;
        }
    }
    if (isDisplayed)
    {
        cout << "Liczba porównań między kluczami: " << tree->numberOfComparisons << endl;
        cout << "Maksymalna liczba porównań między kluczami dla jednej operacji: " << maxNumberOfComparisons << endl;
        cout << "Liczba odczytów i podstawień wskaźników łączących elementy struktury drzewa: " << tree->getNumberOfAccesses() << endl;
        cout << "Maksymalna liczba odczytów i podstawień wskaźników łączących elementy struktury drzewa dla jednej operacji: " << maxNumberOfAccesses << endl;
        cout << "Średnia wysokość drzewa po każdej operacji: " << avgTreeHeight << endl;
        cout << "Maksymalna wysokość drzewa w trakcie całego testu: " << maxHeight << endl;
    }
    else
    {
        // n    liczbaPorównań  maksLiczbaPorównańDlaJednejOperacji liczbaOdczytówIPodstawieńWskaźników maksLiczbaOdczytówIPodstawień   średniaWysokośćDrzewaPoKażdejOperacji   maksWysokośćDrzewa
        cout << n << "\t" << tree->numberOfComparisons << "\t" << maxNumberOfComparisons << "\t" << tree->getNumberOfAccesses() << "\t" << maxNumberOfAccesses << "\t" << avgTreeHeight << "\t" << maxHeight << endl;
    }    
    delete(tree);
    return 0;
}
