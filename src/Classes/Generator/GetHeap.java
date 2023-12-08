package Classes.Generator;
public class GetHeap extends Instruction {
    public GetHeap(String target, String index) {
        super(Type.GETHEAP);
        this.target = target;
        this.index = index;
    }
    public String toString() {
        return "\t" + target + " = heap[(int) " + index + "];";
    }
}