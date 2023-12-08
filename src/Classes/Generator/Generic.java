package Classes.Generator;
public class Generic extends Instruction {
    public String arg;
    public Generic(String arg) {
        super(Type.GENERIC);
        this.arg = arg;
    }
    public String toString() {
        return arg;
    }
}