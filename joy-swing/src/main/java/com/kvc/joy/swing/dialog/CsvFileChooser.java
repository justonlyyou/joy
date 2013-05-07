package com.kvc.joy.swing.dialog;

//~--- non-JDK imports --------------------------------------------------------

//~--- JDK imports ------------------------------------------------------------

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.kvc.joy.commons.lang.string.I18nTool;
import com.kvc.joy.swing.SwingUtility;

/**
 * A JFileChooser subclass that provides validating the input directory or file
 * before approve button performs
 *
 * @author Kevice
 */
public abstract class CsvFileChooser extends JFileChooser {

    /**
     * type: exporter
     */
    public static final int EXPORTER = 2;
    /**
     * type: importer
     */
    public static final int IMPORTER = 1;
    public static final String[] ALL_SUPPORT_TYPE = {
        "Csv", "Excel"
    };
    private int type;    // importer or exporter
    private File defaultFile = null;

    /**
     * Constructs ...
     *
     *
     * @param type
     */
    public CsvFileChooser(int type) {
        this(type, null);
    }

    public CsvFileChooser(int type, File defaultFile) {
        this.type = type;
        if (defaultFile != null) {
            setSelectedFile(defaultFile);
            this.defaultFile = defaultFile;
        }
        init();
    }

    /**
     * Method description
     *
     */
    private void init() {
        setMultiSelectionEnabled(false);    // 不可多选
        setAcceptAllFileFilterUsed(false);    // 不启用"接受所有文件"过滤器
        FileFilter currentFileFilter = null;
        if (type == EXPORTER) {
            setDialogTitle(I18nTool.getLocalStr("Export"));
        } else {    // importer
            //TODO:for next version
//            currentFileFilter = new FileNameExtensionFilter("Excel/Csv", "xls", "csv");
            addChoosableFileFilter(currentFileFilter);
            setDialogTitle(I18nTool.getLocalStr("Import"));
        }
        addChoosableFileFilter(new FileNameExtensionFilter("Csv", "csv"));
        //TODO:for next version
//        addChoosableFileFilter(new FileNameExtensionFilter("Excel", "xls"));
        if (currentFileFilter != null) {
            setFileFilter(currentFileFilter);
        }
        if (type == EXPORTER) {
            addPropertyChangeListener(FILE_FILTER_CHANGED_PROPERTY, new PropertyChangeListener() {

                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if (evt.getNewValue() != evt.getOldValue()) {

                        String currentExpansion = getCurrentExpansion();
                        File selectedFile = getSelectedFile();
                        if (selectedFile == null) {
                            selectedFile = defaultFile;
                        }
                        if (selectedFile != null) {
                            if (!selectedFile.getName().toLowerCase().endsWith(currentExpansion)) {
                                String fileName = selectedFile.getName();
                                int splitIndex = fileName.indexOf(".");
                                if (splitIndex >= 1) {
                                    fileName = fileName.substring(0, splitIndex);
                                }
                                fileName = fileName.concat("." + currentExpansion);
                                defaultFile = new File(fileName);
                                setSelectedFile(defaultFile);
                            }
                        }
                    }
                }
            });
        }
        showOpenDialog(SwingUtility.getInstance().getMainFrame());
    }

    protected String getCurrentExpansion() {
        return getFileFilter().getDescription().equals("Excel") ? "xls" : "csv";
    }

    /**
     * Method description
     *
     */
    @Override
    public void approveSelection() {
        File selectedFile = getSelectedFile();
        String fileName = selectedFile.getPath();
        String msgKey = null;

        if (type == IMPORTER) {
            if (fileName.toLowerCase().endsWith(".csv") == false && fileName.toLowerCase().endsWith(".xls") == false) {
                msgKey = "FilePathIsInvalid";
            } else {
                if (selectedFile.exists() == false) {
                    msgKey = "CsvFileIsNotExist";
                }
            }
        } else {    // EXPORTER
            if (selectedFile.exists()) {
                if (selectedFile.isDirectory()) {    // 目录
                    msgKey = "InputAFileName";
                } else {    // 文件
                    int option = JOptionPane.showConfirmDialog(this, I18nTool.getLocalStr("FileExistReplaceIt"), null, JOptionPane.YES_NO_OPTION);

                    if (option != JOptionPane.YES_OPTION) {
                        return;
                    }
                }
            } else {    // 非法、文件、不存在的目录
                File parentFile = selectedFile.getParentFile();

                if ((parentFile != null) && parentFile.isDirectory() && !fileName.endsWith("/") && !fileName.endsWith("\\")) {
                    if (fileName.toLowerCase().endsWith("." + getCurrentExpansion()) == false) {
                        fileName += "." + getCurrentExpansion();
                        selectedFile = new File(fileName);
                        try {
                            selectedFile.createNewFile();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    msgKey = "SpecifiedDirectoryNotExist";
                }
            }
        }

        if (msgKey == null) {
            super.approveSelection();
            setSelectedFile(selectedFile);
            doImportOrExport(selectedFile);
        } else {
            JOptionPane.showMessageDialog(this, I18nTool.getLocalStr(msgKey));
        }
    }

    /**
     * write your import or export operation in this method
     *
     * @param csvFile
     */
    public abstract void doImportOrExport(File csvFile);

    /**
     * Method description
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        new CsvFileChooser(IMPORTER) {

            @Override
            public void doImportOrExport(File csvFile) {

                // write your import or export operation here
            }
        };
    }
}
