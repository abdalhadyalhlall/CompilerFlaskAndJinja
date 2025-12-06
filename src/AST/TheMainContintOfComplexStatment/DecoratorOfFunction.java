package AST.TheMainContintOfComplexStatment;
//@x
//@y(5)
//def f():
//pass

import AST.AtomExpression.ExpressionAtom;
import AST.StatmentRoot;

import java.util.List;

public class DecoratorOfFunction extends StatmentRoot {

    public ExpressionAtom name;            // mydecor أو pkg.decor
    public List<ExpressionAtom> args;      // قد تكون null إن لم توجد ()

    public DecoratorOfFunction(ExpressionAtom name, List<ExpressionAtom> args, int line) {
        super("Decorator", line);
        this.name = name;
        this.args = args;
    }

    @Override
    public String toString() {
        return nodeName + "(line=" + line + ", name=" + name + ", args=" + args + ")";
    }
}
