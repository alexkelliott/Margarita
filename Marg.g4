grammar Marg;

begin_program:            exp+;

exp:                      shout
   |                      var_statement;

var_statement:            var_set
             |            var_def;

var_def:                  int_def
       |                  float_def
       |                  bool_def
       |                  string_def;

var_set:                  int_def    '=' INTLIT
       |                  float_def  '=' FLOATLIT
       |                  bool_def   '=' BOOLLIT
       |                  string_def '=' STRING;

int_def:                  'int'    ':' ID;
float_def:                'float'  ':' ID;
bool_def:                 'bool'   ':' ID;
string_def:               'string' ':' ID;

shout:                    'shout' STRING;


BOOLLIT: 'true'|'false';
INTLIT:   [-]?[0-9]+;
FLOATLIT: [+-]?([0-9]*[.])?[0-9]+;
STRING : '"' (ESC | ~('\\'|'"'))* '"';

ID:       [a-zA-Z]+[a-zA-Z0-9_]*;
ESC : '\\' ('n' | 'r');
COMMENT:  ('/*').*?('*/') -> skip; // toss out multiline comments
WS:       [ \t\r\n]+ -> skip ; // toss out whitespace