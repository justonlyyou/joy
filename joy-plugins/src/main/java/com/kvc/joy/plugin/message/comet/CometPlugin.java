package com.kvc.joy.plugin.message.comet;

import com.kvc.joy.core.init.service.IJoyPlugin;
import com.kvc.joy.core.init.support.JoyPropeties;
import org.springframework.stereotype.Component;

/**
 * @author 唐玮琳
 * @time 14-3-3 下午9:06
 * @since 1.0.0
 */
@Component
public class CometPlugin implements IJoyPlugin {

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
        return JoyPropeties.PLUGIN_COMET_ENABLED;
    }

    @Override
    public String getCtxConfLocation() {
        return "classpath*:/conf/plugin-appCtx-comet.xml";
    }
}
