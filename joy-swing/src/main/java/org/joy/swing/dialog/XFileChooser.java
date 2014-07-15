/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.dialog;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Kevice
 */
public class XFileChooser extends JFileChooser {

    /**
     * Constructs a <code>XFileChooser</code> pointing to the user's
     * default directory. This default depends on the operating system.
     * It is typically the "My Documents" folder on Windows, and the
     * user's home directory on Unix.
     */
    public XFileChooser() {
        super();
    }

    /**
     * Constructs a <code>XFileChooser</code> using the given path.
     * Passing in a <code>null</code>
     * string causes the file chooser to point to the user's default directory.
     * This default depends on the operating system. It is
     * typically the "My Documents" folder on Windows, and the user's
     * home directory on Unix.
     *
     * @param currentDirectoryPath  a <code>String</code> giving the path
     *				to a file or directory
     */
    public XFileChooser(String currentDirectoryPath) {
        super(currentDirectoryPath);
    }

    /**
     * Constructs a <code>XFileChooser</code> using the given <code>File</code>
     * as the path. Passing in a <code>null</code> file
     * causes the file chooser to point to the user's default directory.
     * This default depends on the operating system. It is
     * typically the "My Documents" folder on Windows, and the user's
     * home directory on Unix.
     *
     * @param currentDirectory  a <code>File</code> object specifying
     *				the path to a file or directory
     */
    public XFileChooser(File currentDirectory) {
        super(currentDirectory);
    }

    /**
     * Constructs a <code>XFileChooser</code> using the given
     * <code>FileSystemView</code>.
     */
    public XFileChooser(FileSystemView fsv) {
        super(fsv);
    }

    /**
     * Constructs a <code>XFileChooser</code> using the given current directory
     * and <code>FileSystemView</code>.
     */
    public XFileChooser(File currentDirectory, FileSystemView fsv) {
        super(currentDirectory, fsv);
    }

    /**
     * Constructs a <code>XFileChooser</code> using the given current directory
     * path and <code>FileSystemView</code>.
     */
    public XFileChooser(String currentDirectoryPath, FileSystemView fsv) {
        super(currentDirectoryPath, fsv);
    }
}
