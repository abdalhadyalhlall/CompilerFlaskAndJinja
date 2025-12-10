package ASTFlask.TheMainContintOfSimpleStatment;

import java.util.ArrayList;
import java.util.List;

public class ImportName extends ImportStatment{
    public List<ImportItem> items;

    public ImportName(List<ImportItem> items, int line) {
        super("ImportStmt", line);
        this.items = items;
    }

    public void addItem(ImportItem item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }

    public void addItems(List<ImportItem> list) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.addAll(list);
    }

    @Override
    public String toString() {
        return "ImportStmt(line=" + line + ", items=" + items + ")";
    }
}
