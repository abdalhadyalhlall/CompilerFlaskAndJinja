// Generated from D:/about command/Compiler/Compiler/src/antlr/ProjectParser.g4 by ANTLR 4.13.2
package antlr;
 package parser; 
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ProjectParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ProjectParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code progeRoot}
	 * labeled alternative in {@link ProjectParser#proge}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgeRoot(ProjectParser.ProgeRootContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleStatement}
	 * labeled alternative in {@link ProjectParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleStatement(ProjectParser.SimpleStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compoundStatement}
	 * labeled alternative in {@link ProjectParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(ProjectParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleStmtLabel}
	 * labeled alternative in {@link ProjectParser#simple_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleStmtLabel(ProjectParser.SimpleStmtLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStatement(ProjectParser.ExprStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code delStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelStatement(ProjectParser.DelStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code passStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPassStatement(ProjectParser.PassStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code flowStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlowStatement(ProjectParser.FlowStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code importStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportStatement(ProjectParser.ImportStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code globalStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalStatement(ProjectParser.GlobalStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprStmtLabel}
	 * labeled alternative in {@link ProjectParser#expr_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStmtLabel(ProjectParser.ExprStmtLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code annAssignLabel}
	 * labeled alternative in {@link ProjectParser#annassign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnAssignLabel(ProjectParser.AnnAssignLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code augPlusLabel}
	 * labeled alternative in {@link ProjectParser#augassign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAugPlusLabel(ProjectParser.AugPlusLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code augMinusLabel}
	 * labeled alternative in {@link ProjectParser#augassign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAugMinusLabel(ProjectParser.AugMinusLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code augStarLabel}
	 * labeled alternative in {@link ProjectParser#augassign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAugStarLabel(ProjectParser.AugStarLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code augSlashLabel}
	 * labeled alternative in {@link ProjectParser#augassign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAugSlashLabel(ProjectParser.AugSlashLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code testListStarLabel}
	 * labeled alternative in {@link ProjectParser#testlist_star_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestListStarLabel(ProjectParser.TestListStarLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compForExprLabel}
	 * labeled alternative in {@link ProjectParser#testlist_star_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompForExprLabel(ProjectParser.CompForExprLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code testLabel}
	 * labeled alternative in {@link ProjectParser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestLabel(ProjectParser.TestLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orTestLabel}
	 * labeled alternative in {@link ProjectParser#or_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrTestLabel(ProjectParser.OrTestLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andTestLabel}
	 * labeled alternative in {@link ProjectParser#and_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndTestLabel(ProjectParser.AndTestLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notTestLabel}
	 * labeled alternative in {@link ProjectParser#not_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotTestLabel(ProjectParser.NotTestLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonInNotTestLabel}
	 * labeled alternative in {@link ProjectParser#not_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonInNotTestLabel(ProjectParser.ComparisonInNotTestLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonLabel}
	 * labeled alternative in {@link ProjectParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonLabel(ProjectParser.ComparisonLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ltLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLtLabel(ProjectParser.LtLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gtLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGtLabel(ProjectParser.GtLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eqeqLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqeqLabel(ProjectParser.EqeqLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code geLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGeLabel(ProjectParser.GeLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code leLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeLabel(ProjectParser.LeLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code noteqLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoteqLabel(ProjectParser.NoteqLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInLabel(ProjectParser.InLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notInLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotInLabel(ProjectParser.NotInLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arithExprLabel}
	 * labeled alternative in {@link ProjectParser#arith_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithExprLabel(ProjectParser.ArithExprLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code termLabel}
	 * labeled alternative in {@link ProjectParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermLabel(ProjectParser.TermLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryFactorLabel}
	 * labeled alternative in {@link ProjectParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryFactorLabel(ProjectParser.UnaryFactorLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code powerLabel}
	 * labeled alternative in {@link ProjectParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowerLabel(ProjectParser.PowerLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code powerExprLabel}
	 * labeled alternative in {@link ProjectParser#power}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowerExprLabel(ProjectParser.PowerExprLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomExprLabel}
	 * labeled alternative in {@link ProjectParser#atom_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomExprLabel(ProjectParser.AtomExprLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExprLabel}
	 * labeled alternative in {@link ProjectParser#atom_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExprLabel(ProjectParser.ParenExprLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomParenLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomParenLabel(ProjectParser.AtomParenLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomListLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomListLabel(ProjectParser.AtomListLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomDictLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomDictLabel(ProjectParser.AtomDictLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomNameLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomNameLabel(ProjectParser.AtomNameLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomNumberLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomNumberLabel(ProjectParser.AtomNumberLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomStringLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomStringLabel(ProjectParser.AtomStringLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomFStringLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomFStringLabel(ProjectParser.AtomFStringLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomTrueLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomTrueLabel(ProjectParser.AtomTrueLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomFalseLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomFalseLabel(ProjectParser.AtomFalseLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomNoneLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomNoneLabel(ProjectParser.AtomNoneLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callTrailerLabel}
	 * labeled alternative in {@link ProjectParser#trailer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallTrailerLabel(ProjectParser.CallTrailerLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subscriptTrailerLabel}
	 * labeled alternative in {@link ProjectParser#trailer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptTrailerLabel(ProjectParser.SubscriptTrailerLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dotTrailerLabel}
	 * labeled alternative in {@link ProjectParser#trailer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotTrailerLabel(ProjectParser.DotTrailerLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subscriptListLabel}
	 * labeled alternative in {@link ProjectParser#subscriptlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptListLabel(ProjectParser.SubscriptListLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subscriptTestLabel}
	 * labeled alternative in {@link ProjectParser#subscript}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptTestLabel(ProjectParser.SubscriptTestLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subscriptSliceLabel}
	 * labeled alternative in {@link ProjectParser#subscript}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptSliceLabel(ProjectParser.SubscriptSliceLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code listLabel}
	 * labeled alternative in {@link ProjectParser#listmaker}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListLabel(ProjectParser.ListLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compForListLabel}
	 * labeled alternative in {@link ProjectParser#listmaker}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompForListLabel(ProjectParser.CompForListLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dictLabel}
	 * labeled alternative in {@link ProjectParser#dictmaker}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDictLabel(ProjectParser.DictLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compForDictLabel}
	 * labeled alternative in {@link ProjectParser#dictmaker}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompForDictLabel(ProjectParser.CompForDictLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compForLabel}
	 * labeled alternative in {@link ProjectParser#comp_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompForLabel(ProjectParser.CompForLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code testListCompLabel}
	 * labeled alternative in {@link ProjectParser#testlist_comp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestListCompLabel(ProjectParser.TestListCompLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compForCompLabel}
	 * labeled alternative in {@link ProjectParser#testlist_comp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompForCompLabel(ProjectParser.CompForCompLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code argListLabel}
	 * labeled alternative in {@link ProjectParser#arglist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgListLabel(ProjectParser.ArgListLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code positionalArgLabel}
	 * labeled alternative in {@link ProjectParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositionalArgLabel(ProjectParser.PositionalArgLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code keywordArgLabel}
	 * labeled alternative in {@link ProjectParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordArgLabel(ProjectParser.KeywordArgLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code delStmtLabel}
	 * labeled alternative in {@link ProjectParser#del_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelStmtLabel(ProjectParser.DelStmtLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code passStmtLabel}
	 * labeled alternative in {@link ProjectParser#pass_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPassStmtLabel(ProjectParser.PassStmtLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakLabel}
	 * labeled alternative in {@link ProjectParser#flow_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakLabel(ProjectParser.BreakLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continueLabel}
	 * labeled alternative in {@link ProjectParser#flow_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueLabel(ProjectParser.ContinueLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnLabel}
	 * labeled alternative in {@link ProjectParser#flow_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnLabel(ProjectParser.ReturnLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code importNamesLabel}
	 * labeled alternative in {@link ProjectParser#import_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportNamesLabel(ProjectParser.ImportNamesLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code importFromLabel}
	 * labeled alternative in {@link ProjectParser#import_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportFromLabel(ProjectParser.ImportFromLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dottedNameLabel}
	 * labeled alternative in {@link ProjectParser#dotted_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDottedNameLabel(ProjectParser.DottedNameLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dottedAsNamesLabel}
	 * labeled alternative in {@link ProjectParser#dotted_as_names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDottedAsNamesLabel(ProjectParser.DottedAsNamesLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dottedAsNameLabel}
	 * labeled alternative in {@link ProjectParser#dotted_as_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDottedAsNameLabel(ProjectParser.DottedAsNameLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code importAsNamesLabel}
	 * labeled alternative in {@link ProjectParser#import_as_names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportAsNamesLabel(ProjectParser.ImportAsNamesLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code importAsNameLabel}
	 * labeled alternative in {@link ProjectParser#import_as_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportAsNameLabel(ProjectParser.ImportAsNameLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code globalStmtLabel}
	 * labeled alternative in {@link ProjectParser#global_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalStmtLabel(ProjectParser.GlobalStmtLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStmtCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmtCompound(ProjectParser.IfStmtCompoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStmtCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmtCompound(ProjectParser.WhileStmtCompoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forStmtCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmtCompound(ProjectParser.ForStmtCompoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcDefCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDefCompound(ProjectParser.FuncDefCompoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classDefCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDefCompound(ProjectParser.ClassDefCompoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tryStmtCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTryStmtCompound(ProjectParser.TryStmtCompoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code withStmtCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithStmtCompound(ProjectParser.WithStmtCompoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decoratedCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecoratedCompound(ProjectParser.DecoratedCompoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decoratedLabel}
	 * labeled alternative in {@link ProjectParser#decorated}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecoratedLabel(ProjectParser.DecoratedLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decoratorLabel}
	 * labeled alternative in {@link ProjectParser#decorator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecoratorLabel(ProjectParser.DecoratorLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStmtLabel}
	 * labeled alternative in {@link ProjectParser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmtLabel(ProjectParser.IfStmtLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStmtLabel}
	 * labeled alternative in {@link ProjectParser#while_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmtLabel(ProjectParser.WhileStmtLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forStmtLabel}
	 * labeled alternative in {@link ProjectParser#for_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmtLabel(ProjectParser.ForStmtLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcDefLabel}
	 * labeled alternative in {@link ProjectParser#funcdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDefLabel(ProjectParser.FuncDefLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parametersLabel}
	 * labeled alternative in {@link ProjectParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametersLabel(ProjectParser.ParametersLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typedArgsListLabel}
	 * labeled alternative in {@link ProjectParser#typedargslist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedArgsListLabel(ProjectParser.TypedArgsListLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typedArgsStarLabel}
	 * labeled alternative in {@link ProjectParser#typedargslist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedArgsStarLabel(ProjectParser.TypedArgsStarLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tfpdefLabel}
	 * labeled alternative in {@link ProjectParser#tfpdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTfpdefLabel(ProjectParser.TfpdefLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classDefLabel}
	 * labeled alternative in {@link ProjectParser#classdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDefLabel(ProjectParser.ClassDefLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tryStmtLabel}
	 * labeled alternative in {@link ProjectParser#try_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTryStmtLabel(ProjectParser.TryStmtLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code withStmtLabel}
	 * labeled alternative in {@link ProjectParser#with_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithStmtLabel(ProjectParser.WithStmtLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code withItemLabel}
	 * labeled alternative in {@link ProjectParser#with_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithItemLabel(ProjectParser.WithItemLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleSuite}
	 * labeled alternative in {@link ProjectParser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleSuite(ProjectParser.SimpleSuiteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code indentedSuite}
	 * labeled alternative in {@link ProjectParser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndentedSuite(ProjectParser.IndentedSuiteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprListLabel}
	 * labeled alternative in {@link ProjectParser#exprlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprListLabel(ProjectParser.ExprListLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code testListLabel}
	 * labeled alternative in {@link ProjectParser#testlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestListLabel(ProjectParser.TestListLabelContext ctx);
}