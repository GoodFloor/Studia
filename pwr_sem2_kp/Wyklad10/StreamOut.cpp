#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main () {
  string line;
  // Otwieranie strumienia do odczytu
  ifstream myfile ("example.txt");
  if (myfile.is_open())
  {
    while ( getline (myfile,line) )
    {
      cout << line << '\n';
    }
    myfile.close();
  }

  else cout << "Unable to open file"; 

  return 0;
}