import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Project/JDBC")
public class JDBC extends jakarta.servlet.
  http.HttpServlet implements jakarta.servlet.Servlet {
  static final long serialVersionUID = 1L;
   
  public JDBC () {
    super();
  }   	
	
  protected void doGet(HttpServletRequest request, 
    HttpServletResponse response)throws ServletException, IOException {

    String name = request.getParameter("name");
    try {
	Class.forName("com.mysql.jdbc.Driver").newInstance();

	Connection con = DriverManager.getConnection(
	  "jdbc:mysql://localhost:3306/njit?user=root&password=root");
	Statement stmt = con.createStatement();
	try{
	  //Sometimes you may decide to remove the table.
	  //In this case uncomment the following line.
	  //stmt.execute("DROP TABLE simple"); 
	  stmt.execute("CREATE TABLE IF NOT EXISTS simple( name char(30))");
	}catch(Exception e){
	}
	//Note that the variable is not part of the string.
	String s = "Insert into simple values(\'" + name + "\')";
	if(name != null)
	  stmt.execute(s);
	ResultSet rs = stmt.executeQuery("Select * from simple");
	while ( rs.next())
	  System.out.println(rs.getString(1));
    } catch (Exception ex) {
	System.out.println(ex);
	System.exit(0);
    }
    System.out.println("Program terminated with no error.");
  }  	
	
  protected void doPost(HttpServletRequest request, 
    HttpServletResponse response)throws ServletException, IOException {
    // TODO Auto-generated method stub
  }   	  	    
}
