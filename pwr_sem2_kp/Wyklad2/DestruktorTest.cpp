#include <iostream>
using namespace std;

class Test {
  public:
    int a;
    Test( int a ) { this->a=a; cout << "Utworzono " << a << endl; }
    ~Test() { cout << "Zniszczono " << a << endl; }

};
int main(int argc, char* argv[]) {

  Test a(1);
  Test* b = new Test(2);

  delete b;
};

