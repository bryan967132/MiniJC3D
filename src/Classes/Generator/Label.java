package Classes.Generator;
public class Label extends Instruction {
    public Label(String lbl) {
        super(Type.LABEL);
        this.lbl = lbl;
    }
    public String toString() {
        return lbl + ":";
    }
}