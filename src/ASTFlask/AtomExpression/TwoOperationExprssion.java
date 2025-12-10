package ASTFlask.AtomExpression;
//+=
public class TwoOperationExprssion extends ExpressionAtom{
    public ExpressionAtom left;
    public String op;
    public ExpressionAtom right;

    public TwoOperationExprssion(ExpressionAtom left, String op, ExpressionAtom right, int line) {
        super("BinaryExpr", line);
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public void setLeft(ExpressionAtom left) { this.left = left; }
    public void setOp(String op) { this.op = op; }
    public void setRight(ExpressionAtom right) { this.right = right; }

    @Override
    public String toString() {
        return nodeName + "(line=" + line + ", left=" + left + ", op=" + op + ", right=" + right + ")";
    }
}
