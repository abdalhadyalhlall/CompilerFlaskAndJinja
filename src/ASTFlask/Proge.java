//package AST;
//
//import java.util.List;
//
//public class Proge extends StatmentRoot{
//    public List<Statment> statements;
//
//    public Proge(List<Statment> statements, int line) {
//        super("Program", line);
//        this.statements = statements;
//    }
//    public void setStatements(List<Statment> statements) {
//        this.statements = statements;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        for(Statment child : this.statements){
//            stringBuilder.append(child);
//            stringBuilder.append("\n");
//            System.out.println();
//        }
//        return stringBuilder.toString();
//    }
//}


package ASTFlask;

import java.util.List;

public class Proge extends StatmentRoot {
    public List<Statment> statements;

    public Proge(List<Statment> statements, int line) {
        super("Program", line);
        this.statements = statements;
    }
//
//    @Override
//    public String toString() {
//        return ASTPrinter.printTree(this);
//    }

        @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Statment child : this.statements){
            stringBuilder.append(child);
            stringBuilder.append("\n");
            System.out.println();
        }
        return stringBuilder.toString();
    }
    public String toSimpleString() {
        StringBuilder sb = new StringBuilder();
        for (Statment stmt : this.statements) {
            sb.append(stmt.toString()).append("\n");
        }
        return sb.toString();
    }
}