package ru.jpol.vocabot.dao.mapper;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import ru.jpol.vocabot.entity.Dictionary;

import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumPropertyRowMapper extends BeanPropertyRowMapper<Dictionary> {

    public EnumPropertyRowMapper() {
        super(Dictionary.class);
    }

    @Override
    protected Object getColumnValue(ResultSet rs, int index, PropertyDescriptor pd) throws SQLException {
        if (pd.getPropertyType().isEnum()) {
            return Dictionary.Type.valueOfName(rs.getString(index));
        }
        return super.getColumnValue(rs, index, pd);
    }
}
