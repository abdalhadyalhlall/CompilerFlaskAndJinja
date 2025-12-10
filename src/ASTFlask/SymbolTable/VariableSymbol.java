package ASTFlask.SymbolTable;

public class VariableSymbol extends Symbol {
    public Object value;

    public VariableSymbol(String name, String type, int line, Scope scope) {
        super(name, type, line, scope);
    }

    @Override
    public String toString() {
        return "Variable(" + name + ":" + type + " at line " + line + ")";
    }
}