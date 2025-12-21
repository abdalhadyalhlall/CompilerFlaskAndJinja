package VISITORJINJA.ST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * فئة تمثل رمز كتلة Jinja2 في جدول الرموز
 * ترث من JinjaSymbol وتضيف وظائف خاصة بالكتل
 */
public class JinjaBlockSymbol extends JinjaSymbol {

    public List<String> conditions; // لكتل if/elif
    public List<JinjaSymbol> thenBlock; // المحتوى عند تحقق الشرط
    public List<JinjaSymbol> elseBlock; // المحتوى عند عدم تحقق الشرط
    public String iterator; // لكتل for
    public String iterable; // لكتل for
    public boolean hasElseBlock; // هل تحتوي على else؟
    public Map<String, Object> blockVariables; // متغيرات الكتلة

    /**
     * مُنشئ كتلة Jinja
     */
    public JinjaBlockSymbol(JinjaType type, String content, int line, int column) {
        super(type, content, line, column);
        this.conditions = new ArrayList<>();
        this.thenBlock = new ArrayList<>();
        this.elseBlock = new ArrayList<>();
        this.hasElseBlock = false;
        this.blockVariables = new HashMap<>();

        // تحليل المحتوى لاستخراج المعلومات
        parseBlockContent();
    }

    /**
     * تحليل محتوى الكتلة
     */
    public void parseBlockContent() {
        String content = getContent();
        JinjaType type = getJinjaType();

        if (content == null || content.isEmpty()) {
            return;
        }

        switch (type) {
            case IF:
                parseIfBlock(content);
                break;
            case FOR:
                parseForBlock(content);
                break;
            case WITH:
                parseWithBlock(content);
                break;
            default:
                // لا يحتاج لتحليل إضافي
                break;
        }
    }

    /**
     * تحليل كتلة IF
     */
    public void parseIfBlock(String content) {
        // إضافة الشرط الأولي
        String condition = content.substring(2).trim(); // إزالة "if"
        conditions.add(condition);
    }

    /**
     * تحليل كتلة FOR
     */
    public void parseForBlock(String content) {
        // استخراج المتغير والمصفوفة
        // مثال: "for item in items"
        String forContent = content.substring(3).trim(); // إزالة "for"

        if (forContent.contains(" in ")) {
            String[] parts = forContent.split(" in ", 2);
            if (parts.length == 2) {
                this.iterator = parts[0].trim();
                this.iterable = parts[1].trim();

                // إضافة المتغير إلى سياق الكتلة
                blockVariables.put(iterator, "undefined");
            }
        }
    }

    /**
     * تحليل كتلة WITH
     */
    public void parseWithBlock(String content) {
        // استخراج التعيينات في with
        // مثال: "with var = value"
        String withContent = content.substring(4).trim(); // إزالة "with"

        if (withContent.contains("=")) {
            String[] parts = withContent.split("=", 2);
            if (parts.length == 2) {
                String variable = parts[0].trim();
                String value = parts[1].trim();
                blockVariables.put(variable, value);
            }
        }
    }

    // ================ Getter و Setter Methods ================

    /**
     * الحصول على الشروط
     */
    public List<String> getConditions() {
        return new ArrayList<>(conditions);
    }

    /**
     * تعيين الشروط
     */
    public void setConditions(List<String> conditions) {
        this.conditions = conditions != null ? new ArrayList<>(conditions) : new ArrayList<>();
    }

    /**
     * الحصول على محتوى then block
     */
    public List<JinjaSymbol> getThenBlock() {
        return new ArrayList<>(thenBlock);
    }

    /**
     * تعيين then block
     */
    public void setThenBlock(List<JinjaSymbol> thenBlock) {
        this.thenBlock = thenBlock != null ? new ArrayList<>(thenBlock) : new ArrayList<>();
    }

    /**
     * الحصول على محتوى else block
     */
    public List<JinjaSymbol> getElseBlock() {
        return new ArrayList<>(elseBlock);
    }

    /**
     * تعيين else block
     */
    public void setElseBlock(List<JinjaSymbol> elseBlock) {
        this.elseBlock = elseBlock != null ? new ArrayList<>(elseBlock) : new ArrayList<>();
        this.hasElseBlock = !elseBlock.isEmpty();
    }

    /**
     * الحصول على المكرر (لـ for)
     */
    public String getIterator() {
        return iterator;
    }

    /**
     * تعيين المكرر (لـ for)
     */
    public void setIterator(String iterator) {
        this.iterator = iterator;
        if (iterator != null && !iterator.isEmpty()) {
            blockVariables.put(iterator, "undefined");
        }
    }

    /**
     * الحصول على المصفوفة (لـ for)
     */
    public String getIterable() {
        return iterable;
    }

    /**
     * تعيين المصفوفة (لـ for)
     */
    public void setIterable(String iterable) {
        this.iterable = iterable;
    }

    /**
     * التحقق مما إذا كانت تحتوي على else block
     */
    public boolean hasElseBlock() {
        return hasElseBlock;
    }

    /**
     * تعيين ما إذا كانت تحتوي على else block
     */
    public void setHasElseBlock(boolean hasElseBlock) {
        this.hasElseBlock = hasElseBlock;
    }

    /**
     * الحصول على متغيرات الكتلة
     */
    public Map<String, Object> getBlockVariables() {
        return new HashMap<>(blockVariables);
    }

    /**
     * تعيين متغيرات الكتلة
     */
    public void setBlockVariables(Map<String, Object> blockVariables) {
        this.blockVariables = blockVariables != null ? new HashMap<>(blockVariables) : new HashMap<>();
    }

    // ================ إدارة البيانات ================

    /**
     * إضافة شرط
     */
    public void addCondition(String condition) {
        if (condition != null && !condition.isEmpty()) {
            conditions.add(condition);
        }
    }

    /**
     * إضافة رمز إلى then block
     */
    public void addToThenBlock(JinjaSymbol symbol) {
        if (symbol != null && symbol != this) {
            thenBlock.add(symbol);
        }
    }

    /**
     * إضافة رمز إلى else block
     */
    public void addToElseBlock(JinjaSymbol symbol) {
        if (symbol != null && symbol != this) {
            elseBlock.add(symbol);
            hasElseBlock = true;
        }
    }

    /**
     * إضافة متغير إلى كتلة
     */
    public void addBlockVariable(String key, Object value) {
        if (key != null && !key.isEmpty()) {
            blockVariables.put(key, value);
        }
    }

    /**
     * الحصول على قيمة متغير من الكتلة
     */
    public Object getBlockVariable(String key) {
        return blockVariables.get(key);
    }

    /**
     * الحصول على عدد الرموز في then block
     */
    public int getThenBlockSize() {
        return thenBlock.size();
    }

    /**
     * الحصول على عدد الرموز في else block
     */
    public int getElseBlockSize() {
        return elseBlock.size();
    }

    /**
     * الحصول على إجمالي عدد الرموز في الكتلة
     */
    public int getTotalSymbolsInBlock() {
        return thenBlock.size() + elseBlock.size();
    }

    /**
     * التحقق مما إذا كانت كتلة IF
     */
    public boolean isIfBlock() {
        return getJinjaType() == JinjaType.IF;
    }

    /**
     * التحقق مما إذا كانت كتلة FOR
     */
    public boolean isForBlock() {
        return getJinjaType() == JinjaType.FOR;
    }

    /**
     * التحقق مما إذا كانت كتلة WITH
     */
    public boolean isWithBlock() {
        return getJinjaType() == JinjaType.WITH;
    }

    /**
     * الحصول على الشروط كسلسلة نصية
     */
    public String getConditionsAsString() {
        return String.join(" AND ", conditions);
    }

    /**
     * الحصول على معلومات الكتلة (لـ for)
     */
    public String getForLoopInfo() {
        if (!isForBlock() || iterator == null || iterable == null) {
            return "Not a FOR loop";
        }
        return iterator + " in " + iterable;
    }

    // ================ المعلومات التفصيلية ================

    /**
     * الحصول على معلومات تفصيلية عن كتلة Jinja
     */
    @Override
    public String getDetailedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jinja2 Block: ").append(getJinjaType()).append("\n");
        sb.append("  Content: ").append(getContent()).append("\n");
        sb.append("  Line: ").append(getLine()).append(", Column: ").append(getColumn()).append("\n");

        if (iterator != null && iterable != null) {
            sb.append("  Iterator: ").append(iterator).append(" in ").append(iterable).append("\n");
        }

        if (!conditions.isEmpty()) {
            sb.append("  Conditions (").append(conditions.size()).append("):\n");
            for (String condition : conditions) {
                sb.append("    • ").append(condition).append("\n");
            }
        }

        sb.append("  Then block symbols: ").append(thenBlock.size()).append("\n");
        sb.append("  Else block symbols: ").append(elseBlock.size()).append("\n");
        sb.append("  Has else block: ").append(hasElseBlock).append("\n");
        sb.append("  Block variables: ").append(blockVariables.size()).append("\n");

        if (!blockVariables.isEmpty()) {
            sb.append("  Variables:\n");
            for (Map.Entry<String, Object> entry : blockVariables.entrySet()) {
                sb.append("    • ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
        }

        if (getParentElement() != null) {
            sb.append("  Parent element: <").append(getParentElement().getName()).append(">\n");
        }

        // عرض عينة من الرموز في then block
        if (!thenBlock.isEmpty()) {
            sb.append("  Then block preview:\n");
            for (int i = 0; i < Math.min(thenBlock.size(), 3); i++) {
                JinjaSymbol symbol = thenBlock.get(i);
                sb.append("    • ").append(symbol.getContent())
                        .append(" [").append(symbol.getJinjaType()).append("]\n");
            }
            if (thenBlock.size() > 3) {
                sb.append("    ... and ").append(thenBlock.size() - 3).append(" more\n");
            }
        }

        // عرض عينة من الرموز في else block
        if (!elseBlock.isEmpty()) {
            sb.append("  Else block preview:\n");
            for (int i = 0; i < Math.min(elseBlock.size(), 3); i++) {
                JinjaSymbol symbol = elseBlock.get(i);
                sb.append("    • ").append(symbol.getContent())
                        .append(" [").append(symbol.getJinjaType()).append("]\n");
            }
            if (elseBlock.size() > 3) {
                sb.append("    ... and ").append(elseBlock.size() - 3).append(" more\n");
            }
        }

        return sb.toString();
    }

    /**
     * تمثيل نصي مختصر
     */
    @Override
    public String toString() {
        String typeStr = getJinjaType().name();
        String contentPreview = getContent().length() > 20 ?
                getContent().substring(0, 17) + "..." : getContent();

        String blockInfo = "";
        if (isForBlock() && iterator != null && iterable != null) {
            blockInfo = " [" + iterator + " in " + iterable + "]";
        } else if (isIfBlock() && !conditions.isEmpty()) {
            blockInfo = " [conditions: " + conditions.size() + "]";
        }

        return String.format("{%s: %s}%s line:%d",
                typeStr, contentPreview, blockInfo, getLine());
    }

    /**
     * تصدير ككائن JSON
     */
    @Override
    public Map<String, Object> toJson() {
        Map<String, Object> json = super.toJson();

        // معلومات الكتلة
        json.put("conditions", new ArrayList<>(conditions));
        json.put("hasElseBlock", hasElseBlock);
        json.put("thenBlockSize", thenBlock.size());
        json.put("elseBlockSize", elseBlock.size());
        json.put("totalBlockSymbols", getTotalSymbolsInBlock());
        json.put("blockVariables", new HashMap<>(blockVariables));

        if (iterator != null) {
            json.put("iterator", iterator);
        }

        if (iterable != null) {
            json.put("iterable", iterable);
        }

        // قائمة رموز then block (أسماء فقط)
        List<String> thenNames = new ArrayList<>();
        for (JinjaSymbol symbol : thenBlock) {
            thenNames.add(symbol.getName() + " [" + symbol.getJinjaType() + "]");
        }
        json.put("thenBlockSymbols", thenNames);

        // قائمة رموز else block (أسماء فقط)
        List<String> elseNames = new ArrayList<>();
        for (JinjaSymbol symbol : elseBlock) {
            elseNames.add(symbol.getName() + " [" + symbol.getJinjaType() + "]");
        }
        json.put("elseBlockSymbols", elseNames);

        return json;
    }

    // ================ طرق ثابتة مساعدة ================

    /**
     * إنشاء كتلة IF
     */
    public static JinjaBlockSymbol createIfBlock(String condition, int line, int column) {
        JinjaBlockSymbol block = new JinjaBlockSymbol(
                JinjaType.IF, "if " + condition, line, column
        );
        block.addCondition(condition);
        return block;
    }

    /**
     * إنشاء كتلة FOR
     */
    public static JinjaBlockSymbol createForBlock(String iterator, String iterable,
                                                  int line, int column) {
        String content = "for " + iterator + " in " + iterable;
        JinjaBlockSymbol block = new JinjaBlockSymbol(
                JinjaType.FOR, content, line, column
        );
        block.setIterator(iterator);
        block.setIterable(iterable);
        return block;
    }

    /**
     * إنشاء كتلة WITH
     */
    public static JinjaBlockSymbol createWithBlock(String assignment, int line, int column) {
        JinjaBlockSymbol block = new JinjaBlockSymbol(
                JinjaType.WITH, "with " + assignment, line, column
        );

        // تحليل التعيين
        if (assignment.contains("=")) {
            String[] parts = assignment.split("=", 2);
            if (parts.length == 2) {
                block.addBlockVariable(parts[0].trim(), parts[1].trim());
            }
        }

        return block;
    }

    /**
     * التحقق مما إذا كان النص يمثل كتلة Jinja
     */
    public static boolean isBlockText(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }

        String trimmed = text.trim();
        if (!trimmed.startsWith("{%") || !trimmed.endsWith("%}")) {
            return false;
        }

        String inner = trimmed.substring(2, trimmed.length() - 2).trim().toLowerCase();
        return inner.startsWith("if ") ||
                inner.startsWith("for ") ||
                inner.startsWith("with ") ||
                inner.startsWith("block ") ||
                inner.startsWith("macro ");
    }
}