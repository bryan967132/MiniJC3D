package Classes.Generator;
public class Expression extends Instruction {
    public Expression(String target, String left, String operator, String right) {
        super(Type.EXPRESSION);
        this.target = target;
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
    public String toString() {
        return "\t" + target + " = " + left + " " + operator + " " + right + ";";
    }
}