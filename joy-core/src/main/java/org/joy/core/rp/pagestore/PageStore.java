package org.joy.core.rp.pagestore;

import org.joy.commons.query.Paging;
import org.joy.commons.query.QueryLogics;
import org.joy.core.persistence.orm.jpa.JpaTool;

import javax.persistence.Transient;

/**
 * 封装用户请求和响应结果的对象
 * 
 * @author Kevice
 * @time 2012-6-15 下午10:27:36
 * @since 1.0.0
 */
public class PageStore {
	
	private Paging paging; // 分页信息
	private QueryLogics queryLogics; // 查询逻辑
	private Object result; // 查询结果
	private boolean success = true; // 是否成功
	private String okMsg; // 成功消息
	private String errMsg; // 错误消息
	
	public Object query(Class<?> entityClass) {
		return JpaTool.pagingSearch(entityClass, this);
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getOkMsg() {
		return okMsg;
	}

	public void setOkMsg(String okMsg) {
		this.okMsg = okMsg;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Transient
	public QueryLogics getQueryLogics() {
		return queryLogics;
	}

	public void setQueryLogics(QueryLogics queryLogics) {
		this.queryLogics = queryLogics;
	}

}
