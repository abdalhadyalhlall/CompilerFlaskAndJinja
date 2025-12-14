package VISITORJINJA.ST;



import java.util.Stack;

public class ScopeManagerImpl implements ScopeManager {
    private Stack<String> scopeStack;
    private int blockCounter;

    public ScopeManagerImpl() {
        scopeStack = new Stack<>();
        blockCounter = 0;
        pushScope("global");
    }

    @Override
    public void pushScope(String scopeName) {
        scopeStack.push(scopeName);
    }

    @Override
    public void popScope() {
        if (!scopeStack.isEmpty()) {
            scopeStack.pop();
        }
    }

    @Override
    public String getCurrentScope() {
        return scopeStack.isEmpty() ? "global" : scopeStack.peek();
    }

    @Override
    public void enterBlock(String blockType) {
        String blockScope = blockType + "_" + (++blockCounter);
        pushScope(blockScope);
    }

    @Override
    public void exitBlock() {
        popScope();
    }

    @Override
    public void enterElement(String elementName) {
        String elementScope = "element_" + elementName + "_" + System.identityHashCode(this);
        pushScope(elementScope);
    }

    @Override
    public void exitElement() {
        popScope();
    }

    public Stack<String> getScopeStack() {
        return scopeStack;
    }
}