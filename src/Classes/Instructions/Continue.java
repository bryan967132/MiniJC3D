package Classes.Instructions;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.TypeInst;
public class Continue extends Instruction {
    public Continue(int line, int column) {
        super(line, column, TypeInst.CONTINUE);
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        c3dgen.addComment("-------- Continue ---------");
        c3dgen.addGoto(env.breakLbl);
        c3dgen.addComment("------ Fin Continue -------");
        return null;
    }
}