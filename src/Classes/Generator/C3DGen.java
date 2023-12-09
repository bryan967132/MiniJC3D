package Classes.Generator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import Classes.Env.Env;
public class C3DGen {
    private int labelCount = 0;
    private int temporalCount = 0;
    private String name;
    private ArrayList<Instruction> C3DCode = new ArrayList<>();
    private ArrayList<Instruction> C3DMain = new ArrayList<>();
    private ArrayList<Instruction> C3DNatives = new ArrayList<>();
    private ArrayList<Instruction> C3DFunctions = new ArrayList<>();
    private ArrayList<Instruction> C3DGlobals = new ArrayList<>();
    private Map<String, String> temporalsSaved = new TreeMap<>();
    private ArrayList<String> temporals = new ArrayList<>();
    private ArrayList<String> declarations = new ArrayList<>();
    private boolean thereIsPow = false;
    private boolean thereIsMod = false;
    private boolean thereIsPrintString = false;
    private boolean thereIsConcatString = false;
    private boolean thereIsIntToString = false;
    private boolean thereIsDoubleToString = false;
    private boolean thereIsCharToString = false;
    private boolean thereAreDeclarations = false;
    private boolean[] keys = {false, false, false, false}; // main, natives, functions, global
    private boolean[] tmpKeys = new boolean[keys.length];
    public boolean thereAreDeclarations() {
        return thereAreDeclarations;
    }
    public void setFileName(String name) {
        this.name = name;
    }
    public void enableMain() {
        keys = new boolean[] {true, false, false, false};
    }
    public void enableNatives() {
        keys = new boolean[] {false, true, false, false};
    }
    public void enableFunction() {
        keys = new boolean[] {false, false, true, false};
    }
    public void enableGlobal() {
        keys = new boolean[] {false, false, false, true};
    }
    public boolean isMain() {
        return keys[0];
    }
    public boolean isNative() {
        return keys[1];
    }
    public boolean isFunction() {
        return keys[2];
    }
    public boolean isGlobal() {
        return keys[3];
    }
    public void saveSetting() {
        for(int i = 0; i < keys.length; i ++) {
            tmpKeys[i] = keys[i];
        }
    }
    public void restoreSetting() {
        for(int i = 0; i < tmpKeys.length; i ++) {
            keys[i] = tmpKeys[i];
        }
    }
    public String newTmp() {
        String tmp = "t" + temporalCount;
        temporalCount ++;
        temporals.add(tmp);
        temporalsSaved.put(tmp, tmp);
        return tmp;
    }
    public String newLbl() {
        String lbl = "L" + labelCount;
        labelCount ++;
        return lbl;
    }
    public String validLabel(String lbl) {
        if(lbl == null) {
            return newLbl();
        }
        return lbl;
    }
    private void addInstruction(Instruction instruction) {
        if(keys[0]) {
            C3DMain.add(instruction);
        } else if(keys[1]) {
            C3DNatives.add(instruction);
        } else if(keys[2]) {
            C3DFunctions.add(instruction);
        } else if(keys[3]) {
            C3DGlobals.add(instruction);
        }
    }
    private boolean repeatedGoto(Instruction instruction) {
        if(keys[0]) {
            if(C3DMain.get(C3DMain.size() - 1).type == Type.GOTO && C3DMain.get(C3DMain.size() - 1).toString().equals(instruction.toString())) {
                return true;
            }
        } else if(keys[1]) {
            if(C3DNatives.get(C3DNatives.size() - 1).type == Type.GOTO && C3DNatives.get(C3DNatives.size() - 1).toString().equals(instruction.toString())) {
                return true;
            }
        } else if(keys[2]) {
            if(C3DFunctions.get(C3DFunctions.size() - 1).type == Type.GOTO && C3DFunctions.get(C3DFunctions.size() - 1).toString().equals(instruction.toString())) {
                return true;
            }
        } else if(keys[3]) {
            if(C3DGlobals.get(C3DGlobals.size() - 1).type == Type.GOTO && C3DGlobals.get(C3DGlobals.size() - 1).toString().equals(instruction.toString())) {
                return true;
            }
        }
        return false;
    }
    public void addLabel(String lbl) {
        addInstruction(new Label(lbl));
    }
    public void addIf(String left, String operator, String right, String lbl) {
        addInstruction(new If(left, operator, right, lbl));
    }
    public void addGoto(String lbl) {
        if(!repeatedGoto(new Goto(lbl))) {
            addInstruction(new Goto(lbl));
        }
    }
    public void addAsign(String target, String value) {
        addInstruction(new Asign(target, value));
    }
    public void addExpression(String target, String left, String operator, String right) {
        addInstruction(new Expression(target, left, operator, right));
    }
    public void addComment(String comment) {
        addInstruction(new Generic("\t/* " + comment + " */"));
    }
    public void addPrintf(String type, String value) {
        addInstruction(new Printf(type, value));
    }
    public void addPrint(String value) {
        for(char ascii : value.toCharArray()) {
            addPrintf("c", "(char) " + ((int) ascii));
        }
    }
    public void addFunction(String id) {
        addInstruction(new Function(id));
    }
    public void addEnd() {
        addInstruction(new Return());
        addInstruction(new End());
    }
    public void addCall(String id) {
        addInstruction(new CallFunction(id));
    }
    public void addSetHeap(String index, String value) {
        addInstruction(new SetHeap(index, value));
    }
    public void addGetHeap(String target, String index) {
        addInstruction(new GetHeap(target, index));
    }
    public void addSetStack(String index, String value) {
        addInstruction(new SetStack(index, value));
    }
    public void addSetStackB(String index, String value) {
        addInstruction(new SetStack(index, value));
    }
    public void addGetStack(String target, String index) {
        addInstruction(new GetStack(target, index));
    }
    public void nextHeap() {
        addInstruction(new Expression("H", "H", "+", "1"));
    }
    public void newEnv(int size) {
        addInstruction(new Expression("P", "P", "+", String.valueOf(size)));
    }
    public void prevEnv(int size) {
        addInstruction(new Expression("P", "P", "-", String.valueOf(size)));
    }
    public void addDeclaration(String declaration) {
        declarations.add(declaration);
    }
    public void generatePow() {
        if(!thereIsPow) {
            // Temporales y Etiquetas
            String tmp1 = newTmp();
            String tmp2 = newTmp();
            String tmp3 = newTmp();
            String lbl1 = newLbl();
            String lbl2 = newLbl();
            String lbl3 = newLbl();
            String lbl4 = newLbl();
            //
            saveSetting();
            enableNatives();
            //
            addFunction("_pow");
            addExpression(tmp1, "P", "+", "1");
            addGetStack(tmp2, tmp1);
            addExpression(tmp1, tmp1, "+", "1");
            addGetStack(tmp3, tmp1);
            addIf(tmp3, "<=", "0", lbl2);
            addAsign(tmp1, tmp2);
            addLabel(lbl1);
            addIf(tmp3, "<=", "1", lbl3);
            addExpression(tmp2, tmp2, "*", tmp1);
            addExpression(tmp3, tmp3, "-", "1");
            addGoto(lbl1);
            addLabel(lbl2);
            addSetStack("P", "1");
            addGoto(lbl4);
            addLabel(lbl3);
            addSetStack("P", tmp2);
            addLabel(lbl4);
            addEnd();
            //
            restoreSetting();
            thereIsPow = true;
        }
    }
    public void generateMod() {
        if(!thereIsMod) {
            // Temporales y Etiquetas
            String tmp1 = newTmp();
            String tmp2 = newTmp();
            String tmp3 = newTmp();
            String lbl1 = newLbl();
            String lbl2 = newLbl();
            //
            saveSetting();
            enableNatives();
            //
            addFunction("_mod");
            addExpression(tmp1, "P", "+", "1");
            addGetStack(tmp2, tmp1);
            addExpression(tmp1, tmp1, "+", "1");
            addGetStack(tmp3, tmp1);
            addLabel(lbl1);
            addIf(tmp2, "<", tmp3, lbl2);
            addExpression(tmp2, tmp2, "-", tmp3);
            addGoto(lbl1);
            addLabel(lbl2);
            addSetStack("P", tmp2);
            addEnd();
            //
            restoreSetting();
            thereIsMod = true;
        }
    }
    public void generatePrintString() {
        if(!thereIsPrintString) {
            // Temporales y Etiquetas
            String tmp1 = newTmp();
            String tmp2 = newTmp();
            String tmp3 = newTmp();
            String lbl1 = newLbl();
            String lbl2 = newLbl();
            //
            saveSetting();
            enableNatives();
            //
            addFunction("_printString");
            addExpression(tmp1, "P", "+", "1");
            addGetStack(tmp2, tmp1);
            addLabel(lbl1);
            addGetHeap(tmp3, tmp2);
            addIf(tmp3, "==", "-1", lbl2);
            addPrintf("c", "(char) " + tmp3);
            addExpression(tmp2, tmp2, "+", "1");
            addGoto(lbl1);
            addLabel(lbl2);
            addEnd();
            //
            restoreSetting();
            thereIsPrintString = true;
        }
    }
    public void generateConcatString() {
        if(!thereIsConcatString) {
            // Temporales y Etiquetas
            String tmp1 = newTmp();
            String tmp2 = newTmp();
            String tmp3 = newTmp();
            String tmp4 = newTmp();
            String lbl1 = newLbl();
            String lbl2 = newLbl();
            String lbl3 = newLbl();
            String lbl4 = newLbl();
            //
            saveSetting();
            enableNatives();
            //
            addFunction("_concatString");
            addAsign(tmp1, "H");
            addExpression(tmp2, "P", "+",  "1");
            addGetStack(tmp3, tmp2);
            addLabel(lbl1);
            addGetHeap(tmp4, tmp3);
            addIf(tmp4, "==", "-1", lbl2);
            addSetHeap("H", tmp4);
            nextHeap();
            addExpression(tmp3, tmp3, "+", "1");
            addGoto(lbl1);
            addLabel(lbl2);
            addExpression(tmp2, "P", "+", "2");
            addGetStack(tmp3, tmp2);
            addLabel(lbl3);
            addGetHeap(tmp4, tmp3);
            addIf(tmp4, "==", "-1", lbl4);
            addSetHeap("H", tmp4);
            nextHeap();
            addExpression(tmp3, tmp3, "+", "1");
            addGoto(lbl3);
            addLabel(lbl4);
            addSetHeap("H", "-1");
            nextHeap();
            addSetStack("P", tmp1);
            addEnd();
            //
            restoreSetting();
            thereIsConcatString = true;
        }
    }
    public void generateIntToString() {
        if(!thereIsIntToString) {
            // Temporales y Etiquetas
            String tmp1 = newTmp();
            String tmp2 = newTmp();
            String tmp3 = newTmp();
            String tmp4 = newTmp();
            String tmp5 = newTmp();
            String tmp6 = newTmp();
            String tmp7 = newTmp();
            String lbl1 = newLbl();
            String lbl2 = newLbl();
            String lbl3 = newLbl();
            String lbl4 = newLbl();
            String lbl5 = newLbl();
            String lbl6 = newLbl();
            //
            saveSetting();
            enableNatives();
            //
            addFunction("_intToString");
            addAsign(tmp1, "H");
            addExpression(tmp2, "P", "+", "1");
            addGetStack(tmp3, tmp2);
            addIf(tmp3, "==", "0", lbl5);
            addIf(tmp3, ">", "0", lbl1);
            addAsign(tmp3, "-" + tmp3);
            addSetHeap("H", "45");
            nextHeap();
            addLabel(lbl1);
            addAsign(tmp4, tmp3);
            addAsign(tmp5, tmp3);
            addAsign(tmp6, "0");
            addAsign(tmp7, "1");
            addLabel(lbl2);
            addIf(tmp4, "<", "1", lbl3);
            addExpression(tmp7, tmp7, "*", "10");
            addExpression(tmp4, tmp4, "/", "10");
            addGoto(lbl2);
            addLabel(lbl3);
            addExpression(tmp7, tmp7, "/", "10");
            addLabel(lbl4);
            addIf(tmp7, "<", "1", lbl6);
            addExpression(tmp5, tmp3, "/", tmp7);
            addExpression(tmp6, tmp5, "+", "48");
            addSetHeap("H", tmp6);
            nextHeap();
            addExpression(tmp3, "(int) " + tmp3, "%", "(int) " + tmp7);
            addExpression(tmp7, tmp7, "/", "10");
            addGoto(lbl4);
            addLabel(lbl5);
            addSetHeap("H", "48");
            nextHeap();
            addLabel(lbl6);
            addSetHeap("H", "-1");
            nextHeap();
            addSetStack("P", tmp1);
            addEnd();
            //
            restoreSetting();
            thereIsIntToString = true;
        }
    }
    public void generateDoubleToString() {
        if(!thereIsDoubleToString) {
            // Temporales y Etiquetas
            String tmp1 = newTmp();
            String tmp2 = newTmp();
            String tmp3 = newTmp();
            String tmp4 = newTmp();
            String tmp5 = newTmp();
            String tmp6 = newTmp();
            String tmp7 = newTmp();
            String tmp8 = newTmp();
            String lbl1 = newLbl();
            String lbl2 = newLbl();
            String lbl3 = newLbl();
            String lbl4 = newLbl();
            String lbl5 = newLbl();
            String lbl6 = newLbl();
            String lbl7 = newLbl();
            String lbl8 = newLbl();
            String lbl9 = newLbl();
            String lblA = newLbl();
            //
            saveSetting();
            enableNatives();
            //
            addFunction("_doubleToString");
            addAsign(tmp1, "H");
            addExpression(tmp2, "P", "+", "1");
            addGetStack(tmp3, tmp2);
            addAsign(tmp8, "(int) " + tmp3);
            addIf(tmp8, "==", "0", lbl5);
            addIf(tmp3, ">", "0", lbl1);
            addAsign(tmp3, "-" + tmp3);
            addSetHeap("H", "45");
            nextHeap();
            addLabel(lbl1);
            addAsign(tmp4, tmp3);
            addAsign(tmp5, tmp3);
            addAsign(tmp6, "0");
            addAsign(tmp7, "1");
            addLabel(lbl2);
            addIf(tmp4, "<", "1", lbl3);
            addExpression(tmp7, tmp7, "*", "10");
            addExpression(tmp4, tmp4, "/", "10");
            addGoto(lbl2);
            addLabel(lbl3);
            addExpression(tmp7, tmp7, "/", "10");
            addLabel(lbl4);
            addIf(tmp7, "<", "1", lbl6);
            addExpression(tmp5, tmp3, "/", tmp7);
            addExpression(tmp6, tmp5, "+", "48");
            addSetHeap("H", tmp6);
            nextHeap();
            addExpression(tmp3, tmp3, "-", "(int) " + tmp5);
            addExpression(tmp7, tmp7, "/", "10");
            addGoto(lbl4);
            addLabel(lbl5);
            addSetHeap("H", "48");
            nextHeap();
            addLabel(lbl6);
            addSetHeap("H", "46");
            nextHeap();
            addIf(tmp3, "==", "0", lbl7);
            addGoto(lbl8);
            addLabel(lbl7);
            addSetHeap("H", "48");
            nextHeap();
            addGoto(lblA);
            addLabel(lbl8);
            addAsign(tmp4, "0");
            addLabel(lbl9);
            addIf(tmp4, ">=", "4", lblA);
            addIf(tmp3, "==", "0", lblA);
            addExpression(tmp3, tmp3, "*", "10");
            addExpression(tmp6, tmp3, "+", "48");
            addSetHeap("H", tmp6);
            nextHeap();
            addExpression(tmp3, tmp3, "-", "(int) " + tmp3);
            addExpression(tmp4, tmp4, "+", "1");
            addGoto(lbl9);
            addLabel(lblA);
            addSetHeap("H", "-1");
            nextHeap();
            addSetStack("P", tmp1);
            addEnd();
            //
            restoreSetting();
            thereIsDoubleToString = true;
        }
    }
    public void generateCharToString() {
        if(!thereIsCharToString) {
            // Temporales y Etiquetas
            String tmp1 = newTmp();
            String tmp2 = newTmp();
            String tmp3 = newTmp();
            String tmp4 = newTmp();
            //
            saveSetting();
            enableNatives();
            //
            addFunction("_charToString");
            addExpression(tmp1, "P", "+", "1");
            addGetStack(tmp2, tmp1);
            addAsign(tmp3, "H");
            addAsign(tmp4, "H");
            addSetHeap(tmp3, tmp2);
            nextHeap();
            addExpression(tmp3, tmp3, "+", "1");
            addSetHeap(tmp3, "-1");;
            nextHeap();
            addSetStack("P", tmp4);
            addEnd();
            //
            restoreSetting();
            thereIsCharToString = true;
        }
    }
    public void generateParserString(Classes.Utils.Type type) {
        switch (type) {
            case INT:
                generateIntToString();
                break;
            case DOUBLE:
                generateDoubleToString();
                break;
            case CHAR:
                generateCharToString();
                break;
            default:
                break;
        }
    }
    public void addCallParserString(Classes.Utils.Type type) {
        switch (type) {
            case INT:
                addCall("_intToString");
                break;
            case DOUBLE:
                addCall("_doubleToString");
                break;
            case CHAR:
                addCall("_charToString");
                break;
            default:
                break;
        }
    }
    public void generateFinalCode() {
        C3DCode.add(new Generic("/* ----- HEADER ----- */"));
        if(declarations.size() > 0) {
            thereAreDeclarations = true;
            declarations.add(0, "/* -- DECLARATIONS -- */");
            C3DCode.add(new Generic("#include \"./" + name + ".hpp\""));
        }
        C3DCode.add(new Generic("#include <stdio.h>"));
        C3DCode.add(new Generic(""));
        C3DCode.add(new Generic("float heap[30101999];"));
        C3DCode.add(new Generic("float stack[30101999];"));
        C3DCode.add(new Generic("float P;"));
        C3DCode.add(new Generic("float H;"));
        if(temporals.size() > 0) {
            C3DCode.add(new Generic("float " + temporals.stream().map(String::toString).collect(Collectors.joining(", ")) + ";"));
        }
        C3DCode.add(new Generic(""));
        // NATIVES
        if(C3DNatives.size() > 0) {
            C3DCode.add(new Generic("/* ------ NATIVES ------ */"));
            for(Instruction s : C3DNatives) {
                C3DCode.add(s);
            }
        }
        // FUNCTIONS
        if(C3DFunctions.size() > 0) {
            C3DCode.add(new Generic("/* ----- FUNCTIONS ----- */"));
            for(Instruction s : C3DFunctions) {
                C3DCode.add(s);
            }
        }
        // MAIN
        C3DCode.add(new Generic("/* ------ MAIN ------ */"));
        C3DCode.add(new Generic("int main() {"));
        C3DCode.add(new Generic("\tP = 0;"));
        C3DCode.add(new Generic("\tH = 0;"));
        if(C3DGlobals.size() > 0) {
            C3DCode.add(new Generic("/* ---- GLOBALES ---- */"));
            for(Instruction s : C3DGlobals) {
                C3DCode.add(s);
            }
            C3DCode.add(new Generic("/* -- END GLOBALES -- */"));
        }
        if(C3DCode.size() > 0) {
            for(Instruction s : C3DMain) {
                C3DCode.add(s);
            }
        }
        C3DCode.add(new Generic("\treturn 0;\n}"));
    }
    public String getFinalCode() {
        Map<String, Integer> newLabels = new TreeMap<>();
        int id = 0;
        for(int i = 0; i < C3DCode.size(); i ++) {
            if(C3DCode.get(i).type == Type.LABEL) {
                newLabels.put(C3DCode.get(i).lbl, id);
                id ++;
            }
        }
        for(int i = 0; i < C3DCode.size(); i ++) {
            if(C3DCode.get(i).type == Type.GOTO || C3DCode.get(i).type == Type.IF || C3DCode.get(i).type == Type.LABEL) {
                C3DCode.get(i).changeLbl(newLabels);
            }
        }
        Map<String, Integer> newTemps = new TreeMap<>();
        ArrayList<Type> types = new ArrayList<>(Arrays.asList(Type.CALLFUNCTION, Type.END, Type.FUNCTION, Type.GENERIC, Type.GOTO, Type.LABEL, Type.RETURN));
        id = 0;
        for(int i = 0; i < C3DCode.size(); i ++) {
            if(!types.contains(C3DCode.get(i).type)) {
                if(C3DCode.get(i).target != null && !newTemps.containsKey(C3DCode.get(i).target) && !C3DCode.get(i).target.equals("H") && !C3DCode.get(i).target.equals("P")) {
                    newTemps.put(C3DCode.get(i).target, id);
                    id ++;
                }
            }
        }
        for(int i = 0; i < C3DCode.size(); i ++) {
            if(!types.contains(C3DCode.get(i).type)) {
                C3DCode.get(i).changeTmp(newTemps);
            }
        }
        return C3DCode.stream().map(Instruction::toString).collect(Collectors.joining("\n"));
    }
    public String getDeclarations() {
        return declarations.stream().map(String::toString).collect(Collectors.joining("\n"));
    }
}