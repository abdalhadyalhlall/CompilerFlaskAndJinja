package AST_HTMLCSSJINJA;

import java.util.ArrayList;
import java.util.List;

public class JinjaBlockNode extends ASTNode {
    private BlockType blockType;
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
            ConditionBlock lastCondition = conditionBlocks.get(conditionBlocks.size() - 1);
            lastCondition.getContent().add(node);
            // ⚠️ مهم: لا تضيف node كطفل مباشرة هنا
            // سيتم إضافتها في finalizeBlock()
        }
    }

    public void addElseContent(ASTNode node) {
        elseContent.add(node);
        // ⚠️ مهم: لا تضيف node كطفل مباشرة هنا
        // سيتم إضافتها في finalizeBlock()
    }

    public List<ConditionBlock> getConditionBlocks() {
        return conditionBlocks;
    }

    public List<ASTNode> getElseContent() {
        return elseContent;
    }

    // دالة جديدة: تجميع جميع المحتويات كأطفال للعقدة
    public void finalizeBlock() {
        // مسح الأطفال الحاليين إذا كانوا موجودين
        children.clear();

        // إضافة محتوى جميع الكتل الشرطية
        for (ConditionBlock conditionBlock : conditionBlocks) {
            for (ASTNode node : conditionBlock.getContent()) {
                super.addChild(node);
            }
        }

        // إضافة محتوى else
        for (ASTNode node : elseContent) {
            super.addChild(node);
        }
    }

    @Override
    public String getNodeName() {
        return "JinjaBlockNode[" + blockType + "]";
    }

    @Override
    public String getAdditionalInfo() {
        if (blockType == BlockType.IF && !conditionBlocks.isEmpty()) {
            return "{% if " + conditionBlocks.get(0).getCondition() + " %}";
        } else if (blockType == BlockType.FOR && !conditionBlocks.isEmpty()) {
            return "{% for " + conditionBlocks.get(0).getCondition() + " %}";
        }
        return "{% " + blockType.toString().toLowerCase() + " ... %}";
    }
}