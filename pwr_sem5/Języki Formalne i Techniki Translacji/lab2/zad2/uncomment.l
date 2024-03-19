%{
#include<stdbool.h>
#include<stdio.h> 

int yywrap(); 
int yylex();
bool inCommas = false;
bool inMarker = false;
bool inM = false;
%}
%%
\" {
    printf("\""); 
    inCommas = !inCommas;
}
"<![CDATA[" {
    if(!inCommas)
        inMarker = true;
    printf("%s", yytext);
}

"]]>" {
    if(inMarker)
        inMarker = false;
    printf("%s", yytext);
}


\<!--[^--]*--\> {
    if((inCommas && inM) || inMarker) 
        printf("%s", yytext);
} 

"<" {
    if(!inCommas) {
        inM = true;
    }
    printf("<");
}
">" {
    if(!inCommas) {
        inM = false;
    }
    printf(">");
}
%%
int yywrap() {
    return 1; 
} 
int main() { 
    return yylex(); 
}
