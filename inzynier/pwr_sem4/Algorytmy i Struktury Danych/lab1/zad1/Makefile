lifo: lifoTest.o lifo.o
	gcc -o lifoTest lifoTest.o lifo.o
lifoTest.o: lifoTest.c
	gcc -c lifoTest.c
lifo.o: lifo.o
	gcc -c lifo.c

fifo: fifoTest.o fifo.o
	gcc -o fifoTest fifoTest.o fifo.o
fifoTest.o: fifoTest.c
	gcc -c fifoTest.c
fifo.o: fifo.o
	gcc -c fifo.c

clean:
	rm -f *.o lifoTest fifoTest *.~