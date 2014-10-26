/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing;

import java.io.*;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * 将当前用户习惯性操作保存到本地磁盘
 * @author Kevice
 */
public class UserConfProperties extends Properties {

    private static String FILE = System.getProperty("user.home") +
            System.getProperty("file.separator") + "UserConfig.pro";
    private volatile static UserConfProperties userConfigProperties;

    private UserConfProperties() {
        try {
            FILE = URLDecoder.decode(FILE, "utf-8");
        } catch (UnsupportedEncodingException ex) {
        }
    }

    public static UserConfProperties getInstance() {
        if (userConfigProperties == null) {
            synchronized (UserConfProperties.class) {
                if (userConfigProperties == null) {
                    userConfigProperties = new UserConfProperties();
                }
            }
        }
        return userConfigProperties;
    }

    public boolean store() {
        try {
            store(new FileOutputStream(FILE), "");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean load() {
        InputStream in;
        try {
            in = new BufferedInputStream(new FileInputStream(FILE));
            load(in);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
