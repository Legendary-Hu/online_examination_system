package com.bjqf.Servlet;

import com.bjqf.entity.Function;
import com.bjqf.exception.FunctionException;
import com.bjqf.service.FunctionService;
import com.bjqf.util.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/FunctionServlet")
public class FunctionServlet extends HttpServlet {
    FunctionService functionService = new FunctionService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("UTF-8");
        String obj = req.getParameter("obj");
        System.out.println(obj);
        if (obj.equals("selectAll")){
            selectAll(req,resp);
        }else if (obj.equals("toAdd")){
            toAdd(req,resp);
        }else if(obj.equals("addFun")){
            addFun(req,resp);
        }else if(obj.equals("selectByFunid")){
            selectByFunid(req,resp);
        }else if(obj.equals("updateFunction")){
            updateFunction(req,resp);
        }

    }

    /**
     * 修改功能信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void updateFunction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int funid = Integer.parseInt(request.getParameter("funid"));
        String funname = request.getParameter("funname");
        String funurl = request.getParameter("funurl");
        boolean funstate = Boolean.parseBoolean(request.getParameter("funstate"));
        Function function = new Function();
        function.setFunid(funid);
        function.setFunname(funname);
        function.setFunurl(funurl);
        function.setFunstate(funstate);
        try {
            functionService.updateFunction(function);
            request.getRequestDispatcher("FunctionServlet?obj=selectAll&pageNo=1").forward(request, response);
        } catch (FunctionException e) {
            request.setAttribute("editfun_error", e.getMessage());
            request.getRequestDispatcher("FunctionServlet?obj=selectByFunid&funid="+funid).forward(request, response);
        }
    }


    private void selectByFunid(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        int funid = Integer.parseInt(req.getParameter("funid"));
        List<Function> list = functionService.selectByFunid(funid);
        System.out.println(list.get(0).getFunpname());
         req.setAttribute("function",list.get(0));
         req.getRequestDispatcher("sys/function/edit.jsp").forward(req,resp);

    }

    /**
     * 传值跳转添加页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        //获取list页面中的值
        int funpid = Integer.parseInt(req.getParameter("funpid"));
        String funpname = req.getParameter("funpname");
        //保存到request中，用于在add界面回显
        req.setAttribute("funpname", funpname);
        req.setAttribute("funpid", funpid);
        req.getRequestDispatcher("sys/function/add.jsp").forward(req, resp);
    }

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = 3;
        PageModel pageModel = functionService.queryByPage(pageNo, pageSize);
        request.setAttribute("pageModel", pageModel);
        request.getRequestDispatcher("sys/function/list.jsp").forward(request, response);
    }

    /**
     * 添加功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addFun(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        int funpid = Integer.parseInt(req.getParameter("funpid"));
        String funpname = req.getParameter("funpname");
        String funname = req.getParameter("funname");
        String funurl = req.getParameter("funurl");

        System.out.println(funpid);
        boolean funstate = Boolean.parseBoolean(req.getParameter("funstate"));
        Function function = new Function();
        function.setFunname(funname);
        function.setFunpid(funpid);
        function.setFunurl(funurl);
        function.setFunstate(funstate);

        try {
            functionService.addFunction(function);
            req.getRequestDispatcher("FunctionServlet?obj=selectAll&pageNo=1").forward(req, resp);
        } catch (FunctionException e) {
            req.setAttribute("addfun_error", e.getMessage());
            req.getRequestDispatcher("FunctionServlet?obj=toAdd&funpid="+funpid+"&funpname="+funpname).forward(req, resp);
        }
    }


}
