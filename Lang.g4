grammar Lang;

begin_program:            'main' exp 'end';
exp:                      shout+;
shout:                    'shout' STRING;

ID:       [a-zA-Z]+[a-zA-Z0-9_]*;
INTLIT:   [-]?[0-9]+;
FLOATLIT: [+-]?([0-9]*[.])?[0-9]+;
STRING : '"' (ESC | ~('\\'|'"'))* '"';
ESC : '\\' ('n' | 'r');
COMMENT:  ('/*').*?('*/') -> skip; // toss out multiline comments
WS:       [ \t\r\n]+ -> skip ; // toss out whitespace