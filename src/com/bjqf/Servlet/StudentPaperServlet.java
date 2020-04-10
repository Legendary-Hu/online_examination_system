package com.bjqf.Servlet;

import com.bjqf.entity.StudentList;
import com.bjqf.entity.StudentPaper;
import com.bjqf.service.PaperErrorService;
import com.bjqf.service.StudentPaperService;
import com.bjqf.util.PageModel;
import org.apache.commons.beanutils.BeanUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet("/StudentPaperServlet")
public class StudentPaperServlet extends HttpServlet {
    StudentPaperService studentPaperService = new StudentPaperService();
    PaperErrorService paperErrorService = new PaperErrorService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置字符编码
        req.setCharacterEncoding("UTF-8");
        //获取obj的值
        String obj = req.getParameter("obj");
        System.out.println(obj);
        //根据obj的值判断执行哪一个方法
        if(obj.equals("addStudentPaper")){
            addStudentPaper(req,resp);
        }else if (obj.equals("score")){
            score(req,resp);
        }else if (obj.equals("studentList")){
            studentList(req,resp);
        }else if (obj.equals("selectAll")){
            selectAll(req,resp);
        }

    }
    /**
     * 查看错题分页方法
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取界面值
        int userid = Integer.parseInt(request.getParameter("userid"));
        String spid = request.getParameter("spid");
        String pname = request.getParameter("pname");
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = 3;
        //调用Service层方法
        PageModel pageModel = paperErrorService.queryByPage(userid, spid, pageNo, pageSize);
        //将数据存储在request中
        request.setAttribute("pname", pname);
        request.setAttribute("spid", spid);
        request.setAttribute("pageModel", pageModel);
        //跳转界面
        request.getRequestDispatcher("user/paper/studenterror.jsp").forward(request, response);
    }
    /**
     * 查看学生答题列表的方法
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void studentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取学生的userid
        int userid = Integer.parseInt(request.getParameter("userid"));
        //调用service层方法
        List<StudentList> list = studentPaperService.studentList(userid);
        //将值存储在request中
        request.setAttribute("list", list);
        //跳转界面
        request.getRequestDispatcher("user/paper/studentpaper.jsp").forward(request, response);
    }

    private void score(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        //获取值
        int userid = Integer.parseInt(req.getParameter("userid"));
        String spid = req.getParameter("spid");
        //调用service层方法，获取答对题目数量
        int count = studentPaperService.score(userid,spid);
        //显示分数
        PrintWriter out = resp.getWriter();
        //打印分数
        out.print("score:"+count*2);
        out.flush();
        out.close();
    }

    private void addStudentPaper(HttpServletRequest req, HttpServletResponse resp) {
        try {
            System.out.println("执行了");
            //创建对象
            StudentPaper studentPaper = new StudentPaper();
            /*
             * 使用beanutils jar包中的方法获取剩下的值
             * 创建一个空对象，使用BeanUtils.populate将AJAX中存储的值赋值给该对象
             */
            BeanUtils.populate(studentPaper, req.getParameterMap());
            System.out.println(studentPaper.getSpid()+"\t"+studentPaper.getUserid()+"\t"+
                    studentPaper.getSid()+"\t"+studentPaper.getStudentkey()+"\t"+
                    studentPaper.getStudentstate()+"\t"+studentPaper.getPname());
            //将值添加到数据库
            studentPaperService.addStudentPaper(studentPaper);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
