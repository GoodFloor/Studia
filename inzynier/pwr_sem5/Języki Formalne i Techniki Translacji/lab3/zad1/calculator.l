%option noyywrap
%option yylineno
%{
#include "calculator_y.h"
int yylex();
%}
%%
\\\n    ;
[ \t]+ 	;
^\#.*\n   ;
\+      { return ADD; }
-       { return SUB; }
\*      { return MUL; }
\/      { return DIV; }
\^      { return POW; }
\(	    { return LBRCKT; }
\)	    { return RBRCKT; }
\n	    { return END; }
[0-9]+  { yylval=atoi(yytext); return VAL; }
.	    { return ERROR; }
%%
