package com.bjqf.mapper;

import com.bjqf.entity.StudentList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentListMapper implements RowMapper {
    @Override
    public Object rowMapper(ResultSet rs) throws SQLException {
        StudentList studentList = new StudentList();
        studentList.setSpid(rs.getString("spid"));
        studentList.setUserid(rs.getInt("userid"));
        studentList.setPname(rs.getString("pname"));
        studentList.setRightcount(rs.getInt("rightcount"));
        studentList.setErrorcount(rs.getInt("errorcount"));
        return studentList;
    }
}
