package nl.hu.v2iac1.rest.resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.hu.v2iac1.Configuration;

public class SecretRetsService extends HttpServlet {


	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uName = req.getParameter("login");
		String pWord = req.getParameter("password");
		Configuration configuration = new Configuration();
		
		if(req.getParameter("commit") != null){
			String name = configuration.getValue(Configuration.Key.UNAME);
			String word = configuration.getValue(Configuration.Key.PWORD);
			String ses = configuration.getValue(Configuration.Key.SES);
			
			if(uName.equals(name) && pWord.equals(word)){
				
				req.getSession().setAttribute("user", ses);
				
				RequestDispatcher rd = null;
				rd = req.getRequestDispatcher("rest/secret");
				rd.forward(req, resp);
			}
			else{
				RequestDispatcher rd = null;
				rd = req.getRequestDispatcher("http://www.google.nl");
				resp.sendRedirect("http://www.google.nl");
				rd.forward(req, resp);
			}
		}
	}
}
