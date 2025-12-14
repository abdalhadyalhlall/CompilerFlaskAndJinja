package VISITORJINJA.ST;


import java.util.*;

public class SymbolTableImpl implements SymbolTableOperations {
    private Map<String, List<Symbol>> symbols;
    private ScopeManager scopeManager;
    private Map<String, String> elementHierarchy;

    public SymbolTableImpl() {
        symbols = new HashMap<>();
        scopeManager = new ScopeManagerImpl();
        elementHierarchy = new HashMap<>();
    }

    public SymbolTableImpl(ScopeManager scopeManager) {
        symbols = new HashMap<>();
        this.scopeManager = scopeManager;
        elementHierarchy = new HashMap<>();
    }

    @Override
    public void addSymbol(String name, SymbolType type, int lineNumber) {
        addSymbol(name, type, lineNumber, null);
    }

    @Override
    public void addSymbol(String name, SymbolType type, int lineNumber, Map<String, String> properties) {
        Symbol symbol = new Symbol(name, type, lineNumber, scopeManager.getCurrentScope());

        if (properties != null) {
            symbol.getProperties().putAll(properties);
        }

        if (scopeManager instanceof ScopeManagerImpl) {
            Stack<String> scopeStack = ((ScopeManagerImpl) scopeManager).getScopeStack();
            if (!scopeStack.isEmpty() && scopeStack.size() > 1) {
                symbol.setParentElement(getParentElementFromScope(scopeStack));
            }
        }

        if (!symbols.containsKey(name)) {
            symbols.put(name, new ArrayList<>());
        }
        symbols.get(name).add(symbol);

        if (isElementType(type)) {
            updateElementHierarchy(name, symbol);
        }
    }

    private boolean isElementType(SymbolType type) {
        return type == SymbolType.HTML_ELEMENT ||
                type == SymbolType.NORMAL_ELEMENT ||
                type == SymbolType.SELF_CLOSING_ELEMENT;
    }

    private String getParentElementFromScope(Stack<String> scopeStack) {
        for (int i = scopeStack.size() - 1; i >= 0; i--) {
            String scope = scopeStack.get(i);
            if (scope.startsWith("element_")) {
                return scope.substring(8).split("_")[0];
            }
        }
        return null;
    }

    private void updateElementHierarchy(String elementName, Symbol elementSymbol) {
        String parentElement = elementSymbol.getParentElement();
        if (parentElement != null) {
            elementHierarchy.put(elementName, parentElement);
        }
    }

    @Override
    public List<Symbol> lookup(String name) {
        return symbols.getOrDefault(name, new ArrayList<>());
    }

    @Override
    public List<Symbol> lookupInCurrentScope(String name) {
        List<Symbol> result = new ArrayList<>();
        String currentScope = scopeManager.getCurrentScope();

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

    @Override
    public List<Symbol> getAllSymbols() {
        List<Symbol> allSymbols = new ArrayList<>();
        for (List<Symbol> symbolList : symbols.values()) {
            allSymbols.addAll(symbolList);
        }
        return allSymbols;
    }

    @Override
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

    @Override
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

    public ScopeManager getScopeManager() {
        return scopeManager;
    }

    public Map<String, String> getElementHierarchy() {
        return elementHierarchy;
    }

    public void printSymbolTable() {
        System.out.println("\n=== SYMBOL TABLE ===");
        System.out.println("Current Scope: " + scopeManager.getCurrentScope());

        if (scopeManager instanceof ScopeManagerImpl) {
            System.out.println("Scope Stack: " + ((ScopeManagerImpl) scopeManager).getScopeStack());
        }

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
