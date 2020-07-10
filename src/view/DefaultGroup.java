package view;


/**
 *
 * @描述：默认Group实现
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:24:48
 */
public class DefaultGroup implements Group {
    private int row = 0;
    private int column = 0;
    private int rowSpan = 1;
    private int columnSpan = 1;
    private Object headerValue = null;

    public int getRow() {
        return this.row;
    }

    /**
     * @param row 要设置的 row。
     */
    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    /**
     * @param column 要设置的 column。
     */
    public void setColumn(int column) {
        this.column = column;
    }

    public int getColumnSpan() {
        return this.columnSpan;
    }

    /**
     * @param columnSpan 要设置的 columnSpan。
     */
    public void setColumnSpan(int columnSpan) {
        this.columnSpan = columnSpan;
    }

    public int getRowSpan() {
        return this.rowSpan;
    }

    /**
     * @param rowSpan 要设置的 rowSpan。
     */
    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }
    public Object getHeaderValue() {
        return this.headerValue;
    }

    /**
     * @param headerValue 要设置的 headerValue。
     */
    public void setHeaderValue(Object headerValue) {
        this.headerValue = headerValue;
    }
}
