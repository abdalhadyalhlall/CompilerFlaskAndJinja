package AST.AtomExpression;

import AST.Statment;
import AST.StatmentRoot;

public abstract class ExpressionAtom extends StatmentRoot {
    public String name;

    public ExpressionAtom(String name, int line) {
        super("NameExpr", line);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return nodeName + "(line=" + line + ", name=" + name + ")";
    }
}
