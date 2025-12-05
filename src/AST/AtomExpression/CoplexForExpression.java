package AST.AtomExpression;
//comp_for
import AST.StatmentRoot;

import java.util.ArrayList;
import java.util.List;

public class CoplexForExpression extends StatmentRoot {

    public ExpressionAtom target;
    public ExpressionAtom iterable;
    public List<ExpressionAtom> conditions;

    public CoplexForExpression(ExpressionAtom target, ExpressionAtom iterable, List<ExpressionAtom> conditions, int line) {
        super("ComprehensionFor", line);
        this.target = target;
        this.iterable = iterable;
        this.conditions = conditions;
    }

    public void addCondition(ExpressionAtom condition) {
        if (this.conditions == null) {
            this.conditions = new ArrayList<>();
        }
        this.conditions.add(condition);
    }

    public void addConditions(List<ExpressionAtom> conds) {
        if (this.conditions == null) {
            this.conditions = new ArrayList<>();
        }
        this.conditions.addAll(conds);
    }

    @Override
    public String toString() {
        return "ComprehensionFor(line=" + line +
                ", target=" + target +
                ", iterable=" + iterable +
                ", conditions=" + conditions + ")";
    }
}
