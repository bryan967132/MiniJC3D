package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Abstracts.Sentence;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
import Classes.Utils.TypeInst;
public class If extends Instruction {
    Expression condition;
    Block block;
    Sentence except;
    public If(int line, int column, Expression condition, Block block, Sentence except) {
        super(line, column, TypeInst.IF);
        this.condition = condition;        
        this.block = block;        
        this.except = except;        
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        c3dgen.addComment("----------- If ------------");
        ReturnValue condition = this.condition.exec(env, c3dgen);
        if(condition.type == Type.BOOLEAN) {
            if(this.condition.typeExp == TypeExp.CALL_FUNC) {
                condition.trueLbl = c3dgen.validLabel(condition.trueLbl);
                condition.falseLbl = c3dgen.validLabel(condition.falseLbl);
                c3dgen.addIf(condition.strValue, "==", "1", condition.trueLbl);
                c3dgen.addGoto(condition.falseLbl);
            }
            c3dgen.addLabel(condition.trueLbl);
            this.block.exec(env, c3dgen);
            String lbl = null;
            if(except != null) {
                lbl = c3dgen.newLbl();
                c3dgen.addGoto(lbl);
            }
            c3dgen.addLabel(condition.falseLbl);
            if(except != null) {
                this.except.exec(env, c3dgen);
                c3dgen.addLabel(lbl);
            }
            c3dgen.addComment("--------- Fin If ----------");
            return null;
        }
        c3dgen.addComment("--------- Fin If ----------");
        env.setError("No se evalúa una expresión lógica o relacional como condicion", line, column);
        return null;
    }
}