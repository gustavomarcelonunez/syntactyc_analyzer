package interprete;

%%

%class Lexer
%byaccj

%{

  private Parser yyparser;

  public Lexer(java.io.Reader r, Parser p )
  {
    this(r);
    yyparser = p  ;
  }

  /** Devuelve el numero de linea donde está el último caracter leído **/

  public int lineaActual()
  {
     return yyline;
  }

%}

%%

"begin"         { return Parser.BEGIN; }
"end"           { return Parser.END; }
"while"         { return Parser.WHILE; }
"if"            { return Parser.IF; }
"switch"        { return Parser.SWITCH; }
"for"           { return Parser.FOR; }
"to"            { return Parser.TO; }
"case"          { return Parser.CASE; }
"break"         { return Parser.BREAK; }
"="             { return Parser.EQUAL; }
"!="            { return Parser.DISTINCT; }

["xyz"]         { return Parser.ID; }
[0-9]+          { return Parser.CONSTANT; }
["+-"]          { return Parser.OP_ADD_SUB; }
["*/"]          { return Parser.OP_MUL_DIV; }

\r|\n|\r\n      /** Conteo de líneas **/
  { 
    yyline++ ;
  }

[><(){}:]     /** Símbolos literales **/ 
    { 
      yyparser.yylval = yytext();
      return yytext().charAt(0);
    }

[ \t\ ]+      /** Accion vacia: se ignoran los espacios en blanco y tabuladores **/
      { }