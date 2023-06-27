#include<string>
#include<exception>

#include "TablicaBool.h"

using namespace std;

//******************************************************************************

MojException::MojException(string ss) : s(ss) {}

MojException::~MojException() throw() {}

const char*MojException::what() const throw() { return s.c_str(); }

//******************************************************************************

bool TablicaBool::getValue( long i ) throw (MojException) {
  if( (i<0) || (i>=n) ) throw MojException("Zly indeks tablicy");
  return t[i];
}

void TablicaBool::setValue( long i, bool b ) throw (MojException) {
  if( (i<0) || (i>=n) ) throw MojException("Zly indeks tablicy");
  t[i] = b;
}

TablicaBool::TablicaBool( long n ) throw (MojException) {
  if(n<=0) throw MojException("Zly rozmiar tablicy");
  try {
    t = new bool[n];
  }
  catch(bad_alloc& w) {
    throw MojException("Za malo pamieci");
  }
  this->n=n;
}

TablicaBool::~TablicaBool() { delete [] t; }

