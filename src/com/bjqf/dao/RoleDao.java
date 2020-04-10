package com.bjqf.dao;

import com.bjqf.entity.FunRole;
import com.bjqf.entity.Role;
import com.bjqf.entity.User;
import com.bjqf.exception.RoleException;
import com.bjqf.exception.UserException;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.FunRoleMapper;
import com.bjqf.mapper.RoleMapper;
import com.bjqf.mapper.UserMapper;
import com.bjqf.util.JDBCUtil;

import java.util.List;

public class RoleDao {
    /**
     * 查询所有方法
     */
    public List<Role> selectAll(){
        String sql = "select * from role";
        List<Role> list = JDBCUtil.executeQuery(sql,new RoleMapper(),null);
        return list;
    }

    /**
     * 获取数据库中数据总数的方法
     * @return
     */
    public int queryTotalNumber(){
        String sql = "select count(*) as count from role";
        /*
         * 因为查询出来的数据是存储在list集合中,
         * 由于select count(*) as count from role肯定能够获取到数据
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
     * pageNo=2,pageSize=5,第二页就是从第六条数据开始显示 ，下标是(2-1)*5=5
     * @param pageNo = 3
     * @param pageSize = 4
     * @return
     */
    public List<Role> queryByPage(int pageNo, int pageSize){
        String sql = "select * from role limit ?,?";
        List<Role> list = JDBCUtil.executeQuery(sql, new RoleMapper(),(pageNo-1)*pageSize,pageSize);
        return list;
    }
    /**
     * 添加用户的方法
     * @param role
     * @throws UserException
     */
    public int addRole(Role role) throws RoleException {
        String sql = null;

        //执行去数据库中根据rolename查询数据
        sql = "select * from role where rolename=?";
        List<User> list = JDBCUtil.executeQuery(sql, new RoleMapper(), role.getRolename());
        if(list.size() == 0){
            sql = "insert into role (rolename,rolestate) values (?,?)";

            //给参数赋值需要与sql语句中字段名一一对应
            int num= JDBCUtil.executeUpdate(sql,role.getRolename(),role.isRolestate());
            return num;
        }else{
            throw new RoleException("该角色已被注册！！");
        }
    }
    /**
     * 根据rolename查询数据
     * 数据回显
     * @param rolename
     * @return
     */
    public List<Role> selectByRolename(String rolename){
        String sql = "select * from role where rolename = ?";
        List<Role> list = JDBCUtil.executeQuery(sql, new RoleMapper(), rolename);
        return list;
    }
    /**
     * 修改数据的方法
     * @param role
     */
    public int updateRole(Role role){
        String sql = "update role set rolename=?,rolestate= ? where roleid=?";
        int num = JDBCUtil.executeUpdate(sql, role.getRolename(),role.isRolestate(),role.getRoleid());
        return num;
    }

    /**
     *权限显示功能
     * @param roleid
     * @return
     */
    public List<FunRole> selectFunRole(int roleid){
        String sql = "select a.funid,a.funname,a.funurl,a.funpid,a.funstate,(case when b.rrid is null then '0' else '1' end) as rr from function a "
                + "left outer join premission b "
                + "on a.funid = b.funid and b.roleid = ? "
                + "where a.funstate = 1";
        List<FunRole> list = JDBCUtil.executeQuery(sql, new FunRoleMapper(), roleid);
        return list;
    }
}
