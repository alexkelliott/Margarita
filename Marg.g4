grammar Marg;

begin_program:            exp+;

exp:                      shout
   |                      var_statement;

var_statement:            var_set
             |            var_def;

var_def:                  INT_DEF
       |                  FLOAT_DEF
       |                  BOOL_DEF
       |                  STRING_DEF;

var_set:                  INT_DEF '=' INTLIT
       |                  FLOAT_DEF '=' FLOATLIT
       |                  BOOL_DEF '=' BOOLLIT
       |                  STRING_DEF '=' STRING;

shout:                    'shout' STRING;


INT_DEF: 'int:'ID;
FLOAT_DEF: 'float:'ID;
BOOL_DEF: 'bool:'ID;
STRING_DEF: 'string:'ID;

BOOLLIT: 'true'|'false';
INTLIT:   [-]?[0-9]+;
FLOATLIT: [+-]?([0-9]*[.])?[0-9]+;
STRING : '"' (ESC | ~('\\'|'"'))* '"';

ID:       [a-zA-Z]+[a-zA-Z0-9_]*;
ESC : '\\' ('n' | 'r');
COMMENT:  ('/*').*?('*/') -> skip; // toss out multiline comments
WS:       [ \t\r\n]+ -> skip ; // toss out whitespace