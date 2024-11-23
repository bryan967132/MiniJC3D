package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Relational extends Expression {
    private Expression exp1;
    private String sign;
    private Expression exp2;
    public Relational(int line, int column, Expression exp1, String sign, Expression exp2) {
        super(line, column, TypeExp.RELATIONAL);
        this.exp1 = exp1;
        this.sign = sign;
        this.exp2 = exp2;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        switch(this.sign) {
            case "==":
                return equal(env, c3dgen);
            case "!=":
                return notEqual(env, c3dgen);
            case ">=":
                return moreEqual(env, c3dgen);
            case "<=":
                return lessEqual(env, c3dgen);
            case ">":
                return more(env, c3dgen);
            case "<":
                return less(env, c3dgen);
            default:
                return new ReturnValue(false, Type.NULL);
        }
    }
    public ReturnValue equal(Env env, C3DGen c3dgen) {
        ReturnValue value1 = exp1.exec(env, c3dgen);
        ReturnValue value2 = exp2.exec(env, c3dgen);
        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                return compare(value1.strValue, "==", value2.strValue, c3dgen);
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            return new ReturnValue(false, Type.NULL);
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            return compareStr(value1.strValue, "==", value2.strValue, c3dgen);
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        return new ReturnValue(false, Type.NULL);
    }
    public ReturnValue notEqual(Env env, C3DGen c3dgen) {
        ReturnValue value1 = exp1.exec(env, c3dgen);
        ReturnValue value2 = exp2.exec(env, c3dgen);
        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                return compare(value1.strValue, "!=", value2.strValue, c3dgen);
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            return new ReturnValue(false, Type.NULL);
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            return compareStr(value1.strValue, "!=", value2.strValue, c3dgen);
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        return new ReturnValue(false, Type.NULL);
    }
    public ReturnValue moreEqual(Env env, C3DGen c3dgen) {
        ReturnValue value1 = exp1.exec(env, c3dgen);
        ReturnValue value2 = exp2.exec(env, c3dgen);
        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                return compare(value1.strValue, ">=", value2.strValue, c3dgen);
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            return new ReturnValue(false, Type.NULL);
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            return compareStr(value1.strValue, ">=", value2.strValue, c3dgen);
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        return new ReturnValue(false, Type.NULL);
    }
    public ReturnValue lessEqual(Env env, C3DGen c3dgen) {
        ReturnValue value1 = exp1.exec(env, c3dgen);
        ReturnValue value2 = exp2.exec(env, c3dgen);
        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                return compare(value1.strValue, "<=", value2.strValue, c3dgen);
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            return new ReturnValue(false, Type.NULL);
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            return compareStr(value1.strValue, "<=", value2.strValue, c3dgen);
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        return new ReturnValue(false, Type.NULL);
    }
    public ReturnValue more(Env env, C3DGen c3dgen) {
        ReturnValue value1 = exp1.exec(env, c3dgen);
        ReturnValue value2 = exp2.exec(env, c3dgen);
        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                return compare(value1.strValue, ">", value2.strValue, c3dgen);
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            return new ReturnValue(false, Type.NULL);
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            return compareStr(value1.strValue, ">", value2.strValue, c3dgen);
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        return new ReturnValue(false, Type.NULL);
    }
    public ReturnValue less(Env env, C3DGen c3dgen) {
        ReturnValue value1 = exp1.exec(env, c3dgen);
        ReturnValue value2 = exp2.exec(env, c3dgen);
        if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
            if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                return compare(value1.strValue, "<", value2.strValue, c3dgen);
            }
            env.setError("Los tipos no son válidos para operaciones relacionales", exp2.line, exp2.column);
            return new ReturnValue(false, Type.NULL);
        }
        if(value1.type == value2.type && value1.type == Type.STRING) {
            return compareStr(value1.strValue, "<", value2.strValue, c3dgen);
        }
        env.setError("Los tipos no son válidos para operaciones relacionales", exp1.line, exp1.column);
        return new ReturnValue(false, Type.NULL);
    }
    public ReturnValue compare(String value1, String sign, String value2, C3DGen c3dgen) {
        checkLbls(c3dgen);
        c3dgen.addIf(value1, sign, value2, trueLbl);
        c3dgen.addGoto(falseLbl);
        return new ReturnValue(false, Type.BOOLEAN, trueLbl, falseLbl);
    }
    public ReturnValue compareStr(String value1, String sign, String value2, C3DGen c3dgen) {
        checkLbls(c3dgen);
        String tmp1 = c3dgen.newTmp();
        String tmp2 = c3dgen.newTmp();
        String tmp3 = c3dgen.newTmp();
        String tmp4 = c3dgen.newTmp();
        String lbl1 = c3dgen.newLbl();
        String lbl2 = c3dgen.newLbl();
        c3dgen.addAsign(tmp1, value1);
        c3dgen.addAsign(tmp2, value2);
        c3dgen.addLabel(lbl1);
        c3dgen.addGetHeap(tmp3, tmp1);
        c3dgen.addGetHeap(tmp4, tmp2);
        c3dgen.addIf(tmp3, "!=", tmp4, lbl2);
        c3dgen.addIf(tmp3, "==", "-1", lbl2);
        c3dgen.addExpression(tmp1, tmp1, "+", "1");
        c3dgen.addExpression(tmp2, tmp2, "+", "1");
        c3dgen.addGoto(lbl1);
        c3dgen.addLabel(lbl2);
        c3dgen.addIf(tmp3, sign, tmp4, trueLbl);
        c3dgen.addGoto(falseLbl);
        return new ReturnValue(false, Type.BOOLEAN, trueLbl, falseLbl);
    }
    public void checkLbls(C3DGen c3dgen) {
        trueLbl = c3dgen.validLabel(trueLbl);
        falseLbl = c3dgen.validLabel(falseLbl);
    }
}