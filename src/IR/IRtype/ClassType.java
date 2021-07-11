package IR.IRtype;

import AST.ClassDclNode;
import IR.Function;

import java.util.ArrayList;

public class ClassType extends BaseType {
    public String name;
    public int size = 0;
    public ArrayList<BaseType> members = new ArrayList<>();
//    public IRScope scope;
    public ClassDclNode dclnode;
    public Function constructor;
    public ClassType(String _name) {
        super();
        name = _name;
//        scope = new IRScope(null);
        dclnode = null;
        constructor = null;
    }
    public void addMember(BaseType member) {
        members.add(member);
        size += member.size();
    }

    @Override
    public String toString() {
        return "%struct." + name;
    }
    public String classToString() {
        StringBuilder string = new StringBuilder(this.toString());
        string.append(" = type { ");
        for (int i = 0; i < members.size(); i++) {
            string.append(members.get(i).toString());
            if (i != members.size() - 1)
                string.append(", ");
        }
        string.append(" }");
        return string.toString();
    }
    @Override
    public boolean sameType(BaseType b) {
        return b instanceof ClassType && (((ClassType)b).name).equals(name);
    }
    @Override
    public int size() { return size; }
}
