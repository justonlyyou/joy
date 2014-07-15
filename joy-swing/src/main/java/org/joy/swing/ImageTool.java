package org.joy.swing;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 图象处理工具类
 * @author Kevice
 */
public class ImageTool {

    private ImageTool() {
    }

    /**
     * 将图片转化为指定大小的图片
     * Convenience method that returns a scaled instance of the
     * provided {@code BufferedImage}.
     *
     * @param img the original image to be scaled
     * @param toWidth the desired width of the scaled instance, in pixels
     * @param toHeight the desired height of the scaled instance, in pixels
     * @param hint one of the rendering hints that corresponds to
     *    {@code RenderingHints.KEY_INTERPOLATION} (e.g.
     *    {@code RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR},
     *    {@code RenderingHints.VALUE_INTERPOLATION_BILINEAR},
     *    {@code RenderingHints.VALUE_INTERPOLATION_BICUBIC})
     * @param higherQuality if true, this method will use a multi-step
     *    scaling technique that provides higher quality than the usual
     *    one-step technique (only useful in down-scaling cases, where
     *    {@code toWidth} or {@code toHeight} is
     *    smaller than the original dimensions, and generally only when
     *    the {@code BILINEAR} hint is specified)
     * @return a scaled version of the original {@codey BufferedImage}
     */
    public static BufferedImage scale(BufferedImage img, int toWidth, int toHeight, Object hint, boolean higherQuality) {
        int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = (BufferedImage) img;
        int width, height;
        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            width = img.getWidth();
            height = img.getHeight();
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            width = toWidth;
            height = toHeight;
        }

        do {
            if (higherQuality && width > toWidth) {
                width /= 2;
                if (width < toWidth) {
                    width = toWidth;
                }
            }

            if (higherQuality && height > toHeight) {
                height /= 2;
                if (height < toHeight) {
                    height = toHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(width, height, type);
            Graphics2D g2d = tmp.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2d.drawImage(ret, 0, 0, width, height, null);
            g2d.dispose();

            ret = tmp;
        } while (width != toWidth || height != toHeight);

        return ret;
    }

    /**
     * 将图片转化为小图像
     * @param imageFile 图片文件
     * @param toWidth 缩小后的宽度
     * @param toHeight 缩小后的高度
     * @return 缩小后的图标
     */
    public static ImageIcon scaleDownImage(File imageFile, int toWidth, int toHeight) {
        ImageIcon userImageIcon = null;
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageFile);
            if (image != null) {
                // 将图片转化为指定大小
                Image newlyImage = scale(image, toWidth, toHeight,
                        RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR, false);
                userImageIcon = new ImageIcon(newlyImage);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return userImageIcon;
    }

    /**
     * 将图片转化为小图像
     * @param fileName 图片文件名
     * @param toWidth 缩小后的宽度
     * @param toHeight 缩小后的高度
     * @return 缩小后的图标
     */
    public static ImageIcon transformImageIconByFileName(String fileName, int toWidth, int toHeight) {
        ImageIcon userImageIcon = null;
        File imageFile = new File(fileName);
        if (imageFile.exists()) {
            userImageIcon = scaleDownImage(imageFile, toWidth, toHeight);
        }
        return userImageIcon;
    }

}
