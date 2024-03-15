#!/bin/bash
printf "n\tliczbaPorównań\tmaksLiczbaPorównańDlaJednejOperacji\tliczbaOdczytówIPodstawieńWskaźników\tmaksLiczbaOdczytówIPodstawień\tśredniaWysokośćDrzewaPoKażdejOperacji\tmaksWysokośćDrzewa\n"
for i in {10000..100000..10000}
do 
    for j in {1..20}
    do
        ./test1 $i | ./main 1
    done
done
