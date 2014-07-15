package org.joy.plugin.message.comet;

import org.joy.core.init.service.IPlugin;
import org.joy.core.init.support.properties.JoyProperties;
import org.springframework.stereotype.Component;

/**
 * @author Kevice
 * @time 14-3-3 下午9:06
 * @since 1.0.0
 */
@Component
public class CometPlugin implements IPlugin {

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
        return "comet";
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
        return JoyProperties.PLUGIN_COMET_ENABLED;
    }

    @Override
    public String getCtxConfLocation() {
        return "classpath*:/conf/plugin-appCtx-comet.xml";
    }
}
