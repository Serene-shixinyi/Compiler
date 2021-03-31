package AST;

import Util.position;

public abstract class FuncDclNode extends DclNode{
    public BlockStNode block;

    public FuncDclNode(position _pos, BlockStNode _block) {
        super(_pos);
        block = _block;
    }
}
