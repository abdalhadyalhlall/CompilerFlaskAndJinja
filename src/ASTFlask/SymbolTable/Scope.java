//package ASTFlask.SymbolTable;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Scope {
//    public String name;
//    public Scope parent;
//    public Map<String, Symbol> symbols = new HashMap<>();
//
//    public Scope(String name, Scope parent) {
//        this.name = name;
//        this.parent = parent;
//    }
//
//    public void addSymbol(Symbol symbol) {
//        symbols.put(symbol.name, symbol);
//    }
//
//    public Symbol lookup(String name) {
//        Symbol symbol = symbols.get(name);
//        if (symbol != null) {
//            return symbol;
//        }
//        if (parent != null) {
//            return parent.lookup(name);
//        }
//        return null;
//    }
//
//    public Symbol lookupCurrent(String name) {
//        return symbols.get(name);
//    }
//}




package ASTFlask.SymbolTable;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Scope {
    public String name;
    public Scope parent;
    public Map<String, Symbol> symbols = new HashMap<>();

    public Scope(String name, Scope parent) {
        this.name = name;
        this.parent = parent;
    }

    // === عمليات الإضافة ===

    /**
     * إضافة رمز إلى النطاق الحالي
     */
    public boolean addSymbol(Symbol symbol) {
        if (symbol == null || symbol.name == null || symbol.name.isEmpty()) {
            return false;
        }

        // التحقق من عدم وجود تعارض في الأسماء
        if (symbols.containsKey(symbol.name)) {
            return false; // أو يمكن رمي استثناء
        }

        symbols.put(symbol.name, symbol);
        return true;
    }

    /**
     * إضافة رمز مع تجاوز الرمز الموجود (إن وجد)
     */
    public boolean addOrUpdateSymbol(Symbol symbol) {
        if (symbol == null || symbol.name == null || symbol.name.isEmpty()) {
            return false;
        }

        symbols.put(symbol.name, symbol);
        return true;
    }

    // === عمليات البحث ===

    /**
     * البحث في النطاق الحالي فقط
     */
    public Symbol lookupCurrent(String name) {
        return symbols.get(name);
    }

    /**
     * البحث بشكل متكرر في جميع النطاقات الأب
     */
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

    /**
     * البحث عن الرمز مع إرجاع النطاق الذي وجد فيه
     */
    public SymbolLookupResult lookupWithScope(String name) {
        Symbol symbol = symbols.get(name);
        if (symbol != null) {
            return new SymbolLookupResult(symbol, this);
        }
        if (parent != null) {
            return parent.lookupWithScope(name);
        }
        return null;
    }

    /**
     * البحث عن جميع الرموز من نوع معين
     */
    public List<Symbol> lookupByType(String type) {
        List<Symbol> result = new ArrayList<>();
        for (Symbol symbol : symbols.values()) {
            if (symbol.type.equals(type)) {
                result.add(symbol);
            }
        }
        return result;
    }

    /**
     * البحث عن جميع الرموز التي تطابق نمط معين
     */
    public List<Symbol> searchByNamePattern(String pattern) {
        List<Symbol> result = new ArrayList<>();
        for (Symbol symbol : symbols.values()) {
            if (symbol.name.contains(pattern) || symbol.name.matches(pattern)) {
                result.add(symbol);
            }
        }
        return result;
    }

    // === عمليات الحذف ===

    /**
     * حذف رمز من النطاق الحالي
     */
    public boolean removeSymbol(String name) {
        if (symbols.containsKey(name)) {
            symbols.remove(name);
            return true;
        }
        return false;
    }

    /**
     * حذف رمز من النطاق الحالي وجميع النطاقات الفرعية
     */
    public boolean removeSymbolRecursive(String name) {
        boolean removed = removeSymbol(name);

        // البحث في النطاقات الفرعية (التي يتم تخزينها في SymbolTableBuilder)
        // تحتاج إلى تمرير reference للbuilder أو استخدام طريقة أخرى للوصول للنطاقات الفرعية
        return removed;
    }

    /**
     * إفراغ النطاق بالكامل
     */
    public void clear() {
        symbols.clear();
    }

    /**
     * الحصول على جميع الرموز
     */
    public Map<String, Symbol> getAllSymbols() {
        return new HashMap<>(symbols);
    }

    /**
     * الحصول على أسماء جميع الرموز
     */
    public List<String> getAllSymbolNames() {
        return new ArrayList<>(symbols.keySet());
    }

    /**
     * التحقق من وجود رمز في النطاق الحالي
     */
    public boolean containsSymbol(String name) {
        return symbols.containsKey(name);
    }

    /**
     * التحقق من وجود رمز في النطاق الحالي أو النطاقات الأب
     */
    public boolean containsSymbolRecursive(String name) {
        return lookup(name) != null;
    }

    /**
     * الحصول على عدد الرموز في النطاق
     */
    public int getSymbolCount() {
        return symbols.size();
    }

    /**
     * طباعة جميع الرموز في النطاق
     */
    public void printSymbols() {
        System.out.println("=== Scope: " + name + " ===");
        System.out.println("Symbol count: " + getSymbolCount());

        for (Symbol symbol : symbols.values()) {
            System.out.println("  • " + symbol.name + " [" + symbol.type + "] at line " + symbol.line);
        }

        if (symbols.isEmpty()) {
            System.out.println("  (no symbols)");
        }
    }

    /**
     * فئة مساعدة لنتيجة البحث مع النطاق
     */
    public static class SymbolLookupResult {
        public final Symbol symbol;
        public final Scope scope;

        public SymbolLookupResult(Symbol symbol, Scope scope) {
            this.symbol = symbol;
            this.scope = scope;
        }
    }
}