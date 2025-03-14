%{
#include<math.h>
#include<stdbool.h>
#include<stdio.h> 

int yywrap(); 
int yylex();
int stack[100];
int currentId = 0;
int currentNumber = 0;
bool readingNumber = false;
bool noError = true;
%} 
%%
-?[0-9]+ {
    if(noError && currentId < 100) {
        stack[currentId] = atoi(yytext);
        currentId++;
    }
    else if(currentId >= 100) {
        noError = false;
        printf("Błąd: za dużo liczb pod rząd");
    }
    
}
\+ {
    if(noError && currentId >= 2) {
        currentId--;
        int v1 = stack[currentId];
        currentId--;
        stack[currentId] = stack[currentId] + v1;
        currentId++;
    }
    else if(noError){
        noError = false;
        printf("Błąd: za mała liczba argumentów\n");
    }
}
\- {
    if(noError && currentId >= 2) {
        currentId--;
        int v1 = stack[currentId];
        currentId--;
        stack[currentId] = stack[currentId] - v1;
        currentId++;
    }
    else if(noError){
        noError = false;
        printf("Błąd: za mała liczba argumentów\n");
    }
}
\* {
    if(noError && currentId >= 2) {
        currentId--;
        int v1 = stack[currentId];
        currentId--;
        stack[currentId] = stack[currentId] * v1;
        currentId++;
    }
    else if(noError){
        noError = false;
        printf("Błąd: za mała liczba argumentów\n");
    }
}
\/ {
    if(noError && currentId >= 2) {
        currentId--;
        int v1 = stack[currentId];
        if(v1 == 0) {
            noError = false;
            printf("Błąd: dzielenie przez 0\n");
        }
        else {
            currentId--;
            stack[currentId] = stack[currentId] / v1;
            currentId++;
        }
    }
    else if(noError){
        noError = false;
        printf("Błąd: za mała liczba argumentów\n");
    }
}
\^ {
    if(noError && currentId >= 2) {
        currentId--;
        int v1 = stack[currentId];
        currentId--;
        int v2 = stack[currentId];
        stack[currentId] = 1;
        for(int i = 0; i < v1; i++) {
            stack[currentId] *= v2;
        }
        currentId++;
    }
    else if(noError){
        noError = false;
        printf("Błąd: za mała liczba argumentów\n");
    }
}
\% {
    if(noError && currentId >= 2) {
        currentId--;
        int v1 = stack[currentId];
        if(v1 == 0) {
            noError = false;
            printf("Błąd: dzielenie przez 0\n");
        }
        else {
            currentId--;
            stack[currentId] = stack[currentId] % v1;
            currentId++;
        }
    }
    else if(noError){
        noError = false;
        printf("Błąd: za mała liczba argumentów\n");
    }
}
[[:blank:]] ;
\n {
    if(noError && currentId > 1) {
        printf("Błąd: za mała liczba operatorów\n");
    }
    else if(currentId < 1) {
        printf("Błąd: za mała liczba argumentów\n");
    }
    else if(noError) {
        printf("= %d\n", stack[0]);
    }
    currentId = 0;
    noError = true;
}
. {
    if(noError) {
        noError = false;
        printf("Błąd: zły symbol \"%s\"\n", yytext);
    }
}
%%
int yywrap() {

    return 1; 
} 
int main() { 
    return yylex(); 
}
