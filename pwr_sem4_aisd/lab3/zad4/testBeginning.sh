#!/bin/bash
for i in {1000..100000..1000}
do 
    ./ascendingArray $i 0 | ./binarySearch s
done
