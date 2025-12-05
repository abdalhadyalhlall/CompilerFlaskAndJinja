package AST.TheMainContintOfComplexStatment;

import AST.AtomExpression.ExpressionAtom;
import AST.Statment;

import java.util.ArrayList;
import java.util.List;

public class IfStatment extends Statment {

    public ExpressionAtom condition;
    public List<Statment> body;
    public List<ElifStatment> elifs;
    public List<Statment> elseBody;

    public IfStatment(ExpressionAtom condition, List<Statment> body, List<ElifStatment> elifs, List<Statment> elseBody, int line) {
        super("IfStmt", line);
        this.condition = condition;
        this.body = body;
        this.elifs = elifs;
        this.elseBody = elseBody;
    }

    public void addToBody(Statment stmt) {
        if (body == null) body = new ArrayList<>();
        body.add(stmt);
    }

    public void addElif(ElifStatment elif) {
        if (elifs == null) elifs = new ArrayList<>();
        elifs.add(elif);
    }

    public void addToElse(Statment stmt) {
        if (elseBody == null) elseBody = new ArrayList<>();
        elseBody.add(stmt);
    }

    @Override
    public String toString() {
        return "IfStmt(line=" + line +
                ", condition=" + condition +
                ", body=" + body +
                ", elifs=" + elifs +
                ", elseBody=" + elseBody + ")";
    }
}
