package org.joy.core.persistence.orm.jpa;

import org.joy.commons.query.Paging;
import org.joy.core.rp.pagestore.PageStore;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.List;

/**
 * jpa分页查询支持
 *
 * @author Kevice
 * @time 2012-6-19 下午11:39:04
 * @since 1.0.0
 */
public class JpaPagingSupport {

	private JpaPagingSupport() {
	}

    /**
     * 分页查询
     *
     * @param em 实体管理器
     * @param query CriteriaQuery
     * @param root Root
     * @param pageStore PageStore
     * @param <T> 实体类
     * @return 实体列表
     */
	public static <T> List<T> paging(EntityManager em, CriteriaQuery<T> query, Root<T> root, PageStore pageStore) {
		Paging paging = pageStore.getPaging();
		TypedQuery<T> typedQuery = em.createQuery(query);
		List<T> resultList = null;
		if (paging != null) {
			typedQuery.setFirstResult(paging.getOffset());
			typedQuery.setMaxResults(paging.getPageSize());
			resultList = typedQuery.getResultList();
			JpaPagingSupport.count(em, query, root, pageStore);
		}
		return resultList;
	}

	private static <T, R> void count(EntityManager em, CriteriaQuery<T> query, Root<R> root, PageStore pageStore) {
		Paging paging = pageStore.getPaging();
		if (paging != null) {
			long totalCount = paging.getTotalCount();
			if (totalCount == 0) {
				totalCount = doCount(em, query, root);
				paging.setTotalCount(Integer.valueOf(totalCount + ""));
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T, R> long doCount(EntityManager em, CriteriaQuery<T> query, Root<R> root) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		query.select((Selection) cb.tuple(cb.count(root)));
		query.orderBy();
		TypedQuery<T> typedQuery = em.createQuery(query);
		return (Long) typedQuery.getSingleResult();
		
//		 Tuple count = (Tuple) typedQuery.getSingleResult();
//		return (Long) count.get(0);
	}


}
