package com.bjqf.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountMapper implements RowMapper{
    @Override
    public Object rowMapper(ResultSet rs) throws SQLException {
        Integer count = new Integer(rs.getInt("count"));
        return count;
    }
}
