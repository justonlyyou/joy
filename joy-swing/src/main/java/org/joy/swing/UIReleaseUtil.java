package org.joy.swing;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerListener;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyListener;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.EventListener;

import javax.swing.AbstractButton;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.event.AncestorListener;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;

/**
 * <p>类型名称: UIReleaseUtil </p>
 * <p>类型描述: 释放资源的工具类。</p>
 */
public class UIReleaseUtil {

    /**
     * Constructs ...
     *
     */
    private UIReleaseUtil() {}

    /**
     * 释放资源
     * @param cmp
     */
    public static void freeSwingObject(Component cmp) {
        freeSwingObjectImpl(cmp);
        freeObject(cmp);
        System.gc();
    }

    /**
     * Method description
     *
     *
     * @param cmp
     */
    private static void freeSwingObjectImpl(Component cmp) {
        if (cmp == null) {
            return;
        }

        freeComponent(cmp);
        freeContainer(cmp);
        freeJComponent(cmp);
        freeButton(cmp);
        freeText(cmp);
        freeWindow(cmp);
    }

    /**
     * Method description
     *
     *
     * @param cmp
     */
    private static void freeComponent(Component cmp) {

        // 注销并释放监听器资源
        EventListener[] listeners = cmp.getComponentListeners();

        for (int i = 0; i < listeners.length; i++) {
            cmp.removeComponentListener((ComponentListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        listeners = cmp.getFocusListeners();

        for (int i = 0; i < listeners.length; i++) {
            cmp.removeFocusListener((FocusListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        listeners = cmp.getHierarchyListeners();

        for (int i = 0; i < listeners.length; i++) {
            cmp.removeHierarchyListener((HierarchyListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        listeners = cmp.getHierarchyBoundsListeners();

        for (int i = 0; i < listeners.length; i++) {
            cmp.removeHierarchyBoundsListener((HierarchyBoundsListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        listeners = cmp.getInputMethodListeners();

        for (int i = 0; i < listeners.length; i++) {
            cmp.removeInputMethodListener((InputMethodListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        listeners = cmp.getKeyListeners();

        for (int i = 0; i < listeners.length; i++) {
            cmp.removeKeyListener((KeyListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        listeners = cmp.getMouseListeners();

        for (int i = 0; i < listeners.length; i++) {
            cmp.removeMouseListener((MouseListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        listeners = cmp.getMouseMotionListeners();

        for (int i = 0; i < listeners.length; i++) {
            cmp.removeMouseMotionListener((MouseMotionListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        listeners = cmp.getMouseWheelListeners();

        for (int i = 0; i < listeners.length; i++) {
            cmp.removeMouseWheelListener((MouseWheelListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        listeners = cmp.getPropertyChangeListeners();

        for (int i = 0; i < listeners.length; i++) {
            cmp.removePropertyChangeListener((PropertyChangeListener) listeners[i]);
        }

//      closeUIFreeable(listeners);
    }

    /**
     * Method description
     *
     *
     * @param cmp
     */
    private static void freeContainer(Component cmp) {
        if (!(cmp instanceof Container)) {
            return;
        }

        Container container = (Container) cmp;
        Component[] cmps = container.getComponents();

        for (int i = 0; i < cmps.length; i++) {
            freeSwingObjectImpl(cmps[i]);
        }

        container.removeAll();
    }

    /**
     * Method description
     *
     *
     * @param cmp
     */
    private static void freeJComponent(Component cmp) {
        if (!(cmp instanceof JComponent)) {
            return;
        }

        JComponent jcmp = (JComponent) cmp;

        // 注销并释放监听器资源
        EventListener[] listeners = jcmp.getAncestorListeners();

        for (int i = 0; i < listeners.length; i++) {
            jcmp.removeAncestorListener((AncestorListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        listeners = jcmp.getContainerListeners();

        for (int i = 0; i < listeners.length; i++) {
            jcmp.removeContainerListener((ContainerListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        listeners = jcmp.getVetoableChangeListeners();

        for (int i = 0; i < listeners.length; i++) {
            jcmp.removeVetoableChangeListener((VetoableChangeListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        // 释放keystroke
        KeyStroke[] keystrokes = jcmp.getRegisteredKeyStrokes();

        for (int i = 0; i < keystrokes.length; i++) {
            jcmp.unregisterKeyboardAction(keystrokes[i]);
        }

//      closeUIFreeable(keystrokes);

        // 其他
        ActionMap actionMap = jcmp.getActionMap();

        if (actionMap != null) {
            actionMap.clear();
        }

        jcmp.setActionMap(null);
    }

    /**
     * Method description
     *
     *
     * @param cmp
     */
    private static void freeWindow(Component cmp) {
        if (!(cmp instanceof Window)) {
            return;
        }

        Window window = (Window) cmp;

        // 注销并释放监听器资源
        EventListener[] listeners = window.getWindowFocusListeners();

        for (int i = 0; i < listeners.length; i++) {
            window.removeWindowFocusListener((WindowFocusListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        listeners = window.getWindowListeners();

        for (int i = 0; i < listeners.length; i++) {
            window.removeWindowListener((WindowListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        listeners = window.getWindowStateListeners();

        for (int i = 0; i < listeners.length; i++) {
            window.removeWindowStateListener((WindowStateListener) listeners[i]);
        }

//      closeUIFreeable(listeners);
        window.dispose();
    }

    /**
     * Method description
     *
     *
     * @param cmp
     */
    private static void freeButton(Component cmp) {
        if (!(cmp instanceof AbstractButton)) {
            return;
        }

        AbstractButton btn = (AbstractButton) cmp;

        // 注销并释放监听器资源
        EventListener[] listeners = btn.getActionListeners();

        for (int i = 0; i < listeners.length; i++) {
            btn.removeActionListener((ActionListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        listeners = btn.getChangeListeners();

        for (int i = 0; i < listeners.length; i++) {
            btn.removeChangeListener((ChangeListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

        listeners = btn.getItemListeners();

        for (int i = 0; i < listeners.length; i++) {
            btn.removeItemListener((ItemListener) listeners[i]);
        }

//      closeUIFreeable(listeners);

//      closeUIFreeable(btn.getAction());
        btn.setAction(null);
    }

    /**
     * Method description
     *
     *
     * @param cmp
     */
    private static void freeText(Component cmp) {
        if (!(cmp instanceof JTextComponent)) {
            return;
        }

        JTextComponent text = (JTextComponent) cmp;
        EventListener[] listeners = text.getCaretListeners();

        for (int i = 0; i < listeners.length; i++) {
            text.removeCaretListener((CaretListener) listeners[i]);
        }

//      closeUIFreeable(listeners);
    }

    /**
     * Method description
     *
     *
     * @param obj
     */
    static void freeObject(final Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            if (Modifier.isFinal(fields[i].getModifiers())) {
                continue;
            }

            if (Modifier.isStatic(fields[i].getModifiers())) {
                continue;
            }

            // 基本类型或者String不释放
            if (fields[i].getType().isPrimitive() || fields[i].getType().getName().equals("java.lang.String")) {
                continue;
            }

            try {
                fields[i].setAccessible(true);
                fields[i].set(obj, null);
            } catch (Exception ignore) {
                ignore.printStackTrace();

//              dMsg.warn(ignore);
            }
        }
    }

//  private static void closeUIFreeable(Object[] freeables) {
//      for (int i = 0; i < freeables.length; i++) {
//          closeUIFreeable(freeables[i]);
//      }
//  }
//
//  private static void closeUIFreeable(Object freeable) {
//      if (freeable instanceof UIFreeable) {
//          ((UIFreeable) (freeable)).freeResource();
//      }
//  }
//
//  public interface UIFreeable {
//
//      public void freeResource();
//  }
//  private static DebugPrn dMsg = new DebugPrn(GuiUtil.class.getName());
}
