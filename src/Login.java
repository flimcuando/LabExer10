

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public boolean isValid(String uname, String pw)
    {
    	try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/AMP_DATABASE", "root", "D0ms@nity");
			Statement stmt1 = con.createStatement();
            ResultSet rs = stmt1.executeQuery("SELECT username,password FROM USERACC WHERE '"+ uname +"'=username");
            rs.next();
            if(uname.compareTo(rs.getString(1))==0 && pw.compareTo(rs.getString(2))==0)
            { 
            	return true;
            }
            else return false; 
		} catch (Exception e) {System.out.println(e); return false;}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("name");
		String password = request.getParameter("pword");
		
		if (isValid(username, password)) 
		{
			response.sendRedirect("services.html");  
			System.out.println(username + " has signed in.");
		}
		
		else
		{
			response.setContentType("text/html");
			PrintWriter out=response.getWriter(); 
			out.print("<script>alert('Wrong username or password');</script>");
			response.sendRedirect("login.html");  
			
			
		}
	}

}
