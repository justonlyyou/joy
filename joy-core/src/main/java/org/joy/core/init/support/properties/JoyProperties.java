package org.joy.core.init.support.properties;


import java.util.Properties;

/**
 * 平台属性
 * 
 * @author Kevice
 * @time 2013-2-4 下午10:56:43
 */
public class JoyProperties extends BaseProperties {

	private static volatile JoyProperties self = new JoyProperties();
    private static Properties dbProperties; // 数据库中存储的properties，不是指db.properties文件中的

	public static String DB_JNDI;
    public static boolean DB_AUTOCOMMIT;
    public static String DB_DATASOURCEID;

	public static String JPA_PERSISTENCEXML;
	public static String JPA_VENDORADAPTERCLASS;
	public static boolean JPA_SHOWSQL;
	public static boolean JPA_GENERATEDDL;
	public static boolean PLUGIN_PERSISTENCE_HIBERNATE_ENABLED;
	public static boolean PLUGIN_SEQGEN_ENABLED;
	public static boolean PLUGIN_JWEBAP_JDBC_ENABLED;
	public static Integer PLUGIN_JWEBAP_JDBC_FILTERTIME;
	public static boolean PLUGIN_JWEBAP_JDBC_LOGPOSITION;
	public static boolean PLUGIN_QUARTZ_ENABLED;
	public static boolean PLUGIN_ERBAC_ENABLED;
	public static String PLUGIN_ERBAC_LOGIN_URL;
	public static String PLUGIN_ERBAC_SUCCESS_URL;
	public static String PLUGIN_ERBAC_UNAUTHORIZED_URL;
	public static String PLUGIN_ERBAC_HASH_ALGORITHM;
	public static boolean PLUGIN_USER_ENABLED;
	public static Integer PLUGIN_USER_PASSWORD_ERROR_ALLOW_COUNT;
	public static Integer PLUGIN_USER_PASSWORD_ERROR_PERIOD_HOUR;
	public static boolean PLUGIN_SPRING_MVC_ENABLED;
	public static boolean PLUGIN_WORKFLOW_ENABLED;
	public static boolean PLUGIN_MAPPING_FIELD_ENABLED;
	public static boolean PLUGIN_REPORT_JASPERREPORTS_ENABLED;
	public static boolean PLUGIN_CAPTCHA_ENABLED;
	public static int 	PLUGIN_CAPTCHA_TIMEOUT;
	public static String PLUGIN_CAPTCHA_KAPTCHA_BORDER;
	public static String PLUGIN_CAPTCHA_KAPTCHA_BORDER_COLOR;
	public static String PLUGIN_CAPTCHA_KAPTCHA_TEXTPRODUCER_FONT_COLOR;
	public static Integer PLUGIN_CAPTCHA_KAPTCHA_IMAGE_WIDTH;
	public static Integer PLUGIN_CAPTCHA_KAPTCHA_TEXTPRODUCER_FONT_SIZE;
	public static Integer PLUGIN_CAPTCHA_KAPTCHA_IMAGE_HEIGHT;
	public static String PLUGIN_CAPTCHA_KAPTCHA_SESSION_KEY;
	public static Integer PLUGIN_CAPTCHA_KAPTCHA_TEXTPRODUCER_CHAR_LENGTH;
	public static String PLUGIN_CAPTCHA_KAPTCHA_TEXTPRODUCER_FONT_NAMES;
	public static String FLYWAY_SQLMIGRATIONPREFIX;
    public static boolean PLUGIN_DRUID_ENABLED;
    public static boolean PLUGIN_COMET_ENABLED;
    public static boolean PLUGIN_MYBATIS_ENABLED;

	private JoyProperties() {
	}

	public static JoyProperties getInstance() {
		return self;
	}

	static {
		self.init();
	}

	protected void init() {
		DB_JNDI = getStr("db.jndi");
		DB_AUTOCOMMIT = getBool("db.autoCommit");
		DB_DATASOURCEID = getStr("db.dataSourceId");
	}

    protected void initDbProperties() {
        JPA_PERSISTENCEXML = getStr("jpa.persistenceXml");
        JPA_VENDORADAPTERCLASS = getStr("jpa.vendorAdapterClass");
        JPA_SHOWSQL = getBool("jpa.showSql");
        JPA_GENERATEDDL = getBool("jpa.generateDdl");
        PLUGIN_PERSISTENCE_HIBERNATE_ENABLED = getBool("plugin.persistence.hibernate.enabled");
        PLUGIN_SEQGEN_ENABLED = getBool("plugin.seqgen.enabled");
        PLUGIN_JWEBAP_JDBC_ENABLED = getBool("plugin.jwebap.jdbc.enabled");
        PLUGIN_JWEBAP_JDBC_FILTERTIME = getInt("plugin.jwebap.jdbc.filterTime");
        PLUGIN_JWEBAP_JDBC_LOGPOSITION = getBool("plugin.jwebap.jdbc.logPosition");
        PLUGIN_QUARTZ_ENABLED = getBool("plugin.quartz.enabled");
        PLUGIN_ERBAC_ENABLED = getBool("plugin.erbac.enabled");
        PLUGIN_ERBAC_LOGIN_URL = getStr("plugin.erbac.loginUrl");
        PLUGIN_ERBAC_SUCCESS_URL = getStr("plugin.erbac.successUrl");
        PLUGIN_ERBAC_UNAUTHORIZED_URL = getStr("plugin.erbac.unauthorizedUrl");
        PLUGIN_ERBAC_HASH_ALGORITHM = getStr("plugin.erbac.hashAlgorithm");
        PLUGIN_USER_ENABLED = getBool("plugin.user.enabled");
        PLUGIN_USER_PASSWORD_ERROR_ALLOW_COUNT = getInt("plugin.user.password.error.allow.count");
        PLUGIN_USER_PASSWORD_ERROR_PERIOD_HOUR = getInt("plugin.user.password.error.period.hour");
        PLUGIN_SPRING_MVC_ENABLED = getBool("plugin.spring.mvc.enabled");
        PLUGIN_WORKFLOW_ENABLED = getBool("plugin.workflow.enabled");
        PLUGIN_MAPPING_FIELD_ENABLED = getBool("plugin.mapping.field.enabled");
        PLUGIN_REPORT_JASPERREPORTS_ENABLED = getBool("plugin.report.jasperreports.enabled");
        PLUGIN_CAPTCHA_ENABLED = getBool("plugin.captcha.enabled");
        PLUGIN_CAPTCHA_TIMEOUT = getInt("plugin.captcha.timeout");
        PLUGIN_CAPTCHA_KAPTCHA_BORDER = getStr("plugin.captcha.kaptcha.border");
        PLUGIN_CAPTCHA_KAPTCHA_BORDER_COLOR = getStr("plugin.captcha.kaptcha.border.color");
        PLUGIN_CAPTCHA_KAPTCHA_TEXTPRODUCER_FONT_COLOR = getStr("plugin.captcha.kaptcha.textproducer.font.color");
        PLUGIN_CAPTCHA_KAPTCHA_IMAGE_WIDTH = getInt("plugin.captcha.kaptcha.image.width");
        PLUGIN_CAPTCHA_KAPTCHA_TEXTPRODUCER_FONT_SIZE = getInt("plugin.captcha.kaptcha.textproducer.font.size");
        PLUGIN_CAPTCHA_KAPTCHA_IMAGE_HEIGHT = getInt("plugin.captcha.kaptcha.image.height");
        PLUGIN_CAPTCHA_KAPTCHA_SESSION_KEY = getStr("plugin.captcha.kaptcha.session.key");
        PLUGIN_CAPTCHA_KAPTCHA_TEXTPRODUCER_CHAR_LENGTH = getInt("plugin.captcha.kaptcha.textproducer.char.length");
        PLUGIN_CAPTCHA_KAPTCHA_TEXTPRODUCER_FONT_NAMES = getStr("plugin.captcha.kaptcha.textproducer.font.names");
        FLYWAY_SQLMIGRATIONPREFIX = getStr("flyway.sqlMigrationPrefix");
        PLUGIN_DRUID_ENABLED = getBool("plugin.druid.enabled");
        PLUGIN_COMET_ENABLED = getBool("plugin.comet.enabled");
        PLUGIN_MYBATIS_ENABLED = getBool("plugin.mybatis.enabled");
    }

	@Override
	protected String getPropertiesFile() {
		return "conf/db.properties";
	}
	
	public static String property(String property) {
		return self.getStr(property);
	}

    protected Object get(String property) {
        boolean containsKey = dbProperties == null ? false : dbProperties.containsKey(property);
        if (containsKey == false) {
            containsKey = getProperties().containsKey(property);
            if (containsKey == false) {
                logger.error(getPropertiesFile() + "不存在key为" + property + "的Property！");
                return null;
            } else {
                return getProperties().get(property);
            }
        } else {
            return dbProperties.get(property);
        }
    }

    public static void setDbProperties(Properties dbProperties) {
        JoyProperties.dbProperties = dbProperties;
        self.initDbProperties();
    }

    public static Properties getDbProperties() {
        return JoyProperties.dbProperties;
    }
}
