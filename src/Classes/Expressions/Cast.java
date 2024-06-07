package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Cast extends Expression {
    Expression exp;
    Type destiny;
    public Cast(int line, int column, Expression exp, Type destiny) {
        super(line, column, TypeExp.CAST);
        this.exp = exp;
        this.destiny = destiny;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        ReturnValue value = exp.exec(env, c3dgen);
        if(value.type != Type.NULL) {
            if(destiny == Type.INT) {
                if(value.type == Type.DOUBLE) {
                    return new ReturnValue(true, value.strValue, destiny);
                }
                if(value.type == Type.STRING) {
                    // if(isValidNumericalString(value.value.toString())) {
                    //     return new ReturnType(Integer.parseInt(value.value.toString()), destiny);
                    // }
                    // env.setError("La cadena \"" + value.value + "\" no tiene formato numérico para castear a \"" + destiny.getValue() + "\"", exp.line, exp.column);
                    // return new ReturnValue(false, Type.NULL);
                }
                if(value.type == Type.CHAR) {
                    return new ReturnValue(true, value.strValue, destiny);
                }
                env.setError("No hay casteo de \"" + value.type.getValue() + "\" a \"" + destiny.getValue() + "\"", exp.line, exp.column);
                return new ReturnValue(false, Type.NULL);
            }
            if(destiny == Type.DOUBLE) {
                if(value.type == Type.INT) {
                    return new ReturnValue(true, value.strValue, destiny);
                }
                if(value.type == Type.STRING) {
                    // if(isValidNumericalString(value.value.toString())) {
                    //     return new ReturnType(Double.parseDouble(value.value.toString()), destiny);
                    // }
                    // env.setError("La cadena \"" + value.value + "\" no tiene formato numérico para castear a \"" + destiny.getValue() + "\"", exp.line, exp.column);
                    // return new ReturnValue(false, Type.NULL);
                }
                env.setError("No hay casteo de \"" + value.type.getValue() + "\" a \"" + destiny.getValue() + "\"", exp.line, exp.column);
                return new ReturnValue(false, Type.NULL);
            }
            if(destiny == Type.STRING) {
                if(value.type != Type.BOOLEAN && value.type != Type.STRING) {
                    c3dgen.generateParserString(value.type);
                    String tmp1 = c3dgen.newTmp();
                    c3dgen.addExpression(tmp1, "P", "+", String.valueOf(env.size));
                    c3dgen.addExpression(tmp1, tmp1, "+", "1");
                    c3dgen.addSetStack(tmp1, value.strValue);
                    c3dgen.newEnv(env.size);
                    c3dgen.addCallParserString(value.type);
                    String tmp2 = c3dgen.newTmp();
                    c3dgen.addGetStack(tmp2, "P");
                    c3dgen.prevEnv(env.size);
                    return new ReturnValue(true, tmp2, destiny);
                }
                env.setError("No hay casteo de \"" + value.type.getValue() + "\" a \"" + destiny.getValue() + "\"", exp.line, exp.column);
                return new ReturnValue(false, Type.NULL);
            }
            if(destiny == Type.CHAR) {
                if(value.type == Type.INT) {
                    return new ReturnValue(true, value.strValue, destiny);
                }
                if(value.type == Type.STRING) {
                    String tmp = c3dgen.newTmp();
                    c3dgen.addGetHeap(tmp, value.strValue);
                    return new ReturnValue(true, tmp, destiny);
                }
            }
            env.setError("No hay casteo de \"" + value.type.getValue() + "\" a \"" + destiny.getValue() + "\"", exp.line, exp.column);
            return new ReturnValue(false, Type.NULL);
        }
        env.setError("Error: No hay casteo de valores null", line, column);
        return new ReturnValue(false, Type.NULL);
    }
}