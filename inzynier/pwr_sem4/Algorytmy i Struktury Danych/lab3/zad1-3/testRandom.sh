#!/bin/bash
for i in {100..10000..100}
do 
    for (( j=1; j<=5; j++ ))
    do
        k=$(($i * $j / 5));
        for (( m=0; m<100; m++ ))
        do
            ./randomArray $i $k | ./randomizedSelect 1
        done
    done
done
