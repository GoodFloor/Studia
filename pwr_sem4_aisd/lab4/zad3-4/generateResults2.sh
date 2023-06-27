#!/bin/bash
printf "n\tśredniaLiczbaPorównań\tmaksLiczbaPorównańDlaJednejOperacji\tśredniaLiczbaOdczytówIPodstawieńWskaźników\tmaksLiczbaOdczytówIPodstawień\tśredniaWysokośćDrzewaPoKażdejOperacji\tmaksWysokośćDrzewa\n"
for i in {100000..100000..10000}
do 
    for j in {1..20}
    do
        ./test2 $i | ./main 1
    done
done
