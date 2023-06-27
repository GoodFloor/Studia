#ifndef __TablicaBool_h__
#define __TablicaBool_h__

#include<exception>

using namespace std;

/**
 *  Klasa definiująca wyjątek wykorzystywany w klasie {\link TablicaBool TablicaBool}
 */
class MojException : public exception {
  private:
    /** Przechowywanie komunikatu błędu */
    string s;
  public:
    /** Konstruktor podstawowy 
     *  \param ss komunikat błędu
     */
    MojException(string ss) throw();
    /** Destruktor 
     */
    virtual ~MojException() throw();
    /** Wypisywanie komunikatu błędu
     */
    virtual const char* what() const throw();
};


/** 
 *  Klasa definiująca tablicę boolowską

 */
class TablicaBool {
  private:
    /** Wskaźnik na tablicę przechowującą wartości */
    bool* t;
    /** Długość tablicy */
    long n;
  public:
    /** Funkcja zwracająca wartość elementu tablicy 
     *  \param i indeks zwracanego elementu
     *  \return wartość zwracanego elementu
     *  \throws MojException wyjątek w przypadku błędnych indeksów 
     */
    bool getValue( long i ) throw (MojException);

    /** Funkcja ustawiająca wartość elementu tablicy 
     *  \param i indeks ustawianego elementu
     *  \param b wartość ustawianego elementu
     *  \throws MojException wyjątek w przypadku błędnych indeksów
     */
    void setValue( long i, bool b ) throw (MojException);

    /** Podstawowy konstruktor
     *  \param n deklarowana wielkość tablicy
     *  \throws MojException wyjątek w przypadku błędnego rozmiaru
     */
    TablicaBool( long n ) throw (MojException); 

    /** Destruktor */
    ~TablicaBool();
};

#endif

