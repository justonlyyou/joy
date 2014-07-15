package org.joy.commons.io;

import org.junit.Test;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-5-18 下午11:57:13
 */
public class PathToolTest {
	
	@Test
	public void demo() {
		System.out.println(PathTool.getClassesPath());
		System.out.println(PathTool.getWebInfPath());
		System.out.println(PathTool.getWebRootPath());
	}

}
