package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.TypeExp;
import Classes.Utils.TypeInst;
public class While extends Instruction {
    private Expression condition;
    private Block block;
    public While(int line, int column, Expression condition, Block block) {
        super(line, column, TypeInst.LOOP_WHILE);
        this.condition = condition;
        this.block = block;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        c3dgen.addComment("--------- While -----------");
        Env envWhile = new Env(env, env.name + " While");
        envWhile.coutinueLbl = c3dgen.newLbl();
        envWhile.returnLbl = env.returnLbl;
        envWhile.size = env.size;
        c3dgen.addLabel(envWhile.coutinueLbl);
        ReturnValue condition = this.condition.exec(env, c3dgen);
        if(this.condition.typeExp == TypeExp.CALL_FUNC) {
            condition.trueLbl = c3dgen.validLabel(condition.trueLbl);
            condition.falseLbl = c3dgen.validLabel(condition.falseLbl);
            c3dgen.addIf(condition.strValue, "==", "1", condition.trueLbl);
            c3dgen.addGoto(condition.falseLbl);
        }
        envWhile.breakLbl = condition.falseLbl;
        c3dgen.addLabel(condition.trueLbl);
        block.exec(envWhile, c3dgen);
        c3dgen.addGoto(envWhile.coutinueLbl);
        c3dgen.addLabel(condition.falseLbl);
        c3dgen.addComment("------- Fin While ---------");
        return null;
    }
}