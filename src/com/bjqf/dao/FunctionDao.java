package com.bjqf.dao;

import com.bjqf.entity.Function;
import com.bjqf.exception.FunctionException;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.FunctionMapper;
import com.bjqf.util.JDBCUtil;

import java.util.List;

public class FunctionDao {
    /**
     * 查询数据总数
     * @return
     */
    public int queryTotalNumber(){
        String sql = "select count(*) as count from (select a.*,(case when b.funname is null then '无' else b.funname end) as funpname from function a left join function b on a.funpid=b.funid where 0=0) as fun";
        int count = (int) JDBCUtil.executeQuery(sql, new CountMapper(), null).get(0);
        return count;
    }
    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Function> queryByPage(int pageNo,int pageSize){
        String sql = "select a.*,(case when b.funname is null then '无' else b.funname end) as funpname from function a left join function b on a.funpid=b.funid where 0=0 limit ?,?";
        List<Function> list = JDBCUtil.executeQuery(sql, new FunctionMapper(), (pageNo-1)*pageSize,pageSize);
        return list;
    }

    /**
     *添加功能
     * @param function
     * @return
     * @throws FunctionException
     */
    public int addFunction(Function function)throws FunctionException {
        String sql = "insert into function (funname,funurl,funpid,funstate) values (?,?,?,?)";
        int num = JDBCUtil.executeUpdate(sql, function.getFunname(),function.getFunurl(),function.getFunpid(),function.isFunstate());
        return num;
    }

    /**
     * 数据回显
     * @param funid
     * @return
     */
    public List<Function> selectByFunid(int funid){
        String sql = "select a.*,(case when b.funname is null then '无' else b.funname end) as funpname from function a left join function b on a.funpid=b.funid where 0=0 and a.funid = ?";
        List<Function> list = JDBCUtil.executeQuery(sql, new FunctionMapper(), funid);
        return list;
    }

    /**
     * 修改功能
     * @param function
     * @return
     */
    public int updateFunction(Function function){
        String sql = "update function set funname = ?,funurl = ?,funstate = ? where funid = ?";
        int num = JDBCUtil.executeUpdate(sql, function.getFunname(),function.getFunurl(),function.isFunstate(),function.getFunid());
        return num;
    }
}
