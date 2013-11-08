package com.kvc.joy.core.rp.pagestore;

import javax.persistence.Transient;

import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.core.persistence.support.Paging;
import com.kvc.joy.core.persistence.support.QueryLogics;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-15 下午10:27:36
 */
public class PageStore {
	
	private Paging paging;
	private QueryLogics queryLogics;
	private Object result;
	private boolean success = true;
	private String okMsg;
	private String errMsg;
	
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
