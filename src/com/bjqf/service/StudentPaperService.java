package com.bjqf.service;

import com.bjqf.dao.StudentPaperDao;
import com.bjqf.entity.StudentList;
import com.bjqf.entity.StudentPaper;

import java.util.List;

public class StudentPaperService {
    //创建Dao层对象
    StudentPaperDao studentPaperDao = new StudentPaperDao();
    /**
     * 添加用户试卷答题信息
     * @param studentPaper
     */
    public void addStudentPaper(StudentPaper studentPaper){
        studentPaperDao.addStudentPaper(studentPaper);
    }

    /**
     * 获取学生分数的方法
     * @param userid
     * @param spid
     * @return
     */
    public int score(int userid,String spid){
        int count = studentPaperDao.score(userid, spid);
        return count;
    }
    /**
     * 查看学生答题列表
     * @param userid
     * @return
     */
    public List<StudentList> studentList(int userid){
        List<StudentList> list = studentPaperDao.studentList(userid);
        return list;
    }
}
