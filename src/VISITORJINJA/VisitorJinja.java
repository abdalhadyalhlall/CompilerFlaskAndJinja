package VISITORJINJA;

import AST_HTMLCSSJINJA.*;

import antlrJinja.HTMLCSSJINJA_parser;
import antlrJinja.HTMLCSSJINJA_parserBaseVisitor;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.ArrayList;
import java.util.List;

public class VisitorJinja extends HTMLCSSJINJA_parserBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitDocumentLabel(HTMLCSSJINJA_parser.DocumentLabelContext ctx) {
        DocumentNode document = new DocumentNode(ctx.getStart().getLine());

        // زيارة جميع العقد الفرعية
        for (ParseTree child : ctx.children) {
            ASTNode childNode = visit(child);
            if (childNode != null) {
                document.addChild(childNode);
            }
        }

        return document;
    }

    @Override
    public ASTNode visitHtmlelementlabel(HTMLCSSJINJA_parser.HtmlelementlabelContext ctx) {
        return visit(ctx.htmlelement());
    }

    @Override
    public ASTNode visitDoctipelabel(HTMLCSSJINJA_parser.DoctipelabelContext ctx) {
        return visit(ctx.doctipe());
    }

    @Override
    public ASTNode visitDoctipe(HTMLCSSJINJA_parser.DoctipeContext ctx) {
        return new DoctypeNode(ctx.getStart().getLine());
    }

    @Override
    public ASTNode visitHtmlelement(HTMLCSSJINJA_parser.HtmlelementContext ctx) {
        HtmlElementNode htmlElement = new HtmlElementNode(
                ctx.getStart().getLine(),
                "html",
                HtmlElementNode.ElementType.HTML
        );

        // زيارة السمات
        for (HTMLCSSJINJA_parser.AttruputehtmlContext attrCtx : ctx.attruputehtml()) {
            ASTNode attrNode = visit(attrCtx);
            if (attrNode != null) {
                htmlElement.addChild(attrNode);
            }
        }

        // زيارة المحتوى (head و body)
        for (HTMLCSSJINJA_parser.ContenthtmlContext contentCtx : ctx.contenthtml()) {
            ASTNode contentNode = visit(contentCtx);
            if (contentNode != null) {
                htmlElement.addChild(contentNode);
            }
        }

        return htmlElement;
    }

    @Override
    public ASTNode visitLangAttributeLabel(HTMLCSSJINJA_parser.LangAttributeLabelContext ctx) {
        String value = "";
        if (ctx.VALUE_LANG_EN() != null) {
            value = ctx.VALUE_LANG_EN().getText();
        } else if (ctx.VALUE_LANG_AR() != null) {
            value = ctx.VALUE_LANG_AR().getText();
        }

        return new AttributeNode(
                ctx.getStart().getLine(),
                "lang",
                value,
                AttributeNode.AttributeType.LANG
        );
    }

    @Override
    public ASTNode visitHeadlabel(HTMLCSSJINJA_parser.HeadlabelContext ctx) {
        return visit(ctx.head());
    }

    @Override
    public ASTNode visitBodylabel(HTMLCSSJINJA_parser.BodylabelContext ctx) {
        return visit(ctx.body());
    }

    @Override
    public ASTNode visitHead(HTMLCSSJINJA_parser.HeadContext ctx) {
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

        return headElement;
    }

    @Override
    public ASTNode visitBody(HTMLCSSJINJA_parser.BodyContext ctx) {
        HtmlElementNode bodyElement = new HtmlElementNode(
                ctx.getStart().getLine(),
                "body",
                HtmlElementNode.ElementType.BODY
        );

        // زيارة السمات
        for (HTMLCSSJINJA_parser.AttributebodyContext attrCtx : ctx.attributebody()) {
            ASTNode attrNode = visit(attrCtx);
            if (attrNode != null) {
                bodyElement.addChild(attrNode);
            }
        }

        // زيارة المحتوى
        for (HTMLCSSJINJA_parser.ContentbodyContext contentCtx : ctx.contentbody()) {
            ASTNode childNode = visit(contentCtx);
            if (childNode != null) {
                bodyElement.addChild(childNode);
            }
        }

        return bodyElement;
    }

    @Override
    public ASTNode visitNormalTagElementheadlabel(HTMLCSSJINJA_parser.NormalTagElementheadlabelContext ctx) {
        return visit(ctx.normalTagElementhead());
    }

    @Override
    public ASTNode visitSelfClosingTagElementheadlabel(HTMLCSSJINJA_parser.SelfClosingTagElementheadlabelContext ctx) {
        return visit(ctx.selfClosingTagElementhead());
    }

    @Override
    public ASTNode visitNormaltaghead(HTMLCSSJINJA_parser.NormaltagheadContext ctx) {
        String tagName = ctx.tagsheadnormal(0).getText();
//        String tagName = "TEST";
        HtmlElementNode element = new HtmlElementNode(
                ctx.getStart().getLine(),
                tagName,
                HtmlElementNode.ElementType.NORMAL
        );

        // زيارة السمات
        for (HTMLCSSJINJA_parser.AttributeheadContext attrCtx : ctx.attributehead()) {
            ASTNode attrNode = visit(attrCtx);
            if (attrNode != null) {
                element.addChild(attrNode);
            }
        }

        // زيارة النصوص
        if (ctx.text() != null) {
            for (HTMLCSSJINJA_parser.TextContext textCtx : ctx.text()) {
                ASTNode textNode = visit(textCtx);
                if (textNode != null) {
                    element.addChild(textNode);
                }
            }
        }

        return element;
    }

    @Override
    public ASTNode visitSelfclostaglabel(HTMLCSSJINJA_parser.SelfclostaglabelContext ctx) {
        String tagName = ctx.tagsheadself().getText();
        HtmlElementNode element = new HtmlElementNode(
                ctx.getStart().getLine(),
                tagName,
                HtmlElementNode.ElementType.SELF_CLOSING
        );

        // زيارة السمات
        for (HTMLCSSJINJA_parser.AttributeheadContext attrCtx : ctx.attributehead()) {
            ASTNode attrNode = visit(attrCtx);
            if (attrNode != null) {
                element.addChild(attrNode);
            }
        }

        return element;
    }

    @Override
    public ASTNode visitNormalTagElementbodylabel(HTMLCSSJINJA_parser.NormalTagElementbodylabelContext ctx) {
        return visit(ctx.normalTagElementbody());
    }

    @Override
    public ASTNode visitSelfClosingTagElementbodylabel(HTMLCSSJINJA_parser.SelfClosingTagElementbodylabelContext ctx) {
        return visit(ctx.selfClosingTagElementbody());
    }

    @Override
    public ASTNode visitTextlabel(HTMLCSSJINJA_parser.TextlabelContext ctx) {
        Token token = ctx.TEXT().getSymbol();
        return new TextNode(
                token.getLine(),
                ctx.TEXT().getText(),
                TextNode.TextContext.HTML
        );
    }

    @Override
    public ASTNode visitNormal_Tag_Element_body(HTMLCSSJINJA_parser.Normal_Tag_Element_bodyContext ctx) {
        String tagName = ctx.tagsbodynamenormal(0).getText();
//        String tagName = "TEST";
        HtmlElementNode element = new HtmlElementNode(
                ctx.getStart().getLine(),
                tagName,
                HtmlElementNode.ElementType.NORMAL
        );

        // زيارة السمات
        for (HTMLCSSJINJA_parser.AttributebodyContext attrCtx : ctx.attributebody()) {
            ASTNode attrNode = visit(attrCtx);
            if (attrNode != null) {
                element.addChild(attrNode);
            }
        }

        // زيارة المحتوى
        for (HTMLCSSJINJA_parser.ContentbodyContext contentCtx : ctx.contentbody()) {
            ASTNode childNode = visit(contentCtx);
            if (childNode != null) {
                element.addChild(childNode);
            }
        }

        return element;
    }

    @Override
    public ASTNode visitSelfClosingTagElementbody(HTMLCSSJINJA_parser.SelfClosingTagElementbodyContext ctx) {
        String tagName = ctx.tagsbodynameself().getText();
        return new HtmlElementNode(
                ctx.getStart().getLine(),
                tagName,
                HtmlElementNode.ElementType.SELF_CLOSING
        );
    }

    @Override
    public ASTNode visitAttribute_stylelabel(HTMLCSSJINJA_parser.Attribute_stylelabelContext ctx) {
        return visit(ctx.attribute_style());
    }

    @Override
    public ASTNode visitStylelabel(HTMLCSSJINJA_parser.StylelabelContext ctx) {
        StyleAttributeNode styleAttr = new StyleAttributeNode(ctx.getStart().getLine());

        // زيارة جميع قواعد CSS
        for (HTMLCSSJINJA_parser.StylenameContext styleCtx : ctx.stylename()) {
            ASTNode ruleNode = visit(styleCtx);
            if (ruleNode instanceof CssRuleNode) {
                styleAttr.addCssRule((CssRuleNode) ruleNode);
            }
        }

        return styleAttr;
    }

    @Override
    public ASTNode visitColorLabel(HTMLCSSJINJA_parser.ColorLabelContext ctx) {
        return visit(ctx.color());
    }

    @Override
    public ASTNode visitLengthLabel(HTMLCSSJINJA_parser.LengthLabelContext ctx) {
        return visit(ctx.length());
    }

    @Override
    public ASTNode visitUrlLabel(HTMLCSSJINJA_parser.UrlLabelContext ctx) {
        return visit(ctx.url());
    }

    @Override
    public ASTNode visitRepeatLabel(HTMLCSSJINJA_parser.RepeatLabelContext ctx) {
        return visit(ctx.repeat());
    }

    @Override
    public ASTNode visitPositionLabel(HTMLCSSJINJA_parser.PositionLabelContext ctx) {
        return visit(ctx.position());
    }

    @Override
    public ASTNode visitBorderStyleLabel(HTMLCSSJINJA_parser.BorderStyleLabelContext ctx) {
        return visit(ctx.border_style());
    }

    @Override
    public ASTNode visitBorderWidthLabel(HTMLCSSJINJA_parser.BorderWidthLabelContext ctx) {
        return visit(ctx.border_width());
    }

    @Override
    public ASTNode visitBorderLabel(HTMLCSSJINJA_parser.BorderLabelContext ctx) {
        return visit(ctx.border());
    }

    @Override
    public ASTNode visitFontLabel(HTMLCSSJINJA_parser.FontLabelContext ctx) {
        return visit(ctx.font());
    }

    @Override
    public ASTNode visitTextCssLabel(HTMLCSSJINJA_parser.TextCssLabelContext ctx) {
        return visit(ctx.textcss());
    }

    @Override
    public ASTNode visitEffectLabel(HTMLCSSJINJA_parser.EffectLabelContext ctx) {
        return visit(ctx.effect());
    }

    @Override
    public ASTNode visitLayoutLabel(HTMLCSSJINJA_parser.LayoutLabelContext ctx) {
        return visit(ctx.layout());
    }

    @Override
    public ASTNode visitOffsetLabel(HTMLCSSJINJA_parser.OffsetLabelContext ctx) {
        return visit(ctx.offset());
    }

    @Override
    public ASTNode visitColor(HTMLCSSJINJA_parser.ColorContext ctx) {
        String property = ctx.CSS_ATTR_COLOR().getText();
        String value = ctx.CSS_COLOR_VALUE().getText();

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

        // جمع جميع القيم
        StringBuilder valueBuilder = new StringBuilder();
        for (TerminalNode valueNode : ctx.CSS_SHORTHAND_VALUE()) {
            if (valueBuilder.length() > 0) {
                valueBuilder.append(" ");
            }
            valueBuilder.append(valueNode.getText());
        }

        return new CssRuleNode(
                ctx.getStart().getLine(),
                property,
                valueBuilder.toString(),
                CssRuleNode.RuleType.LENGTH
        );
    }

    @Override
    public ASTNode visitUrl(HTMLCSSJINJA_parser.UrlContext ctx) {
        String property = ctx.CSS_URL_PROPERTY().getText();
        String value = ctx.CSS_URL_VALUE().getText();

        return new CssRuleNode(
                ctx.getStart().getLine(),
                property,
                value,
                CssRuleNode.RuleType.URL
        );
    }

    @Override
    public ASTNode visitRepeat(HTMLCSSJINJA_parser.RepeatContext ctx) {
        String property = ctx.CSS_REPEAT_PROPERTY().getText();
        String value = ctx.CSS_REPEAT_VALUE().getText();

        return new CssRuleNode(
                ctx.getStart().getLine(),
                property,
                value,
                CssRuleNode.RuleType.REPEAT
        );
    }

    @Override
    public ASTNode visitPosition(HTMLCSSJINJA_parser.PositionContext ctx) {
        String property = ctx.CSS_POSITION_PROPERTY().getText();

        // جمع جميع القيم
        StringBuilder valueBuilder = new StringBuilder();
        for (TerminalNode valueNode : ctx.CSS_SHORTHAND_VALUE()) {
            if (valueBuilder.length() > 0) {
                valueBuilder.append(" ");
            }
            valueBuilder.append(valueNode.getText());
        }

        return new CssRuleNode(
                ctx.getStart().getLine(),
                property,
                valueBuilder.toString(),
                CssRuleNode.RuleType.POSITION
        );
    }

    @Override
    public ASTNode visitOffset(HTMLCSSJINJA_parser.OffsetContext ctx) {
        String property = ctx.CSS_OFFSET_PROPERTY().getText();
        String value = ctx.CSS_SHORTHAND_VALUE().getText();

        return new CssRuleNode(
                ctx.getStart().getLine(),
                property,
                value,
                CssRuleNode.RuleType.OFFSET
        );
    }

    @Override
    public ASTNode visitBorder_style(HTMLCSSJINJA_parser.Border_styleContext ctx) {
        String property = ctx.CSS_BORDER_STYLE_PROPERTY().getText();
        String value = ctx.CSS_BORDER_STYLE_VALUE().getText();

        return new CssRuleNode(
                ctx.getStart().getLine(),
                property,
                value,
                CssRuleNode.RuleType.BORDER_STYLE
        );
    }

    @Override
    public ASTNode visitBorder_width(HTMLCSSJINJA_parser.Border_widthContext ctx) {
        String property = ctx.CSS_BORDER_WIDTH_PROPERTY().getText();
        String value;

        if (ctx.CSS_BORDER_WIDTH_VALUE() != null) {
            value = ctx.CSS_BORDER_WIDTH_VALUE().getText();
        } else {
            value = ctx.CSS_SHORTHAND_VALUE().getText();
        }

        return new CssRuleNode(
                ctx.getStart().getLine(),
                property,
                value,
                CssRuleNode.RuleType.BORDER_WIDTH
        );
    }

    @Override
    public ASTNode visitBorder(HTMLCSSJINJA_parser.BorderContext ctx) {
        String property = ctx.CSS_BORDER_PROPERTY().getText();

        // جمع جميع القيم
        List<String> values = new ArrayList<>();
        for (TerminalNode valueNode : ctx.CSS_SHORTHAND_VALUE()) {
            values.add(valueNode.getText());
        }
        for (TerminalNode valueNode : ctx.CSS_COLOR_VALUE()) {
            values.add(valueNode.getText());
        }

        StringBuilder valueBuilder = new StringBuilder();
        for (String val : values) {
            if (valueBuilder.length() > 0) {
                valueBuilder.append(" ");
            }
            valueBuilder.append(val);
        }

        return new CssRuleNode(
                ctx.getStart().getLine(),
                property,
                valueBuilder.toString(),
                CssRuleNode.RuleType.BORDER
        );
    }

    @Override
    public ASTNode visitFont(HTMLCSSJINJA_parser.FontContext ctx) {
        String property = ctx.CSS_FONT_PROPERTY().getText();
        String value;

        if (ctx.CSS_FONT_VALUE() != null) {
            value = ctx.CSS_FONT_VALUE().getText();
        } else {
            value = ctx.CSS_SHORTHAND_VALUE().getText();
        }

        return new CssRuleNode(
                ctx.getStart().getLine(),
                property,
                value,
                CssRuleNode.RuleType.FONT
        );
    }

    @Override
    public ASTNode visitTextcss(HTMLCSSJINJA_parser.TextcssContext ctx) {
        String property = ctx.CSS_TEXT_PROPERTY().getText();
        String value;

        if (ctx.CSS_TEXT_VALUE() != null) {
            value = ctx.CSS_TEXT_VALUE().getText();
        } else {
            value = ctx.CSS_SHORTHAND_VALUE().getText();
        }

        return new CssRuleNode(
                ctx.getStart().getLine(),
                property,
                value,
                CssRuleNode.RuleType.TEXT
        );
    }

    @Override
    public ASTNode visitEffect(HTMLCSSJINJA_parser.EffectContext ctx) {
        String property = ctx.CSS_EFFECT_PROPERTY().getText();

        // جمع جميع القيم
        List<String> values = new ArrayList<>();
        for (TerminalNode valueNode : ctx.CSS_EFFECT_VALUE()) {
            values.add(valueNode.getText());
        }
        for (TerminalNode valueNode : ctx.CSS_SHORTHAND_VALUE()) {
            values.add(valueNode.getText());
        }

        StringBuilder valueBuilder = new StringBuilder();
        for (String val : values) {
            if (valueBuilder.length() > 0) {
                valueBuilder.append(" ");
            }
            valueBuilder.append(val);
        }

        return new CssRuleNode(
                ctx.getStart().getLine(),
                property,
                valueBuilder.toString(),
                CssRuleNode.RuleType.EFFECT
        );
    }

    @Override
    public ASTNode visitLayout(HTMLCSSJINJA_parser.LayoutContext ctx) {
        String property = ctx.CSS_LAYOUT_PROPERTY().getText();

        // جمع جميع القيم
        StringBuilder valueBuilder = new StringBuilder();
        for (TerminalNode valueNode : ctx.CSS_LAYOUT_VALUE()) {
            if (valueBuilder.length() > 0) {
                valueBuilder.append(" ");
            }
            valueBuilder.append(valueNode.getText());
        }

        return new CssRuleNode(
                ctx.getStart().getLine(),
                property,
                valueBuilder.toString(),
                CssRuleNode.RuleType.LAYOUT
        );
    }

    @Override
    public ASTNode visitAttributenamebodylabel(HTMLCSSJINJA_parser.AttributenamebodylabelContext ctx) {
        String name = ctx.attributenamebody().getText();
        String value = ctx.ATTRIBUTE_VALUE().getText();

        // إزالة علامات الاقتباس إذا وجدت
        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1);
        }

        return new AttributeNode(
                ctx.getStart().getLine(),
                name,
                value,
                AttributeNode.AttributeType.NORMAL
        );
    }

    @Override
    public ASTNode visitAttributenamebodywithoutvaluelabel(HTMLCSSJINJA_parser.AttributenamebodywithoutvaluelabelContext ctx) {
        String name = ctx.attributenamebodywithoutvalue().getText();

        return new AttributeNode(
                ctx.getStart().getLine(),
                name,
                null,
                AttributeNode.AttributeType.WITHOUT_VALUE
        );
    }

    @Override
    public ASTNode visitAttributehead(HTMLCSSJINJA_parser.AttributeheadContext ctx) {
        String name = ctx.attributenamehead().getText();
        String value = ctx.ATTRIBUTE_VALUE().getText();

        // إزالة علامات الاقتباس إذا وجدت
        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1);
        }

        return new AttributeNode(
                ctx.getStart().getLine(),
                name,
                value,
                AttributeNode.AttributeType.NORMAL
        );
    }

    @Override
    public ASTNode visitJinjaElementlabel(HTMLCSSJINJA_parser.JinjaElementlabelContext ctx) {
        return visit(ctx.jinjaElement());
    }

    @Override
    public ASTNode visitJinjasecondElementlabel(HTMLCSSJINJA_parser.JinjasecondElementlabelContext ctx) {
        return visit(ctx.jinjaElement());
    }

    @Override
    public ASTNode visitJinjathirdElementlabel(HTMLCSSJINJA_parser.JinjathirdElementlabelContext ctx) {
        return visit(ctx.jinjaElement());
    }

    @Override
    public ASTNode visitJinjaExprlabel(HTMLCSSJINJA_parser.JinjaExprlabelContext ctx) {
        return visit(ctx.jinjaExpr());
    }

    @Override
    public ASTNode visitJinjaStmtlabel(HTMLCSSJINJA_parser.JinjaStmtlabelContext ctx) {
        return visit(ctx.jinjaStmt());
    }

    @Override
    public ASTNode visitJinjaCommentlabel(HTMLCSSJINJA_parser.JinjaCommentlabelContext ctx) {
        return visit(ctx.jinjaComment());
    }

    @Override
    public ASTNode visitJinjaIfBlocklabel(HTMLCSSJINJA_parser.JinjaIfBlocklabelContext ctx) {
        return visit(ctx.jinjaIfBlock());
    }

    @Override
    public ASTNode visitJinjaForBlocklabel(HTMLCSSJINJA_parser.JinjaForBlocklabelContext ctx) {
        return visit(ctx.jinjaForBlock());
    }

    @Override
    public ASTNode visitJinjaExpr(HTMLCSSJINJA_parser.JinjaExprContext ctx) {
        StringBuilder content = new StringBuilder();

        // جمع محتوى التعبير
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

        JinjaNode jinjaNode = new JinjaNode(
                ctx.getStart().getLine(),
                JinjaNode.JinjaType.EXPRESSION,
                content.toString().trim()
        );

        return jinjaNode;
    }

    @Override
    public ASTNode visitJinjaStmt(HTMLCSSJINJA_parser.JinjaStmtContext ctx) {
        StringBuilder content = new StringBuilder();

        // جمع محتوى ال statement
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

        JinjaNode jinjaNode = new JinjaNode(
                ctx.getStart().getLine(),
                JinjaNode.JinjaType.STATEMENT,
                content.toString().trim()
        );

        return jinjaNode;
    }

    @Override
    public ASTNode visitJinjaComment(HTMLCSSJINJA_parser.JinjaCommentContext ctx) {
        String content = ctx.JANJI_COMMENT().getText();
        // إزالة علامات التعليق {# و #}
        if (content.startsWith("{#") && content.endsWith("#}")) {
            content = content.substring(2, content.length() - 2).trim();
        }

        return new JinjaNode(
                ctx.getStart().getLine(),
                JinjaNode.JinjaType.COMMENT,
                content
        );
    }

    @Override
    public ASTNode visitJinjaIfBlock(HTMLCSSJINJA_parser.JinjaIfBlockContext ctx) {
        JinjaBlockNode ifBlock = new JinjaBlockNode(
                ctx.getStart().getLine(),
                JinjaBlockNode.BlockType.IF
        );

        // معالجة الكتلة الرئيسية if
        String condition = extractCondition(ctx.JINJA_STMT_IF(), ctx.jinjaStmtArgument());
        ifBlock.addConditionBlock(condition);

        // معالجة المحتوى داخل if
        for (HTMLCSSJINJA_parser.JinjaBlockContentContext contentCtx : ctx.jinjaBlockContent()) {
            ASTNode contentNode = visit(contentCtx);
            if (contentNode != null) {
                ifBlock.addContentToLastCondition(contentNode);
            }
        }

        // معالجة elif blocks
        for (int i = 0; i < ctx.JINJA_STMT_ELIF().size(); i++) {
            String elifCondition = extractCondition(null, ctx.jinjaStmtArgument());
            ifBlock.addConditionBlock(elifCondition);

            // TODO: جمع المحتوى لكل elif
        }

        // معالجة else block
        if (ctx.JINJA_STMT_ELSE() != null) {
            for (HTMLCSSJINJA_parser.JinjaBlockContentContext elseContentCtx : ctx.jinjaBlockContent()) {
                ASTNode elseNode = visit(elseContentCtx);
                if (elseNode != null) {
                    ifBlock.addElseContent(elseNode);
                }
            }
        }

        return ifBlock;
    }

    @Override
    public ASTNode visitJinjaForBlock(HTMLCSSJINJA_parser.JinjaForBlockContext ctx) {
        JinjaBlockNode forBlock = new JinjaBlockNode(
                ctx.getStart().getLine(),
                JinjaBlockNode.BlockType.FOR
        );

        // معالجة الشرط في for
        String condition = extractCondition(ctx.JINJA_STMT_FOR(), ctx.jinjaStmtArgument());
        forBlock.addConditionBlock(condition);

        // معالجة المحتوى داخل for
        for (HTMLCSSJINJA_parser.JinjaBlockContentContext contentCtx : ctx.jinjaBlockContent()) {
            ASTNode contentNode = visit(contentCtx);
            if (contentNode != null) {
                forBlock.addContentToLastCondition(contentNode);
            }
        }

        // معالجة else block
        if (ctx.JINJA_STMT_ELSE() != null) {
            for (HTMLCSSJINJA_parser.JinjaBlockContentContext elseContentCtx : ctx.jinjaBlockContent()) {
                ASTNode elseNode = visit(elseContentCtx);
                if (elseNode != null) {
                    forBlock.addElseContent(elseNode);
                }
            }
        }

        return forBlock;
    }

    @Override
    public ASTNode visitJinjaNormalTagLabel(HTMLCSSJINJA_parser.JinjaNormalTagLabelContext ctx) {
        return visit(ctx.normalTagElementbody());
    }

    @Override
    public ASTNode visitJinjaSelfClosingTagLabel(HTMLCSSJINJA_parser.JinjaSelfClosingTagLabelContext ctx) {
        return visit(ctx.selfClosingTagElementbody());
    }

    @Override
    public ASTNode visitJinjaNestedElementLabel(HTMLCSSJINJA_parser.JinjaNestedElementLabelContext ctx) {
        return visit(ctx.jinjaElement());
    }

    @Override
    public ASTNode visitJinjaTextLabel(HTMLCSSJINJA_parser.JinjaTextLabelContext ctx) {
        Token token = ctx.TEXT().getSymbol();
        return new TextNode(
                token.getLine(),
                ctx.TEXT().getText(),
                TextNode.TextContext.JINJA
        );
    }

    @Override
    public ASTNode visitHtmlcommentlabel(HTMLCSSJINJA_parser.HtmlcommentlabelContext ctx) {
        String commentText = ctx.HTML_COMMENT().getText();
        return new TextNode(
                ctx.getStart().getLine(),
                commentText,
                TextNode.TextContext.HTML
        );
    }

    @Override
    public ASTNode visitText(HTMLCSSJINJA_parser.TextContext ctx) {
        Token token = ctx.TEXT().getSymbol();
        return new TextNode(
                token.getLine(),
                ctx.TEXT().getText(),
                TextNode.TextContext.HTML
        );
    }

    // طريقة مساعدة لاستخراج الشرط من Jinja blocks
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
}