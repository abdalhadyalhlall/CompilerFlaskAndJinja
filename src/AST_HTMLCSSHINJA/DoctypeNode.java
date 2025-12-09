package AST;

public class DoctypeNode extends ASTNode {
    public DoctypeNode(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public String getNodeName() {
        return "DoctypeNode";
    }
}