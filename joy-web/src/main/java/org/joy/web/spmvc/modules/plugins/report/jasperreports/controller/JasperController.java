package org.joy.web.spmvc.modules.plugins.report.jasperreports.controller;

import org.joy.commons.lang.string.StringTool;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/**
 * JasperReports Controller，用户处理JasperReports的请求，
 * 请求会根据用户访问的后缀来判断使用哪种生成方式，可接受的
 * 请问方式有xhtml(因为html已经被默认的view处理了。)、pdf、xls。<br>
 * @author Kevice
 * @version 2013-2-27 下午11:35:16
 */
//@RequestMapping("/jasper")
//@Controller
public class JasperController {
     
    @Resource
//    private BasicDataSource scsDataSource;
 
    /**
     * 主要的处理类
     * 请求例子: <br>
     * http://example.com/jasper/reports/someReport.xhtml<br>
     * http://example.com/jasper/reports/someReport.pdf<br>
     * http://example.com/jasper/reports/someReport.xls<br>
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(method = RequestMethod.GET, value="reports/{reportName}")
    public ModelAndView handleSimpleReportMulti(@PathVariable("reportName") String reportName, 
            HttpServletRequest request) throws Exception {
        Map param = _getParam(request);
        String uri = request.getRequestURI();
        String format = uri.substring(uri.lastIndexOf(".") + 1);
        param.put("format", format);
//        param.put("datasource", scsDataSource); //TODO
        return new ModelAndView(reportName, param);
    }
     
    /**
     * 这个方法主要是把一些请求参数的类型转换。<br>
     * 例如:<br>
     * 10000会自动转换为java.lang.Long<br>
     * 2012-11-20会自动转换为java.util.Date<br>
     * @param request 当前的http请求对象
     * @return 处理后的HashMap
     */
    @SuppressWarnings("rawtypes")
    private Map<String, Object> _getParam(HttpServletRequest request) {
        Map<String, Object> param = new HashMap<String, Object>();
        for(Object obj: request.getParameterMap().entrySet()) {
            Map.Entry en = (Map.Entry) obj;
            String key = (String)en.getKey();
            Object valueObj = en.getValue();
             
            if(valueObj instanceof String[]) {
                valueObj = Array.get(valueObj, 0);
            }
             
            if(valueObj instanceof String) {
                String valueStr = (String)valueObj;
                if(StringTool.isNumeric(valueStr)) {
                    valueObj = Long.valueOf(valueStr);
                } else {
                	//TODO
                    // 自己写的时间处理包
//                    Object d = DDateUtils.safeParse(valueStr);
//                    if(d != null) {
//                        valueObj = d;
//                    }
                }
            }
             
            param.put(key, valueObj);
        }
        return param;
    }
}
