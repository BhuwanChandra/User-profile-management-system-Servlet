import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

@SuppressWarnings("serial")
public class Login extends HttpServlet {
 
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        try{
        PrintWriter out = res.getWriter();
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        
        
            Connection con = connection.initializeDatabase();
             PreparedStatement st = con 
                   .prepareStatement("select * from login where email=? and pass=?"); 
             st.setString(1, email);
             st.setString(2, pass);
             ResultSet k = st.executeQuery();
             HttpSession ses = req.getSession();
             
             if(k.next()){
	             String username = k.getString("name");
	             String useremail = k.getString("email");
//	             out.println(username);
//	             out.println(useremail);
//	             
	             ses.setAttribute("username", username);
	             ses.setAttribute("useremail", useremail);

	             con.close();
	             
	             RequestDispatcher rs = req.getRequestDispatcher("welcome.html");
	                rs.forward(req, res);
             } else
             {
               
                RequestDispatcher rs = req.getRequestDispatcher("index.html");
                 out.println("<p stytle=\"color:red;font-size:24px;\">Username or Password incorrect</p>");
                rs.include( req, res);
             }
             
        }
        catch (Exception ex) {
        	RequestDispatcher rs = req.getRequestDispatcher("index.html");
           rs.include( req, res);
        }        
    }
   
    }

