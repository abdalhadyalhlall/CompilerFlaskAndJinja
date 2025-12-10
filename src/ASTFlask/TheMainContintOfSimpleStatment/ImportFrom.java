package ASTFlask.TheMainContintOfSimpleStatment;

import java.util.ArrayList;
import java.util.List;

public class ImportFrom extends ImportStatment{
    public String module;
    public List<ImportItem> items;

    public ImportFrom(String module, List<ImportItem> items, int line) {
        super("ImportFromStmt", line);
        this.module = module;
        this.items = items;
    }

    public void addItem(ImportItem item) {
        if (this.items == null) this.items = new ArrayList<>();
        this.items.add(item);
    }

    @Override
    public String toString() {
        return "ImportFromStmt(line=" + line + ", module=" + module + ", items=" + items + ")";
    }
}
