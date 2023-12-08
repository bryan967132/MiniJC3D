package Classes.Generator;
public class Return extends Instruction {
    public Return() {
        super(Type.RETURN);
    }
    public String toString() {
        return "\treturn;";
    }
}