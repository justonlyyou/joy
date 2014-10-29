package org.joy.core.persistence.flyway.service.impl;

import com.googlecode.flyway.core.Flyway;
import org.joy.commons.exception.SystemException;
import org.joy.core.persistence.flyway.service.IRdbObjectsInitService;
import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.DbSupportFactory;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 关系型数据库对象初始化服务, 利用Flyway对数据库脚本进行初始化或升级
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月13日 下午8:27:07
 */
public class RdbObjectsInitService implements IRdbObjectsInitService {

	private DataSource dataSource;
    /**
     * 各版本数据库脚本存放的总目录，其下会有如mysql、oracle等目录，这些目录下放的是sql脚本的文件,
     * 多个以半角逗号分隔，至少包括"sql"(它是joy内置脚本总目录)
     */
	private String locations;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Flyway createFlyway() {
		Flyway flyway = new Flyway();
		flyway.setDataSource(dataSource);
		flyway.setTable("t_sys_db_schema_version");
        String[] locs = processLocations();
		flyway.setLocations(locs);
		flyway.setInitOnMigrate(true);
		return flyway;
	}

	@Override
	public void migrate(Flyway flyway) {
		flyway.migrate();
	}

    private String[] processLocations() {
        String[] locs = locations.split(",");
        String dbType = getDatabaseType();
        if (dbType != null) {
            for (int i = 0; i < locs.length; i++) {
                locs[i] += File.separator + dbType;
            }
        }
        return locs;
    }

    private String getDatabaseType() {
        Connection conn = null;
        String dbType = null;
        try {
            conn = dataSource.getConnection();
            DbSupport dbSupport = DbSupportFactory.createDbSupport(conn);
            dbType = dbSupport.getDatabaseType().getName().toLowerCase();
        } catch (SQLException e) {
            throw new SystemException("获取数据库类型出错！", e);
        } finally {
            JdbcTool.closeConnection(conn);
        }
        return dbType;
    }

	public void setLocations(String locations) {
		this.locations = locations;
	}

}