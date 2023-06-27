 
#include <iostream>
#include <exception>
using namespace std;

/// Metoda oznaczona jako taka ktora nie wyrzuca wyjatkow.
/// Program zostanie zakonczony.
void fun1() noexcept {throw 44;}
/// Metoda moze wyrzucac wyjatek
void fun2() noexcept(false) { throw 55; } 
void fun3()  { throw 66; } 
 
int main() 
{
   
   try{
    fun1(); 
    fun2(); 
   }
   catch(int s)
   {
       cout << "Caught:"<< s << endl;
   }

}