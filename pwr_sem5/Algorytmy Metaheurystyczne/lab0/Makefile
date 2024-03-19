executables = app

all: ${executables}

app: app.o CompleteGraph.o
	g++ -o app app.o CompleteGraph.o
app.o: app.cpp
	g++ -c app.cpp
CompleteGraph.o: CompleteGraph.cpp
	g++ -c CompleteGraph.cpp

# Czyszczenie
exe: ${executables}
	rm -f *.o *.~
clean:
	rm -f *.o *.~ ${executables}