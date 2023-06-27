#include <queue>
#include <iostream>
#include <conio.h>

using namespace std;

int main()
{
	// Tworznie kolejki
    queue < int > kolejkaLiczb;
    
    cout << "Podaj liczbe: ";
    int liczba;
    cin >> liczba;
    
	// Wstawianie do kolejki
    kolejkaLiczb.push( liczba );
    kolejkaLiczb.push( 222 );
    kolejkaLiczb.push( 555 );
    
	cout << "W kolejce znajduje sie " << kolejkaLiczb.size() << " liczb." << endl;

    cout << "Pierwsza liczba w kolejce to: " << kolejkaLiczb.front() << endl;
    kolejkaLiczb.front() *= 2;
    
    cout << "Zmodyfikowalem pierwsza liczbe w kolejce" << endl;
    cout << "Pierwsza liczba w kolejce to: " << kolejkaLiczb.front() << endl;
    
    kolejkaLiczb.front() = 1234;
    cout << "Zmodyfikowalem pierwsza liczbe w kolejce" << endl;
    
    cout << "Pierwsza liczba w kolejce to: " << kolejkaLiczb.front() << endl;

	cout << "Ostatnia liczba w kolejce to: " << kolejkaLiczb.back() << endl;

	cout << "Usuwam liczbe z kolejki "  << endl;
	kolejkaLiczb.pop();

	cout << "W kolejce znajduje sie " << kolejkaLiczb.size() << " liczb." << endl;
	    
	cout << "Pierwsza liczba w kolejce to: " << kolejkaLiczb.front() << endl;
    
    
    getch();
    return 0;
}