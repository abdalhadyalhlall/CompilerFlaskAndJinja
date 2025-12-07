// Generated from C:/D/CompilerFlaskAndJinja/src/antlr/HTMLCSSJINJA_parser.g4 by ANTLR 4.13.2
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HTMLCSSJINJA_parser}.
 */
public interface HTMLCSSJINJA_parserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(HTMLCSSJINJA_parser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(HTMLCSSJINJA_parser.DocumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code htmlelementlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#html}.
	 * @param ctx the parse tree
	 */
	void enterHtmlelementlabel(HTMLCSSJINJA_parser.HtmlelementlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code htmlelementlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#html}.
	 * @param ctx the parse tree
	 */
	void exitHtmlelementlabel(HTMLCSSJINJA_parser.HtmlelementlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code htmlcommentlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#html}.
	 * @param ctx the parse tree
	 */
	void enterHtmlcommentlabel(HTMLCSSJINJA_parser.HtmlcommentlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code htmlcommentlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#html}.
	 * @param ctx the parse tree
	 */
	void exitHtmlcommentlabel(HTMLCSSJINJA_parser.HtmlcommentlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doctipelabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#html}.
	 * @param ctx the parse tree
	 */
	void enterDoctipelabel(HTMLCSSJINJA_parser.DoctipelabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doctipelabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#html}.
	 * @param ctx the parse tree
	 */
	void exitDoctipelabel(HTMLCSSJINJA_parser.DoctipelabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jinjaElementlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#html}.
	 * @param ctx the parse tree
	 */
	void enterJinjaElementlabel(HTMLCSSJINJA_parser.JinjaElementlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jinjaElementlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#html}.
	 * @param ctx the parse tree
	 */
	void exitJinjaElementlabel(HTMLCSSJINJA_parser.JinjaElementlabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#doctipe}.
	 * @param ctx the parse tree
	 */
	void enterDoctipe(HTMLCSSJINJA_parser.DoctipeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#doctipe}.
	 * @param ctx the parse tree
	 */
	void exitDoctipe(HTMLCSSJINJA_parser.DoctipeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#htmlelement}.
	 * @param ctx the parse tree
	 */
	void enterHtmlelement(HTMLCSSJINJA_parser.HtmlelementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#htmlelement}.
	 * @param ctx the parse tree
	 */
	void exitHtmlelement(HTMLCSSJINJA_parser.HtmlelementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#attruputehtml}.
	 * @param ctx the parse tree
	 */
	void enterAttruputehtml(HTMLCSSJINJA_parser.AttruputehtmlContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#attruputehtml}.
	 * @param ctx the parse tree
	 */
	void exitAttruputehtml(HTMLCSSJINJA_parser.AttruputehtmlContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#attributehtml}.
	 * @param ctx the parse tree
	 */
	void enterAttributehtml(HTMLCSSJINJA_parser.AttributehtmlContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#attributehtml}.
	 * @param ctx the parse tree
	 */
	void exitAttributehtml(HTMLCSSJINJA_parser.AttributehtmlContext ctx);
	/**
	 * Enter a parse tree produced by the {@code headlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contenthtml}.
	 * @param ctx the parse tree
	 */
	void enterHeadlabel(HTMLCSSJINJA_parser.HeadlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code headlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contenthtml}.
	 * @param ctx the parse tree
	 */
	void exitHeadlabel(HTMLCSSJINJA_parser.HeadlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bodylabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contenthtml}.
	 * @param ctx the parse tree
	 */
	void enterBodylabel(HTMLCSSJINJA_parser.BodylabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bodylabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contenthtml}.
	 * @param ctx the parse tree
	 */
	void exitBodylabel(HTMLCSSJINJA_parser.BodylabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#text}.
	 * @param ctx the parse tree
	 */
	void enterText(HTMLCSSJINJA_parser.TextContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#text}.
	 * @param ctx the parse tree
	 */
	void exitText(HTMLCSSJINJA_parser.TextContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#head}.
	 * @param ctx the parse tree
	 */
	void enterHead(HTMLCSSJINJA_parser.HeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#head}.
	 * @param ctx the parse tree
	 */
	void exitHead(HTMLCSSJINJA_parser.HeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(HTMLCSSJINJA_parser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(HTMLCSSJINJA_parser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code normalTagElementheadlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contenthead}.
	 * @param ctx the parse tree
	 */
	void enterNormalTagElementheadlabel(HTMLCSSJINJA_parser.NormalTagElementheadlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code normalTagElementheadlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contenthead}.
	 * @param ctx the parse tree
	 */
	void exitNormalTagElementheadlabel(HTMLCSSJINJA_parser.NormalTagElementheadlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selfClosingTagElementheadlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contenthead}.
	 * @param ctx the parse tree
	 */
	void enterSelfClosingTagElementheadlabel(HTMLCSSJINJA_parser.SelfClosingTagElementheadlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selfClosingTagElementheadlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contenthead}.
	 * @param ctx the parse tree
	 */
	void exitSelfClosingTagElementheadlabel(HTMLCSSJINJA_parser.SelfClosingTagElementheadlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jinjaElementlabel1}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contenthead}.
	 * @param ctx the parse tree
	 */
	void enterJinjaElementlabel1(HTMLCSSJINJA_parser.JinjaElementlabel1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code jinjaElementlabel1}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contenthead}.
	 * @param ctx the parse tree
	 */
	void exitJinjaElementlabel1(HTMLCSSJINJA_parser.JinjaElementlabel1Context ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#normalTagElementhead}.
	 * @param ctx the parse tree
	 */
	void enterNormalTagElementhead(HTMLCSSJINJA_parser.NormalTagElementheadContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#normalTagElementhead}.
	 * @param ctx the parse tree
	 */
	void exitNormalTagElementhead(HTMLCSSJINJA_parser.NormalTagElementheadContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#tagsheadnormal}.
	 * @param ctx the parse tree
	 */
	void enterTagsheadnormal(HTMLCSSJINJA_parser.TagsheadnormalContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#tagsheadnormal}.
	 * @param ctx the parse tree
	 */
	void exitTagsheadnormal(HTMLCSSJINJA_parser.TagsheadnormalContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#selfClosingTagElementhead}.
	 * @param ctx the parse tree
	 */
	void enterSelfClosingTagElementhead(HTMLCSSJINJA_parser.SelfClosingTagElementheadContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#selfClosingTagElementhead}.
	 * @param ctx the parse tree
	 */
	void exitSelfClosingTagElementhead(HTMLCSSJINJA_parser.SelfClosingTagElementheadContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#tagsheadself}.
	 * @param ctx the parse tree
	 */
	void enterTagsheadself(HTMLCSSJINJA_parser.TagsheadselfContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#tagsheadself}.
	 * @param ctx the parse tree
	 */
	void exitTagsheadself(HTMLCSSJINJA_parser.TagsheadselfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code normalTagElementbodylabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contentbody}.
	 * @param ctx the parse tree
	 */
	void enterNormalTagElementbodylabel(HTMLCSSJINJA_parser.NormalTagElementbodylabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code normalTagElementbodylabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contentbody}.
	 * @param ctx the parse tree
	 */
	void exitNormalTagElementbodylabel(HTMLCSSJINJA_parser.NormalTagElementbodylabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selfClosingTagElementbodylabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contentbody}.
	 * @param ctx the parse tree
	 */
	void enterSelfClosingTagElementbodylabel(HTMLCSSJINJA_parser.SelfClosingTagElementbodylabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selfClosingTagElementbodylabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contentbody}.
	 * @param ctx the parse tree
	 */
	void exitSelfClosingTagElementbodylabel(HTMLCSSJINJA_parser.SelfClosingTagElementbodylabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jinjaElementlabel2}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contentbody}.
	 * @param ctx the parse tree
	 */
	void enterJinjaElementlabel2(HTMLCSSJINJA_parser.JinjaElementlabel2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code jinjaElementlabel2}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contentbody}.
	 * @param ctx the parse tree
	 */
	void exitJinjaElementlabel2(HTMLCSSJINJA_parser.JinjaElementlabel2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code textlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contentbody}.
	 * @param ctx the parse tree
	 */
	void enterTextlabel(HTMLCSSJINJA_parser.TextlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#contentbody}.
	 * @param ctx the parse tree
	 */
	void exitTextlabel(HTMLCSSJINJA_parser.TextlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code normal_Tag_Element_body}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#normalTagElementbody}.
	 * @param ctx the parse tree
	 */
	void enterNormal_Tag_Element_body(HTMLCSSJINJA_parser.Normal_Tag_Element_bodyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code normal_Tag_Element_body}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#normalTagElementbody}.
	 * @param ctx the parse tree
	 */
	void exitNormal_Tag_Element_body(HTMLCSSJINJA_parser.Normal_Tag_Element_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#attribute_style}.
	 * @param ctx the parse tree
	 */
	void enterAttribute_style(HTMLCSSJINJA_parser.Attribute_styleContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#attribute_style}.
	 * @param ctx the parse tree
	 */
	void exitAttribute_style(HTMLCSSJINJA_parser.Attribute_styleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code colorlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void enterColorlabel(HTMLCSSJINJA_parser.ColorlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code colorlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void exitColorlabel(HTMLCSSJINJA_parser.ColorlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lengthlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void enterLengthlabel(HTMLCSSJINJA_parser.LengthlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lengthlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void exitLengthlabel(HTMLCSSJINJA_parser.LengthlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code urllabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void enterUrllabel(HTMLCSSJINJA_parser.UrllabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code urllabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void exitUrllabel(HTMLCSSJINJA_parser.UrllabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code repeatlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void enterRepeatlabel(HTMLCSSJINJA_parser.RepeatlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code repeatlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void exitRepeatlabel(HTMLCSSJINJA_parser.RepeatlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code positionlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void enterPositionlabel(HTMLCSSJINJA_parser.PositionlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code positionlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void exitPositionlabel(HTMLCSSJINJA_parser.PositionlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code border_stylelabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void enterBorder_stylelabel(HTMLCSSJINJA_parser.Border_stylelabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code border_stylelabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void exitBorder_stylelabel(HTMLCSSJINJA_parser.Border_stylelabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code border_widthlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void enterBorder_widthlabel(HTMLCSSJINJA_parser.Border_widthlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code border_widthlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void exitBorder_widthlabel(HTMLCSSJINJA_parser.Border_widthlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code borderlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void enterBorderlabel(HTMLCSSJINJA_parser.BorderlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code borderlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void exitBorderlabel(HTMLCSSJINJA_parser.BorderlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fontlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void enterFontlabel(HTMLCSSJINJA_parser.FontlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fontlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void exitFontlabel(HTMLCSSJINJA_parser.FontlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textcsslabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void enterTextcsslabel(HTMLCSSJINJA_parser.TextcsslabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textcsslabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void exitTextcsslabel(HTMLCSSJINJA_parser.TextcsslabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code effectlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void enterEffectlabel(HTMLCSSJINJA_parser.EffectlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code effectlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void exitEffectlabel(HTMLCSSJINJA_parser.EffectlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code layoutlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void enterLayoutlabel(HTMLCSSJINJA_parser.LayoutlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code layoutlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void exitLayoutlabel(HTMLCSSJINJA_parser.LayoutlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code offsetlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void enterOffsetlabel(HTMLCSSJINJA_parser.OffsetlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code offsetlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#stylename}.
	 * @param ctx the parse tree
	 */
	void exitOffsetlabel(HTMLCSSJINJA_parser.OffsetlabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#color}.
	 * @param ctx the parse tree
	 */
	void enterColor(HTMLCSSJINJA_parser.ColorContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#color}.
	 * @param ctx the parse tree
	 */
	void exitColor(HTMLCSSJINJA_parser.ColorContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#length}.
	 * @param ctx the parse tree
	 */
	void enterLength(HTMLCSSJINJA_parser.LengthContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#length}.
	 * @param ctx the parse tree
	 */
	void exitLength(HTMLCSSJINJA_parser.LengthContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#url}.
	 * @param ctx the parse tree
	 */
	void enterUrl(HTMLCSSJINJA_parser.UrlContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#url}.
	 * @param ctx the parse tree
	 */
	void exitUrl(HTMLCSSJINJA_parser.UrlContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#repeat}.
	 * @param ctx the parse tree
	 */
	void enterRepeat(HTMLCSSJINJA_parser.RepeatContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#repeat}.
	 * @param ctx the parse tree
	 */
	void exitRepeat(HTMLCSSJINJA_parser.RepeatContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#position}.
	 * @param ctx the parse tree
	 */
	void enterPosition(HTMLCSSJINJA_parser.PositionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#position}.
	 * @param ctx the parse tree
	 */
	void exitPosition(HTMLCSSJINJA_parser.PositionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#offset}.
	 * @param ctx the parse tree
	 */
	void enterOffset(HTMLCSSJINJA_parser.OffsetContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#offset}.
	 * @param ctx the parse tree
	 */
	void exitOffset(HTMLCSSJINJA_parser.OffsetContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#border_style}.
	 * @param ctx the parse tree
	 */
	void enterBorder_style(HTMLCSSJINJA_parser.Border_styleContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#border_style}.
	 * @param ctx the parse tree
	 */
	void exitBorder_style(HTMLCSSJINJA_parser.Border_styleContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#border_width}.
	 * @param ctx the parse tree
	 */
	void enterBorder_width(HTMLCSSJINJA_parser.Border_widthContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#border_width}.
	 * @param ctx the parse tree
	 */
	void exitBorder_width(HTMLCSSJINJA_parser.Border_widthContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#border}.
	 * @param ctx the parse tree
	 */
	void enterBorder(HTMLCSSJINJA_parser.BorderContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#border}.
	 * @param ctx the parse tree
	 */
	void exitBorder(HTMLCSSJINJA_parser.BorderContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#font}.
	 * @param ctx the parse tree
	 */
	void enterFont(HTMLCSSJINJA_parser.FontContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#font}.
	 * @param ctx the parse tree
	 */
	void exitFont(HTMLCSSJINJA_parser.FontContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#textcss}.
	 * @param ctx the parse tree
	 */
	void enterTextcss(HTMLCSSJINJA_parser.TextcssContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#textcss}.
	 * @param ctx the parse tree
	 */
	void exitTextcss(HTMLCSSJINJA_parser.TextcssContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#effect}.
	 * @param ctx the parse tree
	 */
	void enterEffect(HTMLCSSJINJA_parser.EffectContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#effect}.
	 * @param ctx the parse tree
	 */
	void exitEffect(HTMLCSSJINJA_parser.EffectContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#layout}.
	 * @param ctx the parse tree
	 */
	void enterLayout(HTMLCSSJINJA_parser.LayoutContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#layout}.
	 * @param ctx the parse tree
	 */
	void exitLayout(HTMLCSSJINJA_parser.LayoutContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#tagsbodynamenormal}.
	 * @param ctx the parse tree
	 */
	void enterTagsbodynamenormal(HTMLCSSJINJA_parser.TagsbodynamenormalContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#tagsbodynamenormal}.
	 * @param ctx the parse tree
	 */
	void exitTagsbodynamenormal(HTMLCSSJINJA_parser.TagsbodynamenormalContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#selfClosingTagElementbody}.
	 * @param ctx the parse tree
	 */
	void enterSelfClosingTagElementbody(HTMLCSSJINJA_parser.SelfClosingTagElementbodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#selfClosingTagElementbody}.
	 * @param ctx the parse tree
	 */
	void exitSelfClosingTagElementbody(HTMLCSSJINJA_parser.SelfClosingTagElementbodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#tagsbodynameself}.
	 * @param ctx the parse tree
	 */
	void enterTagsbodynameself(HTMLCSSJINJA_parser.TagsbodynameselfContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#tagsbodynameself}.
	 * @param ctx the parse tree
	 */
	void exitTagsbodynameself(HTMLCSSJINJA_parser.TagsbodynameselfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code attribute_stylelabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#attributebody}.
	 * @param ctx the parse tree
	 */
	void enterAttribute_stylelabel(HTMLCSSJINJA_parser.Attribute_stylelabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code attribute_stylelabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#attributebody}.
	 * @param ctx the parse tree
	 */
	void exitAttribute_stylelabel(HTMLCSSJINJA_parser.Attribute_stylelabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code attributenamebodylabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#attributebody}.
	 * @param ctx the parse tree
	 */
	void enterAttributenamebodylabel(HTMLCSSJINJA_parser.AttributenamebodylabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code attributenamebodylabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#attributebody}.
	 * @param ctx the parse tree
	 */
	void exitAttributenamebodylabel(HTMLCSSJINJA_parser.AttributenamebodylabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code attributenamebodywithoutvaluelabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#attributebody}.
	 * @param ctx the parse tree
	 */
	void enterAttributenamebodywithoutvaluelabel(HTMLCSSJINJA_parser.AttributenamebodywithoutvaluelabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code attributenamebodywithoutvaluelabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#attributebody}.
	 * @param ctx the parse tree
	 */
	void exitAttributenamebodywithoutvaluelabel(HTMLCSSJINJA_parser.AttributenamebodywithoutvaluelabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#attributenamebodywithoutvalue}.
	 * @param ctx the parse tree
	 */
	void enterAttributenamebodywithoutvalue(HTMLCSSJINJA_parser.AttributenamebodywithoutvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#attributenamebodywithoutvalue}.
	 * @param ctx the parse tree
	 */
	void exitAttributenamebodywithoutvalue(HTMLCSSJINJA_parser.AttributenamebodywithoutvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#attributenamebody}.
	 * @param ctx the parse tree
	 */
	void enterAttributenamebody(HTMLCSSJINJA_parser.AttributenamebodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#attributenamebody}.
	 * @param ctx the parse tree
	 */
	void exitAttributenamebody(HTMLCSSJINJA_parser.AttributenamebodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#attributehead}.
	 * @param ctx the parse tree
	 */
	void enterAttributehead(HTMLCSSJINJA_parser.AttributeheadContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#attributehead}.
	 * @param ctx the parse tree
	 */
	void exitAttributehead(HTMLCSSJINJA_parser.AttributeheadContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#attributenamehead}.
	 * @param ctx the parse tree
	 */
	void enterAttributenamehead(HTMLCSSJINJA_parser.AttributenameheadContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#attributenamehead}.
	 * @param ctx the parse tree
	 */
	void exitAttributenamehead(HTMLCSSJINJA_parser.AttributenameheadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jinjaExprlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaElement}.
	 * @param ctx the parse tree
	 */
	void enterJinjaExprlabel(HTMLCSSJINJA_parser.JinjaExprlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jinjaExprlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaElement}.
	 * @param ctx the parse tree
	 */
	void exitJinjaExprlabel(HTMLCSSJINJA_parser.JinjaExprlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jinjaStmtlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaElement}.
	 * @param ctx the parse tree
	 */
	void enterJinjaStmtlabel(HTMLCSSJINJA_parser.JinjaStmtlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jinjaStmtlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaElement}.
	 * @param ctx the parse tree
	 */
	void exitJinjaStmtlabel(HTMLCSSJINJA_parser.JinjaStmtlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jinjaCommentlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaElement}.
	 * @param ctx the parse tree
	 */
	void enterJinjaCommentlabel(HTMLCSSJINJA_parser.JinjaCommentlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jinjaCommentlabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaElement}.
	 * @param ctx the parse tree
	 */
	void exitJinjaCommentlabel(HTMLCSSJINJA_parser.JinjaCommentlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jinjaIfBlocklabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaElement}.
	 * @param ctx the parse tree
	 */
	void enterJinjaIfBlocklabel(HTMLCSSJINJA_parser.JinjaIfBlocklabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jinjaIfBlocklabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaElement}.
	 * @param ctx the parse tree
	 */
	void exitJinjaIfBlocklabel(HTMLCSSJINJA_parser.JinjaIfBlocklabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jinjaForBlocklabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaElement}.
	 * @param ctx the parse tree
	 */
	void enterJinjaForBlocklabel(HTMLCSSJINJA_parser.JinjaForBlocklabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jinjaForBlocklabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaElement}.
	 * @param ctx the parse tree
	 */
	void exitJinjaForBlocklabel(HTMLCSSJINJA_parser.JinjaForBlocklabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#jinjaExpr}.
	 * @param ctx the parse tree
	 */
	void enterJinjaExpr(HTMLCSSJINJA_parser.JinjaExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#jinjaExpr}.
	 * @param ctx the parse tree
	 */
	void exitJinjaExpr(HTMLCSSJINJA_parser.JinjaExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#jinjaExprContent}.
	 * @param ctx the parse tree
	 */
	void enterJinjaExprContent(HTMLCSSJINJA_parser.JinjaExprContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#jinjaExprContent}.
	 * @param ctx the parse tree
	 */
	void exitJinjaExprContent(HTMLCSSJINJA_parser.JinjaExprContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#jinjaBlockContent}.
	 * @param ctx the parse tree
	 */
	void enterJinjaBlockContent(HTMLCSSJINJA_parser.JinjaBlockContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#jinjaBlockContent}.
	 * @param ctx the parse tree
	 */
	void exitJinjaBlockContent(HTMLCSSJINJA_parser.JinjaBlockContentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jinjaIfBlockLabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaIfBlock}.
	 * @param ctx the parse tree
	 */
	void enterJinjaIfBlockLabel(HTMLCSSJINJA_parser.JinjaIfBlockLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jinjaIfBlockLabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaIfBlock}.
	 * @param ctx the parse tree
	 */
	void exitJinjaIfBlockLabel(HTMLCSSJINJA_parser.JinjaIfBlockLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jinjaForBlockLabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaForBlock}.
	 * @param ctx the parse tree
	 */
	void enterJinjaForBlockLabel(HTMLCSSJINJA_parser.JinjaForBlockLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jinjaForBlockLabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaForBlock}.
	 * @param ctx the parse tree
	 */
	void exitJinjaForBlockLabel(HTMLCSSJINJA_parser.JinjaForBlockLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLCSSJINJA_parser#jinjaStmtArgument}.
	 * @param ctx the parse tree
	 */
	void enterJinjaStmtArgument(HTMLCSSJINJA_parser.JinjaStmtArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLCSSJINJA_parser#jinjaStmtArgument}.
	 * @param ctx the parse tree
	 */
	void exitJinjaStmtArgument(HTMLCSSJINJA_parser.JinjaStmtArgumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jinjaStmtLabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaStmt}.
	 * @param ctx the parse tree
	 */
	void enterJinjaStmtLabel(HTMLCSSJINJA_parser.JinjaStmtLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jinjaStmtLabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaStmt}.
	 * @param ctx the parse tree
	 */
	void exitJinjaStmtLabel(HTMLCSSJINJA_parser.JinjaStmtLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jinjaCommentLabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaComment}.
	 * @param ctx the parse tree
	 */
	void enterJinjaCommentLabel(HTMLCSSJINJA_parser.JinjaCommentLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jinjaCommentLabel}
	 * labeled alternative in {@link HTMLCSSJINJA_parser#jinjaComment}.
	 * @param ctx the parse tree
	 */
	void exitJinjaCommentLabel(HTMLCSSJINJA_parser.JinjaCommentLabelContext ctx);
}