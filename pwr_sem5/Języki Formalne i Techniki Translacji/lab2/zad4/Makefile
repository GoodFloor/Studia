calculator: calculator.c
	gcc -o calculator calculator.c

calculator.c: calculator.l
	flex -o calculator.c calculator.l

clean:
	rm calculator.c calculator
