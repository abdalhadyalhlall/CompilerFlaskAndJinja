package AST.AtomExpression;

public class FStringExpression extends ExpressionAtom{
    public String value;

    public FStringExpression(String value, int line) {
        super("FStringExpr", line);
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return nodeName + "(line=" + line + ", value=" + value + ")";
    }
}
