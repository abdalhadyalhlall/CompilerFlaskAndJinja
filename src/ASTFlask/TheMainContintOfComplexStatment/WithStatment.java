package ASTFlask.TheMainContintOfComplexStatment;

import ASTFlask.Statment;

import java.util.ArrayList;
import java.util.List;

public class WithStatment extends Statment {

    public List<WithItemStatment> items;
    public List<Statment> body;

    public WithStatment(List<WithItemStatment> items, List<Statment> body, int line) {
        super("WithStmt", line);
        this.items = items;
        this.body = body;
    }

    public void addItem(WithItemStatment item) {
        if (this.items == null) this.items = new ArrayList<>();
        this.items.add(item);
    }

    public void addBodyStmt(Statment stmt) {
        if (this.body == null) this.body = new ArrayList<>();
        this.body.add(stmt);
    }

    @Override
    public String toString() {
        return "WithStmt(line=" + line +
                ", items=" + items +
                ", body=" + body + ")";
    }
}
