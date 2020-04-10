package com.bjqf.mapper;

import com.bjqf.entity.PaperError;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaperErrorMapper implements RowMapper{
    @Override
    public Object rowMapper(ResultSet rs) throws SQLException {
        //创建对象
        PaperError paperError = new PaperError();
        paperError.setSid(rs.getInt("sid"));
        paperError.setScontent(rs.getString("scontent"));
        paperError.setSa(rs.getString("sa"));
        paperError.setSb(rs.getString("sb"));
        paperError.setSc(rs.getString("sc"));
        paperError.setSd(rs.getString("sd"));
        paperError.setSkey(rs.getString("skey"));
        paperError.setStudentkey(rs.getString("studentkey"));
        return paperError;
    }
}
