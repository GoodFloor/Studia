#include<iostream>
#include<list>
#include<string>

using namespace std;

int main(int argc, char* argv[]) {

	//Tworznie listy elementow typu string
    list<string> *nazwiska = new list<string>();
	// Wstawianie elementow
    nazwiska->push_back("Nowak"); nazwiska->push_back("Kowalski"); 
    nazwiska->push_back("Bielecki"); nazwiska->push_back("Adamski");
    nazwiska->push_back("Kowalski");

	// Przechodznie po elementach za pomoca iteratra
  cout << "Prechodzenie po elementach" << endl;
  list<string>::iterator it;
  for(it=nazwiska->begin(); it!=nazwiska->end(); it++) 
    cout << " - " << *it << endl;
    
	// Sortowanie listy
  cout << "Lista posortowana" << endl;
    nazwiska->sort(); 
    for(it=nazwiska->begin(); it!=nazwiska->end(); it++) 
      cout << " + " << *it << endl;
    
	// Odwracanie listy
    cout << "Lista odwrocona" << endl;
    nazwiska->reverse(); 
    for(it=nazwiska->begin(); it!=nazwiska->end(); it++) 
      cout << " - " << *it << endl;
      
	// Usuwanie elementow z listy
   cout << "Po usunieciu Kowalskiego" << endl;
    nazwiska->remove("Kowalski"); 
    for(it=nazwiska->begin(); it!=nazwiska->end(); it++) 
      cout << " + " << *it << endl;
    delete(nazwiska);
}
