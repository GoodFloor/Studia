#!/bin/bash
suma_cyfr() {
  n=$1
  suma=0
  while [ $n -gt 0 ]
  do
    (( suma += $n % 10 ))
    (( n /= 10 ))
  done
  return $suma
}

start=2022
for i in {2..2022}
do
  suma_cyfr $start
  ((start -= $? ))
done
echo $start
