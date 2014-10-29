/**
 * Copyright 2010-2013 Axel Fontaine and the many contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.joy.commons.scanner.classpath;

import org.joy.commons.exception.SystemException;
import org.joy.commons.io.IoTool;
import org.joy.commons.scanner.support.Resource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;

/**
 * A resource on the classpath.
 *
 * @since 1.0.0
 */
public class ClassPathResource implements Comparable<ClassPathResource>, Resource {
    /**
     * The location of the resource on the classpath.
     */
    private final String location;

    /**
     * Creates a new ClassPathResource.
     *
     * @param location The location of the resource on the classpath.
     * @since 1.0.0
     */
    public ClassPathResource(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public String getLocationOnDisk() {
        URL url = getUrl();
        if (url == null) {
            throw new SystemException("Unable to location resource on disk: " + location);
        }
        try {
            return URLDecoder.decode(url.getPath(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new SystemException(e, "Unknown encoding: UTF-8");
        }
    }

    /**
     * @return The url of this resource.
     * @since 1.0.0
     */
    private URL getUrl() {
        return getClassLoader().getResource(location);
    }

    /**
     * @return The classloader to load the resource with.
     * @since 1.0.0
     */
    private ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public String loadAsString(String encoding) {
        InputStream inputStream = getClassLoader().getResourceAsStream(location);
        if (inputStream == null) {
            throw new SystemException("Unable to obtain inputstream for resource: " + location);
        }
        Reader reader = new InputStreamReader(inputStream, Charset.forName(encoding));
        return IoTool.toString(reader);
    }

    public byte[] loadAsBytes() {
        InputStream inputStream = getClassLoader().getResourceAsStream(location);
        if (inputStream == null) {
            throw new SystemException("Unable to obtain inputstream for resource: " + location);
        }
        return IoTool.toByteArray(inputStream);
    }

    public String getFilename() {
        return location.substring(location.lastIndexOf("/") + 1);
    }

    public boolean exists() {
        return getUrl() != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ClassPathResource that = (ClassPathResource) o;

        return location.equals(that.location);

    }

    @Override
    public int hashCode() {
        return location.hashCode();
    }

    public int compareTo(ClassPathResource o) {
        return location.compareTo(o.location);
    }
}
