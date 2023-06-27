# Makefile
all: main

main: treeTemplate.o main.o
	clang++ -o main treeTemplate.o main.o
	
main.o: main.cpp
	clang++ main.cpp -c

treeTemplate.o: treeTemplate.cpp
	clang++ treeTemplate.cpp -c
	
clean:
	rm -f *.o main *.~

necessary:
	rm -f *.o *.~
	
