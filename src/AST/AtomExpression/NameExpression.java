package AST.AtomExpression;

public class NameExpression extends ExpressionAtom{
    public String name;

    public NameExpression(String name, int line) {
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
