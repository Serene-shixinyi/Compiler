grammar Mx;

//keyword
Int : 'int';
Bool : 'bool';
String : 'string';
Null : 'null';
Void : 'void';
True : 'true';
False : 'false';
If : 'if';
Else : 'else';
For : 'for';
While : 'while';
Break : 'break';
Continue : 'continue';
Return : 'return';
New : 'new';
Class : 'class';
This : 'this';

Intconst: [0-9]+;
Blankchar: [ \r\t\n]+ -> skip;
NotationLine: '//' .*? ('\n' | EOF) -> skip;
Stringconst: '"' (~["\n\r\\] | '\\' ["nr\\])*? '"';
Identifier: [a-zA-Z][a-zA-Z0-9_]*;
identifier: Identifier;
boolConst: True | False;
constant
    : Intconst      #intcst
    | Stringconst   #stringcst
    | boolConst     #boolcst
    | Null          #nullcst
    ;

bracket: '[' ']';
numbracket: '[' expression ']'; //
simpletype
    : Int           #intType
    | Bool          #boolType
    | String        #stringType
    | identifier    #identiferType//Identifier: class
    ;

type
    : simpletype    #narraytype
    | type bracket  #arraytype
    ;
functype
    : type  #nfunctype
    | Void  #voidtype
    ;

expressionlist : '(' (expression (',' expression)*)? ')';

newthings
    : New simpletype numbracket+ bracket+ numbracket+   #newError
    | New simpletype numbracket+ bracket*               #newArrayType
    | New simpletype '(' ')'                            #newClassType
    | New simpletype                                    #newSimpleType //maybe classtype
    ;

expression
    : newthings                                     #newExpr
    | expression '.' identifier                     #classMemberExpr
    | expression expressionlist                     #funcCall //before class member
    | expression numbracket                         #arrayIndex
    | expression op=('++' | '--')                   #suffixOp
    | <assoc=right> op=('++'|'--') expression       #prefixOp
    | <assoc=right> op=('+'|'-') expression         #unaryOp
    | <assoc=right> op=('!'|'~') expression         #unaryOp
    | expression op=('*'|'/'|'%') expression        #binaryOp
    | expression op=('+'|'-') expression            #binaryOp
    | expression op=('<<' | '>>') expression        #binaryOp
    | expression op=('<'|'>'|'<='|'>=') expression  #binaryOp
    | expression op=('=='|'!=') expression          #binaryOp
    | expression op='&' expression                  #binaryOp
    | expression op='^' expression                  #binaryOp
    | expression op='|' expression                  #binaryOp
    | expression op='&&' expression                 #binaryOp
    | expression op='||' expression                 #binaryOp
//    | cond=expression '?' expr1=expression ':' expr2=expression
//                                                    #ternaryOp
    | <assoc=right> expression op='=' expression    #binaryOp
    | identifier                                    #identifierExpr
    | This                                          #thisExpr
    | constant                                      #constants
    | '(' expression ')'                            #parentheses
    ;

block: '{' statement* '}';
oneDcl: type identifier;
parameterlist: '(' (oneDcl(',' oneDcl)*)? ')';
vardeclaration
    : oneDcl '=' expression ';'             #assignDcl
    | type identifier(',' identifier)* ';'  #simpleDcl
    ;
funcdeclaration: functype identifier parameterlist block;
constructor: identifier parameterlist block;
classmember
    : vardeclaration    #vardclMb
    | constructor       #constructMb
    | funcdeclaration   #funcdclMb
    ;
classdeclaration: Class identifier '{' classmember*'}' ';';
declaration
    : vardeclaration    #varDcl
    | funcdeclaration   #funcDcl
    | classdeclaration  #classDcl
    ;
loop
    : While '(' cond=expression ')' statement   #whileSt
    | For '(' init=expression? ';' cond=expression? ';' incr=expression? ')' statement
                                                #forSt
    ;

statement
    : ';'                                   #emptySt
    | block                                 #blockSt
    | expression ';'                        #exprSt
    | vardeclaration                        #varibalDclSt
    | Return expression? ';'                #returnSt
    | Break ';'                             #breakSt
    | Continue ';'                          #continueSt
    | If '(' cond=expression ')' statement  #ifSt
    | If '(' cond=expression ')' stmt1=statement Else stmt2=statement
                                            #ifelseSt
    | loop                                  #loopSt
    ;

program: declaration* EOF;