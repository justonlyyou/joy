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
 * 
 * @author Kevice
 * @time 2012-6-19 下午11:39:04
 */
public class JpaPagingSupport {

	private JpaPagingSupport() {
	}

	public static <T, R> List<T> paging(EntityManager em, CriteriaQuery<T> query, Root<R> root, PageStore pageStore) {
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
