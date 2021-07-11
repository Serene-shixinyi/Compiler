package AST;

import Util.position;

public class MainFuncDclNode extends FuncDclNode{
    public MainFuncDclNode(position _pos, BlockStNode _block) {
        super(_pos, _block);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
