package IR;

import IR.IRtype.ClassType;
import IR.inst.*;
import IR.operand.GlobalVar;
import IR.operand.Operand;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class IRPrinter implements IRVisitor{
    private File outputFile;
    private OutputStream os;
    private PrintWriter writer;
    private String ind = "    ";

    public IRPrinter(String filename) {
        try{
            outputFile = new File(filename);
            assert outputFile.exists() || outputFile.createNewFile();
            os = new FileOutputStream(filename, false);
            writer = new PrintWriter(os);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    public void print(String string) {
        writer.print(string);
    }
    public void println(String string) {
        writer.println(string);
    }
    public void run(Root root) {
        root.accept(this);
        try {
            writer.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    //Root
    @Override
    public void visit(Root root){
        println("source_filename = \"test.mx\"");
        println("");
        //class
        for(String name: root.typeTable.typeTable.keySet()) {
            if(name.equals("void") || name.equals("int") || name.equals("string") || name.equals("bool")) continue;
            println(((ClassType)root.get_type(name)).classToString());
        }
        println("");
        //global var
        for(String name: root.globalScope.varMap.keySet()) {
            Operand tmp = root.globalScope.get_var(name);
            if(tmp instanceof GlobalVar) println(((GlobalVar) tmp).defineToString());
        }
        println("");
        //external func
        for(String name: root.globalScope.funcMap.keySet()) {
            Function tmp = root.globalScope.get_func(name);
            if(tmp.is_external) println(tmp.declareToString());
        }
        println("");
        for(String name: root.globalScope.funcMap.keySet()) {
            Function tmp = root.globalScope.get_func(name);
            if(!tmp.is_external) tmp.accept(this);
        }
        println("");

    }
    //Function
    @Override
    public void visit(Function function){
        println(function.declareToString().replace("declare", "define") + " {");
        BasicBlock bb = function.entryBlock;
        while (bb != null) {
            bb.accept(this); // visit BasicBlock
            if (bb.nxt != null)
                println("");
            bb = bb.nxt;
        }
        println("}");
    }
    //BasicBlock
    @Override
    public void visit(BasicBlock block){
        print(block.name + ":");
        /*
        if (block.pre !=null) {
            print(" ".repeat(50 - (block.name.length() + 1)));
            print("; preds = ");
            int size = block.getPredecessors().size();
            int cnt = 0;
            for (BasicBlock predecessor : block.getPredecessors()) {
                print(predecessor.toString());
                if (++cnt != size)
                    print(", ");
        }
        */
        println("");
        Inst ptr = block.fir;
        while (ptr != null) {
            ptr.accept(this); // visit IRInstruction
            ptr = ptr.nxt;
        }}
    //Instuction
    @Override
    public void visit(Allo inst){
        println(ind + inst.toString());
    }
    @Override
    public void visit(Binary inst){
        println(ind + inst.toString());
    }
    @Override
    public void visit(BitCastTo inst){
        println(ind + inst.toString());
    }
    @Override
    public void visit(Branch inst){
        println(ind + inst.toString());
    }
    @Override
    public void visit(Call inst){
        println(ind + inst.toString());
    }
    @Override
    public void visit(GetElementPtr inst){
        println(ind + inst.toString());
    }
    @Override
    public void visit(Icmp inst){
        println(ind + inst.toString());
    }
    @Override
    public void visit(Jump inst){
        println(ind + inst.toString());
    }
    @Override
    public void visit(Load inst){
        println(ind + inst.toString());
    }
    @Override
    public void visit(Phi inst){
        println(ind + inst.toString());
    }
    @Override
    public void visit(Return inst){
        println(ind + inst.toString());
    }
    @Override
    public void visit(Store inst){
        println(ind + inst.toString());
    }
}
