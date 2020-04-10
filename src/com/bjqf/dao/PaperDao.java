package com.bjqf.dao;

import com.bjqf.entity.Paper;
import com.bjqf.entity.Subject;
import com.bjqf.exception.PaperException;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.PaperMapper;
import com.bjqf.mapper.SubjectMapper;
import com.bjqf.util.JDBCUtil;

import java.util.List;

public class PaperDao {

    /**
     * 查询所有数据的方法
     * @return
     */
    public List<Paper> selectAll(){
        String sql = "select paper.*,count(*) as pcount from paper group by pname";
        List<Paper> list = JDBCUtil.executeQuery(sql, new PaperMapper(), null);
        return list;
    }
    /**
     * 统计数据库表中数据的总数
     * @return
     */
    public int queryTotalNumber(){
        String sql = "select count(*) as count from (select paper.*,count(*) as pcount from paper group by pname) as p";
        int num = (int) JDBCUtil.executeQuery(sql, new CountMapper(), null).get(0);
        return num;
    }
    /**
     * 获取分页查询得到的数据
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Paper> queryByPage(int pageNo,int pageSize){
        String sql = "select * from ((select paper.*,count(*) as pcount from paper group by pname) as p) limit ?,?";
        List<Paper> list = JDBCUtil.executeQuery(sql, new PaperMapper(), (pageNo-1)*pageSize,pageSize);
        return list;
    }
    /**
     * 添加试卷方法
     * @param pname
     * @param number
     * @throws PaperException
     */
    public void addPaper(String pname,int number) throws PaperException {
        //根据sstate查询可用的试题数量，并与number进行判断
        String sql = null;
        sql = "select * from subject where sstate = 1";
        List<Subject> list = JDBCUtil.executeQuery(sql, new SubjectMapper(), null);
        /*
         * 获取集合的size()与number进行判断
         * 如果list.size() < number,证明填入的数据大于subject表中可用试题的数据，此时需要抛出自定义异常
         * 如果list.size() >= number,证明可以执行添加操作
         */
        if(list.size() < number){
            throw new PaperException("题目数量大于题库数量，添加失败！！！");
        }else{
            sql = "insert into paper (pname,sid) select ?,sid from subject where sstate = 1 order by rand() limit ?";
            JDBCUtil.executeUpdate(sql, pname,number);
        }
    }
    /**
     * 根据pname查看试题
     * @param pname
     * @return
     *
     */
    public List<Subject> selectSub(String pname){
        String sql = "select subject.* from subject inner join paper on subject.sid=paper.sid where paper.pname=?";
        List<Subject> list = JDBCUtil.executeQuery(sql,new SubjectMapper(),pname);
        return list;
    }
}
