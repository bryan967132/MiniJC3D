package Classes.Instructions;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.TypeInst;
public class Break extends Instruction {
    public Break(int line, int column) {
        super(line, column, TypeInst.BREAK);
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        c3dgen.addComment("---------- Break ----------");
        c3dgen.addGoto(env.breakLbl);
        c3dgen.addComment("-------- Fin Break --------");
        return null;
    }
}