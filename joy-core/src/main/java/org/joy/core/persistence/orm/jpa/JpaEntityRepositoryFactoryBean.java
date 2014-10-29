package org.joy.core.persistence.orm.jpa;

import org.joy.commons.bean.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * JPA实体仓库工厂bean
 * 
 * @author Kevice
 * @time 2012-6-26 下午10:46:39
 * @since 1.0.0
 */
public class JpaEntityRepositoryFactoryBean<R extends JpaRepository<T, I>, T extends IEntity<I>, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

	@SuppressWarnings("rawtypes")
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new JpaEntityRepositoryFactory(entityManager);
	}

	private static class JpaEntityRepositoryFactory<T extends IEntity<I>, I extends Serializable> extends JpaRepositoryFactory {

		private final EntityManager entityManager;

		public JpaEntityRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}

		@SuppressWarnings("unchecked")
		protected Object getTargetRepository(RepositoryMetadata metadata) {
			return new JpaEntityRepository<T, I>((Class<T>) metadata.getDomainType(), entityManager);
		}

		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			// The RepositoryMetadata can be safely ignored, it is used by the
			// JpaRepositoryFactory
			// to check for QueryDslJpaRepository's which is out of scope.
			return IJpaEntityRepository.class;
		}

	}

}
