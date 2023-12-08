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
    public ReturnValue exec(Env env, C3DGen c3dgen){
        return null;
    }
    public boolean compare(ReturnValue value1, ReturnValue value2) {
        if(value1.type == value2.type) {
            if(value1.type == Type.INT) {
                // return Integer.parseInt(value1.value.toString()) == Integer.parseInt(value2.value.toString());
            }
            if(value1.type == Type.DOUBLE) {
                // return Double.parseDouble(value1.value.toString()) == Double.parseDouble(value2.value.toString());
            }
            if(value1.type == Type.BOOLEAN) {
                // return Boolean.parseBoolean(value1.value.toString()) && Boolean.parseBoolean(value2.value.toString());
            }
            if(value1.type == Type.CHAR) {
                // return ((int) value1.value.toString().charAt(0)) == ((int) value2.value.toString().charAt(0));
            }
            if(value1.type == Type.STRING) {
                // return value1.value.toString().equals(value2.value.toString());
            }
        }
        return false;
    }
    public ReturnValue compareStr(String value1, String sign, String value2, C3DGen c3dgen) {
        String tmp1 = c3dgen.newTmp();
        String tmp2 = c3dgen.newTmp();
        String tmp3 = c3dgen.newTmp();
        String tmp4 = c3dgen.newTmp();
        String lbl1 = c3dgen.newLbl();
        String lbl2 = c3dgen.newLbl();
        trueLbl = c3dgen.newLbl();
        falseLbl = c3dgen.newLbl();
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
}