

public class MargCustomListener extends MargBaseListener {

    @Override
    public void exitShout(MargParser.ShoutContext ctx) {
        if(ctx.STRING() != null){
            // raw_string has leading and trailing quote
            String raw_string = ctx.STRING().getText();
            String trimmed_string = raw_string.substring(1, raw_string.length()-1);
            System.out.println(trimmed_string);
        }
    }
}