package com.bjqf.Servlet;

import com.bjqf.entity.FunRole;
import com.bjqf.entity.Role;
import com.bjqf.exception.RoleException;
import com.bjqf.exception.UserException;
import com.bjqf.service.RoleService;
import com.bjqf.util.PageModel;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/RoleServlet")
public class RoleServlet extends HttpServlet {
    //创建RoleService对象
    RoleService roleService = new RoleService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置字符编码
        req.setCharacterEncoding("UTF-8");
        //获取隐藏变量
        String obj = req.getParameter("obj");
        System.out.println(obj);
        if (obj.equals("selectAll")){
            selectAll(req,resp);
        }else if (obj.equals("addRole")){
            addRole(req,resp);
        }else if (obj.equals("updateRole")){
            updateRole(req,resp);
        }else if (obj.equals("selectByRolename")){
            selectByRolename(req,resp);
        }else if(obj.equals("selectFunRole")){
            selectFunRole(req,resp);
        }
    }
    private void selectFunRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roleid = Integer.parseInt(request.getParameter("roleid"));
        System.out.println(roleid);
        List<FunRole> list = roleService.selectFunRole(roleid);
        request.setAttribute("list", list);
        request.getRequestDispatcher("sys/role/right.jsp").forward(request, response);
    }
    /**
     * 分页查询所有
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void selectAll(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        //设置每一页显示2跳数据
        int pageSize = 3;
        //调用Service层方法
        PageModel pageModel = roleService.queryByPage(pageNo, pageSize);
        /*
         * 将数据存储在request域中
         * 参数1：表示给存储在request中的数据起一个别名
         * 参数2：表示存储在request中的数据
         */
        req.setAttribute("pageModel", pageModel);
        //跳转界面
        req.getRequestDispatcher("sys/role/list.jsp").forward(req, resp);
    }

    /**
     * 添加角色
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addRole(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        //从界面获取数据

        String rolename = req.getParameter("rolename");
        boolean rolestate = Boolean.parseBoolean(req.getParameter("rolestate"));
        //创建Role对象
        Role role =new Role();
        //赋值
        role.setRolename(rolename);
        role.setRolestate(rolestate);

        try {
            roleService.addRole(role);
            //重定向
            resp.sendRedirect("RoleServlet?obj=selectAll&pageNo=1");
        } catch (RoleException e) {
            //将错误信息保存在session中
            HttpSession session = req.getSession(true);
            session.setAttribute("error_add_role", e.getMessage());
            //跳转添加界面
            resp.sendRedirect("sys/role/add.jsp");
        }
    }

    /**
     * 数据回显
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void selectByRolename(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        //获取界面rolename
        String rolename = req.getParameter("rolename");
        List<Role> list=  roleService.selectRolename(rolename);
        System.out.println(list.get(0).getRolename());
        //存储
        req.setAttribute("role",list.get(0));
        //跳转
        req.getRequestDispatcher("sys/role/edit.jsp").forward(req,resp);

    }

    /**
     * 修改角色
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void updateRole(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        //获取
        int roleid = Integer.parseInt(req.getParameter("roleid"));
        String rolename = req.getParameter("rolename");
        boolean rolestate = Boolean.parseBoolean(req.getParameter("rolestate"));
        System.out.println(rolename);
        System.out.println(roleid);
        System.out.println(rolestate);
        //创建对象
        Role role = new Role();
        role.setRoleid(roleid);
        role.setRolename(rolename);
        role.setRolestate(rolestate);

        HttpSession session = req.getSession(true);
        try {
            roleService.updateRole(role);
            //清除session
            req.removeAttribute("update_role_error");
            //添加成功，跳转到显示界面并显示第一页数据
            resp.sendRedirect("RoleServlet?obj=selectAll&pageNo=1");
        } catch (RoleException e) {
            //如果添加失败，跳转到修改界面并给出错误提示
            session.setAttribute("update_role_error", e.getMessage());
            resp.sendRedirect("RoleServlet?obj=selectByRolename&rolename="+rolename);
        }
    }
}
