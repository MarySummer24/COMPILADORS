package AnalizadorL;
import static AnalizadorL.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]
D=[0-9]
espacios=[ ,\\t]
opAr=[*,\+,\-,//]
opAg=[(,)]
coma=[,]
%{
    public String lexeme;
%}
%%
{coma}	{lexeme=yytext();return coma;}
int {lexeme=yytext(); return inta;}
float {lexeme=yytext(); return floata;}
char {lexeme=yytext(); return chara;}
{espacios} {/*Ignore*/}
{opAg} {lexeme=yytext(); return agrupacion;}
{opAr} {lexeme=yytext(); return aritmetico;}

"=" {lexeme=yytext(); return asignacion;}
";"	{lexeme=yytext();return finSentencia;}
{L}({L}|{D})* {lexeme=yytext(); return id;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return num;}
"\n" {lexeme=yytext(); return salto;}
. {return ERROR;}