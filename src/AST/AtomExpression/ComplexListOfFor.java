package AST.AtomExpression;
//list comprehension
//        [x for y in z if cond]
//        dict comprehension
//        {k:v for a in b if cond}
//        generator expression
//        (x for y in z if cond)
import AST.StatmentRoot;

import java.util.ArrayList;
import java.util.List;

public class ComplexListOfFor extends StatmentRoot {
    public TypeOfFor_Complex type;       // LIST, DICT, GENERATOR
    public List<CoplexForExpression> forClauses;
    public ExpressionAtom body;                    // body = x في (x for y in z)
    public ExpressionAtom key;                     // للـ dict فقط: key:value

    public ComplexListOfFor(TypeOfFor_Complex type, ExpressionAtom body, List<CoplexForExpression> forClauses, int line) {
        super("Comprehension", line);
        this.type = type;
        this.body = body;
        this.forClauses = forClauses;
    }

    // dict فقط: {k : v for ...}
    public void setKey(ExpressionAtom key) {
        this.key = key;
    }

    // إضافة for واحد
    public void addForClause(CoplexForExpression clause) {
        if (this.forClauses == null) {
            this.forClauses = new ArrayList<>();
        }
        this.forClauses.add(clause);
    }

    // إضافة عدة for clauses
    public void addForClauses(List<CoplexForExpression> clauses) {
        if (this.forClauses == null) {
            this.forClauses = new ArrayList<>();
        }
        this.forClauses.addAll(clauses);
    }

    @Override
    public String toString() {
        return "Comprehension(type=" + type +
                ", line=" + line +
                ", key=" + key +
                ", body=" + body +
                ", forClauses=" + forClauses + ")";
    }
}
