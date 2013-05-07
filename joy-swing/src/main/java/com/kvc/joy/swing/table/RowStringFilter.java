package com.kvc.joy.swing.table;

//~--- JDK imports ------------------------------------------------------------
import javax.swing.RowFilter;

/*
 * RowStringFilter.java
 *
 * Created on 2007年10月28日, 下午2:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
/**
 * 字符串过滤器  <br>
 * 用于对表格任意列(可多列)的字符串进行过滤 <br>
 *
 * RegexFilter 未排除正则表达式本身的字符(如："*", "^", "$", ...)，过滤里如果含有这些符号， <br>
 * 过滤结果将不正确，或直接抛异常, 所以有 RowStringFilter 这个类
 * example:
 * <p>
 * <hr><blockquote><pre>
 *
 * ((TableRowSort)JTable.getRowSort())
 * .setRowFilter(new RowStringFilter("to filter string",RowStringFilter.MatchMode.ANYWHERE,true,0,1,2));
 * </pre></blockquote><hr>
 * <p>
 * @author Kevice
 * @author ZY
 */
public class RowStringFilter extends RowFilter<Object, Object> {

    /**
     * 是否忽略大小写
     */
    private boolean ignoreCase;    // 是否忽略大小写 (如果matchMode的值为EXACT，该参数设置无效)
    /**
     * 过滤的索引
     */
    private int[] indices;    // 要过滤的列的索引
    /**
     * 匹配模式
     */
    private MatchMode matchMode;    // 匹配的模式
    /**
     * 查询的关键字
     */
    private String subString;    // 子串

    /**
     * 匹配模式枚举类
     */
    public enum MatchMode {

        /**
         * 精确匹配
         */
        EXACT,
        /**
         * 任何位置
         */
        ANYWHERE,
        /**
         * 匹配开头
         */
        START,
        /**
         * 匹配结尾
         */
        END
    }

    /**
     * 行字符串过滤器
     * @param indices 过滤的索引
     */
    public RowStringFilter(int... indices) {
        this(null, null, true, indices);
    }

    /**
     * 构造器 <br>
     * 默认模糊匹配 ( MatchMode.ANYWHERE && ignoreCase )
     * @param subString 子串
     * @param indices 要过滤的列的索引
     */
    public RowStringFilter(String subString, int... indices) {
        this(subString, MatchMode.ANYWHERE, true, indices);
    }

    /**
     * 行字符串过滤器
     * @param matchMode 匹配模式
     * @param ignoreCase 是否忽略大小写
     * @param indices 过滤的索引
     */
    public RowStringFilter(MatchMode matchMode, boolean ignoreCase, int... indices) {
        this(null, matchMode, ignoreCase, indices);
    }

    /**
     * 构造器
     * @param subString 子串
     * @param matchMode 匹配的模式
     * @param ignoreCase 是否忽略大小写 (如果matchMode的值为EXACT，该参数设置无效)
     * @param indices 要过滤的列的索引
     */
    public RowStringFilter(String subString, MatchMode matchMode, boolean ignoreCase, int... indices) {
        if (matchMode == null) {
            matchMode = MatchMode.ANYWHERE;
        }

        this.subString = subString;
        this.matchMode = matchMode;
        this.ignoreCase = ignoreCase;
        this.indices = indices;
    }

    /**
     * 包含的值是否有效
     * @param entry
     * @return 返回值的有效性
     */
    @Override
    public boolean include(RowFilter.Entry<? extends Object, ? extends Object> entry) {
        if ((subString == null) || subString.trim().isEmpty()) {
            return true;
        }

        for (int index = 0; index < indices.length; index++) {
            Object value = entry.getValue(indices[index]);
            if (value != null) {
                value = value.toString();
                String mainString = (ignoreCase ? ((String) value).toLowerCase() : (String) value).trim();
                String compareString = (ignoreCase ? subString.trim().toLowerCase() : subString).trim();
                switch (matchMode) {
                    case EXACT:
                        if (((String) value).trim().equals(subString.trim())) {
                            return true;
                        }

                        break;

                    case ANYWHERE:
                        if (mainString.contains(compareString)) {
                            return true;
                        }

                        break;

                    case START:
                        if (mainString.startsWith(compareString)) {
                            return true;
                        }

                        break;

                    case END:
                        if (mainString.endsWith(compareString)) {
                            return true;
                        }

                        break;
                }
            }
        }

        return false;
    }

    /**
     * 获取过滤关键字
     * @return 返回过滤的关键字
     */
    public String getSubString() {
        return subString;
    }

    /**
     * 设置过滤的关键字
     * @param subString 过滤关键字
     */
    public void setSubString(String subString) {
        this.subString = subString;
    }

    /**
     * 获得当前的匹配模式
     * @return 返回当前的匹配模式
     */
    public MatchMode getMatchMode() {
        return matchMode;
    }

    /**
     * 设置匹配模式
     * @param matchMode 匹配模式
     */
    public void setMatchMode(MatchMode matchMode) {
        this.matchMode = matchMode;
    }

    /**
     * 是否忽略大小写
     * @return 返回当前过滤状态是否忽略大小写
     */
    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    /**
     * 设置是否忽略大小写
     * @param ignoreCase 是否忽略大小写
     */
    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    /**
     * 获得当前过滤的索引
     * @return 返回当前过滤的索引
     */
    public int[] getIndices() {
        return indices;
    }

    /**
     * 设置过滤的索引
     * @param indices 设置的索引
     */
    public void setIndices(int... indices) {
        this.indices = indices;
    }
}
