package org.joy.web.support.interceptor;

/**
 * 日期时间拦截器的可拦截接口,要使用日期时间拦截器,page必须实现该接口
 */
public interface DateTimeInterceptable {
	/**
	 * 返回可拦截的属性名称
	 * @return String[]
	 */
	String[] dateTimeProperties();
}
