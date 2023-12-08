package Classes.Generator;
public class CallFunction extends Instruction {
    public CallFunction(String id) {
        super(Type.CALLFUNCTION);
        this.id = id;
    }
    public String toString() {
        return "\t" + id + "();";
    }
}