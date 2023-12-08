package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.TypeInst;
public class Switch extends Instruction {
    Expression arg;
    ArrayList<Case> cases;
    Block _default;
    public Switch(int line, int column, Expression arg, ArrayList<Case> cases, Block _default) {
        super(line, column, TypeInst.SWITCH);
        this.arg = arg;
        this.cases = cases;
        this._default = _default;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        return null;
    }
}
