package org.joy.swing;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JLabel;

import org.joy.commons.lang.string.I18nTool;

/**
 * 辅助绘制标签
 * @author ckcs
 */
public class AuxiliaryDrawLabel extends JLabel {

    private Image image;
    private String personName;

    public AuxiliaryDrawLabel() {
        setEnabled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int x = getWidth() / 2 - image.getWidth(null) / 2;
            int y = getHeight() / 2 - image.getHeight(null);
            g.drawImage(image, x, y, null);
            if (personName != null) {
                g.setColor(Color.BLUE);
                g.setFont(new Font(null, Font.ITALIC, 20));
                ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));
                int inrease = image.getHeight(null) / 2;
                g.drawString(personName, getWidth() / 2 - g.getFontMetrics().stringWidth(personName) / 2, y + inrease * 3 + 2);
                ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            }
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * 设置单据状态
     * @param status
     */
    public void updatePanelDoc(DocWartermarkStatus docStatus) {
        if (docStatus != null && docStatus.needDraw()) {
            Image img = XSwingUtil.getIconInJar(I18nTool.getLocalStr(docStatus.getStatus()) + ".gif").getImage();
            setImage(img);
            setPersonName(docStatus.getPersonName());
            setVisible(true);
        } else {
            setImage(null);
            setPersonName(null);
            setVisible(false);
        }
        repaint();
    }
}