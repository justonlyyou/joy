package org.joy.plugin.image.captcha.service.impl;

import com.google.code.kaptcha.Producer;
import org.joy.commons.bean.Pair;
import org.joy.commons.lang.DateTool;
import org.joy.core.init.support.properties.JoyProperties;
import org.joy.plugin.image.captcha.service.ICaptchaService;
import org.apache.commons.lang3.StringUtils;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.Date;

/**
 * 验证码服务
 * 
 * @since 1.0.0
 * @author Kevice
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
        return StringUtils.isNotBlank(captcha) && captcha.toUpperCase().equals(genCaptcha.toUpperCase());
    }
	
	@Override
	public boolean validateTime(String genDate) {
		if (StringUtils.isNotBlank(genDate)) {
			Date date = DateTool.parseDate(genDate, DateTool.UNFMT_yyyyMMddHHmmss);
			long timesToNow = Math.abs(DateTool.timesToNow(date));
			return timesToNow < JoyProperties.PLUGIN_CAPTCHA_TIMEOUT * 1000;
		}
		return false;
	}

	public void setCaptchaProducer(Producer captchaProducer) {
		this.captchaProducer = captchaProducer;
	}

}
