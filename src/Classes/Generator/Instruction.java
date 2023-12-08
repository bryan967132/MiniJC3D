package Classes.Generator;
import java.util.Map;
public abstract class Instruction {
    public Type type;
    public String target;
    public String value;
    public String id;
    public String left;
    public String operator;
    public String right;
    public String index;
    public String lbl;
    public Instruction(Type type) {
        this.type = type;
    }
    public void changeLbl(Map<String, Integer> lbls) {
        lbl = "L" + lbls.get(lbl);
    }
    public void changeTmp(Map<String, Integer> tmps) {
        if(target != null && !target.equals("H") && !target.equals("P")) {
            if(target.matches("[t][0-9]+")) {
                target = "t" + tmps.get(target);
            } else if(target.matches("\\((int|char|float)\\) [t][0-9]+")) {
                target = target.split(" ")[0] + " t"+ tmps.get(target.split(" ")[1]);
            }
        }
        if(value != null && !value.equals("H") && !value.equals("P")) {
            if(value.matches("[t][0-9]+")) {
                value = "t" + tmps.get(value);
            } else if(value.matches("\\((int|char|float)\\) [t][0-9]+")) {
                value = value.split(" ")[0] + " t"+ tmps.get(value.split(" ")[1]);
            } else if(value.matches("\\-[t][0-9]+")) {
                value = "-t"+ tmps.get(value.substring(1));
            }
        }
        if(left != null && !left.equals("H") && !left.equals("P")) {
            if(left.matches("[t][0-9]+")) {
                left = "t" + tmps.get(left);
            } else if(left.matches("\\((int|char|float)\\) [t][0-9]+")) {
                left = left.split(" ")[0] + " t"+ tmps.get(left.split(" ")[1]);
            }
        }
        if(right != null && !right.equals("H") && !right.equals("P")) {
            if(right.matches("[t][0-9]+")) {
                right = "t" + tmps.get(right);
            } else if(right.matches("\\((int|char|float)\\) [t][0-9]+")) {
                right = right.split(" ")[0] + " t"+ tmps.get(right.split(" ")[1]);
            }
        }
        if(index != null && !index.equals("H") && !index.equals("P")) {
            if(index.matches("[t][0-9]+")) {
                index = "t" + tmps.get(index);
            } else if(index.matches("\\((int|char|float)\\) [t][0-9]+")) {
                index = index.split(" ")[0] + " t"+ tmps.get(index.split(" ")[1]);
            }
        }
    }
}