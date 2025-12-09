package AST_HTMLCSSJINJA;

import java.util.ArrayList;
import java.util.List;

public class JinjaNode extends ASTNode {
    private JinjaType jinjaType;
    private String content;
    private List<String> arguments;

    public enum JinjaType {
        EXPRESSION,
        STATEMENT,
        COMMENT,
        IF,
        FOR,
        SET,
        BLOCK,
        WITH,
        MACRO,
        INCLUDE,
        EXTENDS
    }

    public JinjaNode(int lineNumber, JinjaType jinjaType, String content) {
        super(lineNumber);
        this.jinjaType = jinjaType;
        this.content = content;
        this.arguments = new ArrayList<>();
    }

    public JinjaType getJinjaType() {
        return jinjaType;
    }

    public String getContent() {
        return content;
    }

    public void addArgument(String argument) {
        arguments.add(argument);
    }

    public List<String> getArguments() {
        return arguments;
    }

    @Override
    public String getNodeName() {
        return "JinjaNode[" + jinjaType + "]";
    }

    @Override
    public String getAdditionalInfo() {
        if (jinjaType == JinjaType.COMMENT) {
            return "{# " + content + " #}";
        } else if (jinjaType == JinjaType.EXPRESSION) {
            return "{{ " + content + " }}";
        } else if (jinjaType == JinjaType.STATEMENT) {
            return "{% " + content + " %}";
        }
        return content;
    }
}