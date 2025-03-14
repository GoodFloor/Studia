#!/bin/bash
width=10;
startX=0;
startY=19;
height=10;
netRxArray=();
netTxArray=();
netRx=0;
netTx=0;
oldNetRx=0;
oldNetTx=0;
maxRxTx=0;
for i in $(seq 0 $(( $width - 1 )))
do
    netRxArray+=(0);
    netTxArray+=(0);
done
################################################################
readNetStat () {
    oldNetRx=$netRx;
    oldNetTx=$netTx;
    netRx=0;
    netTx=0;
    for INTERFACE in /sys/class/net/*
    do
        netRx=$(( $netRx + `cat ${INTERFACE}/statistics/rx_bytes` ));
        netTx=$(( $netTx + `cat ${INTERFACE}/statistics/tx_bytes` ));
    done
}
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
    tput cup $(( $startY - $height )) $startX;
    s="$(getSufix $maxRxTx)";
    printf "$? $s";
    tput cup $(( $startY - $height )) 9;
    printf "+";
    tput cup $(( $startY + 1 )) 10;
    for i in $(seq 1 $(( $width * 3 + 3)))
    do
        printf "-";
    done
    tput cup $(( $startY + 2 )) 9;
    printf "\u001b[48;2;255;64;32m \u001b[49m-Upload";
    tput cup $(( $startY + 3 )) 9;
    printf "\u001b[48;2;64;128;255m \u001b[49m-Download";
    for i in $(seq 0 $width)
    do
        tput cup $startY $(( $i * 3 + 10 + $startX ))
        for y in $(seq 0 $height)
        do
            if [ $(( ${netTxArray[$i]} * $height / $maxRxTx )) -gt $(( $y - 1 )) ]
            then
                printf "\u001b[48;2;255;64;32m ";
            else
                printf "\u001b[49m ";
            fi
            if [ $(( ${netRxArray[$i]} * $height / $maxRxTx )) -gt $(( $y - 1 )) ]
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
################################################################
readNetStat;
sleep 1;
readNetStat;
netRxArray+=($(( $netRx - $oldNetRx )));
netTxArray+=($(( $netTx - $oldNetTx )));
################################################################
numberOfCores=0;
cpuData2=( $(cat /proc/cpuinfo) );
for i in $(seq 0 ${#cpuData2[@]})
do
    if [ ${cpuData2[$i]} == "processor" ]
    then 
        numberOfCores=$(( $numberOfCores + 1 ));
    fi
done
#Główna pętla programu
while [ 1 ]
do
#STATYSTYKI SIECIOWE
    readNetStat;
    maxRxTx=0;
    for i in $(seq 0 $width)
    do
        if [ $i -lt $width ]
        then
            j=$(( $i + 1 ));
            netTxArray[$i]=${netTxArray[$j]};
            netRxArray[$i]=${netRxArray[$j]};
        else
            currentTx=$(( $netTx - $oldNetTx ))
            currentRx=$(( $netRx - $oldNetRx ))
            netTxArray[$i]=$currentTx;
            netRxArray[$i]=$currentRx;
        fi
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
    
#CORES
    cpuData2=( $(cat /proc/cpuinfo) );
    tmpCounter=0;
    for i in $(seq 1 $numberOfCores)
    do
        temp=$cpuData2[tmpCounter];
        while [ $temp != "MHz" ]
        do
            tmpCounter=$(( $tmpCounter + 1 ));
            temp=${cpuData2[tmpCounter]};
        done
        frequencyData+=( ${cpuData2[$(( $tmpCounter + 2 ))]});
        tmpCounter=$(( $tmpCounter + 3 ));
    done
    cpuData1=( $(cat /proc/stat) );
    totalTime=();
    workTime=();
    for i in $(seq 0 $(( $numberOfCores - 1 )))
    do
        totalTime+=(0);
        for j in {1..10}
        do 
            k=$(( ($i + 1) * 11 + $j ));
            totalTime[$i]=$(( ${totalTime[$i]} + ${cpuData1[$k]} ));
        done
        workTime+=($(( ${totalTime[$i]} - ${cpuData1[($i + 1) * 11 + 4]} )));
    done




#UPTIME
    readarray -d " " uptime < /proc/uptime;
    time=${uptime[0]%.*};
    seconds=$(( $time % 60 ));
    time=$(( ($time - $seconds) / 60 ));
    minutes=$(( $time % 60 ));
    time=$(( ($time - $minutes) / 60 ));
    hours=$(( $time % 24 ));
    time=$(( ($time - $hours) / 24 ));
    
#LOAD
    readarray -d " " load < /proc/loadavg;

#MEMORY USE
    memoryData=( $(cat /proc/meminfo) );

#DISPLAY
    clear;
    s="$(getSufix $currentTx)";
    printf "Upload: $? $s/s ";
    s="$(getSufix $currentRx)";
    printf "Download: $? $s/s \n";
    for i in $(seq 0 $(( $numberOfCores - 1 )))
    do
        echo "CPU$i: $(( ${workTime[$i]} * 100 / ${totalTime[$i]} ))%   ${frequencyData[$i]}MHz";
    done
    echo "Uptime: $time days, $hours hours, $minutes minutes, $seconds seconds";
    echo "Average load in 1 minute: ${load[0]}, 5 minutes: ${load[1]}, 15 minutes: ${load[2]}";
    echo "Memory used: $(( ${memoryData[1]} -  ${memoryData[7]} ))kB / ${memoryData[1]}kb";
    displayNetstat;
    sleep 1;

done
