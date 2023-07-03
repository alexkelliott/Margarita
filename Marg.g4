grammar Marg;

@header {
import margarita.*;
import margarita.variables.*;

import java.util.HashMap;
}

@parser::members {
    

    String trim_quotes(String raw) {
        return raw.substring(1, raw.length()-1);
    }
}

begin_program:        outer_statements*
             ;

// statements made outside the scope of a function
outer_statements:     statement
                |     function
                ;

function:             'fun' ID '<''>' '->' '<''>' '{' statement* '}'
        ;

function_call:        ID '<' '>'
             ;

statement:            shout
         |            var_statement
         |            function_call
         ;

var_statement:        var_set
             ;

var_set:              'int'    ':' ID '=' exp    # SetInt
       |              'float'  ':' ID '=' exp    # SetFloat
       |              'bool'   ':' ID '=' exp    # SetBool
       |              'string' ':' ID '=' STRING # SetString
       |              'ip'     ':' ID '=' IP     # SetIP
       ;

shout:                'shout' STRING # ShoutString
     |                'shout' exp    # ShoutExp
     ;

exp
                  :  '(' exp ')'     # ExpParenthesis
                  |  a=exp '/' b=exp # ExpDivide
                  |  a=exp '*' b=exp # ExpMultiply
                  |  a=exp '-' b=exp # ExpSubtract
                  |  a=exp '+' b=exp # ExpAdd
                  |  INTLIT          # ExpIntLit
                  |  FLOATLIT        # ExpFloatLit
                  |  BOOLLIT         # ExpBoolLit
                  |  IP              # ExpIP
                  |  function_call   # ExpFunctionCall
                  |  ID              # ExpID 
                  ;


BOOLLIT:  'true'|'false';
INTLIT:   [-]?[0-9]+;
FLOATLIT: [-]?([0-9]*[.])?[0-9]+;
STRING :  '"' (ESC | ~('\\'|'"'))* '"';

// HIGH and LOW refer to order of operations priority level
OP: OP_HIGH | OP_LOW;
OP_HIGH: '*' | '/';
OP_LOW:  '+' | '-';

IP:    OCTET'.'OCTET'.'OCTET'.'OCTET;
OCTET: [12]?[0-9]?[0-9];

ID:       [a-zA-Z]+[a-zA-Z0-9_]*;
ESC :     '\\' ('n' | 'r');
COMMENT:  ('/*').*?('*/') -> skip; // toss out multiline comments
WS:       [ \t\r\n]+ -> skip ; // toss out whitespace