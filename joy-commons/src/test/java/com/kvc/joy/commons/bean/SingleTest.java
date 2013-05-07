package com.kvc.joy.commons.bean;

import junit.framework.Assert;

import org.junit.Test;

import com.kvc.joy.commons.bean.Single;
import com.kvc.joy.commons.support.ICommand;

/**
 * com.kvc.joy.common.bean.Single单元测试用例
 * 
 * @author 唐玮琳
 * @time 2013-4-2 下午9:10:40
 */
public class SingleTest {

	@Test
	public void testToString() {
		final Single<String> single = new Single<String>();
		new ICommand() {

			private static final long serialVersionUID = 2176028612149873088L;

			@Override
			public void execute() {
				String str = "str";
				single.setValue(str);
			}
		}.execute();
		String value = single.getValue();
		System.out.println("value: " + value);  // 使用内部类方法中设置的值
		Assert.assertEquals("str", single.toString());
	}

}
