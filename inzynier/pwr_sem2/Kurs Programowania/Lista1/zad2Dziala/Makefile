# Makefile
all: zad2

zad2: LiczbyPierwsze.o parsowanie.o zad2.o
	clang++ -o zad2 LiczbyPierwsze.o parsowanie.o zad2.o
	
zad2.o: zad2.cpp
	clang++ zad2.cpp -c

parsowanie.o: parsowanie.cpp
	clang++ parsowanie.cpp -c

LiczbyPierwsze.o: LiczbyPierwsze.cpp
	clang++ LiczbyPierwsze.cpp -c
	
clean:
	rm -f *.o zad2 *.~

necessary:
	rm -f *.o *.~
	