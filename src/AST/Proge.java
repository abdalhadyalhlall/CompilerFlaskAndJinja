package AST;

import java.util.List;

public class Proge extends StatmentRoot{
    public List<Statment> statements;

    public Proge(List<Statment> statements, int line) {
        super("Program", line);
        this.statements = statements;
    }

    @Override
    public String toString() {
        return "Program(line=" + line + ", statements=" + statements + ")";
    }
}
