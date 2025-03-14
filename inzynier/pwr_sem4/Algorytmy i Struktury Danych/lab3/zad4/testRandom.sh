#!/bin/bash
for i in {1000..100000..1000}
do 
    for (( m=0; m<100; m++ ))
    do
        ./ascendingArray $i 0 | ./binarySearch s r
    done
done