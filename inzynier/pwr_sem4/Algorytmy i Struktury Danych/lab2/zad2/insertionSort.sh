#!/bin/bash
for i in {10..200..10}
do 
    for (( c=0; c<$1; c++ ))
    do
        ./randomArray $i | ./insertionSort
    done
done
