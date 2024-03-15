# Makefile
all: zad4

zad4: newagent.o north.o south.o east.o west.o distance.o zad4.o
	clang -o zad4 newagent.o north.o south.o east.o west.o distance.o zad4.o -lm
	
zad4.o: zad4.c
	clang zad4.c -c -Wextra -std=c11 --pedantic
	
newagent.o: newagent.c
	clang newagent.c -c -Wextra -std=c11 --pedantic

north.o: north.c
	clang north.c -c -Wextra -std=c11 --pedantic

south.o: south.c
	clang south.c -c -Wextra -std=c11 --pedantic

east.o: east.c
	clang east.c -c -Wextra -std=c11 --pedantic

west.o: west.c
	clang west.c -c -Wextra -std=c11 --pedantic

distance.o: distance.c
	clang distance.c -c -Wextra -std=c11 --pedantic
	
clean:
	rm -f *.o zad4 *.~
