// SymbolTableOperations.java
package VISITORJINJA.ST;

import VISITORJINJA.ST.*;

import java.util.List;
import java.util.Map;

public interface SymbolTableOperations {
    void addSymbol(String name, SymbolType type, int lineNumber);
    void addSymbol(String name, SymbolType type, int lineNumber, Map<String, String> properties);
    List<Symbol> lookup(String name);
    List<Symbol> lookupInCurrentScope(String name);
    List<Symbol> getAllSymbols();
    List<Symbol> getSymbolsByType(SymbolType type);
    List<Symbol> getSymbolsByLine(int lineNumber);
}