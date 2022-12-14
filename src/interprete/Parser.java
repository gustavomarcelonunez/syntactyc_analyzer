//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package interprete;



//#line 2 "./src/interpreter.y"
  import java.io.*;
//#line 19 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short BEGIN=257;
public final static short END=258;
public final static short CONSTANT=259;
public final static short ID=260;
public final static short WHILE=261;
public final static short IF=262;
public final static short SWITCH=263;
public final static short CASE=264;
public final static short FOR=265;
public final static short TO=266;
public final static short BREAK=267;
public final static short OP_ADD_SUB=268;
public final static short OP_MUL_DIV=269;
public final static short EQUAL=270;
public final static short DISTINCT=271;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    1,    2,    2,    2,    2,    2,
    2,    3,    5,    6,    7,   11,   11,   12,    8,    9,
    9,    9,    9,    9,    9,    4,    4,   13,   13,   10,
   10,
};
final static short yylen[] = {                            2,
    0,    3,    0,    1,    2,    1,    1,    1,    1,    1,
    1,    3,    7,    7,    7,    1,    2,    7,    9,    4,
    4,    4,    3,    3,    3,    3,    1,    3,    1,    1,
    1,
};
final static short yydefred[] = {                         0,
    0,    0,   30,    0,    0,    0,    0,    0,    0,    4,
    6,    0,    8,    9,   10,   11,   29,    0,    0,    0,
    0,    0,    0,    2,    5,    0,    0,   31,    0,    0,
    0,    0,    0,    0,    0,   28,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   16,    0,   13,   14,    0,   15,   17,    0,    0,
    0,    0,   19,    0,    0,   18,
};
final static short yydgoto[] = {                          2,
    9,   10,   11,   12,   13,   14,   15,   16,   31,   17,
   61,   62,   18,
};
final static short yysindex[] = {                      -244,
 -180,    0,    0, -254,  -20,  -17,  -14,  -12, -187,    0,
    0, -236,    0,    0,    0,    0,    0, -232, -251, -251,
 -251, -251, -251,    0,    0, -251, -251,    0, -236,  -59,
    3,    6,    8, -216, -232,    0, -219, -251, -217, -214,
  -71,  -61,  -57, -251, -251, -236, -251, -236, -251, -236,
 -180, -180, -200,   27, -236, -236, -236,  -91,  -84, -190,
 -121,    0,  -46,    0,    0,   28,    0,    0, -180,  -36,
  -77, -180,    0, -202,  -37,    0,
};
final static short yyrindex[] = {                        89,
 -168,    0,    0, -123,    0,    0,    0,    0,    0,    0,
    0, -110,    0,    0,    0,    0,    0,  -41,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -100,    0,
    0,    0,    0,    0,  -27,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   50,    0,   51,    0,   52,
  -31,  -31,    0,    0,   54,   55,   56,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -31,    0,
    0, -167,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -45,   -4,    0,   -9,    0,    0,    0,    0,   78,   -5,
    0,   40,   76,
};
final static int YYTABLESIZE=244;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         27,
   40,   31,   39,   67,   25,   58,   59,    3,   28,   29,
   30,   30,    1,   26,    7,   19,   33,   34,   27,   20,
   27,   36,   21,   71,   12,   22,   74,   23,   46,   48,
   50,   26,   26,   64,   26,   55,   27,   56,   54,   57,
   65,    3,   28,   41,    3,   28,   42,   73,   43,   44,
   45,   51,   47,   25,   25,   49,    3,    4,    5,    6,
    7,   52,    8,   60,   75,   53,   25,   63,   66,   25,
   24,    3,    4,    5,    6,    7,   69,    8,    3,    4,
    5,    6,    7,   27,    8,   70,   72,   76,    1,    3,
   25,   23,   24,    3,   20,   21,   22,   26,   32,    3,
   68,   35,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   31,   31,   31,   31,   31,   31,
    0,   31,   60,   31,   31,   31,    0,    7,    7,    7,
    7,    7,    7,    0,    7,    0,    7,   12,   12,   12,
   12,   12,   12,    0,   12,    0,   12,    3,    4,    5,
    6,    7,    0,    8,    3,    4,    5,    6,    7,    0,
    8,    3,    4,    5,    6,    7,    0,    8,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   26,    0,
   37,   38,    0,    0,    0,    0,   27,   27,   27,   27,
   27,   27,    0,   27,    0,   27,   27,    0,   27,   27,
   26,   26,   26,   26,   26,   26,    0,   26,    0,   26,
   26,    0,   26,   26,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   60,  125,   62,  125,    9,   51,   52,  259,  260,   19,
   20,   21,  257,   41,  125,  270,   22,   23,   60,   40,
   62,   27,   40,   69,  125,   40,   72,   40,   38,   39,
   40,  268,   60,  125,   62,   45,  269,   47,   44,   49,
  125,  259,  260,   41,  259,  260,   41,  125,   41,  266,
  270,  123,  270,   58,   59,  270,  259,  260,  261,  262,
  263,  123,  265,  264,  267,  123,   71,   41,  259,   74,
  258,  259,  260,  261,  262,  263,  123,  265,  259,  260,
  261,  262,  263,  125,  265,   58,  123,  125,    0,  258,
   41,   41,   41,  125,   41,   41,   41,  125,   21,  267,
   61,   26,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  258,  259,  260,  261,  262,  263,
   -1,  265,  264,  267,  268,  269,   -1,  258,  259,  260,
  261,  262,  263,   -1,  265,   -1,  267,  258,  259,  260,
  261,  262,  263,   -1,  265,   -1,  267,  259,  260,  261,
  262,  263,   -1,  265,  259,  260,  261,  262,  263,   -1,
  265,  259,  260,  261,  262,  263,   -1,  265,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  268,   -1,
  270,  271,   -1,   -1,   -1,   -1,  258,  259,  260,  261,
  262,  263,   -1,  265,   -1,  267,  268,   -1,  270,  271,
  258,  259,  260,  261,  262,  263,   -1,  265,   -1,  267,
  268,   -1,  270,  271,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=271;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'",null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,"':'",null,
"'<'",null,"'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"BEGIN","END","CONSTANT","ID","WHILE","IF",
"SWITCH","CASE","FOR","TO","BREAK","OP_ADD_SUB","OP_MUL_DIV","EQUAL","DISTINCT",
};
final static String yyrule[] = {
"$accept : program",
"program :",
"program : BEGIN statement_list END",
"statement_list :",
"statement_list : statement",
"statement_list : statement_list statement",
"statement : assignment",
"statement : expression",
"statement : while",
"statement : if",
"statement : switch",
"statement : for",
"assignment : ID EQUAL expression",
"while : WHILE '(' condition ')' '{' statement_list '}'",
"if : IF '(' condition ')' '{' statement_list '}'",
"switch : SWITCH '(' operator ')' '{' case_list '}'",
"case_list : case",
"case_list : case_list case",
"case : CASE CONSTANT ':' '{' statement_list BREAK '}'",
"for : FOR '(' operator TO operator ')' '{' statement_list '}'",
"condition : expression EQUAL EQUAL expression",
"condition : expression '>' EQUAL expression",
"condition : expression '<' EQUAL expression",
"condition : expression '>' expression",
"condition : expression '<' expression",
"condition : expression DISTINCT expression",
"expression : expression OP_ADD_SUB term",
"expression : term",
"term : term OP_MUL_DIV operator",
"term : operator",
"operator : CONSTANT",
"operator : ID",
};

//#line 100 "./src/interpreter.y"

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
//#line 309 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
