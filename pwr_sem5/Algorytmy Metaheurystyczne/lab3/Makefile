executables = app
objectFiles = app.o Node.o Optimize.o GeneticAlgorithm.o

all: ${executables}

app: ${objectFiles}
	g++ -o app ${objectFiles}

%.o: %.cpp
	g++ -c $^

# Czyszczenie
exe: ${executables}
	rm -f *.o *.~
clean:
	rm -f *.o *.~ ${executables}