package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.Parameter;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;
public class Function extends Instruction {
    public String id;
    public ArrayList<Parameter> params;
    public Block block;
    public Type type;
    public Function(int line, int column, String id, ArrayList<Parameter> params, Block block, Type type) {
        super(line, column, TypeInst.INIT_FUNCTION);
        this.id = id;
        this.params = params;
        this.block = block;
        this.type = type;
    }
    public void save(Env env, C3DGen c3dgen) {
        env.saveFunction(this);
        c3dgen.addDeclaration("void " + id + "();");
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        c3dgen.saveSetting();
        c3dgen.enableFunction();
        c3dgen.addFunction(id);
        Env envFunc = new Env(env, "Funcion" + id);
        generateC3D(envFunc, c3dgen);
        c3dgen.addEnd();
        c3dgen.restoreSetting();
        return null;
    }
    public void generateC3D(Env env, C3DGen c3dgen) {
        env.returnLbl = c3dgen.newLbl();
        env.size = 1;
        for(int i = 0; i < params.size(); i ++) {
            env.saveID(params.get(i).id, new ReturnValue(params.get(i).type), params.get(i).type, false, params.get(i).line, params.get(i).column);
        }
        block.exec(env, c3dgen);
        c3dgen.addLabel(env.returnLbl);
    }
}