package VISITORJINJA.ST;

// SymbolTableFactory.java


public class SymbolTableFactory {
    public static SymbolTableImpl createDefaultSymbolTable() {
        return new SymbolTableImpl();
    }

    public static SymbolTableImpl createSymbolTableWithCustomScopeManager(ScopeManager scopeManager) {
        return new SymbolTableImpl(scopeManager);
    }
}