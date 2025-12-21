package VISITORJINJA;

import AST_HTMLCSSJINJA.*;
import VISITORJINJA.ST.*;
import antlrJinja.HTMLCSSJINJA_parser;
import antlrJinja.HTMLCSSJINJA_parserBaseVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.*;

/**
 * Visitor متخصص لبناء جدول الرموز (Symbol Table)
 * يرث من Visitor الأصلي ويضيف وظائف Symbol Table فقط
 */
public class VisitorJinjaSymbolTable extends HTMLCSSJINJA_parserBaseVisitor<ASTNode> {

    // ================ المتغيرات الداخلية ================

    private HtmlCssJinjaSymbolTable symbolTable;

    // Stacks للإدارة الهرمية
    private Stack<HtmlElementSymbol> elementStack;
    private Stack<StyleAttributeSymbol> styleAttrStack;
    private Stack<JinjaBlockSymbol> jinjaBlockStack;

    // تتبع السياق الحالي
    private boolean inJinjaContext;
    private boolean inStyleContext;

    // إدارة الأخطاء
    private List<String> errors;
    private List<String> warnings;

    // المرجع إلى Visitor الأصلي (اختياري)
    private VisitorJinja originalVisitor;

    // ================ المُنشئ والتهيئة ================

    public VisitorJinjaSymbolTable() {
        this.symbolTable = new HtmlCssJinjaSymbolTable();

        // تهيئة Stacks
        this.elementStack = new Stack<>();
        this.styleAttrStack = new Stack<>();
        this.jinjaBlockStack = new Stack<>();

        // تهيئة سياقات
        this.inJinjaContext = false;
        this.inStyleContext = false;

        // تهيئة قوائم الأخطاء والتحذيرات
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();

        this.originalVisitor = null;
    }

    /**
     * مُنشئ مع Visitor الأصلي
     */
    public VisitorJinjaSymbolTable(VisitorJinja originalVisitor) {
        this();
        this.originalVisitor = originalVisitor;
    }

    // ================ طرق الوصول العامة ================

    public HtmlCssJinjaSymbolTable getSymbolTable() {
        return symbolTable;
    }

    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }

    public List<String> getWarnings() {
        return new ArrayList<>(warnings);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void printStatistics() {
        symbolTable.printStatistics();
        System.out.println("\n=== Visitor Errors/Warnings ===");
        System.out.println("Errors: " + errors.size());
        System.out.println("Warnings: " + warnings.size());
    }

    // ================ إدارة الأخطاء والتحذيرات ================

    private void addError(String error, int line, int column) {
        String errorMsg = String.format("SymbolTable Error at line %d, col %d: %s",
                line, column, error);
        errors.add(errorMsg);
        System.err.println("[SymbolTable ERROR] " + errorMsg);
    }

    private void addWarning(String warning, int line, int column) {
        String warningMsg = String.format("SymbolTable Warning at line %d, col %d: %s",
                line, column, warning);
        warnings.add(warningMsg);
        System.out.println("[SymbolTable WARNING] " + warningMsg);
    }

    private void logInfo(String info) {
        System.out.println("[SymbolTable INFO] " + info);
    }

    // ================ طرق مساعدة للتحقق ================

    private boolean isValidId(String id) {
        if (id == null || id.isEmpty()) return false;
        // يجب أن يبدأ بحرف أو underscore
        return id.matches("^[a-zA-Z_][a-zA-Z0-9_\\-]*$");
    }

    private boolean isValidClass(String className) {
        if (className == null || className.isEmpty()) return false;
        return !className.contains(" ") && className.matches("^[a-zA-Z_][a-zA-Z0-9_\\-]*$");
    }

    // ================ إدارة العناصر HTML ================

    @Override
    public ASTNode visitHtmlelement(HTMLCSSJINJA_parser.HtmlelementContext ctx) {
        try {
            HtmlElementSymbol htmlElement = new HtmlElementSymbol(
                    "html",
                    HtmlElementSymbol.ElementCategory.STRUCTURAL,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine(),
                    true
            );

            // دخول نطاق العنصر
            symbolTable.enterScope("html_element", SymbolScope.ScopeType.HTML_ELEMENT);
            elementStack.push(htmlElement);

            // زيارة الأطفال
            ASTNode result = super.visitHtmlelement(ctx);

            // الخروج من نطاق العنصر
            symbolTable.closeHtmlElement();
            elementStack.pop();

            // إضافة للجدول
            symbolTable.addHtmlElement(htmlElement);
            logInfo("Added HTML element: <html>");

            return result;
        } catch (Exception e) {
            addError("Error processing HTML element: " + e.getMessage(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine());
            return super.visitHtmlelement(ctx);
        }
    }

    @Override
    public ASTNode visitNormaltaghead(HTMLCSSJINJA_parser.NormaltagheadContext ctx) {
        try {
            String tagName = ctx.tagsheadnormal(0).getText();
            HtmlElementSymbol.ElementCategory category =
                    HtmlElementSymbol.getCategoryFromTag(tagName);

            HtmlElementSymbol element = new HtmlElementSymbol(
                    tagName,
                    category,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine(),
                    true
            );

            // دخول نطاق العنصر
            symbolTable.enterScope("element_" + tagName, SymbolScope.ScopeType.HTML_ELEMENT);
            elementStack.push(element);

            ASTNode result = super.visitNormaltaghead(ctx);

            // الخروج من نطاق العنصر
            symbolTable.closeHtmlElement();
            elementStack.pop();

            // إضافة للجدول
            symbolTable.addHtmlElement(element);
            logInfo("Added head element: <" + tagName + ">");

            return result;
        } catch (Exception e) {
            addError("Error processing head tag: " + e.getMessage(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine());
            return super.visitNormaltaghead(ctx);
        }
    }

    @Override
    public ASTNode visitNormal_Tag_Element_body(HTMLCSSJINJA_parser.Normal_Tag_Element_bodyContext ctx) {
        try {
            String tagName = ctx.tagsbodynamenormal(0).getText();
            HtmlElementSymbol.ElementCategory category =
                    HtmlElementSymbol.getCategoryFromTag(tagName);
            boolean isSelfClosing = HtmlElementSymbol.isSelfClosingTag(tagName);

            HtmlElementSymbol element = new HtmlElementSymbol(
                    tagName,
                    category,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine(),
                    !isSelfClosing
            );

            // تعيين سياق Jinja إذا كنا داخل واحد
            if (inJinjaContext) {
                element.setInJinjaContext(true);
            }

            // دخول نطاق العنصر (إذا لم يكن مغلقاً ذاتياً)
            if (!isSelfClosing) {
                symbolTable.enterScope("element_" + tagName, SymbolScope.ScopeType.HTML_ELEMENT);
                elementStack.push(element);
            }

            ASTNode result = super.visitNormal_Tag_Element_body(ctx);

            // الخروج من نطاق العنصر
            if (!isSelfClosing) {
                symbolTable.closeHtmlElement();
                elementStack.pop();
            }

            // إضافة للجدول
            symbolTable.addHtmlElement(element);
            logInfo("Added body element: <" + tagName + ">");

            return result;
        } catch (Exception e) {
            addError("Error processing body tag: " + e.getMessage(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine());
            return super.visitNormal_Tag_Element_body(ctx);
        }
    }

    @Override
    public ASTNode visitSelfClosingTagElementbody(HTMLCSSJINJA_parser.SelfClosingTagElementbodyContext ctx) {
        try {
            String tagName = ctx.tagsbodynameself().getText();
            HtmlElementSymbol.ElementCategory category =
                    HtmlElementSymbol.getCategoryFromTag(tagName);

            HtmlElementSymbol element = new HtmlElementSymbol(
                    tagName,
                    category,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine(),
                    false  // self-closing
            );

            // تعيين سياق Jinja إذا كنا داخل واحد
            if (inJinjaContext) {
                element.setInJinjaContext(true);
            }

            // زيارة السمات
            super.visitSelfClosingTagElementbody(ctx);

            // إضافة للجدول
            symbolTable.addHtmlElement(element);
            logInfo("Added self-closing element: <" + tagName + "/>");

            return null; // نرجع null لأننا لا نبني AST
        } catch (Exception e) {
            addError("Error processing self-closing tag: " + e.getMessage(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine());
            return super.visitSelfClosingTagElementbody(ctx);
        }
    }

    // ================ إدارة السمات HTML ================

    @Override
    public ASTNode visitAttributenamebodylabel(HTMLCSSJINJA_parser.AttributenamebodylabelContext ctx) {
        try {
            String attrName = ctx.attributenamebody().getText();
            String attrValue = ctx.ATTRIBUTE_VALUE().getText();

            HtmlAttributeSymbol.AttributeType attrType =
                    HtmlAttributeSymbol.inferAttributeType(attrName);

            HtmlAttributeSymbol attribute = new HtmlAttributeSymbol(
                    attrName,
                    attrType,
                    attrValue,
                    true,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
            );

            // ربط السمة بالعنصر الحالي
            if (!elementStack.isEmpty()) {
                HtmlElementSymbol currentElement = elementStack.peek();
                attribute.setParentElement(currentElement);
                currentElement.addAttribute(attribute);

                // التحقق من السمات الخاصة
                if ("id".equals(attrName)) {
                    String idValue = attribute.getValueWithoutQuotes();
                    if (!isValidId(idValue)) {
                        addWarning("Invalid ID format: " + idValue,
                                ctx.getStart().getLine(),
                                ctx.getStart().getCharPositionInLine());
                    }
                } else if ("class".equals(attrName)) {
                    String[] classes = attribute.getValueAsArray();
                    for (String cls : classes) {
                        if (!isValidClass(cls)) {
                            addWarning("Invalid class name: " + cls,
                                    ctx.getStart().getLine(),
                                    ctx.getStart().getCharPositionInLine());
                        }
                    }
                }
            } else {
                addWarning("Attribute " + attrName + " without parent element",
                        ctx.getStart().getLine(),
                        ctx.getStart().getCharPositionInLine());
            }

            // إضافة للجدول
            symbolTable.addHtmlAttribute(attribute);
            logInfo("Added attribute: " + attrName + "=\"" +
                    attribute.getValueWithoutQuotes() + "\"");

            return super.visitAttributenamebodylabel(ctx);
        } catch (Exception e) {
            addError("Error processing attribute: " + e.getMessage(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine());
            return super.visitAttributenamebodylabel(ctx);
        }
    }

    @Override
    public ASTNode visitAttributenamebodywithoutvaluelabel(HTMLCSSJINJA_parser.AttributenamebodywithoutvaluelabelContext ctx) {
        try {
            String attrName = ctx.attributenamebodywithoutvalue().getText();
            HtmlAttributeSymbol.AttributeType attrType =
                    HtmlAttributeSymbol.inferAttributeType(attrName);

            HtmlAttributeSymbol attribute = new HtmlAttributeSymbol(
                    attrName,
                    attrType,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
            );

            // ربط السمة بالعنصر الحالي
            if (!elementStack.isEmpty()) {
                HtmlElementSymbol currentElement = elementStack.peek();
                attribute.setParentElement(currentElement);
                currentElement.addAttribute(attribute);
            }

            // إضافة للجدول
            symbolTable.addHtmlAttribute(attribute);
            logInfo("Added boolean attribute: " + attrName);

            return super.visitAttributenamebodywithoutvaluelabel(ctx);
        } catch (Exception e) {
            addError("Error processing boolean attribute: " + e.getMessage(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine());
            return super.visitAttributenamebodywithoutvaluelabel(ctx);
        }
    }

    // ================ إدارة سمة Style و CSS ================

    @Override
    public ASTNode visitStylelabel(HTMLCSSJINJA_parser.StylelabelContext ctx) {
        try {
            StyleAttributeSymbol styleAttr = new StyleAttributeSymbol(
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
            );

            // ربط سمة style بالعنصر الحالي
            if (!elementStack.isEmpty()) {
                HtmlElementSymbol currentElement = elementStack.peek();
                styleAttr.setParentElement(currentElement);
                currentElement.addAttribute(styleAttr);
            }

            // دخول نطاق CSS
            symbolTable.enterScope("style_attribute", SymbolScope.ScopeType.STYLE_ATTRIBUTE);
            styleAttrStack.push(styleAttr);
            inStyleContext = true;

            // زيارة قواعد CSS
            super.visitStylelabel(ctx);

            // الخروج من نطاق CSS
            symbolTable.exitScope();
            styleAttrStack.pop();
            inStyleContext = false;

            // إضافة للجدول
            symbolTable.addHtmlAttribute(styleAttr);
            logInfo("Added style attribute with " + styleAttr.getRuleCount() + " rules");

            return null;
        } catch (Exception e) {
            addError("Error processing style attribute: " + e.getMessage(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine());
            return super.visitStylelabel(ctx);
        }
    }

    @Override
    public ASTNode visitColor(HTMLCSSJINJA_parser.ColorContext ctx) {
        try {
            if (ctx.CSS_ATTR_COLOR() != null && ctx.CSS_COLOR_VALUE() != null) {
                String property = ctx.CSS_ATTR_COLOR().getText();
                String value = ctx.CSS_COLOR_VALUE().getText();

                CssRuleSymbol rule = new CssRuleSymbol(
                        property,
                        CssRuleSymbol.RuleCategory.COLOR,
                        Collections.singletonList(value),
                        ctx.getStart().getLine(),
                        ctx.getStart().getCharPositionInLine()
                );

                // إضافة القاعدة لسمة style الحالية
                if (!styleAttrStack.isEmpty()) {
                    StyleAttributeSymbol currentStyle = styleAttrStack.peek();
                    currentStyle.addCssRule(rule);

                    // ربط القاعدة بالعنصر
                    if (!elementStack.isEmpty()) {
                        rule.setTargetElement(elementStack.peek());
                    }
                }

                // إضافة للجدول
                symbolTable.addCssRule(rule);
                logInfo("Added CSS rule: " + property + ": " + value);
            }

            return super.visitColor(ctx);
        } catch (Exception e) {
            addError("Error processing CSS color rule: " + e.getMessage(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine());
            return super.visitColor(ctx);
        }
    }

    @Override
    public ASTNode visitLength(HTMLCSSJINJA_parser.LengthContext ctx) {
        try {
            if (ctx.CSS_LENGTH_PROPERTY() != null && ctx.css_value() != null) {
                String property = ctx.CSS_LENGTH_PROPERTY().getText();
                List<String> values = new ArrayList<>();

                for (HTMLCSSJINJA_parser.Css_valueContext valueCtx : ctx.css_value()) {
                    values.add(valueCtx.getText());
                }

                CssRuleSymbol rule = new CssRuleSymbol(
                        property,
                        CssRuleSymbol.RuleCategory.BOX_MODEL,
                        values,
                        ctx.getStart().getLine(),
                        ctx.getStart().getCharPositionInLine()
                );

                // إضافة القاعدة لسمة style الحالية
                if (!styleAttrStack.isEmpty()) {
                    StyleAttributeSymbol currentStyle = styleAttrStack.peek();
                    currentStyle.addCssRule(rule);

                    // ربط القاعدة بالعنصر
                    if (!elementStack.isEmpty()) {
                        rule.setTargetElement(elementStack.peek());
                    }
                }

                // إضافة للجدول
                symbolTable.addCssRule(rule);
                logInfo("Added CSS length rule: " + property);
            }

            return super.visitLength(ctx);
        } catch (Exception e) {
            addError("Error processing CSS length rule: " + e.getMessage(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine());
            return super.visitLength(ctx);
        }
    }

    // يمكن إضافة المزيد من قواعد CSS هنا...

    // ================ إدارة Jinja2 ================

    @Override
    public ASTNode visitJinjaExpr(HTMLCSSJINJA_parser.JinjaExprContext ctx) {
        try {
            StringBuilder content = new StringBuilder();
            if (ctx.jinjaExprContent() != null) {
                for (HTMLCSSJINJA_parser.JinjaExprContentContext exprCtx : ctx.jinjaExprContent()) {
                    if (content.length() > 0) content.append(" ");
                    content.append(exprCtx.getText());
                }
            }

            JinjaSymbol jinja = new JinjaSymbol(
                    JinjaSymbol.JinjaType.EXPRESSION,
                    content.toString().trim(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
            );

            // ربط بالعنصر الحالي
            if (!elementStack.isEmpty()) {
                jinja.setParentElement(elementStack.peek());
            }

            // إضافة للجدول
            symbolTable.addJinjaSymbol(jinja);
            logInfo("Added Jinja expression: {{ " + jinja.getContent() + " }}");

            // تعيين سياق Jinja
            boolean previousJinjaContext = inJinjaContext;
            inJinjaContext = true;

            ASTNode result = super.visitJinjaExpr(ctx);

            // استعادة السياق
            inJinjaContext = previousJinjaContext;

            return result;
        } catch (Exception e) {
            addError("Error processing Jinja expression: " + e.getMessage(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine());
            return super.visitJinjaExpr(ctx);
        }
    }

    @Override
    public ASTNode visitJinjaStmt(HTMLCSSJINJA_parser.JinjaStmtContext ctx) {
        try {
            StringBuilder content = new StringBuilder();
            if (ctx.jinjaStmtArgument() != null) {
                for (HTMLCSSJINJA_parser.JinjaStmtArgumentContext argCtx : ctx.jinjaStmtArgument()) {
                    if (content.length() > 0) content.append(" ");
                    content.append(argCtx.getText());
                }
            }

            JinjaSymbol jinja = new JinjaSymbol(
                    JinjaSymbol.JinjaType.STATEMENT,
                    content.toString().trim(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
            );

            // التحقق من نوع البيان
            if (jinja.isControlStructure()) {
                logInfo("Found Jinja control structure: {% " + jinja.getContent() + " %}");
            }

            // ربط بالعنصر الحالي
            if (!elementStack.isEmpty()) {
                jinja.setParentElement(elementStack.peek());
            }

            // إضافة للجدول
            symbolTable.addJinjaSymbol(jinja);

            // تعيين سياق Jinja
            boolean previousJinjaContext = inJinjaContext;
            inJinjaContext = true;

            ASTNode result = super.visitJinjaStmt(ctx);

            // استعادة السياق
            inJinjaContext = previousJinjaContext;

            return result;
        } catch (Exception e) {
            addError("Error processing Jinja statement: " + e.getMessage(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine());
            return super.visitJinjaStmt(ctx);
        }
    }

    @Override
    public ASTNode visitJinjaIfBlock(HTMLCSSJINJA_parser.JinjaIfBlockContext ctx) {
        try {
            // استخراج الشرط
            String condition = extractJinjaCondition(ctx);

            // إنشاء كتلة IF
            JinjaBlockSymbol ifBlock = JinjaBlockSymbol.createIfBlock(
                    condition,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
            );

            // ربط بالعنصر الحالي
            if (!elementStack.isEmpty()) {
                ifBlock.setParentElement(elementStack.peek());
            }

            // دخول نطاق Jinja IF
            symbolTable.enterScope("jinja_if_" + condition.hashCode(),
                    SymbolScope.ScopeType.JINJA_IF);
            jinjaBlockStack.push(ifBlock);
            boolean previousJinjaContext = inJinjaContext;
            inJinjaContext = true;

            // زيارة محتوى الكتلة
            super.visitJinjaIfBlock(ctx);

            // الخروج من نطاق Jinja IF
            symbolTable.closeJinjaBlock();
            jinjaBlockStack.pop();
            inJinjaContext = previousJinjaContext;

            // إضافة للجدول
            symbolTable.addJinjaSymbol(ifBlock);
            logInfo("Added Jinja IF block with condition: " + condition);

            return null;
        } catch (Exception e) {
            addError("Error processing Jinja IF block: " + e.getMessage(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine());
            return super.visitJinjaIfBlock(ctx);
        }
    }

    @Override
    public ASTNode visitJinjaForBlock(HTMLCSSJINJA_parser.JinjaForBlockContext ctx) {
        try {
            // استخراج معلومات الحلقة
            ForLoopInfo loopInfo = extractForLoopInfo(ctx);

            // إنشاء كتلة FOR
            JinjaBlockSymbol forBlock = JinjaBlockSymbol.createForBlock(
                    loopInfo.iterator,
                    loopInfo.iterable,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
            );

            // ربط بالعنصر الحالي
            if (!elementStack.isEmpty()) {
                forBlock.setParentElement(elementStack.peek());
            }

            // دخول نطاق Jinja FOR
            symbolTable.enterScope("jinja_for_" + loopInfo.iterator,
                    SymbolScope.ScopeType.JINJA_FOR);
            jinjaBlockStack.push(forBlock);
            boolean previousJinjaContext = inJinjaContext;
            inJinjaContext = true;

            // زيارة محتوى الكتلة
            super.visitJinjaForBlock(ctx);

            // الخروج من نطاق Jinja FOR
            symbolTable.closeJinjaBlock();
            jinjaBlockStack.pop();
            inJinjaContext = previousJinjaContext;

            // إضافة للجدول
            symbolTable.addJinjaSymbol(forBlock);
            logInfo("Added Jinja FOR block: " + loopInfo.iterator + " in " + loopInfo.iterable);

            return null;
        } catch (Exception e) {
            addError("Error processing Jinja FOR block: " + e.getMessage(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine());
            return super.visitJinjaForBlock(ctx);
        }
    }

    @Override
    public ASTNode visitJinjaComment(HTMLCSSJINJA_parser.JinjaCommentContext ctx) {
        try {
            String commentText = ctx.JANJI_COMMENT().getText();
            // إزالة علامات التعليق {# و #}
            String content = commentText;
            if (content.startsWith("{#") && content.endsWith("#}")) {
                content = content.substring(2, content.length() - 2).trim();
            }

            JinjaSymbol jinja = new JinjaSymbol(
                    JinjaSymbol.JinjaType.COMMENT,
                    content,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
            );

            // إضافة للجدول
            symbolTable.addJinjaSymbol(jinja);
            logInfo("Added Jinja comment");

            return super.visitJinjaComment(ctx);
        } catch (Exception e) {
            addError("Error processing Jinja comment: " + e.getMessage(),
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine());
            return super.visitJinjaComment(ctx);
        }
    }

    // ================ طرق مساعدة ================

    private String extractJinjaCondition(HTMLCSSJINJA_parser.JinjaIfBlockContext ctx) {
        StringBuilder condition = new StringBuilder();
        List<HTMLCSSJINJA_parser.JinjaStmtArgumentContext> arguments = ctx.jinjaStmtArgument();

        if (arguments != null) {
            for (HTMLCSSJINJA_parser.JinjaStmtArgumentContext arg : arguments) {
                if (condition.length() > 0) condition.append(" ");
                condition.append(arg.getText());
            }
        }

        return condition.toString().trim();
    }

    private ForLoopInfo extractForLoopInfo(HTMLCSSJINJA_parser.JinjaForBlockContext ctx) {
        ForLoopInfo info = new ForLoopInfo();
        List<HTMLCSSJINJA_parser.JinjaStmtArgumentContext> arguments = ctx.jinjaStmtArgument();

        if (arguments != null) {
            boolean foundIn = false;
            for (HTMLCSSJINJA_parser.JinjaStmtArgumentContext arg : arguments) {
                String text = arg.getText();

                if ("in".equals(text)) {
                    foundIn = true;
                } else if (!foundIn) {
                    if (info.iterator == null) {
                        info.iterator = text;
                    } else {
                        info.iterator += " " + text;
                    }
                } else {
                    if (info.iterable == null) {
                        info.iterable = text;
                    } else {
                        info.iterable += " " + text;
                    }
                }
            }
        }

        return info;
    }

    // ================ فئة مساعدة ================

    private static class ForLoopInfo {
        String iterator;
        String iterable;
    }

    // ================ الزيارة للعناصر الأخرى ================

    @Override
    public ASTNode visitText(HTMLCSSJINJA_parser.TextContext ctx) {
        // نعامل النص فقط إذا كنا نريد تتبعه في جدول الرموز
        // يمكن إضافة رمز نصي إذا لزم الأمر
        return super.visitText(ctx);
    }

    @Override
    public ASTNode visitDoctipe(HTMLCSSJINJA_parser.DoctipeContext ctx) {
        logInfo("Found DOCTYPE declaration");
        return super.visitDoctipe(ctx);
    }

    @Override
    public ASTNode visitHtmlcommentlabel(HTMLCSSJINJA_parser.HtmlcommentlabelContext ctx) {
        logInfo("Found HTML comment");
        return super.visitHtmlcommentlabel(ctx);
    }

    // ================ معالجة النهايات ================

    @Override
    public ASTNode visitChildren(org.antlr.v4.runtime.tree.RuleNode node) {
        // نستخدم زيارة الأطفال العادية
        try {
            return super.visitChildren(node);
        } catch (Exception e) {
            // تسجيل الخطأ دون إيقاف التنفيذ
            if (node instanceof ParserRuleContext) {
                ParserRuleContext ctx = (ParserRuleContext) node;
                addError("Error visiting children: " + e.getMessage(),
                        ctx.getStart().getLine(),
                        ctx.getStart().getCharPositionInLine());
            }
            return null;
        }
    }

    /**
     * تنظيف الذاكرة وإغلاق النطاقات المفتوحة
     */
    public void cleanup() {
        // إغلاق أي نطاقات مفتوحة
        while (!elementStack.isEmpty()) {
            elementStack.pop();
        }
        while (!styleAttrStack.isEmpty()) {
            styleAttrStack.pop();
        }
        while (!jinjaBlockStack.isEmpty()) {
            jinjaBlockStack.pop();
        }

        // إغلاق جميع النطاقات في جدول الرموز
        symbolTable.closeAllScopes();

        logInfo("Cleanup completed. Open scopes: " + symbolTable.getScopeDepth());
    }
}