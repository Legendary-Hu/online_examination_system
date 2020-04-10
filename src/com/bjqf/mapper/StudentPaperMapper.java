package com.bjqf.mapper;

import com.bjqf.entity.StudentPaper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentPaperMapper implements RowMapper{
    @Override
    public Object rowMapper(ResultSet rs) throws SQLException {
        //创建对象
        StudentPaper studentPaper = new StudentPaper();
        //给对象赋值
        studentPaper.setSpid(rs.getString("spid"));
        studentPaper.setUserid(rs.getInt("userid"));
        studentPaper.setSid(rs.getInt("sid"));
        studentPaper.setStudentkey(rs.getString("studentkey"));
        studentPaper.setStudentstate(rs.getString("studentstate"));
        studentPaper.setPname(rs.getString("pname"));
        return studentPaper;
    }
}
