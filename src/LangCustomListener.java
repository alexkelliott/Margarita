import java.util.HashMap;

public class LangCustomListener extends LangBaseListener {
    // TerminalNode
    HashMap<String, Object> variables = new HashMap<>();

    @Override
    public void exitShout(LangParser.ShoutContext ctx) {
        String output = "";
        if (ctx.ID() != null) {
            output = variables.get(ctx.ID().getText()).toString();
        }
        else if (ctx.STRING() != null) {
            // raw_string has leading and trailing quote
            String raw_string = ctx.STRING().getText();
            output = raw_string.substring(1, raw_string.length() - 1);
        }

        System.out.println(output);
    }

    public void exitSet(LangParser.SetContext ctx) {
        Object new_value = null;
        if (ctx.INTLIT() != null) {
            new_value = Integer.valueOf(ctx.INTLIT().getText());;
        } else if (ctx.STRING() != null) {
            String raw_string = ctx.STRING().getText();
            new_value = raw_string.substring(1, raw_string.length()-1);
        }
        
        if (new_value != null) {
            variables.put(ctx.ID().getText(), new_value);
        } else {
            // TODO: Add error throw here instead
            System.out.println("Runtime error: No new value provided.");
        }
    }
}