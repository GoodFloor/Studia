#!/bin/bash
for i in {100..10000..100}
do 
    for (( m=0; m<100; m++ ))
    do
        ./randomArray $i | ./selectQuickSort 1
    done
done
