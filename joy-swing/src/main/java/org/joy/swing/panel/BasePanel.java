package org.joy.swing.panel;


//~--- JDK imports ------------------------------------------------------------

import org.joy.commons.lang.string.I18nTool;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * 实现ChildWindowListener的面板。
 * 如果要提示保存，需要：
 * 1、需要重写saveOperation方法，返回保存结果，保存操作不需要放在额外线程中，
 * 2、需要重写getShowCondition方法，返回判断保存的条件
 * 其他需要提示保存的操作，就覆盖operationNeedToBeSaved方法，将该操作写在这个方法中，在进行该操作的时候通过调用operate方法来执行操作。
 * @author Allenwc
 */
public abstract class BasePanel<T extends Serializable> extends BASPanel<T> {

    /**
     * 是否要显示确认框
     */
    private boolean isShow = true;
    private boolean closable = true;

    public BasePanel() {
        super();
    }

    /**
     * 显示确认框
     *
     * @param isClose
     * @param operate
     *
     * @throws HeadlessException
     */
    private final void showMessageDialog(boolean canClose, final boolean isClosing, final Object operate) throws HeadlessException {
        int result = JOptionPane.showConfirmDialog(this, I18nTool.getLocalStr("ChangedDataIsNotSavedYet"),
                I18nTool.getLocalStr("Msg"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        switch (result) {
            case JOptionPane.YES_OPTION:
                closable = canClose;
                new Thread() {

                    @Override
                    public void run() {
                        final boolean flag = saveOperation();
                        SwingUtilities.invokeLater(new Runnable() {

                            @Override
                            public void run() {
                                if (flag) {
                                    if (isClosing) {
                                        isShow = false;
                                        close();
                                    } else {
                                        operationNeedToBeSaved(operate);
                                        operationNeedToBeSaved();
                                    }
                                }
                            }
                        });
                    }
                }.start();
                break;
            case JOptionPane.CANCEL_OPTION:
            case JOptionPane.DEFAULT_OPTION:
                closable = canClose;
                break;
            case JOptionPane.NO_OPTION:
                this.isShow = false;
                if (isClosing) {
                    closable = true;
                } else {
                    isShow = true;
                    operationNeedToBeSaved(operate);
                    operationNeedToBeSaved();
                }
                break;
        }

    }

    /**
     * Method description
     *
     *
     * @param operate
     */
    public void operate(Object operate) {
        if (isShow && getShowCondition()) {
            showMessageDialog(true, false, operate);
        } else {
            operationNeedToBeSaved(operate);
        }
    }

    /**
     * 进行其他需要判断是否要保存的操作
     */
    public void operate() {
        if (isShow && getShowCondition()) {
            showMessageDialog(true, false, null);
        } else {
            operationNeedToBeSaved();
        }
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public boolean closeingPanel() {
        if (isShow && getShowCondition()) {
            this.showMessageDialog(false, true, null);
        } else {
            closable = true;
        }
        return closable;
    }

    /**
     * 关闭之前进行的操作，不需要放在另外的线程中
     * @return 操作是否成功
     */
    public boolean saveOperation() {
        return true;
    }

    /**
     * 需要保存的操作
     */
    public void operationNeedToBeSaved() {
    }

    /**
     * 需要保存的操作
     * @param operate
     */
    public void operationNeedToBeSaved(Object operate) {
    }

    /**
     * 得到弹出确认框的条件，根据这个条件判断是否弹出确认框，是则弹出，否则不弹
     * @return 弹出确认框的条件
     */
    public boolean getShowCondition() {
        return false;
    }
}
