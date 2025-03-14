executables = app seriesGenerator

all: ${executables}

# Szukanie najdłuższego wspólnego podciągu
app: app.o
	g++ -o app app.o
app.o: app.cpp
	g++ -c app.cpp

# Generowanie ciągów testowych
seriesGenerator: seriesGenerator.o
	g++ -o seriesGenerator seriesGenerator.o
seriesGenerator.o: seriesGenerator.cpp
	g++ -c seriesGenerator.cpp

# Czyszczenie
exe: ${executables}
	rm -f *.o *.~
clean:
	rm -f *.o *.~ ${executables}