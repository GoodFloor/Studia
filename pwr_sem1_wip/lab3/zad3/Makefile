# Makefile
all: zad3

zad3: czyWzgledniePierwsze.o phi.o zad3.o
	clang -o zad3 czyWzgledniePierwsze.o phi.o zad3.o
	
zad3.o: zad3.c
	clang zad3.c -c -Wextra -std=c11 --pedantic
	
phi.o: phi.c
	clang phi.c -c -Wextra -std=c11 --pedantic

czyWzgledniePierwsze.o: czyWzgledniePierwsze.c
	clang czyWzgledniePierwsze.c -c -Wextra -std=c11 --pedantic
	
clean:
	rm -f *.o zad3 *.~
