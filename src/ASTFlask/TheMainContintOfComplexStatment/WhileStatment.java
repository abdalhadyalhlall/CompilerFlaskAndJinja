package ASTFlask.TheMainContintOfComplexStatment;

import ASTFlask.AtomExpression.ExpressionAtom;
import ASTFlask.Statment;

import java.util.ArrayList;
import java.util.List;

public class WhileStatment extends Statment {

    public ExpressionAtom condition;
    public List<Statment> body;
    public List<Statment> elseBody;

    public WhileStatment(ExpressionAtom condition, List<Statment> body, List<Statment> elseBody, int line) {
        super("WhileStmt", line);
        this.condition = condition;
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
        return "WhileStmt(line=" + line +
                ", condition=" + condition +
                ", body=" + body +
                ", elseBody=" + elseBody + ")";
    }
}
