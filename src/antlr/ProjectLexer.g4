lexer grammar ProjectLexer;

//@header { package parser; }

@members {
    java.util.Stack<Integer> indents = new java.util.Stack<>();
    int opened = 0;
    java.util.LinkedList<Token> tokens = new java.util.LinkedList<>();

    CommonToken commonToken(int type, String text) {
        int stop = getCharIndex() - 1;
        int start = stop - text.length() + 1;
        return new CommonToken(this._tokenFactorySourcePair, type, DEFAULT_TOKEN_CHANNEL, start, stop);
    }

    @Override
    public void emit(Token token) {
        tokens.add(token);
    }

    @Override
    public Token nextToken() {
        if (!tokens.isEmpty()) return tokens.poll();
        return super.nextToken();
    }
}

NEWLINE
    :   ('\r'? '\n')
        {
            String newline = getText();
            String spaces = "";
            int nextChar = _input.LA(1);


         if (nextChar == '\n' || nextChar == '\r' || nextChar == '#') {
             tokens.add(commonToken(NEWLINE, newline));
             return;
         }



            int i = 1;
            while (nextChar == ' ' || nextChar == '\t') {
                spaces += (char)nextChar;
                i++;
                nextChar = _input.LA(i);
            }

            int indent = spaces.replace("\t", "    ").length();
            int prev = indents.isEmpty() ? 0 : indents.peek();

            if (opened == 0) {
                if (indent > prev) {
                    indents.push(indent);
                    tokens.add(commonToken(INDENT, spaces));
                } else {
                    while (!indents.isEmpty() && indents.peek() > indent) {
                        indents.pop();
                        tokens.add(commonToken(DEDENT, ""));
                    }
                }
                tokens.add(commonToken(NEWLINE, newline));
            } else {
                 indent / dedent
                tokens.add(commonToken(NEWLINE, newline));
            }
        }
    ;




INDENT : ;
DEDENT : ;


WS : [ \t]+ -> skip ;
COMMENT : '#' ~[\r\n]* -> skip ;


LPAREN : '(' {opened++;} ;
RPAREN : ')' {opened--;} ;
LBRACK : '[' {opened++;} ;
RBRACK : ']' {opened--;} ;
LBRACE : '{' {opened++;} ;
RBRACE : '}' {opened--;} ;


DEF     : 'def'     { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
CLASS   : 'class'   { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
RETURN  : 'return'  { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
IF      : 'if'      { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
ELSE    : 'else'    { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
ELIF    : 'elif'    { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
FOR     : 'for'     { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
WHILE   : 'while'   { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
IN      : 'in'      { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
IMPORT  : 'import'  { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
FROM    : 'from'    { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
AS      : 'as'      { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
PASS    : 'pass'    { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
BREAK   : 'break'   { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
CONTINUE: 'continue'{ !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
GLOBAL  : 'global'  { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
NONLOCAL: 'nonlocal'{ !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
TRUE    : 'True' ;
FALSE   : 'False';
NONE    : 'None';
DEL     : 'del' { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
NOT     : 'not' { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
AND     : 'and' { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
OR      : 'or'  { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
TRY     : 'try'  { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
EXCEPT  : 'except'  { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
FINALLY : 'finally' { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
WITH    : 'with'    { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
LAMBDA  : 'lambda'  { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
ASYNC   : 'async'   { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;
AWAIT   : 'await'   { !Character.isJavaIdentifierPart((char)_input.LA(1)) }? ;


PLUS   : '+' ; MINUS  : '-' ; STAR   : '*' ; POW    : '**' ;
SLASH  : '/' ; FSLASH : '//' ; PERCENT: '%' ;
EQ     : '=' ; EQEQ   : '==' ; NOTEQ  : '!=' ;
LT     : '<' ; LE     : '<=' ; GT     : '>' ; GE     : '>=' ;
ARROW  : '->' ;
TILDE  : '~' ; BITAND : '&' ; BITOR : '|' ; XOR : '^' ;
LSHIFT : '<<' ; RSHIFT: '>>' ;
COLON  : ':' ; COMMA  : ',' ; DOT   : '.' ; SEMI : ';' ; AT : '@' ;


STRING
    : '"' ( ~["\\\r\n] | '\\' . )* '"'
    | '\'' ( ~['\\\r\n] | '\\' . )* '\''
    ;

FSTRING
    : ('f'|'F') '"' ( ~["\\\r\n{] | ESC | BRACE )* '"'
    | ('f'|'F') '\'' ( ~['\\\r\n{] | ESC | BRACE )* '\''
    ;
fragment ESC : '\\' . ;
fragment BRACE : '{' (~[}] | '}' )* '}' ;


NUMBER
    : [0-9]+ ('.' [0-9]+)? ([eE][+-]?[0-9]+)?
    | '.' [0-9]+ ([eE][+-]?[0-9]+)?
    ;

NAME
    : [a-zA-Z_] [a-zA-Z0-9_]*
    ;

UNKNOWN : . ;