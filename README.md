<img src="logos/marg_transparent.png"  width="200" height="200">

# Margarita
Project to create a functional programming language 

## Setup
Download antlr-4.13.0-complete.jar into the root of the project  
```wget https://www.antlr.org/download/antlr-4.13.0-complete.jar```  

## Run

Build the lexer and parser from the .g4 grammar file by running  
```./build.sh```

### Parse and run file
Run the parser on a file  
```./run.sh <path/to/file.marg>```

### Generate parse tree
```antlr4-parse Marg.g4 begin_program <path/to/file.marg> -gui```

## Structure

**antlr-4.13.0-complete.jar**: Lexer and parser utilities  
**Marg.g4**: Defines the languages grammar  
**build.sh**: Uses antlr-4.13.0-complete.jar to generate a lexer and parser based on Marg.g4  
**run.sh**: Uses ProjectMarg.java to parse a given input file  
**src**  
├─ **ProjectMarg.java**: Uses lexer and parser generated from build.sh to parse an input file  
├─ **ProjectMargErrorListener.java**: Handles printing of lexing and parsing errors  
├─ **MargBaseListener.java**: Implements functions of the MargListener interface  
└─ **MargCustomListener.java**: Overrides MargListener functions by extending MargBaseListener.java  
**test_cases**: Directory storing test files written in the project language    
