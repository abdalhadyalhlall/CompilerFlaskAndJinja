package AST;

import java.util.ArrayList;
import java.util.List;

public class JinjaBlockNode extends ASTNode {
    private BlockType blockType;
    private String condition;
    private List<ConditionBlock> conditionBlocks;
    private List<ASTNode> elseContent;

    public enum BlockType {
        IF,
        FOR,
        WITH,
        MACRO,
        BLOCK
    }

    public static class ConditionBlock {
        private String condition;
        private List<ASTNode> content;

        public ConditionBlock(String condition) {
            this.condition = condition;
            this.content = new ArrayList<>();
        }

        public String getCondition() {
            return condition;
        }

        public List<ASTNode> getContent() {
            return content;
        }
    }

    public JinjaBlockNode(int lineNumber, BlockType blockType) {
        super(lineNumber);
        this.blockType = blockType;
        this.conditionBlocks = new ArrayList<>();
        this.elseContent = new ArrayList<>();
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public void addConditionBlock(String condition) {
        conditionBlocks.add(new ConditionBlock(condition));
    }

    public void addContentToLastCondition(ASTNode node) {
        if (!conditionBlocks.isEmpty()) {
            conditionBlocks.get(conditionBlocks.size() - 1).getContent().add(node);
            this.addChild(node);
        }
    }

    public void addElseContent(ASTNode node) {
        elseContent.add(node);
        this.addChild(node);
    }

    public List<ConditionBlock> getConditionBlocks() {
        return conditionBlocks;
    }

    public List<ASTNode> getElseContent() {
        return elseContent;
    }

    @Override
    public String getNodeName() {
        return "JinjaBlockNode[" + blockType + "]";
    }

    @Override
    public String getAdditionalInfo() {
        if (blockType == BlockType.IF) {
            return "{% if " + conditionBlocks.get(0).getCondition() + " %}";
        } else if (blockType == BlockType.FOR) {
            return "{% for ... in ... %}";
        }
        return "";
    }
}