// fuente 'jflex' para interprete


// CÓDIGO DE USUARIO
package interprete;


%%
// OPCIONES Y DECLARACIONES

%class Lexer
%byaccj

%{

  private Parser yyparser;

  public Lexer(java.io.Reader r, Parser p )
  {
    this(r);
    yyparser = p  ;
  }

  /** devuelve el numero de linea donde está el último caracter leido
  **/

  public int lineaActual()
  {
     return yyline;
  }

%}

%%
// TOKENIZACION

"begin"         { return Parser.BEGIN; }
"end"           { return Parser.END; }
"while"         { return Parser.WHILE; }
"if"            { return Parser.IF; }
"switch_case"   { return Parser.SWITCH_CASE; }

["xyz"]
    {
      return Parser.ID;
    }
[0-9]+
   {  
      return Parser.CONSTANT;
   }

\r|\n|\r\n
  { // lleva la cuenta de lineas
    yyline++ ;
  }

[+\-*/><=(){}!] {
      /** Deja pasar los caracteres únicos como literales **/
      /** No es ninguna expresión regular, sólo los caracteres literales **/
      yyparser.yylval = yytext();
      return yytext().charAt(0);
    }

[ \t\ ]+ {  /** accion vacia: se ignoran los espacios en blanco y tabuladores **/
       }
