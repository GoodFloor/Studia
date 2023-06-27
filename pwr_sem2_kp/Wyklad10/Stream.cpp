#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main () {

	// Tworzenie pliku i otwieranie strumienia do zapisu
  ofstream myfile ("example.txt");
  if (myfile.is_open())
  {
    myfile << "This is a line.\n";
    myfile << "This is another line.\n";
    myfile.close();
  }
  else cout << "Unable to open file";

  return 0;
}