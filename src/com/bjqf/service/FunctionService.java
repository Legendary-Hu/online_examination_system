package com.bjqf.service;

import com.bjqf.dao.FunctionDao;
import com.bjqf.entity.Function;
import com.bjqf.exception.FunctionException;
import com.bjqf.util.PageModel;

import java.util.List;

public class FunctionService {
    //创建对象
    FunctionDao functionDao = new FunctionDao();

    /**
     * 分页查询所有
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageModel queryByPage(int pageNo,int pageSize){
        //创建对象
        PageModel pageModel = new PageModel();
        //赋值
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        //获取总页数
        int num = functionDao.queryTotalNumber();
        pageModel.setCount(num);
        List<Function> list = functionDao.queryByPage(pageNo, pageSize);
        pageModel.setDataList(list);
        return pageModel;
    }

    /**
     * 添加功能
     * @param function
     * @throws FunctionException
     */
    public void addFunction(Function function) throws FunctionException{
        int num = functionDao.addFunction(function);
        if(num==0){
            throw new FunctionException("该功能已存在！！！");
        }
    }

    /**
     * 数据回显，根据funid
     * @param funid
     * @return
     */
    public List<Function> selectByFunid(int funid){
        List<Function> list = functionDao.selectByFunid(funid);
        return list;
    }

    /**
     * 修改功能信息
     * @param function
     * @throws FunctionException
     */
    public void updateFunction(Function function) throws FunctionException{
        int num = functionDao.updateFunction(function);
        if(num == 0){
            throw new FunctionException("修改失败！！！");
        }
    }
}
