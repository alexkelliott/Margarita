package margarita;
import margarita.*;
import margarita.variables.*;
import java.util.List;
import java.util.ArrayList;

public class FunctionDef {

	public class Type_name {
		Type type;
		String name;

		public Type_name(Type type, String name) {
			this.type = type;
			this.name = name;
		}
	}

	public MargParser.FunctionContext ctx;
	public ArrayList<Type_name> param_info;

	public FunctionDef(MargParser.FunctionContext ctx) {
		this.ctx = ctx;
		this.param_info = new ArrayList<Type_name>();
		List<MargParser.ParameterContext> paramCtxList = ctx.parameter_list().parameter();
		
		for (int i = 0; i < paramCtxList.size(); i++) {
			Type type = Type.valueOf(paramCtxList.get(i).type.getText().toUpperCase());
			String name =  paramCtxList.get(i).ID().getText();
			param_info.add(new Type_name(type, name));
		}
	}
}