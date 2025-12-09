package AST;

public class DocumentNode extends ASTNode {
    public DocumentNode(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public String getNodeName() {
        return "DocumentNode";
    }
}