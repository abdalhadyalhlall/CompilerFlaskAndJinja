package VISITORJINJA.ST;


public interface ScopeManager {
    void pushScope(String scopeName);
    void popScope();
    String getCurrentScope();
    void enterBlock(String blockType);
    void exitBlock();
    void enterElement(String elementName);
    void exitElement();
}
