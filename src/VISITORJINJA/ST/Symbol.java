package VISITORJINJA.ST;

// Symbol.java


import java.util.HashMap;
import java.util.Map;

public class Symbol {
    private String name;
    private SymbolType type;
    private int lineNumber;
    private String scope;
    private Map<String, String> properties;
    private String parentElement;
    private String value; // قيمة إضافية للرموز
    private String sourceFile; // اسم الملف المصدر (اختياري)

    public Symbol(String name, SymbolType type, int lineNumber, String scope) {
        this.name = name;
        this.type = type;
        this.lineNumber = lineNumber;
        this.scope = scope;
        this.properties = new HashMap<>();
        this.parentElement = null;
        this.value = null;
        this.sourceFile = null;
    }

    public Symbol(String name, SymbolType type, int lineNumber, String scope, String value) {
        this(name, type, lineNumber, scope);
        this.value = value;
    }

    // Methods to manage properties
    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    public void addProperties(Map<String, String> properties) {
        if (properties != null) {
            this.properties.putAll(properties);
        }
    }

    public String getProperty(String key) {
        return properties.get(key);
    }

    public boolean hasProperty(String key) {
        return properties.containsKey(key);
    }

    public void removeProperty(String key) {
        properties.remove(key);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SymbolType getType() {
        return type;
    }

    public void setType(SymbolType type) {
        this.type = type;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties != null ? properties : new HashMap<>();
    }

    public String getParentElement() {
        return parentElement;
    }

    public void setParentElement(String parentElement) {
        this.parentElement = parentElement;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    // Utility methods
    public boolean isElement() {
        return type == SymbolType.HTML_ELEMENT ||
                type == SymbolType.HEAD_ELEMENT ||
                type == SymbolType.BODY_ELEMENT ||
                type == SymbolType.NORMAL_ELEMENT ||
                type == SymbolType.SELF_CLOSING_ELEMENT;
    }

    public boolean isJinjaConstruct() {
        return type == SymbolType.JINJA_EXPRESSION ||
                type == SymbolType.JINJA_STATEMENT ||
                type == SymbolType.JINJA_COMMENT ||
                type == SymbolType.JINJA_IF_BLOCK ||
                type == SymbolType.JINJA_FOR_BLOCK ||
                type == SymbolType.JINJA_BLOCK;
    }

    public boolean isCssRule() {
        return type == SymbolType.CSS_RULE;
    }

    public boolean isAttribute() {
        return type == SymbolType.ATTRIBUTE ||
                type == SymbolType.LANG_ATTRIBUTE ||
                type == SymbolType.STYLE_ATTRIBUTE;
    }

    public boolean isVariable() {
        return type == SymbolType.VARIABLE ||
                type == SymbolType.TEMPLATE_VARIABLE;
    }

    // Method to get a formatted string for debugging
    public String toFormattedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("┌─ Symbol ──────────────────────\n");
        sb.append(String.format("│ Name: %s\n", name));
        sb.append(String.format("│ Type: %s\n", type));
        sb.append(String.format("│ Line: %d\n", lineNumber));
        sb.append(String.format("│ Scope: %s\n", scope));

        if (parentElement != null) {
            sb.append(String.format("│ Parent: %s\n", parentElement));
        }

        if (value != null) {
            sb.append(String.format("│ Value: %s\n", value));
        }

        if (sourceFile != null) {
            sb.append(String.format("│ Source: %s\n", sourceFile));
        }

        if (!properties.isEmpty()) {
            sb.append("│ Properties:\n");
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                sb.append(String.format("│   %s = %s\n", entry.getKey(), entry.getValue()));
            }
        }
        sb.append("└───────────────────────────────");

        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("Symbol{name='%s', type=%s, line=%d, scope='%s', parent='%s', value='%s'}",
                name, type, lineNumber, scope,
                parentElement != null ? parentElement : "null",
                value != null ? value : "null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol = (Symbol) o;

        if (lineNumber != symbol.lineNumber) return false;
        if (!name.equals(symbol.name)) return false;
        if (type != symbol.type) return false;
        return scope.equals(symbol.scope);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + lineNumber;
        result = 31 * result + scope.hashCode();
        return result;
    }

    // Builder pattern for easier creation (اختياري)
    public static class Builder {
        private String name;
        private SymbolType type;
        private int lineNumber;
        private String scope;
        private Map<String, String> properties;
        private String parentElement;
        private String value;
        private String sourceFile;

        public Builder(String name, SymbolType type, int lineNumber, String scope) {
            this.name = name;
            this.type = type;
            this.lineNumber = lineNumber;
            this.scope = scope;
            this.properties = new HashMap<>();
        }

        public Builder withProperty(String key, String value) {
            this.properties.put(key, value);
            return this;
        }

        public Builder withProperties(Map<String, String> properties) {
            if (properties != null) {
                this.properties.putAll(properties);
            }
            return this;
        }

        public Builder withParentElement(String parentElement) {
            this.parentElement = parentElement;
            return this;
        }

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Builder withSourceFile(String sourceFile) {
            this.sourceFile = sourceFile;
            return this;
        }

        public Symbol build() {
            Symbol symbol = new Symbol(name, type, lineNumber, scope);
            symbol.setProperties(properties);
            symbol.setParentElement(parentElement);
            symbol.setValue(value);
            symbol.setSourceFile(sourceFile);
            return symbol;
        }
    }

    // Static factory methods for common symbol types
    public static Symbol createHtmlElement(String name, int lineNumber, String scope) {
        return new Symbol(name, SymbolType.HTML_ELEMENT, lineNumber, scope);
    }

    public static Symbol createAttribute(String name, int lineNumber, String scope, String value) {
        Symbol symbol = new Symbol(name, SymbolType.ATTRIBUTE, lineNumber, scope);
        symbol.setValue(value);
        return symbol;
    }

    public static Symbol createJinjaVariable(String name, int lineNumber, String scope, String value) {
        Symbol symbol = new Symbol(name, SymbolType.VARIABLE, lineNumber, scope);
        symbol.setValue(value);
        return symbol;
    }

    public static Symbol createCssRule(String selector, int lineNumber, String scope) {
        return new Symbol(selector, SymbolType.CSS_RULE, lineNumber, scope);
    }

    public static Symbol createText(String content, int lineNumber, String scope) {
        return new Symbol("TEXT_CONTENT", SymbolType.TEXT_HTML, lineNumber, scope, content);
    }
}