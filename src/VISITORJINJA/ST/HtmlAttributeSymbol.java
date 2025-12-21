package VISITORJINJA.ST;

import java.util.HashMap;
import java.util.Map;

/**
 * فئة تمثل رمز سمة HTML في جدول الرموز
 */
public class HtmlAttributeSymbol extends Symbol {

    /**
     * أنواع سمات HTML
     */
    public enum AttributeType {
        GLOBAL,       // id, class, style, title, lang, dir, etc.
        EVENT,        // onclick, onchange, onsubmit, etc.
        FORM,         // name, value, placeholder, required, etc.
        MEDIA,        // src, alt, width, height, controls, etc.
        LINK,         // href, target, rel, download, etc.
        META,         // charset, content, http-equiv, property
        BOOLEAN,      // hidden, disabled, checked, readonly, etc.
        CUSTOM,       // السمات المخصصة
        DATA,         // data-* attributes
        ARIA          // aria-* attributes
    }

    // خصائص السمة
    private AttributeType attributeType;
    private String value;
    private boolean hasValue;
    private HtmlElementSymbol parentElement;
    private Map<String, Object> metadata; // معلومات إضافية عن السمة

    /**
     * مُنشئ السمة
     */
    public HtmlAttributeSymbol(String name, AttributeType type,
                               String value, boolean hasValue,
                               int line, int column) {
        super(name, "HTML_ATTRIBUTE", line, column);
        this.attributeType = type;
        this.value = value;
        this.hasValue = hasValue;
        this.parentElement = null;
        this.metadata = new HashMap<>();
        initializeMetadata();
    }

    /**
     * مُنشئ للسمات بدون قيمة (boolean attributes)
     */
    public HtmlAttributeSymbol(String name, AttributeType type,
                               int line, int column) {
        this(name, type, null, false, line, column);
    }

    /**
     * مُنشئ مع قيمة افتراضية
     */
    public HtmlAttributeSymbol(String name, String value,
                               int line, int column) {
        this(name, inferAttributeType(name), value, true, line, column);
    }

    // ================ Getter و Setter Methods ================

    /**
     * الحصول على نوع السمة
     */
    public AttributeType getAttributeType() {
        return attributeType;
    }

    /**
     * تعيين نوع السمة
     */
    public void setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

    /**
     * الحصول على قيمة السمة
     */
    public String getValue() {
        return value;
    }

    /**
     * تعيين قيمة السمة
     */
    public void setValue(String value) {
        this.value = value;
        this.hasValue = true;
        initializeMetadata();
    }

    /**
     * التحقق مما إذا كانت السمة لها قيمة
     */
    public boolean hasValue() {
        return hasValue;
    }

    /**
     * تعيين ما إذا كانت السمة لها قيمة
     */
    public void setHasValue(boolean hasValue) {
        this.hasValue = hasValue;
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
        metadata.put("isBooleanAttribute", !hasValue);
        metadata.put("isCustomAttribute", attributeType == AttributeType.CUSTOM);
        metadata.put("isDataAttribute", name != null && name.startsWith("data-"));
        metadata.put("isAriaAttribute", name != null && name.startsWith("aria-"));
        metadata.put("isEventAttribute", name != null && name.startsWith("on"));

        if (hasValue && value != null) {
            metadata.put("valueLength", value.length());
            metadata.put("hasQuotes",
                    (value.startsWith("\"") && value.endsWith("\"")) ||
                            (value.startsWith("'") && value.endsWith("'")));
        }
    }

    /**
     * الحصول على قيمة السمة بدون علامات الاقتباس
     */
    public String getValueWithoutQuotes() {
        if (value == null || value.isEmpty()) {
            return "";
        }

        // إزالة علامات الاقتباس
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }
        if (value.startsWith("'") && value.endsWith("'")) {
            return value.substring(1, value.length() - 1);
        }

        return value;
    }

    /**
     * الحصول على قيمة السمة كمصفوفة (لسمات مثل class)
     */
    public String[] getValueAsArray() {
        String cleanValue = getValueWithoutQuotes();
        if (cleanValue.isEmpty()) {
            return new String[0];
        }

        // تقسيم القيمة حسب المسافات
        return cleanValue.split("\\s+");
    }

    // ================ التحليلات ================

    /**
     * التحقق مما إذا كانت سمة boolean
     */
    public boolean isBooleanAttribute() {
        return Boolean.TRUE.equals(metadata.get("isBooleanAttribute"));
    }

    /**
     * التحقق مما إذا كانت سمة مخصصة
     */
    public boolean isCustomAttribute() {
        return Boolean.TRUE.equals(metadata.get("isCustomAttribute"));
    }

    /**
     * التحقق مما إذا كانت سمة data
     */
    public boolean isDataAttribute() {
        return Boolean.TRUE.equals(metadata.get("isDataAttribute"));
    }

    /**
     * التحقق مما إذا كانت سمة aria
     */
    public boolean isAriaAttribute() {
        return Boolean.TRUE.equals(metadata.get("isAriaAttribute"));
    }

    /**
     * التحقق مما إذا كانت سمة event
     */
    public boolean isEventAttribute() {
        return Boolean.TRUE.equals(metadata.get("isEventAttribute"));
    }

    /**
     * التحقق مما إذا كانت القيمة محاطة بعلامات اقتباس
     */
    public boolean hasQuotes() {
        return Boolean.TRUE.equals(metadata.get("hasQuotes"));
    }

    /**
     * الحصول على طول القيمة
     */
    public int getValueLength() {
        Integer length = (Integer) metadata.get("valueLength");
        return length != null ? length : 0;
    }

    /**
     * التحقق مما إذا كانت القيمة تحتوي على تعبير Jinja
     */
    public boolean containsJinjaExpression() {
        if (value == null) return false;
        return value.contains("{{") || value.contains("{%");
    }

    /**
     * التحقق مما إذا كانت القيمة تحتوي على CSS
     */
    public boolean containsCss() {
        if (!name.equals("style")) return false;
        if (value == null) return false;
        return value.contains(":") || value.contains(";");
    }

    // ================ المعلومات التفصيلية ================

    /**
     * الحصول على معلومات تفصيلية عن السمة
     */
    @Override
    public String getDetailedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);

        if (hasValue && value != null) {
            String valueDisplay = value;
            if (value.length() > 50) {
                valueDisplay = value.substring(0, 47) + "...";
            }
            sb.append("=\"").append(valueDisplay).append("\"");
        } else {
            sb.append(" (boolean)");
        }

        sb.append(" [").append(attributeType).append("]");
        sb.append(" line:").append(line).append(", col:").append(column);

        // معلومات إضافية
        if (isDataAttribute()) {
            sb.append(" [data-*]");
        } else if (isAriaAttribute()) {
            sb.append(" [aria-*]");
        } else if (isEventAttribute()) {
            sb.append(" [event]");
        }

        if (parentElement != null) {
            sb.append(" on <").append(parentElement.name).append(">");
        }

        return sb.toString();
    }

    /**
     * تمثيل نصي مختصر للسمة
     */
    @Override
    public String toString() {
        if (hasValue && value != null) {
            String shortValue = value.length() > 20 ?
                    value.substring(0, 17) + "..." : value;
            return String.format("%s=\"%s\"", name, shortValue);
        } else {
            return name;
        }
    }

    /**
     * تصدير السمة ككائن JSON
     */
    public Map<String, Object> toJson() {
        Map<String, Object> json = new HashMap<>();
        json.put("name", name);
        json.put("type", attributeType.name());
        json.put("line", line);
        json.put("column", column);
        json.put("hasValue", hasValue);

        if (hasValue && value != null) {
            json.put("value", value);
            json.put("valueWithoutQuotes", getValueWithoutQuotes());
            json.put("valueLength", getValueLength());
        }

        json.put("isBoolean", isBooleanAttribute());
        json.put("isCustom", isCustomAttribute());
        json.put("isDataAttribute", isDataAttribute());
        json.put("isAriaAttribute", isAriaAttribute());
        json.put("isEventAttribute", isEventAttribute());
        json.put("containsJinja", containsJinjaExpression());
        json.put("containsCss", containsCss());

        if (parentElement != null) {
            json.put("parentElement", parentElement.name);
        }

        return json;
    }

    /**
     * تصدير السمة بصيغة HTML
     */
    public String toHtmlString() {
        if (hasValue && value != null) {
            return String.format("%s=\"%s\"", name, getValueWithoutQuotes());
        } else {
            return name;
        }
    }

    // ================ طرق ثابتة مساعدة ================

    /**
     * الاستدلال على نوع السمة من اسمها
     */
    public static AttributeType inferAttributeType(String attributeName) {
        if (attributeName == null) return AttributeType.CUSTOM;

        String attrLower = attributeName.toLowerCase();

        // السمات العامة
        if (attrLower.equals("id") || attrLower.equals("class") ||
                attrLower.equals("style") || attrLower.equals("title") ||
                attrLower.equals("lang") || attrLower.equals("dir") ||
                attrLower.equals("tabindex") || attrLower.equals("accesskey") ||
                attrLower.equals("translate") || attrLower.equals("contenteditable") ||
                attrLower.equals("draggable")) {
            return AttributeType.GLOBAL;
        }

        // سمات الأحداث
        if (attrLower.startsWith("on")) {
            return AttributeType.EVENT;
        }

        // سمات النماذج
        if (attrLower.equals("name") || attrLower.equals("value") ||
                attrLower.equals("placeholder") || attrLower.equals("required") ||
                attrLower.equals("readonly") || attrLower.equals("disabled") ||
                attrLower.equals("checked") || attrLower.equals("max") ||
                attrLower.equals("min") || attrLower.equals("step") ||
                attrLower.equals("pattern") || attrLower.equals("maxlength") ||
                attrLower.equals("minlength") || attrLower.equals("accept") ||
                attrLower.equals("autocomplete") || attrLower.equals("size") ||
                attrLower.equals("multiple") || attrLower.equals("selected") ||
                attrLower.equals("rows") || attrLower.equals("cols") ||
                attrLower.equals("autofocus") || attrLower.equals("form")) {
            return AttributeType.FORM;
        }

        // سمات الوسائط
        if (attrLower.equals("src") || attrLower.equals("alt") ||
                attrLower.equals("width") || attrLower.equals("height") ||
                attrLower.equals("controls") || attrLower.equals("autoplay") ||
                attrLower.equals("loop") || attrLower.equals("muted") ||
                attrLower.equals("poster") || attrLower.equals("preload") ||
                attrLower.equals("playsinline") || attrLower.equals("crossorigin")) {
            return AttributeType.MEDIA;
        }

        // سمات الروابط
        if (attrLower.equals("href") || attrLower.equals("target") ||
                attrLower.equals("rel") || attrLower.equals("download") ||
                attrLower.equals("hreflang") || attrLower.equals("type") ||
                attrLower.equals("referrerpolicy")) {
            return AttributeType.LINK;
        }

        // سمات الميتا
        if (attrLower.equals("charset") || attrLower.equals("content") ||
                attrLower.equals("http-equiv") || attrLower.equals("property") ||
                attrLower.equals("name") || attrLower.equals("scheme")) {
            return AttributeType.META;
        }

        // السمات المنطقية
        if (attrLower.equals("hidden") || attrLower.equals("disabled") ||
                attrLower.equals("checked") || attrLower.equals("readonly") ||
                attrLower.equals("required") || attrLower.equals("multiple") ||
                attrLower.equals("selected") || attrLower.equals("autofocus") ||
                attrLower.equals("novalidate") || attrLower.equals("formnovalidate") ||
                attrLower.equals("open") || attrLower.equals("reversed") ||
                attrLower.equals("allowfullscreen") || attrLower.equals("async") ||
                attrLower.equals("defer") || attrLower.equals("ismap") ||
                attrLower.equals("nomodule") || attrLower.equals("playsinline")) {
            return AttributeType.BOOLEAN;
        }

        // سمات البيانات والوصول
        if (attrLower.startsWith("data-")) {
            return AttributeType.DATA;
        }

        if (attrLower.startsWith("aria-")) {
            return AttributeType.ARIA;
        }

        // السمات المخصصة
        return AttributeType.CUSTOM;
    }

    /**
     * التحقق مما إذا كانت السمة من النوع المنطقي (boolean)
     */
    public static boolean isBooleanAttribute(String attributeName) {
        AttributeType type = inferAttributeType(attributeName);
        return type == AttributeType.BOOLEAN;
    }

    /**
     * التحقق مما إذا كانت السمة تتطلب قيمة
     */
    public static boolean requiresValue(String attributeName) {
        // معظم السمات المنطقية لا تحتاج إلى قيمة
        return !isBooleanAttribute(attributeName);
    }

    /**
     * الحصول على القيمة الافتراضية للسمة
     */
    public static String getDefaultValue(String attributeName) {
        switch (attributeName.toLowerCase()) {
            case "type":
                return "text";
            case "method":
                return "get";
            case "enctype":
                return "application/x-www-form-urlencoded";
            case "target":
                return "_self";
            case "rel":
                return "noopener noreferrer";
            default:
                return "";
        }
    }
}