executables = KMP

all: ${executables}

KMP: KMP.o automate.o
	g++ -o KMP KMP.o automate.o
KMP.o: KMP.cpp
	g++ -c KMP.cpp
automate.o: automate.cpp
	g++ -c automate.cpp

# Czyszczenie
exe: ${executables}
	rm -f *.o *.~
clean:
	rm -f *.o *.~ ${executables}