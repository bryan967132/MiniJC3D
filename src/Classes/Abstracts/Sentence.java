package Classes.Abstracts;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Utils.ReturnValue;
import Classes.Utils.TypeSent;
public abstract class Sentence {
    public int line;
    public int column;
    public TypeSent typeSent;
    public Sentence(int line, int column, TypeSent typeSent) {
        this.line = line;
        this.column = column;
        this.typeSent = typeSent;
    }
    public abstract ReturnValue exec(Env env, C3DGen c3dgen);
}