apps = main test1 test2
all: ${apps}

# Testing
main: main.o binarySearchTree.o
	g++ -o main main.o binarySearchTree.o
main.o: main.cpp
	g++ -c main.cpp
binarySearchTree.o: binarySearchTree.cpp
	g++ -c binarySearchTree.cpp

# Array generator
test1: test1.o rng.o
	g++ -o test1 test1.o rng.o
test1.o: test1.cpp
	g++ -c test1.cpp
test2: test2.o rng.o
	g++ -o test2 test2.o rng.o
test2.o: test2.cpp
	g++ -c test2.cpp

# Dependencies
rng.o: rng.cpp
	g++ -c rng.cpp

# Cleaning
exe: ${apps}
	rm -f *.o *.~
clean: 
	rm -f *.o *.~ ${apps}
