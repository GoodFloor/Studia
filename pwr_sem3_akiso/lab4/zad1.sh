#!/bin/bash
result="";
for FOLDER in /proc/*
do
  #printf "a b c\na b c" | column -t -N "A,B,C";
#   Jesli to folder && jesli jego nazwa to numer
  if [ -d $FOLDER ] && [[ ${FOLDER##/*/} =~ [0-9] ]]
  then 
    FILE=$FOLDER"/stat";
    readarray -d " " data < $FILE;
    result+="${data[3]};${data[0]};${data[1]};${data[2]};${data[6]};${data[23]};${data[4]};${data[5]}";
    result+="\n";
#    printf "%s" $result;
#    echo ${data[1]};
  fi
done
printf "%b" $result | column -t -s ";" -o " | " -N "PPID,PID,COMM,STATE,TTY,RSS,PGID,SID";
