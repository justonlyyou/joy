package org.joy.plugin.security.user.dao;

import org.joy.commons.bean.Pair;
import org.joy.commons.lang.string.StringTool;
import org.joy.commons.query.sort.Direction;
import org.joy.commons.query.sort.Order;
import org.joy.core.persistence.orm.jpa.BaseJpaDao;
import org.joy.plugin.security.user.model.po.TUserLoginLog;
import org.joy.plugin.security.user.model.po.TUserLoginLog_;
import org.joy.plugin.security.user.support.enums.LoginState;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年9月30日 上午12:04:47
 */
public class UserLoginLogDao extends BaseJpaDao<TUserLoginLog> {
	
	/**
	 * 统计某帐号一段时间内密码错误次数
	 * 
	 * @param account 用户帐号
	 * @param fromTime 统计时间起
	 * @param toTime 统计时间止
	 * @return 密码错误次数
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年9月30日 上午12:14:57
	 */
	public long statPasswordErrorCount(final String account, final String fromTime, final String toTime) {
		return count(new JPACallBack<TUserLoginLog>() {
			
			@Override
			public Expression<Boolean> getRestriction(CriteriaBuilder cb, Root<TUserLoginLog> root) {
				return cb.and(
						cb.equal(root.get(TUserLoginLog_.userAccount), account),
						cb.equal(root.get(TUserLoginLog_.loginStateCode), LoginState.PASSWORD_ERR.getCode()),
						cb.greaterThan(root.get(TUserLoginLog_.loginTime), fromTime),
						cb.lessThan(root.get(TUserLoginLog_.loginTime), toTime)
						);
			}
		});
	}
	
	/**
	 * 检查某帐号一段时间内最近密码是否连续错误达指定的次数
	 * 
	 * @param account 用户帐号
	 * @param fromTime 登陆时间起
	 * @param toTime 登陆时间止
	 * @param count 连续错误的次数
	 * @return true: 连续错误达指定的次数, false: 未达到
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月1日 上午11:08:03
	 */
	public boolean isPasswordErrorFrequently(final String account, final String fromTime, final String toTime, final int count) {
 		List<TUserLoginLog> logList = doQuery(new JPACallBack<TUserLoginLog>() {
			
			@Override
			public Expression<Boolean> getRestriction(CriteriaBuilder cb, Root<TUserLoginLog> root) {
				return cb.and(
						cb.equal(root.get(TUserLoginLog_.userAccount), account),
						cb.or(cb.equal(root.get(TUserLoginLog_.loginStateCode), LoginState.SUCCESS.getCode()),
								cb.equal(root.get(TUserLoginLog_.loginStateCode), LoginState.PASSWORD_ERR.getCode())),
						cb.between(root.get(TUserLoginLog_.loginTime), fromTime, toTime)
						);
			}
			
			@Override
			public Pair<Integer, Integer> getPageRange() {
				return new Pair<Integer, Integer>(0, count);
			}
			
			@Override
			public Order[] getOrders() {
				return new Order[] {new Order(TUserLoginLog_.loginTime.getName(), Direction.DESC)};
			}
			
		});
		
		for (TUserLoginLog log : logList) {
			if (log.getLoginState() == LoginState.SUCCESS) {
				return false;
			}
		}
		
		return logList.size() == count;
	}
	
	/**
	 * 取得当前日志的前一条登陆成功的日志
	 * 
	 * @param curLogId 当前日志id，为空时返回最近一条登陆成功的日志
	 * @param userId 用户id
	 * @return 前一条登陆成功的日志
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月15日 上午10:48:58
	 */
	public TUserLoginLog getPreLoginSuccessLog(final String curLogId, final String userId) {
		List<TUserLoginLog> result = doQuery(new JPACallBack<TUserLoginLog>() {

			@Override
			public Expression<Boolean> getRestriction(CriteriaBuilder cb, Root<TUserLoginLog> root) {
				List<Predicate> predicates = new ArrayList<Predicate>(4);
				predicates.add(cb.equal(root.get(TUserLoginLog_.userId), userId));
				predicates.add(cb.equal(root.get(TUserLoginLog_.loginStateCode), LoginState.SUCCESS.getCode()));
				if (StringTool.isNotBlank(curLogId)) {
					predicates.add(cb.notEqual(root.get(TUserLoginLog_.id), curLogId));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
			@Override
			public Pair<Integer, Integer> getPageRange() {
				return new Pair<Integer, Integer>(0, 1);
			}
			
			@Override
			public Order[] getOrders() {
				return new Order[] {new Order(TUserLoginLog_.loginTime.getName(), Direction.DESC)};
			}
		});
		return uniqueResult(result);
	}

}
