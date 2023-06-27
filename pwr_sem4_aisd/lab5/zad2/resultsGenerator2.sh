#!/bin/bash
printf "n\tczas\tliczbaPorównań\n"
for i in {100..10000..100}
do
    for j in {1..100}
    do
        ./generator $i $i | ./app B
    done
done