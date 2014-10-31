package org.joy.plugin.office.excel.jxl;

import java.io.InputStream;

/**
 * excel数据导入器接口
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2011-11-25 上午9:07:18
 */
public interface IExcelImporter {

	/**
	 * 执行导入
     *
	 * @param inputStream 上传的excel文件的输入流
	 * @return 执行结果信息
     * @since 1.0.0
     * @author Kevice
     * @time 2011-11-25 上午9:07:18
	 */
	String doImport(InputStream inputStream);

}
