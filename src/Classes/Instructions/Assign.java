package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Env.Symbol;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.TypeInst;
public class Assign extends Instruction {
    private String id;
    private Expression value;
    public Assign(int line, int column, String id, Expression value) {
        super(line, column, TypeInst.ASIGN_ID);
        this.id = id;
        this.value = value;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        c3dgen.addComment("------- Asignacion --------");
        Symbol variable = env.getValueID(id, line, column);
        if(variable != null) {
            ReturnValue value = this.value.exec(env, c3dgen);
            String tmp = String.valueOf(variable.position);
            if(!variable.isGlobal) {
                tmp = c3dgen.newTmp();
                c3dgen.addExpression(tmp, "P", "+", String.valueOf(variable.position));
            }
            c3dgen.addSetStack(tmp, value.strValue);
        }
        c3dgen.addComment("----- Fin Asignacion ------");
        return null;
    }
}