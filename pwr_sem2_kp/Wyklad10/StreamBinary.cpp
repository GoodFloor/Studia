#include <iostream>
#include <fstream>
using namespace std;

int main () {
  streampos begin,end;
  // Otwieranie strumienia do odczytu
  ifstream myfile ("mydata.bin", ios::binary);
  // Zwraca aktualna pozycje strumienia
  begin = myfile.tellg();
  // Ustawia pozycje na koniec pliku
  myfile.seekg (0, ios::end);
  end = myfile.tellg();
  myfile.close();
  cout << "size is: " << (end-begin) << " bytes.\n";
  return 0;
}