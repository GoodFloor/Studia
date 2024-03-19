all: main.o hashHandler.o priorityQueue.o binaryTree.o
	g++ -o main main.o hashHandler.o priorityQueue.o binaryTree.o
main.o: main.cpp
	g++ -c main.cpp
hashHandler.o: hashHandler.cpp
	g++ -c hashHandler.cpp
priorityQueue.o: priorityQueue.cpp
	g++ -c priorityQueue.cpp
binaryTree.o: binaryTree.cpp
	g++ -c binaryTree.cpp

# Cleaning
exe: main
	rm -f *.o *.~
clean: 
	rm -f *.o *.~ main
