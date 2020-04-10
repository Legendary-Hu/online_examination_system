package com.bjqf.test;

import com.bjqf.dao.*;
import com.bjqf.entity.*;
import com.bjqf.exception.PaperException;
import com.bjqf.exception.SubjectException;
import com.bjqf.exception.UserException;
import com.bjqf.mapper.RoleMapper;
import com.bjqf.service.PaperService;
import com.bjqf.service.UserService;
import com.bjqf.util.JDBCUtil;

import java.util.List;

public class Test {
    public static void main(String args[]){
//        UserDao userDao = new UserDao();
//        List<User> list= userDao.selectByUserid(1);
//        System.out.println(list.get(0).getUsername());
//        PaperService paperService = new PaperService();
//        UserService userService =new UserService();
//        PaperDao paperDao =new PaperDao();
//        RoleDao roleDao = new RoleDao();
//        User user = new User();
//        user.setUserid(6);
//        user.setUsername("hahah");
//        user.setRolename("小老板");
//        user.setRoleid(5);
//        user.setUserpwd("123456");
//        user.setUsertruename("huxiang");
//        user.setRolestate(true);
//        try {
//            userService.addUser(user);
//        } catch (UserException e) {
//            e.printStackTrace();
//        }
//        List<User> list = userService.selectByUsername("钟南山");
////        System.out.println(list.get(0).isUserstate());
//


//        StudentPaperDao stu = new StudentPaperDao();
//        StudentPaper s = new StudentPaper();
//        s.setSpid("20192151100001");
//        s.setUserid(3);
//        s.setSid(1);
//        s.setStudentkey("A");
//        s.setStudentstate("1");
//        s.setPname("奥林匹克数学");
//        stu.addStudentPaper(s);
//        StudentPaperDao stu = new StudentPaperDao();
//        int count = stu.score(3, "1581754684144");
//        System.out.println(count);
//       FunctionDao functionDao = new FunctionDao();
        RoleDao roleDao = new RoleDao();
       List<FunRole> list=roleDao.selectFunRole(1);
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i).getFunpid());
        }

    }
}
