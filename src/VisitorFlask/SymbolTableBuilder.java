package VisitorFlask;

import ASTFlask.AtomExpression.NameExpression;
import ASTFlask.SymbolTable.*;
import ASTFlask.TheMainContintOfComplexStatment.ParametarToFunction;
import antlrFlask.ProjectParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

public class SymbolTableBuilder extends Visitor {

    private Scope currentScope;
    private Stack<Scope> scopeStack = new Stack<>();
    private Scope globalScope;
    private Set<String> builtinFunctions = new HashSet<>();
    private Set<String> builtinConstants = new HashSet<>();

    public SymbolTableBuilder() {
        globalScope = new Scope("global", null);
        currentScope = globalScope;
        scopeStack.push(currentScope);

        addBuiltinFunctions();
    }

    private void addBuiltinFunctions() {
        String[] builtins = {
                "len", "next", "float", "int", "str", "list", "dict",
                "tuple", "set", "bool", "type", "range", "enumerate",
                "zip", "map", "filter", "sorted", "reversed", "sum",
                "min", "max", "abs", "round", "isinstance", "issubclass",
                "hasattr", "getattr", "setattr", "delattr", "property",
                "staticmethod", "classmethod", "super", "object", "BaseException"
        };

        builtinFunctions.addAll(Arrays.asList(builtins));

        builtinConstants.add("True");
        builtinConstants.add("False");
        builtinConstants.add("None");
        builtinConstants.add("__name__");

        VariableSymbol trueSymbol = new VariableSymbol("True", "bool", 0, globalScope);
        VariableSymbol falseSymbol = new VariableSymbol("False", "bool", 0, globalScope);
        VariableSymbol noneSymbol = new VariableSymbol("None", "NoneType", 0, globalScope);
        VariableSymbol nameSymbol = new VariableSymbol("__name__", "str", 0, globalScope);

        globalScope.addSymbol(trueSymbol);
        globalScope.addSymbol(falseSymbol);
        globalScope.addSymbol(noneSymbol);
        globalScope.addSymbol(nameSymbol);
    }

    private void enterScope(String name) {
        Scope newScope = new Scope(name, currentScope);
        currentScope = newScope;
        scopeStack.push(newScope);
    }

    private void exitScope() {
        scopeStack.pop();
        if (!scopeStack.isEmpty()) {
            currentScope = scopeStack.peek();
        } else {
            currentScope = globalScope;
        }
    }

    private void addSymbolToProperScope(Symbol symbol) {
        boolean isGlobal = false;

        if (currentScope == globalScope || currentScope.name.equals("program")) {
            isGlobal = true;
        }

        if (symbol instanceof FunctionSymbol && isDecoratedFunction()) {
            isGlobal = true;
        }

        Scope targetScope = isGlobal ? globalScope : currentScope;

        Symbol existing = targetScope.lookupCurrent(symbol.name);
        if (existing == null) {
            targetScope.addSymbol(symbol);
            String scopeType = targetScope == globalScope ? "global" : "local";
            System.out.println("✓ Added " + scopeType + " " + symbol.type + ": " + symbol.name + " at line " + symbol.line);
        }
    }

    private boolean isDecoratedFunction() {
        for (int i = scopeStack.size() - 1; i >= 0; i--) {
            if (scopeStack.get(i).name.contains("decorated")) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Object visitProgeRoot(ProjectParser.ProgeRootContext ctx) {
        enterScope("program");
        visitChildren(ctx);
        exitScope();
        return null;
    }

    @Override
    public Object visitSuite(ProjectParser.SuiteContext ctx) {
        visitChildren(ctx);
        return null;
    }

    @Override
    public Object visitFuncdef(ProjectParser.FuncdefContext ctx) {
        String funcName = ctx.NAME().getText();
        int line = ctx.getStart().getLine();

        FunctionSymbol funcSymbol = new FunctionSymbol(funcName, "unknown", line, currentScope);
        addSymbolToProperScope(funcSymbol);

        enterScope("function:" + funcName);
        funcSymbol.functionScope = currentScope;

        if (ctx.parameters() != null) {
            Object params = visit(ctx.parameters());
            if (params instanceof List<?>) {
                for (Object param : (List<?>) params) {
                    if (param instanceof ParametarToFunction) {
                        ParametarToFunction p = (ParametarToFunction) param;
                        VariableSymbol paramSymbol = new VariableSymbol(
                                p.name,
                                p.type != null ? p.type.toString() : "any",
                                line,
                                currentScope
                        );
                        addSymbolToProperScope(paramSymbol);
                        funcSymbol.addParameter(paramSymbol);
                    }
                }
            }
        }

        if (ctx.suite() != null) {
            visit(ctx.suite());
        }

        exitScope();
        return null;
    }

    @Override
    public Object visitClassdef(ProjectParser.ClassdefContext ctx) {
        String className = ctx.NAME().getText();
        int line = ctx.getStart().getLine();

        ClassSymbol classSymbol = new ClassSymbol(className, line, currentScope);
        addSymbolToProperScope(classSymbol);

        enterScope("class:" + className);

        if (ctx.suite() != null) {
            visit(ctx.suite());
        }

        exitScope();
        return null;
    }

    @Override
    public Object visitExprStmt(ProjectParser.ExprStmtContext ctx) {
        Object result = super.visitExprStmt(ctx);
        detectAndProcessAssignments(ctx);

        return result;
    }

    private void detectAndProcessAssignments(ProjectParser.ExprStmtContext ctx) {
        int line = ctx.getStart().getLine();

        if (ctx.EQ() != null && ctx.testlist_star_expr() != null && ctx.testlist_star_expr().size() >= 1) {
            processAssignment(ctx.testlist_star_expr(0), line);
        }

        else if (ctx.augassign() != null && ctx.testlist_star_expr() != null && ctx.testlist_star_expr().size() >= 1) {
            processAssignment(ctx.testlist_star_expr(0), line);
        }

        else if (ctx.annAssign() != null && ctx.testlist_star_expr() != null && ctx.testlist_star_expr().size() >= 1) {
            processAssignment(ctx.testlist_star_expr(0), line);
        }
    }

    private void processAssignment(ProjectParser.Testlist_star_exprContext targetCtx, int line) {
        try {
            Object target = visit(targetCtx);
            extractAndAddVariable(target, line);
        } catch (Exception e) {
            try {
                Object target = super.visit(targetCtx);
                extractAndAddVariable(target, line);
            } catch (Exception ex) {
            }
        }
    }

    private void extractAndAddVariable(Object target, int line) {
        if (target instanceof NameExpression) {
            addVariable(((NameExpression) target).name, line);
        }
        else if (target instanceof List<?>) {
            List<?> items = (List<?>) target;
            for (Object item : items) {
                if (item instanceof NameExpression) {
                    addVariable(((NameExpression) item).name, line);
                }
            }
        }
    }

    private void addVariable(String varName, int line) {
        if (builtinFunctions.contains(varName) || builtinConstants.contains(varName)) {
            return;
        }

        Symbol existing = currentScope.lookupCurrent(varName);
        if (existing == null) {
            VariableSymbol varSymbol = new VariableSymbol(varName, "any", line, currentScope);
            addSymbolToProperScope(varSymbol);
        }
    }

    @Override
    public Object visitFor_stmt(ProjectParser.For_stmtContext ctx) {
        int line = ctx.getStart().getLine();

        if (ctx.exprlist() != null) {
            Object exprlistResult = super.visit(ctx.exprlist());

            String scopeName = "for_loop_" + line;
            if (exprlistResult instanceof NameExpression) {
                String varName = ((NameExpression) exprlistResult).name;
                scopeName = "for_loop_" + varName + "_" + line;

                enterScope(scopeName);
                VariableSymbol loopVar = new VariableSymbol(varName, "iteration", line, currentScope);
                addSymbolToProperScope(loopVar);
            }
            else if (exprlistResult instanceof List<?>) {
                List<?> items = (List<?>) exprlistResult;
                for (Object item : items) {
                    if (item instanceof NameExpression) {
                        String varName = ((NameExpression) item).name;
                        scopeName = "for_loop_" + varName + "_" + line;

                        enterScope(scopeName);
                        VariableSymbol loopVar = new VariableSymbol(varName, "iteration", line, currentScope);
                        addSymbolToProperScope(loopVar);
                        break;
                    }
                }
            }
            else {
                enterScope(scopeName);
            }
        } else {
            enterScope("for_loop_" + line);
        }

        if (ctx.suite() != null) {
            visit(ctx.suite(0));
        }

        exitScope();

        if (ctx.ELSE() != null && ctx.suite().size() > 1) {
            enterScope("for_else_" + line);
            visit(ctx.suite(1));
            exitScope();
        }

        return null;
    }

    @Override
    public Object visitAtomName(ProjectParser.AtomNameContext ctx) {
        String name = ctx.getText();
        int line = ctx.getStart().getLine();

        if (builtinFunctions.contains(name) || builtinConstants.contains(name)) {
            return new NameExpression(name,line);
        }

        Symbol symbol = currentScope.lookup(name);

        if (symbol == null) {
            boolean isImported = false;
            for (Symbol s : globalScope.symbols.values()) {
                if (s instanceof VariableSymbol) {
                    VariableSymbol var = (VariableSymbol) s;
                    if ((var.type.equals("module") || var.type.equals("imported")) &&
                            var.name.equals(name)) {
                        isImported = true;
                        break;
                    }
                }
            }

            if (!isImported) {
                System.out.println(" Undefined symbol: '" + name + "' at line " + line);
            }
        }

        return new NameExpression(name,line);
    }


    @Override
    public Object visitImport_stmt(ProjectParser.Import_stmtContext ctx) {
        int line = ctx.getStart().getLine();

        if (ctx.dotted_as_names() != null) {
            for (ProjectParser.Dotted_as_nameContext nctx : ctx.dotted_as_names().dotted_as_name()) {
                String moduleName = nctx.dotted_name().getText();
                String alias = (nctx.AS() != null) ? nctx.NAME().getText() : null;

                VariableSymbol moduleSymbol = new VariableSymbol(
                        alias != null ? alias : moduleName,
                        "module",
                        line,
                        globalScope
                );
                globalScope.addSymbol(moduleSymbol);
            }
        }

        if (ctx.FROM() != null) {
            String moduleName = ctx.dotted_name().getText();

            if (ctx.STAR() != null) {
                VariableSymbol starSymbol = new VariableSymbol("*", "star_import", line, globalScope);
                globalScope.addSymbol(starSymbol);
            } else if (ctx.import_as_names() != null) {
                for (ProjectParser.Import_as_nameContext ictx : ctx.import_as_names().import_as_name()) {
                    String itemName = ictx.NAME(0).getText();
                    String alias = (ictx.AS() != null) ? ictx.NAME(1).getText() : null;

                    VariableSymbol importSymbol = new VariableSymbol(
                            alias != null ? alias : itemName,
                            "imported",
                            line,
                            globalScope
                    );
                    globalScope.addSymbol(importSymbol);
                }
            }
        }

        return super.visitImport_stmt(ctx);
    }

    @Override
    public Object visitGlobal_stmt(ProjectParser.Global_stmtContext ctx) {
        int line = ctx.getStart().getLine();

        for (TerminalNode nameNode : ctx.NAME()) {
            String name = nameNode.getText();

            Symbol existing = currentScope.lookupCurrent(name);
            if (existing instanceof VariableSymbol) {
                ((VariableSymbol) existing).scope = globalScope;
                currentScope.symbols.remove(name);
                globalScope.addSymbol(existing);
            } else {
                VariableSymbol globalVar = new VariableSymbol(name, "any", line, globalScope);
                globalScope.addSymbol(globalVar);
            }
        }

        return super.visitGlobal_stmt(ctx);
    }


    @Override
    public Object visitExprlist(ProjectParser.ExprlistContext ctx) {
        List<Object> results = new ArrayList<>();

        for (ProjectParser.TestContext testCtx : ctx.test()) {
            Object result = visit(testCtx);
            results.add(result);
        }

        if (results.size() == 1) {
            return results.get(0);
        }
        return results;
    }

    @Override
    public Object visitTestlist_star_expr(ProjectParser.Testlist_star_exprContext ctx) {
        List<Object> results = new ArrayList<>();

        for (ProjectParser.TestContext testCtx : ctx.test()) {
            Object result = visit(testCtx);
            results.add(result);
        }

        if (results.size() == 1) {
            return results.get(0);
        }
        return results;
    }

    @Override
    public Object visitTest(ProjectParser.TestContext ctx) {
        if (ctx.or_test() != null) {
            return visit(ctx.or_test(0));
        }
        return null;
    }

    @Override
    public Object visitDecorated(ProjectParser.DecoratedContext ctx) {
        enterScope("decorated_" + ctx.getStart().getLine());

        for (ProjectParser.DecoratorContext decCtx : ctx.decorator()) {
            visit(decCtx);
        }

        if (ctx.funcdef() != null) {
            visit(ctx.funcdef());
        } else if (ctx.classdef() != null) {
            visit(ctx.classdef());
        }

        exitScope();
        return null;
    }

    @Override
    public Object visitFuncDefStmt(ProjectParser.FuncDefStmtContext ctx) {
        return visit(ctx.funcdef());
    }

    @Override
    public Object visitClassDefinition(ProjectParser.ClassDefinitionContext ctx) {
        return visit(ctx.classdef());
    }

    @Override
    public Object visitImportStatement(ProjectParser.ImportStatementContext ctx) {
        return visit(ctx.import_stmt());
    }

    @Override
    public Object visitGlobalStatement(ProjectParser.GlobalStatementContext ctx) {
        return visit(ctx.global_stmt());
    }

    @Override
    public Object visitWhile_stmt(ProjectParser.While_stmtContext ctx) {
        int line = ctx.getStart().getLine();

        enterScope("while_loop_" + line);

        if (ctx.test() != null) {
            visit(ctx.test());
        }

        if (ctx.suite() != null) {
            visit(ctx.suite(0));
        }

        exitScope();

        if (ctx.ELSE() != null && ctx.suite().size() > 1) {
            enterScope("while_else_" + line);
            visit(ctx.suite(1));
            exitScope();
        }

        return null;
    }

    @Override
    public Object visitIf_stmt(ProjectParser.If_stmtContext ctx) {
        int line = ctx.getStart().getLine();

        enterScope("if_" + line);

        if (ctx.test(0) != null) {
            visit(ctx.test(0));
        }

        if (ctx.suite(0) != null) {
            visit(ctx.suite(0));
        }

        exitScope();

        for (int i = 0; i < ctx.ELIF().size(); i++) {
            enterScope("elif_" + line + "_" + i);

            if (i + 1 < ctx.test().size()) {
                visit(ctx.test(i + 1));
            }

            if (i + 1 < ctx.suite().size()) {
                visit(ctx.suite(i + 1));
            }

            exitScope();
        }

        if (ctx.ELSE() != null) {
            enterScope("else_" + line);

            int elseSuiteIndex = ctx.suite().size() - 1;
            if (elseSuiteIndex >= 0) {
                visit(ctx.suite(elseSuiteIndex));
            }

            exitScope();
        }

        return null;
    }

    @Override
    public Object visitWith_stmt(ProjectParser.With_stmtContext ctx) {
        int line = ctx.getStart().getLine();

        enterScope("with_" + line);

        for (ProjectParser.With_itemContext itemCtx : ctx.with_item()) {
            visit(itemCtx);

            if (itemCtx.test().size() > 1) {
                Object varObj = super.visit(itemCtx.test(1));
                if (varObj instanceof NameExpression) {
                    String varName = ((NameExpression) varObj).name;
                    VariableSymbol withVar = new VariableSymbol(varName, "context", line, currentScope);
                    addSymbolToProperScope(withVar);
                }
            }
        }

        if (ctx.suite() != null) {
            visit(ctx.suite());
        }

        exitScope();
        return null;
    }

    public void printSymbolTable() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SYMBOL TABLE");
        System.out.println("=".repeat(60));

        printScopeHierarchy(globalScope, 0);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("SUMMARY");
        System.out.println("=".repeat(60));
        printSummary();
    }

    private void printScopeHierarchy(Scope scope, int depth) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + "[" + scope.name.toUpperCase() + "]");

        if (scope.symbols.isEmpty()) {
            System.out.println(indent + "  (empty)");
            return;
        }

        List<Symbol> variables = new ArrayList<>();
        List<Symbol> functions = new ArrayList<>();
        List<Symbol> classes = new ArrayList<>();
        List<Symbol> imports = new ArrayList<>();

        for (Symbol symbol : scope.symbols.values()) {
            if (symbol instanceof VariableSymbol) {
                VariableSymbol var = (VariableSymbol) symbol;
                if (var.type.equals("module") || var.type.equals("imported") || var.type.equals("star_import")) {
                    imports.add(symbol);
                } else if (!builtinConstants.contains(var.name)) {
                    variables.add(symbol);
                }
            } else if (symbol instanceof FunctionSymbol) {
                functions.add(symbol);
            } else if (symbol instanceof ClassSymbol) {
                classes.add(symbol);
            }
        }

        if (!variables.isEmpty()) {
            System.out.println(indent + "  Variables (" + variables.size() + "):");
            for (Symbol var : variables) {
                VariableSymbol v = (VariableSymbol) var;
                String typeStr = v.type.equals("iteration") ? "loop var" :
                        v.type.equals("context") ? "context var" :
                                v.type.equals("any") ? "variable" : v.type;
                System.out.println(indent + "    • " + v.name + " (" + typeStr + ") at line " + v.line);
            }
        }

        if (!functions.isEmpty()) {
            System.out.println(indent + "  Functions (" + functions.size() + "):");
            for (Symbol func : functions) {
                FunctionSymbol f = (FunctionSymbol) func;
                System.out.println(indent + "    • " + f.name +
                        " (params: " + f.parameters.size() + ") at line " + f.line);

                if (f.functionScope != null) {
                    printScopeHierarchy(f.functionScope, depth + 2);
                }
            }
        }

        if (!classes.isEmpty()) {
            System.out.println(indent + "  Classes (" + classes.size() + "):");
            for (Symbol cls : classes) {
                ClassSymbol c = (ClassSymbol) cls;
                System.out.println(indent + "    • " + c.name + " at line " + c.line);

                for (Scope s : scopeStack) {
                    if (s.name.equals("class:" + c.name)) {
                        printScopeHierarchy(s, depth + 2);
                        break;
                    }
                }
            }
        }

        if (!imports.isEmpty() && scope == globalScope) {
            System.out.println(indent + "  Imports (" + imports.size() + "):");
            for (Symbol imp : imports) {
                VariableSymbol i = (VariableSymbol) imp;
                System.out.println(indent + "    • " + i.name + " (" + i.type + ") at line " + i.line);
            }
        }
    }

    private void printSummary() {
        int classes = 0;
        int functions = 0;
        int variables = 0;
        int imports = 0;

        for (Symbol symbol : globalScope.symbols.values()) {
            if (symbol instanceof ClassSymbol) {
                classes++;
            } else if (symbol instanceof FunctionSymbol) {
                functions++;
            } else if (symbol instanceof VariableSymbol) {
                VariableSymbol var = (VariableSymbol) symbol;
                if (var.type.equals("module") || var.type.equals("imported") || var.type.equals("star_import")) {
                    imports++;
                } else if (!builtinConstants.contains(var.name)) {
                    variables++;
                }
            }
        }

        System.out.println("Global Scope Summary:");
        System.out.println("  • Classes: " + classes);
        System.out.println("  • Functions: " + functions);
        System.out.println("  • Variables: " + variables);
        System.out.println("  • Imports: " + imports);
        System.out.println("  • Total symbols: " + (classes + functions + variables + imports));

        System.out.println("\nScope Hierarchy:");
        for (Scope scope : scopeStack) {
            if (scope != globalScope) {
                int symbolCount = scope.symbols.size();
                System.out.println("  • " + scope.name + ": " + symbolCount + " symbols");
            }
        }
    }

    public Scope getGlobalSymbolTable() {
        return globalScope;
    }

    public Scope getCurrentScope() {
        return currentScope;
    }
}