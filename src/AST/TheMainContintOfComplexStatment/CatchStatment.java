package AST.TheMainContintOfComplexStatment;

import AST.AtomExpression.ExpressionAtom;
import AST.Statment;
import AST.StatmentRoot;

import java.util.ArrayList;
import java.util.List;

public class CatchStatment extends StatmentRoot {
    public ExpressionAtom exceptionType;  // مثل: except ValueError
    public String varName;      // مثل: except ValueError as e
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
