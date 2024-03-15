#!/bin/bash
for FILE in $1/*
do 
  if [ -f "$FILE" ]
  then
    echo "Changing $FILE to $(echo $FILE | tr '[:upper:]' '[:lower:]')";
    mv -f "$FILE" "${FILE}temp" 2>/dev/null;
    mv -f "${FILE}temp" "$(echo $FILE | tr '[:upper:]' '[:lower:]')" 2>/dev/null;
  fi
done
