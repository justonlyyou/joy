package com.kvc.joy.swing.button;

//~--- non-JDK imports --------------------------------------------------------

//~--- JDK imports ------------------------------------------------------------

import java.awt.Dimension;

import com.kvc.joy.commons.lang.string.I18nTool;
import com.kvc.joy.swing.SwingUtility;
import com.kvc.joy.swing.XSwingUtil;

/**
 * 工具栏按钮
 * @author zy
 */
public class ToolButton extends XButton {

    /** Field description */
    protected static final Dimension dimension = new Dimension(24, 24);
    /** Field description */
    private Styles buttonStyle;

    /**
     * Constructs ...
     *
     */
    public ToolButton() {
    }

    /**
     * Constructs ...
     *
     *
     * @param buttonType
     */
    public ToolButton(Styles buttonType) {
        setButtonStyle(buttonType);
    }

    /**
     * Enum description
     *
     */
    public enum Styles {

        NEW_BUTTON("AddNew", "ctrl N","AddNew"),
        SAVE_BUTTON("Save", "ctrl S","Save"),
        DELETE_BUTTON("Delete", "DELETE","Delete"),
        SAVE_AS_BUTTON("SaveAs", "ctrl shift S","SaveAs"),
        PRINT_BUTTON("Printer", "ctrl P","Print"),
        EDIT_BUTTON("Edit", "F2","Edit"),
        EXIT_BUTTON("Exit", "alt F4","Exit"),
        REFRESH_BUTTON("Refresh", "F5","Refresh"),
        HELP_BUTTON("Help", "F1","Help"),
        FILTER_BUTTON("Filter", "","Filter"),
        IMPORT_BUTTON("Import", "ctrl I","Import"),
        EXPORT_BUTTON("Export", "ctrl E","Export"),
        SEARCH_CLEAR_BUTTON("Search_clear", "","ClearSearch"),
        SEARCH_BUTTON("Search", "","Search"),
        FIND_BUTTON("Find", ""),
        APPROVE_BUTTON("Approve", "F3","Approve"),
        UNDO_APPROVE_BUTTON("ApproveUndo", "F4","AntiApproved"),
        CONFIRM_BUTTON("confirm", "","Confirmed"),
        TRACING_BUTTON("RelatedTickets", "","RelativeDocument"),
        ADVANCE_SEARCH_BUTTON("AdvancedSearch","", "AdvancedSearch"),
        POST_BUTTON("post", "","DoPost"),
        UNDO_POST_BUTTON("unpost","","");

        /** Field description */
        private String iconName;
        /** Field description */
        private String shortcutkeys;
        /**
         * 提示
         */
        private String toolTipKey;
        /**
         * Constructs ...
         *
         *
         * @param iconName
         * @param shortcutkeys
         */
        Styles(String iconName, String shortcutkeys) {
            this.iconName = iconName;
            this.shortcutkeys = shortcutkeys;
        }

         Styles(String iconName, String shortcutkeys,String toolTipKey) {
            this.iconName = iconName;
            this.shortcutkeys = shortcutkeys;
            this.toolTipKey = toolTipKey;
        }

        /**
         * Method description
         *
         *
         * @param iconName
         */
        public void setIconName(String iconName) {
            this.iconName = iconName;
        }

        /**
         * Method description
         *
         *
         * @return
         */
        public String getIconName() {
            return iconName;
        }

        /**
         * Method description
         *
         *
         * @param shortcutkey
         */
        public void setShortcutkeys(String shortcutkey) {
            this.shortcutkeys = shortcutkey;
        }

        /**
         * Method description
         *
         *
         * @return
         */
        public String getShortcukes() {
            return shortcutkeys;
        }

        /**
         * @return the toolTipKey
         */
        public String getToolTipKey() {
            return toolTipKey;
        }

        /**
         * @param toolTipKey the toolTipKey to set
         */
        public void setToolTipKey(String toolTipKey) {
            this.toolTipKey = toolTipKey;
        }
    }

    /**
     * Method description
     *
     *
     * @param style
     */
    public void setButtonStyle(Styles style) {
        buttonStyle = style;
        initToolFace();
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Styles getButtonStyle() {
        return buttonStyle;
    }

    /**
     * Method description
     *
     */
    protected void initToolFace() {
        setIcon(XSwingUtil.getIconInJar(buttonStyle.getIconName() + ".gif"));
        setToolTipText(I18nTool.getLocalStr(buttonStyle.getToolTipKey()));
        SwingUtility.getInstance().toolFaceForButton(this, buttonStyle.getShortcukes());
    }

    /**
     * Method description
     *
     *
     * @param toolButton
     *
     * @return
     */
    public static XButton createToolButton(Styles toolButton) {
        return new ToolButton(toolButton);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public String getText() {
        return "";
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public Dimension getPreferredSize() {
        return dimension;
    }
}
