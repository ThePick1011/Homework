package web;

import dao.StudentDao;
import domain.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/selectStudent")
public class selectStudentByIdServlet extends HttpServlet {
    StudentDao studentDao = new StudentDao();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("sid");
        System.out.println(sid);
        Student student = studentDao.findById(Integer.parseInt(sid));
        System.out.println(student);
        req.setAttribute("student",student);
        req.getRequestDispatcher("updateStudent.jsp").forward(req, resp);
    }
}
