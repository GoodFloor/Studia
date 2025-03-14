executables = FA

all: ${executables}

FA: fa.o automate.o
	g++ -o FA fa.o automate.o
fa.o: fa.cpp
	g++ -c fa.cpp
automate.o: automate.cpp
	g++ -c automate.cpp

# Czyszczenie
exe: ${executables}
	rm -f *.o *.~
clean:
	rm -f *.o *.~ ${executables}