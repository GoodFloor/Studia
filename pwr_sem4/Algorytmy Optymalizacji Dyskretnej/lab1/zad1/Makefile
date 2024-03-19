all: main.o arcs.o queue.o stack.o
	g++ -o zad1 main.o arcs.o queue.o stack.o
main.o: main.cpp
	g++ -c main.cpp
arcs.o: arcs.cpp
	g++ -c arcs.cpp
queue.o:
	g++ -c queue.cpp
stack.o:
	g++ -c stack.cpp

rebuild:
	make clean;
	make all;
clean:
	rm -f -r *.o *.~ zad1
exe:
	rm -f -r *.o *.~