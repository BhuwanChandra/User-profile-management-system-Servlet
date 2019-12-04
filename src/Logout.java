import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Logout extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        HttpSession ses =  req.getSession();
        ses.setAttribute("username", null);
        ses.setAttribute("useremail", null);
        ses.invalidate();
        RequestDispatcher rs = req.getRequestDispatcher("signup.html");
        rs.forward( req, res);
        out.println("<center><h3>Successfully logged out!<h3></center>");
    }

}
