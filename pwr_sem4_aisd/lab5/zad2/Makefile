executables = app generator

all: ${executables}

# Implementacja kopców
app: app.o binomialHeap.o fibonacciHeap.o
	g++ -o app app.o binomialHeap.o fibonacciHeap.o
app.o: app.cpp
	g++ -c app.cpp
binomialHeap.o: binomialHeap.cpp
	g++ -c binomialHeap.cpp
fibonacciHeap.o: fibonacciHeap.cpp
	g++ -c fibonacciHeap.cpp

# Generowanie danych testowych
generator: generator.o
	g++ -o generator generator.o
generator.o: generator.cpp
	g++ -c generator.cpp

# Czyszczenie
exe: ${executables}
	rm -f *.o *.~
clean:
	rm -f *.o *.~ ${executables}