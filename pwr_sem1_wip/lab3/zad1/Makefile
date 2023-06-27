# Makefile
all: zad1

zad1: palindrom.o zad1.o
	clang -o zad1 palindrom.o zad1.o
	
zad1.o: zad1.c
	clang zad1.c -c -Wextra -std=c11 --pedantic
	
palindrom.o: palindrom.c
	clang palindrom.c -c -Wextra -std=c11 --pedantic
	
clean:
	rm -f *.o zad1 *.~
