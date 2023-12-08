package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.TypeInst;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
//import Classes.Utils.TypeExp;
public class Print extends Instruction {
    Expression exp;
    public Print(int line, int column, Expression exp) {
        super(line, column, TypeInst.PRINT);
        this.exp = exp;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        c3dgen.addComment("---------- Print ----------");
        if(exp != null) {
            ReturnValue value = exp.exec(env, c3dgen);
            if(value != null && value.type != Type.NULL) {
                if(value.type == Type.INT) {
                    c3dgen.addPrintf("d", "(int) " + value.strValue);
                } else if(value.type == Type.DOUBLE) {
                    c3dgen.addPrintf("f", "(float) " + value.strValue);
                } else if(value.type == Type.BOOLEAN) {
                    String lbl = c3dgen.newLbl();
                    c3dgen.addLabel(value.trueLbl);
                    c3dgen.addPrint("true");
                    c3dgen.addGoto(lbl);
                    c3dgen.addLabel(value.falseLbl);
                    c3dgen.addPrint("false");
                    c3dgen.addLabel(lbl);
                } else if(value.type == Type.CHAR) {
                    c3dgen.addPrintf("c", "(char) " + value.strValue);
                } else if(value.type == Type.STRING) {
                    String tmp1 = c3dgen.newTmp();
                    String tmp2 = c3dgen.newTmp();
                    c3dgen.addExpression(tmp1, "P", "+", String.valueOf(env.size));
                    c3dgen.addExpression(tmp1, tmp1, "+", "1");
                    c3dgen.addSetStack(tmp1, value.tmp);
                    c3dgen.newEnv(env.size);
                    c3dgen.generatePrintString();
                    c3dgen.addCall("_printString");
                    c3dgen.addGetStack(tmp2, "P");
                    c3dgen.prevEnv(env.size);
                }
                c3dgen.addPrint("\n");
                c3dgen.addComment("-------- Fin Print --------");
                return null;
            }
            c3dgen.addPrint("null");
        }
        c3dgen.addPrint("\n");
        c3dgen.addComment("-------- Fin Print --------");
        return null;
    }
}