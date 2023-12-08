package Classes.Generator;
public class Asign extends Instruction {
    public Asign(String target, String value) {
        super(Type.ASIGN);
        this.target = target;
        this.value = value;
    }
    public String toString() {
        return "\t" + target + " = " + value + ";";
    }
}