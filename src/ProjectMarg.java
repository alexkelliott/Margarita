package margarita;
import margarita.*;

import java.lang.reflect.Method;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;


public class ProjectMarg {

    public static boolean errorDetected;
    public static String filename;

    public static void main(String[] args) throws Exception {

        // Validate number of arguments
        if (args.length != 1) {
            System.out.println("Usage: ./run.sh [path to Marg program]");
            return;
        }

        // Check if supplied argument exists as a file
        File MargProgram = new File(args[0]);
        if (!MargProgram.exists()) {
            System.out.println("Cannot find Marg program file: \"" + args[0] + "\"");
            System.out.println("Usage: ./run.sh [path to Marg program]");
            return;
        }
        filename = MargProgram.getName();

        // Instantiate parser and lexer
        MargLexer lexer = new MargLexer((CharStream)null);
        MargParser parser = new MargParser((TokenStream)null);
        // parser.addParseListener(new MargCustomListener());

        // Add custom error listeners
        ProjectMarg.errorDetected = false;
        lexer.removeErrorListeners();
        parser.removeErrorListeners();
        ProjectMargErrorListener err_listen = new ProjectMargErrorListener();
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
            ParseTree tree = parser.begin_program();
            if (!errorDetected) {
                new MargCustomVisitor().visit(tree);
            }
        } finally {
            if (is != null)
                is.close();
        }
    }
}