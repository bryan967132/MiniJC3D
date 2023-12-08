package Classes.Generator;
public class GetStack extends Instruction {
    public GetStack(String target, String index) {
        super(Type.GETSTACK);
        this.target = target;
        this.index = index;
    }
    public String toString() {
        return "\t" + target + " = stack[(int) " + index + "];";
    }
}