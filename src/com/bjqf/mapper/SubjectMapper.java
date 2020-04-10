package com.bjqf.mapper;

import com.bjqf.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements RowMapper{

    @Override
    public Object rowMapper(ResultSet rs) throws SQLException {
        Subject subject = new Subject();
        subject.setSid(rs.getInt("sid"));
        subject.setScontent(rs.getString("scontent"));
        subject.setSa(rs.getString("sa"));
        subject.setSb(rs.getString("sb"));
        subject.setSc(rs.getString("sc"));
        subject.setSd(rs.getString("sd"));
        subject.setSkey(rs.getString("skey"));
        subject.setSstate(rs.getBoolean("sstate"));
        return subject;
    }
}
