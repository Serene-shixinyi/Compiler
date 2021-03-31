import AST.ProgramNode;
import Frontend.ASTBuilder;
import Frontend.SemanticChecker;
import Util.MxErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import parser.MxLexer;
import parser.MxParser;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception{

        String name = "C:\\Users\\Serene\\IdeaProjects\\1\\src\\test.mx";
        InputStream input = new FileInputStream(name);

//        try {
            ProgramNode ASTRoot;

            MxLexer lexer = new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());
            MxParser parser = new MxParser(new CommonTokenStream(lexer));;
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());
            ParseTree parseTreeRoot = parser.program();
            ASTBuilder astBuilder = new ASTBuilder();
            ASTRoot = (ProgramNode)astBuilder.visit(parseTreeRoot);
            new SemanticChecker().visit(ASTRoot);
//        } catch (error er) {
//            System.err.println(er.toString());
//            throw new RuntimeException();
//        }
    }
}
