# Makefile
all: main

main: WierszTrojkataPascala.o parsowanie.o main.o
	clang++ -o main WierszTrojkataPascala.o parsowanie.o main.o
	
main.o: main.cpp
	clang++ main.cpp -c

parsowanie.o: parsowanie.cpp
	clang++ parsowanie.cpp -c

WierszTrojkataPascala.o: WierszTrojkataPascala.cpp
	clang++ WierszTrojkataPascala.cpp -c
	
clean:
	rm -f *.o main *.~

necessary:
	rm -f *.o *.~
	
