import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Main3")
public class Main3 extends jakarta.servlet.http.HttpServlet implements jakarta.servlet.Servlet {
	static final long serialVersionUID = 1L;

	public Main3() {
		super();
	}

	private String m_CourseID;
	private String m_Semester;
	private Connection con =null;
	private String user = "root";
	private String password = "root";
	private String driver ="com.mysql.cj.jdbc.Driver";
	private String table = "Courses";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String radio = request.getParameter("rd");
		String name = request.getParameter("Semester");
		String CourseIDtxt = request.getParameter("CourseIDtxt");
		String Semestertxt = request.getParameter("Semestertxt");
		System.out.println(Semestertxt);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/NJIT?user=root&password=root");	
			//This is a method to check if the table exist else then it will create and insert the values.
			/*if(tableExistsSQL(table) != true)
			{
				CreateDatabase();
			}*/
			System.out.println("0");
			Statement stmt = con.createStatement();
	
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();


			if (radio.equals("0")) {
				if (!name.equals("select semester")) {

					String sql = "select * from Courses where Semester= ?";
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, name);

					ResultSet rs = statement.executeQuery();

					out.println("<html>\n" + "<head><title>" + "Courses" + "</title>\n"
							+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n"
							+ "<style>frm_back:hover{	box-shadow: none;background-color:  #4BA6FF !important;}</style>"
							+ "</head>\n"
							+ "<body bgcolor=\"#BED7C0\" style=\"font-family: 'Open Sans', sans-serif; color: #E6E6E6\">\n"
							+ "<div class=\"div1\" style=\"margin: auto;width: 419px;padding: 10px;background-color: #297373;border-radius: 6px;\">"
							+ "<div style=\"align-content: space-evenly; margin: auto; width: 35%;\">"
							+ "<i class=\"fa fa-database\" style=\"font-size: 150px;\"></i>"
							+ "</div>"	
							+ "<h1 align=\"center\">" +"List of "+ name +" courses.</h1><br>");
					out.println("<table style=\"width: 100%;\">");
					while (rs.next()) {
						out.println( "<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td><td>"
								+ rs.getString(3) + "</td></tr>");
					}

					out.println("</table>");
				} else {

					try {

						String sql = "select * from Courses where Semester= ?";
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setString(1, name);

						ResultSet rs = statement.executeQuery();
						if (rs.next() == false) {

								rs.getString(0);
						} 
							
						con.close();
					} catch (SQLException e) {
						request.setAttribute("message", "<i class=\"fa fa-warning\"></i>ERROR: " + e); 

						request.getRequestDispatcher("Main.jsp").forward(request, response);
					}

				}
			} else {
				if (Semestertxt.equals("") || CourseIDtxt.equals("")) {
					try {

						String sql = "select * from Courses where Semester= ? and courseID = ?";
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setString(1, Semestertxt);
						statement.setString(2, CourseIDtxt);

						ResultSet rs = statement.executeQuery();
						if(rs.next()== false) 
						{
							rs.getString(0);
						}
						else
						{
						out.println("<html>\n" + "<head><title>" + "Course Registration" + "</title>\n"
								+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n"
								+ "<style>frm_back:hover{	box-shadow: none;background-color:  #4BA6FF !important;}</style>"
								+ "</head>\n"
								+ "<body bgcolor=\"#BED7C0\" style=\"font-family: 'Open Sans', sans-serif; color: #E6E6E6\">\n"
								+ "<div class=\"div1\" style=\"margin: auto;width: 419px;padding: 10px;background-color: #297373;border-radius: 6px;\">"
								+ "<div style=\"align-content: space-evenly; margin: auto; width: 45%;\">"
								+ "<i class=\"fa  fa-list\" style=\"font-size: 150px;\"></i>"
								+ "</div>"	
								+ "<h1 align=\"center\">Success</h1><br>" + rs);
						while (rs.next()) {
							out.println("<div>You are registered in " + rs.getString(3) + " for " + rs.getString(2)
									+ ".</div>");
						}


						con.close();
						}
					} catch (SQLException e) {
						request.setAttribute("message", "<i class=\"fa fa-warning\"></i>ERROR: " + e); 
						request.getRequestDispatcher("Main.jsp").forward(request, response);
					}

				} else if (Semestertxt != null && CourseIDtxt != null) {

					String sql = "select * from Courses where Semester= ? and CourseID = ?";
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, Semestertxt);
					statement.setString(2, CourseIDtxt);

					ResultSet rs = statement.executeQuery();
					if (rs.next() == false) {

						request.setAttribute("message",
								"<i class=\"fa fa-warning\"></i>The course is not offered."); 
						request.getRequestDispatcher("Main.jsp").forward(request, response);

					} else {

						out.println("<html>\n" + "<head><title>" + "Course Registration" + "</title>\n"
								+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n"
								+ "<style>frm_back:hover{	box-shadow: none;background-color:  #4BA6FF !important;}</style>"
								+ "</head>\n"
								+ "<body bgcolor=\"#BED7C0\" style=\"font-family: 'Open Sans', sans-serif; color: #E6E6E6\">\n"
								+ "<div class=\"div1\" style=\"margin: auto;width: 419px;padding: 10px;background-color: #297373;border-radius: 6px;\">"
								+ "<div style=\"align-content: space-evenly; margin: auto; width: 35%;\">"
								+ "<i class=\"fa  fa-list\" style=\"font-size: 150px;\"></i>"
								+ "</div>"	
								+ "<h1 align=\"center\">Success</h1><br>");
						// while (rs.next()) {
						out.println("<div>You are registered in " + rs.getString(3) + " for " + rs.getString(2)
								+ ".</div>");
						// }
						out.println("</body></html>");
					}

				}
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
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

