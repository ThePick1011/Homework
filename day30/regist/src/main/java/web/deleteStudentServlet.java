package web;

import dao.StudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteStudent")
public class deleteStudentServlet extends HttpServlet {
    StudentDao studentDao = new StudentDao();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("sid");
        System.out.println(Integer.parseInt(sid));
        studentDao.delete(Integer.parseInt(sid));
        resp.sendRedirect("/findStudent");
    }
}
