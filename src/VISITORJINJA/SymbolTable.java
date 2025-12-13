package VISITORJINJA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class SymbolTable {

    public enum SymbolType {
        // HTML Elements
        HTML_ELEMENT,
        HEAD_ELEMENT,
        BODY_ELEMENT,
        NORMAL_ELEMENT,
        SELF_CLOSING_ELEMENT,

        // Attributes
        ATTRIBUTE,
        LANG_ATTRIBUTE,
        STYLE_ATTRIBUTE,

        // CSS
        CSS_RULE,
        CSS_COLOR,
        CSS_LENGTH,
        CSS_URL,
        CSS_REPEAT,
        CSS_POSITION,
        CSS_BORDER,
        CSS_FONT,
        CSS_TEXT,
        CSS_EFFECT,
        CSS_LAYOUT,
        CSS_OFFSET,

        // Jinja
        JINJA_EXPRESSION,
        JINJA_STATEMENT,
        JINJA_COMMENT,
        JINJA_IF_BLOCK,
        JINJA_FOR_BLOCK,
        JINJA_BLOCK,

        // Text
        TEXT_HTML,
        TEXT_JINJA,

        // Document
        DOCUMENT,
        DOCTYPE,

        // Variables (for Jinja)
        VARIABLE,
        TEMPLATE_VARIABLE,

        // Unknown
        UNKNOWN
    }

    public static class Symbol {
        private String name;
        private SymbolType type;
        private int lineNumber;
        private String scope;
        private Map<String, String> properties;
        private String parentElement;

        public Symbol(String name, SymbolType type, int lineNumber, String scope) {
            this.name = name;
            this.type = type;
            this.lineNumber = lineNumber;
            this.scope = scope;
            this.properties = new HashMap<>();
            this.parentElement = null;
        }

        public void addProperty(String key, String value) {
            properties.put(key, value);
        }

        public String getProperty(String key) {
            return properties.get(key);
        }

        public String getName() { return name; }
        public SymbolType getType() { return type; }
        public int getLineNumber() { return lineNumber; }
        public String getScope() { return scope; }
        public Map<String, String> getProperties() { return properties; }
        public String getParentElement() { return parentElement; }
        public void setParentElement(String parentElement) { this.parentElement = parentElement; }

        @Override
        public String toString() {
            return String.format("Symbol{name='%s', type=%s, line=%d, scope='%s', parent='%s'}",
                    name, type, lineNumber, scope, parentElement != null ? parentElement : "null");
        }
    }

    private Map<String, List<Symbol>> symbols;
    private Stack<String> scopeStack;
    private Map<String, String> elementHierarchy;
    private int blockCounter;

    public SymbolTable() {
        symbols = new HashMap<>();
        scopeStack = new Stack<>();
        elementHierarchy = new HashMap<>();
        blockCounter = 0;
        pushScope("global");
    }

    public void pushScope(String scopeName) {
        scopeStack.push(scopeName);
    }

    public void popScope() {
        if (!scopeStack.isEmpty()) {
            scopeStack.pop();
        }
    }

    public String getCurrentScope() {
        return scopeStack.isEmpty() ? "global" : scopeStack.peek();
    }

    public void addSymbol(String name, SymbolType type, int lineNumber) {
        addSymbol(name, type, lineNumber, null);
    }

    public void addSymbol(String name, SymbolType type, int lineNumber, Map<String, String> properties) {
        Symbol symbol = new Symbol(name, type, lineNumber, getCurrentScope());

        if (properties != null) {
            symbol.getProperties().putAll(properties);
        }

        if (!scopeStack.isEmpty() && scopeStack.size() > 1) {
            symbol.setParentElement(getParentElementFromScope());
        }

        if (!symbols.containsKey(name)) {
            symbols.put(name, new ArrayList<>());
        }
        symbols.get(name).add(symbol);

        if (type == SymbolType.HTML_ELEMENT || type == SymbolType.NORMAL_ELEMENT ||
                type == SymbolType.SELF_CLOSING_ELEMENT) {
            updateElementHierarchy(name, symbol);
        }
    }

    private String getParentElementFromScope() {
        for (int i = scopeStack.size() - 1; i >= 0; i--) {
            String scope = scopeStack.get(i);
            if (scope.startsWith("element_")) {
                return scope.substring(8);
            }
        }
        return null;
    }

    private void updateElementHierarchy(String elementName, Symbol elementSymbol) {
        String parentElement = getParentElementFromScope();
        if (parentElement != null) {
            elementHierarchy.put(elementName, parentElement);
        }
    }

    public void enterBlock(String blockType) {
        String blockScope = blockType + "_" + (++blockCounter);
        pushScope(blockScope);
    }

    public void exitBlock() {
        popScope();
    }

    public void enterElement(String elementName) {
        String elementScope = "element_" + elementName + "_" + System.identityHashCode(this);
        pushScope(elementScope);
    }

    public void exitElement() {
        popScope();
    }

    public List<Symbol> lookup(String name) {
        return symbols.getOrDefault(name, new ArrayList<>());
    }

    public List<Symbol> lookupInCurrentScope(String name) {
        List<Symbol> result = new ArrayList<>();
        String currentScope = getCurrentScope();

        List<Symbol> allSymbols = symbols.get(name);
        if (allSymbols != null) {
            for (Symbol symbol : allSymbols) {
                if (symbol.getScope().equals(currentScope)) {
                    result.add(symbol);
                }
            }
        }

        return result;
    }

    public List<Symbol> getAllSymbols() {
        List<Symbol> allSymbols = new ArrayList<>();
        for (List<Symbol> symbolList : symbols.values()) {
            allSymbols.addAll(symbolList);
        }
        return allSymbols;
    }

    public List<Symbol> getSymbolsByType(SymbolType type) {
        List<Symbol> result = new ArrayList<>();
        for (List<Symbol> symbolList : symbols.values()) {
            for (Symbol symbol : symbolList) {
                if (symbol.getType() == type) {
                    result.add(symbol);
                }
            }
        }
        return result;
    }

    public List<Symbol> getSymbolsByLine(int lineNumber) {
        List<Symbol> result = new ArrayList<>();
        for (List<Symbol> symbolList : symbols.values()) {
            for (Symbol symbol : symbolList) {
                if (symbol.getLineNumber() == lineNumber) {
                    result.add(symbol);
                }
            }
        }
        return result;
    }

    public Map<String, List<Symbol>> getSymbolHierarchy() {
        Map<String, List<Symbol>> hierarchy = new HashMap<>();

        for (Map.Entry<String, List<Symbol>> entry : symbols.entrySet()) {
            for (Symbol symbol : entry.getValue()) {
                String parent = symbol.getParentElement();
                if (parent != null) {
                    if (!hierarchy.containsKey(parent)) {
                        hierarchy.put(parent, new ArrayList<>());
                    }
                    hierarchy.get(parent).add(symbol);
                }
            }
        }

        return hierarchy;
    }

    public void printSymbolTable() {
        System.out.println("\n=== SYMBOL TABLE ===");
        System.out.println("Current Scope: " + getCurrentScope());
        System.out.println("Scope Stack: " + scopeStack);
        System.out.println("\nSymbols:");

        for (String name : symbols.keySet()) {
            List<Symbol> symbolList = symbols.get(name);
            for (Symbol symbol : symbolList) {
                System.out.println(symbol);

                if (!symbol.getProperties().isEmpty()) {
                    System.out.println("  Properties:");
                    for (Map.Entry<String, String> prop : symbol.getProperties().entrySet()) {
                        System.out.println("    " + prop.getKey() + " = " + prop.getValue());
                    }
                }
            }
        }

        System.out.println("\nElement Hierarchy:");
        for (Map.Entry<String, String> entry : elementHierarchy.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
    }
}