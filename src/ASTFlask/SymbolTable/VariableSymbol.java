package ASTFlask.SymbolTable;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class VariableSymbol extends Symbol {
    public Object value;
    public Map<String, Object> attributes;
    private Map<String, VariableSymbol> nestedFields;
    private String parentPath;
    private Set<String> fieldNames;

    public VariableSymbol(String name, String type, int line, Scope scope) {
        super(name, type, line, scope);
        this.value = null;
        this.attributes = new HashMap<>();
        this.nestedFields = new HashMap<>();
        this.parentPath = null;
        this.fieldNames = new HashSet<>();

        initializeAttributesByType(type);
    }

    public VariableSymbol(String name, String type, int line, Scope scope, Object value) {
        super(name, type, line, scope);
        this.value = value;
        this.attributes = new HashMap<>();
        this.nestedFields = new HashMap<>();
        this.parentPath = null;
        this.fieldNames = new HashSet<>();

        initializeAttributesByType(type);
        processValue(value);
    }

    private void initializeAttributesByType(String type) {
        String normalizedType = type.toLowerCase();

        if (normalizedType.contains("list") || normalizedType.contains("array")) {
            this.attributes.put("is_list", true);
            this.attributes.put("element_type", "any");
        } else if (normalizedType.contains("dict") || normalizedType.contains("map")) {
            this.attributes.put("is_dict", true);
        } else if (normalizedType.contains("tuple")) {
            this.attributes.put("is_tuple", true);
        } else if (normalizedType.contains("set")) {
            this.attributes.put("is_set", true);
        }
    }

    private void processValue(Object value) {
        if (value == null) {
            return;
        }

        if (value instanceof List) {
            List<?> list = (List<?>) value;
            this.attributes.put("length", list.size());
            this.attributes.put("is_list", true);

            detectElementTypes(list);

            processListElements(list);
        }
        else if (value instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) value;
            this.attributes.put("size", map.size());
            this.attributes.put("is_dict", true);

            extractFieldNamesFromMap(map);

            processMapElements(map);
        }
    }

    private void detectElementTypes(List<?> list) {
        Set<String> elementTypes = new HashSet<>();
        boolean hasDicts = false;
        boolean hasLists = false;

        for (Object item : list) {
            if (item instanceof Map) {
                elementTypes.add("dict");
                hasDicts = true;
            } else if (item instanceof List) {
                elementTypes.add("list");
                hasLists = true;
            } else if (item instanceof String) {
                elementTypes.add("str");
            } else if (item instanceof Integer || item instanceof Long) {
                elementTypes.add("int");
            } else if (item instanceof Double || item instanceof Float) {
                elementTypes.add("float");
            } else if (item instanceof Boolean) {
                elementTypes.add("bool");
            } else {
                elementTypes.add("any");
            }
        }

        if (elementTypes.size() == 1) {
            this.attributes.put("element_type", elementTypes.iterator().next());
        } else {
            this.attributes.put("element_type", "mixed");
        }

        this.attributes.put("has_dicts", hasDicts);
        this.attributes.put("has_lists", hasLists);
    }

    private void processListElements(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            Object item = list.get(i);
            if (item instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) item;

                for (Object key : map.keySet()) {
                    if (key instanceof String) {
                        String fieldName = key.toString();
                        fieldNames.add(fieldName);

                        VariableSymbol nestedField = new VariableSymbol(
                                fieldName,
                                inferType(map.get(key)),
                                this.line,
                                this.scope
                        );
                        nestedField.setParentPath(this.name);

                        nestedFields.put(fieldName, nestedField);
                    }
                }
            }
        }
    }

    private void extractFieldNamesFromMap(Map<?, ?> map) {
        for (Object key : map.keySet()) {
            if (key instanceof String) {
                fieldNames.add(key.toString());
            }
        }
        this.attributes.put("fields", new ArrayList<>(fieldNames));
    }

    private void processMapElements(Map<?, ?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getKey() instanceof String) {
                String fieldName = entry.getKey().toString();
                Object fieldValue = entry.getValue();

                String fieldType = inferType(fieldValue);

                VariableSymbol nestedField = new VariableSymbol(
                        fieldName,
                        fieldType,
                        this.line,
                        this.scope,
                        fieldValue
                );
                nestedField.setParentPath(this.name);

                nestedFields.put(fieldName, nestedField);

                if (fieldValue instanceof Map || fieldValue instanceof List) {
                    nestedField.processValue(fieldValue);
                }
            }
        }
    }

    private String inferType(Object value) {
        if (value == null) {
            return "None";
        } else if (value instanceof String) {
            return "str";
        } else if (value instanceof Integer || value instanceof Long) {
            return "int";
        } else if (value instanceof Double || value instanceof Float) {
            return "float";
        } else if (value instanceof Boolean) {
            return "bool";
        } else if (value instanceof List) {
            return "list";
        } else if (value instanceof Map) {
            return "dict";
        } else {
            return "any";
        }
    }

    public void setValue(Object value) {
        this.value = value;
        this.nestedFields.clear();
        this.fieldNames.clear();
        processValue(value);
    }

    public void addAttribute(String key, Object value) {
        this.attributes.put(key, value);
    }

    public Object getAttribute(String key) {
        return this.attributes.get(key);
    }

    public boolean hasAttribute(String key) {
        return this.attributes.containsKey(key);
    }


    public void addNestedField(VariableSymbol field) {
        field.setParentPath(this.name);
        this.nestedFields.put(field.name, field);
        this.fieldNames.add(field.name);
    }

    public VariableSymbol getNestedField(String fieldName) {
        return nestedFields.get(fieldName);
    }

    public Map<String, VariableSymbol> getNestedFields() {
        return nestedFields;
    }

    public Set<String> getFieldNames() {
        return fieldNames;
    }

    public boolean hasNestedFields() {
        return !nestedFields.isEmpty();
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getParentPath() {
        return parentPath;
    }

    public String getFullPath() {
        if (parentPath != null) {
            return parentPath + "." + name;
        }
        return name;
    }


    @Override
    public String toString() {
        String valueStr = "";
        if (value != null) {
            if (value instanceof String) {
                valueStr = "\"" + value + "\"";
            } else if (value instanceof List) {
                valueStr = "List[" + ((List<?>) value).size() + " items]";
            } else if (value instanceof Map) {
                valueStr = "Dict[" + ((Map<?, ?>) value).size() + " entries]";
            } else {
                valueStr = value.toString();
            }
        } else {
            valueStr = "null";
        }

        String nestedInfo = "";
        if (!nestedFields.isEmpty()) {
            nestedInfo = " [Fields: " + nestedFields.keySet() + "]";
        }

        return "Variable(" + name + ":" + type + " = " + valueStr + nestedInfo + " at line " + line + ")";
    }

    public String getDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Variable: ").append(name).append("\n");
        sb.append("  Type: ").append(type).append("\n");
        sb.append("  Line: ").append(line).append("\n");
        sb.append("  Full Path: ").append(getFullPath()).append("\n");

        // عرض القيمة
        sb.append("  Value: ");
        if (value != null) {
            if (value instanceof List) {
                List<?> list = (List<?>) value;
                sb.append("List of ").append(list.size()).append(" items\n");

                for (int i = 0; i < Math.min(list.size(), 3); i++) {
                    Object item = list.get(i);
                    if (item instanceof Map) {
                        sb.append("    [").append(i).append("]: Dict with keys: ")
                                .append(((Map<?, ?>) item).keySet()).append("\n");
                    } else {
                        sb.append("    [").append(i).append("]: ").append(item).append("\n");
                    }
                }
                if (list.size() > 3) {
                    sb.append("    ... and ").append(list.size() - 3).append(" more items\n");
                }
            } else if (value instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) value;
                sb.append("Dict with ").append(map.size()).append(" entries\n");

                int count = 0;
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    if (count < 3) {
                        sb.append("    '").append(entry.getKey()).append("': ")
                                .append(entry.getValue()).append(" (")
                                .append(inferType(entry.getValue())).append(")\n");
                        count++;
                    } else {
                        break;
                    }
                }
                if (map.size() > 3) {
                    sb.append("    ... and ").append(map.size() - 3).append(" more entries\n");
                }
            } else {
                sb.append(value).append(" (").append(inferType(value)).append(")\n");
            }
        } else {
            sb.append("null\n");
        }

        if (!attributes.isEmpty()) {
            sb.append("  Attributes:\n");
            for (Map.Entry<String, Object> attr : attributes.entrySet()) {
                sb.append("    ").append(attr.getKey()).append(": ").append(attr.getValue()).append("\n");
            }
        }

        if (!nestedFields.isEmpty()) {
            sb.append("  Nested Fields (").append(nestedFields.size()).append("):\n");
            for (VariableSymbol field : nestedFields.values()) {
                sb.append("    • ").append(field.name).append(": ")
                        .append(field.type).append("\n");
            }
        }

        return sb.toString();
    }

    public String toJsonString(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        String indent = "  ".repeat(indentLevel);

        sb.append(indent).append("\"").append(name).append("\": ");

        if (value == null) {
            sb.append("null");
        } else if (value instanceof String) {
            sb.append("\"").append(value).append("\"");
        } else if (value instanceof Number || value instanceof Boolean) {
            sb.append(value);
        } else if (value instanceof List) {
            List<?> list = (List<?>) value;
            sb.append("[\n");
            for (int i = 0; i < list.size(); i++) {
                Object item = list.get(i);
                sb.append(indent).append("  ");
                if (item instanceof Map) {
                    sb.append("{...}");
                } else {
                    sb.append(item);
                }
                if (i < list.size() - 1) sb.append(",");
                sb.append("\n");
            }
            sb.append(indent).append("]");
        } else if (value instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) value;
            sb.append("{\n");
            int i = 0;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                sb.append(indent).append("  \"").append(entry.getKey()).append("\": ")
                        .append(entry.getValue());
                if (i < map.size() - 1) sb.append(",");
                sb.append("\n");
                i++;
            }
            sb.append(indent).append("}");
        } else {
            sb.append("\"").append(value.toString()).append("\"");
        }

        return sb.toString();
    }
}