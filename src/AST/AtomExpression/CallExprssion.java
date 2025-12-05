package AST.AtomExpression;

import java.util.ArrayList;
import java.util.List;

public class CallExprssion extends ExpressionAtom{

    public ExpressionAtom function;
    public List<ExpressionAtom> args;

    public CallExprssion(ExpressionAtom function, List<ExpressionAtom> args, int line) {
        super("CallExpr", line);
        this.function = function;
        this.args = args;
    }

    public void setFunction(ExpressionAtom function) { this.function = function; }

    public void addArg(ExpressionAtom arg) {
        if (this.args == null) this.args = new ArrayList<>();
        this.args.add(arg);
    }

    public void setArgs(List<ExpressionAtom> args) { this.args = args; }

    @Override
    public String toString() {
        return nodeName + "(line=" + line + ", function=" + function + ", args=" + args + ")";
    }
}
