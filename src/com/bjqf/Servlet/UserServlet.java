package com.bjqf.Servlet;



import com.bjqf.entity.Role;
import com.bjqf.entity.User;
import com.bjqf.exception.UserException;
import com.bjqf.service.PaperService;
import com.bjqf.service.UserService;
import com.bjqf.util.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    //创建service对象
    UserService userService  = new UserService();
    PaperService paperService = new PaperService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置字符编码
        req.setCharacterEncoding("UTF-8");
        //获取隐藏变量
        String obj = req.getParameter("obj");

        //根据obj的值判断执行哪一种方法
        if (obj.equals("login")){
            login(req,resp);
        }else if (obj.equals("logout")){
            logout(req,resp);
        }else if (obj.equals("selectAll")){
            selectAll(req,resp);
        }else if (obj.equals("addUser")){
            addUser(req,resp);
        }else if (obj.equals("selectByUsername")){
            selectByUsername(req,resp);
        }else if (obj.equals("updateUser")){
            updateUser(req,resp);
        }else if (obj.equals("selectRole")){
            selectRole(req,resp);
        }else if (obj.equals("pageLogin")){
            pageLogin(req,resp);
        }

    }

    private void pageLogin(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        //获取pageNo的值
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));

        //调用PaperService层中的queryByPage方法
        PageModel pageModel= paperService.queryByPage(pageNo, 3);
        //将pageModel存储在request中
        request.setAttribute("pageModel", pageModel);
        //界面跳转
        request.getRequestDispatcher("user/index.jsp").forward(request, response);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {

        //获取界面输入框的值
        String username = req.getParameter("username");
        String userpwd = req.getParameter("userpwd");

        /**
         *
         * try语句表示代码不存在异常时执行的代码
         * catch语句块代码表示代码出现异常时执行的代码
         */
        try{
            //调用servce层的方法
            List<User> list  = userService.login(username,userpwd);
            //获取list集合中的数据，并将获取到的数据存储在session中
            //获取session对象
            HttpSession session = req.getSession();
            //将list中的数据存储在session中
            session.setAttribute("user",list.get(0));
            //获取rolename值，根据rolename值判断跳转哪一个界面
            if (list.get(0).getRolename().equals("超级管理员")){
                resp.sendRedirect("index.jsp");
            }else if (list.get(0).getRolename().equals("学生")){
                pageLogin(req,resp);
            }
        } catch (UserException e) {
            /**
             * 如果代码块存在异常，需要将错误信息存储在request中
             * 然后跳转到当前界面，并且展示错误信息
             */
            req.setAttribute("error",e.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }


    }


    /**
     * 退出登录方法
     * @param request
     * @param response
     * @throws IOException
     */
    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //清除session中的数据
        //获取session对象
        HttpSession session = request.getSession();
        //清除session中的user对象
        session.removeAttribute("user");
        //跳转界面，跳转到login.jsp
        response.sendRedirect("login.jsp");
    }
    /**
     * 查询所有数据的方法
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取界面层除数过来的pageNo值
         * 由于request.getParameter()从界面层获的数据只能是String类型
         * 但是pageNo需要int类型
         * 所以在此处需要将String类型转换成int(将引用数据类型转换成基本数据类型)
         * 此处需要使用包装类
         */
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        //设置每一页显示2跳数据
        int pageSize = 3;
        //调用Service层方法
        PageModel pageModel = userService.queryByPage(pageNo, pageSize);
        /*
         * 将数据存储在request域中
         * 参数1：表示给存储在request中的数据起一个别名
         * 参数2：表示存储在request中的数据
         */
        req.setAttribute("pageModel", pageModel);
        //跳转界面
        req.getRequestDispatcher("sys/user/list.jsp").forward(req, resp);
    }

    /**
     * 添加题目的方法
     * @param request
     * @param response
     * @throws IOException
     */
    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取界面的值
        int roleid = Integer.parseInt(request.getParameter("roleid"));
        String username = request.getParameter("username");
        String userpwd = request.getParameter("userpwd");
        String usertruename = request.getParameter("usertruename");
        boolean userstate = Boolean.parseBoolean(request.getParameter("userstate"));
        System.out.println(userstate);
        /*
         * 由于sstate用的是下拉列表，所以获取的值是下拉列表的value值
         * 但是下拉列表的value值为0或1，所以需要将sstate的数据类型以及数据值进行转换
         */
//        boolean state = true;
//        if(sstate.equals("1")){
//            state = true;
//        }else if(sstate.equals("0")){
//            state = false;
//        }
        //创建User对象，并给对象赋值
        User user = new User();
        user.setRoleid(roleid);
        user.setUsername(username);
        user.setUserpwd(userpwd);
        user.setUsertruename(usertruename);
        user.setUserstate(userstate);
        //调用service层方法
        try {
            userService.addUser(user);
            //跳转界面
            response.sendRedirect("UserServlet?obj=selectAll&pageNo=1");
        } catch (UserException e) {
            //将错误信息保存在session中
            HttpSession session = request.getSession(true);
            session.setAttribute("error3", e.getMessage());
            //跳转添加界面
            response.sendRedirect("sys/user/add.jsp");
        }
    }
    /**
     * 查询role表中数据
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void selectRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service层方法
        List<Role> list = userService.queryRole();
        //将数据存储在request中
        request.setAttribute("Rolelist", list);
        //界面跳转
        request.getRequestDispatcher("sys/user/add.jsp").forward(request, response);;
    }
    /**
     * 数据回显的方法
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void selectByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取userid
        String username = request.getParameter("username");
//        System.out.println(username);
        /*
         * 调用service层方法
         * list集合中有且只有一条数据
         */
        List<User> list = userService.selectByUsername(username);
        List<Role> rolelist = userService.queryRole();

//        for (int i = 0; i < rolelist.size(); i++) {
//            System.out.println(rolelist.get(i).getRolename()+rolelist.get(i).getRoleid());
//        }//

        //将list集合中的user对象存储在request中
        request.setAttribute("user", list.get(0));
        request.setAttribute("Rolelist",rolelist);
        //界面跳转
        request.getRequestDispatcher("sys/user/edit.jsp").forward(request, response);
    }
    /**
     * 数据修改的方法
     * @param request
     * @param response
     * @throws IOException
     */
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取界面数据
        int userid = Integer.parseInt(request.getParameter("userid"));
        int roleid = Integer.parseInt(request.getParameter("roleid"));
        String username = request.getParameter("username");
        String userpwd = request.getParameter("userpwd");
        String usertruename = request.getParameter("usertruename");
        boolean userstate = Boolean.parseBoolean(request.getParameter("userstate"));
        System.out.println(userstate);
        //创建对象并给对象赋值
        User user = new User();
        user.setUserid(userid);
        user.setRoleid(roleid);
        user.setUsername(username);
        user.setUserpwd(userpwd);
        user.setUsertruename(usertruename);
        user.setUserstate(userstate);

        HttpSession session = request.getSession(true);
        //调用service层方法
        try {
            userService.updateUser(user);
            //清除session中数据
            session.removeAttribute("error4");
            //添加成功，跳转到显示界面并显示第一页数据
            response.sendRedirect("UserServlet?obj=selectAll&pageNo=1");
        } catch (UserException e) {
            //如果添加失败，跳转到修改界面并给出错误提示
            session.setAttribute("error4", e.getMessage());
            response.sendRedirect("UserServlet?obj=selectByUsername&username="+username);
        }
    }
}
