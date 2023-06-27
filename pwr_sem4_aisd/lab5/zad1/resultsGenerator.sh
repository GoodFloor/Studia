#!/bin/bash
printf "n\tczas\tliczbaPorównań\n"
for i in {1000..5000..1000}
do
    for k in {1..10000}
    do
        ./seriesGenerator $i $i | ./app
    done
done