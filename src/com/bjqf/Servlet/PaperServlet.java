package com.bjqf.Servlet;

import com.bjqf.entity.Paper;
import com.bjqf.entity.Subject;
import com.bjqf.exception.PaperException;
import com.bjqf.service.PaperService;
import com.bjqf.util.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/PaperServlet")
public class PaperServlet extends HttpServlet {
    //创建service层对象
    PaperService paperService = new PaperService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置字符编码
        req.setCharacterEncoding("UTF-8");
        String obj = req.getParameter("obj");
        System.out.println(obj);
        if(obj.equals("selectAll")){
            selectAll(req,resp);
        }else if (obj.equals("addPaper")){
            addPaper(req,resp);
        }else if (obj.equals("selectSub")){
            selectSub(req,resp);
        }else if (obj.equals("studentPaper")){
            studentPaper(req,resp);
        }
    }

    /**
     * 开始答题试题展示
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void studentPaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取pname以及count
        String pname = request.getParameter("pname");
        int count = Integer.parseInt(request.getParameter("count"));

        System.out.println(pname);
        System.out.println(count);
        //调用Service层方法
        List<Subject> list = paperService.selectSub(pname);
        //将数据存储在request中
        request.setAttribute("list", list);
        request.setAttribute("pname", pname);
        request.setAttribute("count", count);
        //跳转界面
        request.getRequestDispatcher("user/paper/paper.jsp").forward(request, response);
    }

    /**
     * 查看试题方法
     * @param req
     * @param resp
     */
    private void selectSub(HttpServletRequest req, HttpServletResponse resp)throws IOException,ServletException {
        //获取pname的值
        String pname = req.getParameter("pname");
        System.out.println(pname);
        List<Subject> list = paperService.selectSub(pname);
        //将数据存储在request中
        req.setAttribute("list",list);
        req.getRequestDispatcher("sys/paper/subjects.jsp").forward(req,resp);

    }

    /**
     * 添加试卷的方法
     * @param req
     * @param resp
     */
    private void addPaper(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        //获取界面曾数据
        String pname = req.getParameter("pname");
        int number = Integer.parseInt(req.getParameter("scount"));
        //创建session对象
        HttpSession session = req.getSession();
        try{
            //调用service层方法
            paperService.addPaper(pname,number);
            //清除session中的数据
            session.removeAttribute("error2");
            //跳转界面
            req.getRequestDispatcher("PaperServlet?obj=selectAll&pageNo=1").forward(req,resp);
        } catch (PaperException e) {
            //将异常信息存储到session中
            session.setAttribute("error2",e.getMessage());
            //跳转界面
            resp.sendRedirect("sys/paper/add.jsp");
        }
    }

    /**
     * 查询所有数据的方法
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取界面层传来的pageNo数据
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        //设置pageSize的值
        int pageSize = 3;
        //调用service层方法
        PageModel pageModel = paperService.queryByPage(pageNo, pageSize);
        //将list集合存储在request中
        request.setAttribute("pageModel", pageModel);
        //界面跳转，跳转到sys/paper/list.jsp
        request.getRequestDispatcher("sys/paper/list.jsp").forward(request, response);
    }
}
