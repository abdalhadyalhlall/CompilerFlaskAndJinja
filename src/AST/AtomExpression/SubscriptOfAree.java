package AST.AtomExpression;
//a[0]
//a[1:5]
//a[x:y:z]
public class SubscriptOfAree extends ExpressionAtom{
    public ExpressionAtom value;
    public SliceTowSubscriptOfAree slice;

    public SubscriptOfAree(ExpressionAtom value, SliceTowSubscriptOfAree slice, int line) {
        super("SubscriptExpr", line);
        this.value = value;
        this.slice = slice;
    }

    public void setValue(ExpressionAtom value) {
        this.value = value;
    }

    public void setSlice(SliceTowSubscriptOfAree slice) {
        this.slice = slice;
    }

    @Override
    public String toString() {
        return nodeName + "(line=" + line + ", value=" + value + ", slice=" + slice + ")";
    }
}
