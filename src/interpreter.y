%{
  import java.io.*;
  import java.util.Collection;
  import java.util.List;
%}

%token BEGIN
%token END
      
%token CONSTANT

%token ID

%token WHILE
%token IF
%token SWITCH_CASE


%%

program
  : // Programa vacio
  | begin_statement statement_list end_statement
  ;

begin_statement
  : BEGIN
  ;

end_statement
  : END
  ;


statement_list
  : // Sentencia vacia
  | statement 
  | statement_list statement
  ;

statement
  : expression
  | assignment_statement
  | while_statement
  | if_statement
  | switch_case
  ;

assignment_statement
  : ID '=' expression
  ;

while_statement
  : WHILE '(' condition ')' '{' statement_list '}'
  ;

if_statement
  : IF '(' condition ')' '{' statement_list '}'
  ;

condition
  : expression '=''=' expression
  | expression '>''=' expression
  | expression '<''=' expression
  | expression '>' expression
  | expression '<' expression
  | expression '!''=' expression
  ;

switch_case
  : statement
  ;

expression
  : expression '+' term
  | expression '-' term
  | term
  ;

term
  : term '*' operator
  | term '/' operator
  | operator
  ;

operator
  : CONSTANT
  | ID
  ;

%%

  /** referencia al analizador léxico
  **/
  private Lexer lexer ;


  /** constructor: crea el Interpreteranalizador léxico (lexer)
  **/
  public Parser(Reader r)
  {
     lexer = new Lexer(r, this);
  }

  /** esta función se invoca por el analizador cuando necesita el
  *** siguiente token del analizador léxico
  **/
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

  /** invocada cuando se produce un error
  **/
  public void yyerror (String descripcion, int yystate, int token)
  {
     System.err.println ("Error en línea "+Integer.toString(lexer.lineaActual())+" : "+descripcion);
     System.err.println ("Token leído : "+yyname[token]);
  }

  public void yyerror (String descripcion)
  {
     System.err.println ("Error en línea "+Integer.toString(lexer.lineaActual())+" : "+descripcion);
  }