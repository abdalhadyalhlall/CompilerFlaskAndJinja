package VISITORJINJA.ST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * فئة تمثل رمز Jinja2 في جدول الرموز
 */
public class JinjaSymbol extends Symbol {

    /**
     * أنواع Jinja2
     */
    public enum JinjaType {
        EXPRESSION,    // {{ ... }}
        STATEMENT,     // {% ... %}
        COMMENT,       // {# ... #}
        BLOCK,         // {% block ... %}
        MACRO,         // {% macro ... %}
        INCLUDE,       // {% include ... %}
        EXTENDS,       // {% extends ... %}
        IMPORT,        // {% import ... %}
        IF,            // {% if ... %}
        FOR,           // {% for ... %}
        SET,           // {% set ... %}
        WITH           // {% with ... %}
    }

    public JinjaType jinjaType;
    public String content;
    public Map<String, Object> context; // المتغيرات المتاحة في هذا السياق
    public List<JinjaSymbol> nestedSymbols; // الرموز المتداخلة داخل Jinja
    public HtmlElementSymbol parentElement; // العنصر HTML الحاوي
    public boolean isControlStructure; // هل هي بنية تحكم؟
    public boolean hasFilters; // هل تحتوي على فلاتر؟

    /**
     * مُنشئ رمز Jinja2
     */
    public JinjaSymbol(JinjaType type, String content, int line, int column) {
        super(generateName(type), "JINJA", line, column);
        this.jinjaType = type;
        this.content = content != null ? content : "";
        this.context = new HashMap<>();
        this.nestedSymbols = new ArrayList<>();
        this.parentElement = null;
        this.isControlStructure = determineIfControlStructure(type, content);
        this.hasFilters = determineIfHasFilters(type, content);
    }

    /**
     * توليد اسم للرمز بناءً على النوع
     */
    public static String generateName(JinjaType type) {
        return "jinja_" + type.toString().toLowerCase();
    }

    /**
     * تحديد ما إذا كانت بنية تحكم
     */
    public boolean determineIfControlStructure(JinjaType type, String content) {
        if (type != JinjaType.STATEMENT) {
            return false;
        }

        if (content == null) {
            return false;
        }

        String lowerContent = content.toLowerCase().trim();
        return lowerContent.startsWith("if ") ||
                lowerContent.startsWith("for ") ||
                lowerContent.startsWith("set ") ||
                lowerContent.startsWith("with ") ||
                lowerContent.startsWith("block ") ||
                lowerContent.startsWith("macro ");
    }

    /**
     * تحديد ما إذا كانت تحتوي على فلاتر
     */
    public boolean determineIfHasFilters(JinjaType type, String content) {
        if (type != JinjaType.EXPRESSION || content == null) {
            return false;
        }
        return content.contains("|");
    }

    // ================ Getter و Setter Methods ================

    /**
     * الحصول على نوع Jinja
     */
    public JinjaType getJinjaType() {
        return jinjaType;
    }

    /**
     * تعيين نوع Jinja
     */
    public void setJinjaType(JinjaType jinjaType) {
        this.jinjaType = jinjaType;
        this.isControlStructure = determineIfControlStructure(jinjaType, content);
        setName(generateName(jinjaType));
    }

    /**
     * الحصول على المحتوى
     */
    public String getContent() {
        return content;
    }

    /**
     * تعيين المحتوى
     */
    public void setContent(String content) {
        this.content = content != null ? content : "";
        this.isControlStructure = determineIfControlStructure(jinjaType, this.content);
        this.hasFilters = determineIfHasFilters(jinjaType, this.content);
    }

    /**
     * الحصول على سياق المتغيرات
     */
    public Map<String, Object> getContext() {
        return new HashMap<>(context);
    }

    /**
     * تعيين سياق المتغيرات
     */
    public void setContext(Map<String, Object> context) {
        this.context = context != null ? new HashMap<>(context) : new HashMap<>();
    }

    /**
     * الحصول على الرموز المتداخلة
     */
    public List<JinjaSymbol> getNestedSymbols() {
        return new ArrayList<>(nestedSymbols);
    }

    /**
     * تعيين الرموز المتداخلة
     */
    public void setNestedSymbols(List<JinjaSymbol> nestedSymbols) {
        this.nestedSymbols = nestedSymbols != null ? new ArrayList<>(nestedSymbols) : new ArrayList<>();
    }

    /**
     * الحصول على العنصر الأب
     */
    public HtmlElementSymbol getParentElement() {
        return parentElement;
    }

    /**
     * تعيين العنصر الأب
     */
    public void setParentElement(HtmlElementSymbol parentElement) {
        this.parentElement = parentElement;
    }

    /**
     * التحقق مما إذا كانت بنية تحكم
     */
    public boolean isControlStructure() {
        return isControlStructure;
    }

    /**
     * التحقق مما إذا كانت تحتوي على فلاتر
     */
    public boolean hasFilters() {
        return hasFilters;
    }

    // ================ إدارة البيانات ================

    /**
     * إضافة رمز متداخل
     */
    public void addNestedSymbol(JinjaSymbol symbol) {
        if (symbol != null && symbol != this) {
            nestedSymbols.add(symbol);
        }
    }

    /**
     * إضافة متغير إلى السياق
     */
    public void addToContext(String key, Object value) {
        if (key != null && !key.isEmpty()) {
            context.put(key, value);
        }
    }

    /**
     * الحصول على متغير من السياق
     */
    public Object getFromContext(String key) {
        return context.get(key);
    }

    /**
     * التحقق مما إذا كان المتغير موجودًا في السياق
     */
    public boolean hasInContext(String key) {
        return context.containsKey(key);
    }

    /**
     * إزالة متغير من السياق
     */
    public Object removeFromContext(String key) {
        return context.remove(key);
    }

    /**
     * مسح السياق بالكامل
     */
    public void clearContext() {
        context.clear();
    }

    /**
     * الحصول على حجم السياق
     */
    public int getContextSize() {
        return context.size();
    }

    /**
     * الحصول على عدد الرموز المتداخلة
     */
    public int getNestedSymbolsCount() {
        return nestedSymbols.size();
    }

    /**
     * التحقق مما إذا كان تعبير متغير
     */
    public boolean isVariableExpression() {
        return jinjaType == JinjaType.EXPRESSION && !content.contains("|");
    }

    /**
     * التحقق مما إذا كان تعبير مع فلاتر
     */
    public boolean isFilteredExpression() {
        return jinjaType == JinjaType.EXPRESSION && content.contains("|");
    }

    /**
     * التحقق مما إذا كان تعليقًا
     */
    public boolean isComment() {
        return jinjaType == JinjaType.COMMENT;
    }

    /**
     * التحقق مما إذا كان بيانًا
     */
    public boolean isStatement() {
        return jinjaType == JinjaType.STATEMENT;
    }

    /**
     * التحقق مما إذا كان بيان IF
     */
    public boolean isIfStatement() {
        return jinjaType == JinjaType.STATEMENT &&
                content.toLowerCase().trim().startsWith("if ");
    }

    /**
     * التحقق مما إذا كان بيان FOR
     */
    public boolean isForStatement() {
        return jinjaType == JinjaType.STATEMENT &&
                content.toLowerCase().trim().startsWith("for ");
    }

    /**
     * التحقق مما إذا كان بيان SET
     */
    public boolean isSetStatement() {
        return jinjaType == JinjaType.STATEMENT &&
                content.toLowerCase().trim().startsWith("set ");
    }

    /**
     * الحصول على متغيرات التعيين (لبيانات SET)
     */
    public List<String> getSetVariables() {
        List<String> variables = new ArrayList<>();
        if (isSetStatement() && content != null) {
            String[] parts = content.split("=", 2);
            if (parts.length > 0) {
                String varPart = parts[0].trim();
                // إزالة كلمة set من البداية
                if (varPart.toLowerCase().startsWith("set ")) {
                    varPart = varPart.substring(4).trim();
                }
                variables.add(varPart);
            }
        }
        return variables;
    }

    // ================ المعلومات التفصيلية ================

    /**
     * الحصول على معلومات تفصيلية عن رمز Jinja2
     */
    @Override
    public String getDetailedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jinja2 ").append(jinjaType).append("\n");
        sb.append("  Content: ").append(content).append("\n");
        sb.append("  Line: ").append(getLine()).append(", Column: ").append(getColumn()).append("\n");
        sb.append("  Control Structure: ").append(isControlStructure).append("\n");
        sb.append("  Has Filters: ").append(hasFilters).append("\n");
        sb.append("  Context variables: ").append(context.size()).append("\n");
        sb.append("  Nested symbols: ").append(nestedSymbols.size()).append("\n");

        if (!context.isEmpty()) {
            sb.append("  Context:\n");
            for (Map.Entry<String, Object> entry : context.entrySet()) {
                sb.append("    • ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
        }

        if (parentElement != null) {
            sb.append("  Parent element: <").append(parentElement.getName()).append(">\n");
        }

        if (!nestedSymbols.isEmpty()) {
            sb.append("  Nested symbols preview:\n");
            for (int i = 0; i < Math.min(nestedSymbols.size(), 3); i++) {
                sb.append("    • ").append(nestedSymbols.get(i).getContent())
                        .append(" [").append(nestedSymbols.get(i).getJinjaType()).append("]\n");
            }
            if (nestedSymbols.size() > 3) {
                sb.append("    ... and ").append(nestedSymbols.size() - 3).append(" more\n");
            }
        }

        return sb.toString();
    }

    /**
     * تمثيل نصي مختصر
     */
    @Override
    public String toString() {
        String typeStr = jinjaType.name().substring(0, Math.min(4, jinjaType.name().length()));
        String contentPreview = content.length() > 30 ?
                content.substring(0, 27) + "..." : content;

        return String.format("{%s: %s} [%s] line:%d",
                typeStr, contentPreview, getName(), getLine());
    }

    /**
     * تصدير ككائن JSON
     */
    public Map<String, Object> toJson() {
        Map<String, Object> json = new HashMap<>();
        json.put("type", jinjaType.name());
        json.put("name", getName());
        json.put("content", content);
        json.put("line", getLine());
        json.put("column", getColumn());
        json.put("isControlStructure", isControlStructure);
        json.put("hasFilters", hasFilters);
        json.put("contextSize", context.size());
        json.put("nestedSymbolsCount", nestedSymbols.size());

        if (parentElement != null) {
            json.put("parentElement", parentElement.getName());
        }

        // قائمة الرموز المتداخلة (أسماء فقط)
        List<String> nestedNames = new ArrayList<>();
        for (JinjaSymbol symbol : nestedSymbols) {
            nestedNames.add(symbol.getName() + " [" + symbol.getJinjaType() + "]");
        }
        json.put("nestedSymbols", nestedNames);

        return json;
    }

    /**
     * الحصول على رمز Jinja2 بصيغة نصية
     */
    public String toJinjaString() {
        switch (jinjaType) {
            case EXPRESSION:
                return "{{ " + content + " }}";
            case STATEMENT:
                return "{% " + content + " %}";
            case COMMENT:
                return "{# " + content + " #}";
            default:
                return "{% " + content + " %}";
        }
    }

    // ================ طرق ثابتة مساعدة ================

    /**
     * تحديد نوع Jinja من النص
     */
    public static JinjaType determineTypeFromText(String text) {
        if (text == null || text.isEmpty()) {
            return JinjaType.STATEMENT;
        }

        String trimmed = text.trim();

        if (trimmed.startsWith("{{") && trimmed.endsWith("}}")) {
            return JinjaType.EXPRESSION;
        } else if (trimmed.startsWith("{#") && trimmed.endsWith("#}")) {
            return JinjaType.COMMENT;
        } else if (trimmed.startsWith("{%")) {
            // تحليل نوع البيان
            String inner = trimmed.substring(2, trimmed.length() - 2).trim().toLowerCase();

            if (inner.startsWith("block")) {
                return JinjaType.BLOCK;
            } else if (inner.startsWith("macro")) {
                return JinjaType.MACRO;
            } else if (inner.startsWith("include")) {
                return JinjaType.INCLUDE;
            } else if (inner.startsWith("extends")) {
                return JinjaType.EXTENDS;
            } else if (inner.startsWith("import")) {
                return JinjaType.IMPORT;
            } else if (inner.startsWith("if")) {
                return JinjaType.IF;
            } else if (inner.startsWith("for")) {
                return JinjaType.FOR;
            } else if (inner.startsWith("set")) {
                return JinjaType.SET;
            } else if (inner.startsWith("with")) {
                return JinjaType.WITH;
            } else {
                return JinjaType.STATEMENT;
            }
        }

        return JinjaType.STATEMENT;
    }

    /**
     * استخراج المحتوى من نص Jinja
     */
    public static String extractContentFromText(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        String trimmed = text.trim();

        if (trimmed.startsWith("{{") && trimmed.endsWith("}}")) {
            return trimmed.substring(2, trimmed.length() - 2).trim();
        } else if (trimmed.startsWith("{#") && trimmed.endsWith("#}")) {
            return trimmed.substring(2, trimmed.length() - 2).trim();
        } else if (trimmed.startsWith("{%") && trimmed.endsWith("%}")) {
            return trimmed.substring(2, trimmed.length() - 2).trim();
        }

        return trimmed;
    }

    /**
     * إنشاء رمز Jinja من النص
     */
    public static JinjaSymbol fromText(String text, int line, int column) {
        JinjaType type = determineTypeFromText(text);
        String content = extractContentFromText(text);
        return new JinjaSymbol(type, content, line, column);
    }
}