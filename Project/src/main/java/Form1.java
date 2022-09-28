import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Form1")
public class Form1 extends jakarta.servlet.http.HttpServlet implements jakarta.servlet.Servlet {
	static final long serialVersionUID = 1L;

	public Form1() {
		super();
	}

	private String m_CourseID;
	private String m_Semester;
	private Connection con =null;
	private String user = "root";
	private String password = "root";
	private String driver ="com.mysql.cj.jdbc.Driver";
	private String table = "Courses";
	PrintWriter out ;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String CourseIDtxt = request.getParameter("CourseIDtxt");
		String Semestertxt = request.getParameter("Semestertxt");
		System.out.println(Semestertxt);
		try {
			
			if(Connect() == true)
			{
				System.out.println("Hit 1");
		      Calendar calendar = Calendar.getInstance();
		      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

		      // the mysql insert statement
		      String query = " insert into people (firstname, lastname)"
		        + " values (?, ?)";

		      // create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = con.prepareStatement(query);
		      preparedStmt.setString (1, firstname);
		      preparedStmt.setString (2, lastname);
		     // preparedStmt.setDate   (3, startDate);
		      //preparedStmt.setBoolean(4, false);
		     // preparedStmt.setInt    (5, 5000);

		      // execute the preparedstatement
		      preparedStmt.execute();
			con.close();
			
			response.setContentType("text/html");
			out = response.getWriter();
			Success();
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}
	
	public  void Success()
	{
		out.println("<html>\n" + "<head><title>" + "Courses" + "</title>\n"
				+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n"
				+ "<style>frm_back:hover{	box-shadow: none;background-color:  #4BA6FF !important;}</style>"
				+ "</head>\n"
				+ "<body bgcolor=\"#BED7C0\" style=\"font-family: 'Open Sans', sans-serif; color: #E6E6E6\">\n"
				+ "<div class=\"div1\" style=\"margin: auto;width: 419px;padding: 10px;background-color: #297373;border-radius: 6px;\">"
				+ "<div style=\"align-content: space-evenly; margin: auto; width: 35%;\">"
				+ "<i class=\"fa fa-database\" style=\"font-size: 150px;\"></i>"
				+ "</div>"	
				+ "<h1 align=\"center\">" +"List of courses.</h1><br>");
		out.println("<table style=\"width: 100%;\">");
		
	}

	public boolean Connect() throws Exception
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/NJIT?user=root&password=root");	
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		
		
	}
	
	public boolean tableExistsSQL(String tableName) throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/NJIT?user="+user+"&password="+password);
	    PreparedStatement preparedStatement = con.prepareStatement("SELECT count(*) "
	      + "FROM information_schema.tables "
	      + "WHERE table_name = ?"
	      + "LIMIT 1;");
	    preparedStatement.setString(1, tableName);

	    ResultSet resultSet = preparedStatement.executeQuery();
	    resultSet.next();
	    return resultSet.getInt(1) != 0;
	}
	
	public void CreateDatabase() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{

		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/NJIT?user="+user+"&password="+password);
		
			Statement stmt = con.createStatement();

		    stmt.execute("CREATE TABLE IF NOT EXISTS Courses( courseID char(5), Semester char(50), Title char(255))");
		    stmt.execute("Insert into courses values('CS670', 'Fall2021', 'Artificial Intelligence')");
		    stmt.execute("Insert into courses values('CS677', 'Fall2021', 'Deep Learning')");
		    stmt.execute("Insert into courses values('CS675', 'Spring2022', 'Machine Learning')");
		    stmt.execute("Insert into courses values('CS680', 'Spring2022', 'Linux Programming')");
		    con.close();
			
		} catch (SQLException e) {
			System.out.println(e);
			
		}
	}


}

