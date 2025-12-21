package AST_HTMLCSSJINJA;

import java.util.List;

public class ASTPrinter {

    public static void printAST(ASTNode root) {
        if (root == null) {
            System.out.println("AST is empty");
            return;
        }
        printNode(root, "", true);
    }

    private static void printNode(ASTNode node, String indent, boolean isLast) {
        // طباعة العقدة الحالية
        System.out.print(indent);

        if (isLast) {
            System.out.print("└── ");
            indent += "    ";
        } else {
            System.out.print("├── ");
            indent += "│   ";
        }

        System.out.println(nodeToString(node));

        // طباعة الأطفال
        List<ASTNode> children = node.getChildren();
        for (int i = 0; i < children.size(); i++) {
            printNode(children.get(i), indent, i == children.size() - 1);
        }
    }

    private static String nodeToString(ASTNode node) {
        StringBuilder sb = new StringBuilder();

        sb.append(node.getNodeName());
        sb.append(" (line ").append(node.getLineNumber()).append(")");

        String additionalInfo = node.getAdditionalInfo();
        if (additionalInfo != null && !additionalInfo.isEmpty()) {
            sb.append(" - ").append(additionalInfo);
        }

        // معلومات إضافية خاصة بنوع العقدة
        if (node instanceof AttributeNode) {
            AttributeNode attrNode = (AttributeNode) node;
            sb.append(" [").append(attrNode.getType()).append("]");
        }
        else if (node instanceof TextNode) {
            TextNode textNode = (TextNode) node;
            sb.append(" [").append(textNode.getContext()).append("]");
            String text = textNode.getText();
            if (text != null && !text.isEmpty()) {
                String preview = text.length() > 30 ? text.substring(0, 27) + "..." : text;
                sb.append(" \"").append(preview).append("\"");
            }
        }
        else if (node instanceof HtmlElementNode) {
            HtmlElementNode elemNode = (HtmlElementNode) node;
            sb.append(" [").append(elemNode.getElementType()).append("]");
        }
        else if (node instanceof JinjaNode) {
            JinjaNode jinjaNode = (JinjaNode) node;
            sb.append(" [").append(jinjaNode.getJinjaType()).append("]");
            List<String> args = jinjaNode.getArguments();
            if (!args.isEmpty()) {
                sb.append(" args=").append(args);
            }
        }
        else if (node instanceof JinjaBlockNode) {
            JinjaBlockNode blockNode = (JinjaBlockNode) node;
            sb.append(" [").append(blockNode.getBlockType()).append("]");
            List<JinjaBlockNode.ConditionBlock> conditions = blockNode.getConditionBlocks();
            if (!conditions.isEmpty()) {
                sb.append(" conditions=").append(conditions.size());
            }
        }
        else if (node instanceof CssRuleNode) {
            CssRuleNode cssNode = (CssRuleNode) node;
            sb.append(" [").append(cssNode.getRuleType()).append("]");
        }
        else if (node instanceof StyleAttributeNode) {
            StyleAttributeNode styleNode = (StyleAttributeNode) node;
            sb.append(" [rules=").append(styleNode.getCssRules().size()).append("]");
        }

        return sb.toString();
    }

    // طريقة لطباعة ملخص عن AST
    public static void printASTSummary(ASTNode root) {
        if (root == null) {
            System.out.println("AST is empty");
            return;
        }

        ASTStats stats = collectStats(root);
        System.out.println("=== AST Summary ===");
        System.out.println("Total nodes: " + stats.totalNodes);
        System.out.println("Node types:");
        for (String type : stats.nodeTypes.keySet()) {
            System.out.println("  " + type + ": " + stats.nodeTypes.get(type));
        }
        System.out.println("===================");
    }

    private static ASTStats collectStats(ASTNode node) {
        ASTStats stats = new ASTStats();
        collectStatsRecursive(node, stats);
        return stats;
    }

    private static void collectStatsRecursive(ASTNode node, ASTStats stats) {
        stats.totalNodes++;

        String nodeType = node.getNodeName();
        stats.nodeTypes.put(nodeType, stats.nodeTypes.getOrDefault(nodeType, 0) + 1);

        for (ASTNode child : node.getChildren()) {
            collectStatsRecursive(child, stats);
        }
    }

    static class ASTStats {
        int totalNodes = 0;
        java.util.Map<String, Integer> nodeTypes = new java.util.HashMap<>();
    }

    // طريقة لطباعة AST بتنسيق JSON (مفيد للتصحيح)
    public static void printASTAsJSON(ASTNode root) {
        System.out.println(toJSON(root, 0));
    }

    private static String toJSON(ASTNode node, int indentLevel) {
        StringBuilder sb = new StringBuilder();
        String indent = "  ".repeat(indentLevel);

        sb.append(indent).append("{\n");
        sb.append(indent).append("  \"type\": \"").append(node.getNodeName()).append("\",\n");
        sb.append(indent).append("  \"line\": ").append(node.getLineNumber()).append(",\n");

        String additionalInfo = node.getAdditionalInfo();
        if (!additionalInfo.isEmpty()) {
            sb.append(indent).append("  \"info\": \"").append(escapeJSON(additionalInfo)).append("\",\n");
        }

        // إضافة معلومات خاصة بنوع العقدة
        if (node instanceof AttributeNode) {
            AttributeNode attrNode = (AttributeNode) node;
            sb.append(indent).append("  \"name\": \"").append(escapeJSON(attrNode.getName())).append("\",\n");
            sb.append(indent).append("  \"value\": \"").append(escapeJSON(attrNode.getValue())).append("\",\n");
            sb.append(indent).append("  \"attributeType\": \"").append(attrNode.getType()).append("\",\n");
        }
        else if (node instanceof TextNode) {
            TextNode textNode = (TextNode) node;
            sb.append(indent).append("  \"text\": \"").append(escapeJSON(textNode.getText())).append("\",\n");
            sb.append(indent).append("  \"context\": \"").append(textNode.getContext()).append("\",\n");
        }
        else if (node instanceof HtmlElementNode) {
            HtmlElementNode elemNode = (HtmlElementNode) node;
            sb.append(indent).append("  \"tagName\": \"").append(elemNode.getTagName()).append("\",\n");
            sb.append(indent).append("  \"elementType\": \"").append(elemNode.getElementType()).append("\",\n");
        }
        else if (node instanceof JinjaNode) {
            JinjaNode jinjaNode = (JinjaNode) node;
            sb.append(indent).append("  \"jinjaType\": \"").append(jinjaNode.getJinjaType()).append("\",\n");
            sb.append(indent).append("  \"content\": \"").append(escapeJSON(jinjaNode.getContent())).append("\",\n");
            if (!jinjaNode.getArguments().isEmpty()) {
                sb.append(indent).append("  \"arguments\": ").append(toJSONArray(jinjaNode.getArguments())).append(",\n");
            }
        }

        List<ASTNode> children = node.getChildren();
        if (!children.isEmpty()) {
            sb.append(indent).append("  \"children\": [\n");
            for (int i = 0; i < children.size(); i++) {
                sb.append(toJSON(children.get(i), indentLevel + 2));
                if (i < children.size() - 1) {
                    sb.append(",\n");
                } else {
                    sb.append("\n");
                }
            }
            sb.append(indent).append("  ]\n");
        } else {
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append("\n");
        }

        sb.append(indent).append("}");
        return sb.toString();
    }

    private static String toJSONArray(List<String> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append("\"").append(escapeJSON(list.get(i))).append("\"");
            if (i < list.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static String escapeJSON(String str) {
        if (str == null) return "";
        return str.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}