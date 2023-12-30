/* 1. Package e importaciones */
package LanguageOut;
import java_cup.runtime.Symbol;
import Painter.WordPainter;

%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */
%{
    WordPainter painter;
    public ScannerFOut(java.io.Reader in, WordPainter painter) {
        yychar = 0;
        this.zzReader = in;
        this.painter = painter;
    }
%}

//Directivas
%class ScannerFOut
%public
%cupsym TOK
%cup
%char
%full
%unicode

//Constructor
%init{
    yychar = 0;
%init}

//Expresiones regulares
UNUSED   = [ \r\t]+
ID       = (\_)*[a-zA-Z][a-zA-Z0-9\_]*
STRING   = \"(([^\n\"\\]|\\.)*)\"
INCLUDE  = \<(([^\n\"\\]|\\.)*)\>
INTEGER  = [0-9]+
DOUBLE   = [0-9]+\.[0-9]+
COMMENTS = "//"([^\r\n]*)
COMMENTM = [/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]

%%

/* 3. Reglas Semanticas */
// Reservadas
"#include"          {return new Symbol(TOK.RW_include,   yychar, yylength(), yytext());}
"void"              {return new Symbol(TOK.RW_void,      yychar, yylength(), yytext());}
"char"              {return new Symbol(TOK.RW_char,      yychar, yylength(), yytext());}
"int"               {return new Symbol(TOK.RW_int,       yychar, yylength(), yytext());}
"float"             {return new Symbol(TOK.RW_float,     yychar, yylength(), yytext());}
"if"                {return new Symbol(TOK.RW_if,        yychar, yylength(), yytext());}
"return"            {return new Symbol(TOK.RW_return,    yychar, yylength(), yytext());}
"goto"              {return new Symbol(TOK.RW_goto,      yychar, yylength(), yytext());}
"printf"            {return new Symbol(TOK.RW_printf,    yychar, yylength(), yytext());}
// Valores
{STRING}            {return new Symbol(TOK.TK_string,    yychar, yylength(), yytext());}
{INCLUDE}           {return new Symbol(TOK.TK_include,   yychar, yylength(), yytext());}
{INTEGER}           {return new Symbol(TOK.TK_int,       yychar, yylength(), yytext());}
{DOUBLE}            {return new Symbol(TOK.TK_float,     yychar, yylength(), yytext());}
// Variables
{ID}                {return new Symbol(TOK.TK_id,        yychar, yylength(), yytext());}
// Operadores Aritméticos
"+"                 {return new Symbol(TOK.TK_plus,      yychar, yylength(), yytext());}
"-"                 {return new Symbol(TOK.TK_minus,     yychar, yylength(), yytext());}
"*"                 {return new Symbol(TOK.TK_mult,      yychar, yylength(), yytext());}
"/"                 {return new Symbol(TOK.TK_div,       yychar, yylength(), yytext());}
"%"                 {return new Symbol(TOK.TK_mod,       yychar, yylength(), yytext());}
// Operadores Relacionales
"=="                {return new Symbol(TOK.TK_equequ,    yychar, yylength(), yytext());}
"!="                {return new Symbol(TOK.TK_notequ,    yychar, yylength(), yytext());}
"<="                {return new Symbol(TOK.TK_lessequ,   yychar, yylength(), yytext());}
">="                {return new Symbol(TOK.TK_moreequ,   yychar, yylength(), yytext());}
"="                 {return new Symbol(TOK.TK_equ,       yychar, yylength(), yytext());}
"<"                 {return new Symbol(TOK.TK_less,      yychar, yylength(), yytext());}
">"                 {return new Symbol(TOK.TK_more,      yychar, yylength(), yytext());}
// Símbolos de Agrupación
"("                 {return new Symbol(TOK.TK_lpar,      yychar, yylength(), yytext());}
")"                 {return new Symbol(TOK.TK_rpar,      yychar, yylength(), yytext());}
"{"                 {return new Symbol(TOK.TK_lbrc,      yychar, yylength(), yytext());}
"}"                 {return new Symbol(TOK.TK_rbrc,      yychar, yylength(), yytext());}
"["                 {return new Symbol(TOK.TK_lbrk,      yychar, yylength(), yytext());}
"]"                 {return new Symbol(TOK.TK_rbrk,      yychar, yylength(), yytext());}
// Signos
","                 {return new Symbol(TOK.TK_comma,     yychar, yylength(), yytext());}
":"                 {return new Symbol(TOK.TK_colon,     yychar, yylength(), yytext());}
";"                 {return new Symbol(TOK.TK_semicolon, yychar, yylength(), yytext());}
//
\n                  {}
{UNUSED}            {}
{COMMENTS}          {painter.COMMENT(yychar, yylength());}
{COMMENTM}          {painter.COMMENT(yychar, yylength());}
.                   {painter.ERROR(yychar, yylength());}