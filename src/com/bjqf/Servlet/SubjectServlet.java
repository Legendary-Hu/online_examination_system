package com.bjqf.Servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjqf.entity.Subject;
import com.bjqf.exception.SubjectException;
import com.bjqf.service.SubjectService;
import com.bjqf.util.PageModel;

@WebServlet("/SubjectServlet")
public class SubjectServlet extends HttpServlet{
    //创建service层对象
    SubjectService subjectService = new SubjectService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置字符编码
        req.setCharacterEncoding("UTF-8");
        //获取隐藏变量
        String obj = req.getParameter("obj");
        if(obj.equals("selectAll")){
            selectAll(req,resp);
        }else if (obj.equals("addSubject")){
            addSubject(req,resp);
        }else if (obj.equals("selectBySid")){
            selectBySid(req,resp);
        }else if (obj.equals("updateSubject")){
            updateSubject(req,resp);
        }
    }




    /**
     * 查询所有数据的方法
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
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
        PageModel pageModel = subjectService.queryByPage(pageNo, pageSize);
        /*
         * 将数据存储在request域中
         * 参数1：表示给存储在request中的数据起一个别名
         * 参数2：表示存储在request中的数据
         */
        req.setAttribute("pageModel", pageModel);
        //跳转界面
        req.getRequestDispatcher("sys/subject/list.jsp").forward(req, resp);
    }

    /**
     * 添加题目的方法
     * @param request
     * @param response
     * @throws IOException
     */
    private void addSubject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取界面的值
        String scontent = request.getParameter("scontent");
        String sa = request.getParameter("sa");
        String sb = request.getParameter("sb");
        String sc = request.getParameter("sc");
        String sd = request.getParameter("sd");
        String skey = request.getParameter("skey");
        String sstate = request.getParameter("sstate");
        /*
         * 由于sstate用的是下拉列表，所以获取的值是下拉列表的value值
         * 但是下拉列表的value值为0或1，所以需要将sstate的数据类型以及数据值进行转换
         */
        boolean state = true;
        if(sstate.equals("1")){
            state = true;
        }else if(sstate.equals("0")){
            state = false;
        }
        //创建Subject对象，并给对象赋值
        Subject subject = new Subject();
        subject.setScontent(scontent);
        subject.setSa(sa);
        subject.setSb(sb);
        subject.setSc(sc);
        subject.setSd(sd);
        subject.setSkey(skey);
        subject.setSstate(state);
        //调用service层方法
        try {
            subjectService.addSubject(subject);
            //跳转界面
            response.sendRedirect("SubjectServlet?obj=selectAll&pageNo=1");
        } catch (SubjectException e) {
            //将错误信息保存在session中
            HttpSession session = request.getSession(true);
            session.setAttribute("subject_add_error", e.getMessage());
            //跳转添加界面
            response.sendRedirect("sys/subject/add.jsp");
        }
    }
    /**
     * 数据回显的方法
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void selectBySid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取sid
        int sid = Integer.parseInt(request.getParameter("sid"));
        /*
         * 调用service层方法
         * list集合中有且只有一条数据
         */
        List<Subject> list = subjectService.selectBySid(sid);
        //将list集合中的subject对象存储在request中
        request.setAttribute("subject", list.get(0));
        //界面跳转
        request.getRequestDispatcher("sys/subject/edit.jsp").forward(request, response);
    }
    /**
     * 数据修改的方法
     * @param request
     * @param response
     * @throws IOException
     */
    private void updateSubject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取界面数据
        int sid = Integer.parseInt(request.getParameter("sid"));
        String scontent = request.getParameter("scontent");
        String sa = request.getParameter("sa");
        String sb = request.getParameter("sb");
        String sc = request.getParameter("sc");
        String sd = request.getParameter("sd");
        String skey = request.getParameter("skey");
        boolean sstate = Boolean.parseBoolean(request.getParameter("sstate"));

        //创建对象并给对象赋值
        Subject subject = new Subject();
        subject.setSid(sid);
        subject.setScontent(scontent);
        subject.setSa(sa);
        subject.setSb(sb);
        subject.setSc(sc);
        subject.setSd(sd);
        subject.setSkey(skey);
        subject.setSstate(sstate);
        HttpSession session = request.getSession(true);
        //调用service层方法
        try {
            subjectService.updateSubject(subject);
            //清除session中数据
            session.removeAttribute("error_subadd");
            //添加成功，跳转到显示界面并显示第一页数据
            response.sendRedirect("SubjectServlet?obj=selectAll&pageNo=1");
        } catch (SubjectException e) {
            //如果添加失败，跳转到修改界面并给出错误提示
            session.setAttribute("error_subadd", e.getMessage());
            response.sendRedirect("SubjectServlet?obj=selectBySid&sid="+sid);
        }
    }
}