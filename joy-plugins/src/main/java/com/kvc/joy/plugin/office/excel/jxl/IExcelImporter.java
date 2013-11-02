package com.kvc.joy.plugin.office.excel.jxl;

import java.io.InputStream;

/**
 * excel数据导入器接口
 * @author <b>唐玮琳</b>
 */
public interface IExcelImporter {

	/**
	 * 执行导入
	 * @param inputStream 上传的excel文件的输入流
	 * @return 执行结果信息
	 */
	String doImport(InputStream inputStream);

}
