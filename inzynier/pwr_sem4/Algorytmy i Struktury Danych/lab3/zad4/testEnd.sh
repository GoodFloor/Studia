#!/bin/bash
for i in {1000..100000..1000}
do 
    v=$(($i - 1));
    ./ascendingArray $i $v | ./binarySearch s
done