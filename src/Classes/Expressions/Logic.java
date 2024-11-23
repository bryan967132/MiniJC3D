package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Logic extends Expression {
    private Expression exp1;
    private String sign;
    private Expression exp2;
    public Logic(int line, int column, Expression exp1, String sign, Expression exp2) {
        super(line, column, TypeExp.LOGIC);
        this.exp1 = exp1;
        this.sign = sign;
        this.exp2 = exp2;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        switch(this.sign) {
            case "&&":
                return and(env, c3dgen);
            case "||":
                return or(env, c3dgen);
            case "!":
                return not(env, c3dgen);
            default:
            return new ReturnValue(false, Type.NULL);
        }
    }
    public ReturnValue and(Env env, C3DGen c3dgen) {
        String andLbl;
        checkLbls(c3dgen);
        andLbl = exp1.trueLbl = c3dgen.newLbl();
        exp2.trueLbl = trueLbl;
        exp1.falseLbl = exp2.falseLbl = falseLbl;
        ReturnValue value1 = exp1.exec(env, c3dgen);
        if(value1.type != Type.BOOLEAN) {
            env.setError("Los tipos no son válidos para operaciones lógicas", exp1.line, exp1.column);
            return new ReturnValue(false, Type.NULL);
        }
        c3dgen.addLabel(andLbl);
        ReturnValue value2 = exp2.exec(env, c3dgen);
        if(value2.type == Type.BOOLEAN) {
            return new ReturnValue(false, Type.BOOLEAN, trueLbl, falseLbl);
        }
        env.setError("Los tipos no son válidos para operaciones lógicas", exp2.line, exp2.column);
        return new ReturnValue(false, Type.NULL);
    }
    public ReturnValue or(Env env, C3DGen c3dgen) {
        String orLbl;
        checkLbls(c3dgen);
        orLbl = exp1.falseLbl = c3dgen.newLbl();
        exp2.falseLbl = falseLbl;
        exp1.trueLbl = exp2.trueLbl = trueLbl;
        ReturnValue value1 = exp1.exec(env, c3dgen);
        if(value1.type != Type.BOOLEAN) {
            env.setError("Los tipos no son válidos para operaciones lógicas", exp1.line, exp1.column);
            return new ReturnValue(false, Type.NULL);
        }
        c3dgen.addLabel(orLbl);
        ReturnValue value2 = exp2.exec(env, c3dgen);
        if(value2.type == Type.BOOLEAN) {
            return new ReturnValue(false, Type.BOOLEAN, trueLbl, falseLbl);
        }
        env.setError("Los tipos no son válidos para operaciones lógicas", exp2.line, exp2.column);
        return new ReturnValue(false, Type.NULL);
    }
    public ReturnValue not(Env env, C3DGen c3dgen) {
        checkLbls(c3dgen);
        exp2.falseLbl = trueLbl;
        exp2.trueLbl = falseLbl;
        ReturnValue value2 = exp2.exec(env, c3dgen);
        if(value2.type == Type.BOOLEAN) {
            return new ReturnValue(false, Type.BOOLEAN, trueLbl, falseLbl);
        }
        env.setError("Los tipos no son válidos para operaciones lógicas", exp2.line, exp2.column);
        return new ReturnValue(false, Type.NULL);
    }
    public void checkLbls(C3DGen c3dgen) {
        trueLbl = c3dgen.validLabel(trueLbl);
        falseLbl = c3dgen.validLabel(falseLbl);
    }
}