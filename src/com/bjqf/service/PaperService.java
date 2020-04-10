package com.bjqf.service;

import com.bjqf.dao.PaperDao;
import com.bjqf.entity.Paper;
import com.bjqf.entity.Subject;
import com.bjqf.exception.PaperException;
import com.bjqf.util.PageModel;

import java.util.List;

public class PaperService {
    //创建Dao层对象
    PaperDao paperDao = new PaperDao();
    /**
     * 查询所有数据的方法
     * @return
     */
    public List<Paper> selectAll(){
        List<Paper> list = paperDao.selectAll();
        return list;
    }
    /**
     * 分页查询service层方法
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageModel queryByPage(int pageNo,int pageSize){
        //创建PageModel对象
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        //调用dao层方法给count赋值
        int count = paperDao.queryTotalNumber();
        pageModel.setCount(count);
        //调用dao层方法给dataList赋值
        List<Paper> list = paperDao.queryByPage(pageNo, pageSize);
        pageModel.setDataList(list);
        return pageModel;
    }
    /**
     * 添加试卷的方法
     * @param pname
     * @param number
     * @throws PaperException
     */
    public void addPaper(String pname,int number) throws PaperException {
        paperDao.addPaper(pname, number);
    }
    /**
     * 查看试题的方法
     * @param pname
     * @return
     */
    public List<Subject> selectSub(String pname){
        List<Subject> list = paperDao.selectSub(pname);
        return list;
    }
}
