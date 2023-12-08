package Classes.Generator;
public class If extends Instruction {
    public If(String left, String operator, String right, String lbl) {
        super(Type.IF);
        this.left = left;
        this.operator = operator;
        this.right = right;
        this.lbl = lbl;
    }
    public String toString() {
        return "\tif(" + left + " " + operator + " " + right + ") goto " + lbl + ";";
    }
}