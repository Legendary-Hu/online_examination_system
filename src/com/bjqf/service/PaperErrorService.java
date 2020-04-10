package com.bjqf.service;

import com.bjqf.dao.PaperErrorDao;
import com.bjqf.entity.PaperError;
import com.bjqf.util.PageModel;

import java.util.List;

public class PaperErrorService {
    //创建Dao层对象
    PaperErrorDao paperErrorDao = new PaperErrorDao();
    /**
     * 分页查询方法
     * @param userid
     * @param spid
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageModel queryByPage(int userid,String spid,int pageNo,int pageSize){
        //创建PageModel对象
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        int count = paperErrorDao.queryTotalNumber(userid, spid);
        pageModel.setCount(count);
        List<PaperError> dataList = paperErrorDao.queryByPage(userid, spid, pageNo, pageSize);
        pageModel.setDataList(dataList);
        return pageModel;
    }
}
