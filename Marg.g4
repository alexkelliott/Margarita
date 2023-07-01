
grammar Marg;

@header {
import margarita.*;
import margarita.variables.*;

import java.util.HashMap;
}

@parser::members {
    HashMap<String, margarita.variables.Variable> mem = new HashMap<String, margarita.variables.Variable>();

    String trim_quotes(String raw) {
        return raw.substring(1, raw.length()-1);
    }
}

begin_program:        statement*
             ;

statement:            shout
         |            var_statement
         ;

var_statement:        var_set
             ;

var_set:              'int'    ':' ID '=' exp     { mem.put($ID.text, new IntVar((Integer)$exp.var.value)); }
       |              'float'  ':' ID '=' exp     { mem.put($ID.text, new FloatVar((Float)$exp.var.value)); }
       |              'bool'   ':' ID '=' exp     { mem.put($ID.text, new BoolVar((Boolean)$exp.var.value)); }
       |              'string' ':' ID '=' STRING  { mem.put($ID.text, new StringVar(trim_quotes($STRING.text))); }
       |              'ip'     ':' ID '=' IP      { mem.put($ID.text, new IPVar($IP.text)); }
       ;

shout:                'shout' STRING { System.out.println(trim_quotes($STRING.text)); }
     |                'shout' exp    { System.out.println($exp.var); }
     ;

exp returns [Variable var]
                  :  '(' exp ')' { $var = $exp.var; }
                  |  a=exp '/' b=exp { $var = $a.var.calc('/', $b.var); }
                  |  a=exp '*' b=exp { $var = $a.var.calc('*', $b.var); }
                  |  a=exp '-' b=exp { $var = $a.var.calc('-', $b.var); }
                  |  a=exp '+' b=exp { $var = $a.var.calc('+', $b.var); }
                  |  INTLIT   { $var = new IntVar($INTLIT.int); }
                  |  FLOATLIT { $var = new FloatVar(Float.parseFloat($FLOATLIT.text)); }
                  |  BOOLLIT  { $var = new BoolVar(Boolean.parseBoolean($BOOLLIT.text)); }
                  |  IP       { $var = new IPVar($IP.text); }
                  |  ID       { String id = $ID.text;
                                if (mem.containsKey(id)) {
                                    $var = mem.get(id);
                                }
                              }
                  ;


BOOLLIT:  'true'|'false';
INTLIT:   [-]?[0-9]+;
FLOATLIT: [-]?([0-9]*[.])?[0-9]+;
STRING :  '"' (ESC | ~('\\'|'"'))* '"';

// HIGH and LOW refer to order of operations priority level
OP: OP_HIGH | OP_LOW;
OP_HIGH: '*' | '/';
OP_LOW:  '+' | '-';

IP: OCTET'.'OCTET'.'OCTET'.'OCTET;
OCTET: [12]?[0-9]?[0-9];

ID:       [a-zA-Z]+[a-zA-Z0-9_]*;
ESC : '\\' ('n' | 'r');
COMMENT:  ('/*').*?('*/') -> skip; // toss out multiline comments
WS:       [ \t\r\n]+ -> skip ; // toss out whitespace