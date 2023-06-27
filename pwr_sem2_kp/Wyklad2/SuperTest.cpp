#include<iostream>

using namespace std;

class Baza {
   public:
  int a;
  Baza() { a=0; cout << "Domyslny konstruktor klasy Baza" << endl; }
  Baza(int i) { a=i; cout << "Konstruktor klasy Baza" << endl; }
  virtual ~Baza() { cout << "Destruktor klasy Baza" << endl; }
  virtual void m1() { cout << "Baza.m1()" << endl; }
  virtual void m2() { cout << "Baza.m2()" << endl; }
};

class Potomna : public Baza {
  public:
  // Wywolywanie konstruktora bazowego
  Potomna() : Baza(0) { cout << "Domyslny konstruktor klasy Potomna" << endl; }
  Potomna(int i) { a=i; cout << "Konstruktor klasy Potomna" << endl; }
  ~Potomna() { cout << "Destruktor klasy Potomna" << endl; }
  void m3() { cout << "Potomna.m3()" << endl; Baza::m2(); }
  void m2() { cout << "Potomna.m2()" << endl; }
};

int main(int argc, char* argv[]) {

    cout << "new a ------" << endl;
    Baza* a = new Potomna();
    cout << "new b ------" << endl;
    Potomna* b = new Potomna(1);
    cout << "new c ------" << endl;;
    void* c = new Baza();

	// m3 nie jest metoda klasy Baza
//    a->m3(); // błąd kompilatora

	// Niepoprawnie. 
	// Potomna* d = new Baza();

    cout << "b.m1() ------" << endl;
    b->m1();

	// m2 wywola sie z klasy Potomna, bo obiekt a jest tworzony na podstawie Potomna. Ale przy 
	// zalozeniu ze m2 jest zdefiniowana jako virtual
    cout << "a.m2() ------" << endl;
    a->m2();

	// Poprawnie, bo a jest typu Potomna
    ((Potomna*)a)->m2();

    cout << "b.m3() ------" << endl;
    b->m3();

    cout << "a ------" << endl;
    delete a;
    cout << "b ------" << endl;
    delete b; 
    cout << "c ------" << endl;
    delete (Baza*)c;

	cout << "new a1 ------" << endl;
	Baza a1;

}

