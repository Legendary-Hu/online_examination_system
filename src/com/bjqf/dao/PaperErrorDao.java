package com.bjqf.dao;

import com.bjqf.entity.PaperError;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.PaperErrorMapper;
import com.bjqf.util.JDBCUtil;

import java.util.List;

public class PaperErrorDao {
    /**
     * 查询数据总数
     * @return
     */
    public int queryTotalNumber(int userid,String spid){
        String sql = "select count(*) as count from "
                + "(select s.sid,s.scontent,s.sa,s.sb,s.sc,s.sd,studentpaper.studentkey from studentpaper "
                + "inner join subject as s "
                + "on studentpaper.sid = s.sid "
                + "where studentstate = 0 and studentpaper.userid = ? and spid = ?) as p";
        int count = (int) JDBCUtil.executeQuery(sql, new CountMapper(), userid,spid).get(0);
        return count;
    }
    /**
     * 分页查询方法
     * @param userid
     * @param spid
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<PaperError> queryByPage(int userid, String spid, int pageNo, int pageSize){
        String sql = "select s.sid,s.scontent,s.sa,s.sb,s.sc,s.sd,s.skey,studentpaper.studentkey from studentpaper "
                + "inner join subject as s "
                + "on studentpaper.sid = s.sid "
                + "where studentstate = 0 and studentpaper.userid = ? and spid = ? "
                + "limit ?,?";
        List<PaperError> list = JDBCUtil.executeQuery(sql, new PaperErrorMapper(), userid,spid,(pageNo-1)*pageSize,pageSize);
        return list;
    }
}
