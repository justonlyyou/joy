package org.joy.core.sysres;

import org.joy.commons.enums.EnumTool;
import org.joy.commons.enums.ICodeEnum;
import org.joy.commons.enums.YesNot;
import org.joy.commons.exception.SystemException;
import org.joy.commons.lang.BooleanTool;
import org.joy.commons.lang.string.StringTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.core.spring.utils.CoreBeanFactory;
import org.joy.core.sysres.code.model.vo.CodeRecord;
import org.joy.core.sysres.datasrc.model.po.TSysDataSrc;
import org.joy.core.sysres.menu.model.po.TSysMenu;

import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * @author Kevice
 * @time 2013-2-7 下午10:29:32
 */
public class SysResTool {
	
	protected static final Log logger = LogFactory.getLog(SysResTool.class);

	private SysResTool() {
	}

	/**
	 * 根据系统参数名，获取系统参数值
	 * 
	 * @param paramName 系统参数名
	 * @return 系统参数值
	 * @author Kevice
	 * @time 2013-2-8 上午11:21:37
	 */
	public static String getParamValue(String paramName) {
		return CoreBeanFactory.getSysParamCacheService().get(paramName).getParamValue();
	}
	
	/**
	 * 刷新所有系统参数
	 * 
	 * @author Kevice
	 * @time 2013-2-8 上午11:28:06
	 */
	public static void refreshParams() {
		CoreBeanFactory.getSysParamCacheService().refresh();
	}
	
	/**
	 * 刷新某一系统参数
	 * 
	 * @param paramName 参数名
	 * @author Kevice
	 * @time 2013-2-8 上午11:28:45
	 */
	public static void refreshParam(String paramName) {
		CoreBeanFactory.getSysParamCacheService().refresh(paramName);
	}
	
	/**
	 * 获取某一代码表的所有代码记录
	 * 
	 * @param codeTableId 代码表id
	 * @return Map<代码值，译文>
	 * @author Kevice
	 * @time 2013-2-8 下午8:04:52
	 */
	public static Map<String, CodeRecord> getAllCodeAndTrans(String codeTableId) {
		codeTableId = adaptCodeTableId(codeTableId);
		return CoreBeanFactory.getSysCodeCacheService().get(codeTableId);
	}
	
	/**
	 * 
	 * 
	 * @param codeTableId
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年12月24日 下午9:57:10
	 */
	public static String getCodeCategory(String codeTableId) {
		Map<String, CodeRecord> allCodeAndTrans = getAllCodeAndTrans(codeTableId);
		if (allCodeAndTrans.isEmpty()) {
			return "";
		}
		Collection<CodeRecord> values = allCodeAndTrans.values();
		return values.iterator().next().getCodeCategory();
	}
	
	/**
	 * 
	 * 
	 * @param codeTableId
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年12月9日 上午12:26:45
	 */
	public static Map<String, String> getAllCodeAndTransStr(String codeTableId) {
		Map<String, CodeRecord> codeMap = getAllCodeAndTrans(codeTableId);
		Map<String, String> map = new LinkedHashMap<String, String>(codeMap.size());
		for (String code : codeMap.keySet()) {
			map.put(code, codeMap.get(code).getTrans());
		}
		return map;
	}
	
	/**
	 * 翻译代码
	 * 
	 * @param codeTableId 代码表id
	 * @param code 代码
	 * @return 译文
	 * @author Kevice
	 * @time 2013-2-8 下午8:07:43
	 */
	public static CodeRecord translateCode(String codeTableId, String code) {
		codeTableId = adaptCodeTableId(codeTableId);
		return CoreBeanFactory.getSysCodeCacheService().get(codeTableId, code);
	}
	
	/**
	 * 翻译代码
	 * 
	 * @param code 代码
	 * @param codeTableId 代码表id
	 * @param enumClass 枚举类
	 * @return 译文
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月13日 下午4:37:48
	 */
	@SuppressWarnings("rawtypes")
	public static CodeRecord translateCode(String code, String codeTableId, String enumClass) {
		CodeRecord result = null;
		if (StringTool.isNotBlank(code)) {
			if (StringTool.isNotBlank(codeTableId)) {
				codeTableId = adaptCodeTableId(codeTableId);
				result = CoreBeanFactory.getSysCodeCacheService().get(codeTableId, code);
			} else {
				if (StringTool.isBlank(enumClass)) {
					logger.error("要翻译的代码对应的表id或枚举类未配置，不作翻译！");
				} else {
					enumClass = adaptEnumClass(enumClass);
					if (enumClass.matches("^([a-zA-Z][\\w]*[.][a-zA-Z][\\w]+)[.]*.*")) {
						Class<? extends ICodeEnum> codeEnumClass = EnumTool.getCodeEnumClass(enumClass);
						if (codeEnumClass == YesNot.class) {
							boolean bool = BooleanTool.toBoolean(code);
							String trans = YesNot.enumOfBool(bool).getTrans();
							String order = bool ? "0" : "1";
							result = new CodeRecord(code, trans, null, order, "");
						} else {
							ICodeEnum e = (ICodeEnum) EnumTool.enumOf(enumClass, code);
							if(e != null) {
								String trans = e.getTrans();
								String order = ((Enum) e).ordinal() + "";
								result = new CodeRecord(code, trans, null, order, "");
							}
						}
					} else {
						logger.error("要翻译的代码对应的枚举类【" + enumClass + "】配置错误，不作翻译！");
					}
				}
			}
			if(result == null) {
				result = new CodeRecord(code, code, null, null, "");
			}
		}
		
		return result;
	}
	
	/**
	 * 获取某一代码表或枚举类的所有代码及译文
	 * 
	 * @param codeTableId 代码表id
	 * @param enumClass 枚举类
	 * @return Map<代码，译文>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月13日 下午5:59:18
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, CodeRecord> getAllCodeAndTrans(String codeTableId, String enumClass) {
		Map<String, CodeRecord> codeMap;
		if (StringTool.isNotBlank(codeTableId)) {
			codeMap = getAllCodeAndTrans(codeTableId);
		} else {
			if (StringTool.isBlank(enumClass)) {
				throw new SystemException("要获取的代码对应的表id或枚举类未配置！");
			} else {
				enumClass = adaptEnumClass(enumClass);
				if (enumClass.matches("^([a-zA-Z][\\w]*[.][a-zA-Z][\\w]+)[.]*.*")) {
					Class eClass = EnumTool.getCodeEnumClass(enumClass);
					Map<String, String> map = EnumTool.getCodeMap(eClass);
					int index = 0;
					codeMap = new LinkedHashMap<String, CodeRecord>(map.size());
					for (Entry<String, String> entry : map.entrySet()) {
						String code = entry.getKey();
						String trans = entry.getValue();
						CodeRecord codeRecord = new CodeRecord(code, trans, null, index + "", "");
						codeMap.put(code, codeRecord);
						index++;
					}
				} else {
					throw new SystemException("要获取的代码对应的枚举类【" + enumClass + "】配置错误！");
				}
			}
		}
		return codeMap==null ? new HashMap<String, CodeRecord>(0) : codeMap;
	}
	
	/**
	 * 获取指定菜单的完整路径
	 * 
	 * @param menuId 菜单
	 * @return List<菜单对象>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月14日 下午8:51:22
	 */
	public static List<TSysMenu> getMenuPath(String menuId) {
		return CoreBeanFactory.getSysMenuService().getMenuPath(menuId);
	}
	
	/**
	 * 获取所有数据源
	 * 
	 * @return List<数据源对象>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月28日 下午4:59:01
	 */
	public static List<TSysDataSrc> getAllDataSrc() {
		return CoreBeanFactory.getSysDataSrcService().getAllDataSrc();
	}
	
	private static String adaptCodeTableId(String codeTableId) {
		if("bool".equalsIgnoreCase(codeTableId)) {
			codeTableId = "yes_not";
		}
		return codeTableId;
	}
	
	private static String adaptEnumClass(String enumClass) {
		if("bool".equalsIgnoreCase(enumClass)) {
			enumClass = YesNot.class.getName();
		} else if(enumClass.matches("^([a-zA-Z][\\w]*[.][a-zA-Z][\\w]+)[.]*.*") == false) {

        }
		return enumClass;
	}

}
