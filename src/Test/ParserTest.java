package Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.C3DGen;
import Classes.Instructions.Function;
import Classes.Instructions.MainMethod;
import Classes.Utils.Outs;
import Classes.Utils.TypeInst;
import Language.Parser;
import Language.Scanner;
public class ParserTest {
    public static void main(String[] args) throws Exception {
        try {
            String input = readInput("./Inputs/Input1.minij");
            Scanner scanner = new Scanner(
                new BufferedReader(
                    new StringReader(input)
                )
            );
            Parser parser = new Parser(scanner);
            parser.parse();
            Classes.Utils.Outs.resetOuts();
            Env global = new Env(null, "Global");
            C3DGen c3dGen = new C3DGen();
            c3dGen.enableGlobal();
            MainMethod mainMethod = null;
            for(Instruction instruction : parser.execute) {
                try {
                    if(instruction.typeInst == TypeInst.MAIN) {
                        mainMethod = (MainMethod) instruction;
                    } else if(instruction.typeInst == TypeInst.INIT_FUNCTION) {
                        ((Function) instruction).save(global, c3dGen);
                    } else if(instruction.typeInst == TypeInst.INIT_ID) {
                        instruction.exec(global, c3dGen);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            for(Instruction instruction : parser.execute) {
                try {
                    if(instruction.typeInst == TypeInst.INIT_FUNCTION) {
                        instruction.exec(global, c3dGen);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            c3dGen.enableMain();
            if(mainMethod != null) {
                mainMethod.exec(global, c3dGen);
            }
            c3dGen.generateFinalCode();
            exportCode(c3dGen.getFinalCode(), "Test", "Test.cpp");
            if(c3dGen.thereAreDeclarations()) {
                exportCode(c3dGen.getDeclarations(), "Test", "Test.hpp");
            }
            System.out.println(Outs.getStringOuts());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static String readInput(String path) {
        try {
            File archivo = new File(path);
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String texto = "";
            String linea;
            while ((linea = br.readLine()) != null) {
                texto += linea + "\n";
            }
            br.close();
            fis.close();
            return texto;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return "";
    }
    public static void exportCode(String content, String dir, String fileName) {
        try {
            File dirName = new File("./Out/" + dir);
            if(!dirName.exists()) {
                dirName.mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream("./Out/" + dir + "/" + fileName), 
                    "utf-8"
                )
            );
            writer.write(content);
            writer.close();
        } catch (Exception e) {}
    }
}