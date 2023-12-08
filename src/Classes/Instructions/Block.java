package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Abstracts.Sentence;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.TypeExp;
import Classes.Utils.TypeInst;
import Classes.Utils.TypeSent;
public class Block extends Instruction {
    ArrayList<Sentence> instructions;
    public Block(int line, int column, ArrayList<Sentence> instructions) {
        super(line, column, TypeInst.BLOCK_INST);
        this.instructions = instructions;
    }
    public ReturnValue exec(Env env, C3DGen c3dgen) {
        Expression exp;
        Instruction inst;
        ReturnValue ret;
        for(Sentence instruction : instructions) {
            if(instruction.typeSent == TypeSent.EXPRESSION) {
                exp = (Expression) instruction;
                ret = exp.exec(env, c3dgen);
                if(ret != null && exp.typeExp != TypeExp.INC && exp.typeExp != TypeExp.DEC) {
                    return ret;
                }
            } else if(instruction.typeSent == TypeSent.INSTRUCTION) {
                inst = (Instruction) instruction;
                ret = inst.exec(env, c3dgen);
                if(ret != null) {
                    return ret;
                }
            }
        }
        return null;
    }
}