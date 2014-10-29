package org.joy.commons.support;

/**
 * 回调接口
 * .
 * @time 2014年5月31日 下午9:28:49
 * @author Kevice
 * @since 1.0.0
 */
public interface ICallback<P, R> {

    /**
     * 回调行为
     *
     * @param p 参数
     * @return 返回值
     * @time 2014年5月31日 下午9:28:49
     * @author Kevice
     * @since 1.0.0
     */
    R execute(P p);

}
