<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<%@ attribute name="id" type="java.lang.String" required="false" description="验证码组件ID"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="验证码输入框名称"%>
<%@ attribute name="captchaClass" type="java.lang.String" required="false" description="样式类"%>

<div class="form-inline hide joy-captcha ${captchaClass}">
	<input type="text" id="${name}" name="${name}" class="form-control required" placeholder="验证码"/>
	<img src="${ctx}/captcha/genImage" onclick="$('.${name}Refresh').click();" class="img-responsive;img-rounded;mid ${name}"/>
	<a href="#" onclick="$('.${name}').attr('src','${ctx}/captcha/genImage?'+new Date().getTime());" class="mid ${name}Refresh">看不清</a>
</div>