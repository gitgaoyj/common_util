package com.wode.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 
 * 异常捕获
 * 
 * @author mengkaixuan
 * 
 */
public class WodeExceptionResolver extends SimpleMappingExceptionResolver {// implements HandlerExceptionResolver {
	private static Logger logger = LoggerFactory.getLogger(WodeExceptionResolver.class);	
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;
	
	@Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {
        try {
            String path = request.getContextPath();
            String basePath = request.getScheme()+"://"+request.getServerName()+path;
            basePath += request.getServletPath();
            String other = "";
            other = "Referer="+request.getHeader("Referer");
            logger.error(basePath+" "+other);
        }catch (Exception e){}

        logger.error(ex.getMessage(),ex);


		String content = request.getContentType();
		


		
		ModelAndView mv = new ModelAndView("redirect:/commons/404.jsp", "", ex.getMessage());
		return mv;
	}
	
	
}
