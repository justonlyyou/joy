package com.kvc.joy.core.rp.pagestore;


public class PageStoreFactory {

//	private static ThreadLocal<PageStore> pageStoreThreadLocal = new ThreadLocal<PageStore>();
//	protected static final Log logger = LogFactory.getLog(PageStoreFactory.class);
//
//	public static PageStore create(Map<String, String> paramMap) {
//		PageStore pageStore = pageStoreThreadLocal.get();
//		if (pageStore == null) {
//			pageStore = new PageStoreCreator(paramMap).create();
//			pageStoreThreadLocal.set(pageStore);
//		} else {
//			logger.warn("当前线程已经绑定一个PageStore！");
//		}
//		return pageStore;
//	}
//
//	public static PageStore curPageStore() {
//		return pageStoreThreadLocal.get();
//	}
//
//	public static Paging curPaging() {
//		return curPageStore() == null ? null : curPageStore().getPaging();
//	}
//
//	public static QueryLogics curQueryLogics() {
//		return curPageStore() == null ? null : curPageStore().getQueryLogics();
//	}
//
//	public static void clearCurrentPageStore() {
//		pageStoreThreadLocal.set(null);
//	}
	
}