package org.joy.web.spmvc.core;

import org.joy.commons.exception.ServiceException;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Kevice
 * @time 2013年10月2日 上午12:10:05
 * @since 1.0.0
 */
public class JoyMappingExceptionResolver extends SimpleMappingExceptionResolver {

    protected static final Log logger = LogFactory.getLog(JoyMappingExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error(ex);

        // Expose ModelAndView for chosen error view.

        // JSP格式返回
        if (!(request.getHeader("accept").contains("application/json") || (request
                .getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest")))) {
            // 如果不是异步请求
            // Apply HTTP status code for error views, if specified.
            // Only apply it if we're processing a top-level request.
            String viewName = determineViewName(ex, request);
            if (viewName != null) {
                Integer statusCode = determineStatusCode(request, viewName);
                if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode);
                }
            }
            return getModelAndView(viewName, ex, request);
        } else { // JSON格式返回
                try {
                    PrintWriter writer = response.getWriter();
                    if (ex instanceof ServiceException) {
                        writer.write(ex.getMessage());
                    } else {
                        writer.write("服务端发生未预期的错误，请联系管理员！");
                    }
                    writer.flush();
                } catch (IOException e) {
                    logger.error(e);
                }
            return null;
        }
    }

}
