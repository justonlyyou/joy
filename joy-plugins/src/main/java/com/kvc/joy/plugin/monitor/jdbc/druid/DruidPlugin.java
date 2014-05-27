package com.kvc.joy.plugin.monitor.jdbc.druid;

import com.alibaba.druid.filter.config.ConfigTools;
import com.kvc.joy.core.init.service.IJoyPlugin;
import com.kvc.joy.core.init.support.properties.JoyProperties;
import org.springframework.stereotype.Component;

/**
 * @author Kevice
 * @time 14-2-27 下午9:57
 * @since 1.0.0
 */
@Component
public class DruidPlugin implements IJoyPlugin {

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
        return "Druid";
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
        return JoyProperties.PLUGIN_DRUID_ENABLED;
    }

    @Override
    public String getCtxConfLocation() {
        return "classpath*:/conf/plugin-appCtx-druid.xml";
    }

    public static void main(String[] args) throws Exception {
        String[] p = {""};
        ConfigTools.main(p);
    }
}


