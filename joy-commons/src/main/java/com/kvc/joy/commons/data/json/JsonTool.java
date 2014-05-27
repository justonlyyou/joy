package com.kvc.joy.commons.data.json;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * json工具类
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-2-7 下午7:28:45
 */
public class JsonTool {

	protected static final Log logger = LogFactory.getLog(JsonTool.class);

	private JsonTool() {
	}

	/**
	 * <p>
	 * 将简单的Json串格式化成页面显示的字符串(去掉花括号、引号及最后面可能的逗号)
	 * </p>
	 * 
	 * @param simpleJsonStr 简单的Json串格式化(如：{"A":"b","B":'b'} ), 为null或空将返回空串
	 * @return 页面显示的字符串(如：A:b,B:b )
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午7:43:08
	 */
	public static String jsonToDisplay(String simpleJsonStr) {
		if (StringTool.isBlank(simpleJsonStr)) {
			return "";
		}
		String displayStr = simpleJsonStr.replaceFirst("^\\{", "");
		displayStr = displayStr.replaceFirst("\\}$", "");
		displayStr = displayStr.replaceAll("\"|'", "");
		displayStr = displayStr.replaceFirst(",$", "");
		return displayStr;
	}
	
	/**
	 * <p>
	 * 反序列化, 将json串解析为指定Class的实例
	 * </p>
	 * 
	 * @param json json串, 为null或空将返回null, 为"[]", 返回空集合
	 * @param clazz Class
	 * @return Class的实例，出错时返回null
	 * @see #fromJson(String, JavaType) 如需反序列化复杂Collection如List<MyBean>
	 * @author Kevice
	 * @time 2013-2-7 下午8:46:57
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		return fromJson(json, clazz, null);
	}
			

	/**
	 * <p>
	 * 反序列化, 将json串解析为指定Class的实例
	 * </p>
	 * 
	 * @param json json串, 为null或空将返回null, 为"[]", 返回空集合
	 * @param clazz Class
	 * @param mapper json转换器，为null时该方法内部将新建一个默认的转换器
	 * @return Class的实例，出错时返回null
	 * @see #fromJson(String, JavaType) 如需反序列化复杂Collection如List<MyBean>
	 * @author Kevice
	 * @time 2013-2-7 下午8:46:57
	 */
	public static <T> T fromJson(String json, Class<T> clazz, ObjectMapper mapper) {
		if(StringTool.isBlank(json)) {
			return null;
		}
		if(mapper == null) {
			mapper = createDefaultMapper();
		}
		try {
			return mapper.readValue(json, clazz);
		} catch (Exception e) {
			logger.error(e, "json解析为对象出错！json: " + json);
		}
		return null;
	}
	
	/**
	 * <p>
	 * 反序列化复杂Collection如List<Bean>, 
	 * 请先使用方法createCollectionType构造类型, 然后调用该方法。
	 * </p>
	 * @param jsonString 要反序列化的json串
	 * @param javaType java类型，通过createCollectionType方法创建
	 * @param mapper json转换器，为null时该方法内部将新建一个默认的转换器
	 * @return 反序列化后的对象
	 * @see #createCollectionType(ObjectMapper, Class, Class...)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午8:25:56
	 */
	public static Object fromJson(String jsonString, JavaType javaType, ObjectMapper mapper) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		if(mapper == null) {
			mapper = createDefaultMapper();
		}
		
		try {
			return mapper.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.error(e, "反序列化json串出错:" + jsonString);
			return null;
		}
	}
	
	/**
	 * <p>
	 * 反序列化复杂Collection如List<Bean>, 
	 * 请先使用方法createCollectionType构造类型, 然后调用该方法。
	 * </p>
	 * @param jsonString 要反序列化的json串
	 * @param javaType java类型，通过createCollectionType方法创建
	 * @return 反序列化后的对象
	 * @see #createCollectionType(Class, Class...)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午8:25:56
	 */
	public static Object fromJson(String jsonString, JavaType javaType) {
		return fromJson(jsonString, javaType, null);
	}
	
	/**
	 * <p>
	 * 创建带有泛型信息的容器类型
	 * </p>
	 * 
	 * <p>
	 * 如:
	 * ArrayList<MyBean>, 则调用createCollectionType(mapper, ArrayList.class,MyBean.class)
	 * HashMap<String,MyBean>, 则调用(mapper, HashMap.class,String.class, MyBean.class)
	 * </p>
	 * 
	 * @param mapper json转换器，为null时该方法内部将新建一个默认的转换器
	 * @param collectionClass 容器类
	 * @param elementClasses 容器元素类型
	 * @return 带有泛型信息的容器类型
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午8:34:30
	 */
	public static JavaType createCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
		if(mapper == null) {
			mapper = createDefaultMapper();
		}
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
	
	/**
	 * <p>
	 * 创建带有泛型信息的容器类型
	 * </p>
	 * 
	 * <p>
	 * 如:
	 * ArrayList<MyBean>, 则调用createCollectionType(mapper, ArrayList.class,MyBean.class)
	 * HashMap<String,MyBean>, 则调用(mapper, HashMap.class,String.class, MyBean.class)
	 * </p>
	 * 
	 * @param mapper json转换器，为null时该方法内部将新建一个默认的转换器
	 * @param collectionClass 容器类
	 * @param elementClasses 容器元素类型
	 * @return 带有泛型信息的容器类型
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午8:34:30
	 */
	public static JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return createCollectionType(null, collectionClass, elementClasses);
	}

	/**
	 * <p>
	 * 将json文件的内容解析为指定Class的实例
	 * </p>
	 * 
	 * @param jsonFile json文件
	 * @param clazz Class
	 * 
	 * @return Class的实例，出错时返回null
	 * @author Kevice
	 * @time 2013-2-7 下午8:56:00
	 */
	public static <T> T fromJson(File jsonFile, Class<T> clazz) {
		return fromJson(jsonFile, clazz, null);
	}
	
	/**
	 * <p>
	 * 将json文件的内容解析为指定Class的实例
	 * </p>
	 * 
	 * @param jsonFile json文件
	 * @param clazz Class
	 * @param mapper json转换器，为null时该方法内部将新建一个默认的转换器
	 * @return Class的实例，出错时返回null
	 * @author Kevice
	 * @time 2013-2-7 下午8:56:00
	 */
	public static <T> T fromJson(File jsonFile, Class<T> clazz, ObjectMapper mapper) {
		if(mapper == null) {
			mapper = createDefaultMapper();
		}
		try {
			return mapper.readValue(jsonFile, clazz);
		} catch (Exception e) {
			logger.error(e, "json解析为对象出错！");
		}
		return null;
	}
	
	/**
	 * <p>
	 * 序列化，将对象转为json串
	 * </p>
	 * 
	 * @param object 要序列化的对象，可以是POJO，也可以是Collection或数组，
	 * 					如果对象为Null, 返回"null"；如果集合为空集合, 返回"[]"
	 * @param mapper json转换器，为null时该方法内部将新建一个默认的转换器
	 * @return 序列化后的json串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午8:09:22
	 */
	public static String toJson(Object object, ObjectMapper mapper) {
		if(mapper == null) {
			mapper = createDefaultMapper();
		}
		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.error(e, "序列化对象为json时出错:" + object);
			return null;
		}
	}
	
	/**
	 * <p>
	 * 序列化，将对象转为json串
	 * </p>
	 * 
	 * @param object 要序列化的对象，可以是POJO，也可以是Collection或数组，
	 * 					如果对象为Null, 返回"null"；如果集合为空集合, 返回"[]"
	 * @return 序列化后的json串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午8:09:22
	 */
	public static String toJson(Object object) {
		return toJson(object, null);
	}
	
	/**
	 * <p>
	 * 创建指定Include枚举元素的json转换器
	 * </p>
	 * 
	 * @param include Include枚举元素
	 * @return json转换器
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午7:58:32
	 */
	public static ObjectMapper createMapper(Include include) {
		ObjectMapper mapper = createDefaultMapper();
		// 设置输出时包含属性的风格
		if (include != null) {
			mapper.setSerializationInclusion(include);
		}
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		return mapper;
	}
	
	/**
	 * <p>
	 * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的json转换器
	 * </p>
	 * 
	 * @return json转换器
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午8:02:38
	 */
	public static ObjectMapper createNonEmptyMapper() {
		return createMapper(Include.NON_EMPTY);
	}

	/**
	 * <p>
	 * 创建只输出初始值被改变的属性到Json字符串的json转换器
	 * </p>
	 * 
	 * @return json转换器
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午8:02:38
	 */
	public static ObjectMapper createNonDefaultMapper() {
		return createMapper(Include.NON_DEFAULT);
	}
	
	/**
	 * <p>
	 * 当json里含有bean的部分属性时，更新该bean的该部分属性
	 * </p>
	 * 
	 * @param jsonString json串
	 * @param object 待更新的bean
	 * @param mapper json转换器，为null时该方法内部将新建一个默认的转换器
	 * @return 更新后的bean，失败时返回null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午8:42:57
	 */
	@SuppressWarnings("unchecked")
	public static <T> T updateBean(String jsonString, T object, ObjectMapper mapper) {
		if(mapper == null) {
			mapper = createDefaultMapper();
		}
		try {
			return (T) mapper.readerForUpdating(object).readValue(jsonString);
		} catch (Exception e) {
			logger.error(e, "将json串:{0}更新到对象:{1}时出错.", jsonString, object);
		}
		return null;
	}
	
	/**
	 * <p>
	 * 当json里含有bean的部分属性时，更新该bean的该部分属性
	 * </p>
	 * 
	 * @param jsonString json串
	 * @param object 待更新的bean
	 * @return 更新后的bean，失败时返回null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午8:42:57
	 */
	public static <T> T updateBean(String jsonString, T object) {
		return updateBean(jsonString, object, null);
	}
	
	/**
	 * <p>
	 * 输出jsonP格式的数据
	 * </p>
	 * 
	 * @param functionName 函数名
	 * @param object 待序列化的对象
	 * @return jsonP字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午8:55:10
	 */
	public static String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(functionName, object));
	}
	
	/**
	 * <p>
	 * 设定使用Enum的toString方法来读写Enum,
	 * 注意本方法一定要在Mapper创建后, 所有的读写动作之前調用.
	 * </p>
	 * 
	 * @param mapper json转换器，为null将什么也不做
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午8:58:32
	 */
	public static void enableEnumUseToString(ObjectMapper mapper) {
		if(mapper != null) {
			mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
			mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);	
		}
	}

	/**
	 * <p>
	 * 支持使用Jaxb的Annotation，使得POJO上的annotation不用与Jackson耦合。
	 * 默认会先查找jaxb的annotation，如果找不到再找jackson的。
	 * </p>
	 * 
	 * @param mapper json转换器，为null将什么也不做
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午9:07:33
	 */
	public static void enableJaxbAnnotation(ObjectMapper mapper) {
		if(mapper != null) {
			JaxbAnnotationModule module = new JaxbAnnotationModule();
			mapper.registerModule(module);
		}
	}

	/**
	 * <p>
	 * 允许单引号
	 * 允许不带引号的字段名称
	 * </p>
	 * 
	 * @param mapper json转换器，为null将什么也不做
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-15 下午9:09:25
	 */
	public static void enableSimple(ObjectMapper mapper) {
		if(mapper != null) {
			mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
			mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		}
	}
	
	private static ObjectMapper createDefaultMapper() {
		return new ObjectMapper();
	}
	
}
