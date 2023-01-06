%{
  import java.io.*;
%}

%token BEGIN
%token END
      
%token CONSTANT
%token ID

%token WHILE
%token IF
%token SWITCH
%token CASE
%token FOR
%token TO
%token BREAK
%token OP_ADD_SUB;
%token OP_MUL_DIV;
%token EQUAL;
%token DISTINCT;


%%

program
  : // Programa vacio
  | BEGIN statement_list END
  ;

statement_list
  : // Sentencia vacia
  | statement 
  | statement_list statement
  ;

statement
  : assignment
  | while
  | if
  | switch
  | for
  ;

assignment
  : ID EQUAL expression
  ;

while
  : WHILE '(' condition ')' '{' statement_list '}'
  ;

if
  : IF '(' condition ')' '{' statement_list '}'
  ;

switch
  : SWITCH '(' operator ')' '{' case_list '}'
  ;

case_list
  : case
  | case_list case
  ;

case
  : CASE CONSTANT ':' '{' statement_list BREAK '}'
  ;

for
  : FOR '(' operator TO operator ')' '{' statement_list '}'
  ;

condition
  : expression EQUAL EQUAL expression
  | expression '>'EQUAL expression
  | expression '<'EQUAL expression
  | expression '>' expression
  | expression '<' expression
  | expression DISTINCT expression
  ;

expression
  : expression OP_ADD_SUB term
  | term
  ;

term
  : term OP_MUL_DIV operator
  | operator
  ;

operator
  : CONSTANT
  | ID
  ;

%%

  /** Referencia al analizador léxico **/
  private Lexer lexer ;


  /** Constructor: crea el Interpreter-analizador léxico (lexer) **/
  public Parser(Reader r)
  {
     lexer = new Lexer(r, this);
  }

  /** Esta función se invoca por el analizador cuando necesita el
  ** siguiente token del analizador léxico **/
  private int yylex ()
  {
    int yyl_return = -1;

    try
    {
       yylval = new Object();
       yyl_return = lexer.yylex();
    }
    catch (IOException e)
    {
       System.err.println("error de E/S:"+e);
    }

    return yyl_return;
  }

  public void yyerror (String description)
  {
     System.err.println ("Error en línea "+Integer.toString(lexer.lineaActual())+" : "+description);
  }