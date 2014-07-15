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

import org.joy.commons.io.FileTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.commons.log.Log;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Set;
import java.util.TreeSet;

/**
 * ClassPathLocationScanner for the file system.
 */
public class FileSystemClassPathLocationScanner implements ClassPathLocationScanner {

	protected static final Log LOG = LogFactory.getLog(FileSystemClassPathLocationScanner.class);

	public Set<String> findResourceNames(String location, URL locationUrl) throws IOException {
		String filePath = FileTool.toFile(locationUrl).getPath();
		File folder = new File(filePath);
		if (!folder.isDirectory()) {
			LOG.debug("Skipping path as it is not a directory: " + filePath);
			return new TreeSet<String>();
		}

		String classPathRootOnDisk = filePath.substring(0, filePath.length() - location.length());
		if (!classPathRootOnDisk.endsWith("/")) {
			classPathRootOnDisk = classPathRootOnDisk + "/";
		}
		LOG.debug("Scanning starting at classpath root in filesystem: " + classPathRootOnDisk);
		return findResourceNamesFromFileSystem(classPathRootOnDisk, location, folder);
	}

	/**
	 * Finds all the resource names contained in this file system folder.
	 * 
	 * @param classPathRootOnDisk The location of the classpath root on disk,
	 *            with a trailing slash.
	 * @param scanRootLocation The root location of the scan on the classpath,
	 *            without leading or trailing slashes.
	 * @param folder The folder to look for resources under on disk.
	 * @return The resource names;
	 * @throws IOException when the folder could not be read.
	 */
	/* private -> for testing */
	Set<String> findResourceNamesFromFileSystem(String classPathRootOnDisk, String scanRootLocation, File folder)
			throws IOException {
		LOG.debug("Scanning for resources in path: " + folder.getPath() + " (" + scanRootLocation + ")");

		Set<String> resourceNames = new TreeSet<String>();

		File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.canRead()) {
                    if (file.isDirectory()) {
                        resourceNames.addAll(findResourceNamesFromFileSystem(classPathRootOnDisk, scanRootLocation, file));
                    } else {
                        resourceNames.add(toResourceNameOnClasspath(classPathRootOnDisk, file));
                    }
                }
            }
        }

		return resourceNames;
	}

	/**
	 * Converts this file into a resource name on the classpath.
	 * 
	 * @param classPathRootOnDisk The location of the classpath root on disk,
	 *            with a trailing slash.
	 * @param file The file.
	 * @return The resource name on the classpath.
	 * @throws IOException when the file could not be read.
	 */
	private String toResourceNameOnClasspath(String classPathRootOnDisk, File file) throws IOException {
		String fileName = URLDecoder.decode(file.toURI().toURL().getFile(), "UTF-8");

		// Cut off the part on disk leading to the root of the classpath
		// This leaves a resource name starting with the scanRootLocation,
		// with no leading slash, containing subDirs and the fileName.
		return fileName.substring(classPathRootOnDisk.length());
	}
}
