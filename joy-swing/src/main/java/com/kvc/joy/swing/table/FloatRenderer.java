package com.kvc.joy.swing.table;

//~--- JDK imports ------------------------------------------------------------

import java.math.RoundingMode;

import java.text.NumberFormat;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 表格的要展现Float型的Renderer
 */
public class FloatRenderer extends DefaultTableCellRenderer {

    /**
     * 不带百分号的浮点
     */
    public static final int FLOAT_NUMBER = 0;

    /**
     * 带百分号的浮点
     */
    public static final int PERCENT_NUMBER = 1;

    /**
     * 数值格式化
     */
    private NumberFormat formatter = null;

    /**
     * 构造一个显示浮点形式的绘制器
     */
    public FloatRenderer() {
        this(FLOAT_NUMBER, 2);
    }

    /**
     *
     * @param viewType 是展现浮点型，还是百分号
     * @param precision 浮点的精度
     */
    public FloatRenderer(int viewType, int precision) {
        this(viewType, precision, javax.swing.SwingConstants.RIGHT);
    }

    /**
     *
     * @param viewType 是展现浮点型，还是百分号
     * @param precision 浮点的精度
     * @param groupingUsed 是否要用逗号分割
     */
    public FloatRenderer(int viewType, int precision, boolean groupingUsed) {
        this(viewType, precision, SwingConstants.RIGHT, groupingUsed);
    }

    /**
     *
     * @param viewType 是展现浮点型，还是百分号
     * @param precision 浮点的精度
     * @param orientation 显示方位
     */
    public FloatRenderer(int viewType, int precision, int orientation) {
        this(viewType, precision, orientation, true);
    }

    /**
     *
     * @param viewType 是展现浮点型，还是百分号
     * @param precision 浮点的精度
     * @param orientation 显示方位
     * @param groupingUsed 是否要用逗号分割
     */
    public FloatRenderer(int viewType, int precision, int orientation, boolean groupingUsed) {
        super();

        if ((orientation >= SwingConstants.CENTER) && (orientation <= SwingConstants.RIGHT)) {
            this.setHorizontalAlignment(orientation);
        } else {
            this.setHorizontalAlignment(SwingConstants.RIGHT);
        }

        initFormat(viewType, precision, groupingUsed);
    }

    /**
     * 初始化格式化
     * @param viewType 显示模式(
     * FLOAT_NUMBER:不带百分号的浮点
     * PERCENT_NUMBER: 百分号的浮点形式)
     * @param precision 小数位数
     * @param groupingUsed 是否分组
     */
    private void initFormat(int viewType, int precision, boolean groupingUsed) {
        switch (viewType) {
        case PERCENT_NUMBER :
            formatter = NumberFormat.getPercentInstance();

            break;

        case FLOAT_NUMBER :
        default :
            formatter = NumberFormat.getNumberInstance();
        }

        formatter.setGroupingUsed(groupingUsed);
        formatter.setMaximumFractionDigits(precision);

        // 现在都不补0
//      formatter.setMinimumFractionDigits(precision);
        formatter.setRoundingMode(RoundingMode.HALF_UP);
    }

    /**
     * 显示的小数位数
     * @return 显示的小数位数
     */
    public int getPrecision() {
        return formatter.getMaximumFractionDigits();
    }

    /**
     * 设置显示的小数位数
     * @param precision 显示的小数位数
     */
    public void setPrecision(int precision) {
        formatter.setMaximumFractionDigits(precision);
        formatter.setMinimumFractionDigits(precision);
    }

    /**
     * 设置显示的模式
     *
     * @param viewType
     */
    public void setViewType(int viewType) {
        initFormat(viewType, 2, true);
    }

    /**
     * 是否使用分组
     * @return 是否分组
     */
    public boolean isGroupingUsed() {
        return formatter.isGroupingUsed();
    }

    /**
     * 设置是否使用分组
     * @param groupingUsed 是否分组
     */
    public void setGroupingUsed(boolean groupingUsed) {
        formatter.setGroupingUsed(groupingUsed);
    }

    /**
     * 重写setvalue方法，将数值转化为特定形式显示
     * @param value
     */
    @Override
    public void setValue(Object value) {
        if (value instanceof Number) {
            value = formatter.format(value);
        }

        super.setValue(value);
    }
}
