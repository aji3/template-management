package org.xlbean.xlapi.dao.other;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.xlbean.xlapi.dto.ValuedEnum;

public class EnumCodeMappingTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    private Class<E> type;

    public EnumCodeMappingTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            if (parameter instanceof ValuedEnum) {
                ps.setString(i, String.valueOf(((ValuedEnum<?>) parameter).getValue()));
            } else {
                ps.setString(i, parameter.name());
            }
        } else {
            if (parameter instanceof ValuedEnum) {
                ps.setObject(i, ((ValuedEnum<?>) parameter).getValue(), jdbcType.TYPE_CODE);
            } else {
                ps.setObject(i, parameter.name(), jdbcType.TYPE_CODE);
            }
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object s = rs.getObject(columnName);
        return getNullableResultInternal(s);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object s = rs.getObject(columnIndex);
        return getNullableResultInternal(s);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object s = cs.getObject(columnIndex);
        return getNullableResultInternal(s);
    }

    private E getNullableResultInternal(Object value) {
        if (ValuedEnum.class.isAssignableFrom(type)) {
            return value == null ? null : ValuedEnum.valueOf(type, value);
        } else {
            return value == null ? null : Enum.valueOf(type, String.valueOf(value));
        }
    }
}
