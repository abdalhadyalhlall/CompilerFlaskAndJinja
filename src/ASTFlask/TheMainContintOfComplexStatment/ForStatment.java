package ASTFlask.TheMainContintOfComplexStatment;

import ASTFlask.AtomExpression.ExpressionAtom;
import ASTFlask.Statment;

import java.util.ArrayList;
import java.util.List;

public class ForStatment extends Statment {

    public ExpressionAtom target;
    public ExpressionAtom iterable;
    public List<Statment> body;
    public List<Statment> elseBody;

    public ForStatment(ExpressionAtom target, ExpressionAtom iterable, List<Statment> body, List<Statment> elseBody, int line) {
        super("ForStmt", line);
        this.target = target;
        this.iterable = iterable;
        this.body = body;
        this.elseBody = elseBody;
    }

    public void addToBody(Statment stmt) {
        if (body == null) body = new ArrayList<>();
        body.add(stmt);
    }

    public void addToElse(Statment stmt) {
        if (elseBody == null) elseBody = new ArrayList<>();
        elseBody.add(stmt);
    }

    @Override
    public String toString() {
        return "ForStmt(line=" + line +
                ", target=" + target +
                ", iterable=" + iterable +
                ", body=" + body +
                ", elseBody=" + elseBody + ")";
    }
}
