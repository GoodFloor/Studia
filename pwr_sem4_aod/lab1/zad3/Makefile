all: main.o stack.o queue.o graphReader.o
	g++ -o zad3 main.o stack.o queue.o graphReader.o
main.o: main.cpp
	g++ -c main.cpp
stack.o:
	g++ -c stack.cpp
queue.o:
	g++ -c queue.cpp
graphReader.o:
	g++ -c graphReader.cpp

rebuild:
	make clean;
	make all;
clean:
	rm -f -r *.o *.~ zad3
exe:
	rm -f -r *.o *.~