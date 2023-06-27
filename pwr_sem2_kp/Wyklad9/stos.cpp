#include<iostream>
#include<string>

using namespace std;

// Klasa element stosu (typ generyczny T)
template<typename T>
class ElemStosu {
  public:
    T elem;
    ElemStosu<T>* nast;
    ElemStosu(T elem, ElemStosu<T>* nast)
    {
      this->elem = elem;
      this->nast = nast;
    }
};

// Klasa Stosu (typ generyczny T)
template<typename T>
class Stos {
  private:
    ElemStosu<T>* wierzch;
  public:
    Stos() { wierzch = NULL; }
    bool empty() { return wierzch==NULL; }
    void push(T elem) { wierzch = new ElemStosu<T>(elem,wierzch); }
    T pop() {
      if( empty() ) throw (string)"PustyStos!";
      T wynik = wierzch->elem;
      wierzch = wierzch->nast;
      return wynik;
    }
};

int main(int argc, char* argv[]) {
  // Tworzenie dwoch stosow
  Stos<int> a;
  Stos<string> b;

  a.push(2); a.push(3);
  try {
    cout << "Wypisywanie elementow stosu typu int:" << endl;
    cout << a.pop() << " " << a.pop() << endl;
    cout << a.pop() << " " << a.pop() << endl;
  }
  catch(string e) {
    cout << e << endl;
  }
  b.push("Maciek"); b.push("Ala");
  try {
    cout << "Wypisywanie elementow stosu typu string:" << endl;
    while( !b.empty() ) 
      cout << b.pop() << endl;
  }
  catch(string e) {
    cout << e << endl;
  }
}
