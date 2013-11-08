package com.kvc.joy.core.persistence.jdbc.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.commons.bean.BeanTool;
import com.kvc.joy.commons.data.json.JsonTool;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumnComment;

/**
 * 关系数据库列注释解析器
 * 
 * @author 唐玮琳
 * @time 2013-2-5 下午11:44:41
 */
public class MdRdbColumnCommentParser {

	private static final String SEPARATER = ",";
	private static final String JSON_REQ_EXP = ".*\\{(\"\\w+\":\".+\")+\\}.*";
	private static Logger logger = LoggerFactory.getLogger(MdRdbColumnCommentParser.class);

	private MdRdbColumnCommentParser() {
	}

	public static MdRdbColumnComment parse(String origComment) {
		MdRdbColumnComment comment = null;
		if (StringTool.isNotBlank(origComment)) {
			origComment = origComment.trim();
			if (origComment.equals("") == false) {
				comment = new MdRdbColumnComment(origComment);
				comment.setBriefDesc(parseBriefDesc(origComment));
				comment.setDetailDesc(parseDetailDesc(origComment));
				MdRdbColumnComment tempComment = parseOthers(origComment);
				if (tempComment != null) {
					String[] excludeProperties = { "origComment", "briefDesc", "detailDesc" };
					BeanTool.copyPropertiesExclude(tempComment, comment, excludeProperties);
				}
			}
		}
		return comment;
	}

	private static String parseBriefDesc(String origComment) {
		String briefDesc = origComment;
		int index = origComment.indexOf(SEPARATER);
		if (index != -1) {
			briefDesc = briefDesc.substring(0, index).trim();
		}
		return briefDesc;
	}

	private static String parseDetailDesc(String origComment) {
		String detailDesc = null;
		boolean existJsonStr = origComment.matches(JSON_REQ_EXP);
		int index = origComment.indexOf(SEPARATER);
		if (index != -1) {
			int start = index + 1;
			if (existJsonStr) {
				int end = origComment.indexOf("{");
				detailDesc = origComment.substring(start, end).trim();
			} else {
				detailDesc = origComment.substring(start).trim();
			}
			if (StringTool.isNotBlank(detailDesc)) {
				detailDesc = detailDesc.replaceFirst(SEPARATER + "$", "").trim();
			}
		}
		return detailDesc;
	}

	private static MdRdbColumnComment parseOthers(String origComment) {
		Pattern p = Pattern.compile(JSON_REQ_EXP);
		Matcher m = p.matcher(origComment);
		MdRdbColumnComment comment = null;
		if (m.find(1)) {
			String jsonStr = "{" + m.group(1) + "}";
			comment = JsonTool.fromJson(jsonStr, MdRdbColumnComment.class);
			if (comment == null) {
				logger.error("字段注释不符合规范：" + origComment);
			}
		}
		return comment;
	}

	public static void main(String[] args) {
//		MdRdbColumnComment comment = parse("brief,detail, {\"codeId\":\"DM_SEX\"}");
		MdRdbColumnComment comment = parse("是否系统内置");
		System.out.println(comment);
	}

}
