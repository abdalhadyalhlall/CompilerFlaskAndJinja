package VISITORJINJA.ST;

import java.util.*;

// ================ جدول الرموز الرئيسي ================
public class HtmlCssJinjaSymbolTable {
    public SymbolScope globalScope;
    public SymbolScope currentScope;
    public Map<Integer, List<Symbol>> symbolsByLine; // الرموز مرتبة حسب السطر
    public Map<String, List<Symbol>> symbolsByType;  // الرموز مرتبة حسب النوع

    // إحصائيات
    public int htmlElementCount;
    public int htmlAttributeCount;
    public int cssRuleCount;
    public int jinjaSymbolCount;

    public HtmlCssJinjaSymbolTable() {
        this.globalScope = new SymbolScope("global", SymbolScope.ScopeType.GLOBAL, null);
        this.currentScope = globalScope;
        this.symbolsByLine = new HashMap<>();
        this.symbolsByType = new HashMap<>();
    }

    // ================ إدارة النطاقات ================
    public void enterScope(String name, SymbolScope.ScopeType type) {
        SymbolScope newScope = new SymbolScope(name, type, currentScope);
        currentScope = newScope;
    }

    public void exitScope() {
        if (currentScope.parent != null) {
            currentScope = currentScope.parent;
        }
    }

    public SymbolScope getCurrentScope() {
        return currentScope;
    }

    // ================ إضافة الرموز ================
    public void addHtmlElement(HtmlElementSymbol element) {
        addSymbol(element);
        htmlElementCount++;

        // إنشاء نطاق جديد للعنصر
        enterScope("element_" + element.name, SymbolScope.ScopeType.HTML_ELEMENT);
    }

    public void closeHtmlElement() {
        exitScope();
    }

    public void addHtmlAttribute(HtmlAttributeSymbol attribute) {
        addSymbol(attribute);
        htmlAttributeCount++;
    }

    public void addCssRule(CssRuleSymbol rule) {
        addSymbol(rule);
        cssRuleCount++;
    }

    public void addJinjaSymbol(JinjaSymbol jinjaSymbol) {
        addSymbol(jinjaSymbol);
        jinjaSymbolCount++;

        // إنشاء نطاق جديد إذا كانت كتلة
        if (jinjaSymbol instanceof JinjaBlockSymbol) {
            String scopeName = "jinja_" + jinjaSymbol.jinjaType.toString().toLowerCase();
            enterScope(scopeName, SymbolScope.ScopeType.JINJA_BLOCK);
        }
    }

    public void closeJinjaBlock() {
        exitScope();
    }

    public void addSymbol(Symbol symbol) {
        // إضافة للنطاق الحالي
        currentScope.addSymbol(symbol);

        // إضافة للفهرس حسب السطر
        symbolsByLine.computeIfAbsent(symbol.line, k -> new ArrayList<>())
                .add(symbol);

        // إضافة للفهرس حسب النوع
        symbolsByType.computeIfAbsent(symbol.type, k -> new ArrayList<>())
                .add(symbol);
    }

    // ================ عمليات البحث ================
    public Symbol findSymbol(String name) {
        return currentScope.lookupRecursive(name);
    }

    public List<Symbol> findSymbolsAtLine(int line) {
        return symbolsByLine.getOrDefault(line, new ArrayList<>());
    }

    public List<Symbol> findSymbolsByType(String type) {
        return symbolsByType.getOrDefault(type, new ArrayList<>());
    }

    public List<HtmlElementSymbol> findHtmlElementsByTag(String tagName) {
        List<HtmlElementSymbol> result = new ArrayList<>();
        for (Symbol symbol : symbolsByType.getOrDefault("HTML_ELEMENT", new ArrayList<>())) {
            if (symbol instanceof HtmlElementSymbol && symbol.name.equals(tagName)) {
                result.add((HtmlElementSymbol) symbol);
            }
        }
        return result;
    }

    public List<CssRuleSymbol> findCssRulesByProperty(String property) {
        List<CssRuleSymbol> result = new ArrayList<>();
        for (Symbol symbol : symbolsByType.getOrDefault("CSS_RULE", new ArrayList<>())) {
            if (symbol instanceof CssRuleSymbol && symbol.name.equals(property)) {
                result.add((CssRuleSymbol) symbol);
            }
        }
        return result;
    }

    // ================ التحليلات والإحصائيات ================
    public void printStatistics() {
        System.out.println("========== SYMBOL TABLE STATISTICS ==========");
        System.out.println("HTML Elements: " + htmlElementCount);
        System.out.println("HTML Attributes: " + htmlAttributeCount);
        System.out.println("CSS Rules: " + cssRuleCount);
        System.out.println("Jinja2 Symbols: " + jinjaSymbolCount);
        System.out.println("Total Symbols: " + getTotalSymbols());
        System.out.println("Scope Depth: " + getScopeDepth());
        System.out.println("============================================");
    }

    public void printAllSymbols() {
        System.out.println("========== ALL SYMBOLS ==========");
        printScopeHierarchy(globalScope, 0);
        System.out.println("=================================");
    }

    public void printScopeHierarchy(SymbolScope scope, int depth) {
        scope.printScope(depth);

        // البحث عن النطاقات الفرعية (سيتم إدارتها بشكل أفضل في تطبيق كامل)
        // ...
    }

    public int getTotalSymbols() {
        return htmlElementCount + htmlAttributeCount + cssRuleCount + jinjaSymbolCount;
    }

    public int getScopeDepth() {
        int depth = 0;
        SymbolScope current = currentScope;
        while (current != null) {
            depth++;
            current = current.parent;
        }
        return depth;
    }

    // ================ عمليات مساعدة ================
    public Map<String, Integer> getElementDistribution() {
        Map<String, Integer> distribution = new HashMap<>();
        for (Symbol symbol : symbolsByType.getOrDefault("HTML_ELEMENT", new ArrayList<>())) {
            distribution.put(symbol.name, distribution.getOrDefault(symbol.name, 0) + 1);
        }
        return distribution;
    }

    public Map<String, Integer> getAttributeDistribution() {
        Map<String, Integer> distribution = new HashMap<>();
        for (Symbol symbol : symbolsByType.getOrDefault("HTML_ATTRIBUTE", new ArrayList<>())) {
            distribution.put(symbol.name, distribution.getOrDefault(symbol.name, 0) + 1);
        }
        return distribution;
    }

    public List<HtmlElementSymbol> getElementsWithStyle() {
        List<HtmlElementSymbol> result = new ArrayList<>();
        for (Symbol symbol : symbolsByType.getOrDefault("HTML_ELEMENT", new ArrayList<>())) {
            HtmlElementSymbol element = (HtmlElementSymbol) symbol;
            // التحقق من وجود سمة style
            // (سيتم تنفيذ هذا في الزائر Visitor)
        }
        return result;
    }

    // ================ تصدير البيانات ================
    public Map<String, Object> exportToJson() {
        Map<String, Object> json = new HashMap<>();

        // الإحصائيات
        Map<String, Integer> stats = new HashMap<>();
        stats.put("html_elements", htmlElementCount);
        stats.put("html_attributes", htmlAttributeCount);
        stats.put("css_rules", cssRuleCount);
        stats.put("jinja_symbols", jinjaSymbolCount);
        json.put("statistics", stats);

        // توزيع العناصر
        json.put("element_distribution", getElementDistribution());

        // توزيع السمات
        json.put("attribute_distribution", getAttributeDistribution());

        // الرموز حسب السطر
        Map<Integer, List<String>> symbolsByLineJson = new HashMap<>();
        for (Map.Entry<Integer, List<Symbol>> entry : symbolsByLine.entrySet()) {
            List<String> symbolNames = new ArrayList<>();
            for (Symbol symbol : entry.getValue()) {
                symbolNames.add(symbol.name + " [" + symbol.type + "]");
            }
            symbolsByLineJson.put(entry.getKey(), symbolNames);
        }
        json.put("symbols_by_line", symbolsByLineJson);

        return json;
    }
    public void closeAllScopes() {
        while (currentScope != globalScope) {
            exitScope();
        }
        System.out.println("All scopes closed. Current scope: " + currentScope.name);
    }

}