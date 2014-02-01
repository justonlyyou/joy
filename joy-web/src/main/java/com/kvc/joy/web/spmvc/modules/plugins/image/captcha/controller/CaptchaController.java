package com.kvc.joy.web.spmvc.modules.plugins.image.captcha.controller;

import com.kvc.joy.commons.bean.Pair;
import com.kvc.joy.commons.lang.DateTool;
import com.kvc.joy.plugin.image.captcha.consts.CaptchaConsts;
import com.kvc.joy.plugin.support.PluginBeanFactory;
import com.kvc.joy.web.support.utils.HttpSessionTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年9月21日 下午5:39:29
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController {
	
//	@Resource
//	private ICaptchaService captchaService;

	@RequestMapping("/genImage")
	public ModelAndView genImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		
		Pair<String, RenderedImage> textAndImage = PluginBeanFactory.getCaptchaService().generate();
		String capText = textAndImage.getLeft();
		RenderedImage bi = textAndImage.getRight();
		
		// store the text in the session
		request.getSession().setAttribute(CaptchaConsts.CAPTCHA_SESSION_KEY, capText);
		String currentDate = DateTool.currentDate(DateTool.UNFMT_yyyyMMddHHmmss);
		request.getSession().setAttribute(CaptchaConsts.CAPTCHA_SESSION_DATE, currentDate);
		// create the image with the text
		ServletOutputStream out = response.getOutputStream();
		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping("/validateCode")
	@ResponseBody
	public String validate(@RequestParam("captcha") String captcha) {
		String code = (String) HttpSessionTool.getSession().getAttribute(CaptchaConsts.CAPTCHA_SESSION_KEY);
		return PluginBeanFactory.getCaptchaService().validateCode(captcha, code) + "";
	}
	
	@RequestMapping("/validateTime")
	@ResponseBody
	public String validate() {
		String date = (String) HttpSessionTool.getSession().getAttribute(CaptchaConsts.CAPTCHA_SESSION_DATE);
		return PluginBeanFactory.getCaptchaService().validateTime(date) + "";
	}
	
}
