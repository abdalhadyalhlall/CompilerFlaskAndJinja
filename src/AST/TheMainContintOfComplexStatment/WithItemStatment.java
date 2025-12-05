package AST.TheMainContintOfComplexStatment;

import AST.AtomExpression.ExpressionAtom;
import AST.StatmentRoot;

public class WithItemStatment extends StatmentRoot {
    public ExpressionAtom contextExpr;  // resource
    public ExpressionAtom optionalVar;  // "as x"

    public WithItemStatment(ExpressionAtom contextExpr, ExpressionAtom optionalVar, int line) {
        super("WithItem", line);
        this.contextExpr = contextExpr;
        this.optionalVar = optionalVar;
    }

    @Override
    public String toString() {
        return "WithItem(line=" + line +
                ", context=" + contextExpr +
                ", var=" + optionalVar + ")";
    }
}
