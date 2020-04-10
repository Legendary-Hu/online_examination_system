package com.bjqf.dao;

import com.bjqf.entity.Subject;
import com.bjqf.exception.SubjectException;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.SubjectMapper;
import com.bjqf.util.JDBCUtil;

import java.util.List;

/**
 * 专用于操作subject表的dao类
 * @author Legendary_Hu
 */

public class SubjectDao {
    /**
     * 查询所有方法
     * @return
     */
    public List<Subject> selectAll() {
        String sql = "select * from subject";
        List<Subject> list = JDBCUtil.executeQuery(sql, new SubjectMapper(), null);
        return list;
    }
    /**
     * 获取数据库中数据总数的方法
     * @return
     */
    public int queryTotalNumber(){
        String sql = "select count(*) as count from subject";
        /*
         * 因为查询出来的数据是存储在list集合中,
         * 由于select count(*) as count from subject肯定能够获取到数据
         * 且数据有只有一个
         * 从list集合中获取数据list.get(index)
         * 所以此处可以使用list.get(0)
         */
        int count = (int) JDBCUtil.executeQuery(sql, new CountMapper(), null).get(0);
        return count;
    }
    /**
     * 分页显示数据的方法
     * (pageNo-1)*pageSize:
     * pageNo=3,pageSize=4,第三页就是从第九条数据开始显示 ，下标是(3-1)*4=8
     * pageNo=1,pageSize=3,第一页就是从第一条数据开始显示 ，下标是(1-1)*3=0
     * pageNo=2,pageSize=5,第一页就是从第六条数据开始显示 ，下标是(2-1)*5=5
     * @param pageNo = 3
     * @param pageSize = 4
     * @return
     */
    public List<Subject> queryByPage(int pageNo,int pageSize){
        String sql = "select * from subject limit ?,?";
        List<Subject> list = JDBCUtil.executeQuery(sql, new SubjectMapper(),(pageNo-1)*pageSize,pageSize);
        return list;
    }
    /**
     * 添加题目的方法
     * @param subject
     * @throws SubjectException
     */
    public int  addSubject(Subject subject) throws SubjectException {
        String sql = null;
        //执行去数据库中根据scontent查询数据
        sql = "select * from subject where scontent = ?";
        List<Subject> list = JDBCUtil.executeQuery(sql, new SubjectMapper(), subject.getScontent());
        if(list.size() == 0){
            sql = "insert into subject (scontent,sa,sb,sc,sd,skey,sstate) values (?,?,?,?,?,?,?)";
            //给参数赋值需要与sql语句中字段名一一对应
            int num =JDBCUtil.executeUpdate(sql,subject.getScontent(),subject.getSa(),subject.getSb(),subject.getSc(),subject.getSd(),subject.getSkey(),subject.isSstate());
            return num;
        }else{
            throw new SubjectException("该题干已经添加过了，不能重复添加！！");
        }
    }
    /**
     * 根据sid查询数据
     * @param sid
     * @return
     */
    public List<Subject> selectBySid(int sid){
        String sql = "select * from subject where sid = ?";
        List<Subject> list = JDBCUtil.executeQuery(sql, new SubjectMapper(), sid);
        return list;
    }
    /**
     * 修改数据方法
     * 先根据scontent取数据库中查询是否存在重名的题干
     * 如果list.size()大于0，表示存在重名题干，此时就要给界面层提示
     * 如果List.size()等于0，表示不存在重名题干，此时就可以执行修改操作
     * @param subject
     * @throws SubjectException
     */

    public int updateSubject(Subject subject)throws SubjectException{
        String sql = null;

        sql = "update subject set scontent = ?,sa=?, sb=?,sc=?,sd=?,skey=?,sstate=? where sid=?";
        int num = JDBCUtil.executeUpdate(sql,subject.getScontent(),subject.getSa(),subject.getSb(),subject.getSc(),subject.getSd(),subject.getSkey(),subject.isSstate(),subject.getSid());
        return num;

    }
}
