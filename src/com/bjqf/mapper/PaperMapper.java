package com.bjqf.mapper;

import com.bjqf.entity.Paper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaperMapper implements RowMapper {
    @Override
    public Object rowMapper(ResultSet rs) throws SQLException {
        Paper paper = new Paper();
        paper.setPid(rs.getInt("pid"));
        paper.setPname(rs.getString("pname"));
        paper.setSid(rs.getInt("sid"));
        paper.setPcount(rs.getInt("pcount"));
        return paper;
    }
}
