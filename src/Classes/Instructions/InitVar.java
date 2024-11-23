package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Env.Symbol;
import Classes.Expressions.Primitive;
import Classes.Generator.C3DGen;
import Classes.Utils.IDValue;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;
public class InitVar extends Instruction {
    ArrayList<IDValue> inits;
    Type type;
    public InitVar(int line, int column, ArrayList<IDValue> inits, Type type) {
        super(line, column, TypeInst.INITVAR);
        this.inits = inits;
        this.type = type;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        c3dgen.addComment("------- Declaración -------");
        boolean flag = false;
        for(IDValue idvalue : inits) {
            if(flag) {
                c3dgen.addComment("---------------------------");
            }
            flag = true;
            if(idvalue.value != null) {
                ReturnValue value = idvalue.value.exec(env, c3dgen);
                if(value.type == type || type == Type.DOUBLE && value.type == Type.INT) {
                    init(idvalue.id, value, env, c3dgen);
                    continue;
                }
                env.setError("Los tipos no coinciden en la declaración", line, column);
            } else {
                ReturnValue value = null;
                switch(type) {
                    case INT:
                        value = new Primitive(idvalue.line, idvalue.column, 0, type).exec(env, c3dgen);
                        break;
                    case DOUBLE:
                        value = new Primitive(idvalue.line, idvalue.column, 0.0, type).exec(env, c3dgen);
                        break;
                    case BOOLEAN:
                        value = new Primitive(idvalue.line, idvalue.column, true, type).exec(env, c3dgen);
                        break;
                    case CHAR:
                        value = new Primitive(idvalue.line, idvalue.column, '0', type).exec(env, c3dgen);
                        break;
                    case STRING:
                        value = new Primitive(idvalue.line, idvalue.column, "", type).exec(env, c3dgen);
                        break;
                    default:
                        break;
                }
                init(idvalue.id, value, env, c3dgen);
            }
        }
        c3dgen.addComment("----- Fin Declaración -----");
        return null;
    }
    public void init(String id, ReturnValue value, Env env, C3DGen c3dgen) {
        Symbol newID = env.saveID(id, new ReturnValue(type), type, value.isTrue, line, column);
        String tmp = String.valueOf(env.size - 1);
        if(!newID.isGlobal) {
            tmp = c3dgen.newTmp();
            c3dgen.addExpression(tmp, "P", "+", String.valueOf(newID.position));
        }
        if(value.type != Type.BOOLEAN) {
            c3dgen.addSetStack(tmp, value.strValue);
        } else {
            String lbl = c3dgen.newLbl();
            c3dgen.addLabel(value.trueLbl);
            c3dgen.addSetStack(tmp, "1");
            c3dgen.addGoto(lbl);
            c3dgen.addLabel(value.falseLbl);
            c3dgen.addSetStack(tmp, "0");
            c3dgen.addLabel(lbl);
        }
    }
}