%{
#define YYSTYPE unsigned long long
#include<stdio.h>
#include<stdbool.h>
extern int yylineno;
int yylex();
int yyerror(char*);
bool zerodiv = false;
int mod=1234577;
unsigned long long invert(unsigned long long x, int modulus)
{
    if(x == 0)
        return 0;
    if(x == 1)
        return 1;
    unsigned long long n = (x * 2) % modulus;
    for(int i = 2; i < modulus; i++)
    {
        if(n == 1)
        {
            return i;
        }
        n += x;
        n %= modulus;
    }
    return 0;
}
%}
%token VAL
%token ADD
%token SUB
%token MUL
%token DIV
%token POW
%token LBRCKT
%token RBRCKT
%token END
%token ERROR
%%
input:                  //Epsilon
    | input line
;
line: exp1 END 	{ 
        if(zerodiv) {
            zerodiv = false;
            printf("\nBłąd w linii %d! Dzielenie przez nieodwracalną liczbę!\n", yylineno-1);
        }
        else
            printf("\nWynik:\t%d\n",(int)$1); 
        printf("\n");
    }
    | error END	{ printf("Błąd składni w linii %d!\n",yylineno-1); printf("\n");}
;
exp1: exp2 { $$ = $1; }
    | exp1 ADD exp2 { $$ = ($1 + $3) % mod; printf("+ "); }
    | exp1 SUB exp2 { $$ = ($1 - $3 + mod) % mod; printf("- "); }
;
exp2: exp3 { $$ = $1; }
    | exp2 MUL exp3 { $$ = ($1 * $3) % mod; printf("* "); }
    | exp2 DIV exp3 { 
        printf("/ ");
        unsigned long long inv = invert($3, mod);
        if(inv == 0) {
            $$ = 0;
            zerodiv = true;
        }
        else {
            $$ = ($1 * inv) % mod;
        }
    }
;
exp3: exp4 { $$ = $1; }
    | exp3 POW powexp4 { 
        unsigned long long result = 1;
        for(int i = 0; i < (int)$3; i++) {
            result *= $1;
            result %= mod;
        }
        $$ = result;
        printf("^ "); 
    }
;
exp4: VAL { 
        int realVal = $1;
        while(realVal < 0)
            realVal += mod;
        realVal %= mod;
        printf("%d ", realVal);
        $$ = (unsigned long long)realVal;
    }
    | LBRCKT exp1 RBRCKT { $$ = $2; }
    | SUB exp4 {
        int realVal = mod - $2;
        while(realVal < 0)
            realVal += mod;
        realVal %= mod;
        printf("~ ", realVal);
        $$ = (unsigned long long)realVal;
    }
;
powexp4: VAL { 
        int realVal = $1;
        while(realVal < 0)
            realVal += (mod - 1);
        realVal %= (mod - 1);
        printf("%d ", realVal);
        $$ = realVal;
    }
    | LBRCKT powexp1 RBRCKT { $$ = $2; }
    | SUB powexp4 {
        int realVal = (mod - 1) - $2;
        while(realVal < 0)
            realVal += (mod - 1);
        realVal %= (mod - 1);
        printf("~ ", realVal);
        $$ = (unsigned long long)realVal;
    }
;
powexp1: powexp2 { $$ = $1; }
    | powexp1 ADD powexp2 { $$ = ($1 + $3) % (mod - 1); printf("+ "); }
    | powexp1 SUB powexp2 { $$ = ($1 - $3 + (mod - 1)) % (mod - 1); printf("- "); }
;
powexp2: powexp4 { $$ = $1; }
    | powexp2 MUL powexp4 { $$ = ($1 * $3) % (mod - 1); printf("* "); }
    | powexp2 DIV powexp4 { 
        printf("/ ");
        unsigned long long inv = invert($3, mod - 1);
        if(inv == 0) {
            $$ = 0;
            zerodiv = true;
        }
        else {
            $$ = ($1 * inv) % (mod - 1);
        }
    }
;
%%
int yyerror(char *s)
{
    printf("%s\n",s);	
    return 0;
}

int main()
{
    yyparse();
    printf("Przetworzono linii: %d\n",yylineno-1);
    return 0;
}
