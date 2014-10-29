package org.joy.core.persistence.jdbc.service;

import org.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import org.joy.core.persistence.jdbc.model.vo.RdbConnection;

import java.io.Serializable;
import java.util.Map;

/**
 * 关系型数据库列元数据信息服务接口
 *
 * @author Kevice
 * @time 2013-2-3 下午9:22:12
 * @since 1.0.0
 */
public interface IMdRdbColumnService extends Serializable {

    /**
     * 根据指定的连接和表名，获取对应表的所有列的元数据信息
     *
     * @param connection 关系型数据库连接
     * @param tableName 表名
     * @return Map<表名, 列元数据信息>
     * @author Kevice
     * @time 2013-2-3 下午9:25:26
     * @since 1.0.0
     */
    Map<String, MdRdbColumn> getColumns(RdbConnection connection, String tableName);

}
