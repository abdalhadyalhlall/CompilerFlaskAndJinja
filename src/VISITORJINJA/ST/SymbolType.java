package VISITORJINJA.ST;

public enum SymbolType {
    // HTML Elements
    HTML_ELEMENT,
    HEAD_ELEMENT,
    BODY_ELEMENT,
    NORMAL_ELEMENT,
    SELF_CLOSING_ELEMENT,

    // Attributes
    ATTRIBUTE,
    LANG_ATTRIBUTE,
    STYLE_ATTRIBUTE,

    // CSS
    CSS_RULE,
    CSS_COLOR,
    CSS_LENGTH,
    CSS_URL,
    CSS_REPEAT,
    CSS_POSITION,
    CSS_BORDER,
    CSS_FONT,
    CSS_TEXT,
    CSS_EFFECT,
    CSS_LAYOUT,
    CSS_OFFSET,

    // Jinja
    JINJA_EXPRESSION,
    JINJA_STATEMENT,
    JINJA_COMMENT,
    JINJA_IF_BLOCK,
    JINJA_FOR_BLOCK,
    JINJA_BLOCK,

    // Text
    TEXT_HTML,
    TEXT_JINJA,

    // Document
    DOCUMENT,
    DOCTYPE,

    // Variables (for Jinja)
    VARIABLE,
    TEMPLATE_VARIABLE,

    // Unknown
    UNKNOWN
}