package com.kvc.joy.plugin.office.excel.jxl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.commons.lang.string.StringTool;

/**
 * excel数据导入器抽象类
 * @author <b>唐玮琳</b>
 */
public abstract class AbstractExcelImporter implements IExcelImporter {

	private static final Logger logger = LoggerFactory.getLogger(AbstractExcelImporter.class);

	/**
	 * List<字段名>
	 */
	protected List<String> fieldList = new ArrayList<String>();

	/**
	 * 上传的excel文件的输入流
	 */
	protected InputStream inputStream;

	/**
	 * excel行对象列表
	 */
	protected List<Object> rowObjectList;

	/**
	 * 第一个sheet页
	 */
	protected Sheet sheet;

	public AbstractExcelImporter() {
		initFieldList();
	}

	/**
	 * 获取行对象对应的类
	 */
	protected abstract Class<Object> getRowObjectClass();

	/**
	 * 初始化字段名列表
	 */
	protected abstract void initFieldList();

	/**
	 * 检查模板是否正确
	 * @return 模板是否正确
	 */
	private boolean checkTemplate() {
		boolean success = true;
		try {
			Workbook workbook = Workbook.getWorkbook(inputStream);
			sheet = workbook.getSheet(0);
			if (getSheetName().equals(sheet.getName()) == false) {
				success = false;
			}
		} catch (Exception ex) {
			logger.warn("检查模板出错!", ex);
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}

	/**
	 * 取得第一个sheet页的名称
	 * @return sheet页的名称
	 */
	protected abstract String getSheetName();

	/**
	 * 将excel的每行包装成对象
	 */
	protected void wrapRowObjects() {
		try {
			int rows = sheet.getRows();
			rowObjectList = new ArrayList<Object>(rows - 1); // 扣掉列头
			Map<String, Method> setterMap = new HashMap<String, Method>(fieldList.size());
			Class<Object> rowObjectClass = getRowObjectClass();
			for (int row = 1; row < rows; row++) {
				Cell[] rowCells = sheet.getRow(row);
				Object rowObject = rowObjectClass.newInstance();
				boolean shouldAdd = false;
				for (int column = 0; column < rowCells.length; column++) {
					Cell cell = rowCells[column];
					String fieldName = fieldList.get(column);
					Method setter = setterMap.get(fieldName);
					if (setter == null) {
						Field field = rowObjectClass.getDeclaredField(fieldName);
						String setterName = "set" + Character.toTitleCase(fieldName.charAt(0)) + fieldName.substring(1);
						setter = rowObjectClass.getMethod(setterName, new Class[] { field.getType() });
						setterMap.put(fieldName, setter);
					}
					String valueStr = cell.getContents();
					Object value = valueStr;
					CellType type = cell.getType();
					if (type == CellType.NUMBER) {
						Class<?> argType = setter.getParameterTypes()[0];
						if (argType == Integer.class) {
							value = Integer.valueOf(valueStr);
						} else if (argType == Long.class) {
							value = Long.valueOf(valueStr);
						} else if (argType == Double.class) {
							value = Double.valueOf(valueStr);
						} else if (argType == Float.class) {
							value = Float.valueOf(valueStr);
						}
					}
					if (value != null && !value.toString().trim().equals("")) {
						shouldAdd = true;
						setter.invoke(rowObject, new Object[] { value });	
					}
				}
				if (shouldAdd) {
					rowObjectList.add(rowObject);
				}
			}
		} catch (Exception ex) {
			logger.warn("读取excel数据出错!", ex);
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 导入
	 * @param xlsFile
	 * @return
	 */
	public String doImport(InputStream inputStream) {
		String msg = "导入成功！";
		this.inputStream = inputStream;
		if (checkTemplate() == false) {
			msg = "请选择正确的模板!";
		} else {
			wrapRowObjects();
			try {
				check();
				save();
			} catch (ValidateException ex) {
				msg = ex.getMessage();
			}
		}
		return msg;
	}

	/**
	 * 检查数据合法性
	 * @throws ValidateException
	 */
	protected abstract void check() throws ValidateException;

	/**
	 * 检查导入的值
	 * @param value 导入的值
	 * @param name 名称描述
	 * @param regex 正则表达式
	 * @param isRequire 是否必填项
	 * @throws ValidateException
	 */
	protected void check(String value, String name, String regex, boolean isRequire) throws ValidateException {
		if (StringTool.isBlank(value)) {
			if (isRequire) {
				throw new ValidateException(name + " 是必填项！");
			}
		} else {
			if (value.matches(regex) == false) {
				throw new ValidateException(name + " 数据不合法，请参照说明！\t" + value);
			}
		}
	}

	/**
	 * 保存数据
	 */
	protected abstract void save();

	/**
	 * 验证异常
	 */
	protected final class ValidateException extends Throwable {

		public ValidateException(String message) {
			super(message);
		}
	}
}
