package ASTFlask.TheMainContintOfSimpleStatment;

import ASTFlask.AtomExpression.ExpressionAtom;

import java.util.ArrayList;
import java.util.List;

public class FlowReturn extends FlowStatment{
    public List<ExpressionAtom> values;

    public FlowReturn(List<ExpressionAtom> values, int line) {
        super("ReturnStmt", line);
        this.values = values;
    }

    public void addValue(ExpressionAtom expr) {
        if (this.values == null) {
            this.values = new ArrayList<>();
        }
        this.values.add(expr);
    }

    public void addValues(List<ExpressionAtom> list) {
        if (this.values == null) {
            this.values = new ArrayList<>();
        }
        this.values.addAll(list);
    }

    @Override
    public String toString() {
        return "ReturnStmt(line=" + line + ", values=" + values + ")";
    }
}
