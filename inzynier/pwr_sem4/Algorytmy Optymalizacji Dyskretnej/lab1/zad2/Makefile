all: main.o arcs.o queue.o graphReader.o
	g++ -m64 -o zad2 main.o arcs.o queue.o graphReader.o
main.o: main.cpp
	g++ -m64 -c main.cpp
queue.o:
	g++ -m64 -c queue.cpp
graphReader.o:
	g++ -m64 -c graphReader.cpp
arcs.o: arcs.cpp
	g++ -m64 -c arcs.cpp

rebuild:
	make clean;
	make all;
clean:
	rm -f -r *.o *.~ zad2
exe:
	rm -f -r *.o *.~