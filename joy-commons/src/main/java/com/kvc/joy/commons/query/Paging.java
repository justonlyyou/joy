package com.kvc.joy.commons.query;

import javax.persistence.Transient;

import com.kvc.joy.commons.query.sort.Sort;


/**
 * 分页接口
 * 
 * @author 唐玮琳
 * @time 2012-6-5 下午10:34:04
 */
public class Paging implements java.io.Serializable { // implements Pageable {

	public static final String KEY_PAGE_SIZE = "_joy_key__paging_pageSize";
	public static final String KEY_PAGE_NUMBER = "_joy_key__paging_pageNumber";
	public static final String KEY_OFFSET = "_joy_key__paging_offset";

	private int offset;
	private int pageNumber = 1;
	private int pageSize = 10;
	private int totalCount;
	private int pageCount;
	private int slider = 1; // 前后显示页面长度
	private int midLength = 5; // 显示页面长度
	private int midBeginPage;
	private int midEndPage;
	private int first = 1;
	private int last = 1;
	
	private boolean firstPage;//是否是第一页
	private boolean lastPage;//是否是最后一页
	private int nextPage;
	private int prePage;
	
	private Sort sort;

	public void cal() {
		//1
		first = 1;
		
		last = (totalCount - 1) / pageSize + 1; // (int) (totalCount / (pageSize < 1 ? 20 : pageSize) + first - 1);

//		if (totalCount % pageSize != 0 || last == 0) {
//			last++;
//		}

		if (last < first) {
			last = first;
		}

		if (pageNumber <= 1) {
			pageNumber = first;
			firstPage = true;
		}

		if (pageNumber >= last) {
			pageNumber = last;
			lastPage = true;
		}

		if (pageNumber < last - 1) {
			nextPage = pageNumber + 1;
		} else {
			nextPage = last;
		}

		if (pageNumber > 1) {
			prePage = pageNumber - 1;
		} else {
			prePage = first;
		}

		// 2
		if (pageNumber < first) {// 如果当前页小于首页
			pageNumber = first;
		}
		if (pageNumber > last) {// 如果当前页大于尾页
			pageNumber = last;
		}
		
		// 计算中间要显示的页索引范围
		midBeginPage = pageNumber - (midLength / 2);
		if (midBeginPage < first) {
			midBeginPage = first;
		}
		midEndPage = midBeginPage + midLength - 1;
		if (midEndPage >= last) {
			midEndPage = last;
			midBeginPage = midEndPage - midLength + 1;
			if (midBeginPage < first) {
				midBeginPage = first;
			}
		}
		
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getSlider() {
		return slider;
	}

	public void setSlider(int slider) {
		this.slider = slider;
	}

	public int getMidLength() {
		return midLength;
	}

	public void setMidLength(int midLength) {
		this.midLength = midLength;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getMidBeginPage() {
		return midBeginPage;
	}

	public void setMidBeginPage(int midBeginPage) {
		this.midBeginPage = midBeginPage;
	}

	public int getMidEndPage() {
		return midEndPage;
	}

	public void setMidEndPage(int midEndPage) {
		this.midEndPage = midEndPage;
	}

	@Transient
	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}
	
}
