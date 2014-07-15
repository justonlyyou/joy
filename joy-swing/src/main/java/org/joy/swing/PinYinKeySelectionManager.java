package org.joy.swing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.InputMap;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.text.Position;
import javax.swing.tree.TreePath;

import org.joy.commons.cn.PinYinTool;

/**
 * 支持对XComboBox, XList, XTree的选项按中文拼音首字母进行过滤
 * @author Kevice
 */
public class PinYinKeySelectionManager implements KeyListener {

    private long lastTime;
    /**
     * The time factor to treate the series of typed alphanumeric key
     * as prefix for first letter navigation.
     */
    private long timeFactor = 1000L;
    private Map<Integer, String> pinYinFirstLettersMap;
    private String prefix = "";
    private String typedString = "";
    private long time;
    private JComponent component;

    public PinYinKeySelectionManager(JComponent component) {
        this.component = component;
        KeyListener[] keyListeners = component.getKeyListeners();
        for (KeyListener keyListener : keyListeners) {
            component.removeKeyListener(keyListener);
        }
        component.addKeyListener(this);
    }

    /**
     * Returns the next ComboBox element whose {@code toString} value
     * starts with the given prefix.
     *
     * @param prefix the string to test for a match
     * @param startIndex the index for starting the search
     * @param bias the search direction, either
     * Position.Bias.Forward or Position.Bias.Backward.
     * @return the index of the next ComboBox element that
     * starts with the prefix; otherwise {@code -1}
     * @exception IllegalArgumentException if prefix is {@code null}
     * or startIndex is out of bounds
     */
    public int getNextMatch(String prefix, int startIndex, Position.Bias bias, ListModel model) {
        int max = model.getSize();
        if (prefix == null) {
            throw new IllegalArgumentException();
        }
        if (startIndex < 0 || startIndex >= max) {
            throw new IllegalArgumentException();
        }
        prefix = prefix.toUpperCase();

        // start search from the next element after the selected element
        int increment = (bias == Position.Bias.Forward) ? 1 : -1;
        int index = startIndex;

        do {
            Object o = model.getElementAt(index);

            if (o != null) {
                if (pinYinFirstLettersMap == null) {
                    pinYinFirstLettersMap = new HashMap<Integer, String>();
                }
                String pinYinFirstLetters = pinYinFirstLettersMap.get(index);
                if (pinYinFirstLetters == null) {
                    String string;
                    if (o instanceof String) {
                        string = (String) o;
                    } else {
                        string = o.toString();
                    }
                    if (string != null) {
                        pinYinFirstLetters = PinYinTool.getPinYinHeadChars(string);
                        pinYinFirstLettersMap.put(index, pinYinFirstLetters);
                    }
                }

                if (pinYinFirstLetters != null && pinYinFirstLetters.startsWith(prefix)) {
                    return index;
                }
            }
            index = (index + increment + max) % max;
        } while (index != startIndex);
        return -1;
    }

    public int selectionForKey(char aKey, ListModel aModel) {
        if (lastTime == 0L) {
            prefix = "";
            typedString = "";
        }
        boolean startingFromSelection = true;

        int startIndex = getSelectedIndex();
        if (time - lastTime < timeFactor) {
            typedString += aKey;
            if ((prefix.length() == 1) && (aKey == prefix.charAt(0))) {
                // Subsequent same key presses move the keyboard focus to the next
                // object that starts with the same letter.
                startIndex++;
            } else {
                prefix = typedString;
            }
        } else {
            startIndex++;
            typedString = "" + aKey;
            prefix = typedString;
        }
        lastTime = time;

        if (startIndex < 0 || startIndex >= aModel.getSize()) {
            startingFromSelection = false;
            startIndex = 0;
        }
        int index = getNextMatch(prefix, startIndex, Position.Bias.Forward, aModel);
        if (index < 0 && startingFromSelection) { // wrap
            index = getNextMatch(prefix, 0, Position.Bias.Forward, aModel);
        }
        return index;
    }

    private void setSelectedPath(char c, JTree tree) {
        boolean startingFromSelection = true;
        int startingRow = tree.getLeadSelectionRow();
        if (time - lastTime < timeFactor) {
            typedString += c;
            if ((prefix.length() == 1) && (c == prefix.charAt(0))) {
                // Subsequent same key presses move the keyboard focus to the next
                // object that starts with the same letter.
                startingRow++;
            } else {
                prefix = typedString;
            }
        } else {
            startingRow++;
            typedString = "" + c;
            prefix = typedString;
        }
        lastTime = time;

        if (startingRow < 0 || startingRow >= tree.getRowCount()) {
            startingFromSelection = false;
            startingRow = 0;
        }
        TreePath path = getNextMatch(prefix, startingRow, Position.Bias.Forward, tree);
        if (path != null) {
            tree.setSelectionPath(path);
//            int row = tree.getRowForPath(path);
//            ensureRowsAreVisible(row, row, tree);
        //TODO
        } else if (startingFromSelection) {
            path = getNextMatch(prefix, 0, Position.Bias.Forward, tree);
            if (path != null) {
                tree.setSelectionPath(path);
//                int row = getRowForPath(tree, path);
//                ensureRowsAreVisible(row, row);
            //TODO
            }
        }
    }

    /**
     * Returns the TreePath to the next tree element that
     * begins with a prefix. To handle the conversion of a
     * <code>TreePath</code> into a String, <code>convertValueToText</code>
     * is used.
     *
     * @param prefix the string to test for a match
     * @param startingRow the row for starting the search
     * @param bias the search direction, either
     * Position.Bias.Forward or Position.Bias.Backward.
     * @return the TreePath of the next tree element that
     * starts with the prefix; otherwise null
     * @exception IllegalArgumentException if prefix is null
     * or startingRow is out of bounds
     * @since 1.4
     */
    public TreePath getNextMatch(String prefix, int startingRow,
            Position.Bias bias, JTree tree) {
        int max = tree.getRowCount();
        if (prefix == null) {
            throw new IllegalArgumentException();
        }
        if (startingRow < 0 || startingRow >= max) {
            throw new IllegalArgumentException();
        }
        prefix = prefix.toUpperCase();

        // start search from the next/previous element froom the
        // selected element
        int increment = (bias == Position.Bias.Forward) ? 1 : -1;
        int row = startingRow;
        do {
            TreePath path = tree.getPathForRow(row);
            String text = tree.convertValueToText(
                    path.getLastPathComponent(), tree.isRowSelected(row),
                    tree.isExpanded(row), true, row, false);
            if (PinYinTool.getPinYinHeadChars(text).startsWith(prefix)) {
                return path;
            }
            row = (row + increment + max) % max;
        } while (row != startingRow);
        return null;
    }

    private boolean isNavigationKey(int keyCode, int modifiers) {
        InputMap inputMap = component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        KeyStroke key = KeyStroke.getKeyStroke(keyCode, modifiers);
        if (inputMap != null && inputMap.get(key) != null) {
            return true;
        }

        return false;
    }

    private static boolean isTypeAheadKey(KeyEvent e) {
        return !e.isAltDown() && !e.isControlDown() && !e.isMetaDown();
    }

    protected int getSelectedIndex() {
        return -1;
    }

    protected  void setSelectedIndex(int index) {
        
    }

    void resetLastTime() {
        lastTime = 0L;
    }

    void setTime(long time) {
        this.time = time;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char ch = e.getKeyChar();
        if (isNavigationKey(ch, e.getModifiers())) {
            lastTime = 0L;
        } else if (component.isEnabled() && isTypeAheadKey(e) && ch != KeyEvent.CHAR_UNDEFINED) {
            time = e.getWhen();
            if (component instanceof JTree) {
                JTree tree = (JTree) component;
                if (tree.getRowCount() > 0) {
                    setSelectedPath(ch, tree);
                }
            } else {
                ListModel listModel = null;
                if (component instanceof JComboBox) {
                    listModel = ((JComboBox) component).getModel();
                } else if (component instanceof JList) {
                    listModel = ((JList) component).getModel();
                }
                if (listModel != null && listModel.getSize() > 0F) {
                    int index = selectionForKey(ch, listModel);
                    if (index != -1) {
                        setSelectedIndex(index);
                    }
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
