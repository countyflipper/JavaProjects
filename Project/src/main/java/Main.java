import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Main")
public class Main extends jakarta.servlet.http.HttpServlet implements jakarta.servlet.Servlet {
  static final long serialVersionUID = 1L;
   
  public Main() {
    super();
  }   	
	
  protected void doGet(HttpServletRequest request, 
    HttpServletResponse response) throws ServletException, IOException{
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String title = "Reading Three Request Parameters";
	  
    out.println(
"<html>\n" +
	"<head><title>" + title + "</title></head>\n" +
	"<body bgcolor=\"#ff5e6\">\n" +
	"<h1 align=\"center\">" + title + "</h1>\n" +
	"<ul>\n" +	
	"  <li><b>param1</b>: " + request.getParameter("param1") + "\n" +
	"  <li><b>param2</b>: " + request.getParameter("param2") + "\n" +
	"  <li><b>param3</b>: " + request.getParameter("param3") + "\n" +
	"</ul>\n" +
	"</body></html>");
  }  	
}
