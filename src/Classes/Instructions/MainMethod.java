package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Expressions.CallFunction;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.TypeInst;
public class MainMethod extends Instruction {
    Expression method;
    public MainMethod(int line, int column, Expression method) {
        super(line, column, TypeInst.MAIN);
        this.method = method;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        method.exec(env, c3dgen);
        return null;
    }
    public String getID() {
        return ((CallFunction) method).id;
    }
}