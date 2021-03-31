package AST;

public interface ASTVisitor {

    //Type Node
    void visit(IntTypeNode node);
    void visit(BoolTypeNode node);
    void visit(StringTypeNode node);
    void visit(ClassTypeNode node); //Identifier
    void visit(ArrayTypeNode node);
    void visit(VoidTypeNode node);

    //Expression Node
    void visit(NewExprNode node);
    void visit(FuncCallExprNode node);
    void visit(ClassMemberNode node);
    void visit(ArrayIndexNode node);
    void visit(SuffixOpNode node);
    void visit(PrefixOpNode node);
    void visit(UnaryOpNode node);
    void visit(BinaryOpNode node);
    void visit(IdentifierExprNode node);
    void visit(ThisExprNode node);

    void visit(IntConstNode node);
    void visit(StringConstNode node);
    void visit(BoolConstNode node);
    void visit(NullConstNode node);

    //Statement Node
    void visit(BlockStNode node);
    void visit(ExprStNode node);

    void visit(AssignDclNode node);
    void visit(SimpleDclNode node);
    void visit(OneDclNode node);

    void visit(ReturnNode node);
    void visit(BreakNode node);
    void visit(ContinueNode node);
    void visit(IfelseStNode node);

    void visit(ForStNode node);
    void visit(WhileStNode node);

    void visit(MainFuncDclNode node);
    void visit(OtherFuncDclNode node);
    void visit(ClassDclNode node);

    void visit(ProgramNode node);
}
