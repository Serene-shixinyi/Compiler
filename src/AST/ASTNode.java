package AST;

import Util.position;

public abstract class ASTNode{
    public position pos;

    public ASTNode(position _pos) {this.pos = _pos;}

    abstract public void accept(ASTVisitor visitor);
}
