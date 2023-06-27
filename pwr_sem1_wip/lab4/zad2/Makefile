# Makefile
all: zad2

zad2: tablice.o zad2.o
	clang -o zad2 tablice.o zad2.o -lm
	
zad2.o: zad2.c
	clang zad2.c -c -Wextra -std=c11 --pedantic

tablice.o: tablice.c
	clang tablice.c -c -Wextra -std=c11 --pedantic
	
clean:
	rm -f *.o zad2 *.~

necessary:
	rm -f *.o *.~
	