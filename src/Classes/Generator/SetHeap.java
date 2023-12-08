package Classes.Generator;
public class SetHeap extends Instruction {
    public SetHeap(String index, String value) {
        super(Type.SETHEAP);
        this.index = index;
        this.value = value;
    }
    public String toString() {
        return "\theap[(int) " + index + "] = " + value + ";";
    }
}