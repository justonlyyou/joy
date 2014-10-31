package org.joy.plugin.monitor.jdbc.druid;

import com.alibaba.druid.filter.config.ConfigTools;
import org.joy.core.init.service.IPlugin;
import org.joy.core.init.support.properties.JoyProperties;
import org.springframework.stereotype.Component;

/**
 * druid插件，使用阿里的druid项目，它本质上是个连接池，主要用它jdbc监控和连接密码加密功能
 *
 * @author Kevice
 * @time 14-2-27 下午9:57
 * @since 1.0.0
 */
@Component
public class DruidPlugin implements IPlugin {

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


