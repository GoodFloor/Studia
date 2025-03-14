# Makefile
all: main

main: figury.o main.o
	clang++ -o main figury.o main.o
	
main.o: main.cpp
	clang++ main.cpp -c

figury.o: figury.cpp
	clang++ figury.cpp -c
	
clean:
	rm -f *.o main *.~

necessary:
	rm -f *.o *.~
	
