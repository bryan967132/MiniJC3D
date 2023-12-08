package Classes.Generator;
public class Goto extends Instruction {
    public Goto(String lbl) {
        super(Type.GOTO);
        this.lbl = lbl;
    }
    public String toString() {
        return "\tgoto " + lbl + ";";
    }
}