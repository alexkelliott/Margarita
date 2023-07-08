grammar Marg;

@header {
import margarita.*;
import margarita.variables.*;

import java.util.HashMap;
}

@parser::members {}

begin_program:        outer_statement* EOF
             ;

// statements made outside the scope of a function
outer_statement:      statement
               |      function
               ;

function:             FUNCTION_DEF ID '<' parameter_list '>' '->' '<' ret=('int'|'float'|'bool'|'string'|'ip')? '>' '{' inner_statement* '}'
        ;

parameter_list:       parameter (',' parameter)*
              | 
              ;

parameter:            type=INT_TEXT    ':' ID
         |            type=FLOAT_TEXT  ':' ID
         |            type=BOOL_TEXT   ':' ID
         |            type=STRING_TEXT ':' ID
         |            type=IP_TEXT     ':' ID
         ;

function_call:        ID '<' arg_list '>'
             ;

arg_list:             exp (',' exp)*
        |
        ;

inner_statement:      statement
               |      return
               ;

return:               'ret' exp
      ;

statement:            shout
         |            var_statement
         |            function_call
         |            conditional
         ;

var_statement:        var_def
             |        var_set
             ;

var_def:              INT_TEXT    ':' ID '=' exp    //# SetInt
       |              FLOAT_TEXT  ':' ID '=' exp    //# SetFloat
       |              BOOL_TEXT   ':' ID '=' exp    //# SetBool
       |              STRING_TEXT ':' ID '=' exp    //# SetString
       |              IP_TEXT     ':' ID '=' exp    //# SetIP
       ;

var_set:              ID '=' exp
       ;

shout:                SHOUT exp
     ;

conditional:         IF exp OP_BRACE inner_statement* CL_BRACE (else_if)* (else)?
           ;

else_if:             ELSE IF exp OP_BRACE inner_statement* CL_BRACE
       ;

else:                ELSE OP_BRACE inner_statement* CL_BRACE
    ;

exp:                  '(' exp ')'      # ExpParenthesis
   |                  a=exp '/' b=exp  # ExpDivide
   |                  a=exp '*' b=exp  # ExpMultiply
   |                  a=exp '-' b=exp  # ExpSubtract
   |                  a=exp '+' b=exp  # ExpAdd
   |                  a=exp '<' b=exp  # ExpLT
   |                  a=exp '>' b=exp  # ExpGT
   |                  a=exp '<=' b=exp # ExpLE
   |                  a=exp '>=' b=exp # ExpGE
   |                  a=exp '==' b=exp # ExpEQ
   |                  a=exp '!=' b=exp # ExpNE
   |                  INTLIT           # ExpIntLit
   |                  FLOATLIT         # ExpFloatLit
   |                  BOOLLIT          # ExpBoolLit
   |                  STRING           # ExpString
   |                  IP               # ExpIP
   |                  function_call    # ExpFunctionCall
   |                  ID               # ExpID 
   ;


BOOLLIT:  'true'|'false';
INTLIT:   [-]?[0-9]+;
FLOATLIT: [-]?([0-9]*[.])?[0-9]+;
STRING :  '"' (ESC | ~('\\'|'"'))* '"';

IP:    OCTET'.'OCTET'.'OCTET'.'OCTET;
OCTET: [12]?[0-9]?[0-9];

OP_BRACE: '{';
CL_BRACE: '}';
IF:   'if';
ELSE: 'else';
SHOUT: 'shout';
FUNCTION_DEF: 'fun';
//TYPE: (INT_TEXT | FLOAT_TEXT | BOOL_TEXT | STRING_TEXT | IP_TEXT);
INT_TEXT:    'int';
FLOAT_TEXT:  'float';
BOOL_TEXT:   'bool';
STRING_TEXT: 'string';
IP_TEXT:     'ip';

ID:       [a-zA-Z]+[a-zA-Z0-9_]*;
ESC :     '\\' ('n' | 'r');
COMMENT:  ('/*').*?('*/') -> skip; // toss out multiline comments
WS:       [ \t\r\n]+ -> skip ; // toss out whitespace