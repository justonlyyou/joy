/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.dialog;

import java.awt.Dialog;
import java.awt.Dialog.ModalityType;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import javax.swing.JComponent;
import javax.swing.JDialog;

/**
 *
 * @author Kevice
 */
public class XDialog extends JDialog {

    /**
     * Creates a modeless dialog without a title and without a specified
     * <code>Frame</code> owner.  A shared, hidden frame will be
     * set as the owner of the dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     * <p>
     * NOTE: This constructor does not allow you to create an unowned
     * <code>XDialog</code>. To create an unowned <code>XDialog</code>
     * you must use either the <code>XDialog(Window)</code> or
     * <code>XDialog(Dialog)</code> constructor with an argument of
     * <code>null</code>.
     *
     * @exception HeadlessException if <code>GraphicsEnvironment.isHeadless()</code>
     *     returns <code>true</code>.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog() {
        super();
    }

    /**
     * Creates a modeless dialog without a title with the
     * specified <code>Frame</code> as its owner.  If <code>owner</code>
     * is <code>null</code>, a shared, hidden frame will be set as the
     * owner of the dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     * <p>
     * NOTE: This constructor does not allow you to create an unowned
     * <code>XDialog</code>. To create an unowned <code>XDialog</code>
     * you must use either the <code>XDialog(Window)</code> or
     * <code>XDialog(Dialog)</code> constructor with an argument of
     * <code>null</code>.
     *
     * @param owner the <code>Frame</code> from which the dialog is displayed
     * @exception HeadlessException if <code>GraphicsEnvironment.isHeadless()</code>
     *     returns <code>true</code>.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog(Frame owner) {
        super(owner);
    }

    /**
     * Creates a dialog with the specified owner <code>Frame</code>, modality
     * and an empty title. If <code>owner</code> is <code>null</code>,
     * a shared, hidden frame will be set as the owner of the dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     * <p>
     * NOTE: This constructor does not allow you to create an unowned
     * <code>XDialog</code>. To create an unowned <code>XDialog</code>
     * you must use either the <code>XDialog(Window)</code> or
     * <code>XDialog(Dialog)</code> constructor with an argument of
     * <code>null</code>.
     *
     * @param owner the <code>Frame</code> from which the dialog is displayed
     * @param modal specifies whether dialog blocks user input to other top-level
     *     windows when shown. If <code>true</code>, the modality type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code>, otherwise the dialog is modeless.
     * @exception HeadlessException if <code>GraphicsEnvironment.isHeadless()</code>
     *     returns <code>true</code>.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog(Frame owner, boolean modal) {
        super(owner, modal);
    }

    /**
     * Creates a modeless dialog with the specified title and
     * with the specified owner frame.  If <code>owner</code>
     * is <code>null</code>, a shared, hidden frame will be set as the
     * owner of the dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     * <p>
     * NOTE: This constructor does not allow you to create an unowned
     * <code>XDialog</code>. To create an unowned <code>XDialog</code>
     * you must use either the <code>XDialog(Window)</code> or
     * <code>XDialog(Dialog)</code> constructor with an argument of
     * <code>null</code>.
     *
     * @param owner the <code>Frame</code> from which the dialog is displayed
     * @param title  the <code>String</code> to display in the dialog's
     *			title bar
     * @exception HeadlessException if <code>GraphicsEnvironment.isHeadless()</code>
     *     returns <code>true</code>.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog(Frame owner, String title) {
        super(owner, title);
    }

    /**
     * Creates a dialog with the specified title, owner <code>Frame</code>
     * and modality. If <code>owner</code> is <code>null</code>,
     * a shared, hidden frame will be set as the owner of this dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     * <p>
     * NOTE: Any popup components (<code>JComboBox</code>,
     * <code>JPopupMenu</code>, <code>JMenuBar</code>)
     * created within a modal dialog will be forced to be lightweight.
     * <p>
     * NOTE: This constructor does not allow you to create an unowned
     * <code>XDialog</code>. To create an unowned <code>XDialog</code>
     * you must use either the <code>XDialog(Window)</code> or
     * <code>XDialog(Dialog)</code> constructor with an argument of
     * <code>null</code>.
     *
     * @param owner the <code>Frame</code> from which the dialog is displayed
     * @param title  the <code>String</code> to display in the dialog's
     *     title bar
     * @param modal specifies whether dialog blocks user input to other top-level
     *     windows when shown. If <code>true</code>, the modality type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code> otherwise the dialog is modeless
     * @exception HeadlessException if <code>GraphicsEnvironment.isHeadless()</code>
     *     returns <code>true</code>.
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    /**
     * Creates a dialog with the specified title,
     * owner <code>Frame</code>, modality and <code>GraphicsConfiguration</code>.
     * If <code>owner</code> is <code>null</code>,
     * a shared, hidden frame will be set as the owner of this dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     * <p>
     * NOTE: Any popup components (<code>JComboBox</code>,
     * <code>JPopupMenu</code>, <code>JMenuBar</code>)
     * created within a modal dialog will be forced to be lightweight.
     * <p>
     * NOTE: This constructor does not allow you to create an unowned
     * <code>XDialog</code>. To create an unowned <code>XDialog</code>
     * you must use either the <code>XDialog(Window)</code> or
     * <code>XDialog(Dialog)</code> constructor with an argument of
     * <code>null</code>.
     *
     * @param owner the <code>Frame</code> from which the dialog is displayed
     * @param title  the <code>String</code> to display in the dialog's
     *     title bar
     * @param modal specifies whether dialog blocks user input to other top-level
     *     windows when shown. If <code>true</code>, the modality type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code>, otherwise the dialog is modeless.
     * @param gc the <code>GraphicsConfiguration</code>
     *     of the target screen device.  If <code>gc</code> is
     *     <code>null</code>, the same
     *     <code>GraphicsConfiguration</code> as the owning Frame is used.
     * @exception HeadlessException if <code>GraphicsEnvironment.isHeadless()</code>
     *     returns <code>true</code>.
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog(Frame owner, String title, boolean modal,
            GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
    }

    /**
     * Creates a modeless dialog without a title with the
     * specified <code>Dialog</code> as its owner.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param owner the owner <code>Dialog</code> from which the dialog is displayed
     *     or <code>null</code> if this dialog has no owner
     * @exception HeadlessException <code>if GraphicsEnvironment.isHeadless()</code>
     *     returns <code>true</code>.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog(Dialog owner) {
        super(owner);
    }

    /**
     * Creates a dialog with the specified owner <code>Dialog</code> and modality.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param owner the owner <code>Dialog</code> from which the dialog is displayed
     *     or <code>null</code> if this dialog has no owner
     * @param modal specifies whether dialog blocks user input to other top-level
     *     windows when shown. If <code>true</code>, the modality type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code>, otherwise the dialog is modeless.
     * @exception HeadlessException if <code>GraphicsEnvironment.isHeadless()</code>
     *     returns <code>true</code>.
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog(Dialog owner, boolean modal) {
        super(owner, modal);
    }

    /**
     * Creates a modeless dialog with the specified title and
     * with the specified owner dialog.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param owner the owner <code>Dialog</code> from which the dialog is displayed
     *     or <code>null</code> if this dialog has no owner
     * @param title  the <code>String</code> to display in the dialog's
     *			title bar
     * @exception HeadlessException if <code>GraphicsEnvironment.isHeadless()</code>
     *     returns <code>true</code>.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog(Dialog owner, String title) {
        super(owner, title);
    }

    /**
     * Creates a dialog with the specified title, modality
     * and the specified owner <code>Dialog</code>.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param owner the owner <code>Dialog</code> from which the dialog is displayed
     *     or <code>null</code> if this dialog has no owner
     * @param title  the <code>String</code> to display in the dialog's
     *	   title bar
     * @param modal specifies whether dialog blocks user input to other top-level
     *     windows when shown. If <code>true</code>, the modality type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code>, otherwise the dialog is modeless
     * @exception HeadlessException if <code>GraphicsEnvironment.isHeadless()</code>
     *     returns <code>true</code>.
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog(Dialog owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    /**
     * Creates a dialog with the specified title, owner <code>Dialog</code>,
     * modality and <code>GraphicsConfiguration</code>.
     *
     * <p>
     * NOTE: Any popup components (<code>JComboBox</code>,
     * <code>JPopupMenu</code>, <code>JMenuBar</code>)
     * created within a modal dialog will be forced to be lightweight.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param owner the owner <code>Dialog</code> from which the dialog is displayed
     *     or <code>null</code> if this dialog has no owner
     * @param title  the <code>String</code> to display in the dialog's
     *     title bar
     * @param modal specifies whether dialog blocks user input to other top-level
     *     windows when shown. If <code>true</code>, the modality type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code>, otherwise the dialog is modeless
     * @param gc the <code>GraphicsConfiguration</code>
     *     of the target screen device.  If <code>gc</code> is
     *     <code>null</code>, the same
     *     <code>GraphicsConfiguration</code> as the owning Dialog is used.
     * @exception HeadlessException if <code>GraphicsEnvironment.isHeadless()</code>
     *     returns <code>true</code>.
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog(Dialog owner, String title, boolean modal,
            GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
    }

    /**
     * Creates a modeless dialog with the specified owner <code>Window</code> and
     * an empty title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param owner the <code>Window</code> from which the dialog is displayed or
     *     <code>null</code> if this dialog has no owner
     * @exception HeadlessException when
     *    <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     *
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog(Window owner) {
        super(owner);
    }

    /**
     * Creates a dialog with the specified owner <code>Window</code>, modality
     * and an empty title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param owner the <code>Window</code> from which the dialog is displayed or
     *     <code>null</code> if this dialog has no owner
     * @param modalityType specifies whether dialog blocks input to other
     *     windows when shown. <code>null</code> value and unsupported modality
     *     types are equivalent to <code>MODELESS</code>
     * @exception HeadlessException when
     *    <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog(Window owner, ModalityType modalityType) {
        super(owner, modalityType);
    }

    /**
     * Creates a modeless dialog with the specified title and owner
     * <code>Window</code>.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param owner the <code>Window</code> from which the dialog is displayed or
     *     <code>null</code> if this dialog has no owner
     * @param title the <code>String</code> to display in the dialog's
     *     title bar or <code>null</code> if the dialog has no title
     * @exception java.awt.HeadlessException when
     *     <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     *
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog(Window owner, String title) {
        super(owner, title);
    }

    /**
     * Creates a dialog with the specified title, owner <code>Window</code> and
     * modality.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param owner the <code>Window</code> from which the dialog is displayed or
     *     <code>null</code> if this dialog has no owner
     * @param title the <code>String</code> to display in the dialog's
     *     title bar or <code>null</code> if the dialog has no title
     * @param modalityType specifies whether dialog blocks input to other
     *     windows when shown. <code>null</code> value and unsupported modality
     *     types are equivalent to <code>MODELESS</code>
     * @exception java.awt.HeadlessException when
     *     <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog(Window owner, String title, Dialog.ModalityType modalityType) {
        super(owner, title, modalityType);
    }

    /**
     * Creates a dialog with the specified title, owner <code>Window</code>,
     * modality and <code>GraphicsConfiguration</code>.
     * <p>
     * NOTE: Any popup components (<code>JComboBox</code>,
     * <code>JPopupMenu</code>, <code>JMenuBar</code>)
     * created within a modal dialog will be forced to be lightweight.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param owner the <code>Window</code> from which the dialog is displayed or
     *     <code>null</code> if this dialog has no owner
     * @param title the <code>String</code> to display in the dialog's
     *     title bar or <code>null</code> if the dialog has no title
     * @param modalityType specifies whether dialog blocks input to other
     *     windows when shown. <code>null</code> value and unsupported modality
     *     types are equivalent to <code>MODELESS</code>
     * @param gc the <code>GraphicsConfiguration</code> of the target screen device;
     *     if <code>null</code>, the <code>GraphicsConfiguration</code> from the owning
     *     window is used; if <code>owner</code> is also <code>null</code>, the
     *     system default <code>GraphicsConfiguration</code> is assumed
     * @exception java.awt.HeadlessException when
     *    <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XDialog(Window owner, String title, Dialog.ModalityType modalityType,
            GraphicsConfiguration gc) {
        super(owner, title, modalityType, gc);
    }
}
