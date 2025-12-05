package AST.AtomExpression;

import AST.StatmentRoot;

public class SliceTowSubscriptOfAree extends StatmentRoot {
    public ExpressionAtom start;
    public ExpressionAtom end;
    public ExpressionAtom step;

    public SliceTowSubscriptOfAree(ExpressionAtom start, ExpressionAtom end, ExpressionAtom step, int line) {
        super("Slice", line);
        this.start = start;
        this.end = end;
        this.step = step;
    }

    @Override
    public String toString() {
        return "Slice(line=" + line + ", start=" + start + ", end=" + end + ", step=" + step + ")";
    }
}
