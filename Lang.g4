grammar Lang;

/** The start rule; begin parsing here. */
begin_program:            'main' INTLIT 'end';


ID:       [a-zA-Z]+[a-zA-Z0-9_]*;      // match identifiers
INTLIT:   [-]?[0-9]+;                  // match integers
FLOATLIT: [+-]?([0-9]*[.])?[0-9]+;  // floats
// NEWLINE:  '\r'? '\n';                  // return newlines to parser (is end-statement signal)

WS:       [ \t\r\n]+ -> skip ; // toss out whitespace

COMMENT:  ('/*').*?('*/') -> skip ;