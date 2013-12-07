package com.kvc.joy.plugin.security.login.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.kvc.joy.commons.bean.Pair;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.query.sort.Direction;
import com.kvc.joy.commons.query.sort.Order;
import com.kvc.joy.core.persistence.orm.jpa.BaseJpaDao;
import com.kvc.joy.plugin.security.login.model.po.TLoginLog;
import com.kvc.joy.plugin.security.login.model.po.TLoginLog_;
import com.kvc.joy.plugin.security.login.support.enums.LoginState;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年9月30日 上午12:04:47
 */
public class LoginLogDao extends BaseJpaDao<TLoginLog> {
	
	/**
	 * 统计某帐号一段时间内密码错误次数
	 * 
	 * @param account 用户帐号
	 * @param fromTime 统计时间起
	 * @param toTime 统计时间止
	 * @return 密码错误次数
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年9月30日 上午12:14:57
	 */
	public long statPasswordErrorCount(final String account, final String fromTime, final String toTime) {
		return count(new JPACallBack<TLoginLog>() {
			
			@Override
			public Expression<Boolean> getRestriction(CriteriaBuilder cb, Root<TLoginLog> root) {
				return cb.and(
						cb.equal(root.get(TLoginLog_.userAccount), account), 
						cb.equal(root.get(TLoginLog_.loginStateCode), LoginState.PASSWORD_ERR.getCode()),
						cb.greaterThan(root.get(TLoginLog_.loginTime), fromTime),
						cb.lessThan(root.get(TLoginLog_.loginTime), toTime)
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
	 * @author 唐玮琳
	 * @time 2013年10月1日 上午11:08:03
	 */
	public boolean isPasswordErrorFrequently(final String account, final String fromTime, final String toTime, final int count) {
 		List<TLoginLog> logList = doQuery(new JPACallBack<TLoginLog>() {
			
			@Override
			public Expression<Boolean> getRestriction(CriteriaBuilder cb, Root<TLoginLog> root) {
				return cb.and(
						cb.equal(root.get(TLoginLog_.userAccount), account), 
						cb.or(cb.equal(root.get(TLoginLog_.loginStateCode), LoginState.SUCCESS.getCode()), 
								cb.equal(root.get(TLoginLog_.loginStateCode), LoginState.PASSWORD_ERR.getCode())),
						cb.between(root.get(TLoginLog_.loginTime), fromTime, toTime)
						);
			}
			
			@Override
			public Pair<Integer, Integer> getPageRange() {
				return new Pair<Integer, Integer>(0, count);
			}
			
			@Override
			public Order[] getOrders() {
				return new Order[] {new Order(TLoginLog_.loginTime.getName(), Direction.DESC)};
			}
			
		});
		
		for (TLoginLog log : logList) {
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
	 * @author 唐玮琳
	 * @time 2013年10月15日 上午10:48:58
	 */
	public TLoginLog getPreLoginSuccessLog(final String curLogId, final String userId) {
		List<TLoginLog> result = doQuery(new JPACallBack<TLoginLog>() {

			@Override
			public Expression<Boolean> getRestriction(CriteriaBuilder cb, Root<TLoginLog> root) {
				List<Predicate> predicates = new ArrayList<Predicate>(4);
				predicates.add(cb.equal(root.get(TLoginLog_.userId), userId));
				predicates.add(cb.equal(root.get(TLoginLog_.loginStateCode), LoginState.SUCCESS.getCode()));
				if (StringTool.isNotBlank(curLogId)) {
					predicates.add(cb.notEqual(root.get(TLoginLog_.id), curLogId));	
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
			@Override
			public Pair<Integer, Integer> getPageRange() {
				return new Pair<Integer, Integer>(0, 1);
			}
			
			@Override
			public Order[] getOrders() {
				return new Order[] {new Order(TLoginLog_.loginTime.getName(), Direction.DESC)};
			}
		});
		return uniqueResult(result);
	}

}
