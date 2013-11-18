package com.kvc.joy.core.persistence.flyway.service.impl;

import javax.sql.DataSource;

import com.googlecode.flyway.core.Flyway;
import com.kvc.joy.core.persistence.flyway.service.IRdbObjectsInitService;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月13日 下午8:27:07
 */
public class RdbObjectsInitService implements IRdbObjectsInitService {

	private DataSource dataSource;
	private String locations;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Flyway createFlyway() {
		Flyway flyway = new Flyway();
		flyway.setDataSource(dataSource);
		flyway.setTable("t_sys_db_schema_version");
		String[] locs = locations.split(",");
		flyway.setLocations(locs);
		flyway.setInitOnMigrate(true);
		return flyway;
	}

	@Override
	public void migrate(Flyway flyway) {
		flyway.migrate();
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}

}