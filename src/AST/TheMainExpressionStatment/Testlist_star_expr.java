package AST.TheMainExpressionStatment;

import AST.AtomExpression.ExpressionAtom;
import AST.TheMainContintOfSimpleStatment.ExpressionStatment;

public class Testlist_star_expr extends ExpressionStatment {
    public ExpressionAtom expr;

    public Testlist_star_expr(ExpressionAtom expr, int line) {
        super("ExprOnlyStmt", line);
        this.expr = expr;
        this.value = expr;
    }

    public void addExpr(ExpressionAtom expr) {
        this.expr = expr;
        this.value = expr;
    }

    @Override
    public String toString() {
        return nodeName + "(line=" + line + ", expr=" + expr + ")";
    }
}
