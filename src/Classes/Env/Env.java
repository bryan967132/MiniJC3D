package Classes.Env;
import java.util.Map;
import java.util.TreeMap;
import Classes.Instructions.Function;
import Classes.Utils.ReturnValue;
import Classes.Utils.Type;
import Classes.Utils.TypeError;
import Classes.Utils.Error;
import Classes.Utils.Outs;
public class Env {
    private Map<String, Symbol> ids = new TreeMap<>();
    private Map<String, Function> functions = new TreeMap<>();
    private Env previous;
    public String name;
    public int size = 0;
    public String coutinueLbl;
    public String breakLbl;
    public String returnLbl;
    public Env(Env previous, String name) {
        this.previous = previous;
        this.name = name;
    }
    public Symbol saveID(String id, Object value, Type type, boolean isTrue, int line, int column) {
        if(!this.ids.containsKey(id)) {
            this.ids.put(id, new Symbol(id, type, null, size, name.equals("Global"), isTrue));
            size ++;
            return this.ids.get(id);
        }
        setError("Redeclaración de variable existente", line, column);
        return null;
    }
    public Symbol getValueID(String id, int line, int column) {
        Env current = this;
        while(current != null) {
            if(current.ids.containsKey(id)) {
                return current.ids.get(id);
            }
            current = current.previous;
        }
        setError("Acceso a variable inexistente. '" + id +"'", line, column);
        return null;
    }
    public boolean reasignID(String id, ReturnValue value, int line, int column) {
        Env current = this;
        while(current != null) {
            if(current.ids.containsKey(id)) {
                if(current.ids.get(id).type == value.type || current.ids.get(id).type == Type.DOUBLE && value.type == Type.INT) {
                    //current.ids.get(id).value = value.value;
                    return true;
                }
                setError("Los tipos no coinciden en la asignación. Intenta asignar un \"" + getType(value.type) + "\" a un \"" + getType(current.ids.get(id).type) + "\"", line, column);
                return false;
            }
            current = current.previous;
        }
        setError("Resignación de valor a variable inexistente", line, column);
        return false;
    }
    public boolean saveFunction(Function func) {
        if(!this.functions.containsKey(func.id)) {
            this.functions.put(func.id, func);
            return true;
        }
        setError("Redefinición de función existente", func.line, func.column);
        return false;
    }
    public Function getFunction(String id, int line, int column) {
        Env current = this;
        while(current != null) {
            if(current.functions.containsKey(id)) {
                return current.functions.get(id);
            }
            current = current.previous;
        }
        setError("Acceso a función inexistente", line, column);
        return null;
    }
    public Env getGlobal() {
        Env env = this;
        while(env.previous != null) {
            env = env.previous;
        }
        return env;
    }
    public void setPrint(String print) {
        Outs.printConsole.add(print);
    }
    public void setError(String errorD, int line, int column) {
        if(!match(errorD, line, column)) {
            Outs.errors.add(new Error(line, column, TypeError.SEMANTIC, errorD));
        }
    }
    public boolean match(String err, int line, int column) {
        for(Error s : Outs.errors) {
            if(s.toString().equals(new Error(line, column, TypeError.SEMANTIC, err).toString())) {
                return true;
            }
        }
        return false;
    }
    public String getType(Type type) {
        switch (type) {
            case INT:
                return "INT";
            case DOUBLE:
                return "DOUBLE";
            case BOOLEAN:
                return "BOOLEAN";
            case STRING:
                return "STRING";
            case CHAR:
                return "CHAR";
            default:
                return "null";
        }
    }
}