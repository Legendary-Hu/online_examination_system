package com.bjqf.mapper;

import com.bjqf.entity.Function;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FunctionMapper implements RowMapper {
    @Override
    public Object rowMapper(ResultSet rs) throws SQLException {
        Function function = new Function();
        function.setFunid(rs.getInt("funid"));
        function.setFunname(rs.getString("funname"));
        function.setFunurl(rs.getString("funurl"));
        function.setFunpid(rs.getInt("funpid"));
        function.setFunstate(rs.getBoolean("funstate"));
        function.setFunpname(rs.getString("funpname"));
        return function;
    }
}
