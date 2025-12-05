package AST.AtomExpression;
//+x
public class OneOperationExprssion extends ExpressionAtom{
    public String op;
    public ExpressionAtom expr;

    public OneOperationExprssion(String op, ExpressionAtom expr, int line) {
        super("UnaryExpr", line);
        this.op = op;
        this.expr = expr;
    }

    public void setOp(String op) { this.op = op; }
    public void setExpr(ExpressionAtom expr) { this.expr = expr; }

    @Override
    public String toString() {
        return nodeName + "(line=" + line + ", op=" + op + ", expr=" + expr + ")";
    }
}
