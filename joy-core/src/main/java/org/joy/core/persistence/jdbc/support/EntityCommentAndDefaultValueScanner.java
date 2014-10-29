package org.joy.core.persistence.jdbc.support;

import org.joy.commons.lang.PackageTool;
import org.joy.commons.lang.reflect.MethodTool;
import org.joy.commons.lang.string.StringTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import org.joy.core.persistence.jdbc.model.vo.MdRdbColumnComment;
import org.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import org.joy.core.persistence.orm.jpa.annotations.Comment;
import org.joy.core.persistence.orm.jpa.annotations.DefaultValue;

import javax.persistence.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 关系数据库注释扫描器 <br>
 *
 * 负责从JPA的实体中的Comment注解中获取数据库表格和字段的注释，还有字段的默认值, 以便用于更新到数据库表中
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-10 下午8:24:53
 */
public class EntityCommentAndDefaultValueScanner {

	protected static final Log logger = LogFactory.getLog(EntityCommentAndDefaultValueScanner.class);

	private EntityCommentAndDefaultValueScanner() {
	}

	/**
	 * 根据JPA实体类包模式串，开始描述JPA实体类，获取这些类上的Comment和DefaultValue注解信息
	 *
     * @param pkgs JPA实体类包模式串可变数组
	 * @return 带有注释和默认值信息的MdRdbTable对象列表
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-12 下午10:56:45
	 */
	public static List<MdRdbTable> scan(String... pkgs) {
		List<MdRdbTable> tableList = new ArrayList<MdRdbTable>();
		if (pkgs != null) {
			for (String item : pkgs) {
				Set<String> packages = PackageTool.getPackages(item, true);
				for (String pkg : packages) {
					Set<Class<?>> classes = PackageTool.getClassesInPackage(pkg, false);
					for (Class<?> clazz : classes) {
						if (clazz.getName().endsWith("_") == false) {
							Entity entity = clazz.getAnnotation(Entity.class);
							if (entity != null) {
								MdRdbTable table = scanTableComment(clazz);
								List<MdRdbColumn> columns = scanColumnCommentAndDefaultValue(clazz);
								table.setColumns(columns);
								tableList.add(table);
							}
						}
					}
				}
			}
		}
		return tableList;
	}

    /**
     * 描述表注释
     *
     * @param clazz JPA实体类
     * @return 带有表注释的MdRdbTable对象
     */
	private static MdRdbTable scanTableComment(Class<?> clazz) {
		Table tableAnno = clazz.getAnnotation(Table.class);
		MdRdbTable table = new MdRdbTable();
		table.setName(tableAnno.name());
		Comment tableCommentAnno = clazz.getAnnotation(Comment.class);
		if (tableCommentAnno != null) {
			String comment = tableCommentAnno.value();
			table.setComment(comment);
		} else {
			logger.warn("实体类" + clazz + "未配置Comment注解，将无法自动生成表注释！");
		}
		return table;
	}

    /**
     * 描述列注释和默认值
     *
     * @param clazz JPA实体类
     * @return 带有列注释和默认值的MdRdbColumn对象列表
     */
	public static List<MdRdbColumn> scanColumnCommentAndDefaultValue(Class<?> clazz) {
		List<MdRdbColumn> columns = new ArrayList<MdRdbColumn>();
		List<Method> methods = MethodTool.getReadMethods(clazz);
		for (Method method : methods) {
			Transient trans = method.getAnnotation(Transient.class);
			if (trans == null) {
				Method mthd = getAnnoMethod(clazz, method.getName());
				if (mthd != null) {
					Comment columnCommentAnno = mthd.getAnnotation(Comment.class);
					DefaultValue defaultValueAnno = mthd.getAnnotation(DefaultValue.class);
					if (columnCommentAnno != null || defaultValueAnno != null) {
						MdRdbColumn column = new MdRdbColumn();
						columns.add(column);
						Column columnAnno = mthd.getAnnotation(Column.class);
						String columnName = null;
						if (columnAnno == null) {
							JoinColumn joinColumnAnno = mthd.getAnnotation(JoinColumn.class);
							if (joinColumnAnno != null) {
								columnName = joinColumnAnno.name();
							}
						} else {
							columnName = StringTool.lowerCase(columnAnno.name());
						}
						if (StringTool.isEmpty(columnName)) {
							columnName = StringTool.humpToUnderscore(mthd.getName().replaceFirst("^is|^get", ""));
						}
						column.setName(columnName);

						if (columnCommentAnno != null) {
							MdRdbColumnComment comment = new MdRdbColumnComment();
							column.setComment(comment);
							comment.setBriefDesc(columnCommentAnno.value());
							comment.setDetailDesc(columnCommentAnno.detailDesc());
							comment.setCodeId(columnCommentAnno.codeId());
						}

						if (defaultValueAnno != null) {
							String defaultValue = defaultValueAnno.value();
							column.setDefaultValue(defaultValue);
						}
					}
				}
			}
		}
		return columns;
	}
	
	private static Method getAnnoMethod(Class<?> clazz, String getter) {
		if (clazz != Object.class && clazz.isInterface() == false) {
			Method method = MethodTool.getAccessibleMethod(clazz, getter);
			if (method == null) {
				return getAnnoMethod(clazz.getSuperclass(), getter);
			} else {
				if (method.isAnnotationPresent(Comment.class) || method.isAnnotationPresent(DefaultValue.class)) {
					return method;
				} else {
					if (method.isBridge()) {
						return getAnnoMethod(clazz.getSuperclass(), getter);
					}
				}
			}
		}
		return null;
	}

}
