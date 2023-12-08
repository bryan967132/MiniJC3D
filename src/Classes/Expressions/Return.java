package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Return extends Expression {
    Expression exp;
    public Return(int line, int column, Expression exp) {
        super(line, column, TypeExp.RETURN);
        this.exp = exp;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        c3dgen.addComment("--------- Return ----------");
        if(exp != null) {
            ReturnValue exp = this.exp.exec(env, c3dgen);
            if(exp.type != Type.BOOLEAN) {
                c3dgen.addSetStack("P", exp.strValue);
            } else {
                String tmp = c3dgen.newLbl();
                c3dgen.addLabel(exp.trueLbl);
                c3dgen.addSetStack("P", "1");
                c3dgen.addGoto(tmp);
                c3dgen.addLabel(exp.falseLbl);
                c3dgen.addSetStack("P", "0");
                c3dgen.addLabel(tmp);
            }
            c3dgen.addGoto(env.returnLbl);
            c3dgen.addComment("------- Fin Return --------");
            return null;
        }
        c3dgen.addSetStack("P", "0");
        c3dgen.addGoto(env.returnLbl);
        c3dgen.addComment("------- Fin Return --------");
        return null;
    }
}