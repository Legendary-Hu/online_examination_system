package com.bjqf.dao;


import com.bjqf.entity.Role;
import com.bjqf.entity.User;
import com.bjqf.exception.UserException;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.RoleMapper;
import com.bjqf.mapper.UserMapper;
import com.bjqf.util.JDBCUtil;

import java.util.List;

public class UserDao {
    /**
     * 登陆方法
     * @param username
     * @param userpwd
     * @return
     */
    public List<User> login(String username,String userpwd){
        String sql = "select a.*,b.rolestate,b.rolename from user a "
                + "inner join role b "
                + "on a.roleid = b.roleid "
                + "where username = ? and userpwd = ?";
        List<User> list = JDBCUtil.executeQuery(sql, new UserMapper(), username,userpwd);
        return list;
    }

    /**
     * 查询所有方法
     * @return
     */
    public List<User> selectAll() {
        String sql = "select * from user,role where user.roleid=role.roleid";
        List<User> list = JDBCUtil.executeQuery(sql, new UserMapper(), null);
        return list;
    }
    /**
     * 获取数据库中数据总数的方法
     * @return
     */
    public int queryTotalNumber(){
        String sql = "select count(*) as count from user";
        /*
         * 因为查询出来的数据是存储在list集合中,
         * 由于select count(*) as count from user肯定能够获取到数据
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
    public List<User> queryByPage(int pageNo,int pageSize){
        String sql = "select * from user,role where user.roleid=role.roleid limit ?,?";
        List<User> list = JDBCUtil.executeQuery(sql, new UserMapper(),(pageNo-1)*pageSize,pageSize);
        return list;
    }
    /**
     * 添加用户的方法
     * @param user
     * @throws UserException
     */
    public int addUser(User user) throws UserException {
        String sql = null;

        //执行去数据库中根据username查询数据
        sql = "select * from user,role where user.roleid=role.roleid and username = ?";
        List<User> list = JDBCUtil.executeQuery(sql, new UserMapper(), user.getUsername());
        if(list.size() == 0){
            sql = "insert into user (roleid,username,userpwd,usertruename,userstate) values (?,?,?,?,?)";

            //给参数赋值需要与sql语句中字段名一一对应
            int num= JDBCUtil.executeUpdate(sql,user.getRoleid(),user.getUsername(),user.getUserpwd(),user.getUsertruename(),user.isUserstate());
            return num;
        }else{
            throw new UserException("该用户名已被注册！！");
        }
    }
    /**
     * 根据username查询数据
     * 数据回显
     * @param username
     * @return
     */
    public List<User> selectByUsername(String username){
        String sql = "select * from user,role where user.roleid=role.roleid and username = ?";
        List<User> list = JDBCUtil.executeQuery(sql, new UserMapper(), username);
        return list;
    }
    /**
     * 修改数据的方法
     * @param user
     */
    public int updateUser(User user){
        String sql = "update user set roleid=?,userpwd=?,usertruename=?,userstate=? where username = ?";
        int num = JDBCUtil.executeUpdate(sql, user.getRoleid(),user.getUserpwd(),user.getUsertruename(),user.isUserstate(),user.getUsername());
        return num;
    }
    /**
     * 查询role表中所有数据
     * @return
     */
    public List<Role> queryRole(){
        String sql = "select * from role";
        List<Role> list = JDBCUtil.executeQuery(sql, new RoleMapper(), null);
        return list;
    }
}
