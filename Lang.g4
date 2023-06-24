grammar Lang;

begin_program:            exp+;
exp:                      shout
   |                      set;
shout:                    'shout' STRING
     |                    'shout' ID;
set:                      'set' ID '=' INTLIT
   |                      'set' ID '=' STRING;

ID:       [a-zA-Z]+[a-zA-Z0-9_]*;
INTLIT:   [-]?[0-9]+;
FLOATLIT: [+-]?([0-9]*[.])?[0-9]+;
STRING : '"' (ESC | ~('\\'|'"'))* '"';
ESC : '\\' ('n' | 'r');
COMMENT:  ('/*').*?('*/') -> skip; // toss out multiline comments
WS:       [ \t\r\n]+ -> skip ; // toss out whitespace