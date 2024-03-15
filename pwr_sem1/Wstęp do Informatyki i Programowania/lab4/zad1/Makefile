# Makefile
all: zad1

zad1: match.o zad1.o
	clang -o zad1 match.o zad1.o
	
zad1.o: zad1.c
	clang zad1.c -c -Wextra -std=c11 --pedantic
	
match.o: match.c
	clang match.c -c -Wextra -std=c11 --pedantic
	
clean:
	rm -f *.o zad1 *.~

necessary:
	rm -f *.o *.~