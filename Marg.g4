grammar Marg;

@header {
    import java.util.HashMap;
}

@parser::members {
    HashMap<String, Variable> mem = new HashMap<String, Variable>();

    String trim_quotes(String raw) {
        return raw.substring(1, raw.length()-1);
    }
}

begin_program:        statement+
             ;

statement:            shout
         |            var_statement
         ;

var_statement:        var_set
             ;

var_set:              'int'    ':' ID '=' exp     { mem.put($ID.text, new IntVar((Integer)$exp.var.value)); }
       |              'float'  ':' ID '=' exp     { mem.put($ID.text, new FloatVar((Float)$exp.var.value)); }
       //|              'bool'   ':' ID '=' exp     { make_var($ID.text, Type.BOOL,   $exp.val.boolVal); }
       //|              'string' ':' ID '=' STRING  { make_var($ID.text, Type.STRING, $STRING.text); }
       //|              'ip'     ':' ID '=' IP      { make_var($ID.text, Type.IP,     $IP.text); }
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
                  //|  BOOLLIT  { $val = new Variable(Type.BOOL,  Boolean.parseBoolean($BOOLLIT.text)); }
                  //|  IP       { $val = new Variable(Type.IP,    $IP.text); }
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