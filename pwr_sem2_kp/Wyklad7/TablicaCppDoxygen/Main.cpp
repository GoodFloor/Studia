#include<iostream>
#include<string>

#include "TablicaBool.h"

using namespace std;

/** Główna funkcja programu testującego klasę {@link TablicaBool TablicaBool}
 */
int main(int argc, char* argv[] ) {
  TablicaBool *t;
  long n = 2000000000;

  try { 
    t = new TablicaBool(n); 
  }
  catch(MojException& w) {
    cout << w.what() << endl; return 1;
  }
  try { t->setValue(20,true); }
  catch(MojException& w) {
    cout << w.what() << endl;
  }
  delete t;
}

