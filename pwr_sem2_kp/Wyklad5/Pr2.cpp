#include<iostream>
using namespace std;

int main(int argc, char* argv[]) {
	try
	{
		if (argc==1) throw "Pusto";
		for (int i=0; i<argc; i++){
		  cout << argv[i] << endl;
		}
	}
	catch (const char* msg) {
		cerr << "Jest " << msg << endl;
	 }
}