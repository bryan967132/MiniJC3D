package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Abstracts.Statement;
import Classes.Env.Env;
import Classes.Expressions.Primitive;
import Classes.Generator.C3DGen;
import Classes.Utils.InitializeFor;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;
import Classes.Utils.TypeSent;
public class For extends Instruction {
    private InitializeFor inits;
    private Expression condition;
    private ArrayList<Statement> updates;
    private Block instructions;
    public For(int line, int column, InitializeFor inits, Expression condition, ArrayList<Statement> updates, Block instructions) {
        super(line, column, TypeInst.LOOP_FOR);
        this.inits = inits;
        this.condition = condition;
        this.updates = updates;
        this.instructions = instructions;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        c3dgen.addComment("---------- For ------------");
        Env envFor = new Env(env, env.name + " For");
        if(inits != null) {
            if(inits.inits != null) {
                inits.inits.exec(envFor, c3dgen);
            }
            else if(inits.asigns != null) {
                for(Instruction asign : inits.asigns) {
                    asign.exec(envFor, c3dgen);
                }
            }
        }
        envFor.coutinueLbl = c3dgen.newLbl();
        envFor.returnLbl = env.returnLbl;
        envFor.size = env.size;
        condition.trueLbl = c3dgen.newLbl();
        condition.falseLbl = c3dgen.newLbl();
        c3dgen.addLabel(envFor.coutinueLbl);
        ReturnValue condition = this.condition != null ? this.condition.exec(envFor, c3dgen) : new Primitive(line, column, "true", Type.BOOLEAN).exec(envFor, c3dgen);
        c3dgen.addLabel(condition.trueLbl);
        instructions.exec(envFor, c3dgen);
        if(updates != null) {
            for(Statement update : updates) {
                if(update.typeSent == TypeSent.EXPRESSION) {
                    ((Expression) update).exec(envFor, c3dgen);
                }
                else if(update.typeSent == TypeSent.INSTRUCTION) {
                    ((Instruction) update).exec(envFor, c3dgen);
                }
            }
        }
        c3dgen.addGoto(envFor.coutinueLbl);
        c3dgen.addLabel(condition.falseLbl);
        c3dgen.addComment("-------- End For ----------");
        return null;
    }
}