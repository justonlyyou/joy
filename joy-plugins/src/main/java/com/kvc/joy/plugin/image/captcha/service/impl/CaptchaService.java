package com.kvc.joy.plugin.image.captcha.service.impl;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.google.code.kaptcha.Producer;
import com.kvc.joy.commons.bean.Pair;
import com.kvc.joy.commons.lang.DateTool;
import com.kvc.joy.core.init.support.JoyPropeties;
import com.kvc.joy.plugin.image.captcha.service.ICaptchaService;

/**
 * 验证码服务
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年9月28日 下午10:11:58
 */
public class CaptchaService implements ICaptchaService {
	
	private Producer captchaProducer;

	@Override
	public Pair<String, RenderedImage> generate() {
		Pair<String, RenderedImage> textAndImage = new Pair<String, RenderedImage>();
		String capText = captchaProducer.createText();
		textAndImage.setLeft(capText);
		BufferedImage bi = captchaProducer.createImage(capText);
		textAndImage.setRight(bi);
		return textAndImage;
	}

	@Override
	public boolean validateCode(String captcha, String genCaptcha) {
		if (StringUtils.isNotBlank(captcha)) {
			return captcha.toUpperCase().equals(genCaptcha.toUpperCase());
		}
		return false;
	}
	
	@Override
	public boolean validateTime(String genDate) {
		if (StringUtils.isNotBlank(genDate)) {
			Date date = DateTool.parseDate(genDate, DateTool.UNFMT_yyyyMMddHHmmss);
			long timesToNow = Math.abs(DateTool.timesToNow(date));
			return timesToNow < JoyPropeties.PLUGIN_CAPTCHA_TIMEOUT * 1000;
		}
		return false;
	}

	public void setCaptchaProducer(Producer captchaProducer) {
		this.captchaProducer = captchaProducer;
	}

}
