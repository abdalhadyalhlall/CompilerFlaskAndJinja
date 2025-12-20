package VISITORJINJA;
import VISITORJINJA.ST.*;
import AST_HTMLCSSJINJA.*;
import antlrJinja.HTMLCSSJINJA_parser;
import antlrJinja.HTMLCSSJINJA_parserBaseVisitor;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisitorJinjaWithSymbolTable extends HTMLCSSJINJA_parserBaseVisitor<ASTNode> {

    private SymbolTableImpl symbolTable;
    private String currentSourceFile;
    private int elementIdCounter;

    public VisitorJinjaWithSymbolTable() {
        this.symbolTable = SymbolTableFactory.createDefaultSymbolTable();
        this.elementIdCounter = 0;
    }

    public VisitorJinjaWithSymbolTable(String sourceFile) {
        this();
        this.currentSourceFile = sourceFile;
    }

    public SymbolTableImpl getSymbolTable() {
        return symbolTable;
    }

    public void setSourceFile(String sourceFile) {
        this.currentSourceFile = sourceFile;
    }

    @Override
    public ASTNode visitDocumentLabel(HTMLCSSJINJA_parser.DocumentLabelContext ctx) {
        // إزالة الجزء الخاص بـ HTML_DOCTYPE لأنه غير موجود في القواعد النحوية
        Map<String, String> docProps = new HashMap<>();
        docProps.put("nodeType", "DocumentNode");
        docProps.put("source", "html");

        // التحقق من وجود Doctype بشكل مختلف
        boolean hasDoctype = false;
        for (ParseTree child : ctx.children) {
            if (child.getText() != null && child.getText().toLowerCase().contains("doctype")) {
                hasDoctype = true;
                break;
            }
        }
        docProps.put("hasDoctype", String.valueOf(hasDoctype));

        if (currentSourceFile != null) {
            docProps.put("sourceFile", currentSourceFile);
        }

        symbolTable.addSymbol("document", SymbolType.DOCUMENT,
                ctx.getStart().getLine(), docProps);

        DocumentNode document = new DocumentNode(ctx.getStart().getLine());

        for (ParseTree child : ctx.children) {
            ASTNode childNode = visit(child);
            if (childNode != null) {
                document.addChild(childNode);
            }
        }

        return document;
    }

    @Override
    public ASTNode visitHtmlelement(HTMLCSSJINJA_parser.HtmlelementContext ctx) {
        String elementId = "html_" + (elementIdCounter++);
        symbolTable.getScopeManager().enterElement("html");

        Map<String, String> htmlProps = new HashMap<>();
        htmlProps.put("elementType", "HTML");
        htmlProps.put("nodeType", "HtmlElementNode");
        htmlProps.put("elementId", elementId);
        if (currentSourceFile != null) {
            htmlProps.put("sourceFile", currentSourceFile);
        }

        symbolTable.addSymbol("html", SymbolType.HTML_ELEMENT,
                ctx.getStart().getLine(), htmlProps);

        HtmlElementNode htmlElement = new HtmlElementNode(
                ctx.getStart().getLine(),
                "html",
                HtmlElementNode.ElementType.HTML
        );

        // Process attributes
        for (HTMLCSSJINJA_parser.AttruputehtmlContext attrCtx : ctx.attruputehtml()) {
            ASTNode attrNode = visit(attrCtx);
            if (attrNode != null) {
                htmlElement.addChild(attrNode);
            }
        }

        // Process content
        for (HTMLCSSJINJA_parser.ContenthtmlContext contentCtx : ctx.contenthtml()) {
            ASTNode contentNode = visit(contentCtx);
            if (contentNode != null) {
                htmlElement.addChild(contentNode);
            }
        }

        symbolTable.getScopeManager().exitElement();

        return htmlElement;
    }

    @Override
    public ASTNode visitHead(HTMLCSSJINJA_parser.HeadContext ctx) {
        String elementId = "head_" + (elementIdCounter++);
        symbolTable.getScopeManager().enterElement("head");

        Map<String, String> headProps = new HashMap<>();
        headProps.put("elementType", "HEAD");
        headProps.put("nodeType", "HtmlElementNode");
        headProps.put("elementId", elementId);
        headProps.put("parent", "html");
        if (currentSourceFile != null) {
            headProps.put("sourceFile", currentSourceFile);
        }

        symbolTable.addSymbol("head", SymbolType.HEAD_ELEMENT,
                ctx.getStart().getLine(), headProps);

        HtmlElementNode headElement = new HtmlElementNode(
                ctx.getStart().getLine(),
                "head",
                HtmlElementNode.ElementType.HEAD
        );

        for (HTMLCSSJINJA_parser.ContentheadContext contentCtx : ctx.contenthead()) {
            ASTNode childNode = visit(contentCtx);
            if (childNode != null) {
                headElement.addChild(childNode);
            }
        }

        symbolTable.getScopeManager().exitElement();

        return headElement;
    }

    @Override
    public ASTNode visitBody(HTMLCSSJINJA_parser.BodyContext ctx) {
        String elementId = "body_" + (elementIdCounter++);
        symbolTable.getScopeManager().enterElement("body");

        Map<String, String> bodyProps = new HashMap<>();
        bodyProps.put("elementType", "BODY");
        bodyProps.put("nodeType", "HtmlElementNode");
        bodyProps.put("elementId", elementId);
        bodyProps.put("parent", "html");
        if (currentSourceFile != null) {
            bodyProps.put("sourceFile", currentSourceFile);
        }

        symbolTable.addSymbol("body", SymbolType.BODY_ELEMENT,
                ctx.getStart().getLine(), bodyProps);

        HtmlElementNode bodyElement = new HtmlElementNode(
                ctx.getStart().getLine(),
                "body",
                HtmlElementNode.ElementType.BODY
        );

        // Process attributes
        for (HTMLCSSJINJA_parser.AttributebodyContext attrCtx : ctx.attributebody()) {
            ASTNode attrNode = visit(attrCtx);
            if (attrNode != null) {
                bodyElement.addChild(attrNode);
            }
        }

        // Process content
        for (HTMLCSSJINJA_parser.ContentbodyContext contentCtx : ctx.contentbody()) {
            ASTNode childNode = visit(contentCtx);
            if (childNode != null) {
                bodyElement.addChild(childNode);
            }
        }

        symbolTable.getScopeManager().exitElement();

        return bodyElement;
    }

    @Override
    public ASTNode visitNormaltaghead(HTMLCSSJINJA_parser.NormaltagheadContext ctx) {
        String tagName = ctx.tagsheadnormal(0).getText();
        String elementId = tagName + "_" + (elementIdCounter++);

        symbolTable.getScopeManager().enterElement(tagName);

        Map<String, String> elementProps = new HashMap<>();
        elementProps.put("elementType", "NORMAL");
        elementProps.put("tagName", tagName);
        elementProps.put("location", "head");
        elementProps.put("nodeType", "HtmlElementNode");
        elementProps.put("elementId", elementId);
        elementProps.put("parent", "head");
        if (currentSourceFile != null) {
            elementProps.put("sourceFile", currentSourceFile);
        }

        symbolTable.addSymbol(tagName, SymbolType.NORMAL_ELEMENT,
                ctx.getStart().getLine(), elementProps);

        HtmlElementNode element = new HtmlElementNode(
                ctx.getStart().getLine(),
                tagName,
                HtmlElementNode.ElementType.NORMAL
        );

        // Process attributes
        for (HTMLCSSJINJA_parser.AttributeheadContext attrCtx : ctx.attributehead()) {
            ASTNode attrNode = visit(attrCtx);
            if (attrNode != null) {
                element.addChild(attrNode);
            }
        }

        // Process text content
        if (ctx.text() != null) {
            for (HTMLCSSJINJA_parser.TextContext textCtx : ctx.text()) {
                ASTNode textNode = visit(textCtx);
                if (textNode != null) {
                    element.addChild(textNode);
                }
            }
        }

        symbolTable.getScopeManager().exitElement();

        return element;
    }

    @Override
    public ASTNode visitSelfclostaglabel(HTMLCSSJINJA_parser.SelfclostaglabelContext ctx) {
        String tagName = ctx.tagsheadself().getText();
        String elementId = tagName + "_" + (elementIdCounter++);

        Map<String, String> elementProps = new HashMap<>();
        elementProps.put("elementType", "SELF_CLOSING");
        elementProps.put("tagName", tagName);
        elementProps.put("location", "head");
        elementProps.put("nodeType", "HtmlElementNode");
        elementProps.put("elementId", elementId);
        elementProps.put("parent", "head");
        if (currentSourceFile != null) {
            elementProps.put("sourceFile", currentSourceFile);
        }

        symbolTable.addSymbol(tagName, SymbolType.SELF_CLOSING_ELEMENT,
                ctx.getStart().getLine(), elementProps);

        HtmlElementNode element = new HtmlElementNode(
                ctx.getStart().getLine(),
                tagName,
                HtmlElementNode.ElementType.SELF_CLOSING
        );

        for (HTMLCSSJINJA_parser.AttributeheadContext attrCtx : ctx.attributehead()) {
            ASTNode attrNode = visit(attrCtx);
            if (attrNode != null) {
                element.addChild(attrNode);
            }
        }

        return element;
    }

    @Override
    public ASTNode visitNormal_Tag_Element_body(HTMLCSSJINJA_parser.Normal_Tag_Element_bodyContext ctx) {
        String tagName = ctx.tagsbodynamenormal(0).getText();
        String elementId = tagName + "_" + (elementIdCounter++);

        symbolTable.getScopeManager().enterElement(tagName);

        Map<String, String> elementProps = new HashMap<>();
        elementProps.put("elementType", "NORMAL");
        elementProps.put("tagName", tagName);
        elementProps.put("location", "body");
        elementProps.put("nodeType", "HtmlElementNode");
        elementProps.put("elementId", elementId);
        elementProps.put("parent", "body");
        if (currentSourceFile != null) {
            elementProps.put("sourceFile", currentSourceFile);
        }

        symbolTable.addSymbol(tagName, SymbolType.NORMAL_ELEMENT,
                ctx.getStart().getLine(), elementProps);

        HtmlElementNode element = new HtmlElementNode(
                ctx.getStart().getLine(),
                tagName,
                HtmlElementNode.ElementType.NORMAL
        );

        // Process attributes
        for (HTMLCSSJINJA_parser.AttributebodyContext attrCtx : ctx.attributebody()) {
            ASTNode attrNode = visit(attrCtx);
            if (attrNode != null) {
                element.addChild(attrNode);
            }
        }

        // Process content
        for (HTMLCSSJINJA_parser.ContentbodyContext contentCtx : ctx.contentbody()) {
            ASTNode childNode = visit(contentCtx);
            if (childNode != null) {
                element.addChild(childNode);
            }
        }

        symbolTable.getScopeManager().exitElement();

        return element;
    }

    @Override
    public ASTNode visitAttributenamebodylabel(HTMLCSSJINJA_parser.AttributenamebodylabelContext ctx) {
        String name = ctx.attributenamebody().getText();
        String value = ctx.ATTRIBUTE_VALUE().getText();

        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1);
        }

        Map<String, String> attrProps = new HashMap<>();
        attrProps.put("attributeType", "NORMAL");
        attrProps.put("value", value);
        attrProps.put("nodeType", "AttributeNode");
        attrProps.put("context", "body");
        if (currentSourceFile != null) {
            attrProps.put("sourceFile", currentSourceFile);
        }

        // Extract parent element from current scope
        String parentElement = extractParentElement();
        if (parentElement != null) {
            attrProps.put("parentElement", parentElement);
        }

        symbolTable.addSymbol(name, SymbolType.ATTRIBUTE,
                ctx.getStart().getLine(), attrProps);

        return new AttributeNode(
                ctx.getStart().getLine(),
                name,
                value,
                AttributeNode.AttributeType.NORMAL
        );
    }

    @Override
    public ASTNode visitLangAttributeLabel(HTMLCSSJINJA_parser.LangAttributeLabelContext ctx) {
        String value = "";
        if (ctx.VALUE_LANG_EN() != null) {
            value = ctx.VALUE_LANG_EN().getText();
        } else if (ctx.VALUE_LANG_AR() != null) {
            value = ctx.VALUE_LANG_AR().getText();
        }

        Map<String, String> attrProps = new HashMap<>();
        attrProps.put("attributeType", "LANG");
        attrProps.put("value", value);
        attrProps.put("nodeType", "AttributeNode");
        attrProps.put("context", "html");
        if (currentSourceFile != null) {
            attrProps.put("sourceFile", currentSourceFile);
        }

        symbolTable.addSymbol("lang", SymbolType.LANG_ATTRIBUTE,
                ctx.getStart().getLine(), attrProps);

        return new AttributeNode(
                ctx.getStart().getLine(),
                "lang",
                value,
                AttributeNode.AttributeType.LANG
        );
    }

    @Override
    public ASTNode visitStylelabel(HTMLCSSJINJA_parser.StylelabelContext ctx) {
        Map<String, String> styleProps = new HashMap<>();
        styleProps.put("nodeType", "StyleAttributeNode");
        styleProps.put("context", "inline");
        if (currentSourceFile != null) {
            styleProps.put("sourceFile", currentSourceFile);
        }

        // Extract parent element from current scope
        String parentElement = extractParentElement();
        if (parentElement != null) {
            styleProps.put("parentElement", parentElement);
        }

        symbolTable.addSymbol("style", SymbolType.STYLE_ATTRIBUTE,
                ctx.getStart().getLine(), styleProps);

        StyleAttributeNode styleAttr = new StyleAttributeNode(ctx.getStart().getLine());

        for (HTMLCSSJINJA_parser.StylenameContext styleCtx : ctx.stylename()) {
            ASTNode ruleNode = visit(styleCtx);
            if (ruleNode instanceof CssRuleNode) {
                styleAttr.addCssRule((CssRuleNode) ruleNode);
            }
        }

        return styleAttr;
    }

    @Override
    public ASTNode visitColor(HTMLCSSJINJA_parser.ColorContext ctx) {
        String property = ctx.CSS_ATTR_COLOR().getText();
        String value = ctx.CSS_COLOR_VALUE().getText();

        Map<String, String> cssProps = new HashMap<>();
        cssProps.put("property", property);
        cssProps.put("value", value);
        cssProps.put("ruleType", "COLOR");
        cssProps.put("nodeType", "CssRuleNode");
        cssProps.put("context", "style");
        if (currentSourceFile != null) {
            cssProps.put("sourceFile", currentSourceFile);
        }

        symbolTable.addSymbol(property, SymbolType.CSS_COLOR,
                ctx.getStart().getLine(), cssProps);

        return new CssRuleNode(
                ctx.getStart().getLine(),
                property,
                value,
                CssRuleNode.RuleType.COLOR
        );
    }

    @Override
    public ASTNode visitLength(HTMLCSSJINJA_parser.LengthContext ctx) {
        String property = ctx.CSS_LENGTH_PROPERTY().getText();

        StringBuilder valueBuilder = new StringBuilder();
        for (HTMLCSSJINJA_parser.Css_valueContext valueNode : ctx.css_value()) {
            if (valueBuilder.length() > 0) {
                valueBuilder.append(" ");
            }
            valueBuilder.append(valueNode.getText());
        }

        Map<String, String> cssProps = new HashMap<>();
        cssProps.put("property", property);
        cssProps.put("value", valueBuilder.toString());
        cssProps.put("ruleType", "LENGTH");
        cssProps.put("nodeType", "CssRuleNode");
        cssProps.put("context", "style");
        if (currentSourceFile != null) {
            cssProps.put("sourceFile", currentSourceFile);
        }

        symbolTable.addSymbol(property, SymbolType.CSS_LENGTH,
                ctx.getStart().getLine(), cssProps);

        return new CssRuleNode(
                ctx.getStart().getLine(),
                property,
                valueBuilder.toString(),
                CssRuleNode.RuleType.LENGTH
        );
    }

    @Override
    public ASTNode visitJinjaExpr(HTMLCSSJINJA_parser.JinjaExprContext ctx) {
        StringBuilder content = new StringBuilder();

        for (HTMLCSSJINJA_parser.JinjaExprContentContext exprCtx : ctx.jinjaExprContent()) {
            if (exprCtx.JINJA_EXPR_STRING() != null) {
                content.append(exprCtx.JINJA_EXPR_STRING().getText());
            } else if (exprCtx.JINJA_EXPR_ATOM() != null) {
                content.append(exprCtx.JINJA_EXPR_ATOM().getText());
            } else if (exprCtx.JINJA_EXPR_SYMBOL() != null) {
                content.append(exprCtx.JINJA_EXPR_SYMBOL().getText());
            } else if (exprCtx.JINJA_EXPR_UNKNOWN() != null) {
                content.append(exprCtx.JINJA_EXPR_UNKNOWN().getText());
            }
        }

        String exprContent = content.toString().trim();

        Map<String, String> jinjaProps = new HashMap<>();
        jinjaProps.put("type", "EXPRESSION");
        jinjaProps.put("content", exprContent);
        jinjaProps.put("nodeType", "JinjaNode");
        if (currentSourceFile != null) {
            jinjaProps.put("sourceFile", currentSourceFile);
        }

        // Extract parent element from current scope
        String parentElement = extractParentElement();
        if (parentElement != null) {
            jinjaProps.put("parentElement", parentElement);
        }

        extractVariablesFromExpression(exprContent, ctx.getStart().getLine());

        symbolTable.addSymbol("jinja_expr", SymbolType.JINJA_EXPRESSION,
                ctx.getStart().getLine(), jinjaProps);

        JinjaNode jinjaNode = new JinjaNode(
                ctx.getStart().getLine(),
                JinjaNode.JinjaType.EXPRESSION,
                exprContent
        );

        return jinjaNode;
    }

    @Override
    public ASTNode visitJinjaStmt(HTMLCSSJINJA_parser.JinjaStmtContext ctx) {
        StringBuilder content = new StringBuilder();

        for (HTMLCSSJINJA_parser.JinjaStmtArgumentContext argCtx : ctx.jinjaStmtArgument()) {
            if (argCtx.JINJA_STMT_ATOM() != null) {
                if (content.length() > 0) content.append(" ");
                content.append(argCtx.JINJA_STMT_ATOM().getText());
            } else if (argCtx.JINJA_STMT_SYMBOL() != null) {
                if (content.length() > 0) content.append(" ");
                content.append(argCtx.JINJA_STMT_SYMBOL().getText());
            } else if (argCtx.JINJA_STMT_STRING() != null) {
                if (content.length() > 0) content.append(" ");
                content.append(argCtx.JINJA_STMT_STRING().getText());
            } else if (argCtx.JINJA_STMT_UNKNOWN() != null) {
                if (content.length() > 0) content.append(" ");
                content.append(argCtx.JINJA_STMT_UNKNOWN().getText());
            }
        }

        String stmtContent = content.toString().trim();

        Map<String, String> jinjaProps = new HashMap<>();
        jinjaProps.put("type", "STATEMENT");
        jinjaProps.put("content", stmtContent);
        jinjaProps.put("nodeType", "JinjaNode");
        if (currentSourceFile != null) {
            jinjaProps.put("sourceFile", currentSourceFile);
        }

        // Extract parent element from current scope
        String parentElement = extractParentElement();
        if (parentElement != null) {
            jinjaProps.put("parentElement", parentElement);
        }

        symbolTable.addSymbol("jinja_stmt", SymbolType.JINJA_STATEMENT,
                ctx.getStart().getLine(), jinjaProps);

        JinjaNode jinjaNode = new JinjaNode(
                ctx.getStart().getLine(),
                JinjaNode.JinjaType.STATEMENT,
                stmtContent
        );

        return jinjaNode;
    }

    @Override
    public ASTNode visitJinjaIfBlock(HTMLCSSJINJA_parser.JinjaIfBlockContext ctx) {
        symbolTable.getScopeManager().enterBlock("if");

        Map<String, String> jinjaProps = new HashMap<>();
        jinjaProps.put("type", "IF_BLOCK");
        jinjaProps.put("nodeType", "JinjaBlockNode");
        if (currentSourceFile != null) {
            jinjaProps.put("sourceFile", currentSourceFile);
        }

        // Extract parent element from current scope
        String parentElement = extractParentElement();
        if (parentElement != null) {
            jinjaProps.put("parentElement", parentElement);
        }

        symbolTable.addSymbol("jinja_if", SymbolType.JINJA_IF_BLOCK,
                ctx.getStart().getLine(), jinjaProps);

        JinjaBlockNode ifBlock = new JinjaBlockNode(
                ctx.getStart().getLine(),
                JinjaBlockNode.BlockType.IF
        );

        String condition = extractCondition(ctx.JINJA_STMT_IF(), ctx.jinjaStmtArgument());
        ifBlock.addConditionBlock(condition);

        extractVariablesFromExpression(condition, ctx.getStart().getLine());

        for (HTMLCSSJINJA_parser.JinjaBlockContentContext contentCtx : ctx.jinjaBlockContent()) {
            ASTNode contentNode = visit(contentCtx);
            if (contentNode != null) {
                ifBlock.addContentToLastCondition(contentNode);
            }
        }

        for (int i = 0; i < ctx.JINJA_STMT_ELIF().size(); i++) {
            String elifCondition = extractCondition(null, ctx.jinjaStmtArgument());
            ifBlock.addConditionBlock(elifCondition);
            extractVariablesFromExpression(elifCondition, ctx.getStart().getLine());
        }

        if (ctx.JINJA_STMT_ELSE() != null) {
            for (HTMLCSSJINJA_parser.JinjaBlockContentContext elseContentCtx : ctx.jinjaBlockContent()) {
                ASTNode elseNode = visit(elseContentCtx);
                if (elseNode != null) {
                    ifBlock.addElseContent(elseNode);
                }
            }
        }

        symbolTable.getScopeManager().exitBlock();

        return ifBlock;
    }

    @Override
    public ASTNode visitJinjaForBlock(HTMLCSSJINJA_parser.JinjaForBlockContext ctx) {
        symbolTable.getScopeManager().enterBlock("for");

        Map<String, String> jinjaProps = new HashMap<>();
        jinjaProps.put("type", "FOR_BLOCK");
        jinjaProps.put("nodeType", "JinjaBlockNode");
        if (currentSourceFile != null) {
            jinjaProps.put("sourceFile", currentSourceFile);
        }

        // Extract parent element from current scope
        String parentElement = extractParentElement();
        if (parentElement != null) {
            jinjaProps.put("parentElement", parentElement);
        }

        symbolTable.addSymbol("jinja_for", SymbolType.JINJA_FOR_BLOCK,
                ctx.getStart().getLine(), jinjaProps);

        JinjaBlockNode forBlock = new JinjaBlockNode(
                ctx.getStart().getLine(),
                JinjaBlockNode.BlockType.FOR
        );

        String condition = extractCondition(ctx.JINJA_STMT_FOR(), ctx.jinjaStmtArgument());
        forBlock.addConditionBlock(condition);

        extractLoopVariables(condition, ctx.getStart().getLine());

        for (HTMLCSSJINJA_parser.JinjaBlockContentContext contentCtx : ctx.jinjaBlockContent()) {
            ASTNode contentNode = visit(contentCtx);
            if (contentNode != null) {
                forBlock.addContentToLastCondition(contentNode);
            }
        }

        if (ctx.JINJA_STMT_ELSE() != null) {
            for (HTMLCSSJINJA_parser.JinjaBlockContentContext elseContentCtx : ctx.jinjaBlockContent()) {
                ASTNode elseNode = visit(elseContentCtx);
                if (elseNode != null) {
                    forBlock.addElseContent(elseNode);
                }
            }
        }

        symbolTable.getScopeManager().exitBlock();

        return forBlock;
    }

    @Override
    public ASTNode visitText(HTMLCSSJINJA_parser.TextContext ctx) {
        Token token = ctx.TEXT().getSymbol();
        String textContent = ctx.TEXT().getText();

        Map<String, String> textProps = new HashMap<>();
        textProps.put("context", "HTML");
        textProps.put("content", textContent);
        textProps.put("nodeType", "TextNode");
        if (currentSourceFile != null) {
            textProps.put("sourceFile", currentSourceFile);
        }

        // Extract parent element from current scope
        String parentElement = extractParentElement();
        if (parentElement != null) {
            textProps.put("parentElement", parentElement);
        }

        symbolTable.addSymbol("text", SymbolType.TEXT_HTML,
                token.getLine(), textProps);

        return new TextNode(
                token.getLine(),
                textContent,
                TextNode.TextContext.HTML
        );
    }

    @Override
    public ASTNode visitJinjaTextLabel(HTMLCSSJINJA_parser.JinjaTextLabelContext ctx) {
        Token token = ctx.TEXT().getSymbol();
        String textContent = ctx.TEXT().getText();

        Map<String, String> textProps = new HashMap<>();
        textProps.put("context", "JINJA");
        textProps.put("content", textContent);
        textProps.put("nodeType", "TextNode");
        if (currentSourceFile != null) {
            textProps.put("sourceFile", currentSourceFile);
        }

        // Extract parent element from current scope
        String parentElement = extractParentElement();
        if (parentElement != null) {
            textProps.put("parentElement", parentElement);
        }

        symbolTable.addSymbol("text_jinja", SymbolType.TEXT_JINJA,
                token.getLine(), textProps);

        return new TextNode(
                token.getLine(),
                textContent,
                TextNode.TextContext.JINJA
        );
    }

    private void extractVariablesFromExpression(String expression, int lineNumber) {
        String[] parts = expression.split("[\\s+\\-*/%&|^~!<>=,()\\[\\]]+");

        for (String part : parts) {
            part = part.trim();
            if (!part.isEmpty() && Character.isLetter(part.charAt(0)) && !isKeyword(part)) {
                Map<String, String> varProps = new HashMap<>();
                varProps.put("type", "EXPRESSION_VARIABLE");
                varProps.put("context", "JINJA");
                varProps.put("source", "expression");
                if (currentSourceFile != null) {
                    varProps.put("sourceFile", currentSourceFile);
                }

                // Extract parent element from current scope
                String parentElement = extractParentElement();
                if (parentElement != null) {
                    varProps.put("parentElement", parentElement);
                }

                symbolTable.addSymbol(part, SymbolType.VARIABLE, lineNumber, varProps);
            }
        }
    }

    private void extractLoopVariables(String forCondition, int lineNumber) {
        String[] parts = forCondition.split("\\s+");
        if (parts.length >= 3 && parts[0].equals("for") && parts[2].equals("in")) {
            String loopVar = parts[1];

            Map<String, String> varProps = new HashMap<>();
            varProps.put("type", "LOOP_VARIABLE");
            varProps.put("context", "JINJA_FOR");
            varProps.put("source", "for_loop");
            if (currentSourceFile != null) {
                varProps.put("sourceFile", currentSourceFile);
            }

            // Extract parent element from current scope
            String parentElement = extractParentElement();
            if (parentElement != null) {
                varProps.put("parentElement", parentElement);
            }

            symbolTable.addSymbol(loopVar, SymbolType.VARIABLE, lineNumber, varProps);
        }
    }

    private String extractParentElement() {
        String currentScope = symbolTable.getScopeManager().getCurrentScope();
        if (currentScope != null && currentScope.startsWith("element_")) {
            // Extract element name from scope (element_<name>_<id>)
            String[] parts = currentScope.split("_");
            if (parts.length >= 2) {
                return parts[1];
            }
        }
        return null;
    }

    private boolean isKeyword(String word) {
        String[] keywords = {
                "if", "else", "elif", "for", "in", "and", "or", "not",
                "True", "False", "None", "is", "as", "with"
        };

        for (String keyword : keywords) {
            if (keyword.equals(word)) {
                return true;
            }
        }
        return false;
    }

    private String extractCondition(TerminalNode keyword, List<HTMLCSSJINJA_parser.JinjaStmtArgumentContext> arguments) {
        StringBuilder condition = new StringBuilder();

        if (keyword != null) {
            condition.append(keyword.getText());
        }

        for (HTMLCSSJINJA_parser.JinjaStmtArgumentContext arg : arguments) {
            if (condition.length() > 0) condition.append(" ");

            if (arg.JINJA_STMT_ATOM() != null) {
                condition.append(arg.JINJA_STMT_ATOM().getText());
            } else if (arg.JINJA_STMT_SYMBOL() != null) {
                condition.append(arg.JINJA_STMT_SYMBOL().getText());
            } else if (arg.JINJA_STMT_STRING() != null) {
                condition.append(arg.JINJA_STMT_STRING().getText());
            } else if (arg.JINJA_STMT_UNKNOWN() != null) {
                condition.append(arg.JINJA_STMT_UNKNOWN().getText());
            }
        }

        return condition.toString().trim();
    }

    // Utility methods for analysis
    public void printSymbolAnalysis() {
        System.out.println("\n=== SYMBOL ANALYSIS ===");
        System.out.println("Total Symbols: " + symbolTable.getAllSymbols().size());

        System.out.println("\nSymbols by Type:");
        for (SymbolType type : SymbolType.values()) {
            List<Symbol> symbolsByType = symbolTable.getSymbolsByType(type);
            if (!symbolsByType.isEmpty()) {
                System.out.println("  " + type + ": " + symbolsByType.size());
            }
        }

        System.out.println("\nElement Hierarchy:");
        Map<String, List<Symbol>> hierarchy = symbolTable.getSymbolHierarchy();
        for (Map.Entry<String, List<Symbol>> entry : hierarchy.entrySet()) {
            System.out.println("  " + entry.getKey() + ":");
            for (Symbol child : entry.getValue()) {
                System.out.println("    - " + child.getName() +
                        " (" + child.getType() + ")" +
                        " [line " + child.getLineNumber() + "]");
            }
        }
    }

    public List<Symbol> getUnusedVariables() {
        List<Symbol> variables = symbolTable.getSymbolsByType(SymbolType.VARIABLE);
        List<Symbol> jinjaExprs = symbolTable.getSymbolsByType(SymbolType.JINJA_EXPRESSION);
        List<Symbol> unused = new ArrayList<>();

        for (Symbol var : variables) {
            boolean isUsed = false;
            String varName = var.getName();

            for (Symbol expr : jinjaExprs) {
                String content = expr.getProperty("content");
                if (content != null && content.contains(varName)) {
                    isUsed = true;
                    break;
                }
            }

            if (!isUsed) {
                unused.add(var);
            }
        }

        return unused;
    }
}