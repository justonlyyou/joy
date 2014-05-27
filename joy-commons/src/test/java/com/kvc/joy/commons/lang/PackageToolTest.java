package com.kvc.joy.commons.lang;

import com.kvc.joy.commons.lang.string.StringTool;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-10 下午9:41:47
 */
public class PackageToolTest {
	
	@Test
	public void testGetClassesInPackage() {
		// in file
		String packageName = PackageTool.class.getPackage().getName();
		Set<Class<?>> classes = PackageTool.getClassesInPackage(packageName, true);
		Assert.assertTrue(classes.contains(PackageTool.class));
		Assert.assertTrue(classes.contains(StringTool.class));
		Assert.assertTrue(classes.contains(BooleanTool.class));
		
		// in jar
		classes = PackageTool.getClassesInPackage("org.apache.commons.lang3", true);
		Assert.assertTrue(classes.contains(org.apache.commons.lang3.StringUtils.class));
		Assert.assertTrue(classes.contains(org.apache.commons.lang3.BooleanUtils.class));
	}
	
	@Test
	public void testGetPackages() {
		// in file
		Set<String> packages = PackageTool.getPackages("com.kvc.joy.commons.*", true);
		Assert.assertTrue(packages.contains("com.kvc.joy.commons.lang"));
		Assert.assertTrue(packages.contains("com.kvc.joy.commons.enums"));
		
		// in jar using package pattern
		packages = PackageTool.getPackages("net.**.exception", true);
		Assert.assertTrue(packages.contains("net.sourceforge.pinyin4j.format.exception"));
	}

}
