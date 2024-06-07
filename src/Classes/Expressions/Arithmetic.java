package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.Operations;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Arithmetic extends Expression {
    private Expression exp1;
    private String sign;
    private Expression exp2;
    private Type type;
    public Arithmetic(int line, int column, Expression exp1, String sign, Expression exp2) {
        super(line, column, TypeExp.ARITHMETIC_OP);
        this.exp1 = exp1;
        this.sign = sign;
        this.exp2 = exp2;
        this.type = Type.NULL;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        switch(this.sign) {
            case "+":
                return plus(env, c3dgen);
            case "-":
                if(exp1 != null) {
                    return minus(env, c3dgen);
                }
                return uminus(env, c3dgen);
            case "*":
                return mult(env, c3dgen);
            case "/":
                return div(env, c3dgen);
            case "^":
                return pow(env, c3dgen);
            case "%":
                return mod(env, c3dgen);
            default:
                return new ReturnValue(false, Type.NULL);
        }
    }
    public ReturnValue plus(Env env, C3DGen c3dgen) {
        ReturnValue value1 = exp1.exec(env, c3dgen);
        ReturnValue value2 = exp2.exec(env, c3dgen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.plus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.INT || type == Type.DOUBLE) {
                String tmp = c3dgen.newTmp();
                c3dgen.addExpression(tmp, value1.strValue, "+", value2.strValue);
                return new ReturnValue(true, tmp, type);
            }
            if(type == Type.STRING) {
                String valueStr1 = value1.strValue;
                String valueStr2 = value2.strValue;
                if(value1.type == Type.INT || value1.type == Type.DOUBLE || value1.type == Type.CHAR) {
                    c3dgen.generateParserString(value1.type);
                    String tmp1 = c3dgen.newTmp();
                    c3dgen.addExpression(tmp1, "P", "+", String.valueOf(env.size));
                    c3dgen.addExpression(tmp1, tmp1, "+", "1");
                    c3dgen.addSetStack(tmp1, value1.strValue);
                    c3dgen.newEnv(env.size);
                    c3dgen.addCallParserString(value1.type);
                    String tmp2 = c3dgen.newTmp();
                    c3dgen.addGetStack(tmp2, "P");
                    c3dgen.prevEnv(env.size);
                    valueStr1 = tmp2;
                } else if(value1.type == Type.BOOLEAN) {
                    if(exp1.typeExp == TypeExp.CALL_FUNC) {
                        String trueLbl = c3dgen.newLbl();
                        String falseLbl = c3dgen.newLbl();
                        String outLbl = c3dgen.newLbl();
                        c3dgen.addIf(value1.strValue, "==", "1", trueLbl);
                        String tmp = c3dgen.newTmp();
                        c3dgen.addGoto(falseLbl);
                        c3dgen.addLabel(trueLbl);
                        ReturnValue v = new Primitive(exp1.line, exp1.column, "true", Type.STRING).exec(env, c3dgen);
                        c3dgen.addAsign(tmp, v.strValue);
                        c3dgen.addGoto(outLbl);
                        c3dgen.addLabel(falseLbl);
                        v = new Primitive(exp1.line, exp1.column, "false", Type.STRING).exec(env, c3dgen);
                        c3dgen.addAsign(tmp, v.strValue);
                        c3dgen.addLabel(outLbl);
                        valueStr1 = tmp;
                    } else {
                        String outLbl = c3dgen.newLbl();
                        String tmp = c3dgen.newTmp();
                        c3dgen.addLabel(value1.trueLbl);
                        ReturnValue v = new Primitive(exp1.line, exp1.column, "true", Type.STRING).exec(env, c3dgen);
                        c3dgen.addAsign(tmp, v.strValue);
                        c3dgen.addGoto(outLbl);
                        c3dgen.addLabel(value1.falseLbl);
                        v = new Primitive(exp1.line, exp1.column, "false", Type.STRING).exec(env, c3dgen);
                        c3dgen.addAsign(tmp, v.strValue);
                        c3dgen.addLabel(outLbl);
                        valueStr1 = tmp;
                    }
                }
                if(value2.type == Type.INT || value2.type == Type.DOUBLE || value2.type == Type.CHAR) {
                    c3dgen.generateParserString(value2.type);
                    String tmp1 = c3dgen.newTmp();
                    c3dgen.addExpression(tmp1, "P", "+", String.valueOf(env.size));
                    c3dgen.addExpression(tmp1, tmp1, "+", "1");
                    c3dgen.addSetStack(tmp1, value2.strValue);
                    c3dgen.newEnv(env.size);
                    c3dgen.addCallParserString(value2.type);
                    String tmp2 = c3dgen.newTmp();
                    c3dgen.addGetStack(tmp2, "P");
                    c3dgen.prevEnv(env.size);
                    valueStr2 = tmp2;
                } else if(value2.type == Type.BOOLEAN) {
                    if(exp2.typeExp == TypeExp.CALL_FUNC) {
                        String lblTrue = c3dgen.newLbl();
                        String lblFalse = c3dgen.newLbl();
                        String lblOut = c3dgen.newLbl();
                        String tmp = c3dgen.newTmp();
                        c3dgen.addIf(value2.strValue, "==", "1", lblTrue);
                        c3dgen.addGoto(lblFalse);
                        c3dgen.addLabel(lblTrue);
                        ReturnValue v = new Primitive(exp2.line, exp2.column, "true", Type.STRING).exec(env, c3dgen);
                        c3dgen.addAsign(tmp, v.strValue);
                        c3dgen.addGoto(lblOut);
                        c3dgen.addLabel(lblFalse);
                        v = new Primitive(exp2.line, exp2.column, "false", Type.STRING).exec(env, c3dgen);
                        c3dgen.addAsign(tmp, v.strValue);
                        c3dgen.addLabel(lblOut);
                        valueStr2 = tmp;
                    } else {
                        String outLbl = c3dgen.newLbl();
                        String tmp = c3dgen.newTmp();
                        c3dgen.addLabel(value2.trueLbl);
                        ReturnValue v = new Primitive(exp2.line, exp2.column, "true", Type.STRING).exec(env, c3dgen);
                        c3dgen.addAsign(tmp, v.strValue);
                        c3dgen.addGoto(outLbl);
                        c3dgen.addLabel(value2.falseLbl);
                        v = new Primitive(exp2.line, exp2.column, "false", Type.STRING).exec(env, c3dgen);
                        c3dgen.addAsign(tmp, v.strValue);
                        c3dgen.addLabel(outLbl);
                        valueStr2 = tmp;
                    }
                }
                c3dgen.generateConcatString();
                String tmp1 = c3dgen.newTmp();
                c3dgen.addExpression(tmp1, "P", "+", String.valueOf(env.size));
                c3dgen.addExpression(tmp1, tmp1, "+", "1");
                c3dgen.addSetStack(tmp1, valueStr1);
                c3dgen.addExpression(tmp1, tmp1, "+", "1");
                c3dgen.addSetStack(tmp1, valueStr2);
                c3dgen.newEnv(env.size);
                c3dgen.addCall("_concatString");
                String tmp2 = c3dgen.newTmp();
                c3dgen.addGetStack(tmp2, "P");
                c3dgen.prevEnv(env.size);
                return new ReturnValue(true, tmp2, type);
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        return new ReturnValue(false, type);
    }
    public ReturnValue minus(Env env, C3DGen c3dgen) {
        ReturnValue value1 = exp1.exec(env, c3dgen);
        ReturnValue value2 = exp2.exec(env, c3dgen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.minus[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.INT || type == Type.DOUBLE) {
                String tmp = c3dgen.newTmp();
                c3dgen.addExpression(tmp, value1.strValue, "-", value2.strValue);
                return new ReturnValue(true, tmp, type);
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        return new ReturnValue(false, type);
    }
    public ReturnValue uminus(Env env, C3DGen c3dgen) {
        ReturnValue value2 = exp2.exec(env, c3dgen);
        type = value2.type;
        if(type != Type.NULL) {
            if(type == Type.INT || type == Type.DOUBLE) {
                String tmp = c3dgen.newTmp();
                c3dgen.addAsign(tmp, "-" + value2.strValue);
                return new ReturnValue(true, tmp, type);
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp2.line, exp2.column);
        return new ReturnValue(false, type);
    }
    public ReturnValue mult(Env env, C3DGen c3dgen) {
        ReturnValue value1 = exp1.exec(env, c3dgen);
        ReturnValue value2 = exp2.exec(env, c3dgen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.mult[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.INT || type == Type.DOUBLE) {
                String tmp = c3dgen.newTmp();
                c3dgen.addExpression(tmp, value1.strValue, "*", value2.strValue);
                return new ReturnValue(true, tmp, type);
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        return new ReturnValue(false, type);
    }
    public ReturnValue div(Env env, C3DGen c3dgen) {
        ReturnValue value1 = exp1.exec(env, c3dgen);
        ReturnValue value2 = exp2.exec(env, c3dgen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.div[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.INT || type == Type.DOUBLE) {
                String tmp1 = c3dgen.newTmp();
                String tmp2 = c3dgen.newTmp();
                String lbl1 = c3dgen.newLbl();
                String lbl2 = c3dgen.newLbl();
                c3dgen.addAsign(tmp1, value2.strValue);
                c3dgen.addIf(tmp1, "!=", "0", lbl1);
                c3dgen.addPrint("MathError\n");
                c3dgen.addAsign(tmp2, "0");
                c3dgen.addGoto(lbl2);
                c3dgen.addLabel(lbl1);
                c3dgen.addExpression(tmp2, value1.strValue, "/", tmp1);
                c3dgen.addLabel(lbl2);
                return new ReturnValue(true, tmp2, type);
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        return new ReturnValue(false, type);
    }
    public ReturnValue pow(Env env, C3DGen c3dgen) {
        ReturnValue value1 = exp1.exec(env, c3dgen);
        ReturnValue value2 = exp2.exec(env, c3dgen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.pow[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.INT || type == Type.DOUBLE) {
                c3dgen.generatePow();
                String tmp1 = c3dgen.newTmp();
                c3dgen.addExpression(tmp1, "P", "+", String.valueOf(env.size));
                c3dgen.addExpression(tmp1, tmp1, "+", "1");
                c3dgen.addSetStack(tmp1, value1.strValue);
                c3dgen.addExpression(tmp1, tmp1, "+", "1");
                c3dgen.addSetStack(tmp1, value2.strValue);
                c3dgen.newEnv(env.size);
                c3dgen.addCall("_pow");
                String tmp2 = c3dgen.newTmp();
                c3dgen.addGetStack(tmp2, "P");
                c3dgen.prevEnv(env.size);
                return new ReturnValue(true, tmp2, type);
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        return new ReturnValue(false, type);
    }
    public ReturnValue mod(Env env, C3DGen c3dgen) {
        ReturnValue value1 = exp1.exec(env, c3dgen);
        ReturnValue value2 = exp2.exec(env, c3dgen);
        int t1 = value1.type.ordinal();
        int t2 = value2.type.ordinal();
        type = !(t1 == 5 || t2 == 5) ? Operations.mod[t1][t2] : Type.NULL;
        if(type != Type.NULL) {
            if(type == Type.INT || type == Type.DOUBLE) {
                c3dgen.generateMod();
                String tmp1 = c3dgen.newTmp();
                c3dgen.addExpression(tmp1, "P", "+", String.valueOf(env.size));
                c3dgen.addExpression(tmp1, tmp1, "+", "1");
                c3dgen.addSetStack(tmp1, value1.strValue);
                c3dgen.addExpression(tmp1, tmp1, "+", "1");
                c3dgen.addSetStack(tmp1, value2.strValue);
                c3dgen.newEnv(env.size);
                c3dgen.addCall("_mod");
                String tmp2 = c3dgen.newTmp();
                c3dgen.addGetStack(tmp2, "P");
                c3dgen.prevEnv(env.size);
                return new ReturnValue(true, tmp2, type);
            }
        }
        env.setError("Los tipos no son válidos para operaciones aritméticas", exp1.line, exp1.column);
        return new ReturnValue(false, type);
    }
}