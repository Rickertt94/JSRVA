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

public class TopSecretRetsService2 extends HttpServlet {

	//Geen idee of dit werkt
	/*  public enum Key {
	        PUBLIC, SECRET, TOPSECRET, VERYSECRET, UNAME, PWORD, SES
	    }

	    private final static String propFileName = "local.properties";
	    private Map<Key, String> parameters;

	    private Map<Key, String> getPropertyValues() throws IOException {
	        Map<Key, String> map = new HashMap<Key, String>();
	        Properties prop = new Properties();

	        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

	        if (inputStream != null) {
	            prop.load(inputStream);
	        } else {
	            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
	        }
	        for (Key key : Key.values()) {
	            String value;
	            if (null != (value = prop.getProperty(key.toString().toLowerCase()))) {
	                map.put(key, value);
	            }
	        }
	        return map;
	    }

	    public String getValue(Key key) {
	        if (parameters == null) {
	            try {
	                parameters = getPropertyValues();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return (parameters == null ? "" : parameters.get(key));
	    }
	*/

	// Hier onder werkt waarschijnlijk wel 
	
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Configuration configuration = new Configuration();
		
		if(req.getParameter("commit") != null){
			String vcode = configuration.getValue(Configuration.Key.VCODE);
			String ses = configuration.getValue(Configuration.Key.SES);
			String uName = req.getParameter("validate");
			
			if(vcode.equals(uName)){
				
				req.getSession().setAttribute("user", ses);
				
				RequestDispatcher rd = null;
				rd = req.getRequestDispatcher("rest/topsecret");
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
