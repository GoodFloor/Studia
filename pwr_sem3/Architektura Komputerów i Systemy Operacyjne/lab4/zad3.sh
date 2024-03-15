#!/bin/bash
catJSON=$(curl -s https://api.thecatapi.com/v1/images/search | sed 's/^.//;s/.$//');
catURL=$(jq -r '.url' <<< $catJSON);
wget -q $catURL;
filename=$(basename $catURL);
h=$(tput lines);
h=$(( 2 * ($h - 3) ));
chuckNorrisJoke=$(curl -s https://api.chucknorris.io/jokes/random | jq -r '.value' );
catimg -H $h $filename;
echo ${chuckNorrisJoke};
rm $filename;
