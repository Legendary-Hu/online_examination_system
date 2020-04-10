package com.bjqf.mapper;

import com.bjqf.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper {
    @Override
    public Object rowMapper(ResultSet rs) throws SQLException {
        //创建Role对象
        Role role = new Role();
        role.setRoleid(rs.getInt("roleid"));
        role.setRolename(rs.getString("rolename"));
        role.setRolestate(rs.getBoolean("rolestate"));
        return role;
    }
}
