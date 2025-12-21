package VISITORJINJA.ST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * فئة تمثل رمز عنصر HTML في جدول الرموز
 */
public class HtmlElementSymbol extends Symbol {

    /**
     * تصنيفات العناصر HTML
     */
    public enum ElementCategory {
        STRUCTURAL,   // html, head, body, div, section, etc.
        HEADING,      // h1-h6
        TEXT,         // p, span, strong, em, etc.
        LIST,         // ul, ol, li, dl, dt, dd
        TABLE,        // table, tr, td, th, etc.
        FORM,         // form, input, button, select, etc.
        MEDIA,        // img, audio, video, canvas, etc.
        SEMANTIC,     // header, footer, nav, article, etc.
        SELF_CLOSING  // br, hr, meta, link, etc.
    }

    // خصائص العنصر
    private ElementCategory category;
    private Map<String, HtmlAttributeSymbol> attributes;
    private List<HtmlElementSymbol> children;
    private HtmlElementSymbol parent;
    private boolean hasClosingTag;
    private String innerText; // النص داخل العنصر
    private boolean isInJinjaContext; // هل العنصر داخل سياق Jinja

    /**
     * مُنشئ العنصر
     */
    public HtmlElementSymbol(String tagName, ElementCategory category,
                             int line, int column, boolean hasClosingTag) {
        super(tagName, "HTML_ELEMENT", line, column);
        this.category = category;
        this.hasClosingTag = hasClosingTag;
        this.attributes = new HashMap<>();
        this.children = new ArrayList<>();
        this.parent = null;
        this.innerText = "";
        this.isInJinjaContext = false;
    }

    // ================ طرق الوصول والتعديل ================

    /**
     * إضافة سمة للعنصر
     */
    public void addAttribute(HtmlAttributeSymbol attribute) {
        if (attribute != null) {
            attributes.put(attribute.name, attribute);
            attribute.setParentElement(this);
        }
    }

    /**
     * الحصول على سمة معينة
     */
    public HtmlAttributeSymbol getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }

    /**
     * التحقق من وجود سمة
     */
    public boolean hasAttribute(String attributeName) {
        return attributes.containsKey(attributeName);
    }

    /**
     * إضافة عنصر فرعي
     */
    public void addChild(HtmlElementSymbol child) {
        if (child != null && child != this) {
            child.setParent(this);
            children.add(child);
        }
    }

    /**
     * تعيين العنصر الأب
     */
    public void setParent(HtmlElementSymbol parent) {
        this.parent = parent;
    }

    /**
     * الحصول على العنصر الأب
     */
    public HtmlElementSymbol getParent() {
        return parent;
    }

    /**
     * تعيين النص الداخلي
     */
    public void setInnerText(String text) {
        this.innerText = text != null ? text : "";
    }

    /**
     * الحصول على النص الداخلي
     */
    public String getInnerText() {
        return innerText;
    }

    /**
     * تعيين سياق Jinja
     */
    public void setInJinjaContext(boolean inJinja) {
        this.isInJinjaContext = inJinja;
    }

    /**
     * التحقق مما إذا كان العنصر داخل سياق Jinja
     */
    public boolean isInJinjaContext() {
        return isInJinjaContext;
    }

    // ================ العلاقات العائلية ================

    /**
     * الحصول على جميع الأسلاف
     */
    public List<HtmlElementSymbol> getAncestors() {
        List<HtmlElementSymbol> ancestors = new ArrayList<>();
        HtmlElementSymbol current = this.parent;
        while (current != null) {
            ancestors.add(current);
            current = current.getParent();
        }
        return ancestors;
    }

    /**
     * التحقق مما إذا كان العنصر سليلًا لعنصر معين
     */
    public boolean isDescendantOf(String tagName) {
        for (HtmlElementSymbol ancestor : getAncestors()) {
            if (ancestor.name.equalsIgnoreCase(tagName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * التحقق مما إذا كان العنصر سليلًا لعنصر من تصنيف معين
     */
    public boolean isDescendantOfCategory(ElementCategory category) {
        for (HtmlElementSymbol ancestor : getAncestors()) {
            if (ancestor.category == category) {
                return true;
            }
        }
        return false;
    }

    /**
     * الحصول على جميع العناصر الفرعية
     */
    public List<HtmlElementSymbol> getChildren() {
        return new ArrayList<>(children);
    }

    /**
     * الحصول على عدد العناصر الفرعية
     */
    public int getChildrenCount() {
        return children.size();
    }

    /**
     * الحصول على جميع السمات
     */
    public Map<String, HtmlAttributeSymbol> getAttributes() {
        return new HashMap<>(attributes);
    }

    /**
     * الحصول على قيم السمات كخريطة
     */
    public Map<String, String> getAttributeValues() {
        Map<String, String> values = new HashMap<>();
        for (HtmlAttributeSymbol attr : attributes.values()) {
            values.put(attr.name, attr.getValueWithoutQuotes());
        }
        return values;
    }

    // ================ التحليلات ================

    /**
     * التحقق مما إذا كان العنصر يحتوي على سمة style
     */
    public boolean hasStyleAttribute() {
        return hasAttribute("style");
    }

    /**
     * الحصول على سمة style
     */
    public HtmlAttributeSymbol getStyleAttribute() {
        return getAttribute("style");
    }

    /**
     * التحقق مما إذا كان العنصر يحتوي على id
     */
    public boolean hasId() {
        return hasAttribute("id");
    }

    /**
     * الحصول على قيمة id
     */
    public String getId() {
        HtmlAttributeSymbol idAttr = getAttribute("id");
        return idAttr != null ? idAttr.getValueWithoutQuotes() : null;
    }

    /**
     * التحقق مما إذا كان العنصر يحتوي على class
     */
    public boolean hasClass() {
        return hasAttribute("class");
    }

    /**
     * الحصول على قيم class كمصفوفة
     */
    public List<String> getClasses() {
        HtmlAttributeSymbol classAttr = getAttribute("class");
        if (classAttr != null) {
            String classValue = classAttr.getValueWithoutQuotes();
            if (classValue != null && !classValue.isEmpty()) {
                String[] classes = classValue.split("\\s+");
                List<String> result = new ArrayList<>();
                for (String cls : classes) {
                    if (!cls.trim().isEmpty()) {
                        result.add(cls.trim());
                    }
                }
                return result;
            }
        }
        return new ArrayList<>();
    }

    /**
     * التحقق مما إذا كان العنصر يحتوي على class معين
     */
    public boolean hasClass(String className) {
        return getClasses().contains(className);
    }

    // ================ المعلومات التفصيلية ================

    /**
     * الحصول على معلومات تفصيلية عن العنصر
     */
    @Override
    public String getDetailedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("HTML Element: <").append(name).append(">\n");
        sb.append("  Category: ").append(category).append("\n");
        sb.append("  Line: ").append(line).append(", Column: ").append(column).append("\n");
        sb.append("  Has closing tag: ").append(hasClosingTag).append("\n");
        sb.append("  In Jinja context: ").append(isInJinjaContext).append("\n");
        sb.append("  Attributes: ").append(attributes.size()).append("\n");
        sb.append("  Children: ").append(children.size()).append("\n");

        if (!innerText.isEmpty()) {
            String textPreview = innerText.length() > 50 ?
                    innerText.substring(0, 47) + "..." : innerText;
            sb.append("  Inner text: \"").append(textPreview).append("\"\n");
        }

        if (!attributes.isEmpty()) {
            sb.append("  Attribute list:\n");
            for (HtmlAttributeSymbol attr : attributes.values()) {
                sb.append("    • ").append(attr.getDetailedInfo()).append("\n");
            }
        }

        if (!children.isEmpty()) {
            sb.append("  Children tags: ");
            for (int i = 0; i < Math.min(children.size(), 5); i++) {
                sb.append("<").append(children.get(i).name).append(">");
                if (i < Math.min(children.size(), 5) - 1) {
                    sb.append(", ");
                }
            }
            if (children.size() > 5) {
                sb.append(" ... and ").append(children.size() - 5).append(" more");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * تمثيل نصي مختصر للعنصر
     */
    @Override
    public String toString() {
        String closing = hasClosingTag ? ">" : "/>";
        String categoryStr = category.name().substring(0, Math.min(3, category.name().length()));
        return String.format("<%s%s [%s] line:%d", name, closing, categoryStr, line);
    }

    /**
     * الحصول على تتبع الهرمية
     */
    public String getHierarchyTrace() {
        List<HtmlElementSymbol> ancestors = getAncestors();
        StringBuilder trace = new StringBuilder();

        for (int i = ancestors.size() - 1; i >= 0; i--) {
            trace.append("<").append(ancestors.get(i).name).append(">");
            if (i > 0) trace.append(" > ");
        }

        if (!ancestors.isEmpty()) trace.append(" > ");
        trace.append("<").append(name).append(">");

        return trace.toString();
    }

    /**
     * تصدير العنصر ككائن JSON
     */
    public Map<String, Object> toJson() {
        Map<String, Object> json = new HashMap<>();
        json.put("tag", name);
        json.put("category", category.name());
        json.put("line", line);
        json.put("column", column);
        json.put("hasClosingTag", hasClosingTag);
        json.put("inJinjaContext", isInJinjaContext);

        // السمات
        Map<String, String> attrsJson = new HashMap<>();
        for (HtmlAttributeSymbol attr : attributes.values()) {
            attrsJson.put(attr.name, attr.getValueWithoutQuotes());
        }
        json.put("attributes", attrsJson);

        // العناصر الفرعية (أسماء فقط لتجنب التكرار)
        List<String> childrenTags = new ArrayList<>();
        for (HtmlElementSymbol child : children) {
            childrenTags.add(child.name);
        }
        json.put("childrenTags", childrenTags);
        json.put("childrenCount", children.size());

        return json;
    }

    // ================ طرق ثابتة مساعدة ================

    /**
     * تحويل اسم الوسم إلى فئته
     */
    public static ElementCategory getCategoryFromTag(String tagName) {
        if (tagName == null) return ElementCategory.STRUCTURAL;

        switch (tagName.toLowerCase()) {
            case "h1": case "h2": case "h3": case "h4": case "h5": case "h6":
                return ElementCategory.HEADING;
            case "p": case "span": case "strong": case "b": case "i": case "em":
            case "u": case "small": case "mark": case "sub": case "sup":
            case "blockquote": case "code": case "pre":
                return ElementCategory.TEXT;
            case "ul": case "ol": case "li": case "dl": case "dt": case "dd":
                return ElementCategory.LIST;
            case "table": case "tr": case "td": case "th": case "thead":
            case "tbody": case "tfoot": case "caption": case "colgroup":
                return ElementCategory.TABLE;
            case "form": case "input": case "button": case "select":
            case "option": case "textarea": case "label": case "datalist":
            case "fieldset": case "legend": case "optgroup":
                return ElementCategory.FORM;
            case "img": case "audio": case "video": case "canvas":
            case "svg": case "picture":
                return ElementCategory.MEDIA;
            case "header": case "footer": case "nav": case "article":
            case "aside": case "section": case "main": case "figure":
            case "figcaption": case "details": case "summary": case "dialog":
                return ElementCategory.SEMANTIC;
            case "br": case "hr": case "meta": case "link":
            case "wbr": case "area": case "base": case "col": case "embed":
            case "param": case "source": case "track":
                return ElementCategory.SELF_CLOSING;
            case "html": case "head": case "body": case "div":
                return ElementCategory.STRUCTURAL;
            default:
                return ElementCategory.STRUCTURAL;
        }
    }

    /**
     * التحقق مما إذا كان الوسم مغلقًا ذاتيًا
     */
    public static boolean isSelfClosingTag(String tagName) {
        if (tagName == null) return false;

        switch (tagName.toLowerCase()) {
            case "br": case "hr": case "img": case "meta": case "link":
            case "input": case "area": case "base": case "col": case "embed":
            case "param": case "source": case "track": case "wbr":
                return true;
            default:
                return false;
        }
    }

    /**
     * التحقق مما إذا كان الوسم من نوع الوسائط (MEDIA)
     */
    public static boolean isMediaTag(String tagName) {
        if (tagName == null) return false;

        switch (tagName.toLowerCase()) {
            case "img": case "audio": case "video": case "canvas":
            case "svg": case "picture": case "source": case "track":
                return true;
            default:
                return false;
        }
    }

    /**
     * التحقق مما إذا كان الوسم يحتاج إلى عنصر مغلق
     */
    public static boolean requiresClosingTag(String tagName) {
        return !isSelfClosingTag(tagName);
    }

    /**
     * الحصول على جميع الوسوم المسموح بها
     */
    public static List<String> getAllValidTags() {
        return List.of(
                "html", "head", "body", "div",
                "h1", "h2", "h3", "h4", "h5", "h6",
                "p", "span", "strong", "b", "i", "em", "u",
                "small", "mark", "sub", "sup", "blockquote",
                "code", "pre",
                "ul", "ol", "li", "dl", "dt", "dd",
                "table", "tr", "td", "th", "thead", "tbody",
                "tfoot", "caption", "colgroup",
                "form", "input", "button", "select", "option",
                "textarea", "label", "datalist", "fieldset",
                "legend", "optgroup",
                "img", "audio", "video", "canvas", "svg",
                "picture", "source", "track",
                "header", "footer", "nav", "article", "aside",
                "section", "main", "figure", "figcaption",
                "details", "summary", "dialog",
                "br", "hr", "meta", "link", "wbr", "area",
                "base", "col", "embed", "param"
        );
    }
    public ElementCategory getCategory() {
        return this.category;
    }
}