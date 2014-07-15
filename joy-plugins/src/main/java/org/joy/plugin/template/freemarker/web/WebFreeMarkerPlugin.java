package org.joy.plugin.template.freemarker.web;

import org.joy.core.init.service.IJoyPlugin;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kevice
 * @time 2014-7-13 下午21:48:26
 */
@Component
public class WebFreeMarkerPlugin implements IJoyPlugin {

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
        return "FreeMarker-web";
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
        return true; //TODO
    }

    @Override
    public String getCtxConfLocation() {
        return "classpath*:/conf/plugin-appCtx-freemarker-web.xml";
    }
}
