apps = randomArray fixedArray randomizedSelect determinedSelect
all: ${apps}

# Testing
randomizedSelect: randomizedSelect.o arrays.o performanceTest.o
	g++ -o randomizedSelect randomizedSelect.o arrays.o performanceTest.o
randomizedSelect.o: randomizedSelect.cpp
	g++ -c randomizedSelect.cpp
determinedSelect: determinedSelect.o arrays.o performanceTest.o
	g++ -o determinedSelect determinedSelect.o arrays.o performanceTest.o
determinedSelect.o: determinedSelect.cpp
	g++ -c determinedSelect.cpp

# Array generator
randomArray: randomArray.o arrays.o rng.o
	g++ -o randomArray randomArray.o arrays.o rng.o
randomArray.o: randomArray.cpp
	g++ -c randomArray.cpp
fixedArray: fixedArray.o arrays.o rng.o
	g++ -o fixedArray fixedArray.o arrays.o rng.o
fixedArray.o: fixedArray.cpp
	g++ -c fixedArray.cpp

# Dependencies
arrays.o: arrays.cpp
	g++ -c arrays.cpp
rng.o: rng.cpp
	g++ -c rng.cpp
performanceTest.o: performanceTest.cpp
	g++ -c performanceTest.cpp

# Cleaning
exe: ${apps}
	rm -f *.o *.~
clean: 
	rm -f *.o *.~ ${apps}
