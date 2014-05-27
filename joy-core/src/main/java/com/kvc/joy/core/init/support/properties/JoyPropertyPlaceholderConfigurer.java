package com.kvc.joy.core.init.support.properties;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Kevice
 * @time 14-3-16 下午3:28
 * @since 1.0.0
 */
public class JoyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    protected Log log = LogFactory.getLog(JoyPropertyPlaceholderConfigurer.class);

    @Override
    protected Properties mergeProperties() throws IOException {
        Properties properties = super.mergeProperties();
        properties.putAll(JoyProperties.getDbProperties()); // 加入数据库中存储的properties
        return properties;
    }

}
