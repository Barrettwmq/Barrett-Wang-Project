import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		BufferedReader br = request.getReader();
		PrintWriter pw = response.getWriter();
		String info = br.readLine();
		Gson gson = new Gson();
		
		JsonObject user = JsonParser.parseString(info).getAsJsonObject();
		String username = user.get("username").getAsString();
		String password = user.get("password").getAsString();
		
		// Check if the user is valid in DB
		if(JDBCConnector.Login(username, password)) {
			pw.write(gson.toJson("User logged in"));
			Cookie cookie = new Cookie(username, "LoggedIn");
			response.addCookie(cookie);
		}
		else {
			pw.write(gson.toJson("Invalid Username or Password"));
		}
		
		pw.flush();
	}

}