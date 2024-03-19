%{
#include<stdbool.h>
#include<stdio.h> 

int yywrap(); 
int yylex();
bool removeDox = true;
bool inCommas = false;
%} 
%%
\#include[[:blank:]]*\<.*\> {
    printf("%s", yytext);
}

\/(\\\n[[:blank:]|\t]*)?\/(\\\n[[:blank:]|\t]*)?[\/|!].*(\\\n.*)* {
    if(inCommas || !removeDox)
        printf("%s", yytext);
}
\/(\\\n[[:blank:]|\t]*)?\/.*(\\\n.*)* {
    if(inCommas)
        printf("%s", yytext);
}
\/(\\\n[[:blank:]|\t]*)?\*(\\\n[[:blank:]|\t]*)?[\*|!]([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+\/ {
    if(inCommas || !removeDox)
        printf("%s", yytext);
}
"/*"(\\\n[[:blank:]|\t]*)?([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+\/ {
    if(inCommas)
        printf("%s", yytext);
}
\"[^\"]*\" {
    printf("%s", yytext);
}

%%
int yywrap() {
    return 1; 
} 
int main(int argc, char const *argv[]) { 
    if(argc > 1)
        removeDox = false;
    return yylex(); 
}
