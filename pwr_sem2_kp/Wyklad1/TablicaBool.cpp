#include "TablicaBool.hpp"

#include<iostream>
#include<string>

using namespace std;

bool TablicaBool::getValue( int i ) throw(string) {
      if( (i<0) || (i>=(int)sizeof t) ) throw (string)"Zly indeks tablicy";
      return t[i];
}

void TablicaBool::setValue( int i, bool b ) throw(string) {
      if( (i<0) || (i>=(int)sizeof t) ) throw (string)"Zly indeks tablicy";
      t[i] = b;
}

TablicaBool::TablicaBool( int i ) throw(string) { 
      if(i<=0) throw (string)"Zly rozmiar tablicy";
      t = new bool(i); 
}

TablicaBool::~TablicaBool() { 
      delete t; 
}
