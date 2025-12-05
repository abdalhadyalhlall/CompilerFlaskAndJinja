package AST.TheMainContintOfSimpleStatment;

import AST.AtomExpression.ExpressionAtom;
import AST.Statment;

import java.util.ArrayList;
import java.util.List;

public class DeleteStatment extends Statment {
    public List<ExpressionAtom> elementsTdelete;

    public DeleteStatment(List<ExpressionAtom> elementsTdelete, int line) {
        super("DelStmt", line);
        this.elementsTdelete = elementsTdelete;
    }

    public void addelementTdelete(ExpressionAtom expr) {
        if (this.elementsTdelete == null) {
            this.elementsTdelete = new ArrayList<>();
        }
        this.elementsTdelete.add(expr);
    }

    public void addelementsTdelete(List<ExpressionAtom> list) {
        if (this.elementsTdelete == null) {
            this.elementsTdelete = new ArrayList<>();
        }
        this.elementsTdelete.addAll(list);
    }

    @Override
    public String toString() {
        return "DelStmt(line=" + line + ", targets=" + elementsTdelete + ")";
    }
}
