import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/servlet1")
public class Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        SQLInsert.insertIntoDatabase(name,username,password);
        System.out.println("name: " + name);
        System.out.println("username: " + username);
        System.out.println("password: " + password);

    }
}



