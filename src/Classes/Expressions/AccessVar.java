package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Env.Symbol;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class AccessVar extends Expression {
    private String id;
    public AccessVar(int line, int column, String id) {
        super(line, column, TypeExp.ACCESS_ID);
        this.id = id;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        c3dgen.addComment("--------- Acceso ----------");
        Symbol value = env.getValueID(id, line, column);
        if(value != null) {
            String tmp1;
            String tmp2 = String.valueOf(value.position);
            if(!value.isGlobal) {
                tmp2 = c3dgen.newTmp();
                tmp1 = c3dgen.newTmp();
                c3dgen.addExpression(tmp2, "P", "+", String.valueOf(value.position));
            } else {
                tmp1 = c3dgen.newTmp();
            }
            c3dgen.addGetStack(tmp1, tmp2);
            c3dgen.addComment("------- Fin Acceso --------");
            return new ReturnValue(true, tmp1, value.type);
        }
        c3dgen.addComment("------- Fin Acceso --------");
        return new ReturnValue(false, Type.NULL);
    }
}