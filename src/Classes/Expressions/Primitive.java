package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Primitive extends Expression {
    public Object value;
    Type type;
    public Primitive(int line, int column, Object value, Type type) {
        super(line, column, TypeExp.PRIMITIVE);
        this.value = value;
        this.type = type;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        switch(type) {
            case INT:
                return new ReturnValue(false, Integer.parseInt(value.toString()), type);
            case DOUBLE:
                return new ReturnValue(false, Double.parseDouble(value.toString()), type);
            case BOOLEAN:
                trueLbl = c3dgen.validLabel(trueLbl);
                falseLbl = c3dgen.validLabel(falseLbl);
                if(value.toString().equals("true")) {
                    c3dgen.addGoto(trueLbl);
                } else {
                    c3dgen.addGoto(falseLbl);
                }
                return new ReturnValue(false, type, trueLbl, falseLbl, value.toString().equals("true"));
            case CHAR:
                return new ReturnValue(false, (int) value.toString().charAt(0), type);
            default:
                value = value.toString().replace("\\n", "\n");
                value = value.toString().replace("\\t", "\t");
                value = value.toString().replace("\\\"", "\"");
                value = value.toString().replace("\\'", "\'");
                value = value.toString().replace("\\\\", "\\");
                String tmp = c3dgen.newTmp();
                c3dgen.addAsign(tmp, "H");
                for(char ascii : value.toString().toCharArray()) {
                    c3dgen.addSetHeap("H", String.valueOf((int) ascii));
                    c3dgen.nextHeap();
                }
                c3dgen.addSetHeap("H", "-1");
                c3dgen.nextHeap();
                return new ReturnValue(true, tmp, type);
        }
    }
}