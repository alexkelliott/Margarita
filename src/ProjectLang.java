import java.lang.reflect.Method;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;


public class ProjectLang {

    public static boolean errorDetected;

    public static void main(String[] args) throws Exception {

        // Validate number of arguments
        if (args.length != 1) {
            System.out.println("Usage: ./run.sh [path to Lang program]");
            return;
        }

        // Check if supplied argument exists as a file
        File LangProgram = new File(args[0]);
        if (!LangProgram.exists()) {
            System.out.println("Cannot find Lang program file: \"" + args[0] + "\"");
            System.out.println("Usage: ./run.sh [path to Lang program]");
            return;
        }

        // Instantiate parser and lexer
        LangLexer lexer = new LangLexer((CharStream)null);
        LangParser parser = new LangParser((TokenStream)null);
        parser.addParseListener(new LangCustomListener());

        // Add custom error listeners
        ProjectLang.errorDetected = false;
        lexer.removeErrorListeners();
        parser.removeErrorListeners();
        ProjectLangErrorListener err_listen = new ProjectLangErrorListener();
        lexer.addErrorListener(err_listen);
        parser.addErrorListener(err_listen);

        // Instantiate input stream for the lexer
        InputStream is = new FileInputStream(args[0]);

        try {
            // Run the lexer
            CharStream input = CharStreams.fromStream(is);
            lexer.setInputStream(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            tokens.fill();

            // Run the parser
            parser.setTokenStream(tokens);
            parser.setTrace(false);
            String parserName = "LangParser";
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            Class<? extends Parser> parserClass = cl.loadClass(parserName).asSubclass(Parser.class);
            try {
                Method startRule = parserClass.getMethod("begin_program");
                ParserRuleContext tree = (ParserRuleContext)startRule.invoke(parser, (Object[])null);
            }
            catch (NoSuchMethodException nsme) {
                System.err.println("No method for rule lang_program or it has arguments");
            }
        }
        finally {
            if (is != null)
                is.close();
        }
    }
}

/*
public class Simplerlang {
    public static void main(String[] args) {
        try {
            CharStream input = (CharStream) new ANTLRFileStream("test.simpler");
            simplerlangLexer lexer = new simplerlangLexer(input);
            simplerlangParser parser = new simplerlangParser(new CommonTokenStream(lexer));
            parser.addParseListener(new simplerlangCustomListener());
            parser.program();
        } catch (IOException ex) {
            Logger.getLogger(Simplerlang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
*/