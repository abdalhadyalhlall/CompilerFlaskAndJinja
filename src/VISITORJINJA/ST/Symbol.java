package VISITORJINJA.ST;

/**
 * الفئة الأساسية للرموز في جدول الرموز
 */
public abstract class Symbol {
    // تغيير private إلى protected للسماح بالوصول في الفئات الفرعية
    protected String name;
    protected String type;
    protected int line;
    protected int column;
    protected String filePath;

    public Symbol(String name, String type, int line, int column) {
        this.name = name;
        this.type = type;
        this.line = line;
        this.column = column;
    }

    // ================ Getter و Setter Methods ================

    /**
     * الحصول على الاسم
     */
    public String getName() {
        return name;
    }

    /**
     * تعيين الاسم
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * الحصول على النوع
     */
    public String getType() {
        return type;
    }

    /**
     * تعيين النوع
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * الحصول على رقم السطر
     */
    public int getLine() {
        return line;
    }

    /**
     * تعيين رقم السطر
     */
    public void setLine(int line) {
        this.line = line;
    }

    /**
     * الحصول على رقم العمود
     */
    public int getColumn() {
        return column;
    }

    /**
     * تعيين رقم العمود
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * الحصول على مسار الملف
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * تعيين مسار الملف
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * الحصول على معلومات تفصيلية عن الرمز
     */
    public abstract String getDetailedInfo();

    /**
     * تمثيل نصي للرمز
     */
    @Override
    public String toString() {
        return String.format("%s [%s] at line %d", name, type, line);
    }
}