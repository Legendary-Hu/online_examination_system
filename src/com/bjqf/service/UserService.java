package com.bjqf.service;

import com.bjqf.dao.UserDao;
import com.bjqf.entity.Role;
import com.bjqf.entity.User;
import com.bjqf.exception.UserException;
import com.bjqf.util.PageModel;

import java.util.List;

public class UserService {
    //创建dao层对象
    UserDao userDao = new UserDao();
    /**
     * 登陆方法
     * @param username
     * @param userpwd
     * @return
     * @throws UserException
     */
    public List<User> login(String username,String userpwd) throws UserException{
        List<User> list = userDao.login(username, userpwd);
        if(list.size() == 0){
            throw new UserException("该用户不存在！！");
        }else if(list.get(0).isRolestate() == false){
            throw new UserException("该角色已经被禁用！！");
        }else{
            return list;
        }
    }
    /**
     * 查询所有方法
     * @return
     */
    public List<User> selectAll(){
        List<User> list = userDao.selectAll();
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
        int count = userDao.queryTotalNumber();
        pageModel.setCount(count);
        //调用分页查询数据的方法，将查询出来的list集合赋值给datalist变量
        List<User> list = userDao.queryByPage(pageNo, pageSize);
        pageModel.setDataList(list);
        return pageModel;
    }
    /**
     * 添加用户
     * @param user
     * @throws UserException
     */
    public void addUser(User user) throws UserException {
        int num = userDao.addUser(user);
        if (num==0){
            throw new UserException("该用户名已存在！！");
        }
    }
    /**
     * 数据回显service层
     * @param username
     * @return
     */
    public List<User> selectByUsername(String username){
        List<User> list = userDao.selectByUsername(username);
        return list;
    }
    /**
     * 修改数据的方法
     * @param User
     * @throws UserException
     */
    public void updateUser(User User) throws UserException{
        int num = userDao.updateUser(User);
        if (num==0){
            throw new UserException("修改失败！！");
        }
    }
    /**
     * 查询role表中数据的方法
     * @return
     */
    public List<Role> queryRole(){
        List<Role> list = userDao.queryRole();
        return list;
    }
}
