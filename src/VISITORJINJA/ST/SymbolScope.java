package VISITORJINJA.ST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ================ إدارة النطاقات ================
public class SymbolScope {
    public enum ScopeType {
        GLOBAL,          // النطاق العام
        HTML_DOCUMENT,   // مستند HTML
        HTML_ELEMENT,    // عنصر HTML
        JINJA_BLOCK,     // كتلة Jinja
        JINJA_FOR,       // حلقة for في Jinja
        JINJA_IF,        // شرط if في Jinja
        STYLE_ATTRIBUTE, // سمة style
        CSS_RULE         // قاعدة CSS
    }

    public String name;
    private ScopeType type;
    public SymbolScope parent;
    private Map<String, Symbol> symbols;
    private Map<String, List<Symbol>> symbolHistory; // تتبع التغيرات في الرموز

    public SymbolScope(String name, ScopeType type, SymbolScope parent) {
        this.name = name;
        this.type = type;
        this.parent = parent;
        this.symbols = new HashMap<>();
        this.symbolHistory = new HashMap<>();
    }

    // إضافة رمز مع تتبع التاريخ
    public boolean addSymbol(Symbol symbol) {
        if (symbol == null || symbol.name == null) {
            return false;
        }

        // تسجيل في التاريخ
        if (!symbolHistory.containsKey(symbol.name)) {
            symbolHistory.put(symbol.name, new ArrayList<>());
        }
        symbolHistory.get(symbol.name).add(symbol);

        // إضافة للرموز الحالية
        symbols.put(symbol.name, symbol);
        return true;
    }

    // البحث الحالي
    public Symbol lookup(String name) {
        return symbols.get(name);
    }

    // البحث المتكرر
    public Symbol lookupRecursive(String name) {
        Symbol symbol = lookup(name);
        if (symbol != null) {
            return symbol;
        }
        if (parent != null) {
            return parent.lookupRecursive(name);
        }
        return null;
    }

    // البحث مع إرجاع النطاق
    public SymbolScope lookupWithScope(String name) {
        if (symbols.containsKey(name)) {
            return this;
        }
        if (parent != null) {
            return parent.lookupWithScope(name);
        }
        return null;
    }

    // الحصول على جميع الرموز من نوع معين
    public List<Symbol> getSymbolsByType(String type) {
        List<Symbol> result = new ArrayList<>();
        for (Symbol symbol : symbols.values()) {
            if (symbol.type.equals(type)) {
                result.add(symbol);
            }
        }
        return result;
    }

    // الحصول على سلسلة النطاقات
    public List<SymbolScope> getScopeChain() {
        List<SymbolScope> chain = new ArrayList<>();
        SymbolScope current = this;
        while (current != null) {
            chain.add(current);
            current = current.parent;
        }
        return chain;
    }

    // طباعة النطاق
    public void printScope(int indent) {
        String indentStr = "  ".repeat(indent);
        System.out.println(indentStr + "[" + type + "] " + name + ":");

        for (Symbol symbol : symbols.values()) {
            System.out.println(indentStr + "  • " + symbol.name + " (" + symbol.type + ")");
        }

        if (symbols.isEmpty()) {
            System.out.println(indentStr + "  (empty)");
        }
    }

}
