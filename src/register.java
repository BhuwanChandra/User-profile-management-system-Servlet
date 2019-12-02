
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;


public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res){
        res.setContentType("text/html");
        try {
        	Connection con = connection.initializeDatabase();
        	String pass = req.getParameter("pass");
        	if(pass == null) {
				HttpSession ses = req.getSession();
				String n = (String) ses.getAttribute("username");
				String p = (String) ses.getAttribute("useremail");
	        	
        		PrintWriter out = res.getWriter();
    	        String name = req.getParameter("name");
    	        String email = req.getParameter("email");
    	        String phone = req.getParameter("phone");
    	        String age = req.getParameter("age");
    	        String city = req.getParameter("city");
    	        String country = req.getParameter("country");
    	        
    	        String qy = "update `login` set ";
    	        System.out.println(qy);
    	        System.out.println(name+email+phone+age+city+country);
    	        int c = 1;
    	        if(name != null) qy += "`name`=? ";
    	        if(email != null) qy += ",`email`=? ";
    	        if(phone != null) qy += ",`phone_no`=? ";
    	        if(age != null) qy += ",`age`=? ";
    	        if(city != null) qy += ",`city`=? ";
				if(country != null) qy += ",`country`=? ";
				
				qy += "where name=? and email=?";
				System.out.println(qy);
				PreparedStatement ps = con.prepareStatement(qy);
				if(name != null) {ps.setString(c, name);c++;}
    	        if(email != null){ps.setString(c, email);c++;}
    	        if(phone != null){ps.setString(c, phone);c++;}
    	        if(age != null){ps.setString(c, age);c++;}
    	        if(city != null) {ps.setString(c, city);c++;}
				if(country != null) {ps.setString(c, country);c++;}
				ps.setString(c, n);
				c++;
				ps.setString(c, p);
    	        ps.executeUpdate();
    	        
    	        out.println("<h3>information saved!!!</h3>");
    	        
        	}else {
		        PrintWriter out = res.getWriter();
		        String name = req.getParameter("name");
		        String email = req.getParameter("email");
		        
		        
		        PreparedStatement ps = con.prepareStatement("insert into login (name, email , pass) values (?,?,?)");
		        ps.setString(1, name);
		        ps.setString(2, email);
		        ps.setString(3, pass);
		        int k = ps.executeUpdate();
		        
		        if(k != 0) {
		        	RequestDispatcher rs = req.getRequestDispatcher("signup.html");
		        	out.println("<center>Registered Successfully!!<center>");
		            rs.include( req, res);
		        }else {
		        	RequestDispatcher rs = req.getRequestDispatcher("signup.html");
		        	out.println("<center>Something went wrong!!<center>");
		            rs.include( req, res);
		        }
        	}
	        con.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        try {
        Connection con = connection.initializeDatabase();
        PreparedStatement ps = con.prepareStatement("select * from login where email=? and name=?");
        HttpSession ses = req.getSession();
        if(ses != null) {
        	String name = (String) ses.getAttribute("username");
        	String email = (String) ses.getAttribute("useremail");
        	ps.setString(1, email);
            ps.setString(2, name);
            ResultSet k = ps.executeQuery();
            if( k.next()){
             String username = k.getString("name");
             String useremail = k.getString("email");
             String userphone = k.getString("phone_no");
             String userage = k.getString("age");
             String usercity = k.getString("city");
             String usercountry = k.getString("country");
             out.println(username + "~" + useremail + "~" + userphone + "~" + userage + "~" + usercity + "~" + usercountry); 
            }
            con.close();
        }else {
        	out.println("<script>alert('please login first!');</script>");
        }
        }catch(Exception e) {
        	System.out.println(e);
        }
	}
}
