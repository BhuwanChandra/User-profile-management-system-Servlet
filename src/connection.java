
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 
public class connection {
     protected static Connection initializeDatabase() 
        throws SQLException, ClassNotFoundException 
    { 
    	String url = "jdbc:mysql://localhost:3306/hello?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
 		String uname = "root";
 		String pass = "";
 		
 		Class.forName("com.mysql.cj.jdbc.Driver");
 		Connection con = DriverManager.getConnection(url,uname,pass);
 		return con; 
    }
    
}
