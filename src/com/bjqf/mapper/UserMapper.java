package com.bjqf.mapper;

import com.bjqf.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper {
    @Override
    public Object rowMapper(ResultSet rs) throws SQLException {
        //创建user对象
        User user  = new User();
        //将结果集中的值拿出来赋值给对象
        user.setUserid(rs.getInt("userid"));
        user.setRoleid(rs.getInt("roleid"));
        user.setUsername(rs.getString("username"));
        user.setUserpwd(rs.getString("userpwd"));
        user.setUsertruename(rs.getString("usertruename"));
        user.setRolename(rs.getString("rolename"));
        user.setRolestate(rs.getBoolean("rolestate"));
        user.setUserstate(rs.getBoolean("userstate"));
        return user;
    }
}
