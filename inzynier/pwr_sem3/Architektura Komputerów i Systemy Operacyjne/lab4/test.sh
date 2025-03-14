#!/bin/bash
width=10;
startX=0;
startY=40;
height=10;
netTxArray=(381 3783 1499 3916 3311 3361 1047 2436 3338 2082 3099);
netRxArray=(2883 3549 3117 1487 1646 1491 152 1677 3680 2440 1129);
maxRxTx=0;
for i in $(seq 0 $width)
do
    if [ ${netTxArray[$i]} -gt $maxRxTx ]
    then
        maxRxTx=${netTxArray[$i]};
    fi
    if [ ${netRxArray[$i]} -gt $maxRxTx ]
    then
        maxRxTx=${netRxArray[$i]};
    fi
done
if [ $maxRxTx -eq 0 ]
then 
    maxRxTx=1;
fi
############s

getSufix () {
    n=$1;
    sufix="B";
    while [ $n -gt 1024 ]
    do
        n=$(( $n / 1024 ));
        if [ $sufix = "B" ]
        then
            sufix="kB";
        elif [ $sufix = "kB" ]
        then
            sufix="MB";
        elif [ $sufix = "MB" ]
        then
            sufix="GB";
        elif [ $sufix = "GB" ]
        then
            sufix="TB";
        fi
    done
    echo $sufix;
    return $n;
}
displayNetstat () {
    tput sc;
    tput cup $(( $startY + 1 )) 9;
    printf "+";
    for i in $(seq $(( $startY - $height )) $startY)
    do
        tput cup $i 9;
        printf "|";
    done
    tput cup $(( $startY - $height + 1 )) $startX;
    s="$(getSufix $maxRxTx)";
    printf "$? $s";
    tput cup $(( $startY - $height )) 9;
    printf "+";
    tput cup $(( $startY + 1 )) 10;
    for i in $(seq 1 $(( $width * 3 + 3)))
    do
        printf "-";
    done
    for i in $(seq 0 $width)
    do
        tput cup $startY $(( $i * 3 + 10 + $startX ))
        for y in $(seq 0 $height)
        do
            if [ $(( ${netTxArray[$i]} * $height / $maxRxTx )) -gt $y ]
            then
                printf "\u001b[48;2;255;64;32m ";
            else
                printf "\u001b[49m ";
            fi
            if [ $(( ${netRxArray[$i]} * $height / $maxRxTx )) -gt $y ]
            then
                printf "\u001b[48;2;64;128;255m ";
            else
                printf "\u001b[49m ";
            fi
            printf "\u001b[49m ";
            tput cuu1;
            tput cub 3;
        done
    done
    tput rc;
}
clear;
echo $maxRxTx;
displayNetstat;