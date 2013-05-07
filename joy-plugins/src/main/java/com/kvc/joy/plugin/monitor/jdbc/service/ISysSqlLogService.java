package com.kvc.joy.plugin.monitor.jdbc.service;

import com.kvc.joy.plugin.monitor.jdbc.model.vo.ParamMsg;

/**
 * 系统sql执行性能日志服务接口
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月3日 下午10:21:52
 */
public interface ISysSqlLogService {
	
	/**
	 * 保存执行日志
	 * 
	 * @param param 参数对象
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月3日 下午10:24:01
	 */
	void saveLog(ParamMsg param);
	
}
