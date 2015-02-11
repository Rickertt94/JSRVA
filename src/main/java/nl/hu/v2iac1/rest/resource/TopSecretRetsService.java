package nl.hu.v2iac1.rest.resource;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.hu.v2iac1.Configuration;

public class TopSecretRetsService extends HttpServlet {

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
		String uName = req.getParameter("login");
		String pWord = req.getParameter("password");
		Configuration configuration = new Configuration();
		
		if(req.getParameter("commit") != null){
			String name = configuration.getValue(Configuration.Key.UNAME);
			String word = configuration.getValue(Configuration.Key.PWORD);
			String ses = configuration.getValue(Configuration.Key.SES);
			
			if(uName.equals(name) && pWord.equals(word)){
				
				String email = configuration.getValue(Configuration.Key.EMAIL);
				String pword = configuration.getValue(Configuration.Key.PASW);
				String email2 = configuration.getValue(Configuration.Key.EMAIL2);
				String vcode = configuration.getValue(Configuration.Key.VCODE);
				
				//verstuur validatie email
				final String username = email;
				final String password = pword;
		 
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
		 
				Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		 
				try {
		 
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(email));
					message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(email2));
					message.setSubject("Verification Code");
					message.setText("Your code: " + vcode);
		 
					Transport.send(message);
		 
					System.out.println("Done");
		 
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
				
				
				req.getSession().setAttribute("user", ses);
				
				RequestDispatcher rd = null;
				rd = req.getRequestDispatcher("login2_1.html");
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
