#include <iostream>
#include <exception>
using namespace std;

class MyException : public exception {
public:
	const char * what () const noexcept {
      return "C++ Exception";
   }
};
 
int main() {
   try {
      throw MyException();
	   throw exception();
   }catch(MyException& e) {
      std::cout << "MyException caught" << std::endl;
      std::cout << e.what() << std::endl;
   } catch(std::exception& e) {
      //Inne wyjatki
	   std::cout << e.what() << std::endl;
   }
}