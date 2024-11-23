package Classes.Expressions;
import java.util.ArrayList;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Instructions.Function;
import Classes.Utils.Parameter;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class CallFunction extends Expression {
    public String id;
    ArrayList<Expression> args;
    public CallFunction(int line, int column, String id, ArrayList<Expression> args) {
        super(line, column, TypeExp.CALLFUNC);
        this.id = id;
        this.args = args;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        Function func = env.getFunction(id, this.line, this.column);
        if(func != null) {
            if(func.params.size() == args.size()) {
                c3dgen.addComment("----- Llamada Funcion -----");
                String tmp = c3dgen.newTmp();
                if(func.params.size() > 0) {
                    c3dgen.addComment("------- Parametros --------");
                    c3dgen.addExpression(tmp, "P", "+", String.valueOf(env.size + 1));
                    for(int i = 0; i < func.params.size(); i ++) {
                        ReturnValue value = args.get(i).exec(env, c3dgen);
                        Parameter param = func.params.get(i);
                        if(value.type == param.type || param.type == Type.DOUBLE && value.type == Type.INT) {
                            if(value.type != Type.BOOLEAN) {
                                c3dgen.addSetStack(tmp, value.strValue);
                            } else {
                                c3dgen.addLabel(value.trueLbl);
                                c3dgen.addSetStack(tmp, "1");
                                String lbl = c3dgen.newLbl();
                                c3dgen.addGoto(lbl);
                                c3dgen.addLabel(value.falseLbl);
                                c3dgen.addSetStack(tmp, "0");
                                c3dgen.addLabel(lbl);
                            }
                            if(i < func.params.size() - 1) {
                                c3dgen.addExpression(tmp, tmp, "+", "1");
                            }
                            continue;
                        }
                        env.setError("El tipo de dato del parámetro no es el esperado", param.line, param.column);
                        c3dgen.addComment("----- Fin Parametros ------");
                        c3dgen.addComment("--- Fin Llamada Funcion ---");
                        return null;
                    }
                    c3dgen.addComment("----- Fin Parametros ------");
                }
                c3dgen.newEnv(env.size);
                c3dgen.addCall(id);
                c3dgen.addGetStack(tmp, "P");
                c3dgen.prevEnv(env.size);
                if(func.type != Type.NULL) {
                    c3dgen.addComment("--- Fin Llamada Funcion ---");
                    return new ReturnValue(true, tmp, func.type);
                }
                c3dgen.addComment("--- Fin Llamada Funcion ---");
                return null;
            }
            env.setError("Cantidad errónea de parámetros enviados", this.line, this.column);
            c3dgen.addComment("--- Fin Llamada Funcion ---");
            return null;
        }
        c3dgen.addComment("--- Fin Llamada Funcion ---");
        return null;
    }
}