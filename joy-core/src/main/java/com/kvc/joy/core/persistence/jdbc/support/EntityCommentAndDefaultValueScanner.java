package com.kvc.joy.core.persistence.jdbc.support;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.commons.lang.PackageTool;
import com.kvc.joy.commons.lang.reflect.MethodTool;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumnComment;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;
import com.kvc.joy.core.persistence.orm.jpa.annotations.DefaultValue;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacOrganization;

/**
 * <p>
 * 关系数据库注释扫描器
 * </p>
 * 
 * <pre>
 * 负责从JPA的实体中的Comment注解中获取数据库表格和字段的注释，还有字段的默认值,
 * 以便用于更新到数据库表中
 * </pre>
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-10 下午8:24:53
 */
public class EntityCommentAndDefaultValueScanner {

	private static Logger logger = LoggerFactory.getLogger(EntityCommentAndDefaultValueScanner.class);

	private EntityCommentAndDefaultValueScanner() {
	}

	/**
	 * 
	 * 
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
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

	private static MdRdbTable scanTableComment(Class<?> clazz) {
		Table tableAnno = clazz.getAnnotation(Table.class);
		MdRdbTable table = new MdRdbTable();
		table.setName(tableAnno.name().toLowerCase());
		Comment tableCommentAnno = clazz.getAnnotation(Comment.class);
		if (tableCommentAnno != null) {
			String comment = tableCommentAnno.value();
			table.setComment(comment);
		} else {
			logger.warn("实体类" + clazz + "未配置Comment注解，将无法自动生成表注释！");
		}
		return table;
	}

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
								columnName = joinColumnAnno.name().toLowerCase();
							}
						} else {
							columnName = StringTool.lowerCase(columnAnno.name());
						}
						if (StringTool.isEmpty(columnName)) {
							columnName = StringTool.humpToUnderscore(mthd.getName().replaceFirst("^is|^get", "")).toLowerCase();
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

	public static void main(String[] args) {
//		EntityCommentAndDefaultValueScanner.scan("com.kvc.joy.core.ehcache.model.po");
		List<MdRdbColumn> columnList = EntityCommentAndDefaultValueScanner.scanColumnCommentAndDefaultValue(TErbacOrganization.class);
		System.out.println("columnList: "+columnList.size());
	}
	
	

}
