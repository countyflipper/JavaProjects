import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Main2")
public class Main2 extends jakarta.servlet.http.HttpServlet implements jakarta.servlet.Servlet {
	static final long serialVersionUID = 1L;

	public int radio = -1;
	public String m_default_gender = "<b><font color='#FF8552'>Please choose a gender.</font></b>";
	public String gender = "";
	public HttpServletRequest request;
	public String error1 = "";
	public String error2 = "";
	public String m_gender = "";
	public String title = "Welcome to my shop";

	public Main2() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		radio = -1;

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		GetFirstName(request, response);
		GetLastName(request, response);
		GetRadioButton(request, response);
		if (request.getParameter("param1").isEmpty() || request.getParameter("param2").isEmpty() || radio == -1) {
			out.println("<html>\n" + "<head><title>" + title + "</title>\n"
					+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n"
					+ "<style>frm_back:hover{	box-shadow: none;background-color:  #4BA6FF !important;}</style>"
					+ "</head>\n"
					+ "<body bgcolor=\"#BED7C0\" style=\"font-family: 'Open Sans', sans-serif; color: #E6E6E6\">\n"
					+ "<div class=\"div1\" style=\"margin: auto;width: 419px;padding: 10px;background-color: #297373;border-radius: 6px;\">"
					+ "<h1 align=\"center\">" + title + "</h1>\n" + "<ul>\n"
					+ "<div style=\"align-content: space-evenly;margin: auto;width: 45%;\"><i class=\"fa fa-user-times\" style=\"font-size: 150px;\"></i></div>");
			
			if (request.getParameter("param1").isEmpty()) {
				out.println(error1 + "<br>");
			}

			if (request.getParameter("param2").isEmpty()) {
				out.println(error2 + "<br>");
			}
			if (radio == -1) {
				out.println(m_default_gender + "<br>");
			}
			out.println("</ul>\n" + "<button class=\"frm_back\" type=\"button\" name=\"frm_back\" onclick=\"history.back()\" style=\"background: dodgerblue;border: none;border-radius: 5px;box-sizing: border-box;color: #fff;font-size: 0.88889em;font-weight: 700;line-height: 1.2;outline: none;padding: 0.76rem 1rem;cursor: pointer;\">Go Back</button>"
			+ "</div>" 
			+"</body></html>");

		} else {
			PrintFullResponse(request, response);
		}

	}

	private void PrintFullResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
		out.println("<html>\n" + "<head><title>" + title + "</title>\n"
				+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n"
				+ "<style>frm_back:hover{	box-shadow: none;background-color:  #4BA6FF !important;}</style>"
				+ "</head>\n"
				+ "<body bgcolor=\"#BED7C0\" style=\"font-family: 'Open Sans', sans-serif;color: #E6E6E6;\">\n"
				+ "<div class=\"div1\" style=\"margin: auto;width: 30%;padding: 10px;background-color: #297373;border-radius: 6px;\">"
				+ "<h1 align=\"center\">" + title + "</h1>\n"
				+ "<div style=\"align-content: space-evenly;margin: auto;width: 35%;\"><i class=\"fa   fa-check-circle-o\" style=\"font-size: 150px; color: chartreuse\"></i></div>"
				+ "<div style=\"text-align: center;font-size: 20px;font-weight: bold;\">SUCCESS!</div>"
				+ "<ul>\n" + m_gender + request.getParameter("param1") + " " + request.getParameter("param2") + "."
				+ "</ul>\n" 
				+ "<button class=\"frm_back\" type=\"button\" name=\"frm_back\" onclick=\"history.back(0)\" style=\"background: dodgerblue;border: none;border-radius: 5px;box-sizing: border-box;color: #fff;font-size: 0.88889em;font-weight: 700;line-height: 1.2;outline: none;padding: 0.76rem 1rem;cursor: pointer;\">Log Out</button>"
				+ "</div>" 
				+ "</body></html>");
	}

	private void GetRadioButton(HttpServletRequest request, HttpServletResponse response) {
		try {
			radio = Integer.parseInt(request.getParameter("rd"));

			switch (radio) {
			case 0:
				m_gender = "Thank you Mr. ";
				break;
			case 1:
				m_gender = "Thank you Ms. ";
				break;
			case 2:
				m_gender = "Thank you ";
				break;
			default:
				// m_gender = "<b><font color='#FF8552'>Please choose a gender.</font></b>";
				throw new Exception();
			}

		} catch (Exception e) {
			m_gender = "<b><font color='#FF8552'>Please choose a gender.</font></b>";
		}
	}

	private void GetFirstName(HttpServletRequest request, HttpServletResponse response) {
		try {

			if (request.getParameter("param1").isEmpty()) {
				error1 = "";
				throw new NullPointerException();
			} else {
				error1 = "";
			}
		} catch (Exception e) {

			error1 = "<b><font color='#FF8552'>Please enter in your first name.</font></b>";
		}
	}

	private void GetLastName(HttpServletRequest request, HttpServletResponse response) {

		try {

			if (request.getParameter("param2").isEmpty()) {

				throw new NullPointerException();
			} else {
				error2 = "";
			}
		} catch (Exception e) {
			error2 = "<b><font color='#FF8552'>Please enter in your last name.</font></b> ";
		}
	}

}
