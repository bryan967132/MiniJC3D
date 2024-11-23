package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.TypeExp;
import Classes.Utils.TypeInst;
public class DoWhile extends Instruction {
    private Expression condition;
    private Block block;
    public DoWhile(int line, int column, Expression condition, Block block) {
        super(line, column, TypeInst.DOWHILE);
        this.condition = condition;
        this.block = block;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        c3dgen.addComment("------- Do While ----------");
        Env envWhile = new Env(env, env.name + " While");
        envWhile.coutinueLbl = c3dgen.newLbl();
        condition.trueLbl = envWhile.coutinueLbl;
        envWhile.returnLbl = env.returnLbl;
        envWhile.size = env.size;
        envWhile.breakLbl = c3dgen.newLbl();
        condition.falseLbl = envWhile.breakLbl;
        c3dgen.addLabel(envWhile.coutinueLbl);
        block.exec(envWhile, c3dgen);
        ReturnValue condition = this.condition.exec(env, c3dgen);
        if(this.condition.typeExp == TypeExp.CALLFUNC) {
            c3dgen.addIf(condition.strValue, "==", "1", this.condition.trueLbl);
            c3dgen.addGoto(this.condition.falseLbl);
        }
        c3dgen.addLabel(this.condition.falseLbl);
        c3dgen.addComment("----- Fin Do While --------");
        return null;
    }
}