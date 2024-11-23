package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Ternary extends Expression {
    private Expression condition;
    private Expression yes;
    private Expression no;
    public Ternary(int line, int column, Expression condition, Expression yes, Expression no) {
        super(line, column, TypeExp.TERNARY);
        this.condition = condition;
        this.yes = yes;
        this.no = no;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        ReturnValue condition = this.condition.exec(env, c3dgen);
        if(condition.type != Type.BOOLEAN) {
            env.setError("El tipo de dato de la condición no es aceptable", line, column);
            return new ReturnValue(false, Type.NULL);
        }
        if(this.condition.typeExp == TypeExp.CALLFUNC) {
            condition.trueLbl = c3dgen.validLabel(condition.trueLbl);
            condition.falseLbl = c3dgen.validLabel(condition.falseLbl);
            c3dgen.addIf(condition.strValue, "==", "1", condition.trueLbl);
            c3dgen.addGoto(condition.falseLbl);
        }
        c3dgen.addLabel(condition.trueLbl);
        ReturnValue yes = this.yes.exec(env, c3dgen);
        String tmp = c3dgen.newTmp();
        c3dgen.addAsign(tmp, yes.strValue);
        String lbl = c3dgen.newLbl();
        c3dgen.addGoto(lbl);
        c3dgen.addLabel(condition.falseLbl);
        ReturnValue no = this.no.exec(env, c3dgen);
        c3dgen.addAsign(tmp, no.strValue);
        c3dgen.addLabel(lbl);
        return new ReturnValue(true, tmp, yes.type);
    }
}