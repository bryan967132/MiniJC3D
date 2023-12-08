package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Env.Symbol;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.TypeExp;
public class IncDec extends Expression {
    private String id;
    private String sign;
    public IncDec(int line, int column, String id, String sign) {
        super(line, column, sign.equals("++") ? TypeExp.INC : TypeExp.DEC);
        this.id = id;
        this.sign = sign;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        c3dgen.addComment(sign.equals("++") ? "------- Incremento --------" : "------- Decremento --------");
        Symbol value = env.getValueID(id, line, column);
        if(value != null) {
            String tmp1 = c3dgen.newTmp();
            String tmp2 = String.valueOf(value.position);
            if(!value.isGlobal) {
                tmp2 = c3dgen.newTmp();
                c3dgen.addExpression(tmp2, "P", "+", String.valueOf(value.position));
            }
            c3dgen.addGetStack(tmp1, tmp2);
            c3dgen.addExpression(tmp1, tmp1, sign.equals("++") ? "+" : "-", "1");
            c3dgen.addSetStack(tmp2, tmp1);
            c3dgen.addComment(sign.equals("++") ? "----- Fin Incremento ------" : "----- Fin Decremento ------");
            return new ReturnValue(true, tmp2, value.type);
        }
        c3dgen.addComment(sign.equals("++") ? "----- Fin Incremento ------" : "----- Fin Decremento ------");
        return null;
    }
}