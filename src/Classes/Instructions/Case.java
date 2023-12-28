package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;
public class Case extends Instruction {
    Expression _case;
    Block block;
    ReturnValue caseEvaluate;
    String trueLbl;
    String falseLbl;
    public Case(int line, int column, Expression _case, Block block){
        super(line, column, TypeInst.CASE);
        this._case = _case;
        this.block = block;
    }
    public void setCase(ReturnValue caseEvaluate) {
        this.caseEvaluate = caseEvaluate;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        c3dgen.addComment("---------- Case -----------");
        ReturnValue evaluate = compare(caseEvaluate, _case, env, c3dgen);
        c3dgen.addLabel(evaluate.trueLbl);
        block.exec(env, c3dgen);
        c3dgen.addLabel(evaluate.falseLbl);
        c3dgen.addComment("-------- Fin Case ---------");
        return null;
    }
    public ReturnValue compare(ReturnValue value1, Expression value2, Env env, C3DGen c3dgen) {
        if(value1.type != Type.NULL) {
            if(value1.type == Type.INT) {
                return compare(value1.strValue, "==", value2.exec(env, c3dgen).strValue, c3dgen);
            }
            if(value1.type == Type.DOUBLE) {
                return compare(value1.strValue, "==", value2.exec(env, c3dgen).strValue, c3dgen);
            }
            if(value1.type == Type.BOOLEAN) {
                // FALLIDO
                return compareBool(value1, "==", value2, env, c3dgen);
            }
            if(value1.type == Type.CHAR) {
                return compare(value1.strValue, "==", value2.exec(env, c3dgen).strValue, c3dgen);
            }
            if(value1.type == Type.STRING) {
                return compareStr(value1.strValue, "==", value2.exec(env, c3dgen).strValue, c3dgen);
            }
        }
        return null;
    }
    public ReturnValue compare(String value1, String sign, String value2, C3DGen c3dgen) {
        checkLbls(c3dgen);
        c3dgen.addIf(value1, sign, value2, trueLbl);
        c3dgen.addGoto(falseLbl);
        return new ReturnValue(false, Type.BOOLEAN, trueLbl, falseLbl);
    }
    public ReturnValue compareBool(ReturnValue value1, String sign, Expression value2, Env env, C3DGen c3dgen) {
        String andLbl;
        checkLbls(c3dgen);
        andLbl = value1.trueLbl;
        value2.trueLbl = trueLbl;
        falseLbl = value2.falseLbl = value1.falseLbl;
        c3dgen.addLabel(andLbl);
        value2.exec(env, c3dgen);
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