#include <iostream>
#include <string>
#include "treeTemplate.cpp"

using namespace std;

/**
 * @brief główna funkcja programu
 * 
 * @return int kod zakończenia
 */
int main()
{
	Tree<int> integerTree;
	Tree<double> doubleTree;
	Tree<string> stringTree;

////////////////////////// TestTree - drzewo domyślne dla typu INTEGER
	integerTree.insertElement(10);
	integerTree.insertElement(7);
	integerTree.insertElement(5);
	integerTree.insertElement(11);
	integerTree.insertElement(3);
	integerTree.insertElement(13);
	integerTree.insertElement(9);
	integerTree.insertElement(12);
	integerTree.insertElement(20);
	integerTree.insertElement(17);
	integerTree.insertElement(14);
	integerTree.insertElement(1);
	integerTree.insertElement(2);
	integerTree.insertElement(0);
	integerTree.insertElement(6);
	integerTree.insertElement(50);
	integerTree.insertElement(18);
	integerTree.insertElement(21);
	integerTree.insertElement(51);
/////////////////////////////

	string inputLine = "";
	string variableType = "INTEGER";
	while (inputLine != "exit") {
		cout << "Podaj jedną z dostępnych komend(search/insert/delete/draw/exit) lub typ drzewa na którym chcsesz pracować(int/double/string): ";
		cin >> inputLine;
		if(inputLine == "int") {
			variableType = "INTEGER";
			cout << "Pracujesz teraz na drzewie typu INTEGER" << endl;
		}
		else if(inputLine == "double") {
			variableType = "DOUBLE";
			cout << "Pracujesz teraz na drzewie typu DOUBLE" << endl;
		}
		else if(inputLine == "string") {
			variableType = "STRING";
			cout << "Pracujesz teraz na drzewie typu STRING" << endl;
		}
		else if(inputLine == "search") {
			cout << "Podaj wartość którą chcesz wyszukać: ";
			if(variableType == "INTEGER") {
				int x;
				cin >> x;
				if(integerTree.search(x))
					cout << "Podana wartość istnieje w drzewie" << endl;
				else 
					cout << "Podana wartość nie istnieje w drzewie" << endl;
			}
			else if(variableType == "DOUBLE") {
				double x;
				cin >> x;
				if(doubleTree.search(x))
					cout << "Podana wartość istnieje w drzewie" << endl;
				else 
					cout << "Podana wartość nie istnieje w drzewie" << endl;
			}
			else if(variableType == "STRING") {
				string x;
				cin >> x;
				if(stringTree.search(x))
					cout << "Podana wartość istnieje w drzewie" << endl;
				else 
					cout << "Podana wartość nie istnieje w drzewie" << endl;
			}
			else {
				cout << "Błędny typ danych(" << variableType << "), wybierz inny typ danych." << endl;
			}
		}
		else if(inputLine == "insert") {
			cout << "Podaj wartość którą chcesz wprowadzić: ";
			if(variableType == "INTEGER") {
				int x;
				cin >> x;
				if(integerTree.insertElement(x))
					cout << "Wartość pomyślnie wprowadzona do drzewa" << endl;
				else 
					cout << "Podana wartość już istniała w drzewie" << endl;
			}
			else if(variableType == "DOUBLE") {
				double x;
				cin >> x;
				if(doubleTree.insertElement(x))
					cout << "Wartość pomyślnie wprowadzona do drzewa" << endl;
				else 
					cout << "Podana wartość już istniała w drzewie" << endl;
			}
			else if(variableType == "STRING") {
				string x;
				cin >> x;
				if(stringTree.insertElement(x))
					cout << "Wartość pomyślnie wprowadzona do drzewa" << endl;
				else 
					cout << "Podana wartość już istniała w drzewie" << endl;
			}
			else {
				cout << "Błędny typ danych(" << variableType << "), wybierz inny typ danych." << endl;
			}
		}
		else if(inputLine == "delete") {
			cout << "Podaj wartość którą chcesz usunąć: ";
			if(variableType == "INTEGER") {
				int x;
				cin >> x;
				integerTree.deleteElement(x);
			}
			else if(variableType == "DOUBLE") {
				double x;
				cin >> x;
				doubleTree.deleteElement(x);
			}
			else if(variableType == "STRING") {
				string x;
				cin >> x;
				stringTree.deleteElement(x);
			}
			else {
				cout << "Błędny typ danych(" << variableType << "), wybierz inny typ danych." << endl;
			}
		}
		else if(inputLine == "draw") {
			if(variableType == "INTEGER") {
				integerTree.draw();
			}
			else if(variableType == "DOUBLE") {
				doubleTree.draw();
			}
			else if(variableType == "STRING") {
				stringTree.draw();
			}
			else {
				cout << "Błędny typ danych(" << variableType << "), wybierz inny typ danych." << endl;
			}
		}
		else if(inputLine == "exit") {
			cout << "Do widzenia" << endl;
		}
		else {
			cout << "Podana komenda jest niepoprawna" << endl;
		}
		
	}

	return 0;
}
 
