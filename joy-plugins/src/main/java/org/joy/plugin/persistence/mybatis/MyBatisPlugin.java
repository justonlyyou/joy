package org.joy.plugin.persistence.mybatis;

import org.joy.core.init.service.IPlugin;
import org.joy.core.init.support.properties.JoyProperties;
import org.springframework.stereotype.Component;

/**
 * @since 1.0.0
 * @author Kevice
 * @time 2014年10月15日 下午22:36:42
 */
@Component
public class MyBatisPlugin implements IPlugin {

    @Override
    public String getSqlMigrationPrefix() {
        return null;
    }

    @Override
    public String getPoPackage() {
        return null;
    }

    @Override
    public String getName() {
        return "MyBatis";
    }

    @Override
    public int getInitPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void startup() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public boolean isEnabled() {
        return JoyProperties.PLUGIN_MYBATIS_ENABLED;
    }

    @Override
    public String getCtxConfLocation() {
        return "classpath*:/conf/plugin-appCtx-mybatis.xml";
    }
}
