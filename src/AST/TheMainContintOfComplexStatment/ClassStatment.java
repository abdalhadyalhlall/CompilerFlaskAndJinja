package AST.TheMainContintOfComplexStatment;

import AST.AtomExpression.ExpressionAtom;
import AST.Statment;

import java.util.ArrayList;
import java.util.List;

public class ClassStatment extends Statment {

    public String name;
    public List<ExpressionAtom> bases;
    public List<Statment> body;

    public ClassStatment(String name, List<ExpressionAtom> bases, List<Statment> body, int line) {
        super("ClassDef", line);
        this.name = name;
        this.bases = bases;
        this.body = body;
    }

    // إضافة base واحدة
    public void addBase(ExpressionAtom base) {
        if (this.bases == null) {
            this.bases = new ArrayList<>();
        }
        this.bases.add(base);
    }

    // إضافة سطر إلى جسم الصف
    public void addBodyStmt(Statment stmt) {
        if (this.body == null) {
            this.body = new ArrayList<>();
        }
        this.body.add(stmt);
    }

    @Override
    public String toString() {
        return "ClassDef(line=" + line +
                ", name=" + name +
                ", bases=" + bases +
                ", body=" + body + ")";
    }
}
