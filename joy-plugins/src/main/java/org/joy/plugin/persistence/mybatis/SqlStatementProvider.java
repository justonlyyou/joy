package org.joy.plugin.persistence.mybatis;

import org.joy.commons.bean.IEntity;
import org.joy.commons.exception.SystemException;
import org.joy.commons.lang.ArrayTool;
import org.joy.commons.lang.string.StringTool;
import org.joy.commons.query.Paging;
import org.joy.commons.query.QueryLogic;
import org.joy.commons.query.QueryLogicOperator;
import org.joy.commons.query.sort.Order;
import org.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.DbSupportFactory;
import org.joy.core.persistence.jdbc.support.enums.RdbType;
import org.joy.core.persistence.jdbc.support.utils.MdRdbTool;
import org.joy.plugin.persistence.mybatis.service.IEntityMappingHolder;
import org.joy.plugin.support.PluginBeanFactory;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.text.MessageFormat;
import java.util.*;

/**
 * sql语句提供者
 *
 * @author Kevice
 * @time 2014年10月23日 上午09:36:42
 * @since 1.0.0
 */
public class SqlStatementProvider {

    private IEntityMappingHolder holder = PluginBeanFactory.getEntityMappingHolder();

    public String get(Map<String, Object> map) {
        Class clazz = (Class) map.get("clazz");
        String tableName = holder.getTableName(clazz);
        String idColumn = holder.getColumnName(clazz, "id");
        String sqlPattern = "select * from {0} where {1} = #'{id}'";
        return MessageFormat.format(sqlPattern, tableName, idColumn);
    }

    public String insert(Map<String, Object> map) {
        Class clazz = (Class) map.get("clazz");
        Object entity = map.get("entity");
        String tableName = holder.getTableName(clazz);
//        Map<String, Object> properties = BeanTool.extract(entity);
        StringBuilder columnNames = new StringBuilder();
        StringBuilder values = new StringBuilder();
        Map<String, MdRdbColumn> columns = MdRdbTool.getColumns((String) null, tableName);
        for (String columnName : columns.keySet()) {
            String propertyName = holder.getPropertyName(clazz, columnName);
//            Object value = properties.get(propertyName);
//            if (value != null) {
            columnNames.append(columnName).append(",");
            values.append("#{entity.").append(propertyName).append("},");
//            }
        }
        String sqlPattern = "insert into {0} ( {1} ) values ( {2} )";
        return MessageFormat.format(sqlPattern, tableName,
                columnNames.substring(0, columnNames.length() - 1), values.substring(0, values.length() - 1));
    }

    public String update(Map<String, Object> map) {
        Class clazz = (Class) map.get("clazz");
        Object entity = map.get("entity");
        String tableName = holder.getTableName(clazz);
        String idField = holder.getColumnName(clazz, "id");
        StringBuilder setValues = new StringBuilder();
        Map<String, MdRdbColumn> columns = MdRdbTool.getColumns((String) null, tableName);
        for (String columnName : columns.keySet()) {
            String propertyName = holder.getPropertyName(clazz, columnName);
            if (!"id".equals(propertyName)) {
                setValues.append(columnName).append("=").append("#{entity.").append(propertyName).append("},");
            }
        }
        String sqlPattern = "update {0} set {1} where {2} = #'{entity.id}'";
        return MessageFormat.format(sqlPattern, tableName, setValues.subSequence(0, setValues.length() - 1), idField);
    }

    public String delete(Map<String, Object> map) {
        Class clazz = (Class) map.get("clazz");
        String tableName = holder.getTableName(clazz);
        String idField = holder.getColumnName(clazz, "id");
        String sqlPattern = "delete from {0} where {1} = #'{id}'";
        return MessageFormat.format(sqlPattern, tableName, idField);
    }

    public String inSearch(Map<String, Object> map) {
        return in(map, "*");
    }

    public String inSearchReturnProperty(Map<String, Object> map) {
        return in(map, getReturnColumnName(map));
    }

    public String inSearchReturnProperties(Map<String, Object> map) {
        return in(map, getReturnColumns(map));
    }

    private String in(Map<String, Object> map, String resultColumn) {
        Class clazz = (Class) map.get("clazz");
        Map<String, Object> elems = (Map<String, Object>) map.get("elems");
        String tableName = holder.getTableName(clazz);
        StringBuilder inConditions = new StringBuilder();
        for (int i = 0; i < elems.size(); i++) {
            String key = "" + i;
            Object elem = elems.get(key);
            if (elem != null) {
                inConditions.append("#{elems.").append(key).append("},");
            }
        }
        String sqlPattern = "select {0} from {1} where {2} in ({3})";
        return MessageFormat.format(sqlPattern, resultColumn, tableName, getColumnName(map), inConditions.substring(0, inConditions.length() - 1));
    }

    public String search(Map<String, Object> map) {
        Class clazz = (Class) map.get("clazz");
        String tableName = holder.getTableName(clazz);
        String sqlPattern = "select * from {0} {1}";
        return MessageFormat.format(sqlPattern, tableName, getOrders(map)).trim();
    }

    public String searchReturnProperty(Map<String, Object> map) {
        Class clazz = (Class) map.get("clazz");
        String tableName = holder.getTableName(clazz);
        String sqlPattern = "select {0} from {1} {2}";
        return MessageFormat.format(sqlPattern, getReturnColumnName(map), tableName, getOrders(map)).trim();
    }

    public String searchReturnProperties(Map<String, Object> map) {
        Class clazz = (Class) map.get("clazz");
        String tableName = holder.getTableName(clazz);
        String sqlPattern = "select {0} from {1} {2}";
        return MessageFormat.format(sqlPattern, getReturnColumns(map), tableName, getOrders(map)).trim();
    }

    public String andSearch(Map<String, Object> map) {
        return andOrSearch(map, "and", "*");
    }

    public String andSearchReturnProperty(Map<String, Object> map) {
        return andOrSearch(map, "and", getReturnColumnName(map));
    }

    public String andSearchReturnProperties(Map<String, Object> map) {
        return andOrSearch(map, "and", getReturnColumns(map));
    }

    public String orSearch(Map<String, Object> map) {
        return andOrSearch(map, "or", "*");
    }

    public String orSearchReturnProperty(Map<String, Object> map) {
        return andOrSearch(map, "or", getReturnColumnName(map));
    }

    public String orSearchReturnProperties(Map<String, Object> map) {
        return andOrSearch(map, "or", getReturnColumns(map));
    }

    private String andOrSearch(Map<String, Object> map, String logicOp, String resultColumn) {
        Class clazz = (Class) map.get("clazz");
        Map<String, Object> properties = (Map<String, Object>) map.get("properties");
        String tableName = holder.getTableName(clazz);
        StringBuilder whereClause = new StringBuilder();
        for (Map.Entry<String, Object> property : properties.entrySet()) {
            String propertyName = property.getKey();
            String columnName = holder.getColumnName(clazz, property.getKey());
            Object value = property.getValue();
            whereClause.append(columnName);
            if (value == null) {
                whereClause.append(" is null");
            } else {
                whereClause.append("=").append("#{properties.").append(propertyName).append("}");
            }
            whereClause.append(" ").append(logicOp).append(" ");
        }
        String sqlPattern = "select {0} from {1} where {2} {3}";
        return MessageFormat.format(sqlPattern, resultColumn, tableName, whereClause.subSequence(0, whereClause.length() - 5), getOrders(map)).trim();
    }

    public String pagingSearch(Map<String, Object> map) {
        // where
        Class clazz = (Class) map.get("clazz");
        Map<String, QueryLogic> logics = (Map<String, QueryLogic>) map.get("logics");
        StringBuilder whereClause = new StringBuilder();
        for (int i = 0; i < logics.size(); i++) {
            QueryLogic logic = logics.get("" + i);
            whereClause.append(buildWhereClause(clazz, logic, "#{logics." + i + ".value}")).append(" and ");
        }
        String where = whereClause.subSequence(0, whereClause.length() - 5).toString();

        // sort
        String order = getOrders(map);

        // paging
        Paging paging = (Paging) map.get("paging");
        int first = paging.getFirst();
        int last = paging.getLast();
        int pageSize = paging.getPageSize();

        Connection connection = MyBatisTool.getConnection();
        DbSupport dbSupport = DbSupportFactory.createDbSupport(connection);
        RdbType dbType = dbSupport.getDatabaseType();
        String sqlPattern = null;
        switch (dbType) {
            case MYSQL:
                first--;
                sqlPattern = "select * from {0} where {1} {2} limit {3},{4}";
                break;
            case ORACLE:
                pageSize += first; // 当作最后一条
                Order[] orders = (Order[]) map.get("orders");
                if (ArrayTool.isEmpty(orders)) {
                    sqlPattern = "select * from （" +
                            "    select t.*,rownum from {0} t where {1} {2})" +
                            "    where rownum>={3} and rownum<{4}";
                } else {
                    sqlPattern = "select * from （" +
                            "    select t.*,rownum from (" +
                            "        select * from {0} where {1} {2}) t )" +
                            "    where rownum>={3} and rownum<{4}";
                }
                break;
            case H2:
                sqlPattern = "select * from {0} where {1} {2} limit {3},{4}";
                break;
        }
        String tableName = holder.getTableName(clazz);
        return MessageFormat.format(sqlPattern, tableName, where, order, first, pageSize);
    }

    private String buildWhereClause(Class<? extends IEntity> clazz, QueryLogic logic, String valueTmpl) {
        String property = logic.getProperty();
        Object value = logic.getValue();
        QueryLogicOperator operator = logic.getOperator();
        String column = holder.getColumnName(clazz, property);
        if (value == null || "".equals(value)) {
            return null;
        }
        if (operator.getCode().contains("LIKE") && value instanceof String) {
            value = ((String) value).replaceAll("%", "\\\\%").replaceAll("_", "\\\\_");
        }
        switch (operator) {
            case IEQ:
                if (value instanceof String) {
                    return "lower(" + column + ") = '" + value.toString().toLowerCase() + "'";
                }
                return column + " =" + valueTmpl;
            case EQ_P:
                return column + " = " + value;
            case NE_P:
            case LG_P:
                return column + " != " + value;
            case GE_P:
                return column + " >= " + value;
            case LE_P:
                return column + " <= " + value;
            case GT_P:
                return column + " > " + value;
            case LT_P:
                return column + " < " + value;
            case LIKE:
                return column + " like '%" + value + "%'";
            case LIKE_S:
                return column + " like '" + value + "%'";
            case LIKE_E:
                return column + " like '%" + value + "'";
            case ILIKE:
                return "lower(" + column + ") like '%" + value.toString().toLowerCase() + "%'";
            case ILIKE_S:
                return "lower(" + column + ") like '" + value.toString().toLowerCase() + "%'";
            case ILIKE_E:
                return "lower(" + column + ") like '%" + value.toString().toLowerCase() + "'";
            case IN:
                Class<?> returnType = null;
                try {
                    Method method = clazz.getMethod("get" + StringTool.capitalize(property));
                    returnType = method.getReturnType();
                } catch (NoSuchMethodException e) {
                    throw new SystemException(e);
                }
                List<Object> elems = null;
                if (value instanceof String) {
                    Object[] values = ((String) value).split(",");
                    elems = Arrays.asList(values);
                } else if (value instanceof Collection) {
                    elems = new ArrayList<>((Collection<Object>) value);
                } else {
                    elems = new ArrayList<>(1);
                    elems.add(value);
                }
                StringBuilder sb = new StringBuilder(column);
                sb.append(" in (");
                for (Object elem : elems) {
                    if (returnType == String.class) {
                        sb.append("'").append(elem).append("'");
                    } else {
                        sb.append(elem);
                    }
                    sb.append(",");
                }
                return sb.toString().replaceFirst(",$", ")");
            case IS_NULL:
                return column + " is null";
            case IS_NOT_NULL:
                return column + " is not null";
            case IS_EMPTY:
                return column + " = ''";
            case IS_NOT_EMPTY:
                return column + " != ''";
            default:
                return column + " " + operator.getCode() + valueTmpl;
        }
    }

    private String getOrders(Map<String, Object> map) {
        Class clazz = (Class) map.get("clazz");
        Order[] orders = (Order[]) map.get("orders");
        if (ArrayTool.isEmpty(orders)) {
            return "";
        }
        StringBuilder orderSb = new StringBuilder("order by ");
        for (Order order : orders) {
            String columnName = holder.getColumnName(clazz, order.getProperty());
            orderSb.append(columnName).append(" ").append(order.getDirection().name().toLowerCase()).append(",");
        }
        return orderSb.substring(0, orderSb.length() - 1);
    }

    private String getReturnColumns(Map<String, Object> map) {
        Class clazz = (Class) map.get("clazz");
        Collection<String> properties = (Collection<String>) map.get("returnProperties");
        StringBuilder resultColumns = new StringBuilder();
        for (String propertyName : properties) {
            String columnName = holder.getColumnName(clazz, propertyName);
            resultColumns.append(columnName).append(",");
        }
        return resultColumns.substring(0, resultColumns.length() - 1);
    }

    private String getReturnColumnName(Map<String, Object> map) {
        Class clazz = (Class) map.get("clazz");
        String propertyName = (String) map.get("returnProperty");
        return holder.getColumnName(clazz, propertyName);
    }

    private String getColumnName(Map<String, Object> map) {
        Class clazz = (Class) map.get("clazz");
        String propertyName = (String) map.get("property");
        return holder.getColumnName(clazz, propertyName);
    }

}
