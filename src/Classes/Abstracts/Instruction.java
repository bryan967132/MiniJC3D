package Classes.Abstracts;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.TypeInst;
import Classes.Utils.TypeSent;
public abstract class Instruction extends Sentence {
    public TypeInst typeInst;
    public Instruction(int line, int column, TypeInst typeInst) {
        super(line, column, TypeSent.INSTRUCTION);
        this.typeInst = typeInst;
    }
    public abstract ReturnValue exec(Env env, C3DGen c3dgen);
}