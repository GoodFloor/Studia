%{
#include<stdbool.h>
#include<stdio.h> 

int yywrap(); 
int yylex();
int WC=0;
int L=0;
bool NL=true;
%} 
%%

([[:blank:]|\t]*\n)+  {printf("\n"); NL = true;}

^[[:blank:]|\t]* {L++;}

^([^[:blank:]|\t]+) {printf("%s", yytext); NL = false; WC++; L++;}

[[:blank:]|\t]+ ;

[^[:blank:]|\t|\n]+ {
    WC++;
    if(NL) {
        printf("%s", yytext);
        NL = false;
    }
    else
        printf(" %s", yytext);
    }

%%
int yywrap() { 
    printf("\n---\nLinii:%d\nSłów:%d\n", L, WC);
    return 1; 
} 
int main() { 
    return yylex(); 
}
