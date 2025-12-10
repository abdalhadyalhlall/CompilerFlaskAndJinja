package ASTFlask.AtomExpression;

import java.util.ArrayList;
import java.util.List;

public class CompareExprssion extends ExpressionAtom{
    public ExpressionAtom left;
    public List<String> ops;
    public List<ExpressionAtom> rights;

    public CompareExprssion(ExpressionAtom left, List<String> ops, List<ExpressionAtom> rights, int line) {
        super("CompareExpr", line);
        this.left = left;
        this.ops = ops;
        this.rights = rights;
    }

    public void setLeft(ExpressionAtom left) { this.left = left; }

    public void addOp(String op) {
        if (this.ops == null) this.ops = new ArrayList<>();
        this.ops.add(op);
    }

    public void addRight(ExpressionAtom expr) {
        if (this.rights == null) this.rights = new ArrayList<>();
        this.rights.add(expr);
    }

    public void setOps(List<String> ops) { this.ops = ops; }
    public void setRights(List<ExpressionAtom> rights) { this.rights = rights; }


    @Override
    public String toString() {
        return nodeName + "(line=" + line +
                ", left=" + left +
                ", ops=" + ops +
                ", rights=" + rights +
                ")";
    }
}
