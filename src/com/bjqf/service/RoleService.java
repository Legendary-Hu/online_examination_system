package com.bjqf.service;

import com.bjqf.dao.RoleDao;
import com.bjqf.entity.FunRole;
import com.bjqf.entity.Role;
import com.bjqf.entity.User;
import com.bjqf.exception.RoleException;
import com.bjqf.exception.UserException;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.UserMapper;
import com.bjqf.util.JDBCUtil;
import com.bjqf.util.PageModel;

import java.util.List;

public class RoleService {
   //创建dao层对象
    RoleDao roleDao = new RoleDao();

    /**
     * 查询所有方法
     */
    public List<Role> selectAll(){
        List<Role> list = roleDao.selectAll();
        return list;
    }

    /**
     * 分页查询
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
        int count = roleDao.queryTotalNumber();
        pageModel.setCount(count);
        //调用分页查询的方法，存入list
        List<Role> list = roleDao.queryByPage(pageNo,pageSize);
        pageModel.setDataList(list);
        return pageModel;
    }

    /**
     * 添加角色
     * @param role
     * @throws UserException
     */
    public void addRole(Role role) throws RoleException {
        int num = roleDao.addRole(role);
        if (num==0){
            throw new RoleException("该用户名已存在！！");
        }
    }

    /**
     * 角色数据回显
     * @param rolename
     * @return
     */
    public List<Role> selectRolename(String rolename){
        List<Role> list = roleDao.selectByRolename(rolename);
        return list;
    }

    /**
     * 修改角色
     * @param role
     * @throws UserException
     */
    public void updateRole(Role role) throws RoleException{
        int num = roleDao.updateRole(role);
        if (num==0){
            throw new RoleException("修改失败！！");
        }
    }

    public List<FunRole> selectFunRole(int roleid){
        List<FunRole> list = roleDao.selectFunRole(roleid);
        return list;
    }
}
