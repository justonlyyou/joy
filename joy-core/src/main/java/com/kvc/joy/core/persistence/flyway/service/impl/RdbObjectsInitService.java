package com.kvc.joy.core.persistence.flyway.service.impl;

import com.googlecode.flyway.core.Flyway;
import com.kvc.joy.core.init.support.properties.JoyProperties;
import com.kvc.joy.core.persistence.flyway.service.IRdbObjectsInitService;

import javax.sql.DataSource;
import java.io.File;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月13日 下午8:27:07
 */
public class RdbObjectsInitService implements IRdbObjectsInitService {

	private DataSource dataSource;
    /**
     * 各版本数据库脚本存放的总目录，其下会有如mysql、oracle等目录，这些目录下放的是sql脚本的文件,多个以半角逗号分隔，至少包括"sql"(它是joy内置脚本总目录)
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
        for (int i = 0; i < locs.length; i++) {
            locs[i] += File.separator + JoyProperties.DB_TYPE.toLowerCase();
        }
        return locs;
    }

	public void setLocations(String locations) {
		this.locations = locations;
	}

}