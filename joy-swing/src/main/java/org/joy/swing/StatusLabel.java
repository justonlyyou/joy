package org.joy.swing;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

/**
 * 单据状态的标签
 * @author zy
 */
public class StatusLabel extends JLabel {

    /** Creates a new instance of StatusLabel */
    public StatusLabel() {
        super();
        initStyle();
    }

    /**
     * Method description
     *
     */
    private void initStyle() {
        setPreferredSize(new Dimension(80, 60));
        this.setBackground(new Color(255, 255, 224));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setForeground(new Color(0, 70, 213));
        this.setOpaque(true);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * 重写LABEL的setText方法。
     * 不论传入的字符串大小写，全部转化为大写
     *
     * @param text
     */
    public void setText(String text) {
        text = text.toUpperCase(Locale.ENGLISH);
        super.setText(text);
        setToolTipText(text);
    }

//
//  public static void main(String[] d){
//      try {
//          UIManager.setLookAndFeel(new WindowsLookAndFeel());
//      } catch (UnsupportedLookAndFeelException ex) {
//          ex.printStackTrace();
//      }
//      JFrame jFrame=new JFrame();
//      StatusLabel test = new StatusLabel();
//      test.setText("acbaeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
//      jFrame.getContentPane().add(test);
//      jFrame.setTitle("StatusLabel");
//      jFrame.pack();
//      jFrame.setLocation((jFrame.getToolkit().getScreenSize().width-jFrame.getWidth())/2,
//              (jFrame.getToolkit().getScreenSize().height-jFrame.getHeight())/2 );
//      jFrame.setVisible(true);
//      //   jFrame.setResizable(false);
//      jFrame.setDefaultCloseOperation(
//              WindowConstants.DISPOSE_ON_CLOSE);
//      jFrame.addWindowListener(new WindowAdapter() {
//          public void windowClosed(WindowEvent e) {
//              System.exit(0);
//          }
//      });
//  }
}
