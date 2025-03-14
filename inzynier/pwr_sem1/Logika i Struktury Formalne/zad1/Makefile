# Makefile
all: next_tuple gen_tuples

next_tuple: tuples.o next_tuple.o
	clang++ -o next_tuple tuples.o next_tuple.o

gen_tuples: tuples.o gen_tuples.o
	clang++ -o gen_tuples tuples.o gen_tuples.o

next_tuple.o: next_tuple.cpp
	clang++ next_tuple.cpp -c

gen_tuples.o: gen_tuples.cpp
	clang++ gen_tuples.cpp -c 

tuples.o: tuples.cpp
	clang++ tuples.cpp -c

clean:
	rm -f *.o next_tuple gen_tuples *.~

necessary:
	rm -f *.o *.~
