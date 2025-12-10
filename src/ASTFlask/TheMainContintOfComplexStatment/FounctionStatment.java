package ASTFlask.TheMainContintOfComplexStatment;

import ASTFlask.AtomExpression.ExpressionAtom;
import ASTFlask.Statment;

import java.util.ArrayList;
import java.util.List;

public class FounctionStatment extends Statment {

    public String name;
    public List<ParametarToFunction> params;
    public ExpressionAtom returnType;
    public List<Statment> body;

    public FounctionStatment(String name, List<ParametarToFunction> params, ExpressionAtom returnType, List<Statment> body, int line) {
        super("FuncDef", line);
        this.name = name;
        this.params = params;
        this.returnType = returnType;
        this.body = body;
    }

    public void addParam(ParametarToFunction p) {
        if (params == null) params = new ArrayList<>();
        params.add(p);
    }

    public void addToBody(Statment stmt) {
        if (body == null) body = new ArrayList<>();
        body.add(stmt);
    }

    @Override
    public String toString() {
        return "FuncDef(line=" + line +
                ", name=" + name +
                ", params=" + params +
                ", returnType=" + returnType +
                ", body=" + body + ")";
    }
}
