/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvc.joy.swing;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.kvc.joy.swing.res.img.MarkLocation;

/**
 * xswing工程的工具类
 * @author Kevice
 */
public class XSwingUtil {

    private XSwingUtil() {
    }

    /**
     * 获取在Jar中的配置文件的
     * @param filePathInJar  文件的类路径
     * @return 指向该文件的URL
     */
    private static URL getFileInJar(String filePathInJar) {
        URL configFileURL = XSwingUtil.class.getResource(filePathInJar);
        return configFileURL;
    }

    /**
     * 获取jar包中的图标。图标必须存在MarkLocation类所在的类路径中。
     * @param iconName 图标名称
     * @return ImageIcon 对象。
     */
    public static ImageIcon getIconInJar(String iconName) {
//        final String iconsFolder = MarkLocation.class.getName().replaceFirst(MarkLocation.class.getSimpleName(), "").replaceAll("\\.", "/");
//        String iconFileName = iconsFolder + iconName;

        URL imageURL = MarkLocation.class.getResource(iconName); // getFileInJar(iconFileName);
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(imageURL);
        } catch (Exception ex) {
            imageURL = MarkLocation.class.getResource("Missicon.gif");
            icon = new ImageIcon(imageURL);
        }
        return icon;
    }

    public static void runTextPanel(final JPanel panel) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(panel);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {

        System.out.println();
    }
}
