package Classes.Generator;
public class Function extends Instruction {
    public Function(String id) {
        super(Type.FUNCTION);
        this.id = id;
    }
    public String toString() {
        return "void " + id + "() {";
    }
}