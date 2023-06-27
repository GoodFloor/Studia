# Makefile
all: zad2

zad2: f.o rozwiazanie.o zad2.o
	clang -o zad2 f.o rozwiazanie.o zad2.o -lm
	
zad2.o: zad2.c
	clang zad2.c -c -Wextra -std=c11 --pedantic
	
rozwiazanie.o: rozwiazanie.c 
	clang rozwiazanie.c -c -Wextra -std=c11 --pedantic

f.o: f.c
	clang f.c -c -Wextra -std=c11 --pedantic
	
clean:
	rm -f *.o zad2 *.~
