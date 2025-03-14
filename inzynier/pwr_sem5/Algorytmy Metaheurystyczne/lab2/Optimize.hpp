#include "Node.hpp"

/**
 * Wykonuje algorytm Symulowanego wyżarzania dla permutacji p
 * \param p Permutacja początkowa
 * \param n Długość permutacji
 * \return Koszt najlepszego znalezionego rozwiązania (zapisanego w p)
*/
int SimulatedAnnealing(Node* p, int n);

/**
 * Wykonuje algorytm TabuSearch na początkowej permutacji p
 * \param p Permutacja początkowa
 * \param n Długość permutacji
 * \return Koszt najlepszego znalezionego rozwiązania (zapisanego w p)
*/
int TabuSearch(Node* p, int n);
