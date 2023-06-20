import java.util.HashMap;

public class LangCustomListener extends LangBaseListener {

    HashMap<String, String> variables = new HashMap<>();

    @Override
    public void exitShout(LangParser.ShoutContext ctx) {
        String raw_string = "";
        if(ctx.ID() != null) {
            raw_string = variables.get(ctx.ID().getText());
        }
        else if(ctx.STRING() != null) {
            raw_string = ctx.STRING().getText();
        }

        // raw_string has leading and trailing quote
        String trimmed_string = raw_string.substring(1, raw_string.length()-1);
        System.out.println(trimmed_string);
    }

    public void exitSet(LangParser.SetContext ctx) {
        if(ctx.STRING() != null) {
            // raw_string has leading and trailing quote
            // String raw_string = ctx.STRING().getText();
            // String trimmed_string = raw_string.substring(1, raw_string.length()-1);
            // System.out.println(ctx.ID().getText() + " " + ctx.STRING().getText());
            variables.put(ctx.ID().getText(), ctx.STRING().getText());
        }
    }
}