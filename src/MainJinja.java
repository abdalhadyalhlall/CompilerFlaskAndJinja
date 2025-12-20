import AST_HTMLCSSJINJA.*;
import VISITORJINJA.VisitorJinja;
import VISITORJINJA.VisitorJinjaWithSymbolTable;
import VISITORJINJA.ST.*;
import antlrJinja.HTMLCSSJINJA_lexer;
import antlrJinja.HTMLCSSJINJA_parser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainJinja {
    public static void main(String[] args) {
        try {
            // ============ 1. قراءة الملف ============
            String filePath = "Files/html.txt";
            String input = new String(Files.readAllBytes(Paths.get(filePath)));

            System.out.println("=== قراءة الملف ===");
            System.out.println(input);
            System.out.println("===================\n");

            // ============ 2. إنشاء Lexer و Parser ============
            CharStream charStream = CharStreams.fromString(input);
            HTMLCSSJINJA_lexer lexer = new HTMLCSSJINJA_lexer(charStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            HTMLCSSJINJA_parser parser = new HTMLCSSJINJA_parser(tokens);

            // ============ 3. إعداد معالجة الأخطاء ============
            parser.removeErrorListeners();
            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                        int line, int charPositionInLine,
                                        String msg, RecognitionException e) {
                    System.err.println("⚠️ خطأ في السطر " + line + ":" + charPositionInLine + " - " + msg);
                }
            });

            // ============ 4. تحليل الشجرة مرتين ============
            ParseTree tree = parser.document();

            // ============ 5. أولاً: استخدام Visitor العادي لطباعة AST كاملة ============
            System.out.println("\n🎄 ==================== شجرة AST (كاملة) ==================== 🎄\n");
            VisitorJinja visitor = new VisitorJinja();
            ASTNode ast = visitor.visit(tree);

            if (ast != null) {
                ast.print();  // ✅ هذه تطبع AST كاملة
            } else {
                System.out.println("❌ AST فارغة من Visitor العادي!");
            }

            // ============ 6. ثانياً: استخدام Visitor مع Symbol Table ============
            System.out.println("\n📊 ============== تحليل جدول الرموز ============== 📊\n");
            VisitorJinjaWithSymbolTable visitorWithST = new VisitorJinjaWithSymbolTable(filePath);
            ASTNode astWithST = visitorWithST.visit(tree);

            // طباعة تحليل Symbol Table
            visitorWithST.printSymbolAnalysis();

            // ============ 7. معلومات إضافية من Symbol Table ============
            SymbolTableImpl symbolTable = visitorWithST.getSymbolTable();

            System.out.println("\n🔍 ============== معلومات مفصلة ============== 🔍\n");

            // أ. إحصائيات عامة
            System.out.println("📈 الإحصائيات:");
            System.out.println("  • عدد العقد في AST: " + countASTNodes(ast));
            System.out.println("  • عدد الرموز الكلي: " + symbolTable.getAllSymbols().size());

            // بقية الكود كما هو...
            // ... [نفس الكود السابق]

        } catch (Exception e) {
            System.err.println("❌ حدث خطأ: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static int countASTNodes(ASTNode node) {
        if (node == null) return 0;
        int count = 1;
        for (ASTNode child : node.getChildren()) {
            count += countASTNodes(child);
        }
        return count;
    }
}