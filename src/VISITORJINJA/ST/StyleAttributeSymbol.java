package VISITORJINJA.ST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * فئة تمثل رمز سمة style في جدول الرموز
 * ترث من HtmlAttributeSymbol وتضيف وظائف خاصة بـ CSS
 */
public class StyleAttributeSymbol extends HtmlAttributeSymbol {

    private List<CssRuleSymbol> cssRules;
    private Map<String, CssRuleSymbol> rulesByProperty; // فهرس سريع
    private boolean hasErrors; // هل تحتوي على أخطاء CSS
    private List<String> cssErrors; // قائمة الأخطاء

    /**
     * مُنشئ سمة style
     */
    public StyleAttributeSymbol(int line, int column) {
        super("style", AttributeType.GLOBAL, null, true, line, column);
        this.cssRules = new ArrayList<>();
        this.rulesByProperty = new HashMap<>();
        this.hasErrors = false;
        this.cssErrors = new ArrayList<>();

        // تحديث البيانات الوصفية
        setMetadata("isStyleAttribute", true);
        setMetadata("containsCss", true);
    }

    /**
     * مُنشئ مع قيمة أولية
     */
    public StyleAttributeSymbol(String initialValue, int line, int column) {
        super("style", AttributeType.GLOBAL, initialValue, true, line, column);
        this.cssRules = new ArrayList<>();
        this.rulesByProperty = new HashMap<>();
        this.hasErrors = false;
        this.cssErrors = new ArrayList<>();

        setMetadata("isStyleAttribute", true);
        setMetadata("containsCss", true);

        // تحليل القيمة الأولية إذا كانت موجودة
        if (initialValue != null && !initialValue.isEmpty()) {
            parseStyleValue(initialValue);
        }
    }

    // ================ Getter و Setter Methods ================

    /**
     * الحصول على جميع قواعد CSS
     */
    public List<CssRuleSymbol> getCssRules() {
        return new ArrayList<>(cssRules);
    }

    /**
     * تعيين قواعد CSS
     */
    public void setCssRules(List<CssRuleSymbol> cssRules) {
        if (cssRules != null) {
            this.cssRules = new ArrayList<>(cssRules);
            this.rulesByProperty.clear();
            for (CssRuleSymbol rule : cssRules) {
                if (rule != null && rule.isValid()) {
                    rulesByProperty.put(rule.getProperty().toLowerCase(), rule);
                    rule.setTargetElement(getParentElement());
                    rule.setInline(true);
                }
            }
            updateStyleValue();
        }
    }

    /**
     * الحصول على قاعدة CSS حسب الخاصية
     */
    public CssRuleSymbol getCssRule(String property) {
        return rulesByProperty.get(property.toLowerCase());
    }

    /**
     * التحقق مما إذا كانت هناك أخطاء
     */
    public boolean hasErrors() {
        return hasErrors;
    }

    /**
     * تعيين حالة الأخطاء
     */
    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
        setMetadata("hasErrors", hasErrors);
    }

    /**
     * الحصول على قائمة الأخطاء
     */
    public List<String> getCssErrors() {
        return new ArrayList<>(cssErrors);
    }

    /**
     * تعيين قائمة الأخطاء
     */
    public void setCssErrors(List<String> cssErrors) {
        if (cssErrors != null) {
            this.cssErrors = new ArrayList<>(cssErrors);
            setMetadata("errorCount", cssErrors.size());
        }
    }

    /**
     * الحصول على فهرس القواعد
     */
    public Map<String, CssRuleSymbol> getRulesByProperty() {
        return new HashMap<>(rulesByProperty);
    }

    // ================ إدارة قواعد CSS ================

    /**
     * إضافة قاعدة CSS
     */
    public void addCssRule(CssRuleSymbol rule) {
        if (rule != null && rule.isValid()) {
            cssRules.add(rule);
            rulesByProperty.put(rule.getProperty().toLowerCase(), rule);

            // ربط القاعدة بسمة style
            rule.setTargetElement(getParentElement());
            rule.setInline(true);

            updateStyleValue();
        }
    }

    /**
     * إضافة قواعد CSS متعددة
     */
    public void addCssRules(List<CssRuleSymbol> rules) {
        if (rules != null) {
            for (CssRuleSymbol rule : rules) {
                addCssRule(rule);
            }
        }
    }

    /**
     * التحقق من وجود قاعدة CSS
     */
    public boolean hasCssRule(String property) {
        return rulesByProperty.containsKey(property.toLowerCase());
    }

    /**
     * إزالة قاعدة CSS
     */
    public boolean removeCssRule(String property) {
        CssRuleSymbol rule = rulesByProperty.remove(property.toLowerCase());
        if (rule != null) {
            cssRules.remove(rule);
            updateStyleValue();
            return true;
        }
        return false;
    }

    /**
     * تحديث قيمة سمة style بناءً على القواعد
     */
    private void updateStyleValue() {
        StringBuilder styleBuilder = new StringBuilder();

        for (int i = 0; i < cssRules.size(); i++) {
            CssRuleSymbol rule = cssRules.get(i);
            styleBuilder.append(rule.toCssString());

            if (i < cssRules.size() - 1) {
                styleBuilder.append(" ");
            }
        }

        setValue(styleBuilder.toString());
    }

    /**
     * تحليل قيمة سمة style إلى قواعد CSS
     */
    private void parseStyleValue(String styleValue) {
        if (styleValue == null || styleValue.trim().isEmpty()) {
            return;
        }

        // تنظيف القيمة من علامات الاقتباس إذا كانت موجودة
        String cleanValue = getValueWithoutQuotes();

        try {
            // تقسيم القواعد بواسطة ;
            String[] ruleStrings = cleanValue.split(";");

            for (String ruleStr : ruleStrings) {
                ruleStr = ruleStr.trim();
                if (ruleStr.isEmpty()) continue;

                // تقسيم الخاصية والقيمة
                String[] parts = ruleStr.split(":", 2);
                if (parts.length == 2) {
                    String property = parts[0].trim();
                    String valueStr = parts[1].trim();

                    // تقسيم القيم المتعددة
                    List<String> values = parseCssValues(valueStr);

                    // تحديد تصنيف الخاصية
                    CssRuleSymbol.RuleCategory category =
                            CssRuleSymbol.getCategoryFromProperty(property);

                    // إنشاء القاعدة
                    CssRuleSymbol rule = new CssRuleSymbol(
                            property, category, values, getLine(), getColumn()
                    );

                    addCssRule(rule);
                } else {
                    addCssError("Invalid CSS rule: " + ruleStr);
                }
            }
        } catch (Exception e) {
            addCssError("Error parsing CSS: " + e.getMessage());
        }
    }

    /**
     * تحليل قيم CSS (معالجة القيم المتعددة والدوال)
     */
    private List<String> parseCssValues(String valueStr) {
        List<String> values = new ArrayList<>();
        StringBuilder currentValue = new StringBuilder();
        int parenthesisDepth = 0;
        boolean inQuotes = false;
        char quoteChar = '\0';

        for (int i = 0; i < valueStr.length(); i++) {
            char c = valueStr.charAt(i);

            // تتبع علامات الاقتباس
            if ((c == '"' || c == '\'') && !inQuotes) {
                inQuotes = true;
                quoteChar = c;
            } else if (inQuotes && c == quoteChar) {
                inQuotes = false;
            }

            // تتبع الأقواس
            if (!inQuotes) {
                if (c == '(') parenthesisDepth++;
                else if (c == ')') parenthesisDepth--;
            }

            // تقسيم عند المسافات خارج الأقواس وعلامات الاقتباس
            if (c == ' ' && parenthesisDepth == 0 && !inQuotes) {
                if (currentValue.length() > 0) {
                    values.add(currentValue.toString());
                    currentValue = new StringBuilder();
                }
            } else {
                currentValue.append(c);
            }
        }

        // إضافة القيمة الأخيرة
        if (currentValue.length() > 0) {
            values.add(currentValue.toString());
        }

        return values;
    }

    // ================ إدارة الأخطاء ================

    /**
     * إضافة خطأ CSS
     */
    public void addCssError(String error) {
        cssErrors.add(error);
        hasErrors = true;
        setMetadata("hasErrors", true);
        setMetadata("errorCount", cssErrors.size());
    }

    /**
     * مسح جميع الأخطاء
     */
    public void clearErrors() {
        cssErrors.clear();
        hasErrors = false;
        setMetadata("hasErrors", false);
        setMetadata("errorCount", 0);
    }

    // ================ التحليلات ================

    /**
     * الحصول على عدد قواعد CSS
     */
    public int getRuleCount() {
        return cssRules.size();
    }

    /**
     * الحصول على قواعد CSS حسب التصنيف
     */
    public List<CssRuleSymbol> getRulesByCategory(CssRuleSymbol.RuleCategory category) {
        List<CssRuleSymbol> result = new ArrayList<>();
        for (CssRuleSymbol rule : cssRules) {
            if (rule.getCategory() == category) {
                result.add(rule);
            }
        }
        return result;
    }

    /**
     * التحقق مما إذا كانت تحتوي على قواعد من تصنيف معين
     */
    public boolean hasRulesOfCategory(CssRuleSymbol.RuleCategory category) {
        for (CssRuleSymbol rule : cssRules) {
            if (rule.getCategory() == category) {
                return true;
            }
        }
        return false;
    }

    /**
     * الحصول على جميع الخصائص المستخدمة
     */
    public List<String> getAllProperties() {
        return new ArrayList<>(rulesByProperty.keySet());
    }

    /**
     * التحقق مما إذا كانت سمة style فعالة (تحتوي على قواعد صالحة)
     */
    public boolean isEffective() {
        return !cssRules.isEmpty() && !hasErrors;
    }

    /**
     * التحقق مما إذا كانت القاعدة تحتوي على !important
     */
    public boolean hasImportantRules() {
        for (CssRuleSymbol rule : cssRules) {
            if (rule.isImportant()) {
                return true;
            }
        }
        return false;
    }

    /**
     * الحصول على قواعد !important فقط
     */
    public List<CssRuleSymbol> getImportantRules() {
        List<CssRuleSymbol> importantRules = new ArrayList<>();
        for (CssRuleSymbol rule : cssRules) {
            if (rule.isImportant()) {
                importantRules.add(rule);
            }
        }
        return importantRules;
    }

    /**
     * الحصول على جميع قواعد CSS كسلسلة نصية
     */
    public String getCssRulesAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cssRules.size(); i++) {
            sb.append(cssRules.get(i).toCssString());
            if (i < cssRules.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    // ================ المعلومات التفصيلية ================

    /**
     * الحصول على معلومات تفصيلية عن سمة style
     */
    @Override
    public String getDetailedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Style Attribute\n");
        sb.append("  Line: ").append(getLine()).append(", Column: ").append(getColumn()).append("\n");
        sb.append("  CSS Rules: ").append(cssRules.size()).append("\n");
        sb.append("  Has Errors: ").append(hasErrors).append("\n");
        sb.append("  Effective: ").append(isEffective()).append("\n");

        if (getParentElement() != null) {
            sb.append("  On element: <").append(getParentElement().getName()).append(">\n");
        }

        if (!cssRules.isEmpty()) {
            sb.append("  CSS Rules Details:\n");
            for (CssRuleSymbol rule : cssRules) {
                sb.append("    • ").append(rule.toString()).append("\n");
            }
        }

        if (hasErrors && !cssErrors.isEmpty()) {
            sb.append("  CSS Errors:\n");
            for (String error : cssErrors) {
                sb.append("    • ").append(error).append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * تمثيل نصي مختصر
     */
    @Override
    public String toString() {
        String rulesInfo = cssRules.size() + " rule" + (cssRules.size() != 1 ? "s" : "");
        String errorInfo = hasErrors ? " (has errors)" : "";
        return String.format("style=\"%s\" [%s%s]",
                getValueWithoutQuotes(), rulesInfo, errorInfo);
    }

    /**
     * تصدير ككائن JSON
     */
    @Override
    public Map<String, Object> toJson() {
        Map<String, Object> json = super.toJson();

        // معلومات CSS
        json.put("cssRuleCount", cssRules.size());
        json.put("hasErrors", hasErrors);
        json.put("isEffective", isEffective());
        json.put("hasImportantRules", hasImportantRules());

        // قائمة القواعد
        List<Map<String, Object>> rulesJson = new ArrayList<>();
        for (CssRuleSymbol rule : cssRules) {
            rulesJson.add(rule.toJson());
        }
        json.put("cssRules", rulesJson);

        // قائمة الأخطاء
        if (!cssErrors.isEmpty()) {
            json.put("cssErrors", new ArrayList<>(cssErrors));
        }

        return json;
    }

    /**
     * الحصول على سمة style بصيغة HTML
     */
    @Override
    public String toHtmlString() {
        if (cssRules.isEmpty() && getValueWithoutQuotes().isEmpty()) {
            return "";
        }

        StringBuilder html = new StringBuilder();
        html.append("style=\"");

        for (int i = 0; i < cssRules.size(); i++) {
            CssRuleSymbol rule = cssRules.get(i);
            html.append(rule.toCssString());

            if (i < cssRules.size() - 1) {
                html.append(" ");
            }
        }

        html.append("\"");
        return html.toString();
    }

    // ================ طرق ثابتة مساعدة ================

    /**
     * التحقق مما إذا كانت السمة تحتوي على CSS
     */
    public static boolean containsCss(String attributeValue) {
        if (attributeValue == null || attributeValue.isEmpty()) {
            return false;
        }

        return attributeValue.contains(":") && attributeValue.contains(";");
    }

    /**
     * تحليل سمة style إلى قواعد CSS
     */
    public static List<CssRuleSymbol> parseStyleToRules(String styleValue, int line, int column) {
        List<CssRuleSymbol> rules = new ArrayList<>();

        if (styleValue == null || styleValue.trim().isEmpty()) {
            return rules;
        }

        // إزالة علامات الاقتباس
        String cleanValue = styleValue;
        if (cleanValue.startsWith("\"") && cleanValue.endsWith("\"")) {
            cleanValue = cleanValue.substring(1, cleanValue.length() - 1);
        }

        // تقسيم القواعد
        String[] ruleStrings = cleanValue.split(";");

        for (String ruleStr : ruleStrings) {
            ruleStr = ruleStr.trim();
            if (ruleStr.isEmpty()) continue;

            String[] parts = ruleStr.split(":", 2);
            if (parts.length == 2) {
                String property = parts[0].trim();
                String value = parts[1].trim();

                // تقسيم القيم
                List<String> values = new ArrayList<>();
                String[] valueParts = value.split("\\s+");
                for (String v : valueParts) {
                    if (!v.trim().isEmpty()) {
                        values.add(v.trim());
                    }
                }

                // إنشاء القاعدة
                CssRuleSymbol.RuleCategory category =
                        CssRuleSymbol.getCategoryFromProperty(property);

                CssRuleSymbol rule = new CssRuleSymbol(
                        property, category, values, line, column
                );

                rules.add(rule);
            }
        }

        return rules;
    }

    /**
     * إنشاء StyleAttributeSymbol من سلسلة CSS
     */
    public static StyleAttributeSymbol fromCssString(String cssString, int line, int column) {
        StyleAttributeSymbol styleAttr = new StyleAttributeSymbol(line, column);

        if (cssString != null && !cssString.trim().isEmpty()) {
            List<CssRuleSymbol> rules = parseStyleToRules(cssString, line, column);
            styleAttr.addCssRules(rules);
        }

        return styleAttr;
    }

    /**
     * دمج سمتين style
     */
    public static StyleAttributeSymbol merge(StyleAttributeSymbol style1, StyleAttributeSymbol style2) {
        if (style1 == null) return style2;
        if (style2 == null) return style1;

        StyleAttributeSymbol merged = new StyleAttributeSymbol(
                Math.min(style1.getLine(), style2.getLine()),
                Math.min(style1.getColumn(), style2.getColumn())
        );

        // إضافة جميع القواعد من style1
        for (CssRuleSymbol rule : style1.getCssRules()) {
            merged.addCssRule(rule);
        }

        // إضافة القواعد من style2 (تتجاوز القواعد المكررة)
        for (CssRuleSymbol rule : style2.getCssRules()) {
            if (merged.hasCssRule(rule.getProperty())) {
                merged.removeCssRule(rule.getProperty());
            }
            merged.addCssRule(rule);
        }

        // دمج الأخطاء
        List<String> allErrors = new ArrayList<>();
        allErrors.addAll(style1.getCssErrors());
        allErrors.addAll(style2.getCssErrors());
        merged.setCssErrors(allErrors);

        return merged;
    }
}