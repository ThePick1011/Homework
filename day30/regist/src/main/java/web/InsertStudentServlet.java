package web;

import dao.StudentDao;
import domain.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/insertStudent")
public class InsertStudentServlet extends HttpServlet {
    StudentDao studentDao = new StudentDao();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String birthday = req.getParameter("birthday");
        String sex = req.getParameter("sex");
        System.out.println(birthday);
        System.out.println(sex);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(birthday);
            studentDao.insert(new Student(name, date, sex));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/findStudent");
    }
}
