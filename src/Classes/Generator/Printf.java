package Classes.Generator;
public class Printf extends Instruction {
    public String type;
    public Printf(String type, String value) {
        super(Type.PRINTF);
        this.type = type;
        this.value = value;
    }
    public String toString() {
        return "\tprintf(\"%" + type + "\", " + value + ");";
    }
}