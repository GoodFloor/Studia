#!/bin/bash
for i in {10..200..10}
do 
    for (( c=0; c<$1; c++ ))
    do
        ./randomArray $i | ./mergeSort
    done
done
for i in {1000..20000..1000}
do 
    for (( c=0; c<$1; c++ ))
    do
        ./randomArray $i | ./mergeSort
    done
done
