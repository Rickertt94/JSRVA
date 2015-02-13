package nl.hu.v2iac1.rest.resource;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.hu.v2iac1.Configuration;

public class VerySecretRetsService extends HttpServlet  {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Configuration configuration = new Configuration();
		String ses = configuration.getValue(Configuration.Key.SES2);
		req.getSession().setAttribute("user", ses);
		
		RequestDispatcher rd = null;
		rd = req.getRequestDispatcher("rest/verysecret/");
		rd.forward(req, resp);
	}
	
}
