package ASTFlask.SymbolTable;


import java.util.HashMap;
import java.util.Map;

public class Scope {
    public String name;
    public Scope parent;
    public Map<String, Symbol> symbols = new HashMap<>();

    public Scope(String name, Scope parent) {
        this.name = name;
        this.parent = parent;
    }

    // إضافة رمز للنطاق
    public void addSymbol(Symbol symbol) {
        symbols.put(symbol.name, symbol);
    }

    // البحث عن رمز في النطاق الحالي أو الأب
    public Symbol lookup(String name) {
        Symbol symbol = symbols.get(name);
        if (symbol != null) {
            return symbol;
        }
        if (parent != null) {
            return parent.lookup(name);
        }
        return null;
    }

    // البحث في النطاق الحالي فقط
    public Symbol lookupCurrent(String name) {
        return symbols.get(name);
    }
}