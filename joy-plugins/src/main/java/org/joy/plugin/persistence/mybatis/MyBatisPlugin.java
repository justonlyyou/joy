package org.joy.plugin.persistence.mybatis;

import org.joy.core.init.service.IPlugin;
import org.joy.core.init.support.properties.JoyProperties;
import org.joy.plugin.support.PluginBeanFactory;
import org.springframework.stereotype.Component;

/**
 * MyBatis插件, 封装了MyBatis的常用操作，最主要是能以面向对象的方式使用MyBatis
 *
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
        PluginBeanFactory.getMapperManager().manage();
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
