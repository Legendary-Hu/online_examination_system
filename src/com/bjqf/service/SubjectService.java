package com.bjqf.service;

import com.bjqf.dao.SubjectDao;
import com.bjqf.entity.Subject;
import com.bjqf.exception.SubjectException;
import com.bjqf.util.PageModel;

import java.util.List;

public class SubjectService {
    //创建Dao层对象
    SubjectDao subjectDao = new SubjectDao();
    /**
     * 查询所有方法
     * @return
     */
    public List<Subject> selectAll(){
        List<Subject> list = subjectDao.selectAll();
        return list;
    }

    /**
     * 分页查询方法
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageModel queryByPage(int pageNo, int pageSize){
        //创建分页对象
        PageModel pageModel = new PageModel();
        //给属性赋值
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        //调用查询总数的方法获取count值并赋值给pageModel中的count值
        int count = subjectDao.queryTotalNumber();
        pageModel.setCount(count);
        //调用分页查询数据的方法，将查询出来的list集合赋值给datalist变量
        List<Subject> list = subjectDao.queryByPage(pageNo, pageSize);
        pageModel.setDataList(list);
        return pageModel;
    }
    /**
     * 添加题目的方法
     * @param subject
     * @throws SubjectException
     */
    public void addSubject(Subject subject) throws SubjectException {
        int num=subjectDao.addSubject(subject);
        if (num==0){
            throw new SubjectException("该题干已存在");
        }
    }
    /**
     * 根据sid查询数据
     * @param sid
     * @return
     */
    public List<Subject> selectBySid(int sid){
        List<Subject> list = subjectDao.selectBySid(sid);
        return list;
    }
    /**
     * 修改数据的方法
     * @param subject
     * @throws SubjectException
     */
    public void updateSubject(Subject subject) throws SubjectException{
        int num = subjectDao.updateSubject(subject);
        if (num==0){
            throw new SubjectException("该试题已经存在！！");
        }
    }
}
