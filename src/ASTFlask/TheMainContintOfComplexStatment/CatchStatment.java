package ASTFlask.TheMainContintOfComplexStatment;

import ASTFlask.AtomExpression.ExpressionAtom;
import ASTFlask.Statment;
import ASTFlask.StatmentRoot;

import java.util.ArrayList;
import java.util.List;

public class CatchStatment extends StatmentRoot {
    public ExpressionAtom exceptionType;
    public String varName;
    public List<Statment> body;

    public CatchStatment(ExpressionAtom exceptionType, String varName, List<Statment> body, int line) {
        super("Catch", line);
        this.exceptionType = exceptionType;
        this.varName = varName;
        this.body = body;
    }

    public void addBodyStmt(Statment stmt) {
        if (this.body == null) {
            this.body = new ArrayList<>();
        }
        this.body.add(stmt);
    }

    @Override
    public String toString() {
        return "Catch(line=" + line +
                ", type=" + exceptionType +
                ", var=" + varName +
                ", body=" + body + ")";
    }
}
