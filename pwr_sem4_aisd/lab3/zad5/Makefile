apps = randomArray worstCaseArray selectQuickSort selectDualPivotQuickSort quickSort dualPivotQuickSort
all: ${apps}

# Testing
selectQuickSort: selectQuickSort.o arrays.o performanceTest.o
	g++ -o selectQuickSort selectQuickSort.o arrays.o performanceTest.o
selectQuickSort.o: selectQuickSort.cpp
	g++ -c selectQuickSort.cpp
selectDualPivotQuickSort: selectDualPivotQuickSort.o arrays.o performanceTest.o
	g++ -o selectDualPivotQuickSort selectDualPivotQuickSort.o arrays.o performanceTest.o
selectDualPivotQuickSort.o: selectDualPivotQuickSort.cpp
	g++ -c selectDualPivotQuickSort.cpp
quickSort: quickSort.o arrays.o performanceTest.o
	g++ -o quickSort quickSort.o arrays.o performanceTest.o
quickSort.o: quickSort.cpp
	g++ -c quickSort.cpp
dualPivotQuickSort: dualPivotQuickSort.o arrays.o performanceTest.o
	g++ -o dualPivotQuickSort dualPivotQuickSort.o arrays.o performanceTest.o
dualPivotQuickSort.o: dualPivotQuickSort.cpp
	g++ -c dualPivotQuickSort.cpp

# Array generator
randomArray: randomArray.o arrays.o rng.o
	g++ -o randomArray randomArray.o arrays.o rng.o
randomArray.o: randomArray.cpp
	g++ -c randomArray.cpp
worstCaseArray: worstCaseArray.o arrays.o rng.o
	g++ -o worstCaseArray worstCaseArray.o arrays.o rng.o
worstCaseArray.o: worstCaseArray.cpp
	g++ -c worstCaseArray.cpp

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
