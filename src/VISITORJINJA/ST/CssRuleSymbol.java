package VISITORJINJA.ST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * فئة تمثل رمز قاعدة CSS في جدول الرموز
 */
public class CssRuleSymbol extends Symbol {

    /**
     * تصنيفات قواعد CSS
     */
    public enum RuleCategory {
        LAYOUT,          // display, position, float, etc.
        BOX_MODEL,       // margin, padding, width, height, etc.
        TYPOGRAPHY,      // font, text-align, line-height, etc.
        COLOR,           // color, background-color, border-color, etc.
        BORDER,          // border, border-radius, box-shadow, etc.
        EFFECT,          // opacity, filter, transform, etc.
        ANIMATION,       // transition, animation
        FLEX_GRID,       // flex, grid properties
        BACKGROUND,      // background properties
        LIST,            // list-style properties
        TABLE,           // table properties
        OTHER            // قواعد أخرى
    }

    // خصائص القاعدة
    private RuleCategory category;
    private String property;
    private List<String> values;
    private HtmlElementSymbol targetElement; // العنصر الذي تطبق عليه القاعدة
    private boolean isInline; // هل القاعدة inline أم في ملف منفصل
    private String specificity; // تحديد القاعدة (مهم للـ CSS specificity)
    private boolean isImportant; // هل تحتوي على !important
    private Map<String, Object> metadata; // بيانات وصفية إضافية

    /**
     * مُنشئ القاعدة
     */
    public CssRuleSymbol(String property, RuleCategory category,
                         List<String> values, int line, int column) {
        super(property, "CSS_RULE", line, column);
        this.property = property;
        this.category = category;
        this.values = values != null ? new ArrayList<>(values) : new ArrayList<>();
        this.isInline = true; // افتراضيًا inline في سياق HTML+CSS+Jinja
        this.isImportant = false;
        this.metadata = new HashMap<>();
        this.specificity = "0,0,0,0";

        initializeMetadata();
        checkForImportant();
    }

    /**
     * مُنشئ مع قيمة واحدة
     */
    public CssRuleSymbol(String property, RuleCategory category,
                         String value, int line, int column) {
        this(property, category, new ArrayList<>(), line, column);
        if (value != null && !value.isEmpty()) {
            this.values.add(value);
            checkForImportant();
        }
    }

    // ================ Getter و Setter Methods ================

    /**
     * الحصول على الخاصية
     */
    public String getProperty() {
        return property;
    }

    /**
     * تعيين الخاصية
     */
    public void setProperty(String property) {
        this.property = property;
        initializeMetadata();
    }

    /**
     * الحصول على التصنيف
     */
    public RuleCategory getCategory() {
        return category;
    }

    /**
     * تعيين التصنيف
     */
    public void setCategory(RuleCategory category) {
        this.category = category;
    }

    /**
     * الحصول على القيم
     */
    public List<String> getValues() {
        return new ArrayList<>(values);
    }

    /**
     * تعيين القيم
     */
    public void setValues(List<String> values) {
        this.values = values != null ? new ArrayList<>(values) : new ArrayList<>();
        checkForImportant();
        initializeMetadata();
    }

    /**
     * الحصول على العنصر الهدف
     */
    public HtmlElementSymbol getTargetElement() {
        return targetElement;
    }

    /**
     * تعيين العنصر الهدف
     */
    public void setTargetElement(HtmlElementSymbol targetElement) {
        this.targetElement = targetElement;
    }

    /**
     * التحقق مما إذا كانت القاعدة inline
     */
    public boolean isInline() {
        return isInline;
    }

    /**
     * تعيين نوع القاعدة (inline/external)
     */
    public void setInline(boolean inline) {
        isInline = inline;
    }

    /**
     * الحصول على التحديدية (specificity)
     */
    public String getSpecificity() {
        return specificity;
    }

    /**
     * تعيين التحديدية (specificity)
     */
    public void setSpecificity(String specificity) {
        this.specificity = specificity;
    }

    /**
     * التحقق مما إذا كانت القاعدة تحتوي على !important
     */
    public boolean isImportant() {
        return isImportant;
    }

    /**
     * تعيين أهمية القاعدة
     */
    public void setImportant(boolean important) {
        isImportant = important;
    }

    /**
     * الحصول على البيانات الوصفية
     */
    public Map<String, Object> getMetadata() {
        return new HashMap<>(metadata);
    }

    /**
     * الحصول على بيانات وصفية محددة
     */
    public Object getMetadata(String key) {
        return metadata.get(key);
    }

    /**
     * تعيين بيانات وصفية
     */
    public void setMetadata(String key, Object value) {
        metadata.put(key, value);
    }

    // ================ باقي الأكواد (كما هي) ================

    /**
     * تهيئة البيانات الوصفية
     */
    private void initializeMetadata() {
        metadata.put("isShorthand", isShorthandProperty(property));
        metadata.put("isVendorPrefixed", isVendorPrefixed(property));
        metadata.put("isCustomProperty", property.startsWith("--"));
        metadata.put("hasMultipleValues", values.size() > 1);

        // تحليل القيم
        if (!values.isEmpty()) {
            metadata.put("hasColorValue", hasColorValue());
            metadata.put("hasLengthValue", hasLengthValue());
            metadata.put("hasUrlValue", hasUrlValue());
            metadata.put("hasKeywordValue", hasKeywordValue());
            metadata.put("hasFunctionValue", hasFunctionValue());
        }
    }

    /**
     * التحقق من وجود !important
     */
    private void checkForImportant() {
        if (!values.isEmpty()) {
            String lastValue = values.get(values.size() - 1);
            if (lastValue != null && lastValue.trim().equalsIgnoreCase("!important")) {
                this.isImportant = true;
                // إزالة !important من القيم
                values.set(values.size() - 1,
                        lastValue.replace("!important", "").trim());
            }
        }
    }

    /**
     * إضافة قيمة للقاعدة
     */
    public void addValue(String value) {
        if (value != null && !value.isEmpty()) {
            values.add(value);
            checkForImportant();
            initializeMetadata();
        }
    }

    /**
     * إضافة قيم متعددة
     */
    public void addValues(List<String> values) {
        if (values != null) {
            this.values.addAll(values);
            checkForImportant();
            initializeMetadata();
        }
    }

    /**
     * الحصول على القيم كسلسلة نصية
     */
    public String getValuesAsString() {
        return String.join(" ", values);
    }

    /**
     * الحصول على القيمة الأولى
     */
    public String getFirstValue() {
        return values.isEmpty() ? "" : values.get(0);
    }

    // ================ التحليلات ================

    /**
     * التحقق مما إذا كانت الخاصية shorthand
     */
    public boolean isShorthand() {
        return Boolean.TRUE.equals(metadata.get("isShorthand"));
    }

    /**
     * التحقق مما إذا كانت الخاصية vendor-prefixed
     */
    public boolean isVendorPrefixed() {
        return Boolean.TRUE.equals(metadata.get("isVendorPrefixed"));
    }

    /**
     * التحقق مما إذا كانت خاصية مخصصة (CSS custom property)
     */
    public boolean isCustomProperty() {
        return Boolean.TRUE.equals(metadata.get("isCustomProperty"));
    }

    /**
     * التحقق مما إذا كانت تحتوي على قيم متعددة
     */
    public boolean hasMultipleValues() {
        return Boolean.TRUE.equals(metadata.get("hasMultipleValues"));
    }

    /**
     * التحقق مما إذا كانت تحتوي على قيمة لون
     */
    public boolean hasColorValue() {
        if (Boolean.TRUE.equals(metadata.get("hasColorValue"))) {
            return true;
        }

        for (String value : values) {
            if (isColorValue(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * التحقق مما إذا كانت تحتوي على قيمة طول
     */
    public boolean hasLengthValue() {
        if (Boolean.TRUE.equals(metadata.get("hasLengthValue"))) {
            return true;
        }

        for (String value : values) {
            if (isLengthValue(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * التحقق مما إذا كانت تحتوي على قيمة URL
     */
    public boolean hasUrlValue() {
        if (Boolean.TRUE.equals(metadata.get("hasUrlValue"))) {
            return true;
        }

        for (String value : values) {
            if (isUrlValue(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * التحقق مما إذا كانت تحتوي على قيمة keyword
     */
    public boolean hasKeywordValue() {
        return Boolean.TRUE.equals(metadata.get("hasKeywordValue"));
    }

    /**
     * التحقق مما إذا كانت تحتوي على دالة
     */
    public boolean hasFunctionValue() {
        return Boolean.TRUE.equals(metadata.get("hasFunctionValue"));
    }

    /**
     * التحقق مما إذا كانت القاعدة فارغة
     */
    public boolean isEmpty() {
        return values.isEmpty() ||
                (values.size() == 1 && values.get(0).trim().isEmpty());
    }

    /**
     * التحقق مما إذا كانت القاعدة صالحة
     */
    public boolean isValid() {
        return !property.isEmpty() && !values.isEmpty() && !isEmpty();
    }

    // ================ التمثيل النصي ================

    /**
     * الحصول على القاعدة بصيغة CSS كاملة
     */
    public String toCssString() {
        StringBuilder sb = new StringBuilder();
        sb.append(property).append(": ").append(getValuesAsString());

        if (isImportant) {
            sb.append(" !important");
        }

        sb.append(";");
        return sb.toString();
    }

    /**
     * الحصول على معلومات تفصيلية عن القاعدة
     */
    @Override
    public String getDetailedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("CSS Rule: ").append(property).append("\n");
        sb.append("  Category: ").append(category).append("\n");
        sb.append("  Values: ").append(getValuesAsString()).append("\n");
        sb.append("  Line: ").append(line).append(", Column: ").append(column).append("\n");
        sb.append("  Type: ").append(isInline ? "inline" : "external").append("\n");
        sb.append("  Important: ").append(isImportant).append("\n");
        sb.append("  Specificity: ").append(specificity).append("\n");
        sb.append("  Valid: ").append(isValid()).append("\n");

        if (targetElement != null) {
            sb.append("  Target element: <").append(targetElement.name).append(">\n");
        }

        // معلومات إضافية
        if (isShorthand()) {
            sb.append("  Note: Shorthand property\n");
        }
        if (isVendorPrefixed()) {
            sb.append("  Note: Vendor-prefixed property\n");
        }
        if (isCustomProperty()) {
            sb.append("  Note: CSS Custom Property\n");
        }

        return sb.toString();
    }

    /**
     * تمثيل نصي مختصر للقاعدة
     */
    @Override
    public String toString() {
        String importantStr = isImportant ? " !important" : "";
        String targetStr = targetElement != null ?
                " on <" + targetElement.name + ">" : "";
        return String.format("%s: %s%s%s [%s]",
                property, getValuesAsString(), importantStr, targetStr, category);
    }

    /**
     * تصدير القاعدة ككائن JSON
     */
    public Map<String, Object> toJson() {
        Map<String, Object> json = new HashMap<>();
        json.put("property", property);
        json.put("category", category.name());
        json.put("values", new ArrayList<>(values));
        json.put("valuesString", getValuesAsString());
        json.put("line", line);
        json.put("column", column);
        json.put("isInline", isInline);
        json.put("isImportant", isImportant);
        json.put("specificity", specificity);
        json.put("isValid", isValid());

        if (targetElement != null) {
            json.put("targetElement", targetElement.name);
        }

        // البيانات الوصفية
        json.put("isShorthand", isShorthand());
        json.put("isVendorPrefixed", isVendorPrefixed());
        json.put("isCustomProperty", isCustomProperty());
        json.put("hasColorValue", hasColorValue());
        json.put("hasLengthValue", hasLengthValue());
        json.put("hasUrlValue", hasUrlValue());

        return json;
    }

    // ================ طرق ثابتة مساعدة (كما هي) ================

    /**
     * تحديد تصنيف الخاصية
     */
    public static RuleCategory getCategoryFromProperty(String property) {
        if (property == null || property.isEmpty()) {
            return RuleCategory.OTHER;
        }

        String propLower = property.toLowerCase();

        // التخطيط
        if (propLower.contains("display") || propLower.contains("position") ||
                propLower.contains("float") || propLower.contains("clear") ||
                propLower.contains("overflow") || propLower.contains("visibility") ||
                propLower.contains("z-index")) {
            return RuleCategory.LAYOUT;
        }

        // نموذج الصندوق
        if (propLower.contains("margin") || propLower.contains("padding") ||
                propLower.contains("width") || propLower.contains("height") ||
                propLower.contains("min-") || propLower.contains("max-") ||
                propLower.contains("box-sizing") || propLower.contains("box-shadow")) {
            return RuleCategory.BOX_MODEL;
        }

        // الطباعة
        if (propLower.contains("font") || propLower.contains("text") ||
                propLower.contains("line-height") || propLower.contains("letter-spacing") ||
                propLower.contains("word-spacing") || propLower.contains("white-space") ||
                propLower.contains("vertical-align")) {
            return RuleCategory.TYPOGRAPHY;
        }

        // الألوان
        if (propLower.contains("color") || propLower.contains("background-color") ||
                propLower.contains("border-color") || propLower.contains("fill") ||
                propLower.contains("stroke") || propLower.contains("opacity")) {
            return RuleCategory.COLOR;
        }

        // الحدود
        if (propLower.contains("border") || propLower.contains("outline") ||
                propLower.contains("border-radius") || propLower.contains("box-shadow")) {
            return RuleCategory.BORDER;
        }

        // التأثيرات
        if (propLower.contains("transform") || propLower.contains("filter") ||
                propLower.contains("blend") || propLower.contains("mix-blend") ||
                propLower.contains("backdrop-filter") || propLower.contains("perspective")) {
            return RuleCategory.EFFECT;
        }

        // التحريك
        if (propLower.contains("animation") || propLower.contains("transition") ||
                propLower.contains("keyframes") || propLower.contains("timing")) {
            return RuleCategory.ANIMATION;
        }

        // Flex/Grid
        if (propLower.contains("flex") || propLower.contains("grid") ||
                propLower.contains("align") || propLower.contains("justify")) {
            return RuleCategory.FLEX_GRID;
        }

        // الخلفية
        if (propLower.contains("background") && !propLower.contains("color")) {
            return RuleCategory.BACKGROUND;
        }

        return RuleCategory.OTHER;
    }

    /**
     * التحقق مما إذا كانت الخاصية shorthand
     */
    public static boolean isShorthandProperty(String property) {
        if (property == null) return false;

        String[] shorthandProperties = {
                "margin", "padding", "border", "background",
                "font", "list-style", "animation", "transition",
                "flex", "grid", "place", "inset"
        };

        String propLower = property.toLowerCase();
        for (String shorthand : shorthandProperties) {
            if (propLower.equals(shorthand) || propLower.startsWith(shorthand + "-")) {
                return true;
            }
        }
        return false;
    }

    /**
     * التحقق مما إذا كانت الخاصية vendor-prefixed
     */
    public static boolean isVendorPrefixed(String property) {
        if (property == null) return false;
        return property.startsWith("-webkit-") ||
                property.startsWith("-moz-") ||
                property.startsWith("-ms-") ||
                property.startsWith("-o-");
    }

    /**
     * التحقق مما إذا كانت القيمة لونًا
     */
    public static boolean isColorValue(String value) {
        if (value == null) return false;

        // ألوان مسماة
        if (value.equalsIgnoreCase("red") || value.equalsIgnoreCase("blue") ||
                value.equalsIgnoreCase("green") || value.equalsIgnoreCase("black") ||
                value.equalsIgnoreCase("white") || value.equalsIgnoreCase("transparent")) {
            return true;
        }

        // HEX colors
        if (value.matches("^#[0-9a-fA-F]{3,8}$")) {
            return true;
        }

        // RGB/RGBA/HSL/HSLA functions
        if (value.startsWith("rgb(") || value.startsWith("rgba(") ||
                value.startsWith("hsl(") || value.startsWith("hsla(")) {
            return true;
        }

        return false;
    }

    /**
     * التحقق مما إذا كانت القيمة طولًا
     */
    public static boolean isLengthValue(String value) {
        if (value == null) return false;

        // وحدات الطول
        return value.matches("^-?\\d+(\\.\\d+)?(px|em|rem|vh|vw|vmin|vmax|%|pt|cm|mm|in|pc)$") ||
                value.equals("0") || value.equals("auto");
    }

    /**
     * التحقق مما إذا كانت القيمة URL
     */
    public static boolean isUrlValue(String value) {
        if (value == null) return false;
        return value.startsWith("url(") ||
                (value.startsWith("\"") && value.contains("://")) ||
                (value.startsWith("'") && value.contains("://"));
    }
}