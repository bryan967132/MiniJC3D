package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Env.Symbol;
import Classes.Expressions.AccessID;
import Classes.Expressions.Arithmetic;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.TypeInst;
public class AddSub extends Instruction {
    private String id;
    private String sign;
    private Expression exp;
    public AddSub(int line, int column, String id, String sign, Expression exp) {
        super(line, column, sign.equals("+=") ? TypeInst.ADD : TypeInst.SUB);
        this.id = id;
        this.sign = sign;
        this.exp = exp;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        c3dgen.addComment(sign.equals("+=") ? "----------- Add -----------" : "----------- Sub -----------");
        Symbol value = env.getValueID(id, line, column);
        if(value != null) {
            ReturnValue newValue = new Arithmetic(line, column,
                new AccessID(line, column, id),
                sign.equals("+=") ? "+" : "-",
                exp
            ).exec(env, c3dgen);
            String tmp = String.valueOf(value.position);
            if(!value.isGlobal) {
                tmp = c3dgen.newTmp();
                c3dgen.addExpression(tmp, "P", "+", String.valueOf(value.position));
            }
            c3dgen.addSetStack(tmp, newValue.strValue);
        }
        c3dgen.addComment(sign.equals("+=") ? "--------- Fin Add ---------" : "--------- Fin Sub ---------");
        return null;
    }
}