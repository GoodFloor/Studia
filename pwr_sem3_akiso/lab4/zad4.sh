#!/bin/bash
shaArray=();
fileArray=();

checkFolder () {
    for FILE in ${1}/*
    do
        if [ -f $FILE ]
        then
            fileArray+=( $FILE );
            out=$(sha256sum $FILE | awk {'print $1'});
            shaArray+=( $out );
        elif [ -d $FILE ]
        then
            checkFolder $FILE;
        fi
    done
}

checkFolder $1;

isUnique=();
for FILE in ${shaArray[@]}
do
    isUnique+=(1);
done

sameFiles=();
duplicateSizes=();
l=$(( ${#shaArray[@]} - 1 ));
for i in $(seq 0 $l)
do
    if [ ${isUnique[$i]} -eq 0 ]
    then 
        continue;
    fi
    sameTemp="";
    sizeTemp=0;
    for j in $(seq $(( $i + 1 )) $l)
    do
        if [ ${shaArray[$i]} == ${shaArray[$j]} ]
        then
            if [ "$(cat ${fileArray[$i]})" == "$(cat ${fileArray[$j]})" ]
            then
                if [ ${isUnique[$i]} -eq 1 ]
                then 
                    isUnique[$i]=0;
                    sameTemp="(${fileArray[$i]})";
                    sizeTemp=$(stat -c %s ${fileArray[$i]});
                fi
                isUnique[$j]=0;
                sameTemp="${sameTemp},(${fileArray[$j]})";
                sizeTemp=$(( $sizeTemp + $(stat -c %s ${fileArray[$j]}) ))
            fi
        fi
    done
    if [ ${isUnique[$i]} -eq 0 ]
    then
        sameFiles+=( $sameTemp );
        duplicateSizes+=( $sizeTemp );
    fi
done

printf "Rozmiar\t | Ścieżki jednakowych plików\n";
for i in $(seq 0 $((${#sameFiles[@]} - 1 )))
do
    printf "${duplicateSizes[$i]}\t | ${sameFiles[$i]}\n";
done
