apps = ascendingArray binarySearch
all: ${apps}

# Testing
binarySearch: binarySearch.o arrays.o performanceTest.o rng.o
	g++ -o binarySearch binarySearch.o arrays.o performanceTest.o rng.o
binarySearch.o: binarySearch.cpp
	g++ -c binarySearch.cpp

# Array generator
ascendingArray: ascendingArray.o arrays.o
	g++ -o ascendingArray ascendingArray.o arrays.o
ascendingArray.o: ascendingArray.cpp
	g++ -c ascendingArray.cpp

# Dependencies
arrays.o: arrays.cpp
	g++ -c arrays.cpp
performanceTest.o: performanceTest.cpp
	g++ -c performanceTest.cpp
rng.o: rng.cpp
	g++ -c rng.cpp

# Cleaning
exe: ${apps}
	rm -f *.o *.~
clean: 
	rm -f *.o *.~ ${apps}
