all: insertionSort mergeSort quickSort dualPivotQuickSort randomArray

# Sorting testing
insertionSort: insertionSort.o arrays.o
	g++ -o insertionSort insertionSort.o arrays.o
insertionSort.o: insertionSort.cpp
	g++ -c insertionSort.cpp
mergeSort: mergeSort.o arrays.o
	g++ -o mergeSort mergeSort.o arrays.o
mergeSort.o: mergeSort.cpp
	g++ -c mergeSort.cpp
quickSort: quickSort.o arrays.o
	g++ -o quickSort quickSort.o arrays.o
quickSort.o: quickSort.cpp
	g++ -c quickSort.cpp
dualPivotQuickSort: dualPivotQuickSort.o arrays.o
	g++ -o dualPivotQuickSort dualPivotQuickSort.o arrays.o
dualPivotQuickSort.o: dualPivotQuickSort.cpp
	g++ -c dualPivotQuickSort.cpp


# Array generator
randomArray: randomArray.o arrays.o rng.o
	g++ -o randomArray randomArray.o arrays.o rng.o
randomArray.o:
	g++ -c randomArray.cpp

# Dependencies
arrays.o: arrays.cpp
	g++ -c arrays.cpp
rng.o: rng.cpp
	g++ -c rng.cpp

# Cleaning
exe: randomArray insertionSort
	rm -f *.o *.~
clean: 
	rm -f *.o *.~ randomArray ascenddingArray descendingArray insertionSort mergeSort quickSort dualPivotQuickSort
