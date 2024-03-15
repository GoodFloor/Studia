apps = main test1 test2
all: ${apps}

# Testing
main: main.o splayTree.o
	g++ -o main main.o splayTree.o
main.o: main.cpp
	g++ -c main.cpp
splayTree.o: splayTree.cpp
	g++ -c splayTree.cpp

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
