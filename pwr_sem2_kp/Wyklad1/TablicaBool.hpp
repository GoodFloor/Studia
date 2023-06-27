#pragma once

#include <string>

using namespace std;

class TablicaBool {
  private:
    bool *t;
  public:
    bool getValue( int i ) throw(string); 
    void setValue( int i, bool b ) throw(string); 
    TablicaBool( int i ) throw(string); 
    ~TablicaBool();
};
