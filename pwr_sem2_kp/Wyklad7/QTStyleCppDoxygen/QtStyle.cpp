//! Klasa testowa
/*! dokladniejszy opis */

class Test
{
public:


	enum TEnum
	{
		TVal1, 
		TVal2, 
		TVal3 
	}

	*enumPtr,

	enumVar;

	Test();

	~Test();

	//! jakas funkcja
	/*!
	\param a pierwsza zmienna

	*/
	int testMe(int a,const char *s);


	/// Funkcja wirtualna
	virtual void testMeToo(char c1,char c2) = 0;



	int publicVar;

}