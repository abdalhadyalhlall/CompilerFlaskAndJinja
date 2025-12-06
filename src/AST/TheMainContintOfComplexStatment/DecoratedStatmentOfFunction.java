package AST.TheMainContintOfComplexStatment;
//@x
//@y(5)
//def f():
//pass

import AST.Statment;

import java.util.List;

public class DecoratedStatmentOfFunction extends Statment {

    public List<DecoratorOfFunction> decorators;   // @decor1 @decor2 ...
    public Statment statement;               // FuncDef أو ClassDef

    public DecoratedStatmentOfFunction(List<DecoratorOfFunction> decorators, Statment statement, int line) {
        super("DecoratedStmt", line);
        this.decorators = decorators;
        this.statement = statement;
    }

    @Override
    public String toString() {
        return nodeName + "(line=" + line + ", decorators=" + decorators + ", statement=" + statement + ")";
    }
}
