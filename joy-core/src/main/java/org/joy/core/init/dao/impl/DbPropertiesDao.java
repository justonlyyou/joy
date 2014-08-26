package org.joy.core.init.dao.impl;

import org.joy.commons.exception.SystemException;
import org.joy.commons.lang.string.StringTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.commons.scanner.classpath.ClassPathScanner;
import org.joy.commons.scanner.support.Resource;
import org.joy.core.init.dao.IDbPropertiesDao;
import org.joy.core.init.support.properties.JoyProperties;
import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.DbSupportFactory;
import org.joy.core.persistence.jdbc.support.db.Table;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.sql.Connection;
import java.text.MessageFormat;
import java.util.*;

/**
 * @author Kevice
 * @time 14-3-17 上午7:17
 * @since 1.0.0
 */
public class DbPropertiesDao implements IDbPropertiesDao {

    protected Log log = LogFactory.getLog(DbPropertiesDao.class);

    /**
     * @author Kevice
     * @time 14-3-17 上午7:17
     * @since 1.0.0
     */
    protected boolean isTableExists(Connection connection) {
        DbSupport dbSupport = DbSupportFactory.createDbSupport(connection);
        Table table = dbSupport.getCurrentSchema().getTable(TABLE_NAME);
        boolean exists = table.exists();
        log.debug("表" + TABLE_NAME + "是否存在：" + exists);
        return exists;
    }

    /**
     * @author Kevice
     * @time 14-3-17 上午7:17
     * @since 1.0.0
     */
    protected List<Resource> getScriptResources() {
        log.debug("加载joy基础脚本的文件...");
        Resource[] resources = ClassPathScanner.scanForResources(SCRIPT_CLASSPATH, SCRIPT_PREFIX, SCRIPT_SUFFIX);
        List<Resource> resourceList = Arrays.asList(resources);
        Collections.sort(resourceList, new Comparator<Resource>() {
            @Override
            public int compare(Resource o1, Resource o2) {
                return o1.getFilename().substring(0, 13).compareTo(o2.getFilename().substring(0, 13));
            }
        });
        log.debug("加载到的joy基础脚本的文件数为：" + resourceList.size());
        return resourceList;
    }

    /**
     * @author Kevice
     * @time 14-3-17 上午7:17
     * @since 1.0.0
     */
    protected void createTable(Connection connection, Resource createTable) {
        log.debug("创建表" + TABLE_NAME + "...");
        String createTableSql = createTable.loadAsString("UTF-8");
        JdbcTool.execute(connection, createTableSql);
        log.debug("表" + TABLE_NAME + "创建完成");
    }

    /**
     * @author Kevice
     * @time 14-3-17 上午7:17
     * @since 1.0.0
     */
    public String findScriptVersion(Connection connection) {
        log.debug("查找joy基础脚本的版本...");
        String sqlPartern = "SELECT property_value FROM {0} WHERE property_name = ''{1}''";
        String sql = MessageFormat.format(sqlPartern, TABLE_NAME, SCRIPT_VERSION_PROPERTY);
        String version = JdbcTool.queryForString(connection, sql);
        log.debug("joy基础脚本的版本为：" + version);
        return version;
    }

    protected String getVersionUpdateSql(Resource resource) {
        String lastVersion = resource.getFilename().substring(0, 13);
        String sqlPartern = "UPDATE {0} SET property_value = ''{1}'' WHERE property_name = ''{2}'';";
        return MessageFormat.format(sqlPartern, TABLE_NAME, lastVersion, SCRIPT_VERSION_PROPERTY);
    }

    /**
     * @author Kevice
     * @time 14-3-17 上午7:17
     * @since 1.0.0
     */
    protected void initTableData(Connection connection, String scriptVersion, List<Resource> resourceList) {
        log.debug("初始化表" + TABLE_NAME + "的数据...");
        boolean match = StringTool.isBlank(scriptVersion);
        if (match) {
            log.debug("首次(创建表后)初始化表" + TABLE_NAME + "的数据");
        }
        List<String> sqlList = new ArrayList<String>();
        for (int i = 1; i < resourceList.size(); i++) {
            Resource resource = resourceList.get(i);
            String filename = resource.getFilename();
            if (match) {
                String sqls = resource.loadAsString("UTF-8");
                Collections.addAll(sqlList, sqls.split(";"));
            } else if (filename.startsWith(scriptVersion)) {
                match = true;
            }
        }

        if (sqlList.isEmpty() == false) {
            String updateVersionSql = getVersionUpdateSql(resourceList.get(resourceList.size() - 1));
            sqlList.add(updateVersionSql);
            JdbcTool.executeBatch(connection, sqlList);
            log.debug("完成对表" + TABLE_NAME + "数据的初始化");
        } else {
            log.debug("表" + TABLE_NAME + "没有需要初始化的数据");
        }
    }

    /**
     * @author Kevice
     * @time 14-3-17 上午7:17
     * @since 1.0.0
     */
    public Properties findProperties(Connection connection) {
        log.info("加载表" + TABLE_NAME + "中的Properties...");
        Properties properties = new Properties();
        String sqlPartern = "SELECT property_name, property_value FROM {0} WHERE property_name <> ''{1}''";
        String sql = MessageFormat.format(sqlPartern, TABLE_NAME, SCRIPT_VERSION_PROPERTY);
        List<Map<String, String>> maps = JdbcTool.queryForList(connection, sql);
        for (Map<String, String> result : maps) {
            properties.setProperty(result.get("property_name"), result.get("property_value"));
        }

        log.info("加载到表" + TABLE_NAME + "中的Properties个数为：" + properties.size());
        return properties;
    }

    /**
     * @author Kevice
     * @time 14-3-17 上午7:17
     * @since 1.0.0
     */
    public Properties initTable() {
        log.info("初始化表" + TABLE_NAME + "，并加载该表的properties...");
        Connection connection = null;
        try {
            connection = JdbcTool.getConnectionByJndi(JoyProperties.DB_JNDI);
            if (connection == null) {
                throw new SystemException("连接数据库失败！JNDI：" + JoyProperties.DB_JNDI);
            }
            List<Resource> scriptResources = getScriptResources();
            boolean exists = isTableExists(connection);
            if(exists == false) {
                createTable(connection, scriptResources.get(0));
            }
            String scriptVersion = findScriptVersion(connection);
            initTableData(connection, scriptVersion, scriptResources);

            return findProperties(connection);
        } catch (Exception e) {
            throw new SystemException("加载properties出错！", e);
        } finally {
            JdbcTool.closeConnection(connection);
        }
    }

}
