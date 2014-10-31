package org.joy.plugin.security.erbac.service.impl;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.joy.commons.lang.string.StringTool;
import org.joy.plugin.security.erbac.dao.impl.TErbacAuthorityDao;
import org.joy.plugin.security.erbac.model.po.TErbacAuthority;
import org.joy.plugin.security.erbac.support.utils.ShiroPermissionExpTool;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.List;

/**
 * 权限过滤器链定义
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-2-23 上午11:03:56
 */
public class FilterChainDefinition implements FactoryBean<Ini.Section> {

	private String filterChain;
	@Autowired
	private TErbacAuthorityDao authorityDao;

	/**
	 * 默认premission字符串
	 */
	public static final String PREMISSION_STRING = "perms[\"{0}\"]";

	public Section getObject() throws BeansException {
		// 获取所有Authority
		List<TErbacAuthority> urlAuthorities = authorityDao.getActiveUrlAuthorities();

		Ini ini = new Ini();
		// 加载默认的url
		ini.load(filterChain);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		
		// 循环Authority的url,逐个添加到section中。section就是filterChainDefinitionMap,
		// 里面的键就是链接URL,值就是存在什么条件才能访问该链接
		for (TErbacAuthority authority : urlAuthorities) {
			// 如果不为空值添加到section中
			if (StringTool.isNotEmpty(authority.getDomain())) {
				String permission = ShiroPermissionExpTool.createShiroPermissionExp(authority);
				section.put(authority.getResource(), MessageFormat.format(PREMISSION_STRING, permission));
			}
		}

		//section.put("/**", "user"); // 必须放在最后，不然其后的权限会失效
		
		return section;
	}

	public void setFilterChain(String filterChain) {
		this.filterChain= filterChain;
	}

	public Class<?> getObjectType() {
		return this.getClass();
	}

	public boolean isSingleton() {
		return false;
	}
	
	public void setAuthorityDao(TErbacAuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}

}
