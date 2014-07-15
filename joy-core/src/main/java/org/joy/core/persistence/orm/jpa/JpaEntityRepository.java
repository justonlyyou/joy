package org.joy.core.persistence.orm.jpa;

import org.joy.commons.bean.IEntity;
import org.joy.commons.query.sort.Order;
import org.joy.core.rp.pagestore.PageStore;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Kevice
 * @time 2012-6-26 下午8:57:12
 */
public class JpaEntityRepository<T extends IEntity<ID>, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements IJpaEntityRepository<T, ID> {

	private final Class<T> entityClass;

	public JpaEntityRepository(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityClass = domainClass;
	}

	public List<T> searchAll(Order... orders) {
		return JpaTool.searchAll(entityClass, orders);
	}

	public List<T> inSearch(String attr, Collection<?> values, Order... orders) {
		return JpaTool.inSearch(entityClass, attr, values, orders);
	}

	public List<T> inSearch(Collection<?> values, Order... orders) {
		return JpaTool.inSearch(entityClass, values, orders);
	}

	public List<T> search(String attr, Object value, Order... orders) {
		return JpaTool.search(entityClass, attr, value, orders);
	}

	public List<T> andSearch(Map<String, Object> attrMap, Order... orders) {
		return JpaTool.andSearch(entityClass, attrMap, orders);
	}

	public List<T> orSearch(Map<String, Object> attrMap, Order... orders) {
		return JpaTool.orSearch(entityClass, attrMap, orders);
	}

	public List<T> pagingSearch(PageStore pageStore) {
		return JpaTool.pagingSearch(entityClass, pageStore);
	}

	public <E> List<T> inSearch(SingularAttribute<? super T, E> attr, Collection<E> values, Order... orders) {
		return JpaTool.inSearch(entityClass, attr, values, orders);
	}

	public <F> List<T> search(SingularAttribute<? super T, F> attr, F value, Order... orders) {
		return JpaTool.search(entityClass, attr, value, orders);
	}

	public <E> List<T> andQuery(Map<SingularAttribute<? super T, E>, E> attrMap, Order... orders) {
		return JpaTool.andQuery(entityClass, attrMap, orders);
	}

	public <E> List<T> orQuery(Map<SingularAttribute<? super T, E>, E> attrMap, Order... orders) {
		return JpaTool.orQuery(entityClass, attrMap, orders);
	}

	public List<?> findBySql(String sql) {
		return JpaTool.findBySql(sql);
	}

	public long querySequence(String sequence) {
		return JpaTool.querySequence(sequence);
	}

}
