#!/bin/bash
for i in {100..10000..100}
do 
    ./worstCaseArray $i | ./dualPivotQuickSort
done
