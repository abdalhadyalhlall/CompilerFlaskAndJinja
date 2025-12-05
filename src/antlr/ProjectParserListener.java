// Generated from D:/about command/Compiler/Compiler/src/antlr/ProjectParser.g4 by ANTLR 4.13.2
package antlr;
 package parser; 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ProjectParser}.
 */
public interface ProjectParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code progeRoot}
	 * labeled alternative in {@link ProjectParser#proge}.
	 * @param ctx the parse tree
	 */
	void enterProgeRoot(ProjectParser.ProgeRootContext ctx);
	/**
	 * Exit a parse tree produced by the {@code progeRoot}
	 * labeled alternative in {@link ProjectParser#proge}.
	 * @param ctx the parse tree
	 */
	void exitProgeRoot(ProjectParser.ProgeRootContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleStatement}
	 * labeled alternative in {@link ProjectParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSimpleStatement(ProjectParser.SimpleStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleStatement}
	 * labeled alternative in {@link ProjectParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSimpleStatement(ProjectParser.SimpleStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compoundStatement}
	 * labeled alternative in {@link ProjectParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(ProjectParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compoundStatement}
	 * labeled alternative in {@link ProjectParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(ProjectParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleStmtLabel}
	 * labeled alternative in {@link ProjectParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimpleStmtLabel(ProjectParser.SimpleStmtLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleStmtLabel}
	 * labeled alternative in {@link ProjectParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimpleStmtLabel(ProjectParser.SimpleStmtLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void enterExprStatement(ProjectParser.ExprStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void exitExprStatement(ProjectParser.ExprStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code delStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDelStatement(ProjectParser.DelStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code delStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDelStatement(ProjectParser.DelStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code passStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void enterPassStatement(ProjectParser.PassStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code passStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void exitPassStatement(ProjectParser.PassStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code flowStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFlowStatement(ProjectParser.FlowStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code flowStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFlowStatement(ProjectParser.FlowStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code importStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void enterImportStatement(ProjectParser.ImportStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code importStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void exitImportStatement(ProjectParser.ImportStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code globalStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void enterGlobalStatement(ProjectParser.GlobalStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code globalStatement}
	 * labeled alternative in {@link ProjectParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void exitGlobalStatement(ProjectParser.GlobalStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprStmtLabel}
	 * labeled alternative in {@link ProjectParser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void enterExprStmtLabel(ProjectParser.ExprStmtLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprStmtLabel}
	 * labeled alternative in {@link ProjectParser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void exitExprStmtLabel(ProjectParser.ExprStmtLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code annAssignLabel}
	 * labeled alternative in {@link ProjectParser#annassign}.
	 * @param ctx the parse tree
	 */
	void enterAnnAssignLabel(ProjectParser.AnnAssignLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code annAssignLabel}
	 * labeled alternative in {@link ProjectParser#annassign}.
	 * @param ctx the parse tree
	 */
	void exitAnnAssignLabel(ProjectParser.AnnAssignLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code augPlusLabel}
	 * labeled alternative in {@link ProjectParser#augassign}.
	 * @param ctx the parse tree
	 */
	void enterAugPlusLabel(ProjectParser.AugPlusLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code augPlusLabel}
	 * labeled alternative in {@link ProjectParser#augassign}.
	 * @param ctx the parse tree
	 */
	void exitAugPlusLabel(ProjectParser.AugPlusLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code augMinusLabel}
	 * labeled alternative in {@link ProjectParser#augassign}.
	 * @param ctx the parse tree
	 */
	void enterAugMinusLabel(ProjectParser.AugMinusLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code augMinusLabel}
	 * labeled alternative in {@link ProjectParser#augassign}.
	 * @param ctx the parse tree
	 */
	void exitAugMinusLabel(ProjectParser.AugMinusLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code augStarLabel}
	 * labeled alternative in {@link ProjectParser#augassign}.
	 * @param ctx the parse tree
	 */
	void enterAugStarLabel(ProjectParser.AugStarLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code augStarLabel}
	 * labeled alternative in {@link ProjectParser#augassign}.
	 * @param ctx the parse tree
	 */
	void exitAugStarLabel(ProjectParser.AugStarLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code augSlashLabel}
	 * labeled alternative in {@link ProjectParser#augassign}.
	 * @param ctx the parse tree
	 */
	void enterAugSlashLabel(ProjectParser.AugSlashLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code augSlashLabel}
	 * labeled alternative in {@link ProjectParser#augassign}.
	 * @param ctx the parse tree
	 */
	void exitAugSlashLabel(ProjectParser.AugSlashLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code testListStarLabel}
	 * labeled alternative in {@link ProjectParser#testlist_star_expr}.
	 * @param ctx the parse tree
	 */
	void enterTestListStarLabel(ProjectParser.TestListStarLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code testListStarLabel}
	 * labeled alternative in {@link ProjectParser#testlist_star_expr}.
	 * @param ctx the parse tree
	 */
	void exitTestListStarLabel(ProjectParser.TestListStarLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compForExprLabel}
	 * labeled alternative in {@link ProjectParser#testlist_star_expr}.
	 * @param ctx the parse tree
	 */
	void enterCompForExprLabel(ProjectParser.CompForExprLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compForExprLabel}
	 * labeled alternative in {@link ProjectParser#testlist_star_expr}.
	 * @param ctx the parse tree
	 */
	void exitCompForExprLabel(ProjectParser.CompForExprLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code testLabel}
	 * labeled alternative in {@link ProjectParser#test}.
	 * @param ctx the parse tree
	 */
	void enterTestLabel(ProjectParser.TestLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code testLabel}
	 * labeled alternative in {@link ProjectParser#test}.
	 * @param ctx the parse tree
	 */
	void exitTestLabel(ProjectParser.TestLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orTestLabel}
	 * labeled alternative in {@link ProjectParser#or_test}.
	 * @param ctx the parse tree
	 */
	void enterOrTestLabel(ProjectParser.OrTestLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orTestLabel}
	 * labeled alternative in {@link ProjectParser#or_test}.
	 * @param ctx the parse tree
	 */
	void exitOrTestLabel(ProjectParser.OrTestLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andTestLabel}
	 * labeled alternative in {@link ProjectParser#and_test}.
	 * @param ctx the parse tree
	 */
	void enterAndTestLabel(ProjectParser.AndTestLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andTestLabel}
	 * labeled alternative in {@link ProjectParser#and_test}.
	 * @param ctx the parse tree
	 */
	void exitAndTestLabel(ProjectParser.AndTestLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notTestLabel}
	 * labeled alternative in {@link ProjectParser#not_test}.
	 * @param ctx the parse tree
	 */
	void enterNotTestLabel(ProjectParser.NotTestLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notTestLabel}
	 * labeled alternative in {@link ProjectParser#not_test}.
	 * @param ctx the parse tree
	 */
	void exitNotTestLabel(ProjectParser.NotTestLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparisonInNotTestLabel}
	 * labeled alternative in {@link ProjectParser#not_test}.
	 * @param ctx the parse tree
	 */
	void enterComparisonInNotTestLabel(ProjectParser.ComparisonInNotTestLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparisonInNotTestLabel}
	 * labeled alternative in {@link ProjectParser#not_test}.
	 * @param ctx the parse tree
	 */
	void exitComparisonInNotTestLabel(ProjectParser.ComparisonInNotTestLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparisonLabel}
	 * labeled alternative in {@link ProjectParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparisonLabel(ProjectParser.ComparisonLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparisonLabel}
	 * labeled alternative in {@link ProjectParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparisonLabel(ProjectParser.ComparisonLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ltLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterLtLabel(ProjectParser.LtLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ltLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitLtLabel(ProjectParser.LtLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gtLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterGtLabel(ProjectParser.GtLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gtLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitGtLabel(ProjectParser.GtLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqeqLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterEqeqLabel(ProjectParser.EqeqLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqeqLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitEqeqLabel(ProjectParser.EqeqLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code geLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterGeLabel(ProjectParser.GeLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code geLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitGeLabel(ProjectParser.GeLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code leLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterLeLabel(ProjectParser.LeLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code leLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitLeLabel(ProjectParser.LeLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code noteqLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterNoteqLabel(ProjectParser.NoteqLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code noteqLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitNoteqLabel(ProjectParser.NoteqLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterInLabel(ProjectParser.InLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitInLabel(ProjectParser.InLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notInLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterNotInLabel(ProjectParser.NotInLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notInLabel}
	 * labeled alternative in {@link ProjectParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitNotInLabel(ProjectParser.NotInLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arithExprLabel}
	 * labeled alternative in {@link ProjectParser#arith_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithExprLabel(ProjectParser.ArithExprLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arithExprLabel}
	 * labeled alternative in {@link ProjectParser#arith_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithExprLabel(ProjectParser.ArithExprLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code termLabel}
	 * labeled alternative in {@link ProjectParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTermLabel(ProjectParser.TermLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code termLabel}
	 * labeled alternative in {@link ProjectParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTermLabel(ProjectParser.TermLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryFactorLabel}
	 * labeled alternative in {@link ProjectParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterUnaryFactorLabel(ProjectParser.UnaryFactorLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryFactorLabel}
	 * labeled alternative in {@link ProjectParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitUnaryFactorLabel(ProjectParser.UnaryFactorLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code powerLabel}
	 * labeled alternative in {@link ProjectParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterPowerLabel(ProjectParser.PowerLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code powerLabel}
	 * labeled alternative in {@link ProjectParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitPowerLabel(ProjectParser.PowerLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code powerExprLabel}
	 * labeled alternative in {@link ProjectParser#power}.
	 * @param ctx the parse tree
	 */
	void enterPowerExprLabel(ProjectParser.PowerExprLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code powerExprLabel}
	 * labeled alternative in {@link ProjectParser#power}.
	 * @param ctx the parse tree
	 */
	void exitPowerExprLabel(ProjectParser.PowerExprLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomExprLabel}
	 * labeled alternative in {@link ProjectParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void enterAtomExprLabel(ProjectParser.AtomExprLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomExprLabel}
	 * labeled alternative in {@link ProjectParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void exitAtomExprLabel(ProjectParser.AtomExprLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExprLabel}
	 * labeled alternative in {@link ProjectParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExprLabel(ProjectParser.ParenExprLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExprLabel}
	 * labeled alternative in {@link ProjectParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExprLabel(ProjectParser.ParenExprLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomParenLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtomParenLabel(ProjectParser.AtomParenLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomParenLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtomParenLabel(ProjectParser.AtomParenLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomListLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtomListLabel(ProjectParser.AtomListLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomListLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtomListLabel(ProjectParser.AtomListLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomDictLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtomDictLabel(ProjectParser.AtomDictLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomDictLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtomDictLabel(ProjectParser.AtomDictLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomNameLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtomNameLabel(ProjectParser.AtomNameLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomNameLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtomNameLabel(ProjectParser.AtomNameLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomNumberLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtomNumberLabel(ProjectParser.AtomNumberLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomNumberLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtomNumberLabel(ProjectParser.AtomNumberLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomStringLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtomStringLabel(ProjectParser.AtomStringLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomStringLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtomStringLabel(ProjectParser.AtomStringLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomFStringLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtomFStringLabel(ProjectParser.AtomFStringLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomFStringLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtomFStringLabel(ProjectParser.AtomFStringLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomTrueLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtomTrueLabel(ProjectParser.AtomTrueLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomTrueLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtomTrueLabel(ProjectParser.AtomTrueLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomFalseLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtomFalseLabel(ProjectParser.AtomFalseLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomFalseLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtomFalseLabel(ProjectParser.AtomFalseLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomNoneLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtomNoneLabel(ProjectParser.AtomNoneLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomNoneLabel}
	 * labeled alternative in {@link ProjectParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtomNoneLabel(ProjectParser.AtomNoneLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callTrailerLabel}
	 * labeled alternative in {@link ProjectParser#trailer}.
	 * @param ctx the parse tree
	 */
	void enterCallTrailerLabel(ProjectParser.CallTrailerLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callTrailerLabel}
	 * labeled alternative in {@link ProjectParser#trailer}.
	 * @param ctx the parse tree
	 */
	void exitCallTrailerLabel(ProjectParser.CallTrailerLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subscriptTrailerLabel}
	 * labeled alternative in {@link ProjectParser#trailer}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptTrailerLabel(ProjectParser.SubscriptTrailerLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subscriptTrailerLabel}
	 * labeled alternative in {@link ProjectParser#trailer}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptTrailerLabel(ProjectParser.SubscriptTrailerLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dotTrailerLabel}
	 * labeled alternative in {@link ProjectParser#trailer}.
	 * @param ctx the parse tree
	 */
	void enterDotTrailerLabel(ProjectParser.DotTrailerLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dotTrailerLabel}
	 * labeled alternative in {@link ProjectParser#trailer}.
	 * @param ctx the parse tree
	 */
	void exitDotTrailerLabel(ProjectParser.DotTrailerLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subscriptListLabel}
	 * labeled alternative in {@link ProjectParser#subscriptlist}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptListLabel(ProjectParser.SubscriptListLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subscriptListLabel}
	 * labeled alternative in {@link ProjectParser#subscriptlist}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptListLabel(ProjectParser.SubscriptListLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subscriptTestLabel}
	 * labeled alternative in {@link ProjectParser#subscript}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptTestLabel(ProjectParser.SubscriptTestLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subscriptTestLabel}
	 * labeled alternative in {@link ProjectParser#subscript}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptTestLabel(ProjectParser.SubscriptTestLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subscriptSliceLabel}
	 * labeled alternative in {@link ProjectParser#subscript}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptSliceLabel(ProjectParser.SubscriptSliceLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subscriptSliceLabel}
	 * labeled alternative in {@link ProjectParser#subscript}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptSliceLabel(ProjectParser.SubscriptSliceLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listLabel}
	 * labeled alternative in {@link ProjectParser#listmaker}.
	 * @param ctx the parse tree
	 */
	void enterListLabel(ProjectParser.ListLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listLabel}
	 * labeled alternative in {@link ProjectParser#listmaker}.
	 * @param ctx the parse tree
	 */
	void exitListLabel(ProjectParser.ListLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compForListLabel}
	 * labeled alternative in {@link ProjectParser#listmaker}.
	 * @param ctx the parse tree
	 */
	void enterCompForListLabel(ProjectParser.CompForListLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compForListLabel}
	 * labeled alternative in {@link ProjectParser#listmaker}.
	 * @param ctx the parse tree
	 */
	void exitCompForListLabel(ProjectParser.CompForListLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dictLabel}
	 * labeled alternative in {@link ProjectParser#dictmaker}.
	 * @param ctx the parse tree
	 */
	void enterDictLabel(ProjectParser.DictLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dictLabel}
	 * labeled alternative in {@link ProjectParser#dictmaker}.
	 * @param ctx the parse tree
	 */
	void exitDictLabel(ProjectParser.DictLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compForDictLabel}
	 * labeled alternative in {@link ProjectParser#dictmaker}.
	 * @param ctx the parse tree
	 */
	void enterCompForDictLabel(ProjectParser.CompForDictLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compForDictLabel}
	 * labeled alternative in {@link ProjectParser#dictmaker}.
	 * @param ctx the parse tree
	 */
	void exitCompForDictLabel(ProjectParser.CompForDictLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compForLabel}
	 * labeled alternative in {@link ProjectParser#comp_for}.
	 * @param ctx the parse tree
	 */
	void enterCompForLabel(ProjectParser.CompForLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compForLabel}
	 * labeled alternative in {@link ProjectParser#comp_for}.
	 * @param ctx the parse tree
	 */
	void exitCompForLabel(ProjectParser.CompForLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code testListCompLabel}
	 * labeled alternative in {@link ProjectParser#testlist_comp}.
	 * @param ctx the parse tree
	 */
	void enterTestListCompLabel(ProjectParser.TestListCompLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code testListCompLabel}
	 * labeled alternative in {@link ProjectParser#testlist_comp}.
	 * @param ctx the parse tree
	 */
	void exitTestListCompLabel(ProjectParser.TestListCompLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compForCompLabel}
	 * labeled alternative in {@link ProjectParser#testlist_comp}.
	 * @param ctx the parse tree
	 */
	void enterCompForCompLabel(ProjectParser.CompForCompLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compForCompLabel}
	 * labeled alternative in {@link ProjectParser#testlist_comp}.
	 * @param ctx the parse tree
	 */
	void exitCompForCompLabel(ProjectParser.CompForCompLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code argListLabel}
	 * labeled alternative in {@link ProjectParser#arglist}.
	 * @param ctx the parse tree
	 */
	void enterArgListLabel(ProjectParser.ArgListLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code argListLabel}
	 * labeled alternative in {@link ProjectParser#arglist}.
	 * @param ctx the parse tree
	 */
	void exitArgListLabel(ProjectParser.ArgListLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code positionalArgLabel}
	 * labeled alternative in {@link ProjectParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterPositionalArgLabel(ProjectParser.PositionalArgLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code positionalArgLabel}
	 * labeled alternative in {@link ProjectParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitPositionalArgLabel(ProjectParser.PositionalArgLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code keywordArgLabel}
	 * labeled alternative in {@link ProjectParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterKeywordArgLabel(ProjectParser.KeywordArgLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code keywordArgLabel}
	 * labeled alternative in {@link ProjectParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitKeywordArgLabel(ProjectParser.KeywordArgLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code delStmtLabel}
	 * labeled alternative in {@link ProjectParser#del_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDelStmtLabel(ProjectParser.DelStmtLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code delStmtLabel}
	 * labeled alternative in {@link ProjectParser#del_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDelStmtLabel(ProjectParser.DelStmtLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code passStmtLabel}
	 * labeled alternative in {@link ProjectParser#pass_stmt}.
	 * @param ctx the parse tree
	 */
	void enterPassStmtLabel(ProjectParser.PassStmtLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code passStmtLabel}
	 * labeled alternative in {@link ProjectParser#pass_stmt}.
	 * @param ctx the parse tree
	 */
	void exitPassStmtLabel(ProjectParser.PassStmtLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakLabel}
	 * labeled alternative in {@link ProjectParser#flow_stmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakLabel(ProjectParser.BreakLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakLabel}
	 * labeled alternative in {@link ProjectParser#flow_stmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakLabel(ProjectParser.BreakLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueLabel}
	 * labeled alternative in {@link ProjectParser#flow_stmt}.
	 * @param ctx the parse tree
	 */
	void enterContinueLabel(ProjectParser.ContinueLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueLabel}
	 * labeled alternative in {@link ProjectParser#flow_stmt}.
	 * @param ctx the parse tree
	 */
	void exitContinueLabel(ProjectParser.ContinueLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnLabel}
	 * labeled alternative in {@link ProjectParser#flow_stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnLabel(ProjectParser.ReturnLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnLabel}
	 * labeled alternative in {@link ProjectParser#flow_stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnLabel(ProjectParser.ReturnLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code importNamesLabel}
	 * labeled alternative in {@link ProjectParser#import_stmt}.
	 * @param ctx the parse tree
	 */
	void enterImportNamesLabel(ProjectParser.ImportNamesLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code importNamesLabel}
	 * labeled alternative in {@link ProjectParser#import_stmt}.
	 * @param ctx the parse tree
	 */
	void exitImportNamesLabel(ProjectParser.ImportNamesLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code importFromLabel}
	 * labeled alternative in {@link ProjectParser#import_stmt}.
	 * @param ctx the parse tree
	 */
	void enterImportFromLabel(ProjectParser.ImportFromLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code importFromLabel}
	 * labeled alternative in {@link ProjectParser#import_stmt}.
	 * @param ctx the parse tree
	 */
	void exitImportFromLabel(ProjectParser.ImportFromLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dottedNameLabel}
	 * labeled alternative in {@link ProjectParser#dotted_name}.
	 * @param ctx the parse tree
	 */
	void enterDottedNameLabel(ProjectParser.DottedNameLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dottedNameLabel}
	 * labeled alternative in {@link ProjectParser#dotted_name}.
	 * @param ctx the parse tree
	 */
	void exitDottedNameLabel(ProjectParser.DottedNameLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dottedAsNamesLabel}
	 * labeled alternative in {@link ProjectParser#dotted_as_names}.
	 * @param ctx the parse tree
	 */
	void enterDottedAsNamesLabel(ProjectParser.DottedAsNamesLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dottedAsNamesLabel}
	 * labeled alternative in {@link ProjectParser#dotted_as_names}.
	 * @param ctx the parse tree
	 */
	void exitDottedAsNamesLabel(ProjectParser.DottedAsNamesLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dottedAsNameLabel}
	 * labeled alternative in {@link ProjectParser#dotted_as_name}.
	 * @param ctx the parse tree
	 */
	void enterDottedAsNameLabel(ProjectParser.DottedAsNameLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dottedAsNameLabel}
	 * labeled alternative in {@link ProjectParser#dotted_as_name}.
	 * @param ctx the parse tree
	 */
	void exitDottedAsNameLabel(ProjectParser.DottedAsNameLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code importAsNamesLabel}
	 * labeled alternative in {@link ProjectParser#import_as_names}.
	 * @param ctx the parse tree
	 */
	void enterImportAsNamesLabel(ProjectParser.ImportAsNamesLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code importAsNamesLabel}
	 * labeled alternative in {@link ProjectParser#import_as_names}.
	 * @param ctx the parse tree
	 */
	void exitImportAsNamesLabel(ProjectParser.ImportAsNamesLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code importAsNameLabel}
	 * labeled alternative in {@link ProjectParser#import_as_name}.
	 * @param ctx the parse tree
	 */
	void enterImportAsNameLabel(ProjectParser.ImportAsNameLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code importAsNameLabel}
	 * labeled alternative in {@link ProjectParser#import_as_name}.
	 * @param ctx the parse tree
	 */
	void exitImportAsNameLabel(ProjectParser.ImportAsNameLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code globalStmtLabel}
	 * labeled alternative in {@link ProjectParser#global_stmt}.
	 * @param ctx the parse tree
	 */
	void enterGlobalStmtLabel(ProjectParser.GlobalStmtLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code globalStmtLabel}
	 * labeled alternative in {@link ProjectParser#global_stmt}.
	 * @param ctx the parse tree
	 */
	void exitGlobalStmtLabel(ProjectParser.GlobalStmtLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStmtCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmtCompound(ProjectParser.IfStmtCompoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStmtCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmtCompound(ProjectParser.IfStmtCompoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStmtCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmtCompound(ProjectParser.WhileStmtCompoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStmtCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmtCompound(ProjectParser.WhileStmtCompoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStmtCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmtCompound(ProjectParser.ForStmtCompoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStmtCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmtCompound(ProjectParser.ForStmtCompoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcDefCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFuncDefCompound(ProjectParser.FuncDefCompoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcDefCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFuncDefCompound(ProjectParser.FuncDefCompoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classDefCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterClassDefCompound(ProjectParser.ClassDefCompoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classDefCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitClassDefCompound(ProjectParser.ClassDefCompoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tryStmtCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterTryStmtCompound(ProjectParser.TryStmtCompoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tryStmtCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitTryStmtCompound(ProjectParser.TryStmtCompoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code withStmtCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWithStmtCompound(ProjectParser.WithStmtCompoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code withStmtCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWithStmtCompound(ProjectParser.WithStmtCompoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decoratedCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDecoratedCompound(ProjectParser.DecoratedCompoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decoratedCompound}
	 * labeled alternative in {@link ProjectParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDecoratedCompound(ProjectParser.DecoratedCompoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decoratedLabel}
	 * labeled alternative in {@link ProjectParser#decorated}.
	 * @param ctx the parse tree
	 */
	void enterDecoratedLabel(ProjectParser.DecoratedLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decoratedLabel}
	 * labeled alternative in {@link ProjectParser#decorated}.
	 * @param ctx the parse tree
	 */
	void exitDecoratedLabel(ProjectParser.DecoratedLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decoratorLabel}
	 * labeled alternative in {@link ProjectParser#decorator}.
	 * @param ctx the parse tree
	 */
	void enterDecoratorLabel(ProjectParser.DecoratorLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decoratorLabel}
	 * labeled alternative in {@link ProjectParser#decorator}.
	 * @param ctx the parse tree
	 */
	void exitDecoratorLabel(ProjectParser.DecoratorLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStmtLabel}
	 * labeled alternative in {@link ProjectParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmtLabel(ProjectParser.IfStmtLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStmtLabel}
	 * labeled alternative in {@link ProjectParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmtLabel(ProjectParser.IfStmtLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStmtLabel}
	 * labeled alternative in {@link ProjectParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmtLabel(ProjectParser.WhileStmtLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStmtLabel}
	 * labeled alternative in {@link ProjectParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmtLabel(ProjectParser.WhileStmtLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStmtLabel}
	 * labeled alternative in {@link ProjectParser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmtLabel(ProjectParser.ForStmtLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStmtLabel}
	 * labeled alternative in {@link ProjectParser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmtLabel(ProjectParser.ForStmtLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcDefLabel}
	 * labeled alternative in {@link ProjectParser#funcdef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDefLabel(ProjectParser.FuncDefLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcDefLabel}
	 * labeled alternative in {@link ProjectParser#funcdef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDefLabel(ProjectParser.FuncDefLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parametersLabel}
	 * labeled alternative in {@link ProjectParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParametersLabel(ProjectParser.ParametersLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parametersLabel}
	 * labeled alternative in {@link ProjectParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParametersLabel(ProjectParser.ParametersLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code typedArgsListLabel}
	 * labeled alternative in {@link ProjectParser#typedargslist}.
	 * @param ctx the parse tree
	 */
	void enterTypedArgsListLabel(ProjectParser.TypedArgsListLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code typedArgsListLabel}
	 * labeled alternative in {@link ProjectParser#typedargslist}.
	 * @param ctx the parse tree
	 */
	void exitTypedArgsListLabel(ProjectParser.TypedArgsListLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code typedArgsStarLabel}
	 * labeled alternative in {@link ProjectParser#typedargslist}.
	 * @param ctx the parse tree
	 */
	void enterTypedArgsStarLabel(ProjectParser.TypedArgsStarLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code typedArgsStarLabel}
	 * labeled alternative in {@link ProjectParser#typedargslist}.
	 * @param ctx the parse tree
	 */
	void exitTypedArgsStarLabel(ProjectParser.TypedArgsStarLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tfpdefLabel}
	 * labeled alternative in {@link ProjectParser#tfpdef}.
	 * @param ctx the parse tree
	 */
	void enterTfpdefLabel(ProjectParser.TfpdefLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tfpdefLabel}
	 * labeled alternative in {@link ProjectParser#tfpdef}.
	 * @param ctx the parse tree
	 */
	void exitTfpdefLabel(ProjectParser.TfpdefLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classDefLabel}
	 * labeled alternative in {@link ProjectParser#classdef}.
	 * @param ctx the parse tree
	 */
	void enterClassDefLabel(ProjectParser.ClassDefLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classDefLabel}
	 * labeled alternative in {@link ProjectParser#classdef}.
	 * @param ctx the parse tree
	 */
	void exitClassDefLabel(ProjectParser.ClassDefLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tryStmtLabel}
	 * labeled alternative in {@link ProjectParser#try_stmt}.
	 * @param ctx the parse tree
	 */
	void enterTryStmtLabel(ProjectParser.TryStmtLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tryStmtLabel}
	 * labeled alternative in {@link ProjectParser#try_stmt}.
	 * @param ctx the parse tree
	 */
	void exitTryStmtLabel(ProjectParser.TryStmtLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code withStmtLabel}
	 * labeled alternative in {@link ProjectParser#with_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWithStmtLabel(ProjectParser.WithStmtLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code withStmtLabel}
	 * labeled alternative in {@link ProjectParser#with_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWithStmtLabel(ProjectParser.WithStmtLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code withItemLabel}
	 * labeled alternative in {@link ProjectParser#with_item}.
	 * @param ctx the parse tree
	 */
	void enterWithItemLabel(ProjectParser.WithItemLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code withItemLabel}
	 * labeled alternative in {@link ProjectParser#with_item}.
	 * @param ctx the parse tree
	 */
	void exitWithItemLabel(ProjectParser.WithItemLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleSuite}
	 * labeled alternative in {@link ProjectParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSimpleSuite(ProjectParser.SimpleSuiteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleSuite}
	 * labeled alternative in {@link ProjectParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSimpleSuite(ProjectParser.SimpleSuiteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indentedSuite}
	 * labeled alternative in {@link ProjectParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterIndentedSuite(ProjectParser.IndentedSuiteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indentedSuite}
	 * labeled alternative in {@link ProjectParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitIndentedSuite(ProjectParser.IndentedSuiteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprListLabel}
	 * labeled alternative in {@link ProjectParser#exprlist}.
	 * @param ctx the parse tree
	 */
	void enterExprListLabel(ProjectParser.ExprListLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprListLabel}
	 * labeled alternative in {@link ProjectParser#exprlist}.
	 * @param ctx the parse tree
	 */
	void exitExprListLabel(ProjectParser.ExprListLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code testListLabel}
	 * labeled alternative in {@link ProjectParser#testlist}.
	 * @param ctx the parse tree
	 */
	void enterTestListLabel(ProjectParser.TestListLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code testListLabel}
	 * labeled alternative in {@link ProjectParser#testlist}.
	 * @param ctx the parse tree
	 */
	void exitTestListLabel(ProjectParser.TestListLabelContext ctx);
}