package com.kvc.joy.plugin.image.captcha.service;

import java.awt.image.RenderedImage;

import com.kvc.joy.commons.bean.Pair;

/**
 * 验证码服务接口
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年9月28日 下午10:05:16
 */
public interface ICaptchaService {
	
	/**
	 * 生成验证码文本及图片
	 * 
	 * @return Pair<验证码文本, 可展现的验证码图片>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年9月28日 下午10:09:37
	 */
	Pair<String, RenderedImage> generate();
	
	/**
	 * 校验验证码
	 * 
	 * @param captcha 要校验的验证码
	 * @param genCaptcha 生成的验证码
	 * @return true: 匹配，false: 不匹配
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年9月28日 下午10:09:57
	 */
	boolean validateCode(String captcha, String genCaptcha);
	
	/**
	 * 校验验证码时效
	 * 
	 * @param genDate 生成的日期
	 * @return true: 有效，false: 过期
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年9月28日 下午10:09:57
	 */
	boolean validateTime(String genDate);

}
