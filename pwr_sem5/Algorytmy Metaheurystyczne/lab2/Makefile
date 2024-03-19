executables = app

all: ${executables}

app: app.o Optimize.o Node.o
	g++ -o app app.o Optimize.o Node.o
app.o: app.cpp
	g++ -c app.cpp

Node.o: Node.cpp
	g++ -c Node.cpp
Optimize.o: Optimize.cpp
	g++ -c Optimize.cpp
# Czyszczenie
exe: ${executables}
	rm -f *.o *.~
clean:
	rm -f *.o *.~ ${executables}