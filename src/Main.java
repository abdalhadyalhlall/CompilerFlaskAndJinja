import ASTFlask.ASTPrinter;
import ASTFlask.Proge;
import ASTFlask.SymbolTable.*;
import VisitorFlask.Visitor;
import antlrFlask.ProjectLexer;
import antlrFlask.ProjectParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import VisitorFlask.*;
import java.io.IOException;
import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Main {
    public static void main(String[] args)throws IOException {
        // String str = "Files/Flask.txt";
        String str = "Files/Flask.txt";
        String raw = java.nio.file.Files.readString(java.nio.file.Path.of(str));
        System.out.println("=== FILE CONTENT ===");
        // System.out.println(raw);
        System.out.println("====================");

        CharStream charStream =fromFileName(str);
        //  System.out.println(charStream);
        // CharStream charStream = CharStreams.fromString(str);
        ProjectLexer lex =new ProjectLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lex);
        commonTokenStream.fill(); // يجلب كل التوكنز
        for (Token t : commonTokenStream.getTokens()) {
            System.out.println("TOKEN: " + lex.VOCABULARY.getSymbolicName(t.getType()) + " -> '" + t.getText() + "'");
        }
        ProjectParser parser = new ProjectParser(commonTokenStream);
        ParseTree ast = parser.proge();
        Visitor exampleVisitor = new Visitor();
        Proge program =  (Proge) exampleVisitor.visit(ast);

        // System.out.println(program.toString());
        // طباعة شجرة AST كاملة
        // ASTPrinter.print(program);

        // أو يمكنك استخدام:
        System.out.println(ASTPrinter.printTree(program));
        //   exampleVisitor.getSymbolTable().printTable();
        // Build symbol table
        // SymbolTableBuilder symbolBuilder = new SymbolTableBuilder();
        //  symbolBuilder.visitProgeRoot((ProjectParser.ProgeRootContext) ast);

        // Print symbol table
        //  symbolBuilder.printSymbolTable();

        // بناء جدول الرموز
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SYMBOL TABLE ANALYSIS");
        System.out.println("=".repeat(50));
//
//        SymbolTableBuilder symbolBuilder = new SymbolTableBuilder();
//        symbolBuilder.visitProgeRoot((ProjectParser.ProgeRootContext) ast);
//
//        // طباعة جدول الرموز
//        symbolBuilder.printSymbolTable();
//
//        // تحليل إضافي
//        analyzeSymbols(symbolBuilder.getGlobalSymbolTable());
    }

    private static void analyzeSymbols(Scope globalScope) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SYMBOL ANALYSIS SUMMARY");
        System.out.println("=".repeat(50));

        int userFunctions = 0;
        int userVariables = 0;
        int imports = 0;
        int classes = 0;
        int totalSymbols = 0;

        for (Symbol symbol : globalScope.symbols.values()) {
            if (symbol instanceof FunctionSymbol) {
                userFunctions++;
            } else if (symbol instanceof ClassSymbol) {
                classes++;
            } else if (symbol instanceof VariableSymbol) {
                VariableSymbol var = (VariableSymbol) symbol;
                if (var.type.equals("module") || var.type.equals("imported")) {
                    imports++;
                } else if (!var.name.equals("True") && !var.name.equals("False") &&
                        !var.name.equals("None") && !var.name.equals("__name__")) {
                    userVariables++;
                }
            }
        }

        System.out.println("User-defined functions: " + userFunctions);
        System.out.println("User-defined classes: " + classes);
        System.out.println("User-defined variables: " + userVariables);
        System.out.println("Imported modules/functions: " + imports);
        System.out.println("Total symbols (excluding builtins): " +
                (userFunctions + classes + userVariables + imports));
    }
}
