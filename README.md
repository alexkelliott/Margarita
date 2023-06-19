# language
Simple project to create a programming language 

## Setup
Download antlr-4.13.0-complete.jar into the root of the project  
```wget https://www.antlr.org/download/antlr-4.13.0-complete.jar```  

## Run

Build the lexer and parser from the .g4 grammar file by running  
```./build.sh```

### Parse and run file
Run the parser on a file  
```./run.sh <path/to/file.lang>```

### Generate parse tree
```antlr4-parse Lang.g4 begin_program <path/to/file.lang> -gui```

## Structure

**antlr-4.13.0-complete.jar**: Lexer and parser utilities  
**Lang.g4**: Defines the languages grammar  
**build.sh**: Uses antlr-4.13.0-complete.jar to generate a lexer and parser based on Lang.g4  
**run.sh**: Uses ProjectLang.java to parse a given input file  
**src**  
├─ **ProjectLang.java**: Uses lexer and parser generated from build.sh to parse an input file  
├─ **ProjectLangErrorListener.java**: Handles printing of lexing and parsing errors  
├─ **LangBaseListener.java**: Implements functions of the LangListener interface  
└─ **LangCustomListener.java**: Overrides LangListener functions by extending LangBaseListener.java  
**test_cases**: Directory storing test files written in the project language    
