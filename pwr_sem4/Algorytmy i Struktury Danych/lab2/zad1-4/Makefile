all: insertionSort mergeSort quickSort dualPivotQuickSort hybridSort randomArray ascendingArray descendingArray

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
hybridSort: hybridSort.o arrays.o
	g++ -o hybridSort hybridSort.o arrays.o
hybridSort.o: hybridSort.cpp
	g++ -c hybridSort.cpp


# Array generator
randomArray: randomArray.o arrays.o rng.o
	g++ -o randomArray randomArray.o arrays.o rng.o
randomArray.o:
	g++ -c randomArray.cpp
ascendingArray: ascendingArray.o arrays.o rng.o
	g++ -o ascendingArray ascendingArray.o arrays.o rng.o
ascendingArray.o:
	g++ -c ascendingArray.cpp
descendingArray: descendingArray.o arrays.o rng.o
	g++ -o descendingArray descendingArray.o arrays.o rng.o
descendingArray.o:
	g++ -c descendingArray.cpp

# Dependencies
arrays.o: arrays.cpp
	g++ -c arrays.cpp
rng.o: rng.cpp
	g++ -c rng.cpp

# Cleaning
exe: insertionSort mergeSort quickSort dualPivotQuickSort hybridSort randomArray ascendingArray descendingArray
	rm -f *.o *.~
clean: 
	rm -f *.o *.~ randomArray ascendingArray descendingArray insertionSort mergeSort quickSort dualPivotQuickSort hybridSort
