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
        c3dgen.addComment("--------- Switch ----------");
        Env switchEnv = new Env(env, env.name);
        switchEnv.returnLbl = env.returnLbl;
        switchEnv.coutinueLbl = env.coutinueLbl;
        switchEnv.breakLbl = c3dgen.newLbl();
        if(cases != null) {
            ReturnValue arg = this.arg.exec(env, c3dgen);
            for(Case case_ : cases) {
                case_.setCase(arg);
                case_.exec(switchEnv, c3dgen);
            }
        }
        if(_default != null) {
            c3dgen.addComment("--------- Default ---------");
            _default.exec(switchEnv, c3dgen);
            c3dgen.addComment("------- Fin Default -------");
        }
        c3dgen.addLabel(switchEnv.breakLbl);
        c3dgen.addComment("------- Fin Switch --------");
        return null;
    }
}
