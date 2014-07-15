package org.joy.swing.list;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author Administrator
 */
public class EditableListExample {

    /**
     * Creates a new instance of EditableListExample
     */
    public EditableListExample() {}

    /**
     * Method description
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        DefaultMutableListModel testModel = new DefaultMutableListModel();

        for (int index = 0; index < 100; index++) {
            testModel.add(index, "test" + index);
        }

        JListMutable jListMutable = new JListMutable(testModel);

//      jListMutable.setListCellEditor(new DefaultListCellEditor(new JComboBox(new String[]{"test1","test2"} )));
        jListMutable.setListCellEditor(new DefaultListCellEditor(new JTextField()));

//      jListMutable.setListCellEditor(new DefaultListCellEditor(new JCheckBox()));
        frame.getContentPane().add(jListMutable);
        frame.pack();
        frame.setVisible(true);
    }
}
