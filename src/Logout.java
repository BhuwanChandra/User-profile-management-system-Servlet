import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Logout extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        HttpSession ses =  req.getSession();
        ses.invalidate();
        RequestDispatcher rs = req.getRequestDispatcher("signup.html");
        out.println("<h1>Successfully logged out!</h1>");
        rs.include( req, res);
    }

}
