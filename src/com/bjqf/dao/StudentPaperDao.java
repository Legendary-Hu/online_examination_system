package com.bjqf.dao;

import com.bjqf.entity.StudentList;
import com.bjqf.entity.StudentPaper;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.StudentListMapper;
import com.bjqf.util.JDBCUtil;

import java.util.List;

public class StudentPaperDao {
    /**
     * 添加用户考试信息的方法
     * @param studentPaper
     */
    public void addStudentPaper(StudentPaper studentPaper){
        String sql = "insert into studentpaper(spid,userid,sid,studentkey,studentstate,pname) values (?,?,?,?,?,?)";
        JDBCUtil.executeUpdate(sql, studentPaper.getSpid(),studentPaper.getUserid(),studentPaper.getSid(),studentPaper.getStudentkey(),studentPaper.getStudentstate(),studentPaper.getPname());
    }

    /**统计学生分数的方法
	 * @param userid
	 * @param spid
	 * @return
             */
    public int score(int userid,String spid){
        String sql = "select count(*) as count from studentpaper where studentstate = 1 and studentpaper.userid = ? and spid = ?";
        int count = (int) JDBCUtil.executeQuery(sql, new CountMapper(), userid,spid).get(0);
        return count;
    }
    /**
     * 显示学生答题试卷列表
     * @param userid
     * @return
     */
    public List<StudentList> studentList(int userid){
        String sql = "select spid,userid,pname,count(if(studentstate=1,true,null)) as rightcount,count(if(studentstate=0,true,null)) as errorcount from studentpaper where userid = ? group by spid";
        List<StudentList> list = JDBCUtil.executeQuery(sql, new StudentListMapper(), userid);
        return list;
    }
}
