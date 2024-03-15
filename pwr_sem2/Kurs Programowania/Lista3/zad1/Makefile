# Makefile
all: Test

Test: Figura.class Kolo.class Czworokat.class Kwadrat.class Prostokat.class Romb.class Pieciokat.class Szesciokat.class Test.java
	javac Test.java
	
Figura.class: Figura.java
	javac Figura.java

Kolo.class: Figura.class Kolo.java
	javac Kolo.java

Czworokat.class: Figura.class Czworokat.java
	javac Czworokat.java

Kwadrat.class: Figura.class Czworokat.class Kwadrat.java
	javac Kwadrat.java

Prostokat.class: Figura.class Czworokat.class Prostokat.java
	javac Prostokat.java

Romb.class: Figura.class Czworokat.class Romb.java
	javac Romb.java

Pieciokat.class: Figura.class Pieciokat.java
	javac Pieciokat.java

Szesciokat.class: Figura.class Szesciokat.java
	javac Szesciokat.java
	
clean:
	rm -f *.class *.~
