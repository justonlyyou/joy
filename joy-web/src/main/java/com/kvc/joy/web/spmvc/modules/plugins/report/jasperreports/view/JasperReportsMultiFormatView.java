package com.kvc.joy.web.spmvc.modules.plugins.report.jasperreports.view;

import org.springframework.web.servlet.view.jasperreports.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Kevice
 * @time 2013-2-27 下午11:33:12
 */
public class JasperReportsMultiFormatView extends org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView {

	/**
	 * 把原来的html换成xhtml了，这样做的目的是为了防止和Spring mvc的org.springframework.web.servlet.view.ContentNegotiatingViewResolver的处理起冲突
	 */
	public JasperReportsMultiFormatView() {
		super();
        Map<String, Class<? extends AbstractJasperReportsView>> map = new HashMap<String, Class<? extends AbstractJasperReportsView>>(4);
        map.put("csv", JasperReportsCsvView.class);
        map.put("xhtml", JasperReportsHtmlView.class);
        map.put("pdf", JasperReportsPdfView.class);
        map.put("xls", JasperReportsXlsView.class);
        super.setFormatMappings(map);
	}
	
}
