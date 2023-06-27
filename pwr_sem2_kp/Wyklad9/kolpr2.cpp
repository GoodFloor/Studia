#include <iostream>
#include <queue>
#include <vector>
#include <string>

using namespace std;

class Osoba
{
public:
	int wiek;
	string nazwisko;

	Osoba(int wiek, string nazwisko)
	{
		this->wiek = wiek;
		this->nazwisko = nazwisko;
	}
};

struct OsobaKomp
{
	bool operator()(const Osoba &o1, const Osoba &o2) const
	{
		if (o1.wiek != o2.wiek)
			return o1.wiek < o2.wiek;
		else
			return o1.nazwisko < o2.nazwisko;
	}

};

int main(int argc, char ** argv)
{
	Osoba os1(21, "Kowalski");
	Osoba os2(34, "Kowal");
	Osoba os3(28, "Nowak");
	Osoba os4(21, "Grzyb");
	

	priority_queue<Osoba, vector<Osoba>, OsobaKomp> kolejka;

	kolejka.push(os1);
	kolejka.push(os2);
	kolejka.push(os3);
	kolejka.push(os4);


	cout << "Prechodzenie po elementach" << endl;

	while (!kolejka.empty())
	{
		Osoba o = kolejka.top();
		cout << "wiek " << o.wiek << " nazwisko " << o.nazwisko << endl;
		kolejka.pop();
	}

	system("pause");

	return 0;
}