grammar Marg;

@header {
    import java.util.HashMap;
}

@parser::members {
    HashMap<String, Variable> mem = new HashMap<String, Variable>();

    String trim_quotes(String raw) {
        return raw.substring(1, raw.length()-1);
    }

    void make_var(String name, Type type, Object value) {
        switch (type) {
            case STRING:
                value = trim_quotes((String)value);
                break;
            case IP:
                value = new IP((String)value);
                break;
        }

        Variable new_var = new Variable(name, type, value);
        mem.put(name, new_var);
    }

    Variable solve(Variable a, char op, Variable b) {
        int new_val = 0;
        switch (op) {
            case '+':
                new_val = a.intVal + b.intVal;
                break;
            case '-':
                new_val = a.intVal - b.intVal;
                break;
            case '*':
                new_val = a.intVal * b.intVal;
                break;
            case '/':
                new_val = a.intVal / b.intVal;
                break;
        }
        return new Variable(Type.INT, new_val);
    }
}

begin_program:        statement+
             ;

statement:            shout
         |            var_statement
         ;

var_statement:        var_set
             ;

var_set:              'int'    ':' ID '=' exp     { make_var($ID.text, Type.INT,    $exp.val.intVal); }
       |              'float'  ':' ID '=' exp     { make_var($ID.text, Type.FLOAT,  $exp.val.floatVal); }
       |              'bool'   ':' ID '=' exp     { make_var($ID.text, Type.BOOL,   $exp.val.boolVal); }
       |              'string' ':' ID '=' STRING  { make_var($ID.text, Type.STRING, $STRING.text); }
       |              'ip'     ':' ID '=' IP      { make_var($ID.text, Type.IP,     $IP.text); }
       ;


shout:                'shout' STRING { System.out.println(trim_quotes($STRING.text)); }
     |                'shout' exp    { System.out.println($exp.val); }
     ;

exp returns [Variable val]
                  :  '(' exp ')' { $val = $exp.val; }
                  |  a=exp '/' b=exp { $val = solve($a.val, '/', $b.val); }
                  |  a=exp '*' b=exp { $val = solve($a.val, '*', $b.val); }
                  |  a=exp '-' b=exp { $val = solve($a.val, '-', $b.val); }
                  |  a=exp '+' b=exp { $val = solve($a.val, '+', $b.val); }
                  |  INTLIT   { $val = new Variable(Type.INT,   $INTLIT.int); }
                  |  FLOATLIT { $val = new Variable(Type.FLOAT, Float.parseFloat($FLOATLIT.text)); }
                  |  BOOLLIT  { $val = new Variable(Type.BOOL,  Boolean.parseBoolean($BOOLLIT.text)); }
                  |  IP       { $val = new Variable(Type.IP,    $IP.text); }
                  |  ID       { String id = $ID.text;
                                if (mem.containsKey(id)) {
                                    $val = mem.get(id);
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