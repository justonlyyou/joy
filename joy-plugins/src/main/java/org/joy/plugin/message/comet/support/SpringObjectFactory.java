package org.joy.plugin.message.comet.support;

import org.joy.plugin.message.comet.core.ObjectFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

public class SpringObjectFactory implements ObjectFactory {

    private WebApplicationContext context;

    public SpringObjectFactory() {
    }

    public void init(ServletContext servletContext) {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.context = context;
    }

    public Object getObject(String objectName) {
        return context.getBean(objectName);
    }

}
