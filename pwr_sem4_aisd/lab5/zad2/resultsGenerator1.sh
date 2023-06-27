#!/bin/bash
printf "n\tczas\tliczbaPorównań\n"
for i in {500..1000..500}
do
    ./generator $i $i | ./app B
done