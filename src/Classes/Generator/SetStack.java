package Classes.Generator;
public class SetStack extends Instruction {
    public SetStack(String index, String value) {
        super(Type.SETSTACK);
        this.index = index;
        this.value = value;
    }
    public String toString() {
        return "\tstack[(int) " + index + "] = " + value + ";";
    }
}