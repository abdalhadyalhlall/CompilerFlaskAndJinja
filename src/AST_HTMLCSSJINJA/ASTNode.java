package AST_HTMLCSSJINJA;

import java.util.ArrayList;
import java.util.List;

public abstract class ASTNode {
    protected int lineNumber;
    protected List<ASTNode> children;
    private static int printIndentLevel = 0;

    public ASTNode(int lineNumber) {
        this.lineNumber = lineNumber;
        this.children = new ArrayList<>();
    }

    public void addChild(ASTNode child) {
        if (child != null) {
            children.add(child);
        }
    }

    public List<ASTNode> getChildren() {
        return children;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public abstract String getNodeName();

    // طريقة الطباعة المحسنة
    public void print() {
        print("");
    }

    public void print(String indent) {
        System.out.println(indent + toString());
        String childIndent = indent + "  ";
        for (ASTNode child : children) {
            child.print(childIndent);
        }
    }

    // طريقة لطباعة معلومات إضافية (للإرث)
    public String getAdditionalInfo() {
        return "";
    }

    @Override
    public String toString() {
        String additional = getAdditionalInfo();
        if (!additional.isEmpty()) {
            return getNodeName() + " (line " + lineNumber + ") " + additional;
        }
        return getNodeName() + " (line " + lineNumber + ")";
    }
}