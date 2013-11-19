package com.kvc.joy.web.init;

//import javax.servlet.ServletRegistration;
//import javax.servlet.ServletRegistration.Dynamic;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-5 上午12:26:15
 */
public class WebConfig {

//	private ServletContext servletContext;
//	protected static final Log logger = LogFactory.getLog(WebConfig.class);
//
//	public WebConfig(ServletContext servletContext) {
//		this.servletContext = servletContext;
//	}
//
//	public void config() {
//		configSpring();
////		decorateJwebap();
//		configSpringMvc();
//		configShiro();
//		configResRef();
//	}
//
//	private void configSpring() {
//		ContextConfigLocationHandler locationHandler = new ContextConfigLocationHandler();
//		String contextConfigLocation = locationHandler.getContextConfigLocation();
//		
////		servletContext.setInitParameter(ContextLoader.CONFIG_LOCATION_PARAM, contextConfigLocation);
//		
//		
//		XmlWebApplicationContext appContext = new XmlWebApplicationContext();  
//        appContext.setConfigLocation(contextConfigLocation);
//        logger.info("开始加载spring配置文件： \n" + contextConfigLocation.replaceAll(",", "\n"));
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));  
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//        logger.info("结束加载spring配置文件。");
//		
//		servletContext.addListener(org.springframework.web.context.request.RequestContextListener.class);
//		servletContext.addListener(org.springframework.web.util.IntrospectorCleanupListener.class);
//	}
//
////	private void decorateJwebap() {
////		if (JoyPropeties.jwebapJdbcEnabled) {
////			servletContext.setInitParameter("jwebap-config", "WEB-INF/classes/conf/jwebap.xml");
////			new JwebapListener().contextInitialized(contextEvent);
////		}
////	}
//
//	private void configSpringMvc() {
//		if (JoyPropeties.PLUGIN_SPRING_MVC_ENABLED) {
//			//TODO ???? 启动正常，但访问不了
//			final String SERVLET_NAME = "springMVC";
//			Dynamic dynamic = servletContext.addServlet(SERVLET_NAME, org.springframework.web.servlet.DispatcherServlet.class);
//			dynamic.setInitParameter(ContextLoader.CONFIG_LOCATION_PARAM, ContextConfigLocationHandler.SPRING_MVC_CFG_LOCATION);
//			dynamic.setLoadOnStartup(1);
//			dynamic.addMapping("/");
//			
//			servletContext.addListener(org.springframework.web.util.IntrospectorCleanupListener.class);
//			
//		}
//	}
//	
//	private void configShiro() {
//		if (JoyPropeties.PLUGIN_ERBAC_ENABLED) {
//			javax.servlet.FilterRegistration.Dynamic shiroSecurityFilter = servletContext.addFilter("shiroSecurityFilter", 
//						org.springframework.web.filter.DelegatingFilterProxy.class);
//			shiroSecurityFilter.setInitParameter("targetFilterLifecycle", "true");
//			shiroSecurityFilter.addMappingForUrlPatterns(null, false, "/*");
//		}
//	}
//	
//	private void configResRef() {
////		servletContext.
//		//TODO
//	}
//	
//	@Bean
//	@Resource(name="JDBC/JOY")
//	public DataSource dataSourceLookup()
//	{
//	    final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//	    dsLookup.setResourceRef(true);
//	    DataSource dataSource = dsLookup.getDataSource("java:comp/env/JDBC/JOY");
//	    return dataSource;
//	}

}
