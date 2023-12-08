package Classes.Generator;
public class End extends Instruction {
    public End() {
        super(Type.END);
    }
    public String toString() {
        return "}\n";
    }
}