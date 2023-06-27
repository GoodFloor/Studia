#include<iostream>
#include<string>

#include "TablicaBool.hpp"

using namespace std;

int main(int argc, char* argv[] ) {
  TablicaBool *t;
  int n = 2;

  try { t = new TablicaBool(n); }
  catch(string w) { cout << w << endl; return 1; }

  try { t->setValue(20,true); }
  catch(string w) { cout << w << endl; }

  delete t;

  n = 0;
  try { t = new TablicaBool(n); t->setValue(20,true); }
  catch(string w) { cout << w << endl; return 1; }

  delete t;
}
